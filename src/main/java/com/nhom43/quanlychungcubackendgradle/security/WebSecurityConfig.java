package com.nhom43.quanlychungcubackendgradle.security;

import com.nhom43.quanlychungcubackendgradle.security.jwt.AuthEntryPointJwt;
import com.nhom43.quanlychungcubackendgradle.security.jwt.AuthTokenFilter;
import com.nhom43.quanlychungcubackendgradle.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] AUTH_ADMIN_LIST = {
            "/can-ho",
            "/cu-dan"
//            "/auth/signup",
    };
    private static final String[] AUTH_USER_LIST = {

    };

    private static final String[] AUTH_LIST = {

    };

    private static final String[] NO_AUTH_LIST = {
            "/auth/**",
            "/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                // Yêu cầu xác thự quyền ADMIN
                .antMatchers(AUTH_ADMIN_LIST).hasRole("ADMIN")
                // Yêu cầu xác thự quyền USER
                .antMatchers(AUTH_USER_LIST).hasRole("USER")
                // Yêu cầu xác thực quyền USER hoặc ADMIN
                .antMatchers(AUTH_LIST).hasAnyRole("USER", "ADMIN")
                // Không xác thực yêu cầu cụ thể này
                .antMatchers(NO_AUTH_LIST).permitAll().anyRequest().authenticated()
                // Nếu có bất kỳ ngoại lệ nào xảy ra, sẽ gọi
                .and().exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler).and()
                // Đảm bảo rằng chúng ta sử dụng phiên không trạng thái; phiên sẽ không được sử dụng để
                // lưu trữ trạng thái của người dùng
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Thêm bộ lọc để xác thực mã Tokens với mọi yêu cầu
        http.addFilterBefore(authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }
}