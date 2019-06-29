package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.xml.bind.JAXB;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PhonebookController {
	private RecordList records;
	
	private int currentRecord;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField stateTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    private Label entryLbl;

    @FXML
    private Button minusBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button lastBtn;

    @FXML
    private Button nextBtn;

    @FXML
    private Button loadBtn;

    @FXML
    private Button serializeBtn;

    @FXML
    private Label fileLbl;

    @FXML
    private Button exitBtn;
    
    //Handles the event when the + button is clicked, checks to see if records is empty
    //then adds a new record accordingly. Verifies the current entry first.
    @FXML
    void addClicked(MouseEvent event) {
    	if(records.getRecordList().isEmpty()) {
    		addRecord();
        	checkButtons();
        	display();
        	return;
    	}
    	if(!verifyEntry())
    		return;
    	appendRecord();
    	currentRecord = records.getRecordList().size();
    	addRecord();
    	checkButtons();
    	display();
    }
    
    //Handles the event when the << button is clicked, first verifies the current entry then
    //goes to the previous entry
    @FXML
    void lastClicked(MouseEvent event) {
    	if(!verifyEntry())
    		return;
    	appendRecord();
    	currentRecord--;
    	checkButtons();
    	display();
    }

    //Handles the event when the - button is clicked. Removes the current element and updates
    //the current record if the element was the last in the list
    @FXML
    void minusClicked(MouseEvent event) {
    	records.removeRecord(currentRecord);
    	if(currentRecord >= records.getRecordList().size() && !records.getRecordList().isEmpty())
    		currentRecord = records.getRecordList().size() - 1;
    	checkButtons();
    	display();
    }

    //Handles the event when the >> button is clicked, verifies and appends the current
    //and updates the currentRecord count
    @FXML
    void nextClicked(MouseEvent event) {
    	if(!verifyEntry())
    		return;
    	appendRecord();
    	currentRecord++;
    	checkButtons();
    	display();
    }

    //Handles the even when the serialize button is clicked, verifies and appends the current entry
    //then serializes the entries in records to the user chosen xml file
    @FXML
    void serializeClicked(MouseEvent event) {
    	if(!verifyEntry())
    		return;
    	appendRecord();
    	Stage stage = (Stage)serializeBtn.getScene().getWindow();
    	FileChooser chooser = new FileChooser();
    	chooser.setTitle("Choose An XML File to save to");
    	try {
			File test = chooser.showSaveDialog(stage);
			fileLbl.setText("File: " + test.getName());
			BufferedWriter output = Files.newBufferedWriter(Paths.get(test.getName()));
			JAXB.marshal(records, output);	
			output.close();
    	}
    	catch(Exception e) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Serializing Error");
    		alert.setHeaderText("Problem Serializing");
    		alert.setContentText("You must choose a file of type xml");
    		alert.showAndWait();
    	}
    }
    
    //Appends a record to the records using the current record and the information from 
    //the text fields name state and phone
    void appendRecord() {
    	String name = nameTxt.getText();
       	String state = stateTxt.getText();
    	String phone = phoneTxt.getText();
    	records.appendRecord(currentRecord, new Record(name, state, phone));
    }
    
    //adds a blank record to the records
    void addRecord() {
    	records.addRecord(new Record());
    }
    
    //Handles the event when the Exit button is clicked, sends the user an alert
    //When the ok is clicked, the application closes, otherwise the error disappears
    @FXML
    void exitClicked(MouseEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Exit");
    	alert.setContentText("Are you sure you want to exit?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		Stage stage = (Stage)exitBtn.getScene().getWindow();
        	stage.close();
    	} 
    	else {
    	    alert.close();
    	}
    }

    //Handles the event when the load button is clicked, a file chooser appears for the user
    //to select a file to deserialize from. If no file is chosen or the deserialization fails,
    //an empty list is created for records.
    @FXML
    void loadClicked(MouseEvent event) {
    	currentRecord = 0;
    	Stage stage = (Stage)loadBtn.getScene().getWindow();
    	FileChooser chooser = new FileChooser();
    	chooser.setTitle("Choose An XML File");
    	try {
			File test = chooser.showOpenDialog(stage);
			fileLbl.setText("File: " + test.getName());
			BufferedReader input = Files.newBufferedReader(Paths.get(test.getName()));
			records = JAXB.unmarshal(input, RecordList.class);	
			input.close();
    	}
    	catch(Exception e) {
    		records = new RecordList();
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Deserializing Error");
    		alert.setHeaderText("Problem Deserializing");
    		alert.setContentText("The file is either invalid or contains no entries, an empty list has been created");
    		alert.showAndWait();
    	}
    	finally {
    		addBtn.setDisable(false);
	    	checkButtons();
	    	display();	
    	}
    	
    }
    
    //Updates the current display 
    private void display() {
    	
    	if(records.getRecordList().isEmpty()) {
    		nameTxt.setText("");
        	stateTxt.setText("");
        	phoneTxt.setText("");
        	entryLbl.setText("No Entries");
        	return;
    	}
    	entryLbl.setText(currentRecord+1 + " of " + records.getRecordList().size());
    	Record curr = records.getRecordList().get(currentRecord);
    	nameTxt.setText(curr.getName());
    	stateTxt.setText(curr.getState());
    	phoneTxt.setText(curr.getPhoneNumber());
    }
    
    //checks which conditions are met for each button and the text fields to be enabled or disabled
    private void checkButtons() {
    	if(currentRecord > 0)
    		lastBtn.setDisable(false);
    	else
    		lastBtn.setDisable(true);
    	if(records.getRecordList().isEmpty() || currentRecord == records.getRecordList().size() - 1)
    		nextBtn.setDisable(true);
    	else
    		nextBtn.setDisable(false);
    	if(records.getRecordList().isEmpty())
    		minusBtn.setDisable(true);
    	else
    		minusBtn.setDisable(false);
    	if(records.getRecordList().isEmpty()) {
    		nameTxt.setDisable(true);
	    	stateTxt.setDisable(true);
	    	phoneTxt.setDisable(true);
    		serializeBtn.setDisable(true);
    	}
    	else {
    		nameTxt.setDisable(false);
	    	stateTxt.setDisable(false);
	    	phoneTxt.setDisable(false);
	    	serializeBtn.setDisable(false);
    	}
    }
    
    //Verifies the entries typed in the text fields using regex
    private boolean verifyEntry() {
    	String name = nameTxt.getText();
    	String phone = phoneTxt.getText();
    	String state = stateTxt.getText();
    	boolean verify = true;
    	if(!name.matches("([A-Z][a-zA-Z]{2,})")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Invalid Value");
    		alert.setHeaderText("Invalid Name");
    		alert.setContentText("Name should start with an uppercase letter followed by at least two characters");
    		alert.showAndWait();
    		verify = false;
    	}
    	else if(!state.matches("[A-Z][a-zA-Z]{2,}|[A-Z][a-zA-Z]{2,} [A-Z][a-zA-Z]{2,}")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Invalid State");
    		alert.setHeaderText("Invalid State");
    		alert.setContentText("State should consist of one or two words");
    		alert.showAndWait();
    		verify = false;
    	}
    	else if(!phone.matches("[(][1-9][0-9]{2}[)] [1-9][0-9]{2} [-] [0-9]{4}")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Invalid Value");
    		alert.setHeaderText("Invalid Phone Number");
    		alert.setContentText("Ex (212) 555 - 1234");
    		alert.showAndWait();
    		verify = false;
    	}
    	return verify;
    }
    
    //Initializes the fields
    @FXML
    void initialize() {
    	  records = new RecordList();
    	  currentRecord = 0;
    	  assert nameTxt != null : "fx:id=\"nameTxt\" was not injected: check your FXML file 'Phonebook.fxml'.";
          assert stateTxt != null : "fx:id=\"stateTxt\" was not injected: check your FXML file 'Phonebook.fxml'.";
          assert phoneTxt != null : "fx:id=\"phoneTxt\" was not injected: check your FXML file 'Phonebook.fxml'.";
          assert entryLbl != null : "fx:id=\"entryLbl\" was not injected: check your FXML file 'Phonebook.fxml'.";
          assert minusBtn != null : "fx:id=\"minusBtn\" was not injected: check your FXML file 'Phonebook.fxml'.";
          assert addBtn != null : "fx:id=\"addBtn\" was not injected: check your FXML file 'Phonebook.fxml'.";
          assert lastBtn != null : "fx:id=\"lastBtn\" was not injected: check your FXML file 'Phonebook.fxml'.";
          assert nextBtn != null : "fx:id=\"nextBtn\" was not injected: check your FXML file 'Phonebook.fxml'.";
          assert loadBtn != null : "fx:id=\"loadBtn\" was not injected: check your FXML file 'Phonebook.fxml'.";
          assert serializeBtn != null : "fx:id=\"serializeBtn\" was not injected: check your FXML file 'Phonebook.fxml'.";
          assert fileLbl != null : "fx:id=\"fileLbl\" was not injected: check your FXML file 'Phonebook.fxml'.";
          assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'Phonebook.fxml'.";
    }
}