package THJava.Ngay2.Books.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import THJava.Ngay2.Books.Models.Book;
import THJava.Ngay2.Books.Models.Category;
import THJava.Ngay2.Books.Repositories.BookRepository;
import THJava.Ngay2.Books.Repositories.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> listAll() {
		return categoryRepository.findAll();
	}

	public void save(Category category) {
		categoryRepository.save(category);
	}

	public Category get(long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	public void delete(long id) {
		categoryRepository.deleteById(id);
	}
}
