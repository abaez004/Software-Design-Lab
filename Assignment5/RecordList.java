package application;
import java.util.ArrayList;


public class RecordList{
	
	private ArrayList<Record> recordList;
	
	public RecordList() {
		recordList = new ArrayList<Record>();
	}
	
	public ArrayList<Record> getRecordList(){
		return this.recordList;
	}
	
	public void setRecordList(ArrayList<Record> recordList){
		this.recordList = recordList;
	}
	
	public void appendRecord(int index, Record r) {
		recordList.set(index, r);
	}
	
	public void addRecord(Record r) {
		recordList.add(r);
	}
	
	public void removeRecord(int index) {
		recordList.remove(index);
	}
}