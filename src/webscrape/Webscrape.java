package webscrape;

//jsoup
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

//rename this class
public class Webscrape
{
    private static String keywordQuery = "<meta name=\"keywords\" content=";
    private static String titleQuery = "<meta name=\"title\" content=";
    private static String descQuery = "\"shortDescription\":";
    public int errorCode;

    public Video scrape(Video vod)
    {
        Video returnVod = new Video();
        final String url = vod.getUrl();
        System.out.println("Scraping\t" + url + "...");
        try
        {
            //doc shit
            final Document doc = Jsoup.connect(url).get();
            String outerHTML = doc.outerHtml();

            //searching the String
            String tags = new String();
            String title = new String();
            String desc = new String();

            if(!(outerHTML.contains(keywordQuery) && outerHTML.contains(titleQuery)))
            {
                System.out.println("Oof!");
                errorCode = -1;
            }
            else
            {
                //search for title
                int index = outerHTML.indexOf(titleQuery);
                for(int i = index + titleQuery.length(); outerHTML.charAt(i) != '>'; i++)
                {
                    title += outerHTML.charAt(i);
                }

                //search for tags
                index = outerHTML.indexOf(keywordQuery);
                for(int i = index + keywordQuery.length(); outerHTML.charAt(i) != '>'; i++)
                {
                    tags += outerHTML.charAt(i);
                }

                index = outerHTML.indexOf(descQuery);
                int endIndex = outerHTML.indexOf("isCrawlable"); //seems consistent

                for(int i = ++index + descQuery.length(); i < (endIndex - 2); i++) //subtracts two from index, because of the ",
                {
                    desc += outerHTML.charAt(i);
                }

                returnVod.setTitle(title);
                returnVod.setTags(tags);
                returnVod.setUrl(url);
                returnVod.setDesc(desc);
                returnVod.setEmpty(false);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return returnVod;
    }
}