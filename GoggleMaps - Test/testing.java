import java.util.*;

/*

ballard: 37
fremont: 24
roosevelt: 5
u district: 8
wedgewood: 28

*/

public class testing {

   public static void main (String[] args) {

      GoggleMaps map = new GoggleMaps();
      ArrayList<WeightedGraphNode> stops = map.getAllBusStops();
      
      System.out.println(map.getBusStopByName("fake"));
   
   }

}