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

    public void readLine(int lineNumber){
        try(BufferedReader reader = new BufferedReader(new FileReader(this.file))){
            File myFile = new File(this.file);
            Scanner myReader = new Scanner(myFile);
            int currentLine = 1;
            String line;
            while((line = reader.readLine()) != null){
                if (currentLine == lineNumber++) {
                    lineNumber--;
                    System.out.println("Line " + lineNumber + ": " + line);
                    break;
                }
            }
        }catch(Exception e){
            System.out.print(e);
        }
    }
}
