import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

//this class is where everything related to computing things will occur, including inicial bomb placement, any sorting systems, and event listener actions
public class Main implements MouseListener, ActionListener, KeyListener
{
    //declares all variables 
    JFrame f;
    JPanel main,south;
    GraphicsPanel g;
    JButton startbutton, quitbutton, ngbutton;


    int[] bombx, bomby;
    int[] flagx, flagy;

    boolean startgame, endgame;

    int time, timecounter;

    ArrayList<Locations> blanks;
    ArrayList<Flag> flagarray;
    ArrayList<ClickLocation> clicklocations;

    public Main()
    {
        //sets up the game window, creates and adds all frames and interactables
        f = new JFrame("Minesweeper");
            f.setSize(500,500);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setResizable(false);

            //arrays for Bomb x and y coordinates, will be paired by location in arrays
            bombx = new int[10];
            bomby = new int[10];

            //arrays for Flag x and y coordinates, will be paired by location in arrays
            flagx = new int[10];
            flagy = new int[10];

            //arrays for checking blank spaces, will add more but haven't come up with a plan to sort through blanks
            blanks = new ArrayList<Locations>();

            //array for storing the list of flags
            flagarray = new ArrayList<Flag>();

            //array for storing which grid squares have been clicked/cleared
            clicklocations = new ArrayList<ClickLocation>();

            //acts as a screen for the JFrame
            Container c = f.getContentPane();

            //adds event listeners to the GraphicsPanel, allows changes to occur
            g = new GraphicsPanel(); //main method where items will be passed to GraphicsPanel class
                g.addKeyListener(this);
                g.addMouseListener(this);
            
            //creates buttons for various use
            startbutton = new JButton("Start");
                startbutton.addActionListener(this);
            quitbutton = new JButton("Quit");
                quitbutton.addActionListener(this);
            ngbutton = new JButton("New Game");
                ngbutton.addActionListener(this);

                startgame = false;
                endgame = false;

            //adds a section on the button of the window for the buttons
            south = new JPanel();
                south.add(startbutton);
                south.add(quitbutton);
                south.add(ngbutton);
            
            //creates the section of the window where the came will be played
            main = new JPanel();
                main.setLayout(new BorderLayout());
                main.setSize(500,500);
                main.add(g, BorderLayout.CENTER);
                main.add(south,BorderLayout.SOUTH);

            c.add(main);
            f.show();
            rungame();
    }

    //slows down the game in order to play + timer
    public void runGame()
    {
        Thread runner = new Thread();
        while(endgame == false)
        {
            g.repaint();
            try
            {
                runner.sleep(10);
            }
            catch(InterruptedException e) {}
            if(startgame == true)
            {
                timecounter = timecounter + 1;
                if(timecounter == 100)
                {
                    timecounter = 0;
                    time = time + 1;
                }

                g.updateTime(time, startgame);
                g.repaint();
            }
        }
    }

    //the main Event listeners I plan to use are MouseClicked and ActionPerformed. If I have the time I would also like to add some keylistener functions, like p to pause
    public void mousePressed(MouseEvent evt)
    {}

    public void mouseClicked(MouseEvent evt)
    {

    }

    public void mouseReleased(MouseEvent evt)
    {}

    public void mouseEntered(MouseEvent evt)
    {}

    public void mouseExited(MouseEvent evt)
    {}

    //deals with button functions
    public void actionPerformed(ActionEvent event)
    {
        //starts a new game
        if(event.getSource() == startbutton)
        {
            startgame = true;
            time = 0;
            g.requestFocus();
        }

        //ends the current game
        if(event.getSource() == quitbutton)
        {
            endgame = true;
        }

        //clears the info from the previous game
        if(event.getSource() == ngbutton)
        {
            startgame = false;
            time = 0;
            clicklocations.clear();
            flagarray.clear();
            g.updateGame(time);
            g.repaint();
        }
    }

    public void keyPressed(KeyEvent evt)
    {}

    public void keyReleased(KeyEvent evt)
    {}

    public void keyTyped(KeyEvent evt)
    {}

}