package pace.logic;
import java.io.Serializable;
//import java.util.List;

import javax.persistence.Basic;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ChronicDisease")
@NamedQueries({
   
    @NamedQuery(name = "ChronicDisease.findById", query = "SELECT s FROM ChronicDisease s WHERE s.diseaseID = :id"),
    @NamedQuery(name = "ChronicDisease.findAll", query = "SELECT s FROM ChronicDisease s")
  
    
})
public class ChronicDisease implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = true)
    @Column(name = "id")
    private String id;
    @Basic(optional = true)
    @Column(name = "diseaseID")
    private String diseaseID;
    @Basic(optional = true)
    @Column(name = "name")
    private String name;
    
    
    
    public ChronicDisease(String id, String name)
   {
	   this.diseaseID = id;
	   this.name = name;
	  
   }

   public ChronicDisease()
   {
	   
   }


  
    public String getDiseaseID() {
		return diseaseID;
	}



	public void setDiseaseID(String string) {
		this.diseaseID = string;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	@Override
	public int hashCode() {
	    int hash = 0;
	    hash += (diseaseID != null ? diseaseID.hashCode() : 0);
	    return hash;
	}

	@Override
	public boolean equals(Object object) {
	    // TODO: Warning - this method won't work in the case the id fields are not set
	    if (!(object instanceof ChronicDisease)) {
	        return false;
	    }
	    ChronicDisease other = (ChronicDisease) object;
	    if ((this.diseaseID == null && other.diseaseID != null) || (this.diseaseID != null && !this.diseaseID.equals(other.diseaseID))) {
	        return false;
	    }
	    return true;
	}
	

}