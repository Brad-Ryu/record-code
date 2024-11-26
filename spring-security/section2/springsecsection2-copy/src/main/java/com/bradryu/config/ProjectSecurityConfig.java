package com.bradryu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        /*http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());*/
        /*http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());*/
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/notices", "/contact", "/error").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    InMemoryUserDetailsManager userDetailsService() {
        // 아래 계정으로 로그인 시, The provided password is compromised, please change your password 라는 문구가 뜰거임.
        UserDetails user = User.withUsername("user").password("{noop}Brad1234").authorities("read").build();  // 로그인 실패! CompromisedPasswordChecker로 인해...

        // BradRyu@12345
        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$egvirxF8ZP9SVZ5zV9eTxuh2Ui1Io0VkGgtQRILOPvQH9og4cBKBS").authorities("admin").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        // 안에 살펴보면... bcrypt를 디폴트로 사용하는 것을 알 수 있다..
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /* spring security 6.3 이후부터 사용가능.
    * 간단한 비밀번호로 설정해서 로그인 하려는 것을 막아준다...
    *  */
    @Bean
    CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
