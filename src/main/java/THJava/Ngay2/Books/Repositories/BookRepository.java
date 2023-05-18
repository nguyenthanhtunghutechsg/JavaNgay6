package THJava.Ngay2.Books.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import THJava.Ngay2.Books.Models.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
}
