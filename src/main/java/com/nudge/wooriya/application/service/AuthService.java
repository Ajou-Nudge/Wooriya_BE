package com.nudge.wooriya.application.service;

import com.nudge.wooriya.common.OAuth.dto.OAuthJoinDto;
import com.nudge.wooriya.common.config.security.JwtTokenProvider;
import com.nudge.wooriya.common.config.security.SecurityUtil;
import com.nudge.wooriya.common.config.security.TokenInfo;
import com.nudge.wooriya.application.port.out.IndividualDAO;
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
    private final IndividualDAO individualDAO;
    private final CompanyRepository companyRepository;
    private final OrganizationRepository organizationRepository;
    private final EmailConfirmRepository emailConfirmRepository;
    private final IndividualRepository individualRepository;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, IndividualDAO individualDAO,
                       CompanyRepository companyRepository,
                       OrganizationRepository organizationRepository,
                       EmailConfirmRepository emailConfirmRepository,
                       IndividualRepository individualRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.individualDAO = individualDAO;
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