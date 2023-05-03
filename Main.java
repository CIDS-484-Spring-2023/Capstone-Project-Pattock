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

    int time, timecounter,bombcounter;
    int mousexleft, mouseyleft, mousexright, mouseyright;
    
    ArrayList<Flag> flagarray;
    ArrayList<ClickLocation> clicklocations;
    ArrayList<Locations> blanks;
    ArrayList<Locations> needscheck;
    ArrayList<Locations> beenchecked;
    ArrayList<Locations> clicked;
    
    Locations[][] grid;

    public Main()
    {
        //sets up the game window, creates and adds all frames and interactables
        f = new JFrame("Minesweeper");
            f.setSize(455,500);
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
            needscheck = new ArrayList<Locations>();
            beenchecked = new ArrayList<Locations>();
            clicked = new ArrayList<Locations>();

            //array for storing the list of flags
            flagarray = new ArrayList<Flag>();

            //array for storing which grid squares have been clicked/cleared
            clicklocations = new ArrayList<ClickLocation>();

            randomizeBombs();
            
            //acts as a screen for the JFrame
            Container c = f.getContentPane();

            startgame = false;
            endgame = false;
            
            //adds event listeners to the GraphicsPanel, allows changes to occur
            g = new GraphicsPanel(endgame,time,grid,flagarray,clicked,startgame,blanks); //main method where items will be passed to GraphicsPanel class
                g.addKeyListener(this);
                g.addMouseListener(this);
            
            //creates buttons for various use
            startbutton = new JButton("Start");
                startbutton.addActionListener(this);
            quitbutton = new JButton("Quit");
                quitbutton.addActionListener(this);
            ngbutton = new JButton("New Game");
                ngbutton.addActionListener(this);

                

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
            runGame();
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
                runner.sleep(19/2); //can't do decimals
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

    //randomizes location of all bombs and places them at those locations
    public void randomizeBombs()
    {
        //determines the random locations of the bombs
        for(int index = 0; index < bombx.length; index++)
        {
            int possx = (int)((Math.random() * 9));
            int possy = (int)((Math.random() * 9));
            boolean ok = true;
            
            //checks if 2 bombs are on the same space
            for(int i = 0; i < bombx.length; i++)
            {
                if(possx == bombx[i] && possy == bomby[i])
                {
                    ok = false;
                }
            }
            //if 2 bombs are on the same space, rerandomize the most recent bomb
            if(ok == true)
            {
                bombx[index] = possx;
                bomby[index] = possy;
            }
            else
            {
                index--;
            }
        }
        
        //places bombs in their locations
        grid = new Locations[9][9];
        for(int index = 0; index < grid.length; index++)
        {
            for(int i = 0; i < grid[0].length; i++)
            {
                grid[index][i] = new Locations(index,i,false);
            }
        }
        
        //marks locations as a bomb tile
        for(int index = 0; index < bombx.length; index++)
        {
            grid[bomby[index]][bombx[index]].makeBomb();
        }
        
        //counts how many bombs are next to location and places a number value on the tile
            for(int index = 0; index < grid.length; index++)
        {
            for(int i = 0; i < grid[0].length; i++)
            {
                bombcounter = 0;
                if(index > 0 && i > 0)
                if(grid[index-1][i-1].getBomb() == true){bombcounter++;}
                if(i > 0)
                if(grid[index][i-1].getBomb() == true){bombcounter++;}                
                if(index < grid.length - 1 && i > 0)
                if(grid[index+1][i-1].getBomb() == true){bombcounter++;}                 
                if(index > 0)
                if(grid[index-1][i].getBomb() == true){bombcounter++;}
                
                if(index < grid.length - 1)
                if(grid[index+1][i].getBomb() == true){bombcounter++;}
                if(index > 0 && i < grid[0].length - 1)
                if(grid[index-1][i+1].getBomb() == true){bombcounter++;}                
                if(i < grid[0].length - 1)
                if(grid[index][i+1].getBomb() == true){bombcounter++;}
                if(index < grid.length - 1 && i < grid[0].length - 1)
                if(grid[index+1][i+1].getBomb() == true){bombcounter++;}     
                grid[index][i].setBombs(bombcounter);
            }
            
        }
        

    }
    
    //adds beenchecked locations to clicked arraylist
    public void clicked()
    {
        for(int index = 0; index < beenchecked.size(); index++)
        {
            clicked.add(new Locations(beenchecked.get(index).getLocX(), beenchecked.get(index).getLocY()));
        }
    }
    
    //checks if a space has been checked as a blank or not
    public boolean hasbeenchecked(Locations loc)
    {
        boolean found = false;
        for(int index = 0; index < clicked.size(); index++)
        {
            if(loc.getLocX() == clicked.get(index).getLocX() && loc.getLocY() == clicked.get(index).getLocY())
            {
                found = true;
            }
        }
        
        return found;
    }
    
    //checks if spaces around clicked blank space -- is the clear blanks logic
    public void Blanks()
    {
           while(needscheck.size() > 0)
        {
            
            Locations loc = needscheck.get(0);
            
            for(int index = 0; index < grid.length; index++)
            {
                for(int i = 0; i < grid[0].length; i++)
                {
                    //System.out.println("test" + index + i);
                        //top left
                        if(index > 0 && i > 0 && hasbeenchecked(grid[loc.getLocX()-1][loc.getLocY()-1]) == false)
                        if(grid[loc.getLocX()-1][loc.getLocY()-1].drawBombs() == 0)
                        {
                            //System.out.println(" " + needscheck.get(0).getLocX() + needscheck.get(0).getLocY());
                            needscheck.add(grid[loc.getLocX()-1][loc.getLocY()-1]);
                        } 
                        else if(grid[loc.getLocX()-1][loc.getLocY()-1].getBomb() == false && grid[loc.getLocX()-1][loc.getLocY()-1].drawBombs() > 0)
                        {
                            clicked.add(grid[loc.getLocX()-1][loc.getLocY()-1]);
                        }
                    
                        //directly above
                        if(i > 0 && hasbeenchecked(grid[loc.getLocX()][loc.getLocY()-1]) == false)
                        if(grid[loc.getLocX()][loc.getLocY()-1].drawBombs() == 0)
                        {
                             needscheck.add(grid[loc.getLocX()][loc.getLocY()-1]);
                        }
                        else if(grid[index][i-1].getBomb() == false && grid[loc.getLocX()][loc.getLocY()-1].drawBombs() > 0)
                        {
                            clicked.add(grid[loc.getLocX()][loc.getLocY()-1]);
                        }
                    
                        //top right
                        if(index < grid.length -1 && i > 0 && hasbeenchecked(grid[loc.getLocX()+1][loc.getLocY()-1]) == false)
                        if(grid[loc.getLocX()+1][loc.getLocY()-1].drawBombs() == 0)
                        {
                             needscheck.add(grid[loc.getLocX()+1][loc.getLocY()-1]);
                        }
                        else if(grid[loc.getLocX()+1][loc.getLocY()-1].getBomb() == false && grid[loc.getLocX()+1][loc.getLocY()-1].drawBombs() > 0)
                        {
                            clicked.add(grid[loc.getLocX()+1][loc.getLocY()-1]);
                        }
                    
                        //left
                        if(index > 0 && hasbeenchecked(grid[loc.getLocX()-1][loc.getLocY()]) == false)
                        if(grid[loc.getLocX()-1][loc.getLocY()].drawBombs() == 0)
                        {
                            needscheck.add(grid[loc.getLocX()-1][loc.getLocY()]);
                        }
                        else if(grid[loc.getLocX()-1][loc.getLocY()].getBomb() == false && grid[loc.getLocX()-1][loc.getLocY()].drawBombs() > 0)
                        {
                            clicked.add(grid[loc.getLocX()-1][loc.getLocY()]);
                        }
                    
                        //right
                        if(index < grid.length -1 && hasbeenchecked(grid[loc.getLocX()+1][loc.getLocY()]) == false)
                        if(grid[loc.getLocX()+1][loc.getLocY()].drawBombs() == 0)
                        {
                            needscheck.add(grid[loc.getLocX()+1][loc.getLocY()]);
                        }
                        else if(grid[loc.getLocX()+1][loc.getLocY()].getBomb() == false && grid[loc.getLocX()+1][loc.getLocY()].drawBombs() > 0)
                        {
                            clicked.add(grid[loc.getLocX()+1][loc.getLocY()]);
                        }
                    
                        //bottom left
                        if(index > 0 && i < grid[0].length -1 && hasbeenchecked(grid[loc.getLocX()-1][loc.getLocY()+1]) == false)
                        if(grid[loc.getLocX()-1][loc.getLocY()+1].drawBombs() == 0)
                        {
                            needscheck.add(grid[loc.getLocX()-1][loc.getLocY()+1]);
                        }
                        else if(grid[loc.getLocX()-1][loc.getLocY()+1].getBomb() == false && grid[loc.getLocX()-1][loc.getLocY()+1].drawBombs() > 0)
                        {
                            clicked.add(grid[loc.getLocX()-1][loc.getLocY()+1]);
                        }
                    
                        //directly below
                        if(i < grid[0].length -1 && hasbeenchecked(grid[loc.getLocX()][loc.getLocY()+1]) == false)
                        if(grid[loc.getLocX()][loc.getLocY()+1].drawBombs() == 0)
                        {
                            needscheck.add(grid[loc.getLocX()][loc.getLocY()+1]);
                        }
                        else if(grid[loc.getLocX()][loc.getLocY()+1].getBomb() == false && grid[loc.getLocX()][loc.getLocY()+1].drawBombs() > 0)
                        {
                            clicked.add(grid[loc.getLocX()][loc.getLocY()+1]);
                        }
                    
                        //bottom right
                        if(index < grid.length -1 && i < grid[0].length -1 && hasbeenchecked(grid[loc.getLocX()+1][loc.getLocY()+1]) == false)
                        if(grid[loc.getLocX()+1][loc.getLocY()+1].drawBombs() == 0)
                        {
                            needscheck.add(grid[loc.getLocX()+1][loc.getLocY()+1]);
                        }
                        else if(grid[loc.getLocX()+1][loc.getLocY()+1].getBomb() == false && grid[loc.getLocX()+1][loc.getLocY()+1].drawBombs() > 0)
                        {
                            clicked.add(grid[loc.getLocX()+1][loc.getLocY()+1]);
                        }
                }
            }
            
            beenchecked.add(loc);
            
            needscheck.remove(loc);
            
        }
        
        /*
        for(int index = 0; index < beenchecked.size(); index++)
            {
                clicklocations.add(new ClickLocation(beenchecked.get(index).getLocX(),beenchecked.get(index).getLocY()));   
            }
        */
    }
    
    //the main Event listeners I plan to use are MouseClicked and ActionPerformed. If I have the time I would also like to add some keylistener functions, like p to pause
    public void mousePressed(MouseEvent evt)
    {}

    public void mouseClicked(MouseEvent evt)
    {
       //checks if a space is left clicked
       if(evt.getButton() == MouseEvent.BUTTON1)
       {
        //System.out.println("left"  );
        int mousex = evt.getX();
        int mousey = evt.getY();
        int xgrid = mousey/40 - 1;
        int ygrid = mousex/40 - 1;
        
        //checks if a bomb is clicked/ends game
        if(grid[ygrid][xgrid].getBomb() == true)
        {
         endgame = true;  
        }
        else
        {
            clicked.add(new Locations(ygrid,xgrid,false));
        }
        // System.out.println(""+ grid[ygrid][xgrid].drawBombs());
        
        //checks if a blank is clicked
        if(grid[ygrid][xgrid].getBlank() == true)
        {
            needscheck.clear();
            needscheck.add(grid[ygrid][xgrid]);
            Blanks();
            clicked();
            beenchecked.clear();
        }
        
        
        
       }
       //checks if a space is right clicked/adds a flag
         if(evt.getButton() == MouseEvent.BUTTON3)
       {
        //System.out.println("right"  );
        
        int mousex2 = evt.getX();
        int mousey2 = evt.getY();
        
        int xgrid = mousey2/40 - 1;
        int ygrid = mousex2/40 - 1;
        flagarray.add(new Flag(ygrid,xgrid));
        
        g.updateFlag(flagarray);
       }
        
       
        
        g.updateEnd(endgame);
        g.repaint();
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
            endgame = false;
            time = 0;
            beenchecked.clear();
            flagarray.clear();
            randomizeBombs();
            g.updateClickLocations(clicklocations);
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