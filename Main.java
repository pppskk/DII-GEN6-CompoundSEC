import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CardManage manager = new CardManage();

        // เพิ่มบัตรที่มีสิทธิ์ห้อง
        manager.addCard(new RoomCard("Admin", Arrays.asList(1, 2, 3, 4, 5), Arrays.asList("301", "312")));
        manager.addCard(new RoomCard("Customer", Arrays.asList(1, 2, 3), Arrays.asList("312")));

        // ใช้ Facade ตรวจสอบสิทธิ์ก่อนขึ้นลิฟต์
        AccessFacade accessFacade = new AccessFacade(manager);

        // เปิด GUI สำหรับเลือกบัตร
        new ElevatorAccessGUI(manager, accessFacade);
    }
}
