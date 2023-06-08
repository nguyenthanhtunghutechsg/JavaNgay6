package THJava.Ngay2.Books.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import THJava.Ngay2.Books.Models.Book;
import THJava.Ngay2.Books.Repositories.BookRepository;

@Service
@Transactional
public class BookServices {
	int pageSize = 10;
	@Autowired
	private BookRepository bookRepository;

	public Page<Book> listAll(int pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		return bookRepository.findAll(pageable);
	}

	public Page<Book> listAllWithOutDelete(int pageNum, String sortField, String sortType, String keyword) {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
				sortType.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
		System.out.println(keyword);
		if (keyword != null) {
			return bookRepository.Search(pageable, keyword);
		}
		return bookRepository.findWithOutDelete(pageable);

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
