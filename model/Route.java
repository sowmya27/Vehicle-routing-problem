package vrp.model;

import java.util.ArrayList;

/**
 * Created by harikanukala on 4/21/16.
 */
public class Route {
	
	public int loadAllowed;
	public int currentLoad;
	public int totalCost;
	
	
	public int[] customerNodes;
	public Edge[] inEdges;
	public Edge[] outEdges;
	
	
	ArrayList<Edge> edges;
	
	public Route(int nodesNumber){
		edges = new ArrayList<Edge>();

		customerNodes = new int[nodesNumber];
		inEdges = new Edge[nodesNumber];
		outEdges = new Edge[nodesNumber];
	}
	
	public void add(Edge e){
		edges.add(e);
		
		outEdges[e.n1.customerNumber] = e;
		inEdges[e.n2.customerNumber] = e;
		
		e.n1.route = this;
		e.n2.route = this;
		
		totalCost+= e.val;
	}
	
	public void removeEdgeToNode(int index){
		Edge e = inEdges[index];
		outEdges[e.n1.customerNumber] = null;
		
		totalCost-= e.val;
		
		edges.remove(e);
		inEdges[index] = null;
	}
	
	public void removeEdgeFromNode(int index){
		Edge e = outEdges[index];
		inEdges[e.n2.customerNumber] = null;
		
		totalCost-=e.val;
		edges.remove(e);
		outEdges[index] = null;
	}
	
	public int predecessor(int nodeIndex){
		return inEdges[nodeIndex].n1.customerNumber;
	}
	
	
	public int successor(int nodeIndex){
		return outEdges[nodeIndex].n2.customerNumber;
	}
	
	public boolean merge(Route r2,Edge mergingEdge){

		int from = mergingEdge.n1.customerNumber;
		int to = mergingEdge.n2.customerNumber;
		
		int predecessorI = this.predecessor(from);
		int predecessorJ = r2.predecessor(to);
		
		int successorI = this.successor(from);
		int successorJ = r2.successor(to);

		if(successorI == 0 && predecessorJ == 0){
			this.removeEdgeToNode(0);
			r2.removeEdgeFromNode(0);
			for(Edge e:r2.edges){
				this.add(e);
			}
			this.currentLoad+= r2.currentLoad;
			this.add(mergingEdge);
			return true;

		}else if(successorJ == 0 && predecessorI == 0){
			mergingEdge.reverse();
			this.removeEdgeFromNode(0);
			r2.removeEdgeToNode(0);
			for(Edge e:r2.edges){
				this.add(e);
			}
			this.currentLoad+= r2.currentLoad;
			this.add(mergingEdge);
			return true;
		}
		
		return false;
	}
}
