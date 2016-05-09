//package datagenerator;
//
//import entity.Flight;
//import java.util.Random;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import openshift_deploy.DeploymentConfiguration;
//
//public class JsonGenerator {
//
//    static String[] airline = {"AngularJS Airline", "FlightHunter Airline"};
//    static String date;
//    static int numberOfSeats = 10;
//    static String FlightID;
//    static int travelTime;
//    static String flightNumber;
//    static String[] airports = {"CPH", "SXF", "BCN", "STN", "CDG"};
//    static Random random = new Random();
//    static String from;
//    static String to;
//    static String toFrom;
//    static int day;
//    static int month;
//    static double price;
//    static int lastPrice;
//    static int number1;
//    static int number2;
//    static int number4;
//    static String number3;
//    static EntityManager em;
//    static EntityManagerFactory emf;
//
//    public static void generator(int amount) {
//        emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
//        em = emf.createEntityManager();
//
//        for (int i = 0; i < amount; i++) {
////            System.out.print("{\n"
////                    + " \"airline\": \"" + airline[random.nextInt(airline.length)] + "\",\n"
////                    + " \"flights\": [\n"
////                    + " {\n"
////                    + " \"flightID\": \"" + flightID() + "\",\n"
////                    + " \"flightNumber\": \"COL2257\",\n"
////                    + " \"date\": \"");
////            makeDate();
////            setSeats();
////            makePrice(day, numberOfSeats);
////            travelTime();
////            System.out.print(fromTo());
////            System.out.println("\n }\n"
////                    + " ]\n"
////                    + "}");
//            Flight f = new Flight();
//            f.setDate(makeDate());
//            f.setFlightID(flightID());
//            f.setFlightNumber("col" + flightID().substring(0, 8));
//            f.setNumberOfSeats(setSeats());
//            f.setPrice(makePrice(Integer.parseInt(makeDay()), f.getNumberOfSeats()));
//
//        }
//    }
//
//    public static String flightID() {
//        number1 = random.nextInt(3999) + 1000;
//
//        number2 = random.nextInt(899999) + 100000;
//        number4 = random.nextInt(8999990) + 1000000;
//        number3 = "" + number1 + "-" + number2 + number4;
//        //System.out.println(number3);
//
//        return number3;
//    }
//
//    public static int setSeats() {
//        numberOfSeats = random.nextInt(150) + 1;
//        return numberOfSeats;
//    }
//
//    public static String[] fromTo() {
//        String[] result = new String[2];
//        from = airports[random.nextInt(5)];
//        to = airports[random.nextInt(5)];
//        if (from.equals(to)) {
//           return fromTo();
//        }
//        else{
//            result[0] = from;
//            result[1] = to;
//            return result;
//        }
//    }
//    
//    public static void makePrice(int day, int tickets) {
//        price = 0.4 * day * tickets;
//        lastPrice = (int) Math.round(price);
//        System.out.print(", \n"
//                + " \"totalPrice\": " + lastPrice + ", \n");
//    }
//
//    public static void travelTime() {
//        System.out.println(" \"traveltime\": 120,");
//    }
//
//    public static String makeMonth() {
//        month = random.nextInt(12) + 1;
//        if (month < 10) {
//            return "0" + month;
//        } else {
//            return "" + month;
//        }
//    }
//
//    public static String makeDay() {
//        day = random.nextInt(28) + 1;
//        if (day < 10) {
//            return "0" + day;
//        } else {
//            return "" + day;
//        }
//    }
//
//    public static String makeDate() {
//        Return "2016-" + makeMonth() + "-" + makeDay() + "T13:00:00.000Z";
//    }
//
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            
//        }
//       
//    }
//}
