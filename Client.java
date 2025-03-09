import java.io.FileReader;
import java.util.Scanner;
import java.io.File;

public class Client
{
    public static void main(String[] args) {
        String input="Testing_Input.txt";
        Reader r=new Reader(input);
        System.out.println(r.readFile());
        Pipes_Filters p=new Pipes_Filters();
        Blackboard b=new Blackboard();
    }
}