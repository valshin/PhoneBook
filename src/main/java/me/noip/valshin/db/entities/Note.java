package me.noip.valshin.db.entities;

public class Note {
	private String name;
	private String secondName;
	private String lastName;
	private String homePhone;
	private String phone;
	private String adress;
	private String email;
	private String owner;
	
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
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public boolean isEqual(Note note){
		return 	getName().equals(note.getName()) && 
				getSecondName().equals(note.getSecondName()) &&
				getLastName().equals(note.getLastName()) &&
				getPhone().equals(note.getPhone()) &&
				(getHomePhone() == note.getHomePhone() || getHomePhone().equals(note.getHomePhone())) &&
				(getAdress() == note.getAdress() || getAdress().equals(note.getAdress())) &&
				(getEmail() == note.getEmail() || getEmail().equals(note.getEmail())) &&
				getOwner().equals(note.getOwner());
	}
	
	public Note clone(Note note){
		Note newNote = new Note();
		newNote.setName(note.getName());
		newNote.setSecondName(note.getSecondName());
		newNote.setLastName(note.getLastName());
		newNote.setPhone(note.getPhone());
		newNote.setHomePhone(note.getHomePhone());
		newNote.setAdress(note.getAdress());
		newNote.setEmail(note.getEmail());
		return 	newNote;
	}
}


