package am.itspace.companyemployeespring.repository;


import am.itspace.companyemployeespring.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employees, Integer> {

}
