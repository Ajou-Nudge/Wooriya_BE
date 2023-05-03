package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.config.security.JwtTokenProvider;
import com.nudge.wooriya.config.security.TokenInfo;
import com.nudge.wooriya.data.dao.MemberDAO;
import com.nudge.wooriya.data.dto.UserInfoDto;
import com.nudge.wooriya.data.dto.UserJoinDto;
import com.nudge.wooriya.data.dto.UserLoginDto;
import com.nudge.wooriya.data.entity.Member;
import com.nudge.wooriya.data.repository.UserRepository;
import com.nudge.wooriya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.AlreadyBuiltException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberDAO memberDAO;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl (PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, MemberDAO memberDAO, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.memberDAO = memberDAO;
        this.userRepository = userRepository;
    }

    @Override
    public TokenInfo login(UserLoginDto userLoginDto) {
        Member member = userRepository
                .findByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("아이디 혹은 비밀번호를 확인하세요."));

        boolean matches = passwordEncoder.matches(userLoginDto.getPassword(), member.getPassword());
        if (!matches) throw new BadCredentialsException("아이디 혹은 비밀번호를 확인하세요.");

        Authentication authentication = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword(), member.getAuthorities());
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }

    @Override
    public Member join(UserJoinDto userJoinDto) {

        boolean isExist = userRepository.existsByEmail(userJoinDto.getEmail());
        if (isExist) throw new AlreadyBuiltException("이미 존재하는 아이디입니다.");

        Member member = new Member();
        member.setEmail(userJoinDto.getEmail());
        member.setPassword(passwordEncoder.encode(userJoinDto.getPassword()));
        member.setRole(userJoinDto.getRole());
        member.setUserName(userJoinDto.getUserName());
        member.setUserNum(userJoinDto.getUserNum());
        return memberDAO.join(member);
    }

    @Override
    public UserInfoDto info() {
        return memberDAO.info();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(Member member) {
        return User.builder()
                .username(member.getUsername())
                .password(passwordEncoder.encode(member.getPassword()))
                .roles(member.getRole())
                .build();
    }
}