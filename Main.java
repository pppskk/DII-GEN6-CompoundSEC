import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        Scanner scanner = new Scanner(System.in);
        CardManage manager = new CardManage();

        while (true) {
            // เมนูหลัก
            System.out.println("\n=== 📇 Card Management System ===");
            System.out.println("1️⃣ Add Card");
            System.out.println("2️⃣ Remove Card");
            System.out.println("3️⃣ Edit Card Access");
            System.out.println("4️⃣ Display All Cards");
            System.out.println("5️⃣ Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // เคลียร์ช่องว่างหลังการกรอกตัวเลข

            switch (choice) {
                case 1:
                    // เพิ่มบัตรใหม่
                    System.out.print("Enter Card ID: ");
                    String cardId = scanner.nextLine();

                    System.out.print("Enter accessible floors (comma-separated, e.g., 3,5,8): ");
                    String floorsInput = scanner.nextLine();
                    List<Integer> floorAccess = new ArrayList<>();
                    for (String floorStr : floorsInput.split(",")) {
                        try {
                            floorAccess.add(Integer.parseInt(floorStr.trim()));
                        } catch (NumberFormatException e) {
                            System.out.println("⚠️ Invalid floor: " + floorStr);
                        }
                    }

                    BaseCard newCard = new Card(cardId, floorAccess);
                    manager.addCard(newCard);
                    break;

                case 2:
                    // ลบบัตร
                    System.out.print("Enter Card ID to remove: ");
                    String removeId = scanner.nextLine();
                    manager.removeCard(removeId);
                    break;
                case 3:
                    System.out.print("Enter Card ID to edit: ");
                    String editId = scanner.nextLine();
                    BaseCard editCard = manager.findCard(editId);
                    if (editCard != null) {
                        System.out.print("Enter new accessible floors (comma-separated, e.g., 2,4,6): ");
                        String newFloorsInput = scanner.nextLine();
                        List<Integer> newFloorAccess = new ArrayList<>();
                        for (String floorStr : newFloorsInput.split(",")) {
                            try {
                                newFloorAccess.add(Integer.parseInt(floorStr.trim()));
                            } catch (NumberFormatException e) {
                                System.out.println("⚠️ Invalid floor: " + floorStr);
                            }
                        }
                        editCard.setFloorAccess(newFloorAccess);
                        System.out.println("✅ Updated Card: " + editId + " with new floors: " + newFloorAccess);
                    } else {
                        System.out.println("⚠️ Card ID not found.");
                    }
                    break;

                case 4:
                    // แสดงข้อมูลบัตรทั้งหมด
                    manager.displayAllCards();
                    break;

                case 5:
                    // ออกจากโปรแกรม
                    System.out.println("👋 Exiting the program. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("⚠️ Invalid option. Please choose again.");
            }
        }

         */
        new ElevatorAccessGUI();

    }
}
