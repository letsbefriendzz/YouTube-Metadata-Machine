package webscrape;

import java.util.ArrayList;

public class main
{
    private static final int MAX_VOD = 15;

    public static void main(String[] args)
    {
        //init
        String[] videoURLs = new String[] {
                //DREAM VIDEOS
                "https://www.youtube.com/watch?v=X1OXNSCfgUQ", //Speedrunner VS Hunter
                "https://www.youtube.com/watch?v=vXS1pXWslxs", //Deathswap 1
                "https://www.youtube.com/watch?v=3tH4dyOPZnY", //Speenrunner VS 3 Hunters
                "https://www.youtube.com/watch?v=RTXS4MMngnA", //^ Rematch
                "https://www.youtube.com/watch?v=hgdSJdeGF_0", //^^ Finale
                "https://www.youtube.com/watch?v=Y7t5B69G0Dw", //^^^ Finale Rematch
                "https://www.youtube.com/watch?v=tylNqtyj0gs", //^^^^ Grand Finale
                "https://www.youtube.com/watch?v=7BCojznmtRE", //Speednrunner VS 4 Hunters
                "https://www.youtube.com/watch?v=CrtuA5HWFoU", //^ Rematch
                "https://www.youtube.com/watch?v=hDkuUZ3F1GU", //^^ Finale
                "https://www.youtube.com/watch?v=k4v6slOxxXs", //^^^ Finale Rematch
                "https://www.youtube.com/watch?v=a8ds6SnOGow", //^^^^ Grand Finale
                "https://www.youtube.com/watch?v=RJ0jdO5ZfU4", //Speedrunner VS 5 Hunters
                "https://www.youtube.com/watch?v=qqOxkuO3ip0", //^ Rematch
                "https://www.youtube.com/watch?v=FXcLiqIKdmw", //Random Item Challenge VS 2 Hunters
                //Append as necessary.
        };

        ArrayList<Video> videos = new ArrayList<>();

        for(int i = 0; i < videoURLs.length; i++)
        {
            Webscrape scraper = new Webscrape();
            Video vod = new Video();

            vod.setUrl(videoURLs[i]);
            vod = scraper.scrape(vod);

            videos.add(vod); //adds the vod to the videos structure

            System.out.println("Title:\t\t\t\t\t" + vod.getTitle());
            System.out.println("Title Word Count:\t\t" + vod.getTitleWordCount());
            System.out.println("Title Character Count:\t" + vod.getTitleCharCount());
            System.out.println(vod.getTags());
        }
    }
}