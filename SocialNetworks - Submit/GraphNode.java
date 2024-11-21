import java.util.*;

/**
   An unweighted Graph Node
   @author Amanda O'Neal
*/
public class GraphNode<T>
{
   private T data;
   private ArrayList<GraphNode<T>> connections;
   
   /**
      Constructs a Graph Node with data and an empty connections list
      @param data the data for this node
   */
   public GraphNode(T data)
   {
      this.data = data;
      this.connections = new ArrayList<>();
   }
   
   /**
      Adds a node to this node's connections list.
      Note: does not create a two-way connection between the nodes.
      @param node the node to add as a connection
   */
   public void addConnection(GraphNode<T> node)
   {
      this.connections.add(node);
   }
   
   /**
      Gets the data for this node
      @return data for this node
   */
   public T getData()
   {
      return this.data;
   }
   
   /**
      Gets the list of connections for this node
      @return list of connections for this node: ArrayList of GraphNode<T> objects
   */
   public ArrayList<GraphNode<T>> getConnections()
   {
      return this.connections;
   }
   
   @Override
   public String toString()
   {
      return this.data.toString();
   }
}