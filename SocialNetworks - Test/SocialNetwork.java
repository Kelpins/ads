import sh.*;
import java.io.*;
import java.util.*;

public class SocialNetwork {

   private static final String FILE_NAME = "small_social_network.txt";

   public static void main (String[] args) {
      
      Map<String, GraphNode<String>> names = downloadFile(FILE_NAME);
      Scanner in = new Scanner(System.in);
      sh.print("Who do you want to search for? ");
      String name = in.nextLine();
      ArrayList<GraphNode<String>> friends = getFriends(names.get(name));
      ArrayList<GraphNode<String>> fofs = getFriendsOfFriends(names.get(name));
      sh.p("Friends (" + friends.size() + "): " + friends);
      sh.p("Friends of Friends (" + fofs.size() + "): " + fofs);        
   
   }
   
   public static ArrayList<GraphNode<String>> getFriendsOfFriends(GraphNode<String> startNode) {
   
      ArrayList<GraphNode<String>> fofs = new ArrayList<>();
      
      for (GraphNode<String> friend : getFriends(startNode)) {
         
         friendsOfFriend = getFriends(friend));
         for (GraphNode<String> friendOfFriend : 
      
      }
      
      return fofs;
   
   }
   
   public static ArrayList<GraphNode<String>> getFriends(GraphNode<String> startNode) {
   
      return startNode.getConnections();
   
   }
   
   private static Map<String, GraphNode<String>> downloadFile(String fileName) {
      
      Map<String, GraphNode<String>> connections = new HashMap<>();
      File network = new File(fileName);
      String friend1 = null;
      
      try {
      
         Scanner scan = new Scanner(network);
         Scanner scanLineOne = new Scanner("" + scan.nextLine());
         while (scanLineOne.hasNext()) {
         
            String name = scanLineOne.next();
            connections.put(name, new GraphNode<String>(name));
         
         }
         
         while (scan.hasNext()) {
            
            if (friend1 == null) {
            
               friend1 = scan.next();
            
            }
            else {
               
               String friend2 = scan.next();
               
               connections.get(friend1).addConnection(connections.get(friend2));
               connections.get(friend2).addConnection(connections.get(friend1));
               friend1 = null;
            
            }
         
         }
      
      }
      catch (FileNotFoundException e) {
      
         System.out.println("file does not exist lmao");
      
      }
      
      return connections;
   
   }

}