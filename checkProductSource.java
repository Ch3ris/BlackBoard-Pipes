public class checkProductSource implements KnowledgeSource
{
    String[][] listBuyers;
    public checkProductSource(String[][] listBuyers)
    {
        this.listBuyers=listBuyers;
    }
    public void executeAndProduce(String[] input,Blackboard b)
    {
        String output="";
        Boolean[] nrChecks=new Boolean[input.length];
        for(int i=0;i<input.length;i++)
        {
            nrChecks[i]=false;
        }
        int cnt=0;
        for(String s: input)
        {
            String[] tokenizedString=s.split(", ");
            String buyer=tokenizedString[0].trim();
            String product=tokenizedString[1].trim();

            //list of buyers will always be of format String[n][2] ; this is because we will have always only 2 columns: The buyer and The product.
            for(int i=0;i<listBuyers.length;i++)
            {
                // System.out.println(buyer+" "+product);
                // System.out.println(listBuyers[i][0]+" "+listBuyers[i][1]);
                if(buyer.equals(listBuyers[i][0]) && product.equals(listBuyers[i][1]))
                    {
                        nrChecks[cnt]=true;
                        break;
                    }
            }
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