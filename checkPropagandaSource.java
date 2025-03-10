public class checkPropagandaSource implements KnowledgeSource
{
    public void executeAndProduce(String[] input,Blackboard b)
    {
        String output="";
        Boolean[] nrChecks=new Boolean[input.length];
        int cnt=0;
        for(int i=0;i<input.length;i++)
        {
            nrChecks[i]=false;
        }
        for(String s:input)
        {
            String[] tokenizedString=s.split(", ");
            String review=tokenizedString[2];
            if(!review.contains("+++") && !review.contains("---"))
                nrChecks[cnt]=true;
            cnt++;
        }
        for(int i=0;i<input.length;i++)
        {
            if(nrChecks[i]==true)
            {
                output+=input[i]+"\n";
            }
        }
        b.input=output.split("\n");
    }
}