package com.example.securityweb;



import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
	
	
	
	String permit[] = {"/","/aboutus","/contactus","/home","/FarmerPortal","/dashboard","/register_farmer","/registration_success","/deptdashboard","/achievements","/events","/crops","/fertilizer","/todays-bookings/book"};
	String caneyard[] = {"/caneyard","/booking/{id}/view","/confirm-booking","/todays-bookings/fetch","/schedule/confirm_booking/fetch,/schedule/today-schedules","/schedule/schedule","/schedule/schedulebooking/{bookingId}","schedule/schedule-confirm","schedule/today-weight_schedules","/weighingForm","/saveWeight"};
	

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers(permit).permitAll()
                .requestMatchers("/farmerportaldash").hasAuthority("FARMER")
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers(caneyard).hasAnyAuthority("ROLE_Caneyard","ADMIN")
                .requestMatchers("/schedule/today-crushing","/schedule/markAsCompleted").hasAnyAuthority("ROLE_Crushing","ADMIN")
                .requestMatchers("/electrical").hasAnyAuthority("ROLE_Electrical","ADMIN")
                .anyRequest().authenticated()
                )
                .formLogin(login -> login.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/welcome")
                        .permitAll())
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                        ).exceptionHandling((exception) ->exception.accessDeniedPage("/403"))
                .build();

    }
    
    @Bean
    public UserDetailsService userDetailsService(){
        return new MyUserService();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
