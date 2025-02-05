import java.util.List;

public class Card extends BaseCard {
    public Card(String cardId, List<Integer> floorAccess){
        super(cardId, floorAccess);
    }

    @Override
    public  void addFloorAccess(int floor){
        if (!floorAccess.contains(floor)){
            floorAccess.add(floor);
            System.out.println("✅ Added floor " + floor + " to Card: " + cardId);
        }else {
            System.out.println("⚠️ Floor " + floor + " is already accessible by Card: " + cardId);
        }
    }

    @Override
    public void removeFloorAccess(int floor) {
        if (floorAccess.contains(floor)) {
            floorAccess.remove(Integer.valueOf(floor));
            System.out.println("❌ Removed floor " + floor + " from Card: " + cardId);
        } else {
            System.out.println("⚠️ Floor " + floor + " is not accessible by Card: " + cardId);
        }
    }

    @Override
    public void displayCardInfo() {
        System.out.println("Card ID: " + cardId + ", Accessible Floors: " + floorAccess);
    }
}
