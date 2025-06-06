import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private String parkingLotId;
    private List<List<Slot>> slots;

    public ParkingLot(String parkingLotId, int nfloors, int noOfSlotsPerFlr) {
        this.parkingLotId = parkingLotId;
        this.slots = new ArrayList<>();
        
        

        // Initialize slots for each floor
        for (int i = 0; i < nfloors; i++) {
            List<Slot> floorSlots = new ArrayList<>();

            // First slot is truck
            floorSlots.add(new Slot("truck"));

            // Next two slots are for bikes
            floorSlots.add(new Slot("bike"));
            floorSlots.add(new Slot("bike"));

            // Remaining slots are for cars
            for (int j = 3; j < noOfSlotsPerFlr; j++) {
                floorSlots.add(new Slot("car"));
            }

            slots.add(floorSlots);
        }
    }

    // Method to park a vehicle and return a ticket ID
    public String parkVehicle(String type, String regNo, String color) {
        Vehicle vehicle = new Vehicle(type, regNo, color);

        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                if (slot.getType().equals(type) && slot.getVehicle() == null) {
                    String ticketId = generateTicketId(i, j);
                    slot.setVehicle(vehicle);
                    slot.setTicketId(ticketId);
                    return ticketId;
                }
            }
        }
        System.out.println("No slot available for given type.");
        return null; // No available slot
    }
    
    public void unPark(String ticketId) {
    	//parses the ticketID to get floor and slot number where car is
    	String[] extract = ticketId.split("_");
    	int flr_idx=Integer.parseInt(extract[1])-1;
    	int slot_idx=Integer.parseInt(extract[2])-2;
    	
    	for(int i=0; i<slots.size(); i++) {
    		for(int j=0; j<slots.get(i).size(); j++) {
    			if(i==flr_idx && j==slot_idx) {
    				Slot slot = slots.get(i).get(j);
    				slot.setVehicle(null);
    				slot.setTicketId(null);
    				System.out.println("Unparked vehicle");
    			}
    		}
    	}
    }
    
    int getNoOfOpenSlots(String type) {
    	int count=0;
    	for(List<Slot> floor: slots) {
    		for(Slot slot: floor) {
    			if (slot.getVehicle() == null && slot.getType().equals(type))

    				count++;
    			
    		}
    	}
    	return count;
    }
    
    void displayOpenSlots(String type) {
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                
                if (slot.getVehicle() == null && slot.getType().equals(type)) {
                    System.out.println("Floor " + (i + 1) + " slot " + (j + 1));
                }
            }
        }
    }

    
    
    void displayOccupiedSlots(String type) {
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                if (slot.getVehicle() != null && slot.getType().equals(type)) {
                    System.out.println("Floor " + (i + 1) + " slot " + (j + 1));
                }
            }
        }
    }


    // Helper method to generate a ticket ID based on floor and slot index
    private String generateTicketId(int floor, int index) {
        return "TICKET-" + parkingLotId + "-" + floor + "-" + index;
    }
}

