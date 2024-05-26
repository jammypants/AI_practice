package Tic_Tac;
import java.util.*;
public class Ai {
    char[][] board;
    Ai()
    {
        board = new char[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            board[i][j] = '-';
        }
    }
    static void display(char[][] board)
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
        // rows
        for(int i=0;i<3;i++)
        {
            if(board[i][0]==board[i][1] && board[i][1]==board[i][2] && board[i][0]!='-')
            return (board[i][0]=='X')? 1:-1;
        }
        //columns
        for(int i=0;i<3;i++)
        {
            if(board[0][i]==board[1][i] && board[1][i]==board[2][i] && board[0][i]!='-')
            return (board[0][i]=='X')? 1:-1;
        }
        //left diagnol
        if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0]!='-')
        return (board[0][0]=='X')? 1:-1;
        //right diagnol
        if (board[0][2] != ' ' && board[1][1] == board[0][2] && board[2][0] == board[0][2])
        return (board[0][2] == 'X') ? 1 : -1;
        //check for draw
        boolean draw = true;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board[i][j]=='-')
                {
                    draw=false;
                    break;
                }
            }
        }
        if(draw)
        return 0;
        // game is still on, no one is winning
        return 2;
    }
    static int minimax(char[][] board,int n_moves,char turn)// board represents current board state, depth of the tree and whether its the maximizers turn or minimizers
    {
        int score = checkWin(board);//check if someone has already won
        if(score!=2)// won/lost/draw anyone of these has occurred
        {
            return score*10-score*n_moves;// to return a valid score with respect to the depth of the tree
        }
        if(turn=='X')// maximizer
        {
            int bestScore = Integer.MIN_VALUE;
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    if(board[i][j]=='-')
                    {
                        board[i][j] = 'X';// place X on the empty spot
                        score = minimax(board, n_moves+1,'O');
                        board[i][j] = '-';
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
        else// minimizer
        {
            int bestScore = Integer.MAX_VALUE;
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    if(board[i][j]=='-')
                    {
                        board[i][j] = 'O';// place O on the empty spot
                        score = minimax(board, n_moves+1,'X');
                        board[i][j] = '-';
                        bestScore = Math.min(score,bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
    static void computerMove(char[][] board)
    {
        int bestScore = Integer.MIN_VALUE;
        int bestrow= -1;
        int bestcolumn = -1;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board[i][j]=='-')//empty spot
                {
                    board[i][j] = 'X';// place X on the spot and run minimax
                    int score = minimax(board,0,'O');
                    if(score>bestScore)
                    {
                        bestScore = score;
                        bestrow = i;
                        bestcolumn = j;
                    }
                    board[i][j] = '-';// once we've explored that node we undo it to check further possibilities
                }
            }
        }
        board[bestrow][bestcolumn] = 'X';
    }
    public static void main(String[] Args)
    {
        Ai ob = new Ai();
        display(ob.board);
        int turn=1;
        while(turn<=9)
        {
            if(turn%2!=0)
            {
                computerMove(ob.board);
                System.out.println("Computer move played now user");
                display(ob.board);
                turn++;
            }
            else
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter row and column");
                int row = sc.nextInt();
                int column = sc.nextInt();
                if(ob.board[row][column]=='-')
                {
                    ob.board[row][column] = 'O';
                    turn++;
                }
                else
                {
                    System.out.println("Place is not empty");
                    continue;
                }
            }
            int win = checkWin(ob.board);
            if(win==1)
            {
                display(ob.board);
                System.out.println("AI wins!");
                break;
            }
            if(win==-1)
            {
                display(ob.board);
                System.out.println("User wins!");
                break;
            }
            if(turn==9)
            {
                if(win==0)
            {
                display(ob.board);
                System.out.println("Its a draw");
                break;
            }
            }
            
        }
    }
}
