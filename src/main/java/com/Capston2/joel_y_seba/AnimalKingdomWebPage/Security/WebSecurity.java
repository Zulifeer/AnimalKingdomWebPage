package com.Capston2.joel_y_seba.AnimalKingdomWebPage.Security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
    /*
    @Autowired
    BCryptPasswordEncoder encoder;
    /*
    @Autowired
    UserDetailsService userService;
    */
    @Autowired
    DataSource dataSource;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userService).passwordEncoder(encoder);
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(getEncoder())
            .usersByUsernameQuery("select username, password, enabled from users where username = ?;")
            .authoritiesByUsernameQuery("select u.username, a.authority from authorities a, role_user r, users u where u.username = ? and u.user_id = r.user_id and r.authority_id = a.authority_id;");
    }

    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests()
            .antMatchers("/", "/index", "/SingUp", "/Login").permitAll().anyRequest().authenticated().and()
            .formLogin().loginPage("/Login").permitAll().and()
            .logout().permitAll();
        
        http.authorizeRequests().antMatchers("/", "/login", "/singup").permitAll().antMatchers("/animals").hasAnyRole("ADMIN")
        .anyRequest().authenticated().and().formLogin().loginPage("/login").loginProcessingUrl("/process_login")
        .successHandler(new HandelerAuthentication()).permitAll().and().logout().permitAll();
        */
        
        http.logout().and().authorizeRequests()
        .antMatchers("/", "/login", "/signup", "/SignUp","/src/main/resources/**", "/CSS/**", "/JS/**").permitAll()
        .antMatchers("/logout").hasAnyAuthority("ADMIN", "USER")
        .antMatchers("/animals", "/sec/admin","/sec/Admin/AddEmployee","/sec/Admin/AddAnimal","/sec/Admin/AddEnviroment", "/sec/Admin/AddType").hasAuthority("ADMIN").anyRequest().denyAll()
        .and().formLogin().loginPage("/login").loginProcessingUrl("/process_login").successHandler(new HandelerAuthentication()).and()
        .sessionManagement().maximumSessions(2);

        //http.csrf().disable();
    }
    
}
