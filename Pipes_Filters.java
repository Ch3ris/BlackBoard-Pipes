
import java.util.ArrayList;
import java.util.HashMap;

public class Pipes_Filters
{
    private PipeCustom[] pipeLine;

    String[] input;
    public Pipes_Filters(String[] input)
    {
        this.input=input;
    }

    public void addPipeLine(PipeCustom[] pipeLine)
    {
        this.pipeLine=pipeLine;
    }

    public void execute()
    {
        for(PipeCustom p : pipeLine)
        {
            input=p.execute(input);

        }
        System.out.println("Final result in Pipes filters");
        for(String s:input)
        {
            System.out.println(s);
        }
    }
}