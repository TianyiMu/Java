import java.util.*;



public class LinkStrand implements IDnaStrand 
{
	public class Node{
		String value;
		Node next;
		public Node(String s, Node link)
		{
			value =s;
			next=link;
		}

	}
	
	private Node myFront, myBack;
	private long mySize;
	private LinkStrand myInfo;
    private int myAppends;
    
    public LinkStrand() {
    	this("");
    }
    public LinkStrand(String s) {
        Node temp=new Node(s,null);
        myFront=temp;
        myBack=temp;
        myAppends=0;
        mySize=mySize+s.length();
    }
    
	@Override
	public IDnaStrand cutAndSplice(String enzyme, String splicee) {
		Node back=myFront;
		LinkStrand temp=null;
		boolean f = true;
		int pos = 0;
        int start = 0;
        String search = back.value;
        boolean first = true;
        LinkStrand ret = null;
        
        while ((pos = search.indexOf(enzyme, pos)) >= 0) {
            if (first){
                ret = new LinkStrand(search.substring(start, pos));
                first = false;
            }
            else {
                 ret.append(search.substring(start, pos));
            }
            start = pos + enzyme.length();
            ret.append(splicee);
            pos++;
        }

        if (start < search.length()) {
        	if (ret == null){
        		ret = new LinkStrand("");
        	}
        	else 
        	{
        		ret.append(search.substring(start));
        		
        	}
        }
        
		
		return ret;
	}

	@Override
	public long size() {
		return mySize;
	}

	@Override
	public void initializeFrom(String source) {
		Node temp=new Node(source,null);
        myFront=temp;
        myBack=temp;
        mySize=mySize+source.length();

	}

	@Override
	public String strandInfo() {
		return getClass().getName();
	}

	@Override
	public IDnaStrand append(IDnaStrand dna) {
		if (dna instanceof LinkStrand) {
			LinkStrand ss = (LinkStrand) dna;
			myBack.next=ss.myFront;
			myBack=ss.myBack;
			mySize=mySize+ss.size();
			myAppends++;
            return this;
        } 
		else if (dna instanceof SimpleStrand) {
			SimpleStrand ss = (SimpleStrand) dna;
            return append(ss.toString());
        } 
		else {
            return append(dna.toString());
        }
	}

	@Override
	public IDnaStrand append(String dna) {
		Node temp=new Node(dna,null);
		myBack.next=temp;
		myBack=temp;
		mySize=mySize+dna.length();
		myAppends++;
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		StringBuilder first=new StringBuilder(myFront.value);
		String temp=first.reverse().toString();
		HashMap<String,String> strand=new HashMap<String,String>();//use Hashmap to store reversed String
		//this will only reverse String once
		strand.put(myBack.value, temp);
		LinkStrand tempo=new LinkStrand(temp);
		Node back=myFront.next;
		while(back!=null)
		{
			StringBuilder rest=new StringBuilder(back.value);
			String tempRest="";
			if(!strand.containsKey(back.value))
			{
				tempRest=rest.reverse().toString();
				strand.put(back.value, tempRest);
			}
			else
			{
				tempRest=strand.get(back.value);
			}
			Node front=new Node(tempRest,tempo.myFront);
			tempo.myFront=front;
			Node n=back.next;
			back=n;
		}
		tempo.mySize=mySize;
		tempo.myAppends=myAppends;
		this.myFront=tempo.myFront;
		this.myBack=tempo.myBack;
		return this;
	}
	
	@Override
    public String toString() {
		StringBuilder allList=new StringBuilder(myFront.value);
		Node back=myFront.next;
		while(back!=null)
		{
			allList.append(back.value);
			Node temp=back.next;
			back=temp;
		}
        return allList.toString();
    }

	@Override
	public String getStats() {
		return String.format("# append calls = %d",myAppends);
	}

}
