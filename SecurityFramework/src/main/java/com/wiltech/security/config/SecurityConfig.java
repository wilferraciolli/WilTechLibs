package com.wiltech.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.wiltech.security.jwt.JwtConfigurer;
import com.wiltech.security.jwt.JwtTokenProvider;

/**
 * The type Security config.
 * This is our application scope security config bean.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * The Jwt token providers.
     */
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    /**
     * Authentication manager bean authentication manager.
     * @return the authentication manager
     * @throws Exception the exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configure. The path for access based on roles.
     * @param http the http
     * @throws Exception the exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        //set frame options to self to allow h2 console server
        http.headers().frameOptions().sameOrigin();

        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/signin").permitAll()
                .antMatchers("/auth/register").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/eureka/**").permitAll()
                .antMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "USER")
                //                .antMatchers("/*").hasAnyRole("ADMIN", "USER")
                //                .anyRequest().authenticated()
                .anyRequest().permitAll()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
        //@formatter:on
    }

}
