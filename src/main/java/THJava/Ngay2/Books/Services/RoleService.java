package THJava.Ngay2.Books.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import THJava.Ngay2.Books.Models.Role;
import THJava.Ngay2.Books.Repositories.RoleRepository;
@Service
@Transactional
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;

	public List<Role> listAll() {
		return roleRepository.findAll();
	}

	public void save(Role user) {
		roleRepository.save(user);
	}

	public Role get(long id) {
		return roleRepository.findById(id).orElse(null);
	}

	public void delete(long id) {
		roleRepository.deleteById(id);
	}
}
