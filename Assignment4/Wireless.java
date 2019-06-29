import java.util.Date;

public class Wireless extends Landline{
	private double range;
	
	//creates a Wireless object with given parameters
	public Wireless(double range, String id, String brand, double price, Date dateAvailable) {
		super(id, brand, price, dateAvailable);
		this.range = range;
	}
	
	//returns the range
	public double getRange() {
		return this.range;
	}
	
	//returns the "Wireless"
	public String getName() {
		return "Wireless";
	}
}