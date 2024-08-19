package com.example.Mongo.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.Mongo.Models.Employee;
import com.example.Mongo.Repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private LocalDateTime calculateExperienceThreshold(int yearsOfExperience) {
        return LocalDateTime.now().minusYears(yearsOfExperience);
    }

    public List<Employee> filterEmployees(Integer yearsOfExperience, Integer managerId) {
        if (managerId != null && yearsOfExperience != null) {
            LocalDateTime threshold = calculateExperienceThreshold(yearsOfExperience);
            return employeeRepository.findByManagerIdAndExperienceGreaterThanEqual(managerId, threshold);
        } else if (managerId != null) {
            return employeeRepository.findByManagerId(managerId);
        } else if (yearsOfExperience != null) {
            LocalDateTime threshold = calculateExperienceThreshold(yearsOfExperience);
            return employeeRepository.findByExperienceGreaterThanEqual(threshold);
        } else {
            return employeeRepository.findAll();
        }
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}


// package com.example.Mongo.Service;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// import com.example.Mongo.Models.Employee;
// import com.example.Mongo.Repository.EmployeeRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class GetService {

//     @Autowired
//     private EmployeeRepository employeeRepository;

//     private LocalDateTime calculateExperienceThreshold(int yearsOfExperience) {
//         return LocalDateTime.now().minusYears(yearsOfExperience);
//     }

//     public List<Employee> filterEmployees(Integer yearsOfExperience, Integer managerId) {
//         if (managerId != null && yearsOfExperience != null) {
//             LocalDateTime threshold = calculateExperienceThreshold(yearsOfExperience);
//             return employeeRepository.findByManagerIdAndExperienceGreaterThanEqual(managerId, threshold);
//         } else if (managerId != null) {
//             return employeeRepository.findByManagerId(managerId);
//         } else if (yearsOfExperience != null) {
//             LocalDateTime threshold = calculateExperienceThreshold(yearsOfExperience);
//             return employeeRepository.findByExperienceGreaterThanEqual(threshold);
//         } else {
//             return employeeRepository.findAll();
//         }
//     }

//     // New method to handle multiple manager IDs with corresponding years of experience
//     public List<Employee> filterEmployees(List<Integer> managerIds, List<Integer> yearsOfExperience) {
//         List<Employee> filteredEmployees = new ArrayList<>();

//         for (int i = 0; i < managerIds.size(); i++) {
//             Integer managerId = managerIds.get(i);
//             Integer experience = yearsOfExperience.get(i);

//             List<Employee> employees = filterEmployees(experience, managerId);
//             filteredEmployees.addAll(employees);
//         }

//         return filteredEmployees;
//     }
// }


// // package com.example.Mongo.Service;

// // import java.time.LocalDateTime;
// // import java.util.ArrayList;
// // import java.util.List;

// // import com.example.Mongo.Models.Employee;
// // import com.example.Mongo.Repository.EmployeeRepository;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.stereotype.Service;

// // @Service
// // public class GetService {

// //     @Autowired
// //     private EmployeeRepository employeeRepository;
// //     private LocalDateTime calculateExperienceThreshold(int yearsOfExperience) {
// //         return LocalDateTime.now().minusYears(yearsOfExperience);
// //     }
// //     //private EmployeeRepository employeeRepository;
// //     public List<Employee> filterEmployees(Integer yearsOfExperience,Integer managerId ) {
// //         if (managerId != null && yearsOfExperience != null) {
// //             LocalDateTime threshold = calculateExperienceThreshold(yearsOfExperience);
// //             return employeeRepository.findByManagerIdAndExperienceGreaterThanEqual(managerId, threshold);
// //         } else if (managerId != null) {
// //             return employeeRepository.findByManagerId(managerId);
// //         } else if (yearsOfExperience != null) {
// //             LocalDateTime threshold = calculateExperienceThreshold(yearsOfExperience);
// //             return employeeRepository.findByExperienceGreaterThanEqual(threshold);
// //         } else {
// //             return employeeRepository.findAll();
// //         }
// //     }
// //     public List<FilterResult> filterEmployees(List<FilterRequest> requests) {
// //         List<FilterResult> results = new ArrayList<>();
        
// //         for (FilterRequest request : requests) {
// //             List<Employee> employees = filterEmployees(request.getManagerId(), request.getYearsOfExperience());
// //             FilterResult result = new FilterResult(request, employees);
// //             results.add(result);
// //         }

// //         return results;
// //     }

// //     public static class FilterRequest {
// //         private Integer managerId;
// //         private Integer yearsOfExperience;

// //         public Integer getManagerId() {
// //             return managerId;
// //         }

// //         public void setManagerId(Integer managerId) {
// //             this.managerId = managerId;
// //         }

// //         public Integer getYearsOfExperience() {
// //             return yearsOfExperience;
// //         }

// //         public void setYearsOfExperience(Integer yearsOfExperience) {
// //             this.yearsOfExperience = yearsOfExperience;
// //         }
// //     }

// //     public static class FilterResult {
// //         private FilterRequest request;
// //         private List<Employee> employees;

// //         public FilterResult(FilterRequest request, List<Employee> employees) {
// //             this.request = request;
// //             this.employees = employees;
// //         }

// //         public FilterRequest getRequest() {
// //             return request;
// //         }

// //         public List<Employee> getEmployees() {
// //             return employees;
// //         }
// //     }

// // }
