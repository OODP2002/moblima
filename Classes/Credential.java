public class Credential {
    //Attributes
    private String username;
    private String password;
    private AdminRole role; 

    //Constructor 
    public Credential(String username, String password, AdminRole role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //Operations
    public String getUsername(){
        return this.username;
    }

    public AdminRole getRole(){
        return this.role;
    }

    //Change username 
    public boolean setUsername(String newUsername, String password){
        //User needs to provide the corrrect password to change username
        if(this.password.equals(password)){
            this.username = newUsername;
            return true;
        } else {
            return false;
        }
    }

    //Change password
    public boolean setPassword(String oldPassword, String newPassword){
        //User needs to provide the correct old password to change password
        //To do: implement password checks to ensure that password is 8 char, alphanumeric, contains capital letters and symbols
        if(this.password.equals(oldPassword)){
            this.password = newPassword;
            return true;
        } else {
            return false;
        }
    }

    //Check if correct password
    public boolean check(String password){
        
        return this.password.equals(password);
    }

    //Converts Credential instance to a string 
    public String toString(){
        String roleStr = "";
        switch(this.role){
            case CINEMASTAFF:
                roleStr = "CinemaStaff";
                break;
        }
        return this.username + "|" + this.password + "|" + roleStr + "|";
    }
}

