import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class BST {

  TreeNode root = null;
  private HashSet<Integer> count=new HashSet<Integer>();
  public class TreeNode {
    public int myKey;
    public TreeNode myLeft; // holds smaller tree nodes
    public TreeNode myRight; // holds larger tree nodes

    public TreeNode(int key) {
      myKey = key;
    }
  }

  public void add(int newKey) {
    if (root == null)
      root = new TreeNode(newKey);
    else
      add(newKey, root);
  }

  private void add(int newKey, TreeNode current) {
    if (newKey < current.myKey) {
      if (current.myLeft == null) {
        current.myLeft = new TreeNode(newKey);
      } else {
        add(newKey, current.myLeft);
      }
    } else {
      if (current.myRight == null) {
        current.myRight = new TreeNode(newKey);
      } else {
        add(newKey, current.myRight);
      }
    }
  }

  public String toString() {
    return toString(root, "") + "\n";
  }

  private String toString(TreeNode current, String level) {
    String leftString = "null";
    String rightString = "null";

    if (current.myLeft != null)
      leftString = toString(current.myLeft, level + "   ");
    if (current.myRight != null)
      rightString = toString(current.myRight, level + "   ");

    return current.myKey + "\n" + level + "L: " + leftString + "\n" + level + "R: " + rightString;
  }

  public int countNodes() {
    return countNodes(root);
  }

  private int countNodes(TreeNode current) {
    if(current ==null)
    	return 0;
    int countLeft=countNodes(current.myLeft);
    int countRight=countNodes(current.myRight);
    return 1+countLeft+countRight;
    
  }

  public int computeHeight() {
    return computeHeight(root);
  }

  /**
   * Return the height. NOTE: Define the height of a single tree node as 1. Return 0 if the tree is
   * null.
   */
  private int computeHeight(TreeNode current) {
	  if(current ==null)
	    	return 0;
	  int countLeft=computeHeight(current.myLeft);
	    int countRight=computeHeight(current.myRight);
	    if(countLeft>countRight)
	    	return 1+countLeft;
	    else
	    	return 1+countRight;
  }

  public int numLeaves() {
    return numLeaves(root);
  }

  /**
   * Return the number of leaves in the tree
   */
  private int numLeaves(TreeNode current) {
	  if(current ==null)
	    	return 0;
	  if(current.myLeft==null&&current.myRight==null)
		  return 1;
	    int countLeft=numLeaves(current.myLeft);
	    int countRight=numLeaves(current.myRight);
	    return countLeft+countRight;
  }

  public int levelCount(int target) {
    return levelCount(root, target);
  }

  /**
   * Returns number of nodes at specified level in t, where level >= 0. The root is at level 0.
   * 
   * @param level specifies the level, >= 0
   * @param t is the tree whose level-count is determined
   * @return number of nodes at given level in t
   */
  private int levelCount(TreeNode t, int level) {
	  if(t ==null)
	    	return 0;
	  if(level==0)
		  return 1;
	    int countLeft=levelCount(t.myLeft,level-1);
	    int countRight=levelCount(t.myRight,level-1);
	    return countLeft+countRight;

  }

  public boolean hasPathSum(int target) {
    return hasPathSum(root, target);
  }

  /**
   * Return true if and only if t has a root-to-leaf path that sums to target.
   * 
   * @param t is a binary tree
   * @param target is the value whose sum is searched for on some root-to-leaf path
   * @return true if and only if t has a root-to-leaf path summing to target
   */
  private boolean hasPathSum(TreeNode current, int target) {
    if(levelCount(current, target)>0)
    	return true;
    return false;
  }

  public int findKth(int k) {
    return findKth(root, k);
  }

  /**
   * Find key of the kth smallest element in the tree e.g. k = 0 is the smallest element. Return 0
   * for k >= # nodes in the tree
   */
  
  private int findKth(TreeNode current, int k) {
	  if(countNodes(current)<k)
		  return 0;
	  findDatK(current);
	  ArrayList<Integer> comp=new ArrayList<Integer>();
	  for(int s: count)
	  {
		  comp.add(s);
	  }
	  Collections.sort(comp);
	  return comp.get(k-1);

  }
  
  private void findDatK(TreeNode current) {
	    if(current ==null);
	    else
	    {
	    	count.add(current.myKey);
	    	findDatK(current.myLeft);
	    	findDatK(current.myRight);
	    }
	    
	  }
  public static void main(String[] args) {
    BST bst = new BST();
    int[] data = {5, 7, 1, 3, 0, 6, 4, 2, 8};
    for (int i : data) {
      bst.add(i);
    }
    System.out.print(bst.toString());
    System.out.printf("Total number of nodes: %d\n", bst.countNodes()); // 9
    System.out.printf("Tree height: %d\n", bst.computeHeight()); // 4
    System.out.printf("Number of leaves: %d\n", bst.numLeaves()); // 5
    System.out.printf("Number of nodes on level 2: %d\n", bst.levelCount(2)); // 4
    System.out.println("Has path sum 13: " + bst.hasPathSum(13));// true
    System.out.println("Has path sum 10: " + bst.hasPathSum(10));// false
    System.out.printf("The key in the 5th smallest node is: %d\n", bst.findKth(5)); // 5

  }
}
