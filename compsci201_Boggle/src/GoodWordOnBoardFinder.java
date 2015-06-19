
import java.util.*;

public class GoodWordOnBoardFinder implements IWordOnBoardFinder
{
 public List<BoardCell> cellsForWord(BoggleBoard board, String word) 
 {
  ArrayList<BoardCell> list = new ArrayList<BoardCell>();
  for (int r=0; r<board.size(); r++) 
  {
   for (int c=0; c<board.size(); c++) 
   {
    if (findHelper(word, 0, r, c, board, list)) 
    {
     return list;
    }
   }
  }
  list.clear();
  return list;
 }
 
 
 public boolean findHelper(String word, int index, int row, int column, BoggleBoard board, List<BoardCell> list)
 {
	 if(index>=board.size()*board.size())
			return true;
 
  if (row<0 || row>=board.size() || column<0 || column>=board.size()) 
	  return false;
  if (index>=word.length()) 
	  return true;
  if (board.getFace(row, column).equals(word.substring(index,index+1))) 
  {
	  
  
	  BoardCell current = new BoardCell(row,column);
  if (list.contains(current)) 
	  return false;
  
  list.add(current);
  if( !(findHelper(word, index+1, row+1, column, board, list)||
			findHelper(word, index+1, row, column+1, board, list)||
			findHelper(word, index+1, row-1, column, board, list)||
			findHelper(word, index+1, row, column-1, board, list)||
			findHelper(word, index+1, row-1, column-1, board, list)||
			findHelper(word, index+1, row+1, column-1, board, list)||
			findHelper(word, index+1, row-1, column+1, board, list)||
			findHelper(word, index+1, row+1, column+1, board, list))){
		list.remove(current);
		return false;
	}else{
		return true;
	}
  
  }
  
  else if(index<word.length()-2&&board.getFace(row, column).length()==2&&board.getFace(row, column).equals(word.substring(index,index+2)))
  {
		BoardCell current = new BoardCell(row, column);	
		if(list.contains(current))
		{
			return false;
		}
		list.add(current);
		if( !(findHelper(word, index+2, row+1, column, board, list)||
				findHelper(word, index+2, row, column+1, board, list)||
				findHelper(word, index+2, row-1, column, board, list)||
				findHelper(word, index+2, row, column-1, board, list)||
				findHelper(word, index+2, row-1, column-1, board, list)||
				findHelper(word, index+2, row+1, column-1, board, list)||
				findHelper(word, index+2, row-1, column+1, board, list)||
				findHelper(word, index+2, row+1, column+1, board, list))){
			list.remove(current);
			return false;
		}else{
			return true;
		}
		
	}
  
  
  return false;
  
 }

}