import java.util.*;

public class WeightedGraphNode<T>
{
   private T data;
   private HashMap<WeightedGraphNode<T>, Integer> connections;
   
   /**
      Constructs a weighted graph node with no connectoins
      @param data for this node
   */
   public WeightedGraphNode(T data)
   {
      this.data = data;
      this.connections = new HashMap<>();
   }
   
   /**
      Adds a connection to another weighted graph node
      @param node the node to connect to
      @param weight the weight of the edge between the two nodes
   */
   public void addConnection(WeightedGraphNode<T> node, int weight)
   {
      this.connections.put(node, weight);
   }
   
   /**
      Gets the data for this node
      @return the data for this node
   */
   public T getData()
   {
      return this.data;
   }
   
   /**
      Gets all connections for this node, with weights
      @return a HashMap of all connections with their weights
   */
   public HashMap<WeightedGraphNode<T>, Integer> getConnections()
   {
      return this.connections;
   }
   
   @Override
   public String toString()
   {
      return this.data.toString();
   }
}