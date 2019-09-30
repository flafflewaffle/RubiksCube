/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rubik;

import java.util.Arrays;

/**
 *
 * @author sophiasingh
 */
public class Face {
    
    private int numRow;
    private int numCol;
    private Colour colour;
    private Colour[][] grid;

    
    public Face(Colour c, int row, int col)
    {
        this.numRow = row;
        this.numCol = col;
        this.colour = c;
        this.grid = new Colour[row][col];

        for(int i = 0; i<row; i++)
        {
            for(int j = 0; j<col; j++)
            {
                this.grid[i][j] = c;
            }
        }
    }
    
    public int getNumRow()
    {
        return this.numRow;
    }
    
    public int getNumCol()
    {
        return this.numCol;
    }
    
    public Colour[][] getGrid()
    {
        return this.grid;
    }
    
    public Colour getColour()
    {
        return this.colour;
    }

    public void clockwiseTurn()
    {
        int r;
        int c;
        
        if(grid.length%2 == 0)
        {
            r = grid.length/2;
            c = grid.length/2;
        }
        else
        {
            r = grid.length/2 + 1;
            c = grid.length/2 + 1;
        }
          
        for(int i = 0; i<r; i++)
        {
            for(int j = 0; (j+i)<c; j++)
            {
                if(i == grid.length/2 && (j+i) == grid.length/2)
                {
                    break;
                }
                grid = fourSwapClockwise(grid, i, (j+i));
            }
        }
    }
    
    public void antiClockwiseTurn()
    {
        int r;
        int c;
        
        if(grid.length%2 == 0)
        {
            r = grid.length/2;
            c = grid.length/2;
        }
        else
        {
            r = grid.length/2 + 1;
            c = grid.length/2 + 1;
        }
          
        for(int i = 0; i<r; i++)
        {
            for(int j = 0; (j+i)<c; j++)
            {
                grid = fourSwapAntiClockwise(grid, i, (j+i));
            }
        }
    }
    
    public Colour[][] fourSwapClockwise(Colour[][] matrix, int a1, int a2)
    {
        //00, 05, 55, 50
        //01, 15, 54, 40
        //04, 45, 51, 10
        //11, 14, 44, 41
        //03, 35, 53, 30
        //13, 34, 43, 31
        
        //01 12 21 10
        
        Colour save = matrix[a1][a2];
        int row = matrix.length-1;
        
        int d1;
        int d2;
        
        int c1;
        int c2;
        
        int b1; 
        int b2;
        
        if(a2 == matrix.length/2)
        {
            d1 = a2;
            d2 = a1;

            c1 = row;
            c2 = a2;

            b1 = a2; 
            b2 = row;
        }
        else
        {
            d1 = a2+(row-a1-a2);
            d2 = a1;

            c1 = a2+(row-a1-a2);
            c2 = a1+(row-a1-a2);

            b1 = a2; 
            b2 = a2+(row-a1-a2);
        }
        
        matrix[a1][a2] = matrix[d1][d2];
        matrix[d1][d2] = matrix[c1][c2];
        matrix[c1][c2] = matrix[b1][b2];
        matrix[b1][b2] = save;

        return matrix;
    }
    
    public Colour[][] fourSwapAntiClockwise(Colour[][] matrix, int a1, int a2)
    {
        //00, 05, 55, 50
        //01, 15, 54, 40
        //04, 45, 51, 10
        //11, 14, 44, 41
        //03, 35, 53, 30
        //13, 34, 43, 31
        
        Colour save = matrix[a1][a2];
        int row = matrix.length-1;
        int col = matrix[0].length-1;
        
        int d1;
        int d2;
        
        int c1;
        int c2;
        
        int b1; 
        int b2;
        
        if(a2 == matrix.length/2)
        {
            d1 = a2;
            d2 = a1;

            c1 = row;
            c2 = a2;

            b1 = a2; 
            b2 = row;
        }
        else
        {
            d1 = a2+(row-a1-a2);
            d2 = a1;

            c1 = a2+(row-a1-a2);
            c2 = a1+(row-a1-a2);

            b1 = a2; 
            b2 = a2+(row-a1-a2);
        }
        
        matrix[a1][a2] = matrix[b1][b2];
        matrix[b1][b2] = matrix[c1][c2];
        matrix[c1][c2] = matrix[d1][d2];
        matrix[d1][d2] = save;

        return matrix;
    }
    
    public Colour[] swapRow(Colour[] row, int r)
    {
        Colour[] toReturn = new Colour[row.length];
        
        for(int i = 0; i<row.length; i++)
        {
            toReturn[i] = grid[r][i];
            grid[r][i] = row[i];
        }
        
        return toReturn;
    }
    
    public Colour[] swapRowReverse(Colour[] row, int r)
    {
        Colour[] toReturn = new Colour[row.length];
        
        for(int i = row.length-1; i>=0; i--)
        {
            toReturn[i] = grid[r][i];
            grid[r][i] = row[i];
        }
        
        return toReturn;
    }
    
    public Colour[] swapCol(Colour[] col, int c)
    {
        Colour[] toReturn = new Colour[col.length];
        
        for(int i = col.length-1; i>=0; i--)
        {
            toReturn[i] = grid[i][c];
            grid[i][c] = col[i];
        }
            
        return toReturn;
    }
    
    public Colour[] swapColReverse(Colour[] col, int c)
    {
        Colour[] toReturn = new Colour[col.length];
        
        for(int i = 0; i<col.length; i++)
        {
            toReturn[i] = grid[i][c];
            grid[i][c] = col[i];
        }
            
        return toReturn;
    }
    
    public boolean scanCenter() {
        if (this.numCol < 3 && this.numRow < 3){
            return true;
        }
        
        for(int r = 1; r < this.numRow-1; r++) 
        {
            for(int c = 1; c < this.numCol-1; r++) 
            { 
                if(this.colour != this.grid[r][c]) 
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean scanRowEdge(Colour check, int row) {
        if (this.numCol < 3 && this.numRow < 3){
            return true;
        }
        
        for(int c = 1; c < this.numCol-1; c++)
        {
            if(check != this.grid[row][c]) 
            { 
                return false;
            }
        }
        return true;
    }
    
    public boolean scanColEdge(Colour check, int col) {
        if (this.numCol < 3 && this.numRow < 3){
            return true;
        }
        
        for(int r = 1; r < this.numRow-1; r++)
        {
            if(check != this.grid[r][col]) 
            { 
                return false;
            }
        }
        return true;
    }
    
    public boolean scanLeftEdgeOriented() {
        return this.scanColEdge(this.colour, 0);
    }
    
    public boolean scanRightEdgeOriented() {
        return this.scanColEdge(this.colour, this.numCol-1);
    }
    
    public boolean scanTopEdgeOriented() {
        return this.scanRowEdge(this.colour, 0);
    }
    
    public boolean scanBottomEdgeOriented() {
        return this.scanRowEdge(this.colour, this.numRow-1);
    }
    
    public boolean scanL() {
        return this.scanCenter() && this.scanRowEdge(this.colour, 0) && this.scanColEdge(this.colour, 0);
    }
    
    public boolean scanHorizontalLine() {
        return this.scanCenter() && this.scanColEdge(this.colour, 0) && this.scanColEdge(this.colour, this.numCol-1);
    }
    
    public boolean scanVerticalLine() {
        return this.scanCenter() && this.scanRowEdge(this.colour, 0) && this.scanRowEdge(this.colour, this.numRow-1);
    }
    
    public boolean scanCross() {
        return this.scanVerticalLine() && this.scanHorizontalLine();
    }
    
    public String toString()
    {
        return this.colour.toString() + "\n\n" + Arrays.deepToString(grid);
    }
}
