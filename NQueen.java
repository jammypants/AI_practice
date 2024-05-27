// N Queens
package AI_Assignment;
import java.util.*;
public class NQueen {
    char[][] board = new char[4][4];
    NQueen()
    {
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            board[i][j] = 'B';// mark entire board empty/blank
        }
    }
    Pair validMove(int row,int column)//check for a valid move in that column
    {
        boolean flag = false;
        for(int i=0;i<4;i++)
        {
            if(board[i][column]=='B')//check all rows in that column
            {
                System.out.println("Valid move found at "+i+" column "+column);
                Pair p = new Pair(i,column);
                flag = true;
                return p;
            }
        }
        Pair p = new Pair(-1,-1);//no valid move found returning -1
        return p;
    }
    boolean check(int x,int y,int row,int column)
    {
        return true;
    }
    void attack(int x,int y,int row,int column,char ch)// we mark all the places under threat with 'A', x and y are position where we are placing,
    {// row and column are max bounds of our board
        for(int i=0;i<column;i++)// entire column is marked
        {
            if(board[i][y]!='Q')//skip the queen
            board[i][y] = ch;
        }
        for(int i=0;i<column;i++)// marks the entire row
        {
            if(board[x][i]!='Q')
            board[x][i] = ch;
        }
        //left diagonal
        int tx = x;// temp variables
        int ty = y;
        while(tx>=0 && ty>=0)//left upper diagonal
        {
            if(board[tx][ty]!='Q')
            board[tx][ty]= ch;
            tx--;
            ty--;
        }
        tx = x;
        ty = y;
        while(tx<row && ty<column)//left bottom diagonal
        {
            if(board[tx][ty]!='Q')
            board[tx][ty] = ch;
            tx++;
            ty++;
        }
        //right diagonal - alternate increment and decrement - plus column minus row and vice versa
        tx = x;
        ty = y;
        while(tx<row && ty>=0)//bottom half of the right diagonal
        {
            if(board[tx][ty]!='Q')
            board[tx][ty] = ch;
            tx++;
            ty--;
        }
        tx = x;
        ty = y;
        while(tx>=0 && ty<column)//upper half of the right diagonal
        {
            if(board[tx][ty]!='Q')
            board[tx][ty] = ch;
            tx--;
            ty++;
        }
        
    }
    void display(char[][] board)
    {
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            System.out.print(board[i][j]+" ");
            System.out.println();
        }
    }
    boolean place(int i,int column,int row)//column represents which queen we are placing and in which column, we need to determine which row
    {
        display(board);
        if(column>=4)// we have placed all now we can return
        return true;
        for(int j=0;j<row;j++)
        {
            Pair pos = validMove(j, column);// find valid move in that column
            if(pos.x!=-1 && pos.y!=-1)//valid move found
            {
                //Queen q = new Queen(pos.x,pos.y);//creating new queen to store which all places it affects
                board[pos.x][pos.y] = 'Q';//place the queen
                display(board);
                attack(j,column,4,4,'A');//mark all the places
                display(board);
                boolean flag = place(0, column+1, row);//recursive call for the next queen
                if(flag==true)
                return true;
                else// this position does not yield ideal result,check other positions in the same column
                {
                    attack(j, column, 4, 4, 'B');//make those affected positions normal
                    board[pos.x][pos.y] = 'B';
                    continue;
                }
            }
            else// no valid move found,so backtrack
            return false;
        }
        return false;
    }
    public static void main(String[] args)
    {
        NQueen ob = new NQueen();
        System.out.println("N queen problem");
        ob.place(0, 0, 4);
    }
    
}
class Pair
{
    int x;
    int y;
    Pair(int x,int y)
    {
        this.x = x;
        this.y= y;
    }
}
class Queen// coordinate of each queen and the list of integers they attack
{
    int x;
    int y;
    List<Integer> threat = new ArrayList<>();
    Queen(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
}
