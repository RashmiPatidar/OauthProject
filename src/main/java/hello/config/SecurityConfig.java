/*******************************************************************************
 * Copyright (c) 2019.
 * This  code file/snippet/block, including any other configuration files,
 * is for the sole use of the Evive Health, LLC and may contain business
 * confidential and privileged information.
 * Any unauthorized review, use, disclosure or distribution is prohibited.
 ******************************************************************************/

package hello.config;

import hello.security.handler.SuccessHandler;
import javax.inject.Inject;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;


/**
 * @author evivehealth on 2019-03-04.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig  {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public SessionRegistry sessionRegistry() {
    SessionRegistry sessionRegistry = new SessionRegistryImpl();
    return sessionRegistry;
  }

  /*@Bean
  public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
    return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
  }*/

  @Bean
  public HttpSessionEventPublisher httpSessionEventPublisher() {
    return new HttpSessionEventPublisher();
  }

  @Configuration
  @EnableOAuth2Sso
  public static class DefaultWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final SuccessHandler successHandler;
    private final PasswordEncoder passwordEncoder;
    private final SessionRegistry sessionRegistry;

    @Inject
    public DefaultWebSecurityConfigurationAdapter(UserDetailsService userDetailsService,
                                                  SuccessHandler successHandler,
                                                  PasswordEncoder passwordEncoder,
                                                  SessionRegistry sessionRegistry) {
      this.userDetailsService = userDetailsService;
      this.successHandler = successHandler;
      this.passwordEncoder = passwordEncoder;
      this.sessionRegistry = sessionRegistry;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
      PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
      auth.inMemoryAuthentication()
          .withUser("spring")
          .password(encoder.encode("secret"))
          .roles("USER");
    //  auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
      http
          .csrf()
          .disable()
          .authorizeRequests()
          .antMatchers("/signin", "/example", "/images/**","/css/**","/contact").permitAll()
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginPage("/signin")
          //.loginProcessingUrl("/login")
          .successHandler(successHandler)
          .permitAll()
          .defaultSuccessUrl("/dashboard")
          .and()
          .logout()
          .logoutSuccessUrl("/example")
          .permitAll().deleteCookies();
    }
  }
}
