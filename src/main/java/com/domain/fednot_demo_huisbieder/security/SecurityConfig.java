package com.domain.fednot_demo_huisbieder.security;

import com.domain.fednot_demo_huisbieder.entities.Rol;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String USERS_BY_USERNAME =
            "select gebruikersnaam as username, paswoord as password, true as enabled " +
                    "from gebruikers where gebruikersnaam = ?";
    private static final String AUTHORITIES_BY_USERNAME =
//            "select gebruikersnaam as username, 'klant' as authorities from gebruikers where gebruikersnaam = ?";
            "select gebruikers.gebruikersnaam as username, rollen.naam as authorities" +
                    " from gebruikers inner join gebruikersrollen" +
                    " on gebruikers.id = gebruikersrollen.gebruikerId" +
                    " inner join rollen" +
                    " on rollen.id = gebruikersrollen.rolId" +
                    " where gebruikers.gebruikersnaam= ?";


    @Bean
    JdbcDaoImpl jdbcDaoImpl(DataSource dataSource) {
        JdbcDaoImpl impl = new JdbcDaoImpl();
        impl.setDataSource(dataSource);
        impl.setUsersByUsernameQuery(USERS_BY_USERNAME);
        impl.setAuthoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME);
        return impl;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/images/**")
                .mvcMatchers("/css/**")
                .mvcMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // disables forbidden errors for postman (post/put/delete requests)

        http.formLogin().loginPage("/login").defaultSuccessUrl("/")
                .and().logout().logoutSuccessUrl("/")
                .and().authorizeRequests()
                .mvcMatchers("/", "/login").permitAll()
                .mvcMatchers("/nieuwegebruiker/**").anonymous()
                .mvcMatchers("/nieuwegebruiker/toevoegen").authenticated()
                .mvcMatchers("toevoegen").hasAnyAuthority(Rol.NOTARIS.getNaam())
                .mvcMatchers("/**/bieden/").authenticated();
                //.mvcMatchers("/**/bieden/").hasAnyAuthority(Rol.KLANT.getNaam());
        http.httpBasic();
    }
}
