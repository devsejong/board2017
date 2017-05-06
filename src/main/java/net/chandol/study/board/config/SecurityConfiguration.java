package net.chandol.study.board.config;

import net.chandol.study.board.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired UserService userService;

    // userDetails를 조회로직 설정.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // @formatter:off
        httpSecurity
            .authorizeRequests()
                .anyRequest().permitAll()
                .and()
            .formLogin()
                .and()
            .csrf()
                .csrfTokenRepository(csrfTokenRepository())
                .ignoringAntMatchers("/h2-console/**")
             .and()
                .headers().frameOptions().disable();

        // @formatter:on
    }

    // CSRF공격(https://namu.wiki/w/CSRF) 대응
    private CsrfTokenRepository csrfTokenRepository() {
        CookieCsrfTokenRepository csrfTokenRepository = new CookieCsrfTokenRepository();
        csrfTokenRepository.setCookieHttpOnly(true);
        return csrfTokenRepository;
    }
}
