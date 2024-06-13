package btl.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class Sold {
    @Id
    private long bookId;
    @Id
    private long personId;
    private long num;
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
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public Sold(long bookId, long personId, long num) {
		super();
		this.bookId = bookId;
		this.personId = personId;
		this.num = num;
	}
	public Sold() {
		super();
		// TODO Auto-generated constructor stub
	}
}