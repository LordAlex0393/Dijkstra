import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Dijkstra {
    private static HashMap<String, HashMap<String, Integer>> graph;
    private static HashMap<String, Integer> costs = new HashMap<>();
    private static HashMap<String, String> parents = new HashMap<>();
    private static String startKey;
    private static String finalKey;

    public Dijkstra(HashMap<String, HashMap<String, Integer>> graph, String startKey, String finalKey){
        this.graph = graph;
        this.startKey = startKey;
        this.finalKey = finalKey;
        for(String key : graph.keySet()){
            if(!key.equals(startKey)){
                costs.put(key, Integer.MAX_VALUE);
                parents.put(key, null);
            }
        }
        HashMap<String, Integer> ways = (HashMap) graph.get(startKey);
        for(String key : ways.keySet()){
            int value = ways.get(key);
            costs.put(key, value);
            parents.put(key, startKey);
        }
    }
    public void printInfo(){
        System.out.println(graph.toString());
        System.out.println(costs.toString());
        System.out.println(parents.toString() + "\n");
    }

    public int findPass(){
        HashSet<String> processed = new HashSet<>();
        String minKey = finalKey;
        int currentPointCost;
        while(processed.size() < graph.size()-2){
            //FINDING MIN
            for(String key : costs.keySet()) {
                if ((!processed.contains(key)) && (costs.get(key) < costs.get(minKey))) {
                    minKey = key;
                }
            }
            if(minKey == finalKey){
                break;
            }
            //System.out.println(minKey);
            processed.add(minKey);
            currentPointCost = costs.get(minKey);
            //CREATING NEIGHBORS
            HashMap<String, Integer> neighbors = new HashMap<>();
            for(String key : graph.get(minKey).keySet()){
                neighbors.put(key, graph.get(minKey).get(key));
            }
            //UPDATING COSTS
            for(String key : neighbors.keySet()){
                if((currentPointCost+ neighbors.get(key)) < costs.get(key)){
                    costs.put(key, currentPointCost + neighbors.get(key));
                    parents.put(key, minKey);
                }
            }
            //printInfo();
            minKey = finalKey;
        }
        printPath();
        return costs.get(finalKey);
    }
    public static void printPath(){
        String newKey = finalKey;
        String path = "";
        ArrayList<String> points = new ArrayList<>();
        while(parents.get(newKey) != startKey){
            points.add(newKey);
            newKey = parents.get(newKey);
        }
        points.add(newKey);
        points.add(parents.get(newKey));
        for(int i = points.size()-1; i >= 0; i--){
            path+= points.get(i) + " --> ";
        }
        System.out.println(path.substring(0, path.length()-5));
    }
}