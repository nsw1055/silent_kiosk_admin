package org.judy.common.config;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.judy.common.security.CustomLoginSuccessHandler;
import org.judy.common.security.Filtertest;
import org.judy.common.security.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
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
   
   @Bean
   public UserDetailsService customUserService() {
	   return new CustomUserDetailsService();
   }
   
   @Bean

   public Filtertest filterTest() throws Exception {

      Filtertest authenticationFilterAnotherParam = new Filtertest();

       authenticationFilterAnotherParam.setAuthenticationManager(this.authenticationManagerBean());

       authenticationFilterAnotherParam.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login","POST"));

       return authenticationFilterAnotherParam;

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
      
      http.rememberMe().key("judy").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(604800);
      
      http.addFilterBefore(filterTest(),UsernamePasswordAuthenticationFilter.class);
      
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

//   @Override
//   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//      log.info("JDBC............");
//      
//      String queryDetails = "select mid,auth from tbl_auth where  mid =?";
//      
//      String queryManager = "select mid,mpw,enabled from tbl_manager where mid = ?";
//      
//      auth.jdbcAuthentication()
//      .dataSource(dataSource)
//      .passwordEncoder(passwordEncoder())
//      .authoritiesByUsernameQuery(queryDetails)
//      .usersByUsernameQuery(queryManager);
//   }
   
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      
	   auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
   }
   
   
   

}