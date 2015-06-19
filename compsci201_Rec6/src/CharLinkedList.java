import java.util.HashSet;

/**
 * COMPSCI 201 Recitation 6, Spring 2015
 * 
 * Iterative Linked Lists: More Practice!
 * 
 * @author Austin Lu
 */
public class CharLinkedList {

  private class Node {
    char myValue;
    Node myNext;

    Node(char value, Node next) {
      myValue = value;
      myNext = next;
    }
  }

  /**
   * The head "pointer"
   */
  Node myHead;
  
  /**
   * Number of nodes in the linked list
   */
  int myLength;

  /**
   * Adds a new node the end of the linked list
   * 
   * @param valueToAdd value of the new node to add
   */
  public void addToEnd(char valueToAdd) {
    // Completed for you -- be sure to understand it!
    myLength++;
    if (myHead == null) {
      myHead = new Node(valueToAdd, null);
      return;
    }
    Node curr = myHead;
    while (curr.myNext != null) {
      curr = curr.myNext;
    }
    curr.myNext = new Node(valueToAdd, null);
  }

  @Override
  public String toString() {
    // Completed for you -- be sure to understand it!
    Node curr = myHead;
    String ret = "";
    if (curr == null) {
      return ret;
    }
    while (curr != null) {
      ret += curr.myValue + " ";
      curr = curr.myNext;
    }
    return ret.substring(0, ret.length() - 1);
  }


  /**
   * Insert a new node at index k of the linkedlist (0-based indexing). E.g. Inserting at k=0 is the
   * same as adding to the beginning of the list.
   * 
   * If k >= length of the linked list, add the new node to the end of the linked list.
   * 
   * @param k index to insert at
   * @param valueToInsert value of the new node to insert
   */
  public void insertAtIndex(int k, char valueToInsert) {
	  int count=0;
	  if(k>=myLength)
	  {
		  addToEnd(valueToInsert);
	  }
	  else if(k==0)
	  {
		  myHead = new Node(valueToInsert, myHead);
		  myLength++;
	  }
	  else
	  {
		  Node curr = myHead;
		  while(k>=count)
		  {
		  count++;
		  
		  if(k==count)
		  {
			  Node next=new Node(valueToInsert, curr.myNext);
			  curr.myNext = next;
			  myLength++;
		  }
		  else
		  curr = curr.myNext;
		  }
	  }
    // TODO 1. Complete insertAtIndex(int k)

  }

  /**
   * Count the number of unique characters in the linked list If the linked list is empty, return 0.
   */
  public int countUniqueChars() {
    // TODO 2. Complete countUniqueChars()
	  HashSet<String> count=new HashSet<String>();
	  if(myLength==0||myHead==null)
	  {
		  return 0;
	  }
	  else
	  {
		  String temp=toString();
		  for(int i=0;i<temp.length();i=i+2)
		  {
			  count.add(temp.substring(i, i+1));
		  }
	  }
    return (count.size());
  }

  /**
   * Remove all nodes of duplicate values from the list, keeping only the first. If the list is
   * empty, do nothing.
   */
  public void removeDuplicates() {
	  HashSet<Character> count=new HashSet<>();
	  if(myLength!=0||myHead!=null)
	  {
	  Node curr = myHead;
	  while (curr.myNext != null) 
		    {
		    	count.add(curr.myValue);
		    	
		    	if(count.contains(curr.myNext.myValue))
		    	{
		    		curr.myNext=curr.myNext.myNext;
		    		myLength--;
		    	}
		    	else
		    	curr = curr.myNext;
		      
		    }
	  }
	  

  }

  /**
   * Returns number of nodes in the linked list.
   */
  public int getLength() {
    // TODO: 4. Complete getLength as efficiently as possible (involves updating myLength in other methods).
    return 0;
  }
  public int count11(String str) {
	  if(str.length()<2)
	  return 0;
	  if(str.substring(0, 2).equals("11"))
	  {
	    return 1+count11(str.substring(2));
	  }
	  return count11(str.substring(1));
  }
  public String parenBit(String str) {
	  if(str.indexOf("(")==-1||str.indexOf(")")==-1)
	  {
		  return "";
	  }
	  return str.substring(str.indexOf("("), str.indexOf(")")+1);
  }
  public boolean nestParen(String str) {
	  if((str.indexOf("(")==-1||str.indexOf(")")==-1)&&str.length()!=0)
	  {
		  return false;
	  }
	  if(str.equals(""))
		  return true;
	  if(str.substring(0, 1).equals("(")&&str.substring(str.length()-1).equals(")"))
		  return nestParen(str.substring(1, str.length()-1));
	  return false;
  }


}
