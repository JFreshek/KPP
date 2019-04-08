package flat_management;

import java.util.ArrayList;
import java.util.ListIterator;

public class Flat {
	private Address address = new Address();
	private Room room;
	private ArrayList<Human> tenants = new ArrayList<Human>();
	
	public ArrayList<Human> getTenants() {
		return tenants;
	}
	public void acceptTenant(Human human) {
		tenants.add(human);
	}
	public void evictTenant(ListIterator<Human> tenantIter) {
		tenantIter.remove();
	}
	public void allocateRoom() {
		setRoom(new Room());
	}
	public void vacateRoom() {
		setRoom(null);
		tenants.clear();
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(String street, int house, int flatNumber) {
		this.address.setFlatNumber(flatNumber);
		this.address.setHouse(house);
		this.address.setStreet(street);
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
}
