import java.util.ArrayList;
import java.util.Iterator;

public class CardManage {
    private ArrayList<BaseCard> cards = new ArrayList<>();

    public void addCard(BaseCard card) {
        cards.add(card);
        System.out.println("✅ Added Card: " + card.getCardId());
    }
/*
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
        Iterator<BaseCard> iterator = cards.iterator();
        while (iterator.hasNext()){
            BaseCard card = iterator.next();
            if (card.getCardId().equals(cardId)) {
                cards.remove(card);
                System.out.println("❌ Removed Card: " + cardId);
                return;
            }
        }
        System.out.println("⚠️ Card ID not found: " + cardId);
    }
    public BaseCard findCard(String cardId) {
        for (BaseCard card : cards) {
            if (card.getCardId().equals(cardId)) {
                return card;
            }
        }
        return null;
    }

 */
    public BaseCard getCardById(String cardId) {
        for (BaseCard card : cards) {
        if (card.getCardId().equals(cardId)) {
            return card;
            }
        }
        return null;
    }
}
