package THJava.Ngay2.Books.Models;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;
	@Column(name = "name", nullable = false, length = 255)
	private String name;
	@Column(name = "description", nullable = true, length = 255)
	private String description;
	@OneToMany(mappedBy = "category")
	private List<Book> books;
	@Column(name="isdeleted",columnDefinition = "boolean default false")
	private boolean isdeleted;

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
