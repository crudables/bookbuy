package com.ables.bookbuy.models;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
public class User extends AbstractDomainClass{
	@Column(name = "username", nullable = false, unique = true)
	 private String username;
	private String profilePic;
	private String fullName;
	private String otherName;
	 @Column(name = "firstname", nullable = false, unique = false)
	 	private String firstName;
	 @Column(name = "lastname", nullable = false, unique = false)
	 	private String lastName;
	 	@Enumerated(EnumType.STRING)
	 	private  Role role;
	 	@Column(name = "email", nullable = false, unique = true)
	 	private String email;
	 	@Column(name = "password", nullable = false, unique = false)
	    private String password;
	    private Boolean enabled = true;
	    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
		private List<Post> post;
	    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
	    private List<Comment> comments;
	    
	    @ManyToMany
		@JoinTable(name="friends", joinColumns=@JoinColumn(name="userId"), inverseJoinColumns=@JoinColumn(name="friendId"))
		private Set<User> friends;
		
		@ManyToMany
		@JoinTable(name="friends", joinColumns=@JoinColumn(name="friendId"), inverseJoinColumns=@JoinColumn(name="userId"))
		private Set<User> friendOf;
	    
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
		public Boolean getEnabled() {
			return enabled;
		}
		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}
		public List<Post> getPost() {
			return post;
		}
		public void setPost(List<Post> post) {
			this.post = post;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public List<Comment> getComments() {
			return comments;
		}
		public void setComments(List<Comment> comments) {
			this.comments = comments;
		}
		public Set<User> getFriends() {
			return friends;
		}
		public void setFriends(Set<User> friends) {
			this.friends = friends;
		}
		public Set<User> getFriendOf() {
			return friendOf;
		}
		public void setFriendOf(Set<User> friendOf) {
			this.friendOf = friendOf;
		}
		
		
		
		public String getFullName() {
			return fullName;
		}
		public void setFullName() {
			this.fullName = getLastName()+" "+getFirstName()+" "+getOtherName();
		}
		public String getOtherName() {
			return otherName;
		}
		public void setOtherName(String otherName) {
			this.otherName = otherName;
		}
		public String getProfilePic() {
			return profilePic;
		}
		public void setProfilePic(String profilePic) {
			this.profilePic = profilePic;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((username == null) ? 0 : username.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			if(id == null){
				if(other.id != null)
					return false;
			}
			
			else if(!id.equals(other.id))
				return false;
			
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return getFullName()+" ("+getUsername()+")";
		}
		
		
		
}
