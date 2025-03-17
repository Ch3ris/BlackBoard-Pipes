public class ResizeAttachment implements PipeCustom
{
    public String[] execute(String[] input)
    {
        String output="";
        int cnt=0;
        for(String s:input)
        {
            String[] tokenizedString=s.split(", ");
            String attachment = tokenizedString[3];
            attachment=attachment.toLowerCase();
            String newString="";
            for(int i=0;i<4;i++)
            {
                //at attachment
                if(i==3)
                    newString+=attachment;
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