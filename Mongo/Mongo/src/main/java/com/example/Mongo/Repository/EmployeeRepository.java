// package com.example.Mongo.Repository;

// public interface EmployeeRepository {

    
// }
package com.example.Mongo.Repository;

import com.example.Mongo.Models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {

    // Find employees by managerId
    List<Employee> findByManagerId(Integer managerId);

    // Custom query to find employees by managerId and years of experience
    @Query("{'managerId': ?0, 'dateOfJoining': {$lte: ?1}}")
    List<Employee> findByManagerIdAndExperienceGreaterThanEqual(Integer managerId, LocalDateTime dateOfJoiningThreshold);

    // Custom query to find employees by years of experience (without managerId)
    @Query("{'dateOfJoining': {$lte: ?0}}")
    List<Employee> findByExperienceGreaterThanEqual(LocalDateTime dateOfJoiningThreshold);
}
