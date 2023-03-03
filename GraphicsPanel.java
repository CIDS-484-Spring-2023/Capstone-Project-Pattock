import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

//this class is where all the changes to the board will occur, including the flag marking, showing nubmbers and showing bombs upon clicking one.
public class GraphicsPanel extends JPanel
{//creates all variables needed for the Grapgics Panel, includes duplicates for items sent over from main
    
    Image one, two, three, four, five, six, seven, eight, mineimage, flagimage;
    int time;
    boolean startgame;

    public GraphicsPanel()
    {
        one = Toolkit.getDefaultToolkit().getImage("Number 1.png");
        two = Toolkit.getDefaultToolkit().getImage("Number 2.png");
        three = Toolkit.getDefaultToolkit().getImage("Number 3.png");
        four = Toolkit.getDefaultToolkit().getImage("Number 4.png");
        five = Toolkit.getDefaultToolkit().getImage("Number 5.png");
        six = Toolkit.getDefaultToolkit().getImage("Number 6.png");
        seven = Toolkit.getDefaultToolkit().getImage("Number 7.png");
        eight = Toolkit.getDefaultToolkit().getImage("Number 8.png");
        mineimage = Toolkit.getDefaultToolkit().getImage("Mine.png");
        flagimage = Toolkit.getDefaultToolkit().getImage("Flag.png");
    }

    public void updateStart(int time2, boolean startgame2)
    {
        time = time2;
        startgame = startgame2;
    }

    public void updateTime(int time2)
    {
        time = time2;
    }

    public void paint (Graphics g)
    {
        super.paint(g);


    }
}