import java.io.*;
import java.util.*;

public class Main {
    static int n, answer = Integer.MAX_VALUE;;
    static int[][] count;
    static boolean[][] visited;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        count = new int[n][n];
        visited = new boolean[n][n];

        String[] line = br.readLine().split(" ");
        int sx = Integer.parseInt(line[0]) - 1;
        int sy = Integer.parseInt(line[1]) - 1;
        int ex = Integer.parseInt(line[2]) - 1;
        int ey = Integer.parseInt(line[3]) - 1;

        bfs(sx, sy, ex, ey);
        if (answer == Integer.MAX_VALUE)
            answer = -1;
        
        System.out.println(answer);
    }

    public static void bfs(int sx, int sy, int ex, int ey){
        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

        q.add(new int[]{sx, sy});
        while(!q.isEmpty()){
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];

            for(int i=0; i<8; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (checkBoard(nx, ny)){
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    count[nx][ny] = count[x][y] + 1;
                }
            }
        }

        if (visited[ex][ey])
            answer = count[ex][ey];
    }

    public static boolean checkBoard(int x, int y){
        if (0<=x && x<n && 0<=y && y<n && !visited[x][y])
            return true;
        return false;
    }
}