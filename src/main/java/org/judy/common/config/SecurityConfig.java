package org.judy.common.config;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.judy.common.security.CustomLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Configuration
@EnableWebSecurity
@Log4j
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   private final DataSource dataSource;
   
   @Bean
   public AuthenticationSuccessHandler loginSuccessHandler() {
      return new CustomLoginSuccessHandler();
   }
   
   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
   
   @Bean
   public PersistentTokenRepository persistentTokenRepository() {
      JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
      repo.setDataSource(dataSource);
      return repo;
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      
//      http.authorizeRequests()
//      .antMatchers("/sample/all").permitAll()
//      .antMatchers("/notice/list").access("hasRole('ROLE_ADMIN')")
//      .antMatchers("/notice/list").access("hasRole('ROLE_MEMBER')");
      
//      RequestMatcher csrfRequestMatcher = new RequestMatcher() {
//
//            private RegexRequestMatcher requestMatcher =
//                new RegexRequestMatcher("/admin/store/jusoPopup", null);
//
//            @Override
//            public boolean matches(HttpServletRequest request) {
//
//                if(requestMatcher.matches(request)) {
//                    return true;
//                }
//                return false;
//            }
//
//          }; // new RequestMatcher
      
      http.formLogin().loginPage("/sample/customLogin").loginProcessingUrl("/login").successHandler(loginSuccessHandler());
      
      http.logout().logoutUrl("/customLogout").invalidateHttpSession(true).deleteCookies("remember-me", "JSESSION_ID");
      
      http.rememberMe().key("zerock").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(604800);
      
      http.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {

           private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
          // regex to match your api url 
           private RegexRequestMatcher apiMatcher = new RegexRequestMatcher("/admin/store/jusoPopup", null);

           @Override
           public boolean matches(HttpServletRequest request) {
               // No CSRF due to allowedMethod
               if(allowedMethods.matcher(request.getMethod()).matches())
                   return false;

               // No CSRF due to api call
               if(request.getRequestURI().equals("/admin/store/jusoPopup"))
                   return false;

               // CSRF for everything else that is not an API call or an allowedMethod
               return true;
           }
       });
   
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {

      log.info("JDBC............");
      
      String queryUser = "select mid,mpw,enabled from tbl_manager where mid = ?";
      
      String queryDetails = "select mid,auth from tbl_auth where  mid =?";
      
      auth.jdbcAuthentication()
      .dataSource(dataSource)
      .passwordEncoder(passwordEncoder())
      .usersByUsernameQuery(queryUser)
      .authoritiesByUsernameQuery(queryDetails);
   }
   
//   @Override
//   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      
//      log.info("configure........");
//      auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
//      auth.inMemoryAuthentication().withUser("member").password("$2a$10$4kT43wpBCsWhfLWqjPJka.j0mZuw0HnsrXHdC9S.AcuCJ7cuH5Q5a").roles("MEMBER");
//   }
   
   
   

}