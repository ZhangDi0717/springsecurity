package zhangdi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zhangdi.entity.Employee;

public interface EmployeeImpl extends JpaRepository<Employee,Long> {

    Employee findByUsername(String username);
}
