package vrp.model;

/**
 * Created by harikanukala on 4/21/16.
 */
public class Saving implements Comparable<Saving>{
	public int val;
	public CustomerNode from;
	public CustomerNode to;
	
	public Saving(int v,CustomerNode f,CustomerNode t){
		val = v;
		from = f;
		to = t;
	}

	@Override
	public int compareTo(Saving o) {
		if(o.val<this.val){
			return -1;
		}else if(o.val == this.val){
			return 0;
		}else{
			return 1;
		}
	}
}
