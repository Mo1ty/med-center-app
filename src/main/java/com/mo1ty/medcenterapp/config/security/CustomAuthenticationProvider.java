package com.mo1ty.medcenterapp.config.security;

import com.mo1ty.medcenterapp.entity.LoginData;
import com.mo1ty.medcenterapp.repository.LoginDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    LoginDataRepository loginDataRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider(LoginDataRepository loginDataRepository, PasswordEncoder passwordEncoder) {
        this.loginDataRepository = loginDataRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String passwd = authentication.getCredentials().toString();
        List<LoginData> loginData = loginDataRepository.findByEmail(email);
        if(loginData.size() > 0) {
            if(passwordEncoder.matches(passwd, loginData.get(0).getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(loginData.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(email, passwd, authorities);
            }
            else {
                throw new BadCredentialsException("Invalid password!");
            }
        }
        else {
            throw new BadCredentialsException("This email is not registered!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
