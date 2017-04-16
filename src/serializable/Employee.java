package serializable;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable{
    private String name;
    private String id;
    private Date birthday;
    private transient String gender;
    // private static final long serialVersionUID = -6849794470754667710L;
 
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