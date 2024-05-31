import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Booking {
    Scanner sc = new Scanner(System.in);
    Flight particularFlight = null;
    int key;
    public static int RecieptId = 0;

    public Booking(Flight particularFlight, int key) {
        this.key = key;
        this.particularFlight = particularFlight;
    }

    public void bookSeat() throws Exception {
        if (particularFlight.availableSeats == 0) {
            System.out.println("Sorry, there are no seats available.");
            return;
        }
        System.out.println("Please choose a seat class:");
        System.out.println("1. Economy");
        System.out.println("2. Premium");
        System.out.println("3. Business");
        String classChoice = sc.nextLine();
        String className;
        double price;
        int seatNumber;
        switch (classChoice) {
            case "1":
                className = "Economy";
                price = particularFlight.Fare[0];

                viewAvailableSeats();

                System.out.println("Please choose a seat number (1-" + "40" + "):");
                seatNumber = sc.nextInt();
                do {
                    if (seatNumber < 1 || seatNumber > 40) {
                        System.out.println("Invalid seat number. Please choose again.");
                    } else if (particularFlight.seatMap[seatNumber - 1]) {
                        System.out.println("Sorry, that seat is already booked. Please choose another seat.");
                    } else {
                        particularFlight.seatMap[seatNumber - 1] = true;
                        particularFlight.availableSeats--;
                        System.out.println();

                        Main.Users.get(Main.logged_userId).displayDetails();
                        
                        if (payment(price)) {
                            generateTicket(seatNumber, className, (price + (price * 0.12)));
                            break;
                        } else {
                            System.out.println("Ticket Cannot be Generated as Payment failed.");
                            System.out.println("Do you Wish to Continue Booking? enter the integer!");
                            System.out.println("1. Yes");
                            System.out.println("2. No");
                            System.out.print("--> ");
                            int choice;
                            try {
                                choice = sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("Enter your choice as integers only.");
                                choice = -1;
                                sc.nextLine();
                            }
                            if (choice == 2) {
                                break;
                            }
                        }
                        // passenger.displayTicket();
                        // System.out.println("Price : " + price + " Seat number : " + seatNumber);

                    }

                } while (true);
                Thread.sleep(4000);
                System.out.println("  ");
                System.out.println("");
                viewAvailableSeats();
                break;
            case "2":
                className = "Premium";
                price = particularFlight.Fare[1];

                viewAvailableSeats();

                System.out.println("Please choose a seat number (41-" + "80" + "):");
                seatNumber = sc.nextInt();
                do {
                    if (seatNumber < 40 || seatNumber > 80) {
                        System.out.println("Invalid seat number. Please choose again.");
                    } else if (particularFlight.seatMap[seatNumber - 1]) {
                        System.out.println("Sorry, that seat is already booked. Please choose another seat.");
                    } else {
                        particularFlight.seatMap[seatNumber - 1] = true;
                        particularFlight.availableSeats--;
                        System.out.println();

                        Main.Users.get(Main.logged_userId).displayDetails();
                        if (payment(price)) {
                            generateTicket(seatNumber, className, (price + (price * 0.12)));
                            break;
                        } else {
                            System.out.println("Ticket Cannot be Generated as Payment failed.");
                            System.out.println("Do you Wish to Continue Booking? enter the integer!");
                            System.out.println("1. Yes");
                            System.out.println("2. No");
                            System.out.print("--> ");
                            int choice;
                            try {
                                choice = sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("Enter your choice as integers only.");
                                choice = -1;
                                sc.nextLine();
                            }
                            if (choice == 2) {
                                break;
                            }
                        }
                        // passenger.displayTicket();
                        // System.out.println("Price : " + price + " Seat number : " + seatNumber);

                    }

                } while (true);
                Thread.sleep(4000);
                System.out.println("  ");
                System.out.println("");
                viewAvailableSeats();
                break;
            case "3":
                className = "Buisness";
                price = particularFlight.Fare[2];

                viewAvailableSeats();

                System.out.println("Please choose a seat number (81-" + "100" + "):");
                seatNumber = sc.nextInt();
                do {
                    if (seatNumber < 80 || seatNumber > 101) {
                        System.out.println("Invalid seat number. Please choose again.");
                    } else if (particularFlight.seatMap[seatNumber - 1]) {
                        System.out.println("Sorry, that seat is already booked. Please choose another seat.");
                    } else {
                        particularFlight.seatMap[seatNumber - 1] = true;
                        particularFlight.availableSeats--;
                        System.out.println();

                        (Main.Users.get(Main.logged_userId)).displayDetails();
                        if (payment(price)) {
                            generateTicket(seatNumber, className, (price + (price * 0.12)));
                            break;
                        } else {
                            System.out.println("Ticket Cannot be Generated as Payment failed.");
                            System.out.println("Do you Wish to Continue Booking? enter the integer!");
                            System.out.println("1. Yes");
                            System.out.println("2. No");
                            System.out.print("--> ");
                            int choice;
                            try {
                                choice = sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("Enter your choice as integers only.");
                                choice = -1;
                                sc.nextLine();
                            }
                            if (choice == 2) {
                                break;
                            }
                        }
                        // passenger.displayTicket();
                        // System.out.println("Price : " + price + " Seat number : " + seatNumber);

                    }

                } while (true);
                Thread.sleep(4000);
                System.out.println("  ");
                System.out.println("");
                viewAvailableSeats();
                break;
            default:
                System.out.println("Invalid choice. Booking cancelled.");
                return;
        }

    }

    public boolean payment(double price) throws Exception {
        System.out.println("Welcome to the Payment Portal!");

        System.out.println("Calculating your ticket Fare!");
        System.out.printf("%-20s %s%n", "Base Fare", price);
        System.out.printf("%-20s %s%n", "GST(12%)", (price * 0.12));
        double totalFare = price + (price * 0.12);
        System.out.printf("%-20s %s%n", "Total", totalFare);
        boolean paymentStatus = false;

        while (true) {
            System.out.println("Below are the available Payment Methods:");
            System.out.println("1. PayPal.");
            System.out.println("2. Credit/Debit Card.");
            System.out.println("3. Cancel Payment.");
            int payment_choice;
            try {
                System.out.print("--> ");
                payment_choice = sc.nextInt();
                System.out.println();
            } catch (Exception e) {
                System.out.println("Enter your choice as integers only.");
                payment_choice = -1;
                sc.nextLine();
            }
            switch (payment_choice) {
                case 1:
                    paymentStatus = PayPalPayment();
                    break;
                case 2:
                    paymentStatus = CardPayment();
                    break;
                case 3:
                    System.out.println("Exiting Payment Portal.");
                    break;
                default:
                    System.out.println("Enter Valid Choice!");
                    break;
            }
            if (payment_choice == 3 || paymentStatus == true) {
                break;
            }
        }
        return paymentStatus;
    }

    public boolean CardPayment() throws Exception {
        String CardNumber;
        while (true) {
            BufferedReader br = new BufferedReader(new FileReader(
                    "D:\\College\\coding practice\\Java_individual\\AirLineReservation\\RulesforVisaCard.txt"));
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
                Thread.sleep(300);
            }
            br.close();
            sc.nextLine();
            System.out.println("Please enter a Visa Card Number:");
            System.out.print("--> ");
            CardNumber = sc.nextLine();
            System.out.println();
            if (isValidVisaCardNo(CardNumber)) {
                break;
            } else {
                System.out.println("Enter a valid Card Number!");
            }
        }
        System.out.println("Valid Card Number recieved.");
        System.out.println("System Sending you OTP.....");
        Thread.sleep(1000);

        int OTP = (int) (Math.random() * 10000);
        System.out.println("Notification: " + OTP);
        int i;
        for (i = 1; i <= 3; i++) {
            System.out.print("Enter OTP--> ");
            int compareOTP = sc.nextInt();
            System.out.println();
            if (compareOTP == OTP) {
                System.out.println("Payment Successful!");
                return true;
            } else {
                System.out.println("you got " + (3 - i) + " trials to go.");
            }
        }
        if (i == 3) {
            System.out.println("You entered wrong OTP 3 times in a row! ");
            System.out.println("Payment Terminated.");
            return false;
        } else {
            return true;
        }

    }

    public boolean PayPalPayment() throws Exception {
        String paymentEmail;
        System.out.println("Do you want to use your Registered email for payment?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("--> ");
        int choice;
        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Enter your choice as integers only.");
            choice = -1;
            sc.nextLine();
        }
        switch (choice) {
            case 1:
                paymentEmail = Main.Users.get(Main.logged_userId).Email;
                break;
            case 2:
                System.out.println("Then Enter a Valid Email Id for payment: ");
                System.out.print("--> ");
                while (true) {
                    paymentEmail = sc.nextLine();
                    if (Main.isValidEmail(paymentEmail)) {
                        break;
                    } else {
                        System.out.println("Please Enter Again!");
                    }
                }
                break;

            default:
                System.out.println("Enter Valid Choice!");
        }
        System.out.println("Valid Email recieved.");
        System.out.println("System Sending you OTP.....");
        Thread.sleep(1000);

        int OTP = (int) (Math.random() * 10000);
        System.out.println("Notification: " + OTP);
        int i;
        for (i = 1; i <= 3; i++) {
            System.out.print("Enter OTP--> ");
            int compareOTP = sc.nextInt();
            System.out.println();
            if (compareOTP == OTP) {
                System.out.println("Payment Successful!");
                return true;
            } else {
                System.out.println("you got " + (3 - i) + " trials to go.");
            }
        }
        if (i == 3) {
            System.out.println("You entered wrong OTP 3 times in a row! ");
            System.out.println("Payment Terminated.");
            return false;
        } else {
            return true;
        }
    }

    public void viewAvailableSeats() {
        System.out.println("Number of available seats: " + particularFlight.availableSeats);
        System.out.println("Seat Map:");
        System.out.println(" ");

        int k = 1;
        for (int i = 0; i < 25; i++) {
            if (i == 10) {
                System.out.println("Premium Economy");
            } else if (i == 0) {
                System.out.println("Economy");
            } else if (i == 20) {
                System.out.println("Business");
            }
            for (int j = 1; j < 5; j++) {
                if (particularFlight.seatMap[k - 1] == true) {
                    System.out.print("X ");
                } else {
                    if (j == 3) {
                        System.out.print("    " + k + " ");
                    } else
                        System.out.print(k + " ");
                }
                k++;
            }
            System.out.println();
        }
        System.out.println("");
    }

    public void generateTicket(int seatNumber, String classChoice, double totalfare) throws IOException {
        String passengerName = String.valueOf((Main.Users.get(Main.logged_userId)).UserName);
        String email = Main.Users.get(Main.logged_userId).Email;
        String phoneNumber = String.valueOf(Main.Users.get(Main.logged_userId).Phonenumber);
        String flightId = String.valueOf(key);
        String origin = particularFlight.Origin;
        String destination = particularFlight.Destination;
        String seatNo = String.valueOf(seatNumber);
        String flightClass = classChoice;
        String timeOfFlight = particularFlight.departurTime.toString();
        String dateOfFlight = particularFlight.departurDate.toString();
        double totalFare = totalfare;

        RecieptId++;
        FileWriter fw = new FileWriter("ticket-" + RecieptId + ".txt");
        BufferedWriter bw = new BufferedWriter(fw);
        // bw.write(" _________________________________________________ ");
        // bw.newLine();
        // bw.write("| |");
        // bw.newLine();
        // bw.write("| RECIEPT |");
        // bw.newLine();
        // bw.write("| Gujarat Airport |");
        // bw.newLine();
        bw.write(" _________________________________________________ ");
        bw.newLine();
        bw.write("|                                                 |");
        bw.newLine();
        bw.write("|                AIRLINE TICKET                   |");
        bw.newLine();
        bw.write("|                                                 |");
        bw.newLine();
        bw.write("| Passenger: " + passengerName);
        int space1 = 49 - ("Passenger: " + passengerName).length();
        String s1 = String.format("%" + space1 + "s", "|");
        bw.write(s1);
        bw.newLine();
        bw.write("| Email: " + email);
        int space2 = 49 - ("Email: " + email).length();
        String s2 = String.format("%" + space2 + "s", "|");
        bw.write(s2);
        bw.newLine();
        bw.write("| Phone Number: " + phoneNumber);
        int space3 = 49 - ("Phone Number: " + phoneNumber).length();
        String s3 = String.format("%" + space3 + "s", "|");
        bw.write(s3);
        bw.newLine();
        bw.write("| Flight ID: " + flightId);
        int space4 = 49 - ("Flight ID: " + flightId).length();
        String s4 = String.format("%" + space4 + "s", "|");
        bw.write(s4);
        bw.newLine();
        bw.write("| Origin: " + origin);
        int space5 = 49 - ("Origin: " + origin).length();
        String s5 = String.format("%" + space5 + "s", "|");
        bw.write(s5);
        bw.newLine();
        bw.write("| Destination: " + destination);
        int space6 = 49 - ("Destination: " + destination).length();
        String s6 = String.format("%" + space6 + "s", "|");
        bw.write(s6);
        bw.newLine();
        bw.write("| Seat Number: " + seatNo);
        int space7 = 49 - ("Seat Number: " + seatNo).length();
        String s7 = String.format("%" + space7 + "s", "|");
        bw.write(s7);
        bw.newLine();
        bw.write("| Class: " + flightClass);
        int space8 = 49 - ("Class: " + flightClass).length();
        String s8 = String.format("%" + space8 + "s", "|");
        bw.write(s8);
        bw.newLine();
        bw.write("| Time of Flight: " + timeOfFlight);
        int space9 = 49 - ("Time of Flight: " + timeOfFlight).length();
        String s9 = String.format("%" + space9 + "s", "|");
        bw.write(s9);
        bw.newLine();
        bw.write("| Date of Flight: " + dateOfFlight);
        int space10 = 49 - ("Date of Flight: " + dateOfFlight).length();
        String s10 = String.format("%" + space10 + "s", "|");
        bw.write(s10);
        bw.newLine();
        bw.write("| Total Fare: $" + totalFare);
        int space11 = 49 - ("Total Fare: $" + totalFare).length();
        String s11 = String.format("%" + space11 + "s", "|");
        bw.write(s11);
        bw.newLine();
        bw.write("|_________________________________________________|");
        bw.close();
    }

    public boolean isValidVisaCardNo(String str) {
        String regex = "^4[0-9]{12}(?:[0-9]{3})?$";

        Pattern p = Pattern.compile(regex);

        if (str == null) {
            return false;
        }

        Matcher m = p.matcher(str);

        return m.matches();
    }
}
