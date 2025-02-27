import java.util.ArrayList;
import java.util.List;

public class AccessCard {
    private String cardId;
    private List<Integer> floorAccess;
    private List<String> roomAccess;

    public AccessCard(String cardId, List<Integer> floorAccess, List<String> roomAccess) {
        this.cardId = cardId;
        this.floorAccess = new ArrayList<>(floorAccess);
        this.roomAccess = new ArrayList<>(roomAccess);
    }

    public String getCardId() {
        return cardId;
    }

    public List<Integer> getFloorAccess() {
        return floorAccess;
    }

    public List<String> getRoomAccess() {
        return roomAccess;
    }

    public void addFloorAccess(int floor) {
        if (!floorAccess.contains(floor)) {
            floorAccess.add(floor);
        }
    }

    public void addRoomAccess(String room) {
        if (!roomAccess.contains(room)) {
            roomAccess.add(room);
        }
    }

    public void removeFloorAccess(int floor) {
        floorAccess.remove(Integer.valueOf(floor));
    }

    public void removeRoomAccess(String room) {
        roomAccess.remove(room);
    }

    public void displayCardInfo() {
        System.out.println("Card ID: " + cardId);
        System.out.println("Accessible Floors: " + floorAccess);
        System.out.println("Accessible Rooms: " + roomAccess);
    }
}
