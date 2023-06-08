package THJava.Ngay2.Books.Repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import THJava.Ngay2.Books.Models.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	@Query("SELECT b FROM Book b WHERE b.isdeleted = false")
	Page<Book> findWithOutDelete(Pageable page);
	@Modifying
	@Query("UPDATE Book b set b.isdeleted = true where b.id = :id")
	void deleteBookById(long id);
	@Query("SELECT b FROM Book b WHERE CONCAT(b.title, ' ', b.author, ' ', b.category, ' ', b.price) LIKE %:keyword% AND b.isdeleted = false")
    public Page<Book> Search(Pageable page, @Param("keyword")String keyword);
	
}
