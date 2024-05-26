package AI_Assignment;
import java.util.*;
public class Tic_Tac_AI {
    
    public static void display(char[][] board)
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            System.out.print(board[i][j]+" ");
            System.out.println();
        }
    }

    static int checkWin(char[][] board)
    {
        //rows
        for(int i=0;i<3;i++)
        {
            if(board[i][0]==board[i][1] && board[i][1]==board[i][2] && board[i][0]!='-')// any row's all three columns are checked
            {
                if(board[i][0]=='O')//computer win - maximizer
                return 1;
                else// user has won
                return -1;
            }
        }
        //columns
        for(int j=0;j<3;j++)
        {
            if(board[0][j]==board[1][j] && board[1][j]==board[2][j] && board[0][j]!='-')
            {
                if(board[0][j]=='O')
                return 1;
                else
                return -1;
            }
        }
        //left diagonal
        if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0]!='-')
        {
            if(board[0][0]=='O')// computer is winning
            return 1;
            else
            return -1;
        }
        //right diagonal
        if(board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[1][1]!='-')
        {
            if(board[0][2]=='O')
            return 1;
            else
            return -1;
        }
        
        //draw
        boolean draw = true;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board[i][j]=='-')// any empty space indicates draw hasn't occurred
                {
                    draw = false;
                    break;
                }
            }
        }
        if(draw)// if draw remains true then return zero
        return 0;

        // no result, game is still going on return 2
        return 2;
    }
    public static int minimax(char[][] board,int depth,char turn)
    {
        int score = checkWin(board);
        if(score!=2)// either win,draw or loss has occurred and game is over
        return score*10-score*depth;// return the score with respect to depth
        else
        {
            if(turn=='O')// computer maximizer
            {
                int bestScore = Integer.MIN_VALUE;
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        if(board[i][j]=='-')
                    {
                        board[i][j] = 'O';
                        int val = minimax(board, depth+1,'X');// recursively call for X now
                        board[i][j] = '-';
                        bestScore = Math.max(val, bestScore);
                    }
                    }
                }
                return bestScore;// returns best score to computer move function
            }
            else // minimizer turn, assume user plays optimally and return the most minimum value
            {
                int bestScore = Integer.MAX_VALUE;// we have to store minimum value so we initialize it with the highest possible integer value
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        if(board[i][j]=='-')
                        {
                            board[i][j] = 'X';
                            int min = minimax(board, depth+1, 'O');// recursively call for the next move to calculate
                            board[i][j] = '-';// backtrack to examine more moves
                            bestScore = Math.min(min, bestScore);
                        }
                    }
                }
                return bestScore;
            }
        }
    }
    public static void computermove(char[][] board)// computer playing as O, as maximizer
    {
        int bestScore = Integer.MIN_VALUE;// store the highest score
        int bestrow = -1;// store the indices where the highest column is achieved
        int bestcolumn = -1;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board[i][j]=='-')// if a place is empty
                {
                    board[i][j] = 'O';// place the position
                    int score = minimax(board,0,'X');//call minimax, send current board, depth of recursion/moves and next turn
                    board[i][j] = '-';// reverse the move since we need to check for other places as well.
                    if(score>=bestScore)
                    {
                        bestScore = score;
                        bestrow = i;
                        bestcolumn = j;
                    }
                }
            }
        }
        board[bestrow][bestcolumn] = 'O';
    }
    public static void main(String[] args)
    {
        char[][]  board = new char[3][3];// create a board
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            board[i][j] = '-';//initializing board as empty
        }

        Scanner sc = new Scanner(System.in);
        int turn = 1;// first turn will be made by computer,O
        display(board);
        while(turn<=9)
        {
            System.out.println("Current state of board");
            display(board);
            System.out.println();
            if(turn%2==0)
            {
                System.out.println("PLease Enter an empty row and column move");
                int row = sc.nextInt();
                int column = sc.nextInt();
                if(board[row][column]!='-')
                continue;
                if(board[row][column]=='-')
                {
                    board[row][column] = 'X';// user is X
                    System.out.println("Placed at "+row+" row and "+column+" column");
                    display(board);
                    System.out.println();
                    turn++;//increment turn
                }
            }
            else// computer move
            {
                computermove(board);
                System.out.println("Computer move played this is the board now");
                display(board);
                System.out.println();
                turn++;
            }
            int score = checkWin(board);
            if(score==1)
            {
                System.out.println("AI has won");
                display(board);
                break;
            }
            else if(score==-1)
            {
                System.out.println("User has won");
                display(board);
                break;
            }
            else if(score==0)
            {
                System.out.println("Its a draw");
                display(board);
                break;
            }
        }
    }
}
