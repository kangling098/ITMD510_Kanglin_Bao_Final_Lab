package food;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Food {
	private SimpleStringProperty name;
	private SimpleDoubleProperty price;
	private SimpleIntegerProperty pid;
	public Food(int pid, String name, Double price) {
		this.pid = new SimpleIntegerProperty(pid);
		this.name = new SimpleStringProperty(name);
		this.price = new SimpleDoubleProperty(price);
	}
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public Double getPrice() {
		return price.get();
	}
	public void setPrice(Double price) {
		this.price.set(price);
	}
	public int getPid() {
		return pid.get();
	}
	public void setPid(int pid) {
		this.pid.set(pid);
	}
}
