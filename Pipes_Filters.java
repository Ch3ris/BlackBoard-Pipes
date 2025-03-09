import java.util.HashMap;

public class Pipes_Filters implements ProductNotReviewed,ProfanitiesInReview,PoliticalPropagandaReview,ResizeAttachment,RemoveWebsiteLinks,SentimentalAnalysis
{

    //username is not found in a list of buyers of the product
    public String[] checkProductReviewed(String[] input,String[][] listBuyers)
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
        return output.split("\n");
    }

    public String[] checkProfanities(String[] input)
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
            if(!review.contains("@#$%"))
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
        return output.split("\n");
    }

    public String[] checkPoliticalPropaganda(String[] input)
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
        return output.split("\n");
    }

    public String[] resize(String[] input)
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

    public String[] attachClassification(String[] input)
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
        return output.split("\n");
    }

    public String[] checkHTTP(String[] input)
    {
        String output="";
        int cnt=0;
        for(String s:input)
        {
            String[] tokenizedString=s.split(", ");
            String review = tokenizedString[2];
            review=review.replace("http","");
            System.out.println(review);
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

    public Pipes_Filters(String[] input,String[][] listBuyers)
    {
        input=checkProductReviewed(input, listBuyers);
        input=checkProfanities(input);
        input=checkPoliticalPropaganda(input);
        input=resize(input);
        input=checkHTTP(input);
        input=attachClassification(input);
        for(String s:input)
        {
            System.out.println(s);
        }
    }
}