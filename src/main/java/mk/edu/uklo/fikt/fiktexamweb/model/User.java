package mk.edu.uklo.fikt.fiktexamweb.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String brIndex;
	
	private String imePrezime;
	private String email;
	
	private String username;
	private String password;
	private String role;
	
	public User() {
		
	}
	
	public User(User user) {
		user.id = user.getId();
		user.brIndex = user.getBrIndex();
		user.imePrezime = user.getImePrezime();
		user.email = user.getEmail();
		user.username = user.getUsername();
		user.password = user.getPassword();
		user.role = user.getRole();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBrIndex() {
		return brIndex;
	}
	public void setBrIndex(String brIndex) {
		this.brIndex = brIndex;
	}
	public String getImePrezime() {
		return imePrezime;
	}
	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
}
