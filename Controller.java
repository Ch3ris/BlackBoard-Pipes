public class Controller
{
    KnowledgeSource[] sources;
    public Controller(KnowledgeSource[] sources)
    {
        this.sources=sources;
    }
    public void executeCommand(String[] input,Blackboard b)
    {
        for(KnowledgeSource k:sources)
        {
            k.executeAndProduce(input,b);
        }
    }
}