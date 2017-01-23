package vrp.program;

import vrp.model.*;

import java.util.ArrayList;
/**
 * Created by harikanukala on 4/21/16.
 */

public class MyUtils {
	
	public static void printSaving(Saving s){
		int from = s.from.customerNumber;
		int to = s.to.customerNumber;
		System.out.println("Saving - From: " + from + " To: " + to + " Val: " + s.val);
	}
	
	public static void printRoutesCities(ArrayList<Route> routes, StringBuilder sb){
		for(Route r:routes){
			printCities(r,sb);
			sb.append("\r\n");
		}
	}
	
	public static void printRoute(Route r){
		System.out.print("Route: ");
		Edge edge = r.outEdges[0];
		
		System.out.print("(" + edge.n1.customerNumber + "->" + edge.n2.customerNumber + ")");
		
		do{
			edge = r.outEdges[edge.n2.customerNumber];
			System.out.print("(" + edge.n1.customerNumber + "->" + edge.n2.customerNumber + ")");
		}while(edge.n2.customerNumber!=0);
		
		
		System.out.print(" Cost: " + r.totalCost);
		
		System.out.println("");
	}

	public static void printCities(Route r,StringBuilder sb){
		sb.append(0 + " ");
		Edge edge = r.outEdges[0];
		sb.append(edge.n2.customerNumber + " ");
		do{
			edge = r.outEdges[edge.n2.customerNumber];
			sb.append(edge.n2.customerNumber + " ");
		}while(edge.n2.customerNumber!=0);
	}
	public static void printRoutes(ArrayList<Route> routes){
		int totalCost = 0;
		for(Route r:routes){
			printRoute(r);
			totalCost+= r.totalCost;
		}
		
		System.out.println("Total cost of the routes: " + totalCost);
	}
}
