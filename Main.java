import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Path path = null;
    public static File currentFile = null;
    public static void main(String[] args) {
        int userChoice = getUserChoice();
        
        if(userChoice == -1){
            System.out.println("Invalid input type, please restart.");
        } else if(userChoice == 0){
            System.out.println("Thanks for using the application!");
        } else if(userChoice == 1){
            setPath();
        } else if(userChoice == 2){
            verifyValidPath();
            try{
                Files.walk(path).forEach(System.out::println);
            } catch(IOException ioe){
                ioe.printStackTrace();
            }
        } else if(userChoice == 3){
            verifyValidPath();
            setCurrentFile();
            System.out.println("the file is " + currentFile.getName());
        }
    }

    public static void setCurrentFile(){
        System.out.println("Please enter the file name.");
        do{
            currentFile = new File(scanner.nextLine());
            if(currentFile == null || !currentFile.exists()){
                System.out.println("File does not exist, please enter "
                + "a valid file name.");
            }
        } while(currentFile == null || !currentFile.exists());
    }

    public static void verifyValidPath(){
        if(path == null){
            System.out.println("You need a valid path to perform this "
            + "operation.");
            setPath();
        }
    }

    public static void setPath(){
        do{
            System.out.print("Enter your directory path: ");
            path = Paths.get(scanner.nextLine());
            if(!Files.exists(path)){
                System.out.println("Path not found, please enter "
                + "a valid path.");
            }
        } while(!Files.exists(path));
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