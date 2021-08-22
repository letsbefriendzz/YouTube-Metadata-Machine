package webscrape;

public class Keyword
{
    private String word;
    private int occurrences;

    public Keyword()
    {
        word = new String();
        occurrences = 0;
    }

    public String get() {return this.word;}
    public int getOccurrences() {return this.occurrences;}
    public void setWord(String newWord) {this.word = newWord;}
    public void setOccurrences(int newOcc) {this.occurrences = newOcc;}
}
