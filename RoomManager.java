import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomManager {
    private List<Room> rooms;

    public RoomManager() {
        rooms = new ArrayList<>();
    }

    // Method to add a room
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Get all rooms on a specific floor
    public List<Room> getRoomsByFloor(int floor) {
        return rooms.stream()
                .filter(room -> room.getFloor() == floor)
                .collect(Collectors.toList());
    }

    // Get a specific room by its ID
    public Room getRoom(String roomId) {
        return rooms.stream()
                .filter(room -> room.getRoomId().equals(roomId))
                .findFirst()
                .orElse(null);
    }
}
