package webscrape;

import java.util.ArrayList;

/*
public class Video

This is a class meant to store data about a YouTube video that has been scraped using whatever webscraping
technology you'd like to use. I'd like it to remain constant after instantiation, however I'm uncertain how
I should implement it at this time.

The Video class contains basic information about the video, such as the title, description, tags, and the URL
this data was scraped from. It also contains a boolean variable to determine whether or not it is empty.
*/
public class Video
{
    //Basic Video Data
    private boolean empty = true;
    private String title;
    private String url;
    private String desc;
    private ArrayList<String> tags;

    private static int totalTitleLength = 0;
    private static int totalTitleWords = 0;

    /*
    public Video()

    //////DESC//////

    This is the generic Video object constructor. It calls the "initEmpty()" function, which is where I'm
    storing this function's logic. I'm doing so because I'd like to to be portable, and I don't recall how
    to call this constructor from another constructor. Don't dupe code if you don't have to!
    */
    public Video()
    {
        initEmpty();

        //auxData
    }

    /*
    public Video(Video vod)

    //////DESC//////

    This is a copy constructor for the Video class. First, it checks if the Video object being passed to it
    is empty or not. If it is empty, it well default to the "initEmpty()" function, which is called in the
    generic Video constructor. If the Video object passed is not empty, the function copies the values from
    the title, URL, description, and tags and enters them into the respective variables of the Video being
    instantiated.
    */
    public Video(Video vod)
    {
        if(!vod.isEmpty())
        {
            this.title = vod.getTitle();
            this.url = vod.getUrl();
            this.desc = vod.getDesc();
            this.tags = vod.getTags();
        }
        else
        {
            initEmpty();
        }
    }

    /*
    public boolean isEqual(Video vod)

    //////DESC//////

    This is a simple function that compares two Videos, the present item and the parameter passed.
    If the title, description, URL, and tags are matching, the function returns that the two Video
    objects are equal (true). Otherwise, it returns that the two Video objects are not equal (false).
    */
    public boolean isEqual(Video vod)
    {
        return this.title == vod.getTitle() && this.desc == vod.getDesc() && this.url == vod.getUrl() && this.tags == vod.getTags();
    }

    /*
    private void initEmpty()

    //////DESC//////

    This is a simple function that's used to instantiate the Video object members for a new Video object.
    This function is NOT intended to be called anywhere except in constructors. I don't know how to ensure
    this is 100% followed, but please for the love of Jesus Christ our holy saviour don't fucking call this
    anywhere else.

    Anyhow, this function simply initializes all the members of the Video class to default values.
    */
    private void initEmpty()
    {
        empty = true;
        title = "";
        url = "";
        desc = "";

        tags = new ArrayList<String>();
    }

    /*
    private void formatDescription()

    //////DESC//////

    Due to however the HTML documents are acquired using Jsoup, I'm left with a document that literally
    types "\n" instead of interpreting them as a newline character. This function takes the value within
    the 'desc' variable and gets rid of the actual "\n" and replaces it with newline characters.

    //////KNOWN ISSUES//////

    Currently I get rid of some extra forward slashes in a totally different function. I currently
    forget where I put this logic. It would make more sense being in this function, as then all
    formatting functionality for the 'desc' variable is one place.
    */
    private void formatDescription()
    {
        String formatString = "";
        for(int i = 0; i + 1 < desc.length(); i++)
        {
            String temp = "";
            temp += desc.charAt(i);
            temp += desc.charAt(i+1);
            if(temp.charAt(0) == '\\' && temp.charAt(1) == 'n')
            {
                formatString += "\n";
                i++;
            }
            else
                formatString += desc.charAt(i);
        }
        this.desc = formatString;
    }

    /*
    private boolean formatTags()

    //////DESC//////

    This is a function that's used to parse the jSoup tag output into a more readable and, further,
    a more parsable form. It effectively turns the String into a proper csv style file. It returns
    true if the tags variable is not null, and false if it is.

    //////KNOWN ISSUES//////

    At present, this function checks if the passed 'tags' String is null or empty. It doesn't know
    what to return if it is either of those. At present, it's an empty string. Remember this when you
    call it, so that you can check for it.

    Note: look into throwing exceptions because that makes way more sense here.
    */
    private String formatTags(String tags)
    {
        if(tags != null && tags != "")
        {
            tags = tags.replaceAll(", ", ",");
            tags = tags.replaceAll("\"", "");
            return tags;
        }
        else
        {
            return "";
        }
    }

    /*
    public boolean setTags()

    //////DESC//////

    This is as function designed to populate the 'tags' ArrayList. This is a ArrayList
    that stores the phrases found in the tags variable.

    First, it checks if the 'newTags' parameter is null or empty. If it is either, it returns false.

    Second, assuming the 'newTags' parameter has passed the first test, it goes on to create a
    temporary holder String called tempPhrase. tempPhrase is used to store the respective phrases
    until it is time for it to be passed into the tagKeyPhrases ArrayList.

    Third, the entirety of the 'newTags' String is looped through. If no comma is found, the current
    character is appended to the end of 'tempPhrase'. If a comma is found, the tempPhrase
    variable is replaced with a lowercase version of itself, at which time it is then appended
    to the 'tagKeyPhrases' ArrayList. tempPhrase is set to empty, and the loop continues.

    Finally, true is returned if all is successful.
    */
    public boolean setTags(String newTags)
    {
        if(newTags == null || newTags.length() == 0)
        {
            return false;
        }
        else
        {
            newTags = formatTags(newTags);
            String tempPhrase = "";
            for(int i = 0; i < newTags.length(); i++)
            {
                if(newTags.charAt(i) == ',')
                {
                    tempPhrase = tempPhrase.toLowerCase();  //MAKE IT All lower case lol
                    tags.add(tempPhrase);     //Add the tempPhrase to the AL
                    tempPhrase = "";                        //set tempPhrase to empty
                }
                else
                {
                    tempPhrase += newTags.charAt(i);
                }
            }
            return true;
        }
    }

    /*
    public void display()

    //////DESC//////

    This is a simple function that dumps the rudimentary information about a given video object
    to the console. This includes the URL, title, description, tags, among others.
    */
    public void display()
    {
        System.out.println("URL:\t\t" + url);
        System.out.println("Title:\t\t" + title);
        System.out.println("Description:\n" + desc);
        System.out.println("Keywords:\t" + tags);

        System.out.println("\nTitle Data:\n");
        System.out.println("Words in Title:\t" + this.getTitleWordCount());
        System.out.println("Chars in Title:\t" + this.getTitleCharCount());

        System.out.println("\nDescription Data:\n");
        System.out.println("Words in Desc:\t" + this.getDescWordCount());
        System.out.println("Chars in Desc:\t" + this.getDescCharCount());
    }

    /*
    private int calcStringWords(String phrase)

    //////DESC//////
    This is a function that determines the number of words in a string based on the number
    of spaces and newlines it contains.

    First, the function ensures that the String passed to it is not empty or null. If it is,
    it returns -1 as an error code. Next, assuming the String passes the initial condition,
    a return value variable wordCount is created. This is by default 1, because there must
    be at least one word if the String in question isn't empty.

    Then, the function loops through each character of the String, checking if each character
    is a newline or a space. If it isn't either of this, the function does nothing. If it is,
    a new loop is entered that cycles through any concurrent newlines or spaces. This way, if
    multiple newlines or spaces occur consecutively, the function doesn't count each of them
    as their own word. Then, it increments the wordCount variable.

    Once completed, the function will return the wordCount variable.

    //////KNOWN ISSUES//////
    This function currently counts any series of newline characters or spaces at the end of
    a string as a word. This currently does not affect me, as the Strings I'm parsing don't
    have any whitespaces or newlines at the end. However, it needs fixing if it is going to
    be portable whatsoever.
    */
    public int calcStringWords(String phrase)
    {
        if(phrase.length() != 0 || phrase == null)
        {
            int wordCount = 1;
            for(int i = 0; i < phrase.length(); i++)
            {
                if(phrase.charAt(i) == ' ' || phrase.charAt(i) == '\n')
                {
                    for(int j = (i+1); j < phrase.length() && (phrase.charAt(j) == ' ' || phrase.charAt(j) == '\n'); j++)
                        i = j;
                    wordCount++;
                }
            }
            return wordCount;
        }
        else
        {
            System.out.println("No Contents!");
            return -1;
        }
    }

    public String getTitle() { return title; }

    public String getUrl() { return url; }

    public String getDesc() { return desc; }

    public int getDescCharCount() { return this.desc.length(); }

    public int getDescWordCount() { return calcStringWords(this.desc); }

    public boolean isEmpty() { return empty; }

    public int getTitleCharCount() { return this.title.length(); }

    public int getTitleWordCount() { return calcStringWords(this.title); }

    public ArrayList<String> getTags() { return this.tags; }

    public static int getTotalTitleLength() { return totalTitleLength; }

    /*
    public void setTitle(String newTitle)

    //////DESC//////

    This would be a generic setter, if I didn't have a static variable meant to track the sum of all
    instantiated Video objects. Because I'm tracking this, I have some extra logic.

    First, we check if the title is null or empty. If it's neither, we will subtract the length of
    the present title from the totalTitleLength. Then, we assign the new title, assign the titleWordCount
    and titleCharCount variables, and then add to the totalTitleLength the new title length.
    */
    public void setTitle(String newTitle)
    {
        // If there is a title present, remove that length from the total title length.
        if(this.title != null && this.title != "")
        {
            totalTitleLength -= this.title.length();
        }
        title = newTitle;

        //Modify the static variables; add the character count of the new title, as well as the word
        //count of the new title.
        totalTitleLength += this.getTitleCharCount();
        totalTitleWords += this.getTitleWordCount();
    }

    public void setUrl(String newUrl) { url = newUrl; }

    /*
    public void setDesc(String newDesc)

    //////DESC//////

    This is the setter for the 'desc' variable. The 'desc' variable contains the description of a
    video that has been parsed. It also calls the formatDescription() function, which replaces
    actual '\n' appearances with newlines and removes other junk that's left in by the HTML formatting.
    It also assigns the corresponding descWordCount and descCharCount variables, by calling desc.length()
    and calcStringWords(this.desc) respectively.
    */
    public void setDesc(String newDesc)
    {
        desc = newDesc;
        this.formatDescription();
    }

    //this function is dumb lol
    //correction; it's not dumb, it's necessary.
    public void setEmpty(boolean empty)
    {
        this.empty = empty;
    }
}