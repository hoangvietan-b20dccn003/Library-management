package btl.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class Rate{
    @Id
    private long rateId;
    
    private long bookId;
    private long personId;
    private long rating;
    private String cmt;
    private String email;
    private String username;
    
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getRateId() {
		return rateId;
	}
	public void setRateId(long rateId) {
		this.rateId = rateId;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	public long getRating() {
		return rating;
	}
	public void setRating(long rating) {
		this.rating = rating;
	}
	public String getCmt() {
		return cmt;
	}
	public void setCmt(String cmt) {
		this.cmt = cmt;
	}
	public Rate(long rateId, long bookId, long personId, long rating, String cmt, String username) {
		super();
		this.rateId = rateId;
		this.bookId = bookId;
		this.personId = personId;
		this.rating = rating;
		this.cmt = cmt;
		this.username = username;
	}
	public Rate() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	
}