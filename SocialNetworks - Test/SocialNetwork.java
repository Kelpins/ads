import sh.*;
import java.io.*;
import java.util.*;

public class SocialNetwork {

   private static final String FILE_NAME = "large_social_network.txt";

   public static void main (String[] args) {
      
      Map<String, GraphNode<String>> names = downloadFile(FILE_NAME);
      
      if (names == null) {
      
         return;
      
      }
      
      String name;
      ArrayList<GraphNode<String>> friends;
      ArrayList<GraphNode<String>> friendsOfFriends;
      
      boolean keepSearching = true;
      Scanner input = new Scanner(System.in);
            
      while (keepSearching) {
         
         name = userInput(input, names);
         
         if (name == null) {
         
            break;
         
         }
         
         friends = getFriends(names.get(name));
         friendsOfFriends = getFriendsOfFriends(names.get(name));
         
         printFriends(friends, friendsOfFriends);
         
         keepSearching = searchAgain(input);
      
      }
   
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
      
         sh.p("File not found");
         return null;
      
      }
      catch (NullPointerException e) {
      
         sh.p("Invalid file format");
         return null;
      
      }
      
      return connections;
   
   }
   
   private static String userInput(Scanner in, Map<String, GraphNode<String>> names) {
      
      sh.print("Who do you want to search for? ");
      String name = in.nextLine();
      
      if (!names.containsKey(name)) {
      
         sh.p("Name not found in graph.");
         
         if (searchAgain(in)) {
         
            return userInput(in, names);
         
         }
         
         return null;
      
      }
      
      return name;
      
   }
   
   private static boolean searchAgain(Scanner in) {
   
      sh.print("Do you want to search again? ");
      String cont = in.nextLine();
      if (cont.charAt(0) == 'Y' || cont.charAt(0) == 'y') {
      
         return true;
      
      }
      
      return false;
   
   }
   
   private static void printFriends(ArrayList<GraphNode<String>> friends, ArrayList<GraphNode<String>> friendsOfFriends) {
   
      if (friends.size() == 0) {
      
         sh.p("No Friends");
      
      }
      else {
      
         sh.p("Friends (" + friends.size() + "): " + friends);
         
         if (friendsOfFriends.size() == 0) {
      
            sh.p("No friends of friends");
         
         }
         else {
         
            sh.p("Friends of Friends (" + friendsOfFriends.size() + "): " + friendsOfFriends);
         
         }
      
      }
   
   }
   
   private static ArrayList<GraphNode<String>> getFriendsOfFriends(GraphNode<String> startNode) {
            
      ArrayList<GraphNode<String>> allFriendsOfFriends = new ArrayList<>();
      ArrayList<GraphNode<String>> allFriends = getFriends(startNode);
      
      for (GraphNode<String> friend : allFriends) {
      
         ArrayList<GraphNode<String>> friendsOfFriend = getFriends(friend);
      
         for (GraphNode<String> friendOfFriend : friendsOfFriend) {
                  
            if (!allFriendsOfFriends.contains(friendOfFriend) && !allFriends.contains(friendOfFriend) && !friendOfFriend.getData().equals(startNode.getData())) {
            
               allFriendsOfFriends.add(friendOfFriend);
            
            }
         
         }
      
      }
      
      return allFriendsOfFriends;
   
   }
   
   public static ArrayList<GraphNode<String>> getFriends(GraphNode<String> startNode) {
   
      return startNode.getConnections();
   
   }

}