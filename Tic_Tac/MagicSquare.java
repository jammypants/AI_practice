// Magic Square, Tic Tac Toe
package AI_Assignment;
import java.util.*;
public class MagicSquare {
    
    static void display(char[][] board)
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            System.out.print(board[i][j]+" ");
            System.out.println();
        }
    }
    static int possWin(char[][] board,char player,int[][] magic_square,List<Integer> moves,HashMap<Integer,Boolean> map)//returns the magic square value to be filled, else returns -1
    {
        //check all possible pairs
        for(int i=0;i<moves.size()-1;i++)
        {
            int sum=0;
            for(int j=i+1;j<moves.size();j++)
            {
                sum = moves.get(i)+moves.get(j);
                int diff = 15-sum;
                System.out.println(diff);
                if((diff<10 && diff>0) && map.get(diff)==false)// place is empty, player can win in next move
                return diff;
            }
        }
        return -1;// if there isn't a possibility of winning
    }


    static void Go(char[][] board,int[][] magic_square,HashMap<Integer,Boolean> map,List<Integer> ai_moves)
    {
        if(board[0][0]=='-')
        {
            board[0][0] = 'O';
            map.put(magic_square[0][0], true);
            ai_moves.add(magic_square[0][0]);
        }
        else if(board[2][0]=='-')
        {
            board[2][0] = 'O';
            map.put(magic_square[2][0], true);
            ai_moves.add(magic_square[2][0]);
        }
        else if(board[0][2]=='-')
        {
            board[0][2] = 'O';
            map.put(magic_square[0][2], true);
            ai_moves.add(magic_square[0][2]);
        }
        else if(board[2][2]=='-')
        {
            board[2][2] = 'O';
            map.put(magic_square[2][2], true);
            ai_moves.add(magic_square[2][2]);
        }
    }
    public static void main(String[] args)
    {
        char[][] board = new char[3][3];
        for(int i=0;i<3;i++)// intializing empty board
        {
            for(int j=0;j<3;j++)
            board[i][j] = '-';
        }
        int[][] magic_square = {
            {8,3,4},
            {1,5,9},
            {6,7,2},
        };
        HashMap<Integer,Boolean> map = new HashMap<>();// true means its occupied and false means its not
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            map.put(magic_square[i][j],false);
        }

        List<Integer> user_moves = new ArrayList<>();
        List<Integer> ai_moves = new ArrayList<>();
        int turn = 1;
        while(turn<=9)
        {
            boolean flag=false;
            System.out.println("Current board ");
            display(board);
            if(turn%2==0)// user's turn
            {
                int required_score = possWin(board, 'X', magic_square, user_moves, map); 
                Scanner sc = new Scanner(System.in);
                System.out.println("You are X, Enter empty row and column ");
                int row = sc.nextInt();
                int column = sc.nextInt();
                if(board[row][column]=='-')
                {
                    board[row][column] = 'X';
                    if(magic_square[row][column]==required_score)
                    {
                        System.out.println("User has won");
                        display(board);
                        break;
                    }
                    user_moves.add(magic_square[row][column]);
                    map.put(magic_square[row][column], true);
                }
                display(board);
                turn++;
            }
            else// computer's move
            {
                int comp_win = possWin(board,'O',magic_square,ai_moves,map);
                int user_win = possWin(board, 'X', magic_square, user_moves, map);
                if(comp_win!=-1)// computer can win
                {
                    for(int i=0;i<3;i++)
                    {
                        for(int j=0;j<3;j++)
                        {
                            if(magic_square[i][j]==comp_win)
                            {
                                board[i][j] = 'O';
                                ai_moves.add(comp_win);
                                map.put(comp_win, true);
                                System.out.println("Computer has won");
                                display(board);
                                flag=true;
                                break;
                            }
                        }
                        if(flag==true)
                        break;
                    }
                    if(flag)
                    {
                        System.out.println("Computer has won");
                        display(board);
                        break;
                    }
                }

                else if(user_win!=-1)// stop user from winning
                {
                    for(int i=0;i<3;i++)
                    {
                        for(int j=0;j<3;j++)
                        {
                            if(magic_square[i][j]==user_win)
                            {
                                board[i][j] = 'O';
                                ai_moves.add(user_win);
                                map.put(user_win, true);
                                //System.out.println("User has won");
                                display(board);
                                
                            }
                        }
                    }
                }
                else
                {
                    Go(board,magic_square,map,ai_moves);// random move
                }
                turn++;
                System.out.println("Computer move played, this is the board now ");
                display(board);
            }
            if(turn==10)
            {
                System.out.println("its a draw");
                display(board);
            }
        }
    }
}
