import java.util.Date;

public class IPPhone extends Landline{
	private String protocol;
	
	//creates a IPPhone object with given parameters
	public IPPhone(String protocol, String id, String brand, double price, Date dateAvailable) {
		super(id, brand, price, dateAvailable);
		this.protocol = protocol;
	}
	
	//returns the protocol
	public String getProtocol() {
		return this.protocol;
	}
	
	//returns "IPPhone"
	public String getName() {
		return "IPPhone";
	}
}