I have developed a robust Java Spring Boot application named employeeManagementSystem utilizing Maven. This system manages three primary entities: EmployeeInfo, EmpDepartment, and EmpPayroll. Here's a detailed overview of the repository structure and the implemented functionalities:

Entity Classes and Repositories
Entities:

EmployeeInfo: Contains employee information.
EmpDepartment: Represents the department associated with an employee.
EmpPayroll: Handles payroll information for employees.
Repositories:

Separate repository interfaces for each entity to handle database operations using Spring Data JPA.
Service Layer
ServiceDao Interface: Defines CRUD operation methods for each entity.
ServiceDaoImpl Class: Implements the ServiceDao interface, providing the business logic for CRUD operations.
Controllers
Separate controllers for each entity to expose REST APIs for all CRUD operations.
Each REST API returns proper HTTP status codes to ensure robust communication with the clients.
Exception Handling
Custom Exception Classes:

EmployeeNotFoundException
EmployeeAlreadyExistException
DepartmentNotFoundException
DepartmentAlreadyExistException
PayrollNotFoundException
PayrollAlreadyExistException
Global Exception Handler: Centralized handling of exceptions to provide meaningful error responses to the clients.

Entity Relationships
EmployeeInfo and EmpDepartment: One-to-One relationship managed with @JsonManagedReference and @JsonBackReference annotations.
EmployeeInfo and EmpPayroll: One-to-Many relationship (EmployeeInfo) and Many-to-One relationship (EmpPayroll) managed with @JsonManagedReference and @JsonBackReference annotations.
Security Implementation
Spring Security: Implemented with Basic Authentication, storing usernames and hashed passwords in the database using BcryptPasswordEncoder.
JWT (JSON Web Token): Added for enhanced security of the APIs, ensuring secure and stateless authentication mechanisms.
