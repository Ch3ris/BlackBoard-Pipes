
import java.util.ArrayList;
import java.util.HashMap;

public class Pipes_Filters
{
    private PipeCustom[] pipeLine;
    private Arranger r;
    String[] input;
    public Pipes_Filters(String[] input)
    {
        this.input=input;
        r=new Arranger(input);
    }

    public void addPipeLine(PipeCustom[] pipeLine)
    {
        this.pipeLine=pipeLine;
    }

    public void execute()
    {
        for(String s:input)
        {
            System.out.println(s);
        }
        String[] tmp=input.clone();
        // System.out.println("Input before sorting the pipeline:");
        // for(String s:tmp)
        // {   
        //     System.out.println(s);
        // }
        System.out.println("Before sorting the pipes:");
        System.out.println();
        for(PipeCustom p:pipeLine)
        {
            System.out.println(p);
        }
        pipeLine=r.sort(pipeLine);
        
        //System.out.println();
        input=tmp;

        System.out.println("After sorting the pipes:");
        System.out.println();
        for(PipeCustom p:pipeLine)
        {
            System.out.println(p);
        }
        // System.out.println("Input after sorting the pipeline:");
        // for(String s:input)
        // {
        //     System.out.println(s);
        // }
        for(PipeCustom p : pipeLine)
        {
            // System.out.println("iterating through pipes");
            input=p.execute(input);

        }
        System.out.println("Final result in Pipes filters");
        for(String s:input)
        {
            System.out.println(s);
        }
    }
}