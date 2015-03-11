package pace.logic;

public class ChronicDisease {

  private Integer diseaseID;

  private String name;

  private Test tests;

  
    
  
    public Integer getDiseaseID() {
		return diseaseID;
	}



	public void setDiseaseID(Integer diseaseID) {
		this.diseaseID = diseaseID;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Test getTests() {
		return tests;
	}



	public void setTests(Test tests) {
		this.tests = tests;
	}



	

	public PersistenceService  myPersistenceService;

}