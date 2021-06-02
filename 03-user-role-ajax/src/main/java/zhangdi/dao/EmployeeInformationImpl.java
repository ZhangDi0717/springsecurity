package zhangdi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zhangdi.entity.EmployeeInformation;

public interface EmployeeInformationImpl extends JpaRepository<EmployeeInformation,Long> {
}
