import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static long[][] dist;
    static int N, M;
    static int A, B, C;
    static long max_value = 10_000_000_000L;
    static List<List<Edge>> adj;

    static class Edge implements Comparable<Edge> {
        int to;
        long cost;

        Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return Long.compare(this.cost, e.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 1; i <= N; i++) {
            dijkstra(i);
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(dist[i][j] == max_value ? 0 : dist[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString().trim());
    }

    static void dijkstra(int village) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(village, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            int to = curr.to;
            long cost = curr.cost;

            if (dist[village][to] < cost) continue;

            for (Edge next : adj.get(to)) {
                if (dist[village][next.to] > cost + next.cost) {
                    dist[village][next.to] = cost + next.cost;
                    pq.offer(new Edge(next.to, dist[village][next.to]));
                }
            }
        }
    }

    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        dist = new long[N+1][N+1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], max_value);
            dist[i][i] = 0;
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            adj.get(A).add(new Edge(B, C));
        }
    }
}