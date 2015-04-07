package pace.logic;
import java.io.Serializable;
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
@Table(name = "PacePhysician")
@NamedQueries({
   
	 @NamedQuery(name = "PacePhysician.findById", query = "SELECT s FROM PacePhysician s WHERE s.physicianID = :id"),
	 @NamedQuery(name = "PacePhysician.findByFirstName", query = "SELECT s FROM PacePhysician s WHERE s.FirstName = :name"),
	 @NamedQuery(name = "PacePhysician.findByLastName", query = "SELECT s FROM PacePhysician s WHERE s.LastName = :name")
})
public class PacePhysician implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer physicianID;
    @Basic(optional = false)
    @Column(name = "FirstName")
    private String FirstName;
    @Basic(optional = false)
    @Column(name = "LastName")
    private String LastName;
    @Basic(optional = true)
    @Column(name = "myPatient")
    private PacePatient  myPatient;
   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "PacePatient", fetch = FetchType.LAZY)
    @OneToMany
    private List<PacePatient> patients;
    

    
    public PacePhysician(Integer id, String firstName, String lastName)
   {
	   this.physicianID = id;
	   this.FirstName = firstName;
	   this.LastName = lastName;
   }

   public PacePhysician()
   {
	   
   }

 
 
 
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


public PacePatient getMyPatient() {
	return myPatient;
}


public void setMyPatient(PacePatient myPatient) {
	this.myPatient = myPatient;
}




public void getPatients() {
  }

@Override
public int hashCode() {
    int hash = 0;
    hash += (physicianID != null ? physicianID.hashCode() : 0);
    return hash;
}

@Override
public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof PacePhysician)) {
        return false;
    }
    PacePhysician other = (PacePhysician) object;
    if ((this.physicianID == null && other.physicianID != null) || (this.physicianID != null && !this.physicianID.equals(other.physicianID))) {
        return false;
    }
    return true;
}
}