public class Room {
    private String roomId;
    private int floor;
    private String password;

    public Room(String roomId, int floor, String password) {
        this.roomId = roomId;
        this.floor = floor;
        this.password = password;
    }

    public String getRoomId() {
        return roomId;
    }

    public int getFloor() {
        return floor;
    }

    public String getPassword() {
        return password;
    }
}
