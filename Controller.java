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

    public String[] getCommonPart(String[] s1,String[] s2)
    {
        //here we just use an apperance vector
        //if a string/phrase appears two times in both s1 and s2 that means that the value is stored in both of them
        //if we have it appear only once(either in s1 or s2) that means that one of our sources elimnated that string from s1/s2
        //in that case, since its not the common part, we do not retain it.
        String output="";

        HashMap<String,Integer> appearanceVector=new HashMap<>();

        for(String s:s1)
        {
            appearanceVector.put(s, 1);
        }

        for(String s:s2)
        {
            if(appearanceVector.containsKey(s))
            {
                output+=s+"\n";
            }
        }
        return output.split("\n");
    }

    public String[] applyTransformations(String[] commonPart,String[] transformation)
    {
        //here we can just check where the value of a string was modified ; the new string will 
        //here you also don't need to check if commonpart.length>transformation.length or vice versa
        String output="";
        for(int i=0;i<commonPart.length; i++)
        {
            String s1=commonPart[i];
            String s2=transformation[i];

            String[] s1Tokenized=s1.split(" ,");
            String[] s2Tokenized=s2.split(" ,");

            for(int j=0;j<s1Tokenized.length;j++)
            {
                //if not equal, a transformation was performed.
                if(!s1Tokenized[j].equals(s2Tokenized[j]))
                    s1Tokenized[j]=s2Tokenized[j];
                output+=s1Tokenized[j];
                    
            }
            output+="\n";
        }
        return output.split("\n");
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
        //stored all outputs in array
        //check common part/intersection of each string in the output; output result will be the resultant string.

        // for(int i=0;i<cnt;i++)
        // {
        //     for(String s: outputs[i])
        //     {
        //         System.out.println(s);
        //     }
        //     System.out.println();
        // }

        String[] commonPart=outputs[0];
        for(int i=1;i<cnt;i++)
        {
            commonPart=getCommonPart(commonPart,outputs[i]);   
        }
        // System.out.println("Common part before transformations:");
        // for(String s:commonPart)
        // {
        //     System.out.println(s);
        // }

        //apply transformations to the common part.

        for(String[] transOutput:transformedOutputs)
        {
            commonPart=applyTransformations(commonPart, transOutput);
        }



        // System.out.println("after transformations");
        // for(String s:commonPart)
        // {
        //     System.out.println(s);
        // }

        // System.out.println(result);

        //after applying all of these transformations, we finally have the result.

        b.setFinalResult(commonPart);
    }
}