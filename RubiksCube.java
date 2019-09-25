/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rubik;

import java.util.LinkedList;

/**
 *
 * @author sophiasingh
 */
public class RubiksCube {

    private Face front;
    private Face left;
    private Face right;
    private Face up;
    private Face down;
    private Face back;
    private State state;
    private LinkedList<State> stateSequence;

    public RubiksCube()
    {
        this.front = new Face(Colour.WHITE, 3, 3);
        this.left = new Face(Colour.BLUE, 3, 3);
        this.right = new Face(Colour.GREEN, 3, 3);
        this.up = new Face(Colour.RED, 3, 3);
        this.down = new Face(Colour.ORANGE, 3, 3);
        this.back = new Face(Colour.YELLOW, 3, 3);
        this.state = State.SOLVED;
        
        this.stateSequence = new LinkedList<>();
        stateSequence.add(State.SHUFFLED);
        stateSequence.add(State.BOTTOMCROSS);
        stateSequence.add(State.FIRSTLAYER);
        stateSequence.add(State.SECONDLAYER);
        stateSequence.add(State.TOPCROSS);
        stateSequence.add(State.PERMUTEEDGE);
        stateSequence.add(State.PERMUTECORNER);
        stateSequence.add(State.THIRDLAYER);
        stateSequence.add(State.SOLVED);
    }

    public RubiksCube(int x)
    {
        this.front = new Face(Colour.WHITE, x, x);
        this.left = new Face(Colour.BLUE, x, x);
        this.right = new Face(Colour.GREEN, x, x);
        this.up = new Face(Colour.RED, x, x);
        this.down = new Face(Colour.ORANGE, x, x);
        this.back = new Face(Colour.YELLOW, x, x);
        this.state = State.SOLVED;
    }

    public Face getFrontFace()
    {
        return this.front;
    }

    public Face getUpFace()
    {
        return this.up;
    }

    public Face getDownFace()
    {
        return this.down;
    }

    public Face getLeftFace()
    {
        return this.left;
    }

    public Face getRightFace()
    {
        return this.right;
    }

    public Face getBackFace()
    {
        return this.back;
    }

    public State getState()
    {
        return this.state;
    }
    
    public LinkedList<State> getStateSequence()
    {
        return this.stateSequence;
    }

    public void front()
    {
        this.front.clockwiseTurn();
        Colour[] c1 = new Colour[this.up.getRow()];

        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.up.getGrid()[this.up.getRow()-1][i];
        }

        Colour[] c2 = this.right.swapCol(c1, 0);
        Colour[] c3 = this.down.swapRowReverse(c2, 0);
        c1 = this.left.swapCol(c3, this.left.getCol()-1);
        this.up.swapRow(c1, this.up.getRow()-1);
    }

    public void frontInverse()
    {
        this.front.antiClockwiseTurn();
        Colour[] c1 = new Colour[this.up.getRow()];

        for(int i = c1.length-1; i>=0; i--)
        {
            c1[i] = this.up.getGrid()[this.up.getRow()-1][i];
        }

        Colour[] c2 = this.left.swapCol(c1, this.left.getCol()-1);
        Colour[] c3 = this.down.swapRow(c2, 0);
        c1 = this.right.swapColReverse(c3, 0);
        this.up.swapRow(c1, this.up.getRow()-1);
    }

    public void up()
    {
        this.up.clockwiseTurn();
        Colour[] c1 = new Colour[this.front.getRow()];

        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.front.getGrid()[0][i];
        }

        Colour[] c2 = this.left.swapRow(c1, 0);
        Colour[] c3 = this.back.swapRow(c2, 0);
        c1 = this.right.swapRow(c3, 0);
        this.front.swapRow(c1, 0);
    }

    public void upInverse()
    {
        this.up.antiClockwiseTurn();
        Colour[] c1 = new Colour[this.front.getRow()];

        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.front.getGrid()[0][i];
        }

        Colour[] c2 = this.right.swapRow(c1, 0);
        Colour[] c3 = this.back.swapRow(c2, 0);
        c1 = this.left.swapRow(c3, 0);
        this.front.swapRow(c1, 0);
    }

    public void down()
    {
        this.down.clockwiseTurn();
        Colour[] c1 = new Colour[this.front.getRow()];

        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.front.getGrid()[this.front.getRow()-1][i];
        }

        Colour[] c2 = this.right.swapRow(c1, this.left.getRow()-1);
        Colour[] c3 = this.back.swapRow(c2, this.back.getRow()-1);
        c1 = this.left.swapRow(c3, this.right.getRow()-1);
        this.front.swapRow(c1, this.front.getRow()-1);
    }

    public void downInverse()
    {
        this.down.antiClockwiseTurn();
        Colour[] c1 = new Colour[this.front.getRow()];

        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.front.getGrid()[this.front.getRow()-1][i];
        }

        Colour[] c2 = this.left.swapRow(c1, this.left.getRow()-1);
        Colour[] c3 = this.back.swapRow(c2, this.back.getRow()-1);
        c1 = this.right.swapRow(c3, this.right.getRow()-1);
        this.front.swapRow(c1, this.front.getRow()-1);
    }

    public void left()
    {
        this.left.clockwiseTurn();
        Colour[] c1 = new Colour[this.up.getCol()];
        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.up.getGrid()[i][0];
        }

        Colour[] c2 = this.front.swapCol(c1, 0);
        Colour[] c3 = this.down.swapCol(c2, 0);
        c1 = this.back.swapColReverse(c3, this.back.getCol()-1);
        this.up.swapCol(c1, 0);
    }

    public void leftInverse()
    {
        this.left.antiClockwiseTurn();
        Colour[] c1 = new Colour[this.up.getCol()];
        for(int i = c1.length-1; i>=0; i--)
        {
            c1[i] = this.up.getGrid()[i][0];
        }

        Colour[] c2 = this.back.swapCol(c1, 0);
        Colour[] c3 = this.down.swapCol(c2, 0);
        c1 = this.front.swapCol(c3, this.back.getCol()-1);
        this.up.swapCol(c1, 0);
    }

    public void right()
    {
        this.right.clockwiseTurn();
        Colour[] c1 = new Colour[this.up.getCol()];
        for(int i = c1.length-1; i>=0; i--)
        {
            c1[i] = this.up.getGrid()[i][this.up.getCol()-1];
        }

        Colour[] c2 = this.back.swapCol(c1, 0);
        Colour[] c3 = this.down.swapColReverse(c2, this.down.getCol()-1);
        c1 = this.front.swapCol(c3, this.front.getCol()-1);
        this.up.swapCol(c1, this.up.getCol()-1);
    }

    public void rightInverse()
    {
        this.right.antiClockwiseTurn();
        Colour[] c1 = new Colour[this.up.getCol()];
        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.up.getGrid()[i][this.up.getCol()-1];
        }

        Colour[] c2 = this.front.swapCol(c1, this.front.getCol()-1);
        Colour[] c3 = this.down.swapCol(c2, this.down.getCol()-1);
        c1 = this.back.swapCol(c3, 0);
        this.up.swapCol(c1, this.up.getCol()-1);
    }

    public void back()
    {
        this.back.clockwiseTurn();
        Colour[] c1 = new Colour[this.up.getRow()];

        for(int i = 0; i<c1.length; i++)
        {
            c1[i] = this.up.getGrid()[0][i];
        }

        Colour[] c2 = this.left.swapCol(c1, 0);
        Colour[] c3 = this.down.swapRowReverse(c2, this.down.getRow()-1);
        c1 = this.right.swapCol(c3, this.left.getCol()-1);
        this.up.swapRow(c1, 0);
    }

    public void backInverse()
    {
        this.back.antiClockwiseTurn();
        Colour[] c1 = new Colour[this.up.getRow()];

        for(int i = c1.length-1; i>=0; i--)
        {
            c1[i] = this.up.getGrid()[0][i];
        }

        Colour[] c2 = this.right.swapCol(c1, this.left.getCol()-1);
        Colour[] c3 = this.down.swapRow(c2, this.down.getRow()-1);
        c1 = this.left.swapColReverse(c3, 0);
        this.up.swapRow(c1, this.up.getRow()-1);
    }

    public void XTurn()
    {
        Face save = this.front;
        this.front = this.down;
        this.down = this.back;
        this.back = this.up;
        this.up = save;
        this.right.clockwiseTurn();
        this.left.antiClockwiseTurn();
    }

    public void XInverseTurn()
    {
        Face save = this.front;
        this.front = this.up;
        this.up = this.back;
        this.back = this.down;
        this.down = save;
        this.right.clockwiseTurn();
        this.left.antiClockwiseTurn();  
    }

    public void YTurn()
    {
        Face save = this.front;
        this.front = this.right;
        this.right = this.back;
        this.back = this.left;
        this.left = save;
        this.up.clockwiseTurn();
        this.down.antiClockwiseTurn(); 
    }

    public void YInverseTurn()
    {
        Face save = this.front;
        this.front = this.left;
        this.left = this.back;
        this.back = this.right;
        this.right = save;
        this.up.antiClockwiseTurn();
        this.down.clockwiseTurn();  
    }

    public void ZTurn()
    {
        Face save = this.up;
        this.up = this.left;
        this.left = this.down;
        this.down = this.right;
        this.right = save;
        this.front.clockwiseTurn();
        this.back.antiClockwiseTurn(); 
    }


    public void ZInverseTurn()
    {
        Face save = this.up;
        this.up = this.right;
        this.right = this.down;
        this.down = this.left;
        this.left = save;
        this.front.antiClockwiseTurn();
        this.back.clockwiseTurn();  
    }

    public void readCommand(String com)
    {
        switch(com){
            case "F": 
            this.front();
            break;
            
            case "B": 
            this.back();
            break;    
                
            case "R": 
            this.right();
            break;
            
            case "L": 
            this.left();
            break;
                
            case "U": 
            this.up();
            break;
            
            case "D": 
            this.down();
            break;
                
            case "X": 
            this.XTurn();
            break;
                
            case "Y": 
            this.YTurn();
            break;
                
            case "Z": 
            this.ZTurn();
            break; 
                            
            case "Fi": 
            this.frontInverse();
            break;
            
            case "Bi": 
            this.backInverse();
            break;    
                
            case "Ri": 
            this.rightInverse();
            break;
            
            case "Li": 
            this.leftInverse();
            break;
                
            case "Ui": 
            this.upInverse();
            break;
            
            case "Di": 
            this.downInverse();
            break;
                
            case "Xi": 
            this.XInverseTurn();
            break;
                
            case "Yi": 
            this.YInverseTurn();
            break;
                
            case "Zi": 
            this.ZInverseTurn();
            break;   
                
            case "F2": 
            this.front();
            this.front();
            break;
            
            case "B2": 
            this.back();
            this.back();
            break;    
                
            case "R2": 
            this.right();
            this.right();
            break;
            
            case "L2": 
            this.left();
            this.left();
            break;
                
            case "U2": 
            this.up();
            this.up();
            break;
            
            case "D2": 
            this.down();
            this.down();
            break;    
       
        }  
    }

    public void shuffle()
    {
        String[] commands = {"F", "B", "U", "D", "R", "L", "Fi", "Bi", "Ui", "Di", "Ri", "Li"};
        String newCommand;
        
        String[] shuffle = new String[3];
        int shuffleIndex = 0;
     
        for(int i = 0; i<100; i++) {
            do {
              newCommand = commands[(int)(Math.random()*commands.length)];
            } while (newCommand.equals(shuffle[0]) || newCommand.equals(shuffle[1]) || newCommand.equals(shuffle[2]));
            
            shuffle[shuffleIndex] = newCommand;
            shuffleIndex = (shuffleIndex++)%shuffle.length;
            this.readCommand(newCommand);
        }
        this.state = State.SHUFFLED;
    }


    public String toString()
    {
        return "FRONT: " + this.front.toString() + 
               "\n\nBACK: " + this.back.toString() + 
               "\n\nRIGHT: " + this.right.toString() + 
               "\n\nLEFT: " + this.left.toString() + 
               "\n\nUP: " + this.up.toString() + 
               "\n\nDOWN: " + this.down.toString();
    }
}
