import java.util.*;
import sh.*;

public class AdjacencyListSearchExamples
{
   public static void main(String[] args)
   {
      GraphNode<String> starterNode = createGraph();
       
      //For starterNode E, should print: E D B A C F
      dfsTraversal(starterNode);
      
      System.out.println();
      
      //For starterNode E, should print: E D F B C A
      bfsTraversal(starterNode);
      
   }
   
   /**
      Performs a depth-first search on the graph, starting with the parameter node
      
      @param startNode the node from which to start your search
   */
   public static void dfsTraversal(GraphNode<String> startNode)
   {
      dfsHelper(startNode, new HashSet<GraphNode<String>>());
   }
   
   private static void dfsHelper(GraphNode<String> currentNode, Set<GraphNode<String>> visited)
   {
   
      ArrayList<GraphNode<String>> connections = currentNode.getConnections();
      
      sh.print(currentNode + " ");
      visited.add(currentNode);
   
      for (int i = 0; i < connections.size(); i++) {
      
         GraphNode<String> connection = connections.get(i);
         if (!visited.contains(connection)) {
            
            dfsHelper(connection, visited);
         
         }
      
      }
      
   }
   
   /**
      Performs a breadth-first search on the graph, starting with the parameter node
      
      @param startNode the node from which to start your search
   */
   public static void bfsTraversal(GraphNode<String> startNode)
   {
      
      Queue<GraphNode<String>> q = new LinkedList<>();
      q.add(startNode);
      
      HashSet<GraphNode<String>> visited = new HashSet<>();
      visited.add(startNode);
      
      while (!q.isEmpty()) {
      
         ArrayList<GraphNode<String>> connections = q.element().getConnections();
         
         for (int i = 0; i < connections.size(); i++) {
      
            GraphNode<String> connection = connections.get(i);
            if (!visited.contains(connection)) {
               
               q.add(connection);
               visited.add(connection);
            
            }
         
         }
         
         sh.print(q.element() + " ");
         q.poll();
      
      }
      
      /*
         Your code here! This will be a similar algorithm to your tree BFS traversal.
         The main difference is that in addition to the Queue of nodes you need to search,
         you will also need a Set to keep track of the nodes you have already visited.
         Also, instead of searching for a particular thing, we will just print each node 
         when we remove it from the Queue.               
      */
   }
   
   
   
   /**
      Creates a specific graph for testing purposes:
      
      A - B
      |   |
      C - D - E
          \  /
           F
     
      Connections are in alphabetical order (for example, A's connections are [B,C], not [C,B])
      
      @return one of the GraphNodes in this Graph
   */
   public static GraphNode<String> createGraph()
   {
      GraphNode<String> nodeA = new GraphNode<>("A");
      GraphNode<String> nodeB = new GraphNode<>("B");
      GraphNode<String> nodeC = new GraphNode<>("C");
      GraphNode<String> nodeD = new GraphNode<>("D");
      GraphNode<String> nodeE = new GraphNode<>("E");
      GraphNode<String> nodeF = new GraphNode<>("F");
      connectNodePair(nodeA, nodeB);
      connectNodePair(nodeA, nodeC);
      connectNodePair(nodeB, nodeD);
      connectNodePair(nodeC, nodeD);
      connectNodePair(nodeE, nodeD);
      connectNodePair(nodeD, nodeF);
      connectNodePair(nodeF, nodeE);
      
      return nodeE;
   }
   
   /**
      Connects node1 to node2, and node2 to node1, to create a 2-way connection
      
      @param node1 the first node to connect
      @param node2 the second node to connect
   */
   public static void connectNodePair(GraphNode<String> node1, GraphNode<String> node2)
   {
      node1.addConnection(node2);
      node2.addConnection(node1);
   }
}