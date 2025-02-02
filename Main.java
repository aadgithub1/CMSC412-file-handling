import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Path path = null;
    public static void main(String[] args) {
        int userChoice = getUserChoice();
        
        if(userChoice == 0){
            System.out.println("Thanks for using the application!");
        } else if(userChoice == 1){
            do{
                getFilePath();
                if(!Files.exists(path)){
                    System.out.println("Path not found, please enter "
                    + "a valid path.");
                }
            } while(!Files.exists(path));
        }
    }

    public static void getFilePath(){
        //enter do while loop here
        System.out.print("Enter your file path: ");
        path = Paths.get(scanner.nextLine());
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
        
        try{
            do{
                System.out.print("Select option: ");
                input = Integer.parseInt(scanner.nextLine());
            } while(input < 0 || input > 5);

        } catch (NumberFormatException nfe){}

        return input;
    }
}