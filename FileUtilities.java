/**
 * to controll reading and writing files
 *
 * Toby Steiner
 * 16/6/2025
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;
import java.util.Arrays;
public class FileUtilities
{
    // instance variables - replace the example below with your own
    private String file;

    /**
     * Constructor for objects of class File
     */
    public FileUtilities(String file)
    {
        this.file = file;
    }

    public boolean hasNextLine(int lineNumber){
        boolean hasNextLine = true;
        try(BufferedReader reader = new BufferedReader(new FileReader(this.file))){
            File myFile = new File(this.file);
            Scanner myReader = new Scanner(myFile);
            int currentLine = lineNumber + 1;
            if(reader.readLine() != null){
                hasNextLine = true;
            }else{
                hasNextLine = false;
            }
        }catch(Exception e){
            System.out.print(e);
        }
        return hasNextLine;
    }

    public void simulation(){

    }

    public String readLine(int lineNumber){
        String line = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(this.file))){
            File myFile = new File(this.file);


            Scanner myReader = new Scanner(myFile);
            int currentLine = 1;
            while((line = reader.readLine()) != null){
                if (currentLine == lineNumber) {

                    return(line);
                }
                currentLine++;
            }
        }catch(Exception e){
            System.out.print(e);
        }
        return(line);
    }
    
    public boolean isValidCsv() {
        if (this.file == null || this.file.length() == 0) {
            System.out.println("Error: File does not exist, is a directory, or is empty.");
            return false;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                String trimmedLine = line.trim();
                if (trimmedLine.isEmpty()) {
                    continue;
                }

                
                String[] parts = trimmedLine.split(",");

                // all lines must have exactly 4 columns
                if (parts.length != 4) {
                    System.out.println("Validation failed on line " + lineNumber + ": Does not contain 4 columns.");
                    return false;
                }


                if (lineNumber == 1) {
                    if (!isHeaderValid(parts)) {
                        System.out.println("Validation failed on header line: Not all parts are valid words.");
                        return false;
                    }
                } else {
                    
                    if (!isDataLineValid(parts)) {
                        System.out.println("Validation failed on line " + lineNumber + ": Not all parts are valid integers.");
                        return false;
                    }
                }
            }
            
            if (lineNumber == 0) {
                System.out.println("Validation failed: The file is effectively empty (contains no non-empty lines).");
                return false;
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            return false;
        }
        return true;
    }

    private static boolean isHeaderValid(String[] parts) {
        for (String part : parts) {
            String trimmedPart = part.trim();
            if (trimmedPart.isEmpty() || isInteger(trimmedPart)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDataLineValid(String[] parts) {
        for (String part : parts) {
            String trimmedPart = part.trim();
            if (!isInteger(trimmedPart)) {
                return false;
            }
        }
        return true;
    }
    private static boolean isInteger(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
