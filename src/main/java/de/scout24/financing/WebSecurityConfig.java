package de.scout24.financing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import de.scout24.financing.service.impl.auth.ExfoUserDetailsService;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ExfoUserDetailsService exfoUserDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(exfoUserDetailsService);
//        auth.inMemoryAuthentication()
//                .withUser("user").password("is24").roles("USER").and()
//                .withUser("admin").password("is24").roles("USER", "ADMIN");
    }

    /**
     * This section defines the security policy for the app. - BASIC authentication is supported (enough for this REST-based demo) - /employees is
     * secured using URL security shown below - CSRF headers are disabled since we are only testing the REST interface, not a web one.
     *
     * <p>NOTE: GET is not shown which defaults to permitted.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
      //  .httpBasic().and()
        .authorizeRequests()
        .antMatchers("/test", "/", "/view", "/scripts/**", "/authtest", "/authenticate", "/authTest", "/logout", "/admin").permitAll()

        .anyRequest().authenticated().and()
//        .logout().clearAuthentication(true).deleteCookies("SSO", "SSO-HMAC").and()
        .logout().clearAuthentication(true).deleteCookies("SSO", "SSO-HMAC").logoutSuccessHandler(new LogoutSuccessHandler("/")) .and()
        .csrf().disable();


//        http
//                .httpBasic().and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/securedGreetings").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/securedGreetings/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PATCH, "/securedGreetings/**").hasRole("ADMIN").and()
//                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

