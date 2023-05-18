package THJava.Ngay2.Books.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import THJava.Ngay2.Books.Models.Book;
import THJava.Ngay2.Books.Services.BookServices;
import THJava.Ngay2.Books.Services.CategoryService;

@Controller
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookServices bookServices;
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public String viewAllBook(Model model) {
		List<Book> listBook = bookServices.listAll();
		model.addAttribute("books", listBook);
		return "book/index";
	}

	@GetMapping("/new")
	public String showNewBookPage(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		model.addAttribute("categories", categoryService.listAll());
		return "book/new_book";
	}

	@PostMapping("/save")
	public String saveBook(@ModelAttribute("book") Book book) {
		bookServices.save(book);
		return "redirect:/books";
	}

	@GetMapping("/edit/{id}")
	public String showEditBookPage(@PathVariable("id") Long id, Model model) {
		Book book = bookServices.get(id);

		if (book == null) {
			return "notfound";
			
		} else {
			model.addAttribute("categories", categoryService.listAll());
			model.addAttribute("book", book);
			return "book/edit";
		}
	}

	@GetMapping("/delete/{id}")
	public String deletebook(@PathVariable("id") Long id) {
		Book book = bookServices.get(id);
		if (book == null) {
			return "notfound";
		} else {
			bookServices.delete(id);
			return "redirect:/books";
		}
	}

}
