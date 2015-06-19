public class StringLinkedList {

  private class Node {
    String myValue;
    Node myNext;

    Node(String value, Node next) {
      myValue = value;
      myNext = next;
    }
  }

  Node myHead;

  /**
   * Adds a new node with valueToAdd as value to the <em>end</em> of this linked list
   */
  public void addAtEnd(String valueToAdd) {
	 
    
    if(myHead==null)
    {
    	myHead=new Node(valueToAdd, null);
    }
    else
    {
    	Node current = myHead;
    while(current.myNext!=null)
    {
    	current=current.myNext;
    }
    current.myNext=new Node(valueToAdd, null);
    }
	  

  }

  /**
   * Adds a new node with valueToAdd as value to the <em>beginning</em> of this linked list
   */
  public void addAtBeginning(String valueToAdd) {
    myHead = new Node(valueToAdd, myHead);
  }

  /**
   * Removes the longest string from the list,
   * 
   * if you have the list [a,b,longstring,z,q] after this function runs you end up with [a,b,z,q] if
   * more than one string has the same longest length, remove the first one if the list is empty, do
   * nothing if the list has only 1 element, remove it
   */
  public void removeLongestString() {
    // TODO 2 Complete removeLongestString
    // when you implement this function, be sure to think about
    // a. what if the list is empty
    // b. what if the longest element is the first element
    // c. what if the list has only 1 element

	  Node current = myHead;
	  if(current==null);
	  else if(current.myNext==null)
	  {
		  current=null;
	  }
	  else
	  {
		  String currentString=current.myValue;
		  Node temp = myHead;
		  Node pre=myHead;
		  Node tempPre=myHead;
		  int count=0;
		  while(current.myNext!=null)
		    {
			  
			  if(current.myValue.length()>currentString.length())
			  {
				  currentString=current.myValue;
				  temp = current;
				  tempPre=pre;
			  }
		    	current=current.myNext;
		    	if(count>0)
				  {
					  pre=pre.myNext; 
				  }
		    	count++;
		    }
		  if(temp.myNext==null)
		  {
			  tempPre.myNext=null;
		  }
		  else
		  {
			  tempPre.myNext=temp.myNext;
		  }
	  }
  }

  /**
   * Repeats (doubles) each element [a,b,c] -> [a,a,b,b,c,c]
   */
  public void doubleList() {
    // TODO 3. Complete doubleList
  }

  /**
   * Move k elements from the beginning of the list to the end [a,b,c,d,e] -> moveToEnd(2) ->
   * [c,d,e,a,b]
   */
  public void moveToEnd(int k) {
    // TODO 4. completeMoveToEnd
  }

  /**
   * Two StringLinkedLists are equal iff they have the same elements and are are the same length
   */
  public boolean equals(StringLinkedList other) {
    // TODO 5. complete equals
    return false;
  }

  public String toString() {
    // Already complete!
    StringBuilder b = new StringBuilder();
    Node current = myHead;
    while (current != null) {
      b.append(current.myValue);
      b.append(" ");
      current = current.myNext;
    }
    return b.toString();
  }
}
