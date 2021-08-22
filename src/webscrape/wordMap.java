package webscrape;

import java.util.HashMap;
import java.util.Map;

public class wordMap
{
    //The map that stores the k:v pairs
    //String = word
    //Integer = number of times that word has appeared.
    private Map<String, Integer> values;

    //A constructor that instantiates the value of the Map to zip zilch.
    public wordMap()
    {
        values = new HashMap<>();
    }

    //generic getter for values
    public Map<String, Integer> getValues()
    {
        return values;
    }

    //generic setter for values
    public void setValues(Map<String, Integer> values)
    {
        this.values = values;
    }

    //At present, this algorithm is poorly written and should be avoided.
    //This is a function that scans each word in a string "input" that is passed to it.
    //It detects a word by finding a space. That's it. It's a pretty bad way to identify
    //a word, and its present status reflects that quite well.
    public void scan(final String input)
    {
        String scanItem = new String(); //string to contain the word that we wish to increment for.
        for (int i = 0; i < input.length(); i++) //loop until the end of the string.
        {
            if (input.charAt(i) == ' ')
            {
                //if word is detected...
                scanItem = scanItem.replace(",", "");   //get rid of all commas
                scanItem = scanItem.replace("\\", "");  //get rid of backslashes
                scanItem = scanItem.replace("\"", "");  //get rid of quotes
                scanItem = scanItem.replace(".", "");   //get rid of periods
                scanItem = scanItem.toLowerCase();                         //set to lower case
                if (values.get(scanItem) == null) //first time entry
                {
                    values.put(scanItem, 1);
                } else //pre-existing map key
                {
                    values.put(scanItem, values.get(scanItem) + 1);
                }
                scanItem = ""; //reset scanItem to an empty string.
            }
            else
            {
                scanItem += input.charAt(i); //append the scanned string until a space is reached.
            }
        }
    }

    public void contrastTags(video vod)
    {

    }

    //Weird code that I copied from stackoverflow that dumps the contents of a map,
    //further proving how poorly designed the aforementioned algorithm is.
    public void display()
    {
        for (Map.Entry<String, Integer> entry : values.entrySet())
            System.out.println("Key = " + entry.getKey() + "\t\t\t: Value = " + entry.getValue());
    }
}
