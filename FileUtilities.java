/**
 * to controll reading and writin files
 *
 * Toby Steiner
 * 16/6/2025
 */
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
    private int x;

    /**
     * Constructor for objects of class File
     */
    public FileUtilities()
    {
        // initialise instance variables
        x = 0;
    }

    public void read(){
        try{
            File myFile = new File("bankData - bankData.csv");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                List<List<String>> data = new ArrayList<>();
                ArrayList<String> arrL = new ArrayList<String>();
                List<String> lineData = Arrays.asList(myReader.nextLine().split(","));
                data.add(lineData);
                for (List<String> list : data) {
                    for (String str : list) {
                        arrL.add(str);
                    }
                }
                System.out.println(arrL.get(0), arrL.get(1), arrL.get(2), arrL.get(3));
            }
        }catch(Exception e){
            System.out.print(e);
        }
    }
}
