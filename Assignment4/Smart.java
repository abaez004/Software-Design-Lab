import java.util.Date;

public class Smart extends Phone{
	private double cpuSpeed;
	
	//creates a Smart object with given parameters
	public Smart(double cpuSpeed, String id, String brand, double price, Date dateAvailable) {
		super(id, brand, price, dateAvailable);
		this.cpuSpeed = cpuSpeed;
	}
	
	//returns the cpuSpeed
	public double getCpuSpeed() {
		return this.cpuSpeed;
	}
	
	//changes the cpuSpeed to the one passed as an argument
	public void setCpuSpeed(double cpuSpeed) {
		this.cpuSpeed = cpuSpeed;
	}
	
	//returns "Smart"
	public String getName() {
		return "Smart";
	}
}