package model.facilityUse;

public abstract class UserAbstract{
    private UserInterface user;

    public UserAbstract(UserInterface user){
        this.user = user;
    }
    public int getUserID() {
        return user.getUserID();
    }

    public void setUserID(int UserID) {
        user.setUserID(UserID);
    }

    public String getUserFirstName() {
        return user.getUserFirstName();
    }
    public void setUserFirstName(String firstName) {
        user.setUserFirstName(firstName);
    }

    public String getUserLastName() {
        return user.getUserLastName();
    }
    public void setUserLastName(String lastName) {
        user.setUserLastName(lastName);
    }

    public String getUserGender(){
        return user.getUserGender();
    }
    public void setUserGender(String userGender){
        user.setUserGender(userGender);
    }
}
