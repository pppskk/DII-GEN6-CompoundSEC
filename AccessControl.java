public interface AccessControl {

    void addFloorAccess(int floor);
    void removeFloorAccess(int floor);
    void displayCardInfo();
}

//เพิ่มฟังก์ชันในการบันทึกประวัติการเข้าออก (Access Log)