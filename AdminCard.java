import java.util.List;

public class AdminCard extends BaseCard {
    private boolean active;

    public AdminCard(String cardId, List<Integer> floorAccess, boolean active) {
        super(cardId, floorAccess);
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void addFloorAccess(int floor) {
        if (!floorAccess.contains(floor)) {
            floorAccess.add(floor);
            AuditLogger.log("Added floor " + floor + " to AdminCard " + cardId);
        }
    }

    @Override
    public void removeFloorAccess(int floor) {
        if (floorAccess.contains(floor)) {
            floorAccess.remove(Integer.valueOf(floor));
            AuditLogger.log("Removed floor " + floor + " from AdminCard " + cardId);
        }
    }

    @Override
    public void displayCardInfo() {
        super.displayCardInfo();
        System.out.println("Admin Card - Active: " + active);
    }
}
