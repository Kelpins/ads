import java.util.*;
import java.awt.*;

/**
* GoggleMapsRunner with Weighted Graphs
* @author Kellan Williams, Applications of Data Structures
*/
public class GoggleMapsRunner {

   /**
   * Creates a UI for users to determine the most efficient path from point A to point B.
   * Users are able to input any two bus stops, and the program will use Dijkstra's
   * algorithm to determine the fastest path from the first bus stop to the second.
   * @param args console input
   */
   public static void main(String[] args) {
      
      //Initializes the GoggleMap
      GoggleMaps map = new GoggleMaps();
      
      //Gets a list of all bus stops
      ArrayList<WeightedGraphNode> stops = map.getAllBusStops();
      
      //Prompts the user for two bus stops
      ArrayList<WeightedGraphNode> userInput = getInput(map);
      WeightedGraphNode stop1 = userInput.get(0);
      WeightedGraphNode stop2 = userInput.get(1);
      
      //Determines the best path from stop1 to stop2 using Dijkstra's algorithm
      HashMap<WeightedGraphNode, WeightedGraphNode> bestPath = dijkstra(stops, stop1, stop2);
      
      //Parses the results of Dijkstra's algorithm
      ArrayList<WeightedGraphNode> parsedPath = parsePath(bestPath, stop1, stop2);
      
      //Draws the path on the GoggleMap
      drawPath(map, parsedPath, stop1, stop2);
                
   }
   
   /**
   * Using the path determined via Dijkstra's, this method draws
   * the most optimal path between two user-inputted bus stops.
   * @param map - the GoggleMaps object we are displaying
   * @param path - a list of all nodes in the most optimal path
   * @param start - the first user-inputted bus stop
   * @param end - the second user-inputted bus stop
   */
   private static void drawPath(GoggleMaps map, ArrayList<WeightedGraphNode> path,
                                WeightedGraphNode start, WeightedGraphNode end) {
      
      //Starting at the end of the path,
      for (int i = 0; i < path.size()-1; i++) {
                  
         //Turn a bus stop red
         map.drawBusStop(path.get(i), Color.RED);
         
         //Turn the path between said bus stop and next bus stop red
         map.drawConnection(path.get(i), path.get(i+1), Color.RED);
         
         //Move onto next bus stop :D
      
      }
      
      /*
      * Because the parsed path actually finds the route in reverse,
      * the above for loop is missing the start rather than the end.
      */
      map.drawBusStop(start, Color.RED);
   
   }
   
   /**
   * The raw output of Dijkstra's algorithm is a HashMap mapping nodes to each other in
   * the optimal route, so this method parses that output into a simple ArrayList.
   * @param bestPath - a HashMap containing the raw results from Dijkstra's algorithm,
   *                   representing the most optimal path from stop 1 to stop 2
   * @param start - the first user-inputted bus stop
   * @param end - the second user-inputted bus stop
   * @return a list of all nodes in the most optimal path
   */
   private static ArrayList<WeightedGraphNode> parsePath(HashMap<WeightedGraphNode,
                                                         WeightedGraphNode> bestPath,
                                                         WeightedGraphNode start,
                                                         WeightedGraphNode end) {
      
      //Create an empty ArrayList to store the path
      ArrayList<WeightedGraphNode> path = new ArrayList<>();
      
      /*
      * Must start at the end because the bestPath HashMap maps each bus stop to the previous one,
      * so we can only access the bus stop that is one prior in the path sequence.
      */
      path.add(end);
      
      //Repeat until the first bus stop has entered the path (meaning our sequence is complete):
      while (!path.contains(start)) {
         
         //Get the node on furthest into the path ArrayList (closest to the starting bus stop)
         WeightedGraphNode topNode = path.get(path.size()-1);
         
         //Get the node connected to it from Dijkstra's bestPath, which is the preceding bus stop
         WeightedGraphNode nextStep = bestPath.get(topNode);
         
         //Add that bus stop to the path
         path.add(nextStep);
      
      }
            
      return path;
   
   }
   
   /**
   * Implementation of Dijkstra's algorithm. This method determines the
   * best and most efficient path between two user-inputted bus stops.
   * @param allStops - all stops in our GoggleMap
   * @param start - the first user-inputted bus stop
   * @param end - the second user-inputted bus stop
   */
   private static HashMap<WeightedGraphNode, WeightedGraphNode> dijkstra
      (ArrayList<WeightedGraphNode> allStops, WeightedGraphNode start, WeightedGraphNode end) {
      
      //HashMap to keep track of the shortest distance found to each bus stop so far
      HashMap<WeightedGraphNode, Integer> bestDists = new HashMap<>();
      
      //HashMap to keep track of the shortest paths to each bus stop so far
      HashMap<WeightedGraphNode, WeightedGraphNode> bestPaths = new HashMap<>();
      
      //HashSet to keep track of bus stops we have already found the shortest path to
      HashSet<WeightedGraphNode> visited = new HashSet<>();
      
      /*
      * PriorityQueue to keep track of which bus stop we will visit next,
      * which will be the one with the smallest bestDists value
      */
      PriorityQueue<WeightedGraphNode> unexplored =
         new PriorityQueue<>(Comparator.comparingInt(bestDists::get));
      
      //Set the best distance so far to (for all intents and purposes) infinity for all bus stops
      for (WeightedGraphNode stop : allStops) {
      
         bestDists.put(stop, Integer.MAX_VALUE);
      
      }
      
      //Set the best distance for the starting node to 0 and add it to the queue
      bestDists.put(start, 0);
      unexplored.add(start);
      
      /*
      * While we still have bus stops left to explore,
      * and while we haven't already found our target bus stop:
      */
      while (!unexplored.isEmpty() && !visited.contains(end)) {
         
         //Focus on the top of the queue (the bus stop with the smallest bestDists value)
         WeightedGraphNode current = unexplored.poll();
         
         //Get all of its connections
         HashMap<WeightedGraphNode, Integer> connections = current.getConnections();
         Set<WeightedGraphNode> connectionsKeys = connections.keySet();
         
         //For each of said connections,
         for (WeightedGraphNode connection : connectionsKeys) {
            
            //If this bus stop has not already been optimized,
            if (!visited.contains(connection)) {
               
               //Check if the current path to that bus stop is more optimal than the previous one
               if ((bestDists.get(current) + connections.get(connection)) <
                    bestDists.get(connection)) {
                  
                  //If so, then update the bestPath for that bus stop to match the current path
                  bestPaths.put(connection, current);
                  
                  //And update the best distance to that bus stop to the new, smaller distance
                  bestDists.put(connection, bestDists.get(current) + connections.get(connection));
               
               }
               
               //Add this connected node to the queue to be checked soon
               unexplored.add(connection);
            
            }
         
         }
         
         //Add the node we are focusing on to the visited HashSet, and move onto the next one
         visited.add(current);
               
      }
            
      return bestPaths;
   
   }
   
   /**
   * Asks the user to submit two valid bus stops
   * @param map - the GoggleMaps object we are displaying
   * @return a list containing the two user-inputted bus stops
   */
   private static ArrayList<WeightedGraphNode> getInput(GoggleMaps map) {
      
      Scanner input = new Scanner(System.in);
      WeightedGraphNode stop1 = null;
      WeightedGraphNode stop2 = null;
      
      //
      while (stop1 == null) {
      
         System.out.print("Bus stop 1: ");
         /*
         * getBusStopByName returns null if the input is invalid,
         * so the loop will not break until a valid bus stop is entered.
         */
         stop1 = map.getBusStopByName(input.nextLine());
         if (stop1 == null) {
         
            System.out.println("Invalid bus stop - try again!");
         
         }
      
      }
      while (stop2 == null) {
      
         System.out.print("Bus stop 2: ");
         /*
         * getBusStopByName returns null if the input is invalid,
         * so the loop will not break until a valid bus stop is entered.
         */
         stop2 = map.getBusStopByName(input.nextLine());
         if (stop2 == null) {
         
            System.out.println("Invalid bus stop - try again!");
         
         }
      
      }
      
      //Return the two user-inputted bus stops
      ArrayList<WeightedGraphNode> output = new ArrayList<>();
      output.add(stop1);
      output.add(stop2);
      
      return output;
   
   }

}