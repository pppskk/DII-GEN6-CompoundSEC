import java.util.List;

public class AccessFacade {
    private CardManage cardManager;
    private RoomManager roomManager;

    public AccessFacade(CardManage cardManager, RoomManager roomManager) {
        this.cardManager = cardManager;
        this.roomManager = roomManager;
    }

    /**
     * Retrieves all rooms on a given floor using the RoomManager.
     *
     * @param floor the floor to retrieve rooms for
     * @return List of rooms on that floor
     */
    public List<Room> getRoomsByFloor(int floor) {
        return roomManager.getRoomsByFloor(floor);  // Use RoomManager to get rooms for that floor
    }

    /**
     * Checks if a card can access a particular floor and room (with password).
     */
    public boolean canAccess(String cardId, int floor, String room, String roomPassword) {
        BaseCard card = cardManager.getAllCards().stream()
                .filter(c -> c.getCardId().equals(cardId))
                .findFirst()
                .orElse(null);
        if (card == null) {
            AuditLogger.log("Access Denied: Card " + cardId + " not found.");
            return false;
        }

        // Check floor access
        if (!card.getFloorAccess().contains(floor)) {
            AuditLogger.log("Access Denied: Floor " + floor + " not allowed for Card " + cardId);
            return false;
        }

        // If it's a RoomCard, check if the user is allowed to access the room
        if (card instanceof RoomCard) {
            RoomCard roomCard = (RoomCard) card;
            if (room == null || room.isEmpty() || !roomCard.getRoomAccess().contains(room)) {
                AuditLogger.log("Access Denied: Room " + room + " not allowed for Card " + cardId);
                return false;
            }

            // Check the room for correct password
            Room targetRoom = roomManager.getRoom(room);
            if (targetRoom == null) {
                AuditLogger.log("Access Denied: Room " + room + " not found.");
                return false;
            }

            // Ensure the room is on the requested floor
            if (targetRoom.getFloor() != floor) {
                AuditLogger.log("Access Denied: Room " + room + " is on floor " + targetRoom.getFloor() +
                        ". Card " + cardId + " is allowed only that floor.");
                return false;
            }

            // Validate the room password
            if (!targetRoom.getPassword().equals(roomPassword)) {
                AuditLogger.log("Access Denied: Incorrect password for Room " + room);
                return false;
            }
        }

        // If the card passes all checks, grant access
        AuditLogger.log("Access Granted: Card " + cardId + " can access Floor " + floor +
                (room != null && !room.isEmpty() ? " Room " + room : ""));
        return true;
    }
}
