import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, X;
    static int max_value = 1_000_000;
    static List<List<Edge>> adj, reverseAdj;

    static class Edge implements Comparable<Edge> {
    	int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.weight, e.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        int[] distHomeToX = dijkstra(adj);
        int[] distXToHome = dijkstra(reverseAdj);
        
        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, distHomeToX[i] + distXToHome[i]);
        }
        System.out.println(maxTime);
    }

    static int[] dijkstra(List<List<Edge>> adjList) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(X, 0));
        
        int[] dist = new int[N+1];
        Arrays.fill(dist, max_value);
        dist[X] = 0;

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            int to = curr.to;
            int weight = curr.weight;

            if (dist[to] < weight) continue;

            for (Edge next : adjList.get(to)) {
                if (dist[next.to] > weight + next.weight) {
                    dist[next.to] = weight + next.weight;
                    pq.offer(new Edge(next.to, dist[next.to]));
                }
            }
        }
        
        return dist;
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adj = new ArrayList<>();
        reverseAdj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
            reverseAdj.add(new ArrayList<>());
        }
        

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            adj.get(from).add(new Edge(to, T));
            reverseAdj.get(to).add(new Edge(from, T));
        }
    }
}