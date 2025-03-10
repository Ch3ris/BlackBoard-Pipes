public class attachClassificationSource implements KnowledgeSource
{
    public void executeAndProduce(String[] input,Blackboard b)
    {
        String output="";
        int cnt=0;
        for(String s:input)
        {
            String[] tokenizedString=s.split(", ");
            String review = tokenizedString[2];
            int nrLower=0;
            int nrHigher=0;
            for(int i=0;i<review.length();i++)
            {

                char charac=review.charAt(i);
                if(Character.isUpperCase(charac))
                    nrHigher++;
                else if(Character.isLowerCase(charac))
                    nrLower++;
            }

            if(nrHigher>nrLower)
            {
                review+="+";
            }
            else if(nrHigher<nrLower)
            {
                review+="-";
            }
            else
                review+="=";

            String newString="";
            for(int i=0;i<4;i++)
            {
                //at attachment
                if(i==2)
                    newString+=review+", ";
                else if(i==3)
                    newString+=tokenizedString[i];
                else
                    newString+=tokenizedString[i]+", ";
            }
            input[cnt]=newString;
            cnt++;
        }
        for(int i=0;i<input.length;i++)
        {
            output+=input[i]+"\n";
        }
        b.input=output.split("\n");
    }
}