import sh.*;
import java.util.*;
import java.io.*;
import java.awt.*;

public class GoggleMapsRunner {

   public static void main(String[] args) {
   
      GoggleMaps map = new GoggleMaps();
      ArrayList<WeightedGraphNode> stops = map.getAllBusStops();
      
      ArrayList<WeightedGraphNode> userInput = getInput(map);
      WeightedGraphNode stop1 = userInput.get(0);
      WeightedGraphNode stop2 = userInput.get(1);
                  
      HashMap<WeightedGraphNode, WeightedGraphNode> bestPath = dijkstra(stops, stop1, stop2);
      ArrayList<WeightedGraphNode> parsedPath = parsePath(bestPath, stop1, stop2);
      
      drawPath(map, parsedPath, stop1, stop2);
                
   }
   
   private static void drawPath(GoggleMaps map, ArrayList<WeightedGraphNode> path, WeightedGraphNode start, WeightedGraphNode end) {
   
      for (int i = 0; i < path.size()-1; i++) {
      
         map.drawBusStop(path.get(i), Color.RED);
         map.drawConnection(path.get(i), path.get(i+1), Color.RED);
      
      }
      
      map.drawBusStop(start, Color.RED);
      map.drawBusStop(end, Color.RED);
   
   }
   
   private static ArrayList<WeightedGraphNode> getInput(GoggleMaps map) {
   
      Scanner input = new Scanner(System.in);
      WeightedGraphNode stop1 = null;
      WeightedGraphNode stop2 = null;
      
      while (stop1 == null) {
      
         System.out.print("Bus stop 1: ");
         stop1 = map.getBusStopByName(input.nextLine());
         if (stop1 == null) {
         
            System.out.println("Invalid bus stop - try again!");
         
         }
      
      }
      while (stop2 == null) {
      
         System.out.print("Bus stop 2: ");
         stop2 = map.getBusStopByName(input.nextLine());
         if (stop2 == null) {
         
            System.out.println("Invalid bus stop - try again!");
         
         }
      
      }
      
      ArrayList<WeightedGraphNode> output = new ArrayList<>();
      output.add(stop1);
      output.add(stop2);
      
      return output;
   
   }
      
   private static ArrayList<WeightedGraphNode> parsePath(HashMap<WeightedGraphNode, WeightedGraphNode> bestPath, WeightedGraphNode start, WeightedGraphNode end) {
   
      ArrayList<WeightedGraphNode> path = new ArrayList<>();
      path.add(end);
            
      while (!path.contains(start)) {
         
         WeightedGraphNode topNode = path.get(path.size()-1);         
         WeightedGraphNode nextStep = bestPath.get(topNode);
         path.add(nextStep);
      
      }
            
      return path;
   
   }
   
   private static HashMap<WeightedGraphNode, WeightedGraphNode> dijkstra (ArrayList<WeightedGraphNode> allStops, WeightedGraphNode start, WeightedGraphNode end) {
      
      HashMap<WeightedGraphNode, Integer> bestDists = new HashMap<>();
      HashMap<WeightedGraphNode, WeightedGraphNode> bestPaths = new HashMap<>();
      HashSet<WeightedGraphNode> visited = new HashSet<>();
      PriorityQueue<WeightedGraphNode> unexplored = new PriorityQueue<>(Comparator.comparingInt(bestDists::get));
      
      for (WeightedGraphNode stop : allStops) {
      
         bestDists.put(stop, Integer.MAX_VALUE);
      
      }
      
      bestDists.put(start, 0);
      unexplored.add(start);
                  
      while (!unexplored.isEmpty() && !visited.contains(end)) {
                     
         WeightedGraphNode current = unexplored.poll();
         HashMap<WeightedGraphNode, Integer> connections = current.getConnections();
         Set<WeightedGraphNode> connectionsKeys = connections.keySet();
                  
         for (WeightedGraphNode connection : connectionsKeys) {
         
            if (!visited.contains(connection)) {
               
               if ((bestDists.get(current) + connections.get(connection)) < bestDists.get(connection)) { //or current
                  
                  bestPaths.put(connection, current);
                  bestDists.put(connection, (bestDists.get(current) + connections.get(connection)));
               
               }
               
               visited.add(current);
               unexplored.add(connection);
            
            }
         
         }
               
      }
            
      return bestPaths;
   
   }

}