import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static HashMap<Integer, User> Users = new HashMap<Integer, User>();
    static HashMap<Integer, Flight> Flights = new HashMap<Integer, Flight>();
    static int UserId = 0;
    public static int logged_userId;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean login_successful = false;

        System.out.println("---------------------------------------------------");
        System.out.printf("%40s%n", "Welcome To AirLine Reservation!");
        System.out.println("---------------------------------------------------");
        System.out.println();

        while (true) {
            System.out.println("Please Pick one from the below:");
            System.out.println("1. Register.");
            System.out.println("2. Login.");
            System.out.println("3. Exit.");
            System.out.print("--> ");
            int login_choice;
            try {
                login_choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Enter your choice as integers only.");
                login_choice = -1;
                sc.nextLine();
            }
            switch (login_choice) {
                case 1:
                    System.out.println("Welcome to Registration Process...");
                    System.out.println("Please enter a username:");
                    System.out.print("--> ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.println();
                    String password;
                    String email;
                    long phonenumber;
                    while (true) {
                        BufferedReader br = new BufferedReader(new FileReader(
                                "D:\\College\\coding practice\\Java_individual\\AirLineReservation\\Rulesforpassword.txt"));
                        String s;
                        while ((s = br.readLine()) != null) {
                            System.out.println(s);
                            Thread.sleep(300);
                        }
                        br.close();
                        System.out.println("Please enter a Password:");
                        System.out.print("--> ");
                        password = sc.nextLine();
                        System.out.println();
                        if (isValidPassword(password)) {
                            break;
                        } else {
                            System.out.println("Enter a valid password!");
                        }
                    }
                    while (true) {
                        System.out.println("Please enter an Email: (There should be only one dot.)");
                        System.out.print("--> ");
                        email = sc.nextLine();
                        System.out.println();
                        if (isValidEmail(email)) {
                            break;
                        } else {
                            System.out.println("Enter a valid email!");
                        }
                    }

                    while (true) {
                        System.out.println("Phone number should be 10 digits long.");
                        System.out.println("Please enter a Phone Number:");
                        System.out.print("--> ");
                        String Phnno = sc.nextLine();
                        System.out.println();
                        if (Phnno.length() == 10) {
                            phonenumber = Long.parseLong(Phnno);
                            break;
                        } else {
                            System.out.println("Enter a valid Phone number!");
                        }
                    }
                    UserId++;
                    Users.put(UserId, new User(name, password, email, phonenumber));
                    break;
                case 2:
                    System.out.println("Welcome Back!");
                    System.out.println("Please Log in to your Account.");
                    System.out.println("Enter the User-Name: ");
                    System.out.print("-->");
                    sc.nextLine();
                    String username = sc.nextLine();
                    System.out.println();

                    System.out.println("Enter the User-Password: ");
                    System.out.print("-->");
                    String pass_login = sc.nextLine();
                    System.out.println();
                    login_successful = false;
                    for (Map.Entry<Integer, User> entry : Users.entrySet()) {
                        if (entry.getValue().Login(username, pass_login)) {
                            logged_userId = entry.getKey();
                            System.out.println("Login Successful!");
                            login_successful = true;
                            break;
                        }
                    }
                    if (!login_successful) {
                        System.out.println("Login Unsuccessful!");
                    }
                    break;
                case 3:
                    System.out.println("Exiting Login Portal...");
                    break;
                default:
                    System.out.println("Kindly enter a valid Choice.");
            }
            if (login_choice == 3 || login_successful == true) {
                break;
            }
        }

        if (login_successful) {
            System.out.println("Following is the list of Flights:");
            // just for checking purpose whether the flights are working or not.
            Flight dummyFlight = new Flight();
            dummyFlight.initialiseFlights();

            while (true) {
                System.out.println("Please Pick one choice from below:");
                System.out.println("1. Book Ticket.");
                System.out.println("2. Cancel Ticket.");
                System.out.println("3. View All Flights.");
                System.out.println("4. Search Flight.");
                System.out.println("5. Exit Menu.");
                int booking_choice;
                try {
                    booking_choice = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Enter your choice as integers only.");
                    booking_choice = -1;
                    sc.nextLine();
                }

                switch (booking_choice) {
                    case 1:
                        dummyFlight.displayAllFlights();
                        System.out.println("Choose any One Flight using its Flight id:");
                        System.out.print("--> ");
                        int flight_choice = sc.nextInt();
                        System.out.println();
                        if (Flights.containsKey(flight_choice)) {
                            Booking bookFlight = new Booking(Flights.get(flight_choice), flight_choice);
                            bookFlight.bookSeat();
                        } else {
                            System.out.println("Flight not found, enter a valid number!");
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        System.out.println("List of all Flights:");
                        dummyFlight.displayAllFlights();
                        break;
                    case 4:
                        break;
                    case 5:
                        System.out.println("Exiting The Booking menu!");
                        break;
                    default:
                        System.out.println("Enter a Valid Choice!");
                        break;
                }
                if (booking_choice == 5) {
                    break;
                }
            }
            sc.close();
        }
    }

    public static boolean isValidPassword(String password) {

        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);

        if (password == null) {
            return false;
        }

        Matcher m = p.matcher(password);

        return m.matches();
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValid(String s) {
        Pattern p = Pattern.compile("^\\d{10}$");

        Matcher m = p.matcher(s);

        return (m.matches());
    }
}