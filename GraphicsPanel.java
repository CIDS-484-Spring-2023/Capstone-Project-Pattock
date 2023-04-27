import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

//this class is where all the changes to the board will occur, including the flag marking, showing nubmbers and showing bombs upon clicking one.
public class GraphicsPanel extends JPanel
{//creates all variables needed for the Grapgics Panel, includes duplicates for items sent over from main
    
    Image one, two, three, four, five, six, seven, eight, mineimage, flagimage;
    int time;
    boolean startgame,endgame,isflag;
    ArrayList<Flag> flag;
    ArrayList<ClickLocation> clicklocations;
    ArrayList<Locations> clicked;
    ArrayList<Locations> blanks;
    Locations[][] grid;

    public GraphicsPanel(boolean endgame1, int time1, Locations[][] grid1, ArrayList<Flag> flag1, ArrayList<Locations> clicked1, boolean startgame1, ArrayList<Locations> blanks1)
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
        
        endgame = endgame1;
        time = time1;
        grid = grid1;
        flag = flag1;
        clicked = clicked1;
        //clicklocations = clicklocations1;
        startgame = startgame1;
        blanks = blanks1;
    }

    public void updateTime(int time2, boolean startgame2)
    {
        time = time2;
        startgame = startgame2;
    }

    public void updateGame(int time2)
    {
        time = time2;
    }
    
    public void updateFlag(ArrayList<Flag> flaga)
    {
        flag = flaga;
    }
    
    public void updateEnd(boolean endgame1)
    {
        endgame = endgame1;
    }
    
    public void updateClickLocations(ArrayList<ClickLocation> clicklocations1)
    {
        clicklocations = clicklocations1;
    }
    
    public void paint (Graphics g)
    {
        super.paint(g);
        
        g.drawString("Time: " + time,200,20);
        
        g.setColor(new Color(185,185,185));
        g.fillRect(40,40,360,360);
        
        g.setColor(Color.black);
        
        //Rectangles for the grid
        for(int index = 0; index < 9 ; index++)
        {
            g.drawRect(40 + index * 40,40,40,40);
        }
        for(int index = 10; index < 19; index++)
        {
            g.drawRect(index * 40 - 360,80,40,40);
        }
        for(int index = 20; index < 29; index++)
        {
            g.drawRect(index * 40 - 760,120,40,40);
        }
        for(int index = 30; index < 39; index++)
        {
            g.drawRect(index * 40 - 1160,160,40,40);
        }
        for(int index = 40; index < 49; index++)
        {
            g.drawRect(index * 40 - 1560,200,40,40);
        }
        for(int index = 50; index < 59; index++)
        {
            g.drawRect(index * 40 - 1960,240,40,40);
        }
        for(int index = 60; index < 69; index++)
        {
            g.drawRect(index * 40 - 2360,280,40,40);
        }
        for(int index = 70; index < 79; index++)
        {
            g.drawRect(index * 40 - 2760,320,40,40);
        }
        for(int index = 80; index < 89; index++)
        {
            g.drawRect(index * 40 - 3160,360,40,40);
        }
        
        //draws the bombs and images as they are clicked
        for(int index = 0; index < clicked.size(); index++)
        {
            if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 1)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
            if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 2)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
            if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 3)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
            if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 4)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
            if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 5)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
            if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 6)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
            if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 7)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
            if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 8)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
            if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].getBomb() == true)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
        }
        
        //makes bombs and numbers visable before hitting startgame
        /*
        if(startgame == false)
        {
          for(int index = 0; index < grid.length; index++)
          {
            
                if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 1)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
                if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 2)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
                if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 3)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
                if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 4)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
                if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 5)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
                if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 6)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
                if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 7)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
                if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].drawBombs() == 8)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
                if(grid[clicked.get(index).getLocX()][clicked.get(index).getLocY()].getBomb() == true)
                    {
                        g.drawImage(one,clicked.get(index).getLocX() * 40 + 40, clicked.get(index).getLocY() * 40 +40,40,40,null);
                    }
            
          }
        }
        */
        
        //draws the flags
        for(int index = 0; index < flag.size(); index++)
        {
            g.drawImage(flagimage,(flag.get(index).getFlagX() * 40 + 40), (flag.get(index).getFlagY() * 40 + 40),40,40,null);
        }
        
        
    }
}