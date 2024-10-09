/**
   A single binary tree node, which has data; a left child; and a right child.
*/
public class BinaryNode<T extends Comparable<T>>
{
   private T data;
   private BinaryNode<T> left;
   private BinaryNode<T> right;
   
   /**
      Construct a binary node with data, and with null left- and right-children.
   */
   public BinaryNode(T data)
   {
      this.data = data;
      this.left = null;
      this.right = null;
   }
   
   /**
      Get the data from this node
      @return the data for this node
   */
   public T getData()
   {
      return this.data;
   }
   
   /**
      Set the data for this node
      @param data the new data
   */
   public void setData(T data)
   {
      this.data = data;
   }
   
   /**
      Set the left child of this node
      @param node the node to set as the left child.
   */
   public void setLeft(BinaryNode<T> node)
   {
      this.left = node;
   }
   
   /**
      Get the left child of this node
      @return the left child
   */
   public BinaryNode<T> getLeft()
   {
      return this.left;
   }
   
   /**
      Set the right child of this node
      @param node the node to set as the right child.
   */
   public void setRight(BinaryNode<T> node)
   {
      this.right = node;
   }
   
   /**
      Get the right child of this node
      @return the right child
   */
   public BinaryNode<T> getRight()
   {
      return this.right;
   }
   
   @Override
   public String toString()
   {
      return data.toString();
   }
}