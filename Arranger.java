public class Arranger{
    String[] input;
    public Arranger(String[] input)
    {
        this.input=input;
    }

    public PipeCustom[] sort(PipeCustom[] p)
    {
        PipeCustom[] newPipeOrder={};
        for(PipeCustom pipe:p)
        {
            //logic for seeing if its a transofrmation or elimination pipe
        }
        return newPipeOrder;
    }

    public KnowledgeSource[] sort(KnowledgeSource[] k)
    {
        KnowledgeSource[] newKnowledgeOrder={};
        for(KnowledgeSource source:k)
        {
            //logic to see if its a transformation/elimination source.
        }
        return newKnowledgeOrder;
    }
}