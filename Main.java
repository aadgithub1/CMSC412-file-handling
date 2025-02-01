import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String userChoice = getUserChoice();
        System.out.println("your choice was " + userChoice);
    }

    public static String getUserChoice(){
        System.out.print(
            "0\n" +
            "1\n" +
            "2\n" +
            "3\n" +
            "4\n" +
            "5\n"
        );
        System.out.print("Select option: ");
        return scanner.nextLine();
    }
}