package THJava.Ngay2.Books.Repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import THJava.Ngay2.Books.Models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	@Query("SELECT r FROM Role r WHERE r.name = :name")
	public Role getRoleByName(@Param("name") String name);
}
