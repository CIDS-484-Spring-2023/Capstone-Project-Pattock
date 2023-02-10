import java.util.*;

public class Flag
{
    int x, y;

    public Flag(int flagx, int flagy) //this class will be called when the user right clicks on a square, and then send the coordinates to the GraphicsPanel for changing
    {
        x = flagx;
        y = flagy;
    }

    //basic get methods
    public int getFlagX()
    {
        return x;
    }

    public int getFlagY()
    {
        return y;
    }
}