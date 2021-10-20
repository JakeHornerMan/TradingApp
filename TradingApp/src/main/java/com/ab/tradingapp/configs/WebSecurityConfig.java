package com.ab.tradingapp.configs;

import com.ab.tradingapp.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;

@Configuration
    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebSecurityConfigurer<WebSecurity> {

        @Bean
        public LocalValidatorFactoryBean validator() {
            return new LocalValidatorFactoryBean();
        }

        @Bean
        public UserDetailsService userDetailsService() {
            return new CustomUserDetailsService();
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService());
            authProvider.setPasswordEncoder(passwordEncoder());

            return authProvider;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProvider());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/index").permitAll()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/register_success").permitAll()
                    .antMatchers("/save_user").permitAll()
                    .antMatchers("/logout").permitAll()
                    .anyRequest().fullyAuthenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("user_email")
                    .passwordParameter("user_password")
                    .defaultSuccessUrl("/dashboard", true)
                    .permitAll()
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true);
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            // Solve the problem of static resources being intercepted
            web.ignoring().antMatchers("/css/**", "/images/**");
        }
    }

