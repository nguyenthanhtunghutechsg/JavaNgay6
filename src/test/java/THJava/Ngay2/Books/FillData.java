package THJava.Ngay2.Books;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import THJava.Ngay2.Books.Models.Book;
import THJava.Ngay2.Books.Models.Category;
import THJava.Ngay2.Books.Models.Role;
import THJava.Ngay2.Books.Models.User;
import THJava.Ngay2.Books.Repositories.BookRepository;
import THJava.Ngay2.Books.Repositories.CategoryRepository;
import THJava.Ngay2.Books.Repositories.RoleRepository;
import THJava.Ngay2.Books.Repositories.UserRepository;



@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@ComponentScan("THJava.Ngay2.Books.Security")
public class FillData {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Test
	public void testCreateUser() {
		Category categoryCNTT = new Category(); 
		categoryCNTT.setName("Cong Nghe Thong Tin");
		categoryRepository.save(categoryCNTT);
		Category categoryEng = new Category(); 
		categoryEng.setName("Tieng Anh");
		categoryRepository.save(categoryEng);

		Book bookCNTT1 = new Book();
		bookCNTT1.setTitle("Lap trinh web spring MVC");
		bookCNTT1.setAuthor("Anh Nguyen");
		bookCNTT1.setPrice(50000l);
		bookCNTT1.setCategory(categoryCNTT);
		bookRepository.save(bookCNTT1);
		
		Book bookCNTT2 = new Book();
		bookCNTT2.setTitle("Lap trinh Ung dung Spring");
		bookCNTT2.setAuthor("Anh Nguyen");
		bookCNTT2.setPrice(150000l);
		bookCNTT2.setCategory(categoryCNTT);
		bookRepository.save(bookCNTT2);
		
		Book bookCNTT3 = new Book();
		bookCNTT3.setTitle("Lap trinh ung dung java");
		bookCNTT3.setAuthor("Anh Nguyen");
		bookCNTT3.setPrice(250000l);
		bookCNTT3.setCategory(categoryCNTT);
		bookRepository.save(bookCNTT3);
		
		Book bookEng1 = new Book();
		bookEng1.setTitle("IELTS");
		bookEng1.setAuthor("Cambridge");
		bookEng1.setPrice(350000l);
		bookEng1.setCategory(categoryEng);
		bookRepository.save(bookEng1);
		
		Role roleUser = new Role();
		roleUser.setName("USER");
		roleRepository.save(roleUser);
		
		Role roleCreater = new Role();
		roleCreater.setName("CREATER");
		roleRepository.save(roleCreater);
		
		Role roleEditor = new Role();
		roleEditor.setName("EDITOR");
		roleRepository.save(roleEditor);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ADMIN");
		roleRepository.save(roleAdmin);
		
		User user1 = new User();
		user1.setUsername("user1");
		user1.setEmail("user1@gmail.com");
		user1.setEnabled(true);
		user1.setPassword(passwordEncoder.encode("123456"));
		user1.addRoles(roleUser);
		userRepository.save(user1);
		
		
		User user2 = new User();
		user2.setUsername("user2");
		user2.setEmail("user2@gmail.com");
		user2.setEnabled(true);
		user2.setPassword(passwordEncoder.encode("123456"));
		user2.addRoles(roleUser);
		userRepository.save(user2);
		
		User creater1 = new User();
		creater1.setUsername("creater1");
		creater1.setEmail("creater1@gmail.com");
		creater1.setEnabled(true);
		creater1.setPassword(passwordEncoder.encode("123456"));
		creater1.addRoles(roleCreater);
		userRepository.save(creater1);
		
		User creater2 = new User();
		creater2.setUsername("creater2");
		creater2.setEmail("creater2@gmail.com");
		creater2.setEnabled(true);
		creater2.setPassword(passwordEncoder.encode("123456"));
		creater2.addRoles(roleCreater);
		userRepository.save(creater2);
		
		User editor1 = new User();
		editor1.setUsername("editor1");
		editor1.setEmail("editor1@gmail.com");
		editor1.setEnabled(true);
		editor1.setPassword(passwordEncoder.encode("123456"));
		editor1.addRoles(roleEditor);
		userRepository.save(editor1);
		
		User editor2 = new User();
		editor2.setUsername("editor2");
		editor2.setEmail("editor2@gmail.com");
		editor2.setEnabled(true);
		editor2.setPassword(passwordEncoder.encode("123456"));
		editor2.addRoles(roleEditor);
		userRepository.save(editor2);
		
		User admin1 = new User();
		admin1.setUsername("admin1");
		admin1.setEmail("admin1@gmail.com");
		admin1.setEnabled(true);
		admin1.setPassword(passwordEncoder.encode("123456"));
		admin1.addRoles(roleAdmin);
		userRepository.save(admin1);
		
		User admin2 = new User();
		admin2.setUsername("admin2");
		admin2.setEmail("admin2@gmail.com");
		admin2.setEnabled(true);
		admin2.setPassword(passwordEncoder.encode("123456"));
		admin2.addRoles(roleAdmin);
		userRepository.save(admin2);
		
		assertThat("1").isEqualTo("1");
	}
	
}
