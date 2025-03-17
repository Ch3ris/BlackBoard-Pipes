public class RemoveWebsiteLinks implements PipeCustom
{
    public String[] execute(String[] input)
    {
        String output="";
        int cnt=0;
        for(String s:input)
        {
            String[] tokenizedString=s.split(", ");
            String review = tokenizedString[2];
            review=review.replace("http","");
            // System.out.println(review);
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
        return output.split("\n");
    }
}