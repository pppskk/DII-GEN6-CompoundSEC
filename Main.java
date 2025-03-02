import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CardManage manager = new CardManage();

        // สร้างบัตร Admin เพียงใบเดียวในตอนเริ่มต้น
        manager.addCard(new AdminCard("Admin", Arrays.asList(1, 2, 3, 4, 5), true));

        // ไม่เพิ่มบัตร Customer ตอนแรก

        // สร้าง RoomManager และเพิ่มห้องตัวอย่าง (ถ้าจำเป็น)
        RoomManager roomManager = new RoomManager();
        // ตัวอย่าง: ห้อง "312" ชั้น 2 รหัสผ่าน "pass123"
        roomManager.addRoom(new Room("312", 2, "pass123"));

        // สร้าง AccessFacade โดยส่ง CardManage และ RoomManager
        AccessFacade accessFacade = new AccessFacade(manager, roomManager);

        // เปิด ElevatorAccessGUI
        new ElevatorAccessGUI(manager, accessFacade);
    }
}
