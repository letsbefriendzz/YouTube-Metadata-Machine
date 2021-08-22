package webscrape;

import javax.swing.*;

class UI
{
    static private JFrame frame;

    public UI()
    {
        createWindow();
        populateWindow();
    }

    private static void createWindow()
    {
        frame = new JFrame("YouTube Keyword Scraper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //
        frame.setSize(400,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void populateWindow()
    {
        
    }
}