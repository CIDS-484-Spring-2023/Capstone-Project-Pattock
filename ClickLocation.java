import java.util.*;

public class ClickLocation
{
    int x,y;
    

    public ClickLocation(int clickx, int clicky) //main will call this method when a user clicks on the board and then send the the clicked coordinates to the GraphicsPanel for changes
     {
        x = clickx;
        y = clicky;
    }

    //basic get methods
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}