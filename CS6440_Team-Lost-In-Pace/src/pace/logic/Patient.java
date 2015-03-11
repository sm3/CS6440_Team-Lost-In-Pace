package pace.logic;

public class Patient {

  private Integer patientID;

  private String FirstName;

  private String LastName;

    
  private Physician physician;
  private PersistenceService ps;
  
  

  public Integer getPatientID() {
	return patientID;
}



public void setPatientID(Integer patientID) {
	this.patientID = patientID;
}



public String getFirstName() {
	return FirstName;
}



public void setFirstName(String firstName) {
	FirstName = firstName;
}



public String getLastName() {
	return LastName;
}



public void setLastName(String lastName) {
	LastName = lastName;
}



public Physician getPhysician() {
	return physician;
}



public void setPhysician(Physician physician) {
	this.physician = physician;
}


//This is to get Patient data from local db
public void getPatientData() {
  }

}