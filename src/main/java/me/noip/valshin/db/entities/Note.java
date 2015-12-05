package me.noip.valshin.db.entities;

public class Note {
	private String name;
	private String secondName;
	private String lastName;
	private String homePhone;
	private String phone;
	private String address;
	private String email;
	
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
	public void setAdress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isEqual(Note note){
		return 	getName().equals(note.getName()) && 
				getSecondName().equals(note.getSecondName()) &&
				getLastName().equals(note.getLastName()) &&
				getPhone().equals(note.getPhone()) &&
				(getHomePhone() == note.getHomePhone() || getHomePhone().equals(note.getHomePhone())) &&
				(getAddress() == note.getAddress() || getAddress().equals(note.getAddress())) &&
				(getEmail() == note.getEmail() || getEmail().equals(note.getEmail()));
	}
	
	public Note clone(){
		Note newNote = new Note();
		newNote.setName(getName());
		newNote.setSecondName(getSecondName());
		newNote.setLastName(getLastName());
		newNote.setPhone(getPhone());
		newNote.setHomePhone(getHomePhone());
		newNote.setAdress(getAddress());
		newNote.setEmail(getEmail());
		return 	newNote;
	}
	
	@Override
	public String toString(){
		return "\nName: " + getName() + 
				"\nSecondName: " + getSecondName() + 
				"\nLastName: " + getLastName() +
				"\nPhone: " + getPhone() +
				"\nHomePhone: " + getHomePhone() +
				"\nAddress: " + getAddress() +
				"\nEmail: " + getEmail();
	}
}


