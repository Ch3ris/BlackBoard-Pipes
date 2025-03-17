
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
        String[] tmp=input.clone();
        pipeLine=r.sort(pipeLine);
        input=tmp;
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