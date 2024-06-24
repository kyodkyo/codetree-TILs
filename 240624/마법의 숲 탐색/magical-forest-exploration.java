import java.util.*;

public class Main {
    private static final int MAX_L = 70;

    private static int R, C, K;
    private static int[][] A = new int[MAX_L + 3][MAX_L];
    private static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    private static boolean[][] isExit = new boolean[MAX_L + 3][MAX_L];
    private static int answer = 0; 

    private static boolean inRange(int y, int x) {
        return 3 <= y && y < R + 3 && 0 <= x && x < C;
    }

    private static void resetMap() {
        for (int i = 0; i < R + 3; i++) {
            for (int j = 0; j < C; j++) {
                A[i][j] = 0;
                isExit[i][j] = false;
            }
        }
    }

    private static boolean canGo(int y, int x) {
        boolean flag = 0 <= x - 1 && x + 1 < C && y + 1 < R + 3;
        flag = flag && (A[y - 1][x - 1] == 0);
        flag = flag && (A[y - 1][x] == 0);
        flag = flag && (A[y - 1][x + 1] == 0);
        flag = flag && (A[y][x - 1] == 0);
        flag = flag && (A[y][x] == 0);
        flag = flag && (A[y][x + 1] == 0);
        flag = flag && (A[y + 1][x] == 0);
        return flag;
    }

    private static int bfs(int y, int x) {
        int result = y;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[MAX_L + 3][MAX_L];
        q.offer(new int[]{y, x});
        visit[y][x] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int k = 0; k < 4; k++) {
                int ny = cur[0] + dy[k], nx = cur[1] + dx[k];
                
                if (inRange(ny, nx) && !visit[ny][nx] && (A[ny][nx] == A[cur[0]][cur[1]] || (A[ny][nx] != 0 && isExit[cur[0]][cur[1]]))) {
                    q.offer(new int[]{ny, nx});
                    visit[ny][nx] = true;
                    result = Math.max(result, ny);
                }
            }
        }
        return result;
    }

    private static void down(int y, int x, int d, int id) {
        if (canGo(y + 1, x)) {
            down(y + 1, x, d, id);
        } else if (canGo(y + 1, x - 1)) {
            down(y + 1, x - 1, (d + 3) % 4, id);
        } else if (canGo(y + 1, x + 1)) {
            down(y + 1, x + 1, (d + 1) % 4, id);
        } else {
            if (!inRange(y-1, x-1) || !inRange(y+1, x+1)) {
                resetMap();
            } else {
                A[y][x] = id;
                for (int k = 0; k < 4; k++)
                    A[y + dy[k]][x + dx[k]] = id;
                isExit[y + dy[d]][x + dx[d]] = true;
                answer += bfs(y, x) - 3 + 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        R = scanner.nextInt();
        C = scanner.nextInt();
        K = scanner.nextInt();
        for (int id = 1; id <= K; id++) {
            int x = scanner.nextInt() - 1;
            int d = scanner.nextInt();
            down(0, x, d, id);
        }
        System.out.println(answer);
    }
}