package com.example.Mongo.Service;


import com.example.Mongo.Models.Employee;
import com.example.Mongo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Simport org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import lombok.Data;
//import java.time.LocalDateTime;
import java.util.ArrayList;
//import java.util.ArrayList;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
@Service
public class PostService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public String createEmployee(List<Employee> employees) {
        List<String> errors = new ArrayList<>();
        List<String> successfullySavedEmployees = new ArrayList<>();
    
        for(Employee employee : employees) {
            // Check if the employee ID already exists
            Optional<Employee> employeeOpt = employeeRepository.findById(employee.getEmployeeId());
            if(employeeOpt.isPresent()) {
                errors.add("Employee ID " + employee.getEmployeeId() + " already exists. Please try a different employee ID.");
                continue; // Skip this employee and move to the next
            }

            if(!employee.validName()){
                errors.add("name cannot be empty or you have given a invalid name"+employee.getEmployeeId());
                continue;
            }
            if(!employee.chkDesignation()){
                errors.add("designation should not be empty or designation is not valid for the employee :"+employee.getEmployeeId());
                continue;
            }
            
            if(!employee.isValidEmail()){
                errors.add("email cant be null or you havn't given valid email"+employee.getEmployeeId());
                continue;

            }
            if(!employee.chkDepartment()){
                errors.add("department can be null "+employee.getEmployeeId());
                continue;
            }

            if(!employee.isValidMobileNum()){
                errors.add("mobile number is not valid  for the employee :"+employee.getEmployeeId());
                continue;
            }
            if(!employee.chkLocation()){
                errors.add("location cant be null"+employee.getEmployeeId());
                continue;
            }


            if(!employee.isValidManagerId()){
               errors.add("manager id is not valid for the employee :"+employee.getEmployeeId());
               continue;
            }

          
            
           
            

           
            
            if(!employee.validDateOfJoining()){
                errors.add("date of joining cant be null or date of joining is invalid"+employee.getEmployeeId());
                continue;
            }

          
            
            // Check if the designation is "Account_manager" and managerId is not zero
            // if(employee.getDesignation() != null && employee.getDesignation().equals("Account_manager") && employee.getManagerId() != 0) {
            //     errors.add("If designation is 'Account_manager', then managerId for employee ID " + employee.getEmployeeId() + " should be zero.");
            //     continue; // Skip this employee and move to the next
            // }
            // if(employee.getDesignation() != null && employee.getDesignation().equals("associate") && employee.getManagerId() == 0) {
            //     errors.add("If designation is 'associate', then managerId for employee ID " + employee.getEmployeeId() + " should be greater than zero.");
            //     continue; // Skip this employee and move to the next
            // }
            
            // Save the employee
            employeeRepository.save(employee);
            successfullySavedEmployees.add("Employee ID " + employee.getEmployeeId() + " is saved successfully.");
        }
    
        // Combine error messages and success messages
        String response = String.join(" | ", successfullySavedEmployees);
        if (!errors.isEmpty()) {
            response += " | Errors: " + String.join(" | ", errors);
        }
    
        return response;
    }
}
