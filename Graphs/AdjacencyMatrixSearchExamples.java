import java.util.*;
import sh.*;

public class AdjacencyMatrixSearchExamples
{
   public static void main(String[] args)
   {
      boolean[][] graph = createGraph();
       
      //For starterNode E (index 4), should print: E D B A C F
      dfs(graph, 4);
      
      System.out.println();
      
      //For starterNode E (index 4), should print: E D F B C A
      bfs(graph, 4);
   }
   
   /**
      Performs a depth-first search on the graph, starting with the parameter node
      
      @param startNode the node from which to start your search
   */
   public static void dfs(boolean[][] graph, int startIndex)
   {
      dfsHelper(graph, startIndex, new HashSet<Integer>());
   }
   
   private static void dfsHelper(boolean[][] graph, int currentIndex, Set<Integer> visited)
   {
   /**
      Creates a specific graph for testing purposes:
      
      A - B
      |   |
      C - D - E
          \  /
           F
   */
   /**
            A      B     C     D      E      F
       A {false, true, true, false, false, false},
       B {true, false, false, true, false, false},
       C {true, false, false, true, false, false},
       D {false, false, true, false, true, true},
       E {false, false, false, true, false, true},
       F {false, false, false, true, true, false}
       
       S E D C A D
       
       E D C A B F
       
       boolean[][] graph, int currentIndex, Set<Integer> visited
   */
   
      boolean[] connections = graph[currentIndex];
      
      sh.print(indexToLetter(currentIndex) + " ");
      visited.add(currentIndex);
   
      for (int i = 0; i < connections.length; i++) {
         
         if (connections[i] && !visited.contains(i)) {
         
            visited.add(i);
            dfsHelper(graph, i, visited);
         
         }
      
      }
      
   }
   
   public static void bfs(boolean[][] graph, int startIndex)
   {
      /*
         Your code here! The algorithm is the same as when you were using the Adjacency List.        
      */
   }
   
   
   
   /**
      Creates a specific graph for testing purposes:
      
      A - B
      |   |
      C - D - E
          \  /
           F
   */
   public static boolean[][] createGraph()
   {
      //For simplicity, let's have index 0="A", 1="B", etc.
      boolean[][] graph = new boolean[][] 
                          { /* A     B     C      D      E     F    */
                      /*A*/ {false, true, true, false, false, false},
                      /*B*/ {true, false, false, true, false, false},
                      /*C*/ {true, false, false, true, false, false},
                      /*D*/ {false, false, true, false, true, true},
                      /*E*/ {false, false, false, true, false, true},
                      /*F*/ {false, false, false, true, true, false}
                          };
      return graph;
   }
   
   private static int letterToIndex(char letter)
   {
      return letter - 'A';
   }
   
   private static char indexToLetter(int index)
   {
      return (char)(index + 'A');
   }
}