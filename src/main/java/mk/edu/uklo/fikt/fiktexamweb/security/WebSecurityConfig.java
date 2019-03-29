package mk.edu.uklo.fikt.fiktexamweb.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import mk.edu.uklo.fikt.fiktexamweb.util.UserBL;
import mk.edu.uklo.fikt.fiktexamweb.util.UserRepository;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
		
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception{
//		authenticationMgr.inMemoryAuthentication()
//		.withUser("hristijan112").password("{noop}396285ak").authorities("ROLE_STUDENT");
		
		authenticationMgr.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username, password from User where USERNAME=?");
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/user/get/*", "/user/get*").hasRole("STUDENT")
		.and()
		//.authorizeRequests().antMatchers("/get").permitAll();
		.httpBasic();
		
		
		//temporary solution
		//http.cors().and().csrf().disable();
	}
	
}
