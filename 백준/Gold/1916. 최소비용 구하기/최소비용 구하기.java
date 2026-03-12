import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static long[] dist;
    static int N, M, start, end;
    static long max_weight = 10_000_000_000L;
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
        dijkstra();
        System.out.println(dist[end]);
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            int to = curr.to;
            long cost = curr.cost;

            if (dist[to] < cost) continue; // 기존 비용보다 비싼 경우

            for (Edge next : adj.get(to)) {
                if (dist[next.to] > cost + next.cost) {
                    dist[next.to] = cost + next.cost; // minimum update
                    pq.offer(new Edge(next.to, dist[next.to]));
                }
            }
        }
    }

    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // 거리 초기화
        dist = new long[N+1];
        Arrays.fill(dist, max_weight);

        // 인접리스트 초기화
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        // input data
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            // 인접 리스트
            adj.get(from).add(new Edge(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dist[start] = 0;
    }
}