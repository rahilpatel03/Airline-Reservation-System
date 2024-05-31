public class User {
    public String UserName;
    public String Password;
    public String Email;
    public long Phonenumber;

    public User(String userName, String password, String email, long phonenumber) {
        UserName = userName;
        System.out.println(UserName);
        Password = password;
        Email = email;
        Phonenumber = phonenumber;
    }

    boolean Login(String Username, String Password) {
        if (Username.equals(this.UserName)) {
            if (Password.equals(this.Password)) {
                return true;
            } else {
                System.out.println("Password Wrong!");
                return false;
            }
        } else {
            System.out.println("Username not found!");
            return false;
        }
    }
    public void displayDetails(){
        System.out.println("*******************************************************************************************");
        System.out.println("Name of passenger: "+UserName);
        System.out.println("Email of passenger: "+Email);
        System.out.println("Phone Number of passenger: "+Phonenumber);
        System.out.println("*******************************************************************************************");
    }
}
