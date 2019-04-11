package model.facilityUse;

public class User extends UserAbstract {
	public User(UserInterface user) {
		super(user);
	}

	public int getUserID() {
		return getUserID();
	}
	public void setUserID(int UserID) {
		setUserID(UserID);
	}


	public String getUserFirstName() {
		return getUserFirstName();
	}
	public void setUserFirstName(String firstName) {
		setUserFirstName(firstName);
	}


	public String getUserLastName() {
		return getUserLastName();
	}
	public void setUserLastName(String lastName) {
		setUserLastName(lastName);
	}

	public String getUserGender() {
		return getUserGender();
	}
	public void setUserGender(String userGender) {
		setUserGender(userGender);
	}
}
