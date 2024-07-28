package com.employees.management.system.security;

import com.employees.management.system.jwtimplementation.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final CustomUserDetailsService userDetailsService;

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //.csrf(csrf-> csrf.disable())  //Replace Lambda with method
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/Jwt/api/login").permitAll()
                     //**************************************************************************************************
                        .requestMatchers(HttpMethod.POST, "/Info/post/save").hasAuthority("employeeUser")
                        .requestMatchers(HttpMethod.POST, "/Info/post/saveAll").hasAuthority("employeeUser")
                        .requestMatchers(HttpMethod.GET, "/Info/get/{id}").hasAuthority("employeeUser")
                        .requestMatchers(HttpMethod.GET, "/Info/getAll").hasAuthority("employeeUser")
                        .requestMatchers(HttpMethod.GET, "/Info/get/After").hasAuthority("employeeUser")
                        .requestMatchers(HttpMethod.GET,"/Info/get/Between").hasAuthority("employeeUser")
                        .requestMatchers(HttpMethod.DELETE, "/Info/Delete/{empId}").hasAuthority("employeeUser")
                        .requestMatchers(HttpMethod.PUT, "/Info/update/Entity/{id}").hasAuthority("employeeUser")
                      //**************************************************************************************************
                        .requestMatchers(HttpMethod.POST, "/Dep/post/save").hasAuthority("DepUser")
                        .requestMatchers(HttpMethod.GET, "/Dep/get/{id}").hasAuthority("DepUser")
                        .requestMatchers(HttpMethod.GET, "/Dep/get/salaryRangeBetween").hasAuthority("DepUser")
                        .requestMatchers(HttpMethod.GET, "/Dep/getAllDepartmentList").hasAuthority("DepUser")
                        .requestMatchers(HttpMethod.PUT, "/Dep/updateDepartment/{id}").hasAuthority("DepUser")
                        .requestMatchers(HttpMethod.DELETE, "/Dep/departmentDeletion/{id}").hasAuthority("DepUser")
                     //**************************************************************************************************
                        .requestMatchers(HttpMethod.POST, "/Payroll/post/save").hasAuthority("PayrollUser")
                        .requestMatchers(HttpMethod.GET, "/Payroll/getByPayrollStatus").hasAuthority("PayrollUser")
                        .requestMatchers(HttpMethod.GET, "/Payroll/getByEmpId/{id}").hasAuthority("PayrollUser")
                        .requestMatchers(HttpMethod.GET, "/Payroll/getByPayrollId/{id}").hasAuthority("PayrollUser")
                        .requestMatchers(HttpMethod.GET, "/Payroll/getAllPayrollEntity").hasAuthority("PayrollUser")
                        .requestMatchers(HttpMethod.GET, "/Payroll/getByGrmeaterThanBasicSalary").hasAuthority("PayrollUser")
                        .requestMatchers(HttpMethod.GET, "/Payroll/getByLessThanBasicSalary").hasAuthority("PayrollUser")
                        .requestMatchers(HttpMethod.PUT, "/Payroll/update/RequestParam").hasAuthority("PayrollUser")
                        .requestMatchers(HttpMethod.DELETE, "/Payroll/DeletePayroll/{id}").hasAuthority("PayrollUser")
                        .anyRequest()
                        .authenticated())
                        .sessionManagement(ex -> ex.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(authenticationProvider())
                       // .authenticationManager(authenticationManager(new AuthenticationConfiguration()))
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
               http
                       .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}










































