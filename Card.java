import java.util.List;

public class Card extends BaseCard {
    public Card(String cardId, List<Integer> floorAccess) {
        super(cardId, floorAccess);
    }

    @Override
    public void addFloorAccess(int floor) {
        if (!floorAccess.contains(floor)) {
            floorAccess.add(floor);
            AuditLogger.log("Added floor " + floor + " to Card " + cardId);
        }
    }

    @Override
    public void removeFloorAccess(int floor) {
        if (floorAccess.contains(floor)) {
            floorAccess.remove(Integer.valueOf(floor));
            AuditLogger.log("Removed floor " + floor + " from Card " + cardId);
        }
    }
}
