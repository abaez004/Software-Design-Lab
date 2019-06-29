import java.util.Date;

public abstract class Landline extends Phone{
	//creates a landline for classes that implement Landline
	public Landline(String id, String brand, double price, Date dateAvailable) {
		super(id, brand, price, dateAvailable);
	}
}