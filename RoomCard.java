import java.util.List;

public class RoomCard extends BaseCard {
    private List<String> roomAccess;

    public RoomCard(String cardId, List<Integer> floorAccess, List<String> roomAccess) {
        super(cardId, floorAccess);
        this.roomAccess = roomAccess;
    }

    public List<String> getRoomAccess() {
        return roomAccess;
    }

    public void addRoomAccess(String room) {
        if (!roomAccess.contains(room)) {
            roomAccess.add(room);
        }
    }

    public void removeRoomAccess(String room) {
        roomAccess.remove(room);
    }

    @Override
    public void addFloorAccess(int floor) {
        if (!floorAccess.contains(floor)) {
            floorAccess.add(floor);
        }
    }

    @Override
    public void removeFloorAccess(int floor) {
        floorAccess.remove(Integer.valueOf(floor));
    }

    @Override
    public void displayCardInfo() {
        super.displayCardInfo();
        System.out.println("Accessible Rooms: " + roomAccess);
    }
}
