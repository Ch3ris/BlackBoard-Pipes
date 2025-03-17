public class Arranger{
    String[] input;
    public Arranger(String[] input)
    {
        this.input=input;
    }

    public boolean testTransform(String[] transformed,String[] input)
    {
        boolean flag=false;
        for(int i=0;i<input.length;i++)
        {
            String s1=transformed[i];
            String s2=input[i];

            String[] s1Tokenized=s1.split(" ,");
            String[] s2Tokenized=s2.split(" ,");

            for(int j=0;j<s1Tokenized.length;j++)
            {
                //if not equal, transformation performed
                if(!s1Tokenized[j].equals(s2Tokenized[j]))
                    flag=true;
            }
        }
        return flag;
    }

    public PipeCustom[] sort(PipeCustom[] p)
    {
        PipeCustom[] newPipeOrder=new PipeCustom[p.length];
        int cnt=0;
        int inverse=1;
        for(PipeCustom pipe:p)
        {
            // System.out.println("Testing sorting pipes filters");
            //logic for seeing if its a transofrmation or elimination pipe
            String[] tmp=pipe.execute(input);
            if(tmp.length!=input.length)
            {
                //some lines were eliminated. This means that we had an elimination pipe. We can safely add it to the front of the order.
                newPipeOrder[cnt]=pipe;       
                cnt++;     
            }
            else
            {
                //test to see if it transformed something or its just an elim that did nothing
                boolean isTransformation=testTransform(input,tmp);
                if(isTransformation)
                {
                    newPipeOrder[p.length-inverse]=pipe;
                    inverse++;
                }
                else
                {
                    newPipeOrder[cnt]=pipe;       
                    cnt++;  
                }
                
            }
            
        }
        return newPipeOrder;
    }

    public KnowledgeSource[] sort(KnowledgeSource[] k,Blackboard b)
    {
        KnowledgeSource[] newKnowledgeOrder=new KnowledgeSource[k.length];
        int cnt=0;
        String[] aux=b.input;
        int inverse=1;
        for(KnowledgeSource source:k)
        {
            source.executeAndProduce(input, b);
            if(b.input.length!=input.length)
            {
                //elimination knowledge source
                newKnowledgeOrder[cnt]=source;
                cnt++;
            }
            else
            {
                //test to see if it transformed something or its just an elim that did nothing
                boolean isTransformation=testTransform(b.input,input);
                if(isTransformation)
                    {
                        newKnowledgeOrder[k.length-inverse]=source;
                        inverse++;
                    }
                else
                    {
                        newKnowledgeOrder[cnt]=source;
                        cnt++;
                    }
            }
        }
        b.input=aux;
        return newKnowledgeOrder;
    }
}