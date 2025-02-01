import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int userChoice = getUserChoice();
        System.out.println("your choice was " + userChoice);
    }

    public static int getUserChoice(){
        int input = -1;
        System.out.print(
            "0 - Exit\n" +
            "1 - Select directory\n" +
            "2 - List directory content\n" +
            "3 - Display file\n" +
            "4 - Delete file\n" +
            "5 - Mirror reflect file\n"
        );
        System.out.print("Select option: ");
        try{
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException nfe){
            System.out.println("Invalid data type, please enter only "
            + "whole numbers");
        }

        return input;
    }
}