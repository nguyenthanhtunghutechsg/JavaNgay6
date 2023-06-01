package THJava.Ngay2.Books.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import THJava.Ngay2.Books.Models.Book;
import THJava.Ngay2.Books.Repositories.BookRepository;

@Service
@Transactional
public class BookServices {
	@Autowired
	private BookRepository bookRepository;

	public List<Book> listAll() {
		return bookRepository.findAll(Sort.by("title").ascending());
	}
	public List<Book> listAllWithOutDelete() {
		return bookRepository.findWithOutDelete();
	}

	public void save(Book product) {
		bookRepository.save(product);
	}

	public Book get(long id) {
		return bookRepository.findById(id).orElse(null);
	}

	public void delete(long id) {
		bookRepository.deleteBookById(id);
	}
}
