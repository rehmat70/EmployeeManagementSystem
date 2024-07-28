package com.employees.management.system;

import com.employees.management.system.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@RequiredArgsConstructor
//@OpenAPIDefinition(
//		info = @Info(
//				title = "Employees Management System API",
//				version = "1.0",
//				description = "API documentation for the Employees Management System"
//		),
//		security = @SecurityRequirement(name = "basicAuth")
//)
//@SecurityScheme(
//		name = "basicAuth",
//		type = SecuritySchemeType.HTTP,
//		scheme = "basic",
//		in = SecuritySchemeIn.HEADER
//)
public class EmployeeManagement implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagement.class, args);
	}

	@Autowired
	private final UserRepository userRepository;

//	@Bean
//	public GroupedOpenApi employeeApi() {
//		return GroupedOpenApi.builder()
//				.group("employeeApi")
//				.packagesToScan("com.employees.management.system")
//				.build();
//	}

	@Override
	public void run(String... args) throws Exception {

//		User user=new User();
//		user.setUserEmail("rehmat8194@gmail.com");
//		user.setPassword("$2a$12$dHDIrsDXwRSkYgZzmjoFPuzA6C93gRovVbovblNwtccwOqkGk1L.a");
//		user.setRole("employeeUser");
//
//
//		User user1 =new User();
//		user1.setUserEmail("Abid@gmail.com");
//		user1.setPassword("$2a$12$/GE1nasGBSmyN9BGl06WYuG23PKVEZj8XyMXuSMfo.3K.CUxCA0/a");
//		user1.setRole("DepUser");
//
//		User user2=new User();
//		user2.setUserEmail("Ihsan@gmail.com");
//		user2.setPassword("$2a$12$ndY7I0ytC0D1ASKycV3IWuC0I3x65YVjKWNtO9R/R0kpOZuPIOK.6");
//		user2.setRole("PayrollUser");
//
//
//		userRepository.save(user);
//		userRepository.save(user1);
//		userRepository.save(user2);

	}
}
