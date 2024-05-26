package Tic_Tac;
import java.util.*;
class Tic_Tac_Toe_NonAI
{
    static int[][] board=new int[3][3];
    static int turn;
    Tic_Tac_Toe_NonAI()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            board[i][j] = 2;
        }
        turn=0;
    }
    public void display(int[][] board)
    {
        System.out.println("Turn ="+turn);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board[i][j]==3)
                System.out.print("X"+" ");
                else if(board[i][j]==5)
                System.out.print("O"+" ");
                else
                System.out.print("- ");
            }
            
            System.out.println();
        }
    }
    public void Go(int row,int column,int turn,int[][] board)// n = position, turn = indicates turn odd = X,even = O
    {
        System.out.println("placing at row = "+row+" and column = "+column);
        if(board[row][column]==2)
        {
            if(turn%2==0)
            board[row][column]=5;
            else
            board[row][column] = 3;
            display(board);
        }
        else
        System.out.println("Error! position is not empty");
    }
    public void Make2(int[][] board,int turn)
    {
        if(board[1][1]==2)
        Go(1,1,turn,board);
        else if(board[0][1]==2)
        Go(0,1,turn,board);
        else if(board[1][0]==2)
        Go(1,0,turn,board);
        else if(board[1][2]==2)
        Go(1,2, turn, board);
        else
        Go(2, 1, turn, board);
    }
    int[] PossWin(int[][] board,int turn)// returns 0 if player cant win in next move else return the winning move square
    {// row and column need to be initialized with the blank column
        int[] array = new int[3];
        int row=-1;
        int column=-1;
        if(turn%2!=0)// X's turn
        {
            int flag = 0;
            if(flag!=1)
            {
            for(int i=0;i<3;i++)// for all rows
            {
                int prod = 1;
                for(int j=0;j<3;j++)
                {
                    if(board[i][j]==2)// !!!!!!!!!! fails case of 3,2,3 row and column is -1,not anymore
                    {
                        row=i;
                        column=j;
                    }
                    prod = prod*board[i][j];
                }
                if(prod==18)
                {
                    flag =1;
                    break;
                }
                else
                {
                    row= -1;
                    column = -1;
                }
        
            }}
            if(flag!=1)
            {
            for(int i=0;i<3;i++)// for all columns, i and j reversed, i is column and j is row
            {
                int prod = 1;
                for(int j=0;j<3;j++)
                {
                    if(board[j][i]==2)
                    {
                        row=j;
                        column=i;
                    }
                    prod = prod*board[j][i];
                }
                if(prod==18)
                {
                    flag =1;
                    break;
                }
                else
                {
                    row= -1;
                    column = -1;
                }
            }}
            if(flag!=1)
            {
                int prod=1;
            for(int i=0;i<3;i++)// left diagnol, i and j are same
            {
                for(int j=i;j<=i;j++)
                {
                if(board[i][j]==2)
                    {
                        row=i;
                        column=j;
                    }
                prod = prod*board[i][i];
                }
                
            }
            if(prod==18)
                {
                    flag =1;
                }
                else
                {
                    row= -1;
                    column = -1;
                }
        }
            if(flag!=1)// checking right diagnol
            {
            int prod = 1;
            int j=2;
            for(int i=0;i<3;i++)// right diagnol
            {
                if(board[i][j]==2)
                    {
                        row=i;
                        column=j;
                    }
                
                prod = prod*board[i][j];
                j--;
            }
            if(prod==18)
                {
                    flag=1;
                }
                else
                {
                    row= -1;
                    column = -1;
                }
            }
            if(flag==1 && row!=-1 && column!=-1)// final checking
            {
                //board[row][column] = 3;//place the element
                int arr[] = {flag,row,column};
                return arr;
            }
            else
            {
                int arr[] = {flag,row,column};
                return arr;
            }
        }
        else// checking winning possibility of O
        {
            int flag = 0;
            if(flag!=1)
            {
            for(int i=0;i<3;i++)// for all rows
            {
                int prod = 1;
                for(int j=0;j<3;j++)
                {
                    prod = prod*board[i][j];
                    if(board[i][j]==2)
                    {
                        row=i;
                        column=j;
                    }
                }
                if(prod==50)
                {
                    flag =1;
                    break;
                }
                else
                {
                    row= -1;
                    column = -1;
                }
            }}
            if(flag!=1)
            {
            for(int i=0;i<3;i++)// for all columns
            {
                int prod = 1;
                for(int j=0;j<3;j++)
                {
                    prod = prod*board[j][i];
                    if(board[i][j]==2)
                    {
                        row=i;
                        column=j;
                    }
                }

                if(prod==50)
                {
                    flag =1;
                    break;
                }
                else
                {
                    row= -1;
                    column = -1;
                }
            }}
            if(flag!=1)
            {int prod=1;
            for(int i=0;i<3;i++)// left diagnol, i and j are same
            {
                for(int j=i;j<=i;j++)
                {
                if(board[i][j]==2)
                    {
                        row=i;
                        column=j;
                    }
                prod = prod*board[i][i];
                }
                
            }
            if(prod==50)
                {
                    flag =1;
                }
                else
                {
                    row= -1;
                    column = -1;
                }
        }
            if(flag!=1)// checking right diagnol
            {
            int prod = 1;
            int j=2;
            for(int i=0;i<3;i++)// right diagnol
            {
                if(board[i][j]==2)
                    {
                        row=i;
                        column=j;
                    }
                //if(j>=0)
                prod = prod*board[i][j];
                j--;
            }
            if(prod==50)
                {
                    flag=1;
                }
                else
                {
                    row= -1;
                    column = -1;
                }
            }
            if(flag==1 && row!=-1 && column!=-1)// final checking
            {
                //board[row][column] = 5;     !!!!!!!!!!!!!!!! not placing the element just checking it possibility
                int arr[] = {flag,row,column};
                return arr;
            }
            else
            {
                int[] arr={flag,row,column};
                return arr;
            }
        }
    }
    public static void main(String[] Args)
    {
        Tic_Tac_Toe_NonAI ob = new Tic_Tac_Toe_NonAI();
        ob.display(board);
        Scanner sc = new Scanner(System.in);
        for(turn = 1;turn<=9;turn++)
        {
            if(turn%2!=0)// X's turn
            {
                if(board[0][0]==2)
                ob.Go(0, 0, turn, board);
                else if(board[2][2]==2)
                ob.Go(2, 2, turn, board);   
                else
                {
                    int arr[] = ob.PossWin(board,turn);// X ki win possibility
                    int arr1[] = ob.PossWin(board, turn+1);// else check O's, we are just checking it not placing it there
                    if(arr[0]==1)
                    {
                        ob.Go(arr[1], arr[2], turn, board);
                        //ob.display(board);
                        System.out.println(" X has won ");
                        System.out.println("Row = "+arr[1]+" Column = "+arr[2]);
                        break;
                    }
                    else if(arr1[0]==1)
                    {
                        ob.Go(arr1[1], arr1[2], turn, board);
                        System.out.println("Row = "+arr[1]+" Column = "+arr[2]);
                        //ob.display(board);
                    }
                    else if(board[0][2]==2)
                    ob.Go(0, 2, turn, board);
                    else
                    ob.Go(2, 0, turn, board);
                }
            }
            else
            {
                System.out.println("O's Turn enter row and column ");
                int row = sc.nextInt();
                int column  =sc.nextInt();
                int arr[] = ob.PossWin(board,turn);
                if(arr[0]==1 && arr[1]==row && arr[2]==column)
                {
                    ob.Go(row, column, turn, board);
                    System.out.println("O has won");
                    break;
                }
                else
                ob.Go(row, column, turn, board);
            }
        }
        if(turn==9)
        System.out.println("Its a draw");
    }
}
