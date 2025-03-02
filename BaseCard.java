import java.util.List;

public abstract class BaseCard implements AccessControl {
    protected String cardId;
    protected List<Integer> floorAccess;
    protected String encryptedId; // Encrypted card id

    public BaseCard(String cardId, List<Integer> floorAccess) {
        this.cardId = cardId;
        this.floorAccess = floorAccess;
        // Encrypt the card id upon creation
        this.encryptedId = TimeEncryp.encrypt(cardId);
        AuditLogger.log("Card created: " + cardId + " | Encrypted ID: " + encryptedId);
    }

    public String getCardId() {
        return cardId;
    }

    public String getEncryptedId() {
        return encryptedId;
    }

    public List<Integer> getFloorAccess() {
        return floorAccess;
    }

    public abstract void addFloorAccess(int floor);
    public abstract void removeFloorAccess(int floor);

    public void displayCardInfo() {
        System.out.println("Card ID: " + cardId + ", Encrypted ID: " + encryptedId + ", Floors: " + floorAccess);
    }
}
