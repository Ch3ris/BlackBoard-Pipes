import java.io.FileReader;
import java.util.Scanner;
import java.io.File;

public class Reader
{
    String input;
    public Reader(String input)
    {
        this.input=input;
    }
    public String[] readFile()
    {
        String output="";
        //read from file.
        try{
            
            File f=new File(input);
            Scanner fScanner=new Scanner(f);
            while(fScanner.hasNextLine())
            {
                output+=fScanner.nextLine()+"\n";
            }
            fScanner.close();
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        //return lines read.
        return output.split("\n");
    }
}