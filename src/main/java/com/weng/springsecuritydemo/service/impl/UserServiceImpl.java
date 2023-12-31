package com.weng.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.springsecuritydemo.dto.LoginRequest;
import com.weng.springsecuritydemo.dto.RegisterRequest;
import com.weng.springsecuritydemo.entity.EnumUser;
import com.weng.springsecuritydemo.entity.TbUser;
import com.weng.springsecuritydemo.mapper.EnumUserMapper;
import com.weng.springsecuritydemo.mapper.UserMapper;
import com.weng.springsecuritydemo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

/**
* @author weng
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-12-21 15:59:46
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, TbUser>
    implements UserService/*, UserDetailsService*/
{
//    private final UserMapper userMapper;
    private final EnumUserMapper enumUserMapper;
    private final AuthenticationManager authenticationManager;

//    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;


    @Override
    public String login(LoginRequest loginRequest, HttpServletRequest request)
    {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        Authentication authenticationResponse = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        EnumUser enumUser = (EnumUser) authenticationResponse.getPrincipal();
        //Update SecurityContext with authentication information (authenticationResponse)
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authenticationResponse);
        //Set updated securityContext into session of user
        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
//        String token = jwtUtil.generateToken(enumUser);
//        return token;
        return "success";
    }

//    @Override
//    public String register(RegisterRequest registerRequest)
//    {
//        TbUser tbUser = TbUser.builder()
//                .username(registerRequest.username())
//                .password(passwordEncoder.encode(registerRequest.password()))
//                .build();
//        userMapper.insert(tbUser);
//        String token = jwtUtil.generateToken(tbUser);
//        return token;
//    }
    @Override
    public String register(RegisterRequest registerRequest)
    {
        EnumUser enumUser = EnumUser.builder()
                .username(registerRequest.username())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(registerRequest.role().name())//这里的role是枚举类型，name()方法返回枚举常量的名称
                .build();
        enumUserMapper.insert(enumUser);
//        String token = jwtUtil.generateToken(enumUser);
//        return token;
        return "success";
    }

}




