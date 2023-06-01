package THJava.Ngay2.Books.Repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import THJava.Ngay2.Books.Models.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	@Query("SELECT b FROM Book b WHERE b.isdeleted = false")
	List<Book> findWithOutDelete();
	@Modifying
	@Query("UPDATE Book b set b.isdeleted = true where b.id = :id")
	void deleteBookById(long id);
	
}
