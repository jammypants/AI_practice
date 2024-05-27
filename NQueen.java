// N Queens
package CSP;
import java.util.*;
class NQueen {
    boolean isSafe(int row,int column,int n,char[][] board)// if its safe at that index
    {
        // no need to check column because we are placing one column at a time
        for(int i=0;i<n;i++)// checking row
        {
            if(board[row][i]=='Q')
            return false;
        }
        // since we are going left to right we only need to see left upper diagonal and right bottom diagonal to see if any queen has been previously placed
        int tx = row;
        int ty = column;
        while(tx>=0 && ty>=0)//left upper diagonal
        {
            if(board[tx][ty]=='Q')
            return false;
            tx--;
            ty--;
        }
        tx = row;
        ty = column;
        while(tx<n && ty>=0)//bottom half of the right diagonal
        {
            if(board[tx][ty]=='Q')
            return false;
            tx++;
            ty--;
        }
        return true;
    }
    void display(char[][] board,int n)
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            System.out.print(board[i][j]+" ");
            System.out.println();
        }
    }
    void addSolution(int n,char[][] board,List<List<String>> ans)
    {
        List<String> list = new ArrayList<>();
        for(int i=0;i<n;i++)
            {
                String fin="";
                for(int j=0;j<n;j++)
                fin +=board[i][j];
                //System.out.println(fin);
                list.add(fin);
            }
            ans.add(list);
    }
    void solve(int column,int n,List<List<String>> ans,List<String> list,char[][] board)
    {
        if(column>=n)// we have placed all 4 queens,BASE CASE
        {
            display(board,n);
            addSolution(n,board,ans);
            return;
        }
        for(int row=0;row<n;row++)// we check all rows in the column we have to place
        {
            if(isSafe(row,column,n,board))
            {
                board[row][column] = 'Q';//place the queen and solve
                solve(column+1,n,ans,list,board);
                board[row][column] = '.';// backtrack
            }
        }
    }
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];// creating our initial board
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            board[i][j] = '.';
        }
        List<List<String>> ans = new ArrayList<>();
        List<String> list = new ArrayList<>();
        solve(0,n,ans,list,board);
        return ans;
    }
    public static void main(String Args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of queens ");
        int n = sc.nextInt();
        NQueen ob = new NQueen();
        System.out.println(ob.solveNQueens(n));
    }
}
