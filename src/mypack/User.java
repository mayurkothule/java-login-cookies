package mypack;

public class User {
	String fname,lname,email,contact;

	public User() {
		super();
		fname=null;
		lname=null;
		email=null;
		contact=null;
	}

	public User(String fname, String lname, String email, String contact) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.contact = contact;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	

}
