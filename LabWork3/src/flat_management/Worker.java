package flat_management;

public class Worker extends Human{
	public String work() {
		return "DO HARD!!!";
	}
	public String getInfo() {
		return super.getInfo() + this.work();
	}
}
