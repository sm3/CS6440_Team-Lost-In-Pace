package pace.logic;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "LabTest")
@NamedQueries({
   
    @NamedQuery(name = "LabTest.findById", query = "SELECT s FROM LabTest s WHERE s.id = :id"),
    
    
})
public class LabTest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = true)
    @Column(name = "name")
    private String name;
    @Basic(optional = true)
    @Column(name = "unit")
    private String unit;
    @Basic(optional = true)
    @Column(name = "value")
    private double value;
       
    
    public LabTest(String id, String name)
   {
	   this.id = id;
	   this.name = name;
	  
   }

   public LabTest()
   {
	   
   }

   public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

 



  private Integer max;

  private Integer min;




  public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getId() {
	return id;
}


public void setId(String string) {
	this.id = string;
}


public Integer getMax() {
	return max;
}


public void setMax(Integer max) {
	this.max = max;
}


public Integer getMin() {
	return min;
}


public void setMin(Integer min) {
	this.min = min;
}




@Override
public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
}

@Override
public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof LabTest)) {
        return false;
    }
    LabTest other = (LabTest) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
        return false;
    }
    return true;
}
}