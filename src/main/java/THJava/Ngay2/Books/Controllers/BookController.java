package THJava.Ngay2.Books.Controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import THJava.Ngay2.Books.Models.Book;
import THJava.Ngay2.Books.Models.User;
import THJava.Ngay2.Books.Services.BookServices;
import THJava.Ngay2.Books.Services.CategoryService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookServices bookServices;
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public String viewAllBook(Model model) {
		List<Book> listBook = bookServices.listAllWithOutDelete();
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

	@GetMapping("/export")
	public void exportToCSV(HttpServletResponse response)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		response.setContentType("text/csv");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=books_" + currentDateTime + ".csv";
		response.setHeader(headerKey, headerValue);

		List<Book> books = bookServices.listAll();

		StatefulBeanToCsvBuilder<Book> builder = new StatefulBeanToCsvBuilder<Book>(response.getWriter())
				.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR)
				.withOrderedResults(false);

		Arrays.stream(Book.class.getDeclaredFields())
				.filter(field -> !("id".equals(field.getName()) 
						|| "title".equals(field.getName())|| "author".equals(field.getName())
						|| "price".equals(field.getName())))
				.forEach(field -> builder.withIgnoreField(Book.class, field));

		StatefulBeanToCsv<Book> writer = builder.build();

		// write all users to csv file
		writer.write(books);
	}
}
