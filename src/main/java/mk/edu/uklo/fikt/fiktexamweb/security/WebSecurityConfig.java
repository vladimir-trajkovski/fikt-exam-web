package mk.edu.uklo.fikt.fiktexamweb.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import mk.edu.uklo.fikt.fiktexamweb.util.CustomUserDetailsService;
import mk.edu.uklo.fikt.fiktexamweb.util.UserBL;
import mk.edu.uklo.fikt.fiktexamweb.util.UserRepository;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomUserDetailsService userDetailsService;

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
		return new AuthenticationHandler();
	}


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(getPasswordEncoder());
		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(11);
	}




	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.csrf().disable();
		http
				.authorizeRequests()
				.antMatchers("/user/get/**").permitAll()
				.anyRequest().authenticated()
				.and()
//				.httpBasic();
				.formLogin().loginPage("/user/login")
//				.successForwardUrl("/user/adminform").permitAll()
				.successHandler(myAuthenticationSuccessHandler()).permitAll()
//				.and().logout().logoutUrl("/login.html")
			;
		}
	
}
