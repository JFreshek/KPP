package flat_management;

public class Address {
	private String street;
	private int house;
	private int flatNumber;
	private Flat flat;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getFlatNamber() {
		return Integer.toString(flatNumber);
	}
	public void setFlatNumber(int flatNumber) {
		this.flatNumber = flatNumber;
	}
	public String getHouse() {
		return Integer.toString(house);
	}
	public void setHouse(int house) {
		this.house = house;
	}
	public Flat getFlat() {
		return flat;
	}
	public void setFlat(Flat flat) {
		this.flat = flat;
	}
}
