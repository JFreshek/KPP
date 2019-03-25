package application;

public class Human {
	private String firstName;
	private String lastName;
	Address address;
	public void moveIn() {
		address = new Address();
	}
	public void moveOut() {
		address = null;
		System.gc();
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
	
}
