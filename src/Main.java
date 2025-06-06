import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nFloors = 4;
        int nSlotsPerFloor = 6;

        ParkingLot parkingLot = new ParkingLot("PR1234", nFloors, nSlotsPerFloor);

        System.out.println("Welcome to the Parking Lot Manager!");
        System.out.print("Enter your vehicle type (car, bike, truck): ");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.println("\nChecking available slots for '" + type + "'...");
        parkingLot.displayOpenSlots(type);

        System.out.print("\nWould you like to park your vehicle? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            System.out.print("Enter registration number: ");
            String regNo = scanner.nextLine().trim();

            System.out.print("Enter color: ");
            String color = scanner.nextLine().trim();

            String ticket = parkingLot.parkVehicle(type, regNo, color);
            if (ticket != null) {
                System.out.println("Vehicle parked! Ticket ID: " + ticket);
            } else {
                System.out.println("Sorry, no available slot for your vehicle type.");
            }
        } else {
            System.out.println("Okay, have a nice day!");
        }

        scanner.close();
    }
}
