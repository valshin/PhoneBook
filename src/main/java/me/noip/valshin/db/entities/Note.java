package me.noip.valshin.db.entities;

public class Note {
	private String name;
	private String secondName;
	private String lastName;
	private String homePhone;
	private String phone;
	private String address;
	private String email;
	private long owner;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getOwner() {
		return owner;
	}
	public void setOwner(long owner) {
		this.owner = owner;
	}
	
	public boolean equals(Note note){
		return 	toString().equals(note.toString());
	}
	
	public Note clone(){
		Note newNote = new Note();
		newNote.setName(getName());
		newNote.setSecondName(getSecondName());
		newNote.setLastName(getLastName());
		newNote.setPhone(getPhone());
		newNote.setHomePhone(getHomePhone());
		newNote.setAddress(getAddress());
		newNote.setEmail(getEmail());
		return 	newNote;
	}
	
	public String toString(){
		return "\nName: " + getName() + 
				"\nSecondName: " + getSecondName() + 
				"\nLastName: " + getLastName() +
				"\nPhone: " + getPhone() +
				(getHomePhone() != null ? "\nHomePhone: " + getHomePhone() : "") +
				(getAddress() != null ? "\nAddress: " + getAddress() : "") +
				(getEmail() != null ? "\ne-mail: " + getEmail() : "");
	}
}


