package btl.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Person {
	@Id
	private long personId;
	
	private String username;
	private String password;
	private String role;
	private long active;
	private String email;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getActive() {
		return active;
	}
	public void setActive(long active) {
		this.active = active;
	}
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Person(long personId, String username, String password, String role, long active, String email) {
		super();
		this.personId = personId;
		this.username = username;
		this.password = password;
		this.role = role;
		this.active= active;
		this.email = email;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
}


