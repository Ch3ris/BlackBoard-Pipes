import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

public class Client
{

    public static String[][] createListBuyers()
    {
        String[][] listBuyers=new String[3][2];
        listBuyers[0][0]="John";
        listBuyers[0][1]="Laptop";

        listBuyers[1][0]="Mary";
        listBuyers[1][1]="Phone";

        listBuyers[2][0]="Ann";
        listBuyers[2][1]="Book";
        
        return listBuyers;
    }
    public static void main(String[] args) {
        String input="Testing_Input.txt";
        Reader r=new Reader(input);
        String[] fileInput=r.readFile();
        String[][] listBuyers=createListBuyers();

        for(String s:fileInput)
        {
            System.out.println(s);
        }

        String[] copy=fileInput.clone();
        Pipes_Filters p=new Pipes_Filters(fileInput);

        PipeCustom[] pipes={
            new ResizeAttachment(),
            new SentimentalAnalysis(),
            new ProductNotReviewed(listBuyers),
            new ProfanitiesInReview(),
            new RemoveWebsiteLinks(),
            new PoliticalPropagandaReview(),
        };

        p.addPipeLine(pipes);
        // System.out.println();
        p.execute();
        

        Blackboard b=new Blackboard(copy,listBuyers);
        KnowledgeSource[] k={
                                new HTTPSource(),
                                new checkProductSource(listBuyers),
                                new checkProfanitiesSource(),
                                new ResizeSource(),
                                new checkPropagandaSource(),
                                new attachClassificationSource()
                            };
        b.addKnowledgeSources(k);
        System.out.println();
        b.executeBlackboardAndUpdate();
    }
}