import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardManage {
    private List<BaseCard> cards = new ArrayList<>();

    public void addCard(BaseCard card) {
        cards.add(card);
        System.out.println("✅ Added Card: " + card.getCardId());
    }

    public void displayAllCards() {
        System.out.println("\n📋 All Cards:");
        if (cards.isEmpty()){
            System.out.println("No cards available.");
        }else {
            for (BaseCard card : cards) {
                card.displayCardInfo();
            }
        }
    }

    public void removeCard(String cardId) {
        cards.removeIf(card -> card.getCardId().equals(cardId));
        System.out.println("❌ Removed Card: " + cardId);
    }

    public BaseCard getCardById(String cardId) {
        return cards.stream().filter(card -> card.getCardId().equals(cardId)).findFirst().orElse(null);
    }
}
