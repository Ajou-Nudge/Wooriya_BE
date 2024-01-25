package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.config.security.JwtTokenProvider;
import com.nudge.wooriya.config.security.SecurityUtil;
import com.nudge.wooriya.config.security.TokenInfo;
import com.nudge.wooriya.data.dao.CompanyDAO;
import com.nudge.wooriya.data.dao.OrganizationDAO;
import com.nudge.wooriya.data.dto.CompanyJoinDto;
import com.nudge.wooriya.data.dto.LoginDto;
import com.nudge.wooriya.data.dto.OrganizationJoinDto;
import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.data.entity.Company;
import com.nudge.wooriya.data.entity.EmailConfirm;
import com.nudge.wooriya.data.entity.Organization;
import com.nudge.wooriya.data.repository.CompanyRepository;
import com.nudge.wooriya.data.repository.EmailConfirmRepository;
import com.nudge.wooriya.data.repository.OrganizationRepository;
import com.nudge.wooriya.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final CompanyDAO companyDAO;
    private final OrganizationDAO organizationDAO;
    private final CompanyRepository companyRepository;
    private final OrganizationRepository organizationRepository;
    private final EmailConfirmRepository emailConfirmRepository;

    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, CompanyDAO companyDAO, OrganizationDAO organizationDAO,
                           CompanyRepository companyRepository,
                           OrganizationRepository organizationRepository,
                           EmailConfirmRepository emailConfirmRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.companyDAO = companyDAO;
        this.organizationDAO = organizationDAO;
        this.companyRepository = companyRepository;
        this.organizationRepository = organizationRepository;
        this.emailConfirmRepository = emailConfirmRepository;
    }

    @Override
    public TokenInfo login(LoginDto loginDto) {
        if(!loginDto.getIsCompany()) {
            Organization organization = organizationRepository
                    .findByEmail(loginDto.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("아이디 혹은 비밀번호를 확인하세요."));
            Boolean matches = passwordEncoder.matches(loginDto.getPassword(), organization.getPassword());
            if (!matches) throw new BadCredentialsException("아이디 혹은 비밀번호를 확인하세요.");

            Authentication authentication = new UsernamePasswordAuthenticationToken(organization.getUsername(), organization.getPassword(), organization.getAuthorities());
            TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
            tokenInfo.setEmail(organization.getEmail());
            tokenInfo.setMemberRole(organization.getRole());
            return tokenInfo;
        }
        else {
            Company company = companyRepository
                    .findByEmail(loginDto.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("아이디 혹은 비밀번호를 확인하세요."));
            Boolean matches = passwordEncoder.matches(loginDto.getPassword(), company.getPassword());
            if (!matches) throw new BadCredentialsException("아이디 혹은 비밀번호를 확인하세요.");

            Authentication authentication = new UsernamePasswordAuthenticationToken(company.getUsername(), company.getPassword(), company.getAuthorities());
            TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
            tokenInfo.setEmail(company.getEmail());
            tokenInfo.setMemberRole(company.getRole());
            return tokenInfo;
        }
    }

    @Override
    public Exception companyJoin(CompanyJoinDto companyJoinDto) throws Exception {
        EmailConfirm emailConfirm = emailConfirmRepository.findById(companyJoinDto.getEmail()).orElseThrow(() -> new Exception("이메일 인증을 진행해주세요"));
        if(emailConfirm != null) {
            Company company = new Company();
            company.setEmail(companyJoinDto.getEmail());
            company.setPassword(passwordEncoder.encode(companyJoinDto.getPassword()));
            company.setCompanyName(companyJoinDto.getCompanyName());
            company.setCompanyNum(companyJoinDto.getCompanyNum());
            company.setRepresentativeName(companyJoinDto.getRepresentativeName());
            company.setRepresentativeNum(companyJoinDto.getRepresentativeNum());
            company.setKind(companyJoinDto.getKind());
            company.setHistory(companyJoinDto.getHistory());
            company.setGreetings(companyJoinDto.getGreetings());
            companyDAO.join(company);
            emailConfirmRepository.deleteById(companyJoinDto.getEmail());
            return new Exception("회원가입 완료");
        }
        else {
            return new Exception("이메일 인증을 진행해주세요");
        }
    }

    @Override
    public Exception organizationJoin(OrganizationJoinDto organizationJoinDto) throws Exception {
        EmailConfirm emailConfirm = emailConfirmRepository.findById(organizationJoinDto.getEmail()).orElseThrow(() -> new Exception("이메일 인증을 진행해주세요"));
        if(emailConfirm != null) {
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
            return new Exception("회원가입 완료");
        }
        else {
            return new Exception("이메일 인증을 진행해주세요");
        }
    }

    @Override
    public UserInfoDto info() {
         UserInfoDto userInfoDto = SecurityUtil.getCurrentMemberId();
        return userInfoDto;
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return userRepository.findByEmail(email)
//                .map(this::createUserDetails)
//                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
//    }
//
//    private UserDetails createUserDetails(Member member) {
//        return User.builder()
//                .username(member.getUsername())
//                .password(passwordEncoder.encode(member.getPassword()))
//                .roles(member.getRole())
//                .build();
//    }
}