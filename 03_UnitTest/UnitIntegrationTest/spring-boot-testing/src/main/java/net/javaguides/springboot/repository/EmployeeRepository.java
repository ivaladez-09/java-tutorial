package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    // Custom query using JPQL with index params
    @Query("SELECT e FROM Employee e WHERE e.firstName = ?1 AND e.lastName = ?2")
    Employee findByJPQL(String firstName, String lastName);

    // Custom query using JPQL with named params
    @Query("SELECT e FROM Employee e WHERE e.firstName =:firstName AND e.lastName =:lastName")
    Employee findByJPQLNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

    // Custom query using native SQL with index params
    @Query(value = "SELECT * FROM employee e WHERE e.first_name = ?1 AND e.last_name = ?2",
            nativeQuery = true)
    Employee findByNativeSQL(String firstName, String lastName);

    // Custom query using native SQL with named params
    @Query(value = "SELECT * FROM employee e WHERE e.first_name =:firstName AND e.last_name =:lastName",
            nativeQuery = true)
    Employee findByNativeSQLNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
