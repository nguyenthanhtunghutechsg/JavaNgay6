package THJava.Ngay2.Books.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import THJava.Ngay2.Books.Models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
