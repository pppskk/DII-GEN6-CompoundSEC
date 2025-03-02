import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardManage {
    private List<BaseCard> cards;

    public CardManage() {
        cards = new ArrayList<>();
    }

    public void addCard(BaseCard card) {
        cards.add(card);
        AuditLogger.log("Card added: " + card.getCardId());
    }

    public List<BaseCard> getAllCards() {
        return cards;
    }

    public void removeCard(String cardId) {
        Optional<BaseCard> cardOpt = cards.stream()
                .filter(c -> c.getCardId().equals(cardId))
                .findFirst();
        if (cardOpt.isPresent()) {
            cards.remove(cardOpt.get());
            AuditLogger.log("Card removed: " + cardId);
        } else {
            AuditLogger.log("Attempted removal of non-existing card: " + cardId);
        }
    }

    // Example: modify floor access
    public void modifyCardFloorAccess(String cardId, List<Integer> newFloors) {
        Optional<BaseCard> cardOpt = cards.stream()
                .filter(c -> c.getCardId().equals(cardId))
                .findFirst();
        if (cardOpt.isPresent()){
            BaseCard card = cardOpt.get();
            card.floorAccess = newFloors;
            AuditLogger.log("Card " + cardId + " floor access modified to " + newFloors);
        }
    }
}
