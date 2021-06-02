package zhangdi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zhangdi.entity.Permission;

public interface PermissionImpl extends JpaRepository<Permission,Long> {
}
