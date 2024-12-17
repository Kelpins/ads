import java.io.*;
import java.util.*;

/**
* SocialNetwork with Graphs
* @author Kellan Williams, Applications of Data Structures
*/
public class SocialNetwork {
   
   /*
   * To switch to directional, input "directional_social_network.txt" into the FILE_NAME 
   * and change the DIRECTIONAL class variable to true.
   */
   /** Name of file containing the social network */
   private static final String FILE_NAME = "large_social_network.txt";
   /** Whether or not the social network is directional */
   private static final boolean DIRECTIONAL = false;
   
   /**
   * Creates a UI for users to interact with a social network, read only
   * After downloading information from a social network file,
   * Users are able to input any name, and see a list of that person's friends,
   * As well as a list of friends of each of those friends.
   * @param args console input
   */
   public static void main (String[] args) {
      
      //Download the social network
      Map<String, GraphNode<String>> names = downloadFile(FILE_NAME);
      
      /*
      * Sincerest apologies for the one-line if statement, it looked gross otherwise
      * (I say as I add a three-line comment to compensate)
      * This catches both exceptions where the program is supposed to end (invalid or absent file)
      */
      if (names == null) return;
      
      //Defining variables :D
      boolean keepSearching = true;
      Scanner input = new Scanner(System.in);
      
      //Code will run until the user replies "no" when prompted whether they would like to continue
      while (keepSearching) {
         
         //Prompts the user to input a name
         String name = userInput(input, names);
         
         /*
         * Again, I apologize for the one-line if statement (still looks better, though)
         * This catches the weird scenario where a user inputs a name that doesn't exist,
         * then asks the program to end. Normally, the userInput function repeats until the
         * user submits a valid name, but if they never do, then the program needs to stop here.
         */     
         if (name == null) return;
         
         // Get the friends and the friends of friends
         ArrayList<GraphNode<String>> friends = getFriends(names.get(name));
         ArrayList<GraphNode<String>> friendsOfFriends = getFriendsOfFriends(names.get(name));
         
         // Print the friends and friends of friends
         printFriends(friends, friendsOfFriends);
         
         //Prompt the user whether they would like to keep searching
         keepSearching = searchAgain(input);
      
      }
   
   }
   /**
   * Reads the first line of the given file, generating an AdjacencyList with no connections
   * Then uses every following line to draw connections between each person
   * @param fileName - the name of the file to read from
   * @return an AdjacencyList (a HashMap of Strings to their associated GraphNodes)
   *         containing each person and each connection.
   *         Returns null if file is invalid or absent.
   */
   private static Map<String, GraphNode<String>> downloadFile(String fileName) {
      
      File network = new File(fileName);
      String friend1 = null;
      
      //Initialize the AdjacencyList
      Map<String, GraphNode<String>> connections = new HashMap<>();
      
      //Try to read the file & set up connections
      try {
         
         //Okay this is really janky but I also love it:
         
         //First, set up a scanner on the whole network file
         Scanner scan = new Scanner(network);
         
         //Read the first line, and set up a scanner on that String (containing all names)
         Scanner scanLineOne = new Scanner("" + scan.nextLine());
         
         /*
         * Using the second scanner, go token-by-token (name-by-name),
         * setting up the AdjacencyList without any connections in it
         */
         while (scanLineOne.hasNext()) {
         
            String name = scanLineOne.next();
            connections.put(name, new GraphNode<String>(name));
         
         }
         
         //Finally, loop through the remainder of the network file with the original scanner
         while (scan.hasNext()) {
            
            //If the first friend doesn't have a name, then the current name is the first friend
            if (friend1 == null) {
            
               friend1 = scan.next();
            
            }
            //Otherwise, the current name is the second friend and we need to create a connection
            else {
               
               String friend2 = scan.next();
               
               connections.get(friend1).addConnection(connections.get(friend2));
               
               //Sorry for the one-line if statement
               if (!DIRECTIONAL) connections.get(friend2).addConnection(connections.get(friend1));
               
               friend1 = null;
            
            }
         
         }
      
      }
      catch (FileNotFoundException e) {
      
         System.out.println("File not found");
         return null;
      
      }
      catch (NullPointerException e) {
      
         System.out.println("Invalid file format");
         return null;
      
      }
      
      return connections;
   
   }
   
   /**
   * Prompts the user for a name until they provide a valid name or they quit
   * @param in - a Scanner passed through so we don't create a new one each time we ask for input
   * @param names - an AdjacencyList containing the entire network and connections
   * @return a String containing the name (or null if the user decides to end the program)
   */
   private static String userInput(Scanner in, Map<String, GraphNode<String>> names) {
      
      System.out.print("Who do you want to search for? ");
      String name = in.nextLine();
      
      if (!names.containsKey(name)) {
      
         System.out.println("Name not found in graph.");
         
         //Slight bit of recursion; this will run until the user provides a valid name or gives up
         if (searchAgain(in)) {
         
            return userInput(in, names);
         
         }
         
         return null;
      
      }
      
      return name;
      
   }
   
   /**
   * Prompts the user whether they want to search again
   * @param in - a Scanner passed through so we don't create a new one each time we ask for input
   * @return a boolean showing whether the user wants to go again
   */
   private static boolean searchAgain(Scanner in) {
   
      System.out.print("Do you want to search again? ");
      String cont = in.nextLine();
      //Apologies for the one-line if-statement; if they don't put anything then still end program
      if (cont.equals("")) return false;
      if (cont.charAt(0) == 'Y' || cont.charAt(0) == 'y') {
      
         return true;
      
      }
      
      return false;
   
   }
   
   /**
   * Prints friends, then friends of friends
   * @param friends - an ArrayList of GraphNodes, each representing a friend of the inputted person
   * @param friendsOfFriends - an ArrayList of GraphNodes, each representing a friend of a friend
   */
   private static void printFriends(ArrayList<GraphNode<String>> friends,
                                    ArrayList<GraphNode<String>> friendsOfFriends) {
      
      if (friends.size() == 0) {
      
         System.out.println("No friends");
      
      }
      else {
      
         System.out.println("Friends (" + friends.size() + "): " + friends);
         
         if (friendsOfFriends.size() == 0) {
      
            System.out.println("No friends of friends");
         
         }
         else {
         
            System.out.println(
               "Friends of friends (" + friendsOfFriends.size() + "): " + friendsOfFriends
            );
         
         }
      
      }
   
   }
   
   /**
   * Generates a list of friends for a given person
   * @param startNode - a node representing the person for whom we will find friends
   * @return an ArrayList of GraphNodes representing each friend
   */
   public static ArrayList<GraphNode<String>> getFriends(GraphNode<String> startNode) {
   
      return startNode.getConnections();
   
   }
   
   /**
   * Generates a list of friends of friends for a given person
   * @param startNode - a node representing the person for whom we will find friends of friends
   * @return an ArrayList of GraphNodes representing each friend of a friend
   */
   private static ArrayList<GraphNode<String>> getFriendsOfFriends(GraphNode<String> startNode) {
      
      /*
      * This would technically be better as a HashSet (would reduce Big O significantly)
      * But alas it was not in the cards for me (lol my procedural decomposition requires
      * that I pass ArrayLists through a bunch of different places, and it is far more work than
      * it is worth to change every instance of "friends of friends" to a HashSet while leaving
      * every instance of "friends" as an ArrayList).
      */
      ArrayList<GraphNode<String>> allFriendsOfFriends = new ArrayList<>();
      
      //Start by getting a list of all friends of the individual
      ArrayList<GraphNode<String>> allFriends = getFriends(startNode);
      
      //For each friend in that list,
      for (GraphNode<String> friend : allFriends) {
         
         //Get an ArrayList of that individual's friends (using the getFriends function)
         ArrayList<GraphNode<String>> friendsOfFriend = getFriends(friend);
         
         //For each Friend of a Friend in the list found above,
         for (GraphNode<String> friendOfFriend : friendsOfFriend) {
            
            //Add them to the list if they:
            if (
                  //Aren't already in the list of friends of friends
                  !allFriendsOfFriends.contains(friendOfFriend) &&
                  //Aren't also a direct friend
                  !allFriends.contains(friendOfFriend) &&
                  //Aren't the original person whose friends of friends we are searching for
                  !friendOfFriend.getData().equals(startNode.getData())
               ) {
            
               allFriendsOfFriends.add(friendOfFriend);
            
            }
         
         }
      
      }
      
      return allFriendsOfFriends;
   
   }

}