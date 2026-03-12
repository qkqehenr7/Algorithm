import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] dist;
    static int N, M, X;
    static int T, from, to;
    static int max_value = 1_000_000;
    static List<List<Edge>> adj;

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
        for (int i = 1; i <= N; i++) {
            dijkstra(i);
        }

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, dist[i][X] + dist[X][i]);
        }
        System.out.println(maxTime);
    }

    static void dijkstra(int village) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(village, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            int to = curr.to;
            int weight = curr.weight;

            if (dist[village][to] < weight) continue;

            for (Edge next : adj.get(to)) {
                if (dist[village][next.to] > weight + next.weight) {
                    dist[village][next.to] = weight + next.weight;
                    pq.offer(new Edge(next.to, dist[village][next.to]));
                }
            }
        }
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        dist = new int[N+1][N+1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], max_value);
            dist[i][i] = 0;
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            adj.get(from).add(new Edge(to, T));
        }
    }
}