import java.util.List;

public class Card extends BaseCard {
    private String roomNumber;

    public Card(String cardId, List<Integer> floorAccess, String roomNumber){
        super(cardId, floorAccess);
        this.roomNumber=roomNumber;
    }
    public String getRoomNumber(){
        return roomNumber;
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
}
