import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Path path = null;
    public static File currentFile = null;
    public static FileOutputStream byteOutputStream = null;
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
                ArrayList<Path> directories = new ArrayList<>();
                ArrayList<Path> files = new ArrayList<>();
                //if the thing being walked is a file print size
                Files.walk(path, 1).forEach(p -> {
                    if(Files.isRegularFile(p)){
                        files.add(p);
                    } else{
                        directories.add(p);
                    }
                    
                });

                System.out.println("The subdirectories in "
                + directories.get(0) + " are:");

                for(int i = 1; i < directories.size(); i++){
                    System.out.println(directories.get(i));
                }

                System.out.println("\nThe files are");
                for(Path file : files){
                    System.out.println(file + ": " + Files.size(file) + " bytes");
                }
                
            } catch(IOException ioe){
                System.out.println("There was an IO issue with the file walk.");
            }
        } else if(userChoice == 3){
            verifyPath();
            setCurrentFile();
            printCurrentFileContents();
        } else if(userChoice == 4){
            verifyPath();
            verifyCurrentFile();
            deleteCurrentFile();
        } else if (userChoice == 5){
            verifyPath();
            verifyCurrentFile();
            mirrorCurrentFileBytes();
            printCurrentFileContents();
        }
    }

    public static void printWalkInfo(Path pathArg){
        if(Files.isRegularFile(pathArg)){
            //TO-DO
            //make  function reference mthod for walking dir
        }
    }

    public static void deleteCurrentFile(){
        currentFile.delete();
    }

    public static void printByteArray(byte[] bytes){
        for(int i = 1; i <= bytes.length; i++){
            System.out.printf("%02X ", bytes[i-1]);
            if(i % 16 == 0){
                System.out.println();
            }
        }
    }

    public static byte[] getCurrentFileBytes(){
        try{
            return Files.readAllBytes(Paths.get(currentFile.getAbsolutePath()));
        } catch(IOException ioe){
            System.out.println("whoops no bytes!");
        }
        return new byte[1];
    }

    public static void mirrorCurrentFileBytes(){
        byte[] currentFileBytes = getCurrentFileBytes();
        byte[] mirroredBytes = new byte[currentFileBytes.length];

        for(int i = currentFileBytes.length; i > 0; i--){
            mirroredBytes[currentFileBytes.length - i] = currentFileBytes[i-1];
        }

        try{
            byteOutputStream = new FileOutputStream(currentFile);
            byteOutputStream.write(mirroredBytes);
        } catch(FileNotFoundException fnfe){
            System.out.println("File is not there.");
        } catch(IOException ioe){
            System.out.println("There was an IO problem.");
        }
    }

    public static void printCurrentFileContents(){
        System.out.println("The current file contents are:");
        printByteArray(getCurrentFileBytes());
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