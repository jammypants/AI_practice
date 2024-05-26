import java.util.*;
// solving water jar problem using breadth first search
public class WaterJarBFS {
  
    
    static void display(JugState s)
    {
      Stack<JugState> stack = new Stack<>();
      while(s!=null)
      {
        stack.push(s);
        s=s.parent;
      }
      while(!stack.isEmpty())
      {
        JugState curr = stack.pop();
        System.out.println(curr.j1+" , "+curr.j2);
      }
    }
    static void bfs(int capacity1,int capacity2,int target,Queue<JugState> q,int[][] visited)
    {
      JugState start = new JugState(0,0,null);
      q.add(start);// appending start state in the Queue
      while(!q.isEmpty())
      {
        JugState current = q.remove();// pop the topmost element
        if(current.j1 == target || current.j2==target)
        {
          System.out.println("Goal state reached ");
          visited[current.j1][current.j2]=1;
          display(current);
          break;
        }
        if(visited[current.j1][current.j2]==1)// state has already been explored
        continue;
        // if state has not been visited nor is it goal then we explore further Possibilities
        
        if(current.parent!=null)
        System.out.println("Currently jug1 "+current.j1+" jug2 = "+current.j2+" coming from parent jug1 ="+current.parent.j1+" jug2 = "+current.parent.j2);
        
        visited[current.j1][current.j2] = 1;// mark current state as visited
        
        // fill jug1 completely
        if(current.j1<capacity1)
        {
          //System.out.println("Filling jug1");
          //int diff = capacity1-current.j1;
          JugState next = new JugState(capacity1,current.j2,current);
          if(visited[next.j1][next.j2]!=1)
          q.add(next);
          //System.out.println("Filled jug1");
        }
        
        // fill jug2 completely
        if(current.j2<capacity2)
        {
          //int diff = capacity2-current.j2;
          JugState next = new JugState(current.j1,capacity2,current);
          if(visited[next.j1][next.j2]!=1)// check if new state has not been explored before
          q.add(next);
          //System.out.println("Filled jug2");
        }
        
        //transfer water from jug1 to jug2
        if(current.j2<capacity2)
        {
          int sum = current.j1+current.j2;
          if(sum<=capacity2)// jug1 only has almost enough or less water to completely fill j2
          {
            JugState next = new JugState(0,current.j1+current.j2,current);
            if(visited[next.j1][next.j2]!=1)
            q.add(next);
          }
          else// jug1 has more water than what is required to fill up jug2
          {
            int diff = sum-capacity2;
            JugState next = new JugState(diff,capacity2,current);
            if(visited[next.j1][next.j2]!=1)
            q.add(next);
          }
        }
        
        //transfer water from jug2 to jug1
        if(current.j1<capacity1)
        {
          int sum = current.j1+current.j2;
          if(sum<=capacity1)// jug2 only has enough water to completely fill j1
          {
            JugState next = new JugState(current.j1+current.j2,0,current);
            if(visited[next.j1][next.j2]!=1)
            q.add(next);
          }
          else// jug2 has more water than what is required to fill up jug1
          {
            int diff = sum-capacity1;
            JugState next = new JugState(capacity1,diff,current);
            if(visited[next.j1][next.j2]!=1)
            q.add(next);
          }
        }
        
        // empty jug1
        JugState newState = new JugState(0,current.j2,current);
        if(visited[newState.j1][newState.j2]!=1)
        q.add(newState);
        
        //empty jug2
        JugState emp2 = new JugState(current.j1,0,current);
        if(visited[emp2.j1][emp2.j2]!=1)
        q.add(emp2);
      }
    }
    public static void main(String[] args)
    {
      int capacity1 = 5;// jug1 is always greater than or equal to jug2
      int capacity2 = 3;
      int target = 4;
      int[][] visited = new int[capacity1+1][capacity2+1];// keep track of which states have been visited
      for(int i=0;i<=capacity1;i++)
      {
        for(int j=0;j<capacity2;j++)
        visited[i][j] = -1;
      }
      Queue<JugState> q = new LinkedList<>();// queue for breadth first search since we are exploring all nodes at a particular depth
      bfs(capacity1,capacity2,target,q,visited);
  }
}
class JugState
{
  int j1;// how much j1 is holding
  int j2;//quantity that j2 is holding
  JugState parent;// for backtracking
  JugState(int j1,int j2,JugState parent)
  {
    this.j1 = j1;
    this.j2 = j2;
    this.parent = parent;
  }
}