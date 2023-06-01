package THJava.Ngay2.Books.Models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 255)
	private String username;
	@Column(nullable = false, length = 255)
	private String email;
	@Column(nullable = false, length = 255)
	private String password;
	@Column(nullable = true, length = 255)
	private String photourl;
	@Column(nullable = true, length = 255)
	private String tokenforgotpassword;
	@Column(nullable = true)
	private LocalDateTime timeexpired;
	private boolean enabled;
	@Column(name = "isdeleted", columnDefinition = "boolean default false")
	private boolean isdeleted;

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getphotourl() {
		return photourl;
	}

	public void setphotourl(String photourl) {
		this.photourl = photourl;
	}

	public String gettokenforgotpassword() {
		return tokenforgotpassword;
	}

	public void settokenforgotpassword(String tokenforgotpassword) {
		this.tokenforgotpassword = tokenforgotpassword;
	}

	public LocalDateTime getTimeexpired() {
		return timeexpired;
	}

	public void setTimeexpired(LocalDateTime timeexpired) {
		this.timeexpired = timeexpired;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean getEnabled() {
		return enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRoles(Role role) {
		this.roles.add(role);
	}

	public User() {
		roles = new HashSet<>();
	}

	@Transient
	public String getPhotosImagePath() {
		if (photourl == null || id == null)
			return null;

		return "/photos/" + id + "/" + photourl;
	}

}
