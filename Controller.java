import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller
{
    KnowledgeSource[] sources;
    public Controller(KnowledgeSource[] sources)
    {
        this.sources=sources;
    }

    public void executeCommand(String[] input,Blackboard b)
    {
        int cnt=0;
        String[][] outputs=new String[sources.length][];
        String result="";

        List<String[]> transformedOutputs=new ArrayList<>();

        for(KnowledgeSource k:sources)
        {
            String[] aux=b.input.clone();
            k.executeAndProduce(input,b);
            if(aux.length==b.input.length)
                {
                    transformedOutputs.add(b.input);
                }
            else
            {
                outputs[cnt]=b.input;
                cnt++;
            }
        }

        b.setFinalResult(commonPart);
    }
}