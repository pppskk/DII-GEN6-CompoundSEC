import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        CardManage manager = new CardManage();

        // สร้างบัตรใหม่พร้อมสิทธิ์เข้าถึงชั้น
        BaseCard card1 = new Card("C001", Arrays.asList(5, 8, 12));
        BaseCard card2 = new Card("C002", Arrays.asList(3, 7));

        // เพิ่มบัตรลงในระบบ
        manager.addCard(card1);
        manager.addCard(card2);

        // แสดงข้อมูลบัตรทั้งหมด
        manager.displayAllCards();

        // เพิ่มและลบสิทธิ์การเข้าถึงชั้น
        card1.addFloorAccess(10);
        card1.removeFloorAccess(8);

        // แสดงข้อมูลหลังจากแก้ไข
        manager.displayAllCards();

        // ลบบัตร
        manager.removeCard("C002");

        // แสดงข้อมูลบัตรหลังจากลบ
        manager.displayAllCards();

    }
}
