import java.util.ArrayList;

public class Arranger{
    String[] input;
    String[] inputClone;
    public Arranger(String[] input)
    {
        this.input=input;
        inputClone=input.clone();
    }

    public boolean testTransform(String[] transformed,String[] input)
    {
        boolean flag=false;

        System.out.println("Transformed values:");
        for(String s:transformed)
        {
            System.out.println(s);
        }
        System.out.println();

        System.out.println("Input values:");
        for(String s:input)
        {
            System.out.println(s);
        }
        System.out.println();
        for(int i=0;i<input.length;i++)
        {
            String s1=transformed[i];
            String s2=input[i];

            String[] s1Tokenized=s1.split(", ");
            String[] s2Tokenized=s2.split(", ");


            for(int j=0;j<s1Tokenized.length;j++)
            {
                //if not equal, transformation performed
                System.out.println(s1Tokenized[j]+" "+s2Tokenized[j]);
                if(!s1Tokenized[j].equals(s2Tokenized[j]))
                    flag=true;
            }
        }
        return flag;
    }

    public PipeCustom[] sort(PipeCustom[] p)
    {
        
        ArrayList<PipeCustom> eliminationPipes=new ArrayList<>();
        ArrayList<PipeCustom> transPipes=new ArrayList<>();

        for(PipeCustom pipe:p)
        {
            // System.out.println("Testing sorting pipes filters");
            //logic for seeing if its a transofrmation or elimination pipe
            String[] tmp=pipe.execute(input);
            if(tmp.length!=inputClone.length)
            {
                //some lines were eliminated. This means that we had an elimination pipe. We can safely add it to the front of the order.
               eliminationPipes.add(pipe);
            }
            else
            {
                //test to see if it transformed something or its just an elim that did nothing
                boolean isTransformation=testTransform(tmp,inputClone);
                if(isTransformation)
                {
                    transPipes.add(pipe);
                }
                else
                {
                    eliminationPipes.add(pipe); 
                }
            }
        }

        ArrayList<PipeCustom> result=new ArrayList<>();

        result.addAll(eliminationPipes);
        result.addAll(transPipes);

        return result.toArray(new PipeCustom[0]);
    }

    public KnowledgeSource[] sort(KnowledgeSource[] k,Blackboard b)
    {
        ArrayList<KnowledgeSource> eliminationSources=new ArrayList<>();
        ArrayList<KnowledgeSource> transSources=new ArrayList<>();
        
        String[] aux=b.input.clone();
       
        for(KnowledgeSource source:k)
        {
            source.executeAndProduce(input, b);
            if(b.input.length!=inputClone.length)
            {
                eliminationSources.add(source);
            }
            else
            {
                //test to see if it transformed something or its just an elim that did nothing
                boolean isTransformation=testTransform(b.input,inputClone);
                if(isTransformation)
                    {
                        transSources.add(source);
                    }
                else
                    {
                       eliminationSources.add(source);
                    }
            }
        }
        // for(String s:b.input)
        // {
        //     System.out.println(s);
        // }
        // System.out.println();
        b.input=aux;
        // for(String s:b.input)
        // {
        //     System.out.println(s);
        // }
        // System.out.println();

        ArrayList<KnowledgeSource> result=new ArrayList<>();

        result.addAll(eliminationSources);
        result.addAll(transSources);
        return result.toArray(new KnowledgeSource[0]);
    }
}