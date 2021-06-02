package zhangdi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import zhangdi.entity.Department;

@Transactional
public interface DepartmentImpl extends JpaRepository<Department,Long> {
}
