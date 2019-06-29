import java.util.Date;

public abstract class Phone implements Comparable<Phone>{
	private String id;
	private String brand;
	private double price;
	private Date dateAvailable;
	
	//Creates a phone object with given id, brand, price and dateAvailable for classes that implement Phone
	public Phone(String id, String brand, double price, Date dateAvailable) {
		this.id = id;
		this.brand = brand;
		this.price = price;
		this.dateAvailable = dateAvailable;
	}
	
	//returns the price 
	public double getPrice() {
		return this.price;
	}
	
	//returns the name, to be written by classes that implement Phone
	public abstract String getName();
	
	//compares two phone objects by using the compareTo of their ids
	public int compareTo(Phone p) {
		return this.id.compareTo(p.id);
	}
	
	//returns the id
	public String getId() {
		return this.id;
	}
	
	//returns the brand
	public String getBrand() {
		return this.brand;
	}
	
	//returns the date available
	public Date getDateAvailable() {
		return this.dateAvailable;
	}
}
