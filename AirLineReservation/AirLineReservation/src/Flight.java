import java.sql.Date;
import java.sql.Time;
import java.util.Map;

public class Flight {
    // Flight number, origin, destination, date, time, available seats, price,
    static int flightNo = 0;
    final static int totalSeats = 100;
    boolean[] seatMap = new boolean[totalSeats];
    int availableSeats=100;
    String Origin;
    String Destination;
    Date departurDate;
    Time departurTime;
    double Fare[] = new double[3];

    public Flight() {
    }

    public Flight(String origin, String destination, Date departurDate, Time departurTime, double[] fare) {
        Origin = origin;
        Destination = destination;
        this.departurDate = departurDate;
        this.departurTime = departurTime;
        Fare = fare;
    }

    public void initialiseFlights() {
        // flight 1.
        double fare[] = { 3000.0, 5000.0, 9000.0 };
        String o1 = "Hyderabad";
        String d1 = "Mumbai";
        Time departuretime = Time.valueOf("10:00:00");
        Date departuredate = Date.valueOf("2023-04-21");
        flightNo++;
        System.out.println("Flight 1 details :");
        System.out.println("FlightNumber : " + 1 + "    Origin : " + o1);
        System.out.println("Destination : " + d1 + "     DepartureTime : " + departuretime.toString()
                + "        DepartureDate :" + departuredate.toString());
        System.out.println("*****************************************************************");

        Main.Flights.put(flightNo, new Flight(o1, d1, departuredate, departuretime, fare));

        // flight 2.
        double fare2[] = { 4000.0, 6000.0, 11000.0 };
        String o2 = "Delhi";
        String d2 = "Kolkata";
        Time departuretime2 = Time.valueOf("21:45:00");
        Date departuredate2 = Date.valueOf("2023-05-02");
        flightNo++;
        System.out.println("Flight 2 details :");
        System.out.println("FlightNumber : " + 2 + "    Origin : " + o2);
        System.out.println("Destination : " + d2 + "     DepartureTime : " + departuretime2.toString()
                + "        DepartureDate :" + departuredate2.toString());
        System.out.println("*****************************************************************");

        Main.Flights.put(flightNo, new Flight(o2, d2, departuredate2, departuretime2, fare2));

        // flight 3.
        double fare3[] = { 3500.0, 6500.0, 12000.0 };
        String o3 = "Ahmedabad";
        String d3 = "Banglore";
        Time departuretime3 = Time.valueOf("16:35:00");
        Date departuredate3 = Date.valueOf("2023-04-29");
        flightNo++;
        System.out.println("Flight 3 details :");
        System.out.println("FlightNumber : " + 3 + "    Origin : " + o3);
        System.out.println("Destination : " + d3 + "     DepartureTime : " + departuretime3.toString()
                + "        DepartureDate :" + departuredate3.toString());
        System.out.println("*****************************************************************");

        Main.Flights.put(flightNo, new Flight(o3, d3, departuredate3, departuretime3, fare3));
    }

    public void displayAllFlights() {
        for (Map.Entry<Integer, Flight> flight : Main.Flights.entrySet()) {
            System.out.println("FlightNumber : " + flight.getKey() + "    Origin : " + flight.getValue().Origin);
            System.out.println("Destination : " + flight.getValue().Destination + "     DepartureTime : " + flight.getValue().departurTime.toString()
                    + "        DepartureDate :" + flight.getValue().departurDate.toString());
            System.out.println("*****************************************************************");

        }
    }
}
