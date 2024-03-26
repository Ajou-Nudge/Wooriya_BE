package com.nudge.wooriya.application.domain;

import com.nudge.wooriya.common.OAuth.dto.OAuthJoinDto;
import com.nudge.wooriya.common.config.security.JwtTokenProvider;
import com.nudge.wooriya.common.config.security.SecurityUtil;
import com.nudge.wooriya.common.config.security.TokenInfo;
import com.nudge.wooriya.application.port.out.CompanyDAO;
import com.nudge.wooriya.application.port.out.IndividualDAO;
import com.nudge.wooriya.application.port.out.OrganizationDAO;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Company;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.EmailConfirm;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Individual;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Organization;
import com.nudge.wooriya.adapter.out.persistence.Repo.CompanyRepository;
import com.nudge.wooriya.adapter.out.persistence.Repo.EmailConfirmRepository;
import com.nudge.wooriya.adapter.out.persistence.Repo.IndividualRepository;
import com.nudge.wooriya.adapter.out.persistence.Repo.OrganizationRepository;

import com.nudge.wooriya.application.port.in.Auth.AuthUsecase;
import com.nudge.wooriya.application.port.in.Auth.dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthUsecase {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final CompanyDAO companyDAO;
    private final IndividualDAO individualDAO;
    private final OrganizationDAO organizationDAO;
    private final CompanyRepository companyRepository;
    private final OrganizationRepository organizationRepository;
    private final EmailConfirmRepository emailConfirmRepository;
    private final IndividualRepository individualRepository;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, CompanyDAO companyDAO, IndividualDAO individualDAO, OrganizationDAO organizationDAO,
                       CompanyRepository companyRepository,
                       OrganizationRepository organizationRepository,
                       EmailConfirmRepository emailConfirmRepository,
                       IndividualRepository individualRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.companyDAO = companyDAO;
        this.individualDAO = individualDAO;
        this.organizationDAO = organizationDAO;
        this.companyRepository = companyRepository;
        this.organizationRepository = organizationRepository;
        this.emailConfirmRepository = emailConfirmRepository;
        this.individualRepository = individualRepository;
    }

    @Override
    public TokenInfo Userlogin(UserLoginDto userLoginDto) {
        if(!userLoginDto.getIsCompany()) {
            Organization organization = organizationRepository
                    .findByEmail(userLoginDto.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("아이디 혹은 비밀번호를 확인하세요."));
            Boolean matches = passwordEncoder.matches(userLoginDto.getPassword(), organization.getPassword());
            if (!matches) throw new BadCredentialsException("아이디 혹은 비밀번호를 확인하세요.");

            Authentication authentication = new UsernamePasswordAuthenticationToken(organization.getUsername(), organization.getPassword(), organization.getAuthorities());
            TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
            tokenInfo.setEmail(organization.getEmail());
            tokenInfo.setMemberRole(organization.getRole());
            return tokenInfo;
        }
        else {
            Company company = companyRepository
                    .findByEmail(userLoginDto.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("아이디 혹은 비밀번호를 확인하세요."));
            Boolean matches = passwordEncoder.matches(userLoginDto.getPassword(), company.getPassword());
            if (!matches) throw new BadCredentialsException("아이디 혹은 비밀번호를 확인하세요.");

            Authentication authentication = new UsernamePasswordAuthenticationToken(company.getUsername(), company.getPassword(), company.getAuthorities());
            TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
            tokenInfo.setEmail(company.getEmail());
            tokenInfo.setMemberRole(company.getRole());
            return tokenInfo;
        }
    }

    @Override
    public Boolean companyJoin(CompanyJoinDto companyJoinDto) throws Exception {
        Boolean isExists = isExistsMember(companyJoinDto.getEmail());
        EmailConfirm emailConfirm = emailConfirmRepository.findById(companyJoinDto.getEmail()).orElseThrow(() -> new Exception("이메일 인증을 진행해주세요"));
        if(emailConfirm != null && !isExists) {
            Company company = new Company();
            company.setEmail(companyJoinDto.getEmail());
            company.setPassword(passwordEncoder.encode(companyJoinDto.getPassword()));
            company.setCompanyName(companyJoinDto.getCompanyName());
            company.setCompanyAddress(companyJoinDto.getCompanyAddress());
            company.setRepresentativeName(companyJoinDto.getRepresentativeName());
            company.setRepresentativeNum(companyJoinDto.getRepresentativeNum());
            company.setKind(companyJoinDto.getKind());
            company.setHistory(companyJoinDto.getHistory());
            company.setGreetings(companyJoinDto.getGreetings());
            companyDAO.join(company);
            emailConfirmRepository.deleteById(companyJoinDto.getEmail());
            return true;
        }
        return false;
    }

    @Override
    public Boolean organizationJoin(OrganizationJoinDto organizationJoinDto) throws Exception {
        Boolean isExists = isExistsMember(organizationJoinDto.getEmail());
        EmailConfirm emailConfirm = emailConfirmRepository.findById(organizationJoinDto.getEmail()).orElseThrow(() -> new Exception("이메일 인증을 진행해주세요"));
        if(emailConfirm != null && !isExists) {
            Organization organization = new Organization();
            organization.setEmail(organizationJoinDto.getEmail());
            organization.setPassword(passwordEncoder.encode(organizationJoinDto.getPassword()));
            organization.setOrganizationName(organizationJoinDto.getOrganizationName());
            organization.setRepresentativeName(organizationJoinDto.getRepresentativeName());
            organization.setRepresentativeNum(organizationJoinDto.getRepresentativeNum());
            organization.setRepresentativeEmail(organizationJoinDto.getRepresentativeEmail());
            organization.setKind(organizationJoinDto.getKind());
            organization.setHistory(organizationJoinDto.getHistory());
            organization.setGreetings(organizationJoinDto.getGreetings());
            organizationDAO.join(organization);
            emailConfirmRepository.deleteById(organizationJoinDto.getEmail());
            return true;
        }
        return false;
    }

    @Override
    public Boolean individualJoin(IndividualJoinDto individualJoinDto) throws Exception {
        Boolean isExists = isExistsMember(individualJoinDto.getEmail());
        EmailConfirm emailConfirm = emailConfirmRepository.findById(individualJoinDto.getEmail()).orElseThrow(() -> new Exception("이메일 인증을 진행해주세요"));
        if(emailConfirm != null && !isExists) {
            Individual individual = new Individual();
            individual.setEmail(individualJoinDto.getEmail());
            individual.setPassword(passwordEncoder.encode(individualJoinDto.getPassword()));
            individual.setKind(individualJoinDto.getKind());
            individual.setName(individualJoinDto.getName());
            individual.setPhoneNum(individualJoinDto.getPhoneNum());
            individual.setUserId(individualJoinDto.getUserId());

            individualDAO.join(individual);
            emailConfirmRepository.deleteById(individualJoinDto.getEmail());
            return true;
        }
        return false;
    }

    private Boolean isExistsMember(String email) {
        return companyRepository.existsByEmail(email) || organizationRepository.existsByEmail(email) || individualRepository.existsByEmail(email);
    }

    @Override
    public UserInfoDto Userinfo() {
       return SecurityUtil.getCurrentMemberId();
    }

    @Override
    public void oAuthJoin(OAuthJoinDto oAuthJoinDto) {

    }
}