package pace.logic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "PacePatient")
@NamedQueries({
   
	@NamedQuery(name = "PacePatient.findAll", query = "SELECT s FROM PacePatient s"),
    @NamedQuery(name = "PacePatient.findById", query = "SELECT s FROM PacePatient s WHERE s.patientID = :id"),
    @NamedQuery(name = "PacePatient.findByFirstName", query = "SELECT s FROM PacePatient s WHERE s.FirstName = :name"),
    @NamedQuery(name = "PacePatient.findByLastName", query = "SELECT s FROM PacePatient s WHERE s.LastName = :name")
    
})
public class PacePatient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "patientID")
    private String patientID;
    @Basic(optional = false)
    @Column(name = "FirstName")
    private String FirstName;
    @Basic(optional = false)
    @Column(name = "LastName")
    private String LastName;
    @Basic(optional = true)
    @Column(name = "physician")
    private PacePhysician physician;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "PacePatient", fetch = FetchType.LAZY)
    @OneToMany
    private List<ChronicDisease> conditions = new ArrayList<ChronicDisease>();
    @OneToMany
    private List<LabTest> tests;
    
    
    
    public PacePatient(String id, String firstName, String lastName)
   {
	   this.patientID = id;
	   this.FirstName = firstName;
	   this.LastName = lastName;
   }

   public PacePatient()
   {
	   
   }



  public String getPatientID() {
	return patientID;
}



public void setPatientID(String stringDt) {
	this.patientID = stringDt;
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



public PacePhysician getPhysician() {
	return physician;
}



public void setPhysician(PacePhysician physician) {
	this.physician = physician;
}

public List<ChronicDisease> getConditions()
{
	return conditions;
	
}
public List<LabTest> getTests() {
	return tests;
}



public void setTests(List<LabTest> tests) {
	this.tests = tests;
}

//This is to get Patient data from local db
public void getPatientData() {
  }

@Override
public int hashCode() {
    int hash = 0;
    hash += (patientID != null ? patientID.hashCode() : 0);
    return hash;
}

@Override
public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof PacePatient)) {
        return false;
    }
    PacePatient other = (PacePatient) object;
    if ((this.patientID == null && other.patientID != null) || (this.patientID != null && !this.patientID.equals(other.patientID))) {
        return false;
    }
    return true;
}
}