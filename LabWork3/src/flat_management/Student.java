package flat_management;

public class Student extends Human{
	public String study() {
		return "Study HARD!!!";
	}
	public String getInfo() {
		return super.getInfo() + this.study();
	}
}
