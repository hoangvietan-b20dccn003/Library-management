package btl.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Book {
	@Id
	private long bookId;
	
	@NotBlank(message = "Not empty")
	private String title;
	
	@NotBlank(message = "Not empty")
	private String author;
	
	@NotBlank(message = "Not empty")
	private String type;
	
	@NotBlank(message = "Not empty")
	private long sold;
	
	@NotBlank(message = "Not empty")
	private String date;
	
	@NotBlank(message = "Not empty")
	private long page;
	
	@NotBlank(message = "Not empty")
	private String description;
	
	private String skinpath;
	
	public String getSkinpath() {
		return skinpath;
	}
	public void setSkinpath(String skinpath) {
		this.skinpath = skinpath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getSold() {
		return sold;
	}
	public void setSold(long sold) {
		this.sold = sold;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getPage() {
		return page;
	}
	public void setPage(long page) {
		this.page = page;
	}
	public Book(long bookId, String title, String author, String type, String date, long sold, long page, String description, String skinpath) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.type = type;
		this.sold = sold;
		this.date = date;
		this.page = page;
		this.description = description;
		this.skinpath = skinpath;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
}
