package THJava.Ngay2.Books.Models;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "title", nullable = false, length = 255)
	private String title;
	@Column(name = "author", nullable = false, length = 255)
	private String author;
	@Column(name = "price")
	private Long price;
	@Column(name="isdeleted",columnDefinition = "boolean default false")
	private boolean isdeleted;
	
	
	public boolean isIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

}
