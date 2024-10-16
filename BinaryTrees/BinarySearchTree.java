import sh.*;

/**
   A binary search tree, which is defined by this:
      For any given node, every node to the left of it is smaller; every node to the right of it is larger.
*/
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>
{
   private BinaryNode<T> root;
   
   /**
      Constructs a binary search tree with no nodes.
   */
   public BinarySearchTree()
   {
      this.root = null;
   }
   
   @Override
   public BinaryNode<T> getRoot()
   {
      return this.root;
   }
   
   /**
      Adds the data as a valid node in this Binary Search Tree.
      This involves traversing the tree to find the right place to put the node,
      such that it meets the definition of a valid BST.
      @param data the data for the node to add.
   */
   public void add(T data)
   {
      if (this.root == null) {
      
         this.root = new BinaryNode<T>(data);
      
      }
      else {
      
         addHelper(data, this.root);
      
      }

   }
   
   public void addHelper(T data, BinaryNode<T> rt) {
   
      if (rt.getData().compareTo(data) > 0) {
      
         if (rt.getLeft() == null) {
         
            rt.setLeft(new BinaryNode<T>(data));
         
         }   
         else {
         
            addHelper(data, rt.getLeft());
            
         }
         
      }
      else {
      
         if (rt.getRight() == null) {
         
            rt.setRight(new BinaryNode<T>(data));
         
         }   
         else {
         
            addHelper(data, rt.getRight());
            
         }
      
      }
   
   }
   
   /**
      Prints the tree in "pre-order", meaning for a given node, it first
         prints the node's left child; then the node's data; then the node's right
         child. For a valid binary search tree, this will always print the contents
         in sorted order from smallest to largest.
   */
   public void printPreOrder()
   {
      
      if (this.root == null) {
      
         sh.print("null");
      
      }
      else {
         
         printHelper(this.root);
      
      }

   }
   
   public void printHelper(BinaryNode<T> rt) {
      
      if (rt == null) {
      
         sh.print("");
      
      }
      else {
            
         printHelper(rt.getLeft());
         sh.print(" " + rt.getData());
         printHelper(rt.getRight());
         
      }
   
   }

   //Optional: Also make methods to print the tree in "post-order" (left, right, self) 
   //          and "in-order" (self, left, right)

}