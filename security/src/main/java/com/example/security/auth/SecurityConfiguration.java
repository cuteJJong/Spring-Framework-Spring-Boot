package com.example.security.auth;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //보안 관련 설정 메서드 추가
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(HttpBasicConfigurer::disable)//기본 설정 disable
                .csrf(CsrfConfigurer::disable)  // 쓰기 작업 막은것을 해제, post, put...를 요청 가능
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests((authz) -> authz
                        //forward 모두 허용
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() //네트워크 도메인, ip 허용 코드 작성한 내용 적용
                        .requestMatchers("/", "/join", "/autherror", "/loginform","/login").permitAll()
                        .requestMatchers("/auth").authenticated())
                //로그인 폼 설정
                .formLogin((login) -> login
                        .loginPage("/loginform")    //로그인 폼을 주는 요청 url
                        .loginProcessingUrl("/login")   //로그인 처리 요청 url
                        .usernameParameter("id")    //id 입력양식의 이름
                        .passwordParameter("pwd")   //패스워드 입력양식의 이름
                        .defaultSuccessUrl("/", true).permitAll()   //성공 시 기본 이동 경로
                        .successHandler(new MySuccessHandler())   //성공 시 실행될 핸들러
                        .failureHandler(new MyFailureHandler())   //실패 시 실행될 핸들러
                );
        return http.build();
    }
}
