import java.util.ArrayList;
import java.util.List;



public class BoardFirstAutoPlayer extends AbstractAutoPlayer {
	private ILexicon myLex;
	private BoggleBoard myBoard;
    @Override
    public void findAllValidWords(BoggleBoard board, ILexicon lex, int minLength) {
    		
    		myBoard=board;
    		myLex=lex;
    		clear();
    		for (int r=0; r<board.size(); r++) 
    		  {
    		   for (int c=0; c<board.size(); c++) 
    		   {
    			   StringBuilder word=new StringBuilder();
    		    findHelper(word, r, c, minLength, new ArrayList<BoardCell>());
    		   }
    		  }
    		
    }
    public boolean findHelper(StringBuilder word, int row, int column, int minLength, List<BoardCell> list)
    {
    		if(row<0||row>=myBoard.size()||column<0||column>=myBoard.size())
    		{
			return false;
		}
    		BoardCell current = new BoardCell(row, column);
        	if(list.contains(current))
        	{
        		return false; 
        	}
        	if(myLex.wordStatus(word)==LexStatus.NOT_WORD)
        	{
        		return false;
        	}
        	word.append(myBoard.getFace(row, column));
        	list.add(current);
        	if(myLex.wordStatus(	word)==LexStatus.WORD &&	word.length()>=minLength)
        	{
        		add(	word.toString());
        	}
        	if(!(	findHelper(word, row+1, column+1, minLength, list)||
        			findHelper(word, row, column+1, minLength, list)||
        			findHelper(word, row-1, column+1, minLength, list)||
        			findHelper(word, row+1, column-1, minLength, list)||
        			findHelper(word, row+1, column, minLength, list)||
        			findHelper(word, row-1, column, minLength, list)||
        			findHelper(word, row-1, column-1, minLength, list)||
        			findHelper(word, row, column-1, minLength, list))){
        		list.remove(current);
        		if(myBoard.getFace(row, column).equalsIgnoreCase("qu"))
        		{
        			word.deleteCharAt(word.length()-1);
        			word.deleteCharAt(word.length()-1);
        		}
        		else
        		{
        			word.deleteCharAt(word.length()-1);
        		}
        	}
        	else
        	{
        		return true;
        	}
    		return false;
    }
    

}
