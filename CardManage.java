import java.util.ArrayList;

public class CardManage {
    private ArrayList<BaseCard> cards = new ArrayList<>();

    public void addCard(BaseCard card) {
        cards.add(card);
        System.out.println("✅ Added Card: " + card.getCardId());
    }

    public void displayAllCards() {
        System.out.println("\n📋 All Cards:");
        for (BaseCard card : cards) {
            card.displayCardInfo();
        }
    }

    public void removeCard(String cardId) {
        for (BaseCard card : cards) {
            if (card.getCardId().equals(cardId)) {
                cards.remove(card);
                System.out.println("❌ Removed Card: " + cardId);
                return;
            }
        }
        System.out.println("⚠️ Card ID not found: " + cardId);
    }
}
