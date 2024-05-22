package AI_Assignment;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class EightPuzzle_A_Star {
    
    public static int calculateheuristic(int[][] grid,int[][] goal)
    {
        int count=0;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(goal[i][j]!=grid[i][j])// misplaced tile
                count++;
            }
        }
        return count;
    }
    public static String State_to_String(int[][] grid)// convert grid into a string to store in visited set
    {
        String ans="";
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            ans+=grid[i][j];
        }
        return ans;
    }
    public static boolean isGoalState(int[][] grid,int[][] goal)
    {
        boolean flag = true;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(grid[i][j]!=goal[i][j])
                return false;
            }
        }
        return flag;
    }
    public static void printPath(State s)
    {
        Stack<State> stack = new Stack<>();
        while(s!=null)
        {
            stack.push(s);
            s = s.parent;
        }
        while(!stack.isEmpty())
        {
            State k = stack.pop();// pop every state and print its grid
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    System.out.print(k.grid[i][j]+" , ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    public static void A_Star(PriorityQueue<State> pq,HashSet<String> visited,int[][] start,int sr,int sc,int[][] goal)// sr and sc are of start state
    {
        State initial = new State(start, sr,sc, calculateheuristic(start, goal),"none",0,null);// start state does not have parent
        pq.add(initial);
        while(!pq.isEmpty())
        {
            State current = pq.remove();//pop the most priority state
            if(visited.contains(State_to_String(current.grid)))
            continue;
            else{
                visited.add(State_to_String(current.grid));// added in the set
                //check for goal state
                if(isGoalState(current.grid,goal))
                {
                    System.out.println("Goal State reached");
                    printPath(current);
                    break;
                }
                // if not goal state, start moving tile in all directions
                System.out.println("Current grid ");
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    System.out.print(current.grid[i][j]+", ");
                    System.out.println();
                }
                //move right
                if((!current.last_move.equalsIgnoreCase("left")) && current.sc!=2)//moving right if last move is not right and we are not in the last column
                {
                    //same row, move one column ahead to move right
                    int temp = current.grid[current.sr][current.sc+1];//the value which is about to be exchanged with empty tile
                    current.grid[current.sr][current.sc+1] = -1;
                    current.grid[current.sr][current.sc] = temp;

                    int new_grid[][] = new int[3][3];// copy this change in a new grid otherwise it'll point to the same grid
                    for(int i=0;i<3;i++)
                    {
                        for(int j=0;j<3;j++)
                        new_grid[i][j] = current.grid[i][j];
                    }
                    
                    State moveRight = new State(new_grid, current.sr, current.sc+1, calculateheuristic(new_grid, goal), "right", current.depth+1, current); 
                    
                    if(!visited.contains(State_to_String(new_grid)))// if state has not been visited
                    pq.add(moveRight);//add the new state in priority queue
                    
                    //backtrack the change we made to original grid
                    temp = current.grid[current.sr][current.sc];
                    current.grid[current.sr][current.sc] = -1;// empty tile back at its original place
                    current.grid[current.sr][current.sc+1] = temp;
                }

                //move left
                if((!current.last_move.equalsIgnoreCase("right")) && current.sc!=0)//moving left if last move was not right and we are not in first column
                {
                    //same row, one column to the left(minus)
                    int temp = current.grid[current.sr][current.sc-1];//the value which is about to be exchanged with empty tile
                    current.grid[current.sr][current.sc-1] = -1;
                    current.grid[current.sr][current.sc] = temp;

                    int new_grid[][] = new int[3][3];// copy this change in a new grid otherwise it'll point to the same grid
                    for(int i=0;i<3;i++)
                    {
                        for(int j=0;j<3;j++)
                        new_grid[i][j] = current.grid[i][j];
                    }

                    State moveLeft = new State(new_grid, current.sr, current.sc-1, calculateheuristic(new_grid, goal), "right", current.depth+1, current);
                    if(!visited.contains(State_to_String(new_grid)))
                    pq.add(moveLeft);

                    //backtrack the change we made to original grid
                    temp = current.grid[current.sr][current.sc];
                    current.grid[current.sr][current.sc] = -1;// empty tile back at its original place
                    current.grid[current.sr][current.sc-1] = temp;
                }

                //move up
                if((!current.last_move.equalsIgnoreCase("down")) && current.sr!=0)// moving up if last move was not down and tile is not in start row
                {
                    //same column, upper row(minus row by 1)
                    int temp = current.grid[current.sr-1][current.sc];//the value which is about to be exchanged with empty tile
                    current.grid[current.sr-1][current.sc] = -1;
                    current.grid[current.sr][current.sc] = temp;

                    int new_grid[][] = new int[3][3];// copy this change in a new grid otherwise it'll point to the same grid
                    for(int i=0;i<3;i++)
                    {
                        for(int j=0;j<3;j++)
                        new_grid[i][j] = current.grid[i][j];
                    }
                    State moveUp = new State(new_grid, current.sr-1, current.sc, calculateheuristic(new_grid, goal), "up", current.depth+1, current);
                    if(!visited.contains(State_to_String(new_grid)))
                    pq.add(moveUp);

                    //backtrack the change we made to original grid
                    temp = current.grid[current.sr][current.sc];
                    current.grid[current.sr][current.sc] = -1;// empty tile back at its original place
                    current.grid[current.sr-1][current.sc] = temp;
                }
                //move down
                if((!current.last_move.equalsIgnoreCase("up")) && current.sr!=2)
                {
                    //same column, one row below(add one row)
                    int temp = current.grid[current.sr+1][current.sc];//the value which is about to be exchanged with empty tile
                    current.grid[current.sr+1][current.sc] = -1;
                    current.grid[current.sr][current.sc] = temp;

                    int new_grid[][] = new int[3][3];// copy this change in a new grid otherwise it'll point to the same grid
                    for(int i=0;i<3;i++)
                    {
                        for(int j=0;j<3;j++)
                        new_grid[i][j] = current.grid[i][j];
                    }

                    State moveDown = new State(new_grid, current.sr+1, current.sc, calculateheuristic(new_grid, goal), "down", current.depth+1, current);
                    if(!visited.contains(State_to_String(new_grid)))
                    pq.add(moveDown);
                    
                    //backtrack the change we made to original grid
                    temp = current.grid[current.sr][current.sc];
                    current.grid[current.sr][current.sc] = -1;// empty tile back at its original place
                    current.grid[current.sr+1][current.sc] = temp;

                }
            }
        }
    }

    public static void main(String[] args)
    {
        PriorityQueue<State> pq = new PriorityQueue<>();
        HashSet<String> visited = new HashSet<>();
        int[][] goal = {
            {1, 2, 3},
			{8, -1, 4},
			{7, 6, 5},
                    };
        int [][] start = {
            {2,8,3},
            {1,6,4},
            {7,-1,5}
        };
        A_Star(pq, visited, start, 2, 1, goal);
    }
}
class State implements Comparable<State>// will have override function - compareTo
{
    int[][] grid;
    int sr;//empty tile row
    int sc;//empty tile column
    int h;//heuristic function = misplaced tile+depth
    int depth;
    String last_move;// keeps track of previous move so we dont move in its opposite direction - if prev move is left then we dont go right
    State parent;// for backtracking while printing
    
    State(int[][] grid,int sr,int sc,int h,String last_move,int depth,State parent)
    {
        this.grid= grid;
        this.sr = sr;
        this.sc = sc;
        this.h = h;
        this.depth = depth;
        this.last_move = last_move;
        this.parent = parent;
    }
    @Override
    public int compareTo(State o)
    {
        return (this.h+this.depth - o.h-o.depth);
    }

}