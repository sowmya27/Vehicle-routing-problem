package vrp.model;

public class Edge implements Comparable<Edge>{
	public CustomerNode n1;
	public CustomerNode n2;
	
	public int val;

	
	public Edge(CustomerNode ln1,CustomerNode ln2,int dist){
		this.n1 = ln1;
		this.n2 = ln2;
		this.val = dist;
	}
	
	public void reverse(){
		CustomerNode swap = this.n2;
		this.n2 = n1;
		this.n1 = swap;
	}

	@Override
	public int compareTo(Edge o) {
		if(this.val<o.val)
			return -1;
		else if(o.val == this.val)
			return 0;
		else
			return 1;
	}
}
