import java.util.HashMap;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //test();
        //test2();
        HashMap<String, HashMap<String, Integer>> graph = readGraph();

        Dijkstra solve = new Dijkstra(graph, "H", "K");
        //solve.printInfo();
        System.out.println(solve.findPass());

    }
    public static HashMap<String, HashMap<String, Integer>> readGraph(){
        Scanner scanner = new Scanner(System.in);
        HashMap<String, HashMap<String, Integer>> graph = new HashMap<>();
        String line;
        String currentName = null;
        HashMap<String, Integer> exGraph = new HashMap<>();
        while(true){
            line = scanner.nextLine();
            if(line.contains(" ")){
                String[] key_value = line.split(" ");
                exGraph.put(key_value[0], Integer.valueOf(key_value[1]));
            }
            else if(line.equals("stop")){
                graph.put(currentName, exGraph);
                graph.put("K", null);
                return graph;
            }
            else{
                if(currentName!=null){
                    graph.put(currentName, exGraph);
                }
                currentName = line;
                exGraph = new HashMap<>();
            }
            //System.out.println(graph.toString());
        }
    }

    public static void test(){
        HashMap<String, Integer> beg = new HashMap<>();
        beg.put("A", 6);
        beg.put("B", 2);
        HashMap<String, Integer> a = new HashMap<>();
        a.put("End", 1);
        HashMap<String, Integer> b = new HashMap<>();
        b.put("A", 3);
        b.put("End", 5);

        HashMap<String, HashMap<String, Integer>> graph = new HashMap<>();
        graph.put("Beg", beg);
        graph.put("A", a);
        graph.put("B", b);
        graph.put("End", null);

        Dijkstra solve = new Dijkstra(graph, "Beg", "End");
        //solve.printInfo();
        System.out.println("Cost = " + solve.findPass());
    }

    public static void test2(){
        HashMap<String, Integer> beg = new HashMap<>();
        beg.put("A", 5);
        beg.put("B", 2);
        HashMap<String, Integer> a = new HashMap<>();
        a.put("C", 2);
        a.put("D", 7);
        HashMap<String, Integer> b = new HashMap<>();
        b.put("A", 8);
        b.put("C", 7);
        HashMap<String, Integer> c = new HashMap<>();
        c.put("End", 1);
        HashMap<String, Integer> d = new HashMap<>();
        d.put("C", 6);
        d.put("End", 3);


        HashMap<String, HashMap<String, Integer>> graph = new HashMap<>();
        graph.put("Beg", beg);
        graph.put("A", a);
        graph.put("B", b);
        graph.put("C", c);
        graph.put("D", d);
        graph.put("End", null);

        Dijkstra solve = new Dijkstra(graph, "Beg", "End");
        //solve.printInfo();
        System.out.println("Cost = " + solve.findPass());
    }
}