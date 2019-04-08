package flat_management;

public abstract class Human {
	private String firstName;
	private String lastName;
	private Address address;
	private Flat flat;
	
	public void moveIn() {
		setAddress(new Address());
	}
	public void moveOut() {
		setAddress(null);
		setFlat(null);
	}
	public String getLastName() {
		return lastName;
	}
	public String getInfo() {
		return this.firstName + " " + this.lastName + " ";
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Flat getFlat() {
		return flat;
	}
	public void setFlat(Flat flat) {
		this.flat = flat;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
