public class AccessFacade {
    private CardManage cardManager;

    public AccessFacade(CardManage cardManager) {
        this.cardManager = cardManager;
    }

    // ตรวจสอบว่าสามารถขึ้นชั้นและเข้าห้องได้หรือไม่
    public boolean canAccess(String cardId, int floor, String room) {
        // ค้นหาบัตรจาก ID
        BaseCard baseCard = cardManager.getCardById(cardId);
        if (baseCard == null) {
            System.out.println("⛔ Access Denied: Card " + cardId + " not found.");
            return false;
        }
        // ตรวจสอบสิทธิ์การเข้าถึงชั้น
        if (!baseCard.getFloorAccess().contains(floor)) {
            System.out.println("⛔ Access Denied: Floor " + floor + " not allowed for Card " + cardId);
            return false;
        }

        // ตรวจสอบสิทธิ์การเข้าห้อง (เฉพาะ RoomCard เท่านั้น)
        if (baseCard instanceof RoomCard) {
            RoomCard roomCard = (RoomCard) baseCard;
            if (!roomCard.getRoomAccess().contains(room)) {
                System.out.println("⛔ Access Denied: Room " + room + " not allowed for Card " + cardId);
                return false;
            }
        }

        System.out.println("✅ Access Granted: Card " + cardId + " can access Floor " + floor + " and Room " + room);
        return true;
    }
}
