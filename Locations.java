import java.util.*;

public class Locations
{
    int x, y;
    int numbombs;
    boolean bomb, blank;
    //creates the variables for the Locations class

    public Locations(int locx, int locy, boolean isbomb) //initializes the variables
    {
        x = locx;
        y = locy;
        blank = true;
        numbombs = 0;
        //9x9 grid with 10 mines
        if(isbomb == true)
        {
            makeBomb();
        }
        else
        {
            bomb = isbomb;
        }
    }

    //all methods below are basic get methods
    public int getLocX()
    {
        return x;
    }

    public int getLocY()
    {
        return y;
    }

    public boolean getBomb()
    {
        return bomb;
    }

    public boolean getBlank()
    {
        return blank;
    }

    //below methods deal with making a space a bomb
    public void makeBomb()
    {
        bomb = true;
        blank = false;
    }

    public void setBombs(int nb)
    {
        numbombs = nb;
        if(numbombs != 0)
        {
            blank = false;
        }
    }

    public int drawBombs()
    {
        return numbombs;
    }
}