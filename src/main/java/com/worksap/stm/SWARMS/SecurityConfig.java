package com.worksap.stm.SWARMS;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.worksap.stm.SWARMS.utils.PasswordHash;
import com.worksap.stm.SWARMS.utils.UserAuthenticationSuccessHandler;


@Configuration
@EnableWebMvcSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService ;
	
	RequestMatcher csrfRequestMatcher = new RequestMatcher() {

		private String allowedMethod = "GET";

		
		private AntPathRequestMatcher[] requestMatchers = {
				new AntPathRequestMatcher("/login"),
				new AntPathRequestMatcher("/logout"),
				new AntPathRequestMatcher("/hello"),
				new AntPathRequestMatcher("/admin/**"),
				 };


		@Override
		public boolean matches(HttpServletRequest request) {
			for (AntPathRequestMatcher rm : requestMatchers) {
				if (rm.matches(request)) {
					return false;
				}
			}
			if (request.getMethod().equals(allowedMethod)) {
				return false;
			}
			return true;
		}

	};

	
	
	UserAuthenticationSuccessHandler successHandler = new UserAuthenticationSuccessHandler();

//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//            .authorizeRequests()
//            	.antMatchers("/resources/**", "/").permitAll()
//                .and()
//            .formLogin()
//            	.successHandler(successHandler)
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout().permitAll();
//            
//    }
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            	.antMatchers("/resources/**", "/login/**").permitAll()
            	.anyRequest().authenticated().and()
            .formLogin()
            	.successHandler(successHandler)
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().permitAll();
            
    }
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().requireCsrfProtectionMatcher(csrfRequestMatcher).and()
//				.authorizeRequests().antMatchers("/resources/**", "/login/**")
//				.permitAll().anyRequest().authenticated().and().formLogin()
//				.loginPage("/login").failureUrl("/login")
//				.usernameParameter("userId").permitAll().and().logout()
//				.logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
//	}
//
	
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//            	.anyRequest().permitAll();
//        
//        http.csrf().disable();
//                
//            
//    }
	
	
	/*@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordHash());
	}*/
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordHash());
	}
	
//	@Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		  auth.inMemoryAuthentication().withUser("ram").password("ram123").roles("ADMIN");
//		  auth.inMemoryAuthentication().withUser("ravan").password("ravan123").roles("USER");
//		  auth.inMemoryAuthentication().withUser("kans").password("kans123").roles("USER");
//    }
}


