import java.util.List;

public abstract class BaseCard implements AccessControl {
    protected String cardId;
    protected List<Integer> floorAccess;

    public BaseCard(String cardId, List<Integer> floorAccess){
        this.cardId = cardId;
        this.floorAccess = floorAccess;
    }
    public  String getCardId(){
        return cardId;
    }
    public List<Integer> getFloorAccess(){
        return floorAccess;
    }

    @Override
    public void displayCardInfo() {
        System.out.println("Card ID: " + cardId + ", Accessible Floors: " + floorAccess);
    }
    /*
    public void setFloorAccess(List<Integer> newAccess) {
        this.floorAccess = newAccess;
    }
     */

}
