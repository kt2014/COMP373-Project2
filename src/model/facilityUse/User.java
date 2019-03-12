package model.facilityUse;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String gender;  
    
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String fName) {
		this.firstName = fName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lName) {
		this.lastName = lName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

}
