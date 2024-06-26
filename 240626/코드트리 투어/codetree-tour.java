import java.util.*;

public class Main {
    final static int INF = 0x7fffffff, MAX_N = 2000, MAX_ID = 30005;
    static int N, M, S, A[][] = new int[MAX_N][MAX_N], D[] = new int[MAX_N];
    static boolean[] isMade = new boolean[MAX_ID], isCancel = new boolean[MAX_ID];

    static class Package implements Comparable<Package> {
        int id, revenue, dest, profit;

        public Package(int id, int revenue, int dest, int profit) {
            this.id = id;
            this.revenue = revenue;
            this.dest = dest;
            this.profit = profit;
        }

        @Override
        public int compareTo(Package o) {
            if (this.profit == o.profit)
                return Integer.compare(this.id, o.id);
            return Integer.compare(o.profit, this.profit);
        }
    }

    static PriorityQueue<Package> pq = new PriorityQueue<>();

    static void dijkstra() {
        boolean[] visit = new boolean[N];
        Arrays.fill(D, INF);
        D[S] = 0;

        for (int i=0; i<N-1; i++) {
            int v = 0, minDist = INF;

            for (int j=0; j<N; j++) {
                if (!visit[j] && minDist > D[j]) {
                    v = j;
                    minDist = D[j];
                }
            }

            visit[v] = true;
            for (int j=0; j<N; j++) {
                if (!visit[j] && D[v] != INF && A[v][j] != INF && D[j] > D[v] + A[v][j])
                    D[j] = D[v] + A[v][j];
            }
        }
    }

    static void buildLand(Scanner sc) {
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i=0; i<N; i++) {
            Arrays.fill(A[i], INF);
            A[i][i] = 0;
        }

        for (int i=0; i<M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            A[u][v] = Math.min(A[u][v], w);
            A[v][u] = Math.min(A[v][u], w);
        }
    }

    static void addPackage(int id, int revenue, int dest) {
        isMade[id] = true;
        int profit = revenue - D[dest];
        pq.offer(new Package(id, revenue, dest, profit));
    }

    static void cancelPackage(int id) {
        if (isMade[id]) isCancel[id] = true;
    }

    static int sellPackage() {
        while (!pq.isEmpty()) {
            Package p = pq.peek();

            if (p.profit < 0)
                break;

            pq.poll();
            if (!isCancel[p.id]) {
                return p.id;
            }
        }
        return -1;
    }

    static void changeStart(Scanner sc) {
        S = sc.nextInt();
        dijkstra();
        List<Package> packages = new ArrayList<>();

        while (!pq.isEmpty())
            packages.add(pq.poll());

        for (Package p : packages)
            addPackage(p.id, p.revenue, p.dest);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Q = sc.nextInt();

        while (Q-- > 0) {
            int T = sc.nextInt();

            switch (T) {
                case 100:
                    buildLand(sc);
                    dijkstra();
                    break;
                case 200:
                    int id = sc.nextInt();
                    int revenue = sc.nextInt();
                    int dest = sc.nextInt();
                    addPackage(id, revenue, dest);
                    break;
                case 300:
                    int cancelId = sc.nextInt();
                    cancelPackage(cancelId);
                    break;
                case 400:
                    System.out.println(sellPackage());
                    break;
                case 500:
                    changeStart(sc);
                    break;
            }
        }
        sc.close();
    }
}