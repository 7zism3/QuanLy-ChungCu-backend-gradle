package com.nhom43.quanlychungcubackendgradle.share.security;

import com.nhom43.quanlychungcubackendgradle.share.security.component.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] AUTH_ADMIN_LIST = {
//            "/can-ho",
//            "/cu-dan",
//            "/auth/signup",
//            "/**",
    };
    private static final String[] AUTH_USER_LIST = {
//            "/phuong-tien",
    };

    private static final String[] AUTH_LIST = {
            "/**",
    };

    private static final String[] NO_AUTH_LIST = {
            "/auth/can-ho/**",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/**",
    };

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                // Y??u c????u xa??c th???? quy????n ADMIN
                .antMatchers(AUTH_ADMIN_LIST).hasAnyAuthority("Admin")
                // Y??u c????u xa??c th???? quy????n USER
                .antMatchers(AUTH_USER_LIST).hasAnyAuthority("User")
                .antMatchers(AUTH_LIST).hasAnyAuthority("User", "Admin", "Staff_bql")
                // Kh??ng x??c th???c y??u c???u c??? th??? n??y
                .antMatchers(NO_AUTH_LIST).permitAll().anyRequest().authenticated();

        // Th??m b??? l???c ????? x??c th???c m?? Tokens v???i m???i y??u c???u
        httpSecurity.addFilterBefore(jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);
    }

}
