# EmployeeMS
This is a simple Java Based REST service for CRUD operations on Employee table in MySQL

### PREREQUISITES
Below are the prerequisites to run the application.

* Run MYSQL
* Update MYSQL Configuration in /src/main/resources/application.properties in spring.datasource.url=jdbc:mysql://<MYSQL_HOST>:<MYSQL_PORT>/employee_db?serverTimezone=UTC

### EXTRACT and BUILD PROJECT

1. Extract the project folder 
2. Run command "mvn clean install"

### HOW TO RUN As CMD

* Run "java -jar target\EmployeeMicroservice-0.0.1.jar"

### HOW TO RUN in Eclipse

1. Import project in Eclipse as Maven project
2. Run EmployeeMicroserviceApplication as 'Java Application'

### Test the CRUD URLs

* The Application will run on port 8088 as defined in /src/main/resources/application.properties
* Test below URLs on Postman or any REST Client.

1. Get all the Employees

  http://localhost:8088/employee/all
  Method = GET
  It will return the list of all employees
  
2. Create Employee
 
  http://localhost:8088/employee
  Method = POST
  Payload: {"firstName":"Shivam","lastName":"V","email":"hl@gmail.com","phone":"4545353"}
  
  It will create an employee, and return the URL with ID.
  
3. Get Single Employee by ID

  http://localhost:8088/employee/2
  Method = GET
  It will return Employee object which has ID = 2.

4. Update Employee

  http://localhost:8088/employee/2
  Method = PUT
  Payload: {"firstName":"Shivam2","lastName":"V2","email":"hl2@gmail.com","phone":"24545353"}
  
  It will update Employee ID=2, with the Payload provided.
  
5. Delete Employee

  http://localhost:8088/employee/2
  Method = DELETE
  
  It will delete Employee ID=2.
