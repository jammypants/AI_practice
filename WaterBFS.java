package AI_Assignment;
/**
 * WaterBFS
 */
import java.util.*;
public class WaterBFS {
    public void compute(Queue<Node> q,boolean[][] visited,int capacity1,int capacity2,int target)
    {
        Node curr = new Node(0, 0);//initial Node
        q.add(curr);
        visited[curr.i][curr.j] = true;
        while (!q.isEmpty())
         {
            Node newNode = q.remove();
            visited[newNode.i][newNode.j]=true;
            //System.out.println(visited);
            System.out.println("i="+newNode.i+" j = "+newNode.j);
            if(newNode.i==target)
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
                        q.add(n);
                        //visited.add(n);
                    }
                }
                //fill jug2
                if(newNode.j<capacity2)
                {
                    Node n = new Node(newNode.i, capacity2);
                    if(visited[n.i][n.j]==false)
                    {
                        q.add(n);
                        //visited.add(n);
                    }
                }
                //empty jug1
                    Node n = new Node(0, newNode.j);
                    if(visited[n.i][n.j]==false)
                    {
                        q.add(n);
                        //visited.add(n);
                    }
                    //empty jug2
                    Node emp = new Node(newNode.i, 0);
                    if(visited[emp.i][emp.j]==false)
                    {
                        q.add(emp);
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
                                    q.add(trans);
                            }
                        }
                        else
                        {
                            Node trans = new Node(newNode.i-diff,newNode.j+diff );
                            if(visited[trans.i][trans.j]==false)
                            q.add(trans);
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
                                q.add(trans);
                            }
                        }
                        else
                        {
                            Node trans = new Node(newNode.i+diff,newNode.j-diff );
                            if(visited[trans.i][trans.j]==false)
                            q.add(trans);
                        }
                    }
            }
            
        }
    }
    public static void main(String[] args)
    {
        Queue<Node> q = new LinkedList<>();//queue interface implemented by LinkedList class
        boolean visited[][] = new boolean[5][5];
        WaterBFS w = new WaterBFS();
        w.compute(q,visited, 4, 3, 2);
    }
}
class Node{
    int i;
    int j;
    Node(int x,int y)
    {
        this.i = x;
        this.j = y;
    }
}