import java.util.*;

public class Locations
{
    int x, y;
    int numbombs;
    boolean bomb, blank;

    public Locations(int locx, int locy)
    {
        x = locx;
        y = locy;
        blank = true;
        bomb = false;
        numbombs = 0;
    }

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