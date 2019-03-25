package application;

public class Address {
	private String street;
	private int flat;
	private int house;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getFlat() {
		return Integer.toString(flat);
	}
	public void setFlat(int flat) {
		this.flat = flat;
	}
	public String getHouse() {
		return Integer.toString(house);
	}
	public void setHouse(int house) {
		this.house = house;
	}
}
