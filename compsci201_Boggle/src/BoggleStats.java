import java.io.InputStream;
import java.util.*;

import javax.swing.ProgressMonitorInputStream;

public class BoggleStats {

    ArrayList<Integer> myBoardScores, myLexiconScores;
   
    private static final int MIN_WORD = 3;
    private static final int NUM_TRIALS = 50000;
    
    public BoggleStats(){
        myBoardScores = new ArrayList<Integer>();
        myLexiconScores = new ArrayList<Integer>();
    }
    
    public String wordTester(IAutoPlayer player, ILexicon lex, ArrayList<Integer> log, int count, int boardSize){
        
        BoggleBoardFactory.setRandom(new Random(12345));
        log.clear();
        double start = System.currentTimeMillis();
        
        for(int k=0; k < count; k++){
            BoggleBoard board = BoggleBoardFactory.getBoard(boardSize);
            //if(k==7046)
            //System.out.println(board.toString());
            player.findAllValidWords(board, lex, MIN_WORD);
            log.add(player.getScore());           
        }
               
        double end = System.currentTimeMillis();
        int max = Collections.max(log);
        return String.format("%s %s\t count: %d\tmax: %d\ttime: %f", 
               player.getClass().getName(),
               lex.getClass().getName(),count,max,(end-start)/1000.0);
    }
    
    public void doTests(ILexicon lex){
        //IAutoPlayer ap1 = new LexiconFirstAutoPlayer(); 
        //String result1 = wordTester(ap1,lex,myBoardScores,NUM_TRIALS,4);
        //System.out.println(result1);
        //System.out.println("=======================================================");
        //String result2 = wordTester(ap1,lex,myBoardScores,NUM_TRIALS,5);
        //System.out.println(result2);
        //System.out.println("=======================================================");
        //System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        IAutoPlayer ap2 = new BoardFirstAutoPlayer();
        String result3 = wordTester(ap2,lex,myLexiconScores, NUM_TRIALS,4);
        System.out.println(result3);
        //System.out.println("=======================================================");
        String result4 = wordTester(ap2,lex,myLexiconScores, NUM_TRIALS,5);
        System.out.println(result4);
        //System.out.println("=======================================================");
        //for(int k=0; k < NUM_TRIALS; k++) {
            //if (!myBoardScores.get(k).equals(myLexiconScores.get(k))){
            //    System.out.println(k+"\t"+myBoardScores.get(k)+"\t"+myLexiconScores.get(k));
            //}
              //  if(myLexiconScores.get(k)==1011)
             //  { 
             //   	System.out.println("board4 is :"+ k);
                	
             //   }
            //    if(myLexiconScores.get(k)==2120)
            //    {   
            //    	System.out.println("board5 is :"+ k);
            //    }
            
        //}
    }
    
    public void runTests(ILexicon lex, ArrayList<String> list){
        lex.load(list);
        doTests(lex);
       
    }
    
    public static void main(String[] args){

        ILexicon lex = new SimpleLexicon();   
        
        
        
        InputStream is = lex.getClass().getResourceAsStream("/ospd3.txt");   
        ProgressMonitorInputStream pmis = StoppableReader.getMonitorableStream(is, "reading..."); 
        Scanner s = new Scanner(pmis);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next().toLowerCase());
        }
      
        BoggleStats bs = new BoggleStats();
        //bs.runTests(lex,list);
        //lex = new TrieLexicon();
        //bs.runTests(lex,list);
        lex = new CompressedTrieLexicon();
        bs.runTests(lex, list);
        //lex = new BinarySearchLexicon();
        //bs.runTests(lex, list);
    }
}
