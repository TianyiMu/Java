import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TreeHuffProcessor implements IHuffProcessor {
    
    private HuffViewer myViewer;
    private int[] freqCount;
    private TreeNode treeRoot;
    private Map<Integer,String> huffCode = new HashMap<Integer,String>();
    private TreeNode daRoot;
    private int count;
    private int bitNum;
    
    
    
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException 
    {
    	
        //throw new IOException("compress is not implemented");
    		BitInputStream bis =new BitInputStream(in);
    		BitOutputStream bos = new BitOutputStream(out);
    		count = 0;
	    bos.writeBits(BITS_PER_INT, MAGIC_NUMBER);
	    count+=BITS_PER_INT;
	    bos.writeBits(BITS_PER_INT, STORE_TREE);
	    count+=BITS_PER_INT;
	    codeFromTree(bos,treeRoot);
	    
	    int idx=0;
	    
	    
	    while((idx = bis.readBits(BITS_PER_WORD))!=-1)
	    {
		    	String a = huffCode.get(idx);
		    	for (int i = 0; i < a.length(); i++) 
		    	{
		    		bos.writeBits(1,Character.getNumericValue(a.charAt(i)));
		    	}
		    	count+=1;
	    }
	    String eof = huffCode.get(PSEUDO_EOF);
	    for (int i = 0; i < eof.length(); i++) 
	    {
	    		bos.writeBits(1,Character.getNumericValue(eof.charAt(i)));
		}
	    bis.close();
	    bos.close();
	    if(count>bitNum&&!force)
	    		throw new IOException("Compression does not save any space");
	    
        return count;
    }
    
    public void codeFromTree(BitOutputStream bos, TreeNode root)
    {
	    	if(root==null)
	    		return;
	    	if(root.myLeft==null&&root.myRight==null)
	    	{
	    		bos.writeBits(1, 1);
	    		if(root.myValue==PSEUDO_EOF)
	    		{
	    			bos.writeBits(BITS_PER_WORD+1, PSEUDO_EOF);
	    			return;
	    		}
	    		else 
	    		{
	    			bos.writeBits(1, 0);
	    			bos.writeBits(BITS_PER_WORD, root.myValue);
	    			return;
	    			
	    		}
	    	}
	    	if(root.myRight==null)
	    	{
	    		bos.writeBits(1, 0);
	    		codeFromTree(bos, root.myLeft);
	    		return;
	    	}
	    	if(root.myLeft==null)
	    	{
	    		bos.writeBits(1, 0);
	    		codeFromTree(bos, root.myRight);
	    		return;
	    	}
	    	bos.writeBits(1, 0);
	    	codeFromTree(bos, root.myLeft);
	    	codeFromTree(bos, root.myRight);
    }

    public int preprocessCompress(InputStream in) throws IOException {
        //throw new IOException("preprocess not implemented");
	    	if(in==null)
	    		return 0;
	    	
	    	BitInputStream bis = new BitInputStream(in); 
	    	freqCount=new int[ALPH_SIZE];
	    	int idx=0;
	    	bitNum=0;
	    	while((idx=bis.readBits(BITS_PER_WORD))!=-1)
	    	{
	    		freqCount[idx]++;
	    		bitNum+=8;
	    	}
	    	treeRoot=getRoot(freqCount);
	    	huffCode=new HashMap<Integer, String>();
	    	
	    	buildDaMap(treeRoot, new StringBuilder());
	    bis.close();
	    	return bitNum;
    }
    
    public void buildDaMap(TreeNode root, StringBuilder temp)
    {
    		if(root == null)
	    		return;
	    	if(root.myLeft==null&&root.myRight==null)
	    	{
	    		huffCode.put(root.myValue,temp.toString());
	    		return;
	    	}
	    	buildDaMap(root.myLeft,temp.append("0"));
	    	
	    	temp.deleteCharAt(temp.toString().length()-1);
	    	
	    	buildDaMap(root.myRight,temp.append("1"));
	    	
	    	temp.deleteCharAt(temp.toString().length()-1);
    }
    
    public TreeNode getRoot(int[] freq)
    {
	    	PriorityQueue<TreeNode> tempQ = new PriorityQueue<TreeNode>();
	    	int i=0;
	    	while(i<freq.length)
	    	{
	    		if(freq[i]>0)
	    			tempQ.add(new TreeNode(i,freq[i],null,null));
	    		i++;
	    	}
	    	
	    	tempQ.add(new TreeNode(PSEUDO_EOF,1));
	    	
	    	while (tempQ.size() > 1)
		{
	    		TreeNode l=tempQ.remove();
	    		TreeNode r=tempQ.remove();
	    		tempQ.add(new TreeNode(0,l.myWeight+r.myWeight,l,r));
		}
		daRoot = tempQ.remove();
		return daRoot;
	    	
    }

    public void setViewer(HuffViewer viewer) {
        myViewer = viewer;
    }

    public int uncompress(InputStream in, OutputStream out) throws IOException 
    {
        //throw new IOException("uncompress not implemented");
	    	BitInputStream bis = new BitInputStream(in);
	    	BitOutputStream bos = new BitOutputStream(out);
	    	
	    	int magicNum = bis.readBits(BITS_PER_INT);
	    	
        if (magicNum!=MAGIC_NUMBER)
            throw new IOException("Wrong MAGIC NUMBER");
        
        int temp = bis.readBits(BITS_PER_INT);
        
        TreeNode tempRoot = null;
        if(temp==STORE_TREE)
        {
	        	System.out.println("Compressed Tree used");
	        	tempRoot = getRoot(bis);
        }
        else if(temp==STORE_COUNTS)
        {
	        	System.out.println("Compressed Freqs used");
	        	int[] freq= new int[ALPH_SIZE];
	        	for (int i = 0; i < ALPH_SIZE; i++) 
	        		freq[i]=bis.readBits(BITS_PER_INT);
	        	tempRoot = getRoot(freq);
        }
        
        TreeNode current = tempRoot;
        int count = 0;
        while(current.myValue!=PSEUDO_EOF)
        {
	        	int first = bis.readBits(1);
	        	if (first == -1) 
	        		throw new IOException("No PSEUDO-EOF");
	        	if((first&1)==1)
	        		current =current.myRight;
	        	if((first&1)==0)
	        		current = current.myLeft;
	        	if(current.myValue==PSEUDO_EOF)
	        		break;
	        	if(current.myLeft==null&&current.myRight==null)
	        	{
	        		if(current.myValue==PSEUDO_EOF)
	        			break;
	        		bos.writeBits(BITS_PER_WORD, current.myValue);
	        		current = tempRoot;
	        		count+=BITS_PER_WORD;
	        	}
        	
        }
        bis.close();
        bos.close();
        
        
        return count;
    }
    
    public TreeNode getRoot(BitInputStream bis) throws IOException
    {
    	   
        int first = bis.readBits(1);	
	    	if(first==-1)
	    	{
	    		System.out.println("Wrong");
	    		return null;
    		}
        if(first==0) 
        {
	        	TreeNode root = new TreeNode(0,0,null,null);
	        	root.myLeft = getRoot(bis);
	        	root.myRight = getRoot(bis);
	        	return root;
        }
        else 
        {
        		first= bis.readBits(BITS_PER_WORD+1);
	        	TreeNode root = new TreeNode(first,0,null,null);
	        	return root;
        }	
    }
    
    private void showString(String s){
        myViewer.update(s);
    }

}
