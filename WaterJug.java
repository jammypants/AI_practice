package AI_Assignment;
import java.util.*;
public class WaterJug // using DFS
{
    
     int target;
     int capacity1;
     int capacity2;
    WaterJug(int c1,int c2,int t)
    {
        target = t;
        capacity1 = c1;
        capacity2 = c2;
    }
    static void compute(Stack<Node> stack,boolean[][] visited,int capacity1,int capacity2,int target)
    {
        Node curr = new Node(0, 0);//initial Node
        stack.push(curr);
        visited[curr.i][curr.j] = true;
        while (!stack.empty())
         {
            Node newNode = stack.pop();
            visited[newNode.i][newNode.j]=true;
            //System.out.println(visited);
            System.out.println("i="+newNode.i+" j = "+newNode.j);
            if(newNode.i==target || newNode.j==target)
            {
                if(visited[newNode.i][newNode.j]==false)
                visited[newNode.i][newNode.j] = true;
                break;
            }
            else// push further nodes
            {
                //fill jug1
                System.out.println("Pushing further nodes");
                if(newNode.i<capacity1)
                {
                    Node n = new Node(capacity1, newNode.j);
                    if(visited[n.i][n.j]==false)
                    {
                        System.out.println("Filling jug1");
                        stack.push(n);
                        //visited.add(n);
                    }
                }
                //fill jug2
                if(newNode.j<capacity2)
                {
                    Node n = new Node(newNode.i, capacity2);
                    if(visited[n.i][n.j]==false)
                    {
                        stack.push(n);
                        //visited.add(n);
                    }
                }
                //empty jug1
                    Node n = new Node(0, newNode.j);
                    if(visited[n.i][n.j]==false)
                    {
                        stack.push(n);
                        //visited.add(n);
                    }
                    //empty jug2
                    Node emp = new Node(newNode.i, 0);
                    if(visited[emp.i][emp.j]==false)
                    {
                        stack.push(emp);
                        //visited.add(emp);
                    }
                    //transfer water from jug1 to jug2
                    if(newNode.j!=capacity2)
                    {
                        int diff = capacity2-newNode.j;//how much of jug2 is empty
                        if(newNode.i<=diff)
                        {
                            Node trans = new Node(0, newNode.j+newNode.i);
                            if(visited[trans.i][trans.j]==false)
                            {
                                stack.push(trans);
                            }
                        }
                        else
                        {
                            Node trans = new Node(newNode.i-diff,newNode.j+diff );
                            if(visited[trans.i][trans.j]==false)
                            stack.push(trans);
                        }
                    }
                    // transfer jug2 to jug1
                    if(newNode.i!=capacity1)
                    {
                        int diff = capacity1-newNode.i;//how much of jug2 is empty
                        if(newNode.j<=diff)
                        {
                            Node trans = new Node(newNode.i+newNode.j,0);
                            if(visited[trans.i][trans.j]==false)
                            {
                                stack.push(trans);
                            }
                        }
                        else
                        {
                            Node trans = new Node(newNode.i+diff,newNode.j-diff );
                            if(visited[trans.i][trans.j]==false)
                            stack.push(trans);
                        }
                    }
            }
            
        }
    }
    public static void main(String[] args)
    {
        WaterJug w = new WaterJug(5, 7, 6);
        boolean[][] visited= new boolean[6][8];
    Stack<Node> stack = new Stack<>();
        w.compute(stack,visited, 5, 7, 6);
        System.out.println(visited);
    }
}
class Node
{
    int i;
    int j;
    Node(int i,int j)
    {
        this.i = i;
        this.j = j;
    }
}
