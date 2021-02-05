package com.example.restauran.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                    .antMatchers("/css/**", "/images/**",
                        "/page/*", "/login**")
                    .permitAll()
                    .antMatchers("/", "/main", "/task", "/registration", "/result")
                    .permitAll()
                    .antMatchers("/adminPage**").hasAuthority("ADMIN")
                    .antMatchers("/userCabinet**").hasAuthority("USER")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error")
                    .permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .deleteCookies("JSESSIONID")
                    .permitAll();

    }


/*   protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("123456")).authorities("USER")
                .and()
                .withUser("u").password(passwordEncoder().encode("123456")).authorities("USER")
                .and()
                .withUser("vvvv@gmail.com").password(passwordEncoder().encode("123456")).authorities("ADMIN");
    }*/

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT email AS username, password, 'true' as enabled FROM users WHERE email=?")
                .authoritiesByUsernameQuery("SELECT email AS username, role FROM users WHERE email=?");
    }


/*    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }*/
/*

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                    .antMatchers("/css/**", "/images/**",
                        "/page/*", "/login**")
                    .permitAll()
                    .antMatchers("/", "/main", "/task", "/registration", "/result")
                    .permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error")
                    .permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .deleteCookies("my-remember-me-cookie")
                    .permitAll()
                .and()
                    .rememberMe()
                .rememberMeCookieName("my-remember-me-cookie")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(24 * 60 * 60)
                .and().exceptionHandling();
    }
*/

/*    PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepositoryImp = new JdbcTokenRepositoryImpl();
        tokenRepositoryImp.setDataSource(dataSource);
        return tokenRepositoryImp;
    }*/

}
