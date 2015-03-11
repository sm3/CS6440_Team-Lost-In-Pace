package pace.logic;

public class Physician {

 private Integer physicianID;

 private String FirstName;

 private String LastName;

 private Patient  myPatient;
   

 private PersistenceService  myPersistenceService;
 
 
  public Integer getPhysicianID() {
	return physicianID;
}


public void setPhysicianID(Integer physicianID) {
	this.physicianID = physicianID;
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


public Patient getMyPatient() {
	return myPatient;
}


public void setMyPatient(Patient myPatient) {
	this.myPatient = myPatient;
}




public void getPatients() {
  }

}