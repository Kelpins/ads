import java.util.*;
public class CustomTrieNode
{
   private ArrayList<CustomTrieNode> children;
   private boolean isEndOfWord;
   private Character character;
   
   /**
      Constructs the root node, which is the only node
         with no character data.
   */
   public CustomTrieNode()
   {
      this(null, false);
   }
   
   /**
      Constructs a trie node
      @param character the character for this node
      @param isEndOfWord true if this character is the end of a valid word
   */
   public CustomTrieNode(Character character, boolean isEndOfWord)
   {
      this.character = character;
      this.isEndOfWord = isEndOfWord;
      this.children = new ArrayList<>();
   }
   
   /**
      Returns whether or not this character is already in the list of children
      @param c the character to searhc for
      @return if any of the children have this character as data
   */
   public boolean containsChild(Character c)
   {
      return children.contains(new CustomTrieNode(c, false));
   }
      
   /**
      Adds a child to this node's children
      @param c the character data for the child
      @param isEndOfWord whether the child is the end of a valid word
   */
   public void addChild(Character c, boolean isEndOfWord)
   {
      if (!containsChild(c))
      {
         //If this character isn't already in the children list,
         // add it.
         this.children.add(new CustomTrieNode(c, isEndOfWord));
      }
      else if (isEndOfWord)
      {
         //If this character was already in the children list, 
         // but we're now saying this character is also the end
         // of a valid word, update isEndOfWord.
         getChild(c).isEndOfWord = true;
      }
   }
   
   /**
      Gets the node for the child with given character data
      @param c the character data to search for
      @return the child node with that character data
   */
   public CustomTrieNode getChild(Character c)
   {
      //could use containsChild but this is more efficient
      for (CustomTrieNode childNode : this.children)
      {
         if (childNode.getCharacter() == c)
         {
            return childNode;
         }
      }
      return null;
   }
   
   /**
      Returns all children
      @return all children of this node
   */
   public ArrayList<CustomTrieNode> getChildren()
   {
      return this.children;
   }
   
   /**
      Returns the number of children
      @return the number of children for this node
   */
   public int getNumberOfChildren()
   {
      return this.children.size();
   }
   
   /**
      Returns whether this node represents the end of a word
      @return true if this is the end of a valid word, false otherwise
   */
   public boolean isEndOfWord()
   {
      return this.isEndOfWord;
   }
   
   /**
      Returns the character data
      @return the character data for this node
   */
   public Character getCharacter()
   {
      return this.character;
   }
   
   @Override
   //For ease of use, equality is determined by character data only.
   public boolean equals(Object other)
   {
      if (other instanceof CustomTrieNode)
      {
         CustomTrieNode otherNode = (CustomTrieNode)other;
         return this.character == otherNode.character;
      }
      else
      {
         return false;
      }
   }
}