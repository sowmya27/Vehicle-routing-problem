package vrp.program;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import vrp.model.*;

/**
 * Created by harikanukala on 4/21/16.
 */

public class CVRPAlgorithm {

    public static int VEHICLE_CAPACITY = 100;
    public static int[][] distances;
    public static int[] arr1;
    public static int[] arr2;
    private static CustomerNode[] customerNodes;
    private static ArrayList<Route> routes;
    private static int customerCount;

    public static void loadData(String filename) throws IOException{

        File f = new File("./input/"+filename);
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f.getCanonicalPath())));
        customerCount = Integer.parseInt(in.readLine());
        distances = new int[customerCount][customerCount];
        arr1=new int[customerCount];
        arr2=new int[customerCount];
        customerNodes = new CustomerNode[customerCount];
        for(int i=0;i<customerCount;i++){
            String line = in.readLine();
            String[] inDist = line.split(" ");
            arr1[i]=Integer.parseInt(inDist[2]);
            arr2[i]=Integer.parseInt(inDist[3]);
            CustomerNode n = new CustomerNode(i);
            n.x = Double.parseDouble(inDist[2]);
            n.y = Double.parseDouble(inDist[3]);
            customerNodes[i] = n;
        }
        distances=distanceMatrix(arr1,arr2);
        in.readLine();
        for(int i=1;i<customerCount;i++){
            String nodeInfo = in.readLine();
            String[] info = nodeInfo.split(" ");
            customerNodes[i].demand=Integer.parseInt(info[2]);
        }
    }
    public static String clarkAndWright(){
        routes = new ArrayList<Route>();
        for(int i=0;i<customerCount;i++){
            CustomerNode n = customerNodes[i];
            if(i!=0){
                Edge e  = new Edge(customerNodes[0],n,distances[0][n.customerNumber]);
                Edge e2 = new Edge(n,customerNodes[0],distances[0][n.customerNumber]);
                Route r = new Route(customerCount);
                r.loadAllowed = VEHICLE_CAPACITY;
                r.add(e);
                r.add(e2);
                r.currentLoad += n.demand;
                routes.add(r);
            }
        }
        ArrayList<Saving> sList = calculateSaving(distances, customerCount, customerNodes);
        Collections.sort(sList);
        MyUtils.printRoutes(routes);
        while(!sList.isEmpty()){
            Saving actualS = sList.get(0);
            CustomerNode n1 = actualS.from;
            CustomerNode n2 = actualS.to;
            Route r1 = n1.route;
            Route r2 = n2.route;
            int from = n1.customerNumber;
            int to = n2.customerNumber;
            MyUtils.printSaving(actualS);
            if(actualS.val>0 && r1.currentLoad+r2.currentLoad<r1.loadAllowed && !r1.equals(r2)) {
                Edge outgoingR2 = r2.outEdges[to];
                Edge incommingR1 = r1.inEdges[from];
                if (outgoingR2 != null && incommingR1 != null) {
                    boolean succ = r1.merge(r2, new Edge(n1, n2, distances[n1.customerNumber][n2.customerNumber]));
                    if (succ) {
                        routes.remove(r2);
                    }
                } else {
                    System.out.println("Problem");
                }
            }
            sList.remove(0);
            MyUtils.printRoutes(routes);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(routes.size() + "\r\n");
        MyUtils.printRoutesCities(routes,sb);
        return sb.toString();
    }
    public static ArrayList<Saving> calculateSaving(int[][] dist,int n,CustomerNode[] nodesField){
        int[][] sav = new int[n][n];
        ArrayList<Saving> sList = new ArrayList<Saving>();
        for(int i=1;i<n;i++) {
            for (int j = i + 1; j < n; j++) {
                sav[i][j] = dist[0][i] + dist[j][0] - dist[i][j];
                CustomerNode n1 = nodesField[i];
                CustomerNode n2 = nodesField[j];
                Saving s = new Saving(sav[i][j], n1, n2);
                sList.add(s);
            }
        }
        return sList;
    }
    public static int[][] distanceMatrix(int[] arr1,int[] arr2){
        int distMatr[][]=new int[arr1.length][arr1.length];
        for(int i=0;i<arr1.length;i++){
            for(int j=0;j<arr1.length;j++){
                distMatr[i][j]=(int)Math.sqrt(Math.pow(arr2[j]-arr2[i],2.0)+Math.pow(arr1[j]-arr1[i],2.0));
            }
        }
        return distMatr;
    }
}
