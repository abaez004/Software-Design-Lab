import java.text.SimpleDateFormat;
import java.util.TreeSet;

public class PhoneList{
	private TreeSet<Phone> setPhone;
	
	//creates a PhoneList object which contains a TreeSet of type Phone
	public PhoneList() {
		this.setPhone = new TreeSet<Phone>();
	}
	
	//returns true if the add was successful and no duplicate ids were found
	//returns false otherwise
	public boolean add(Phone p) {
		if(this.setPhone.contains(p))
			return false;
		this.setPhone.add(p);
		return true;
	}
	
	//returns a TreeSet of type Landline of all Landlines in setPhone
	public TreeSet<Landline> getListLandline(){
		TreeSet<Landline> setLandline = new TreeSet<Landline>();
		for(Phone p: setPhone) {
			if(p instanceof Landline)
				setLandline.add((Landline)p);
		}
		return setLandline;
	}
	
	//prints the contents of setPhone in a formatted table
	public void printFormatted() {
		SimpleDateFormat f = new SimpleDateFormat("MMM dd, yyyy");
		String header = "+----------+-------+----------------------+-----------+------------------+--------+";
		String divider = "|";
		System.out.printf("%s%n", header);
		System.out.printf("%s%5s%6s%3s%5s%12s%11s%3s%7s%2s%5s%2s%4s%n", divider, "Type", divider, "ID", divider,
						"Brand", divider, "Price", divider, "Date Available", divider, "Notes", divider);
		for(Phone p:setPhone) {
			System.out.printf("%s%n", header);
			String name = p.getName();
			String id = p.getId();
			String brand = p.getBrand();
			String price = Double.toString(p.getPrice());
			String date = f.format(p.getDateAvailable());
			String notes = "";
			
			if(p instanceof Wireless)
				notes = Double.toString(((Wireless)p).getRange());
			else if(p instanceof Smart)
				notes = Double.toString(((Smart)p).getCpuSpeed());
			else if(p instanceof IPPhone)
				notes = ((IPPhone)p).getProtocol();
			
			int spaceNa = 11 - name.length();
			int spaceId = 8 - id.length();
			int spaceB = 23 - brand.length();
			int spaceD = 19 - date.length();
			int spaceP = 12 - price.length();
			int spaceNo = 9 - notes.length();
			
			String format = "%s%s%" + spaceNa + "s%s%"+spaceId+
							"s%s%"+spaceB+"s%s%"+spaceP+"s%s%"+spaceD+
							"s%s%"+spaceNo+"s%n";
			System.out.printf(format, divider, name, divider, id, divider,
					brand, divider, price, divider, date, divider, notes, divider);
		}
		System.out.printf("%s%n", header);

	}
}