package com.example.Mongo.Service;

import java.util.Optional;

import com.example.Mongo.Models.Designation;
import com.example.Mongo.Models.Employee;
import com.example.Mongo.Repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PutService {
    @Autowired
    EmployeeRepository employeeRepository;
    public Employee updateManager(Integer employeeId,Integer newManagerId){
       Optional<Employee> toChkEmpId=employeeRepository.findById(employeeId);
       if(toChkEmpId.isPresent()){
           
           Employee empObj=toChkEmpId.get();
           System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx"+empObj.getEmail());
           if(newManagerId==0){
               empObj.setDesignation(Designation.Account_manager);
           }else{
            empObj.setDesignation(Designation.associate);
           }
           empObj.setManagerId(newManagerId);
           System.out.println("  ");
           System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx"+empObj.getEmail());
           employeeRepository.save(empObj);
           return empObj;
       }return null;
    }
    
}
