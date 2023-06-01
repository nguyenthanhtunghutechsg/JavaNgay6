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

import THJava.Ngay2.Books.Models.Category;
import THJava.Ngay2.Books.Services.UserService;
import THJava.Ngay2.Books.Services.CategoryService;
import THJava.Ngay2.Books.Services.RoleService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public String viewAllCategories(Model model) {
		List<Category> listCategories = categoryService.listAll();
		model.addAttribute("categories", listCategories);
		return "category/index";
	}

	@GetMapping("/new")
	public String showNewCategoryPage(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "category/new_category";
	}

	@PostMapping("/save")
	public String saveCategory(@ModelAttribute("category") Category category) {
		categoryService.save(category);
		return "redirect:/category";
	}

	@GetMapping("/edit/{id}")
	public String showEditCategoryPage(@PathVariable("id") Long id, Model model) {
		Category category = categoryService.get(id);

		if (category == null) {
			return "notfound";
			
		} else {
			model.addAttribute("category", category);
			return "category/edit";
		}
	}

	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable("id") Long id) {
		Category category = categoryService.get(id);
		if (category == null) {
			return "notfound";
		} else {
			categoryService.delete(id);
			return "redirect:/users";
		}
	}
	
	
}
