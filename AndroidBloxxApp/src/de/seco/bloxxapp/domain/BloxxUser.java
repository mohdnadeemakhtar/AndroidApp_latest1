package de.seco.bloxxapp.domain;

public class BloxxUser {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private byte[] image;
	
	public void setId(final Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setEmail(final String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}