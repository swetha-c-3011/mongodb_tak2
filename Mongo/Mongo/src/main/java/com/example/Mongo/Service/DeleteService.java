package com.example.Mongo.Service;

//import com.example.Mongo.Models.Employee;
import com.example.Mongo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeleteService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<String> deleteEmployees(List<Integer> employeeIds) {
        List<String> results = new ArrayList<>();
        
        for (Integer employeeId : employeeIds) {
            if (employeeRepository.existsById(employeeId)) {
                employeeRepository.deleteById(employeeId);
                results.add("Successfully deleted employee with ID " + employeeId);
            } else {
                results.add("Employee with ID " + employeeId + " not found");
            }
        }
        
        return results;
    }
}

// package com.example.Mongo.Service;

// import com.example.Mongo.Repository.EmployeeRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class DeleteService {

//     @Autowired
//     private EmployeeRepository employeeRepository;

//     public void deleteEmployeesByIds(List<String> employeeIds) {
//         for (Integer employeeId : employeeIds) {
//             if (employeeRepository.existsById(employeeId)) {
//                 employeeRepository.deleteById(employeeId);
//             } else {
//                 throw new IllegalArgumentException("Employee with ID " + employeeId + " does not exist.");
//             }
//         }
//     }
// }

// package com.example.Mongo.Service;

// //package com.example.Mongo.Service;

// import java.util.ArrayList;
// import java.util.List;

// import com.example.Mongo.Models.Employee;
// import com.example.Mongo.Repository.EmployeeRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class DeleteService {

//     @Autowired
//     private EmployeeRepository employeeRepository;

//     public boolean deleteEmployee(Integer employeeId) {
//         List<Employee> subordinates = employeeRepository.findByManagerId(employeeId);
//         if (subordinates.isEmpty()) {
//             employeeRepository.deleteById(employeeId);
//             return true;
//         }
//         return false;
//     }

//     public List<DeleteResult> deleteEmployees(List<Integer> employeeIds) {
//         List<DeleteResult> results = new ArrayList<>();

//         for (Integer employeeId : employeeIds) {
//             boolean isDeleted = deleteEmployee(employeeId);
//             if (isDeleted) {
//                 results.add(new DeleteResult(employeeId, true, "Successfully deleted employee with ID " + employeeId));
//             } else {
//                 results.add(new DeleteResult(employeeId, false, "Cannot delete employee with ID " + employeeId + " as they have subordinates"));
//             }
//         }

//         return results;
//     }

//     public static class DeleteResult {
//         private Integer employeeId;
//         private boolean success;
//         private String message;

//         public DeleteResult(Integer employeeId, boolean success, String message) {
//             this.employeeId = employeeId;
//             this.success = success;
//             this.message = message;
//         }

//         public Integer getEmployeeId() {
//             return employeeId;
//         }

//         public boolean isSuccess() {
//             return success;
//         }

//         public String getMessage() {
//             return message;
//         }
//     }
// }
