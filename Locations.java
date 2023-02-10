import java.util.*;

public class Locations
{
    int x, y;
    int numbombs;
    boolean bomb, blank;
    //creates the variables for the Locations class

    public Locations(int locx, int locy) //initializes the variables
    {
        x = locx;
        y = locy;
        blank = true;
        bomb = false;
        numbombs = 0;
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

}