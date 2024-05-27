// Crypt Arithmetic
package CSP;
import java.util.*;
// Basically for every unique character, assign it a value which has not been assigned previously,
// once all letters have been assigned check if the addition holds true, if yes print, then back track
// unassign the number and continue the loop
public class crypt {

    static int convertNum(String s,HashMap<Character,Integer> map)// convert the string values into integer
    {
        String num = "";
        for(int i=0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            num = num+map.get(ch);
        }
        return Integer.parseInt(num);
    }
    static void CAP(String s1,String s2,String result,HashMap<Character,Integer> map,HashSet<Integer> set,String unique,int index)// index indicates traversal
    {
        if(index==unique.length())// traversed entire unique string now check if assigned values match
        {
            int n1 = convertNum(s1,map);
            int n2 = convertNum(s2, map);
            int n3 = convertNum(result, map);
            if((n1+n2)==n3)// simply print the map if you want to show all possible conditions, can remove the if conditions below
            {
                if(result.length()>s1.length() && result.length()>s2.length() && map.get(result.charAt(0))!=0)
				System.out.println(map);
				if(result.length()==s1.length() && result.length()==s2.length())
				System.out.println(map);
            }
            return;// one solution found return
        }
        //for each unique character try all values between 0-9 which give correct answer
        char ch = unique.charAt(index);
        for(int i=0;i<=9;i++)
        {
            if(!set.contains(i))
            {
                map.put(ch, i);// assigned a value to this character and store in the hashmap
                set.add(i);//marked as visited
                CAP(s1, s2, result, map, set, unique, index+1);// recursively call for next index
                map.put(ch, -1);// backtrack to find solutions with different values
                set.remove(i);// remove the value from visited set
            }
        }
    }
    public static void main(String[] args)
    {
        String s1="BASE";
        String s2="BALL";
        String result="GAMES";

        HashMap<Character,Integer> map = new HashMap<>();// keep track all unique letters and their digits
        HashSet<Integer> set = new HashSet<>();// to keep track of used digits
        String unique = "";// string of unique characters to be traversed
        for(int i=0;i<s1.length();i++)
        {
            if(!map.containsKey(s1.charAt(i)))
            {
                map.put(s1.charAt(i), -1);
                unique+=s1.charAt(i);
            }
        }
        for(int i=0;i<s2.length();i++)
        {
            if(!map.containsKey(s2.charAt(i)))
            {
                map.put(s2.charAt(i), -1);
                unique+=s2.charAt(i);
            }
        }
        for(int i=0;i<result.length();i++)
        {
            if(!map.containsKey(result.charAt(i)))
            {
                map.put(result.charAt(i), -1);
                unique+=result.charAt(i);
            }
        }
        CAP(s1,s2,result,map,set,unique,0);
    }
}
