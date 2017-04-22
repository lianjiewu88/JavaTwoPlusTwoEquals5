package serializable;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable{

	private static final long serialVersionUID = 32660034130636461L;
	private String name;
    private String id;
    private Date birthday;
    private transient String gender;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

    public String getID(){
    	return id;
    }
    
    public void setID(String id){
    	System.out.println("Set id called: " + id);
    	this.id = id;
    }
    public Date getBirthday() {
        return birthday;
    }
 
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
 
    public String getGender() {
        return gender;
    }
 
    public void setGender(String gender) {
        this.gender = gender;
    }
 
    @Override
    public String toString() {
        return "Employee {" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", gender=" + gender +
                ", birthday=" + birthday +
                '}';
    }
}