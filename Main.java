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
            verifyPath();
            try{
                Files.walk(path).forEach(System.out::println);
            } catch(IOException ioe){
                ioe.printStackTrace();
            }
        } else if(userChoice == 3){
            verifyPath();
            setCurrentFile();
            printCurrentFileContents();
        } else if(userChoice == 4){
            verifyPath();
            verifyCurrentFile();
            deleteCurrentFile();
        }
    }

    public static void deleteCurrentFile(){
        currentFile.delete();
    }

    public static byte[] getCurrentFileBytes(){
        try{
            return Files.readAllBytes(Paths.get(currentFile.getAbsolutePath()));
        } catch(IOException ioe){
            System.out.println("whoops no bytes!");
        }
        return new byte[1];
    }

    public static void printCurrentFileContents(){
        byte[] bytes = getCurrentFileBytes();
        for(int i = 1; i <= bytes.length; i++){
            System.out.printf("%02X ", bytes[i-1]);
            if(i % 16 == 0){
                System.out.println();
            }
        }
    }

    public static void setCurrentFile(){
        System.out.println("Please enter the file name.");
        do{
            currentFile = new File(path.toFile(), scanner.nextLine());
            if(currentFile == null || !currentFile.exists()){
                System.out.println("File does not exist, please enter "
                + "a valid file name.");
            }
        } while(currentFile == null || !currentFile.exists());
    }

    public static void verifyCurrentFile(){
        if(currentFile == null || !currentFile.exists()){
            System.out.println("You need a valid file to perform "
            + "this operation.");
            setCurrentFile();
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

    public static void verifyPath(){
        if(path == null){
            System.out.println("You need a valid path to perform this "
            + "operation.");
            setPath();
        }
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