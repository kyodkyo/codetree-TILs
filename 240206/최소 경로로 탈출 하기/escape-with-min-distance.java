import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> q = new LinkedList<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        visited = new boolean[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++)
                map[i][j] = sc.nextInt();
        }

        System.out.println(bfs());
    }

    public static int bfs(){
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        visited[0][0] = true;
        q.add(new int[]{0, 0});

        while(!q.isEmpty()){
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (checkMap(nx, ny)){
                    visited[nx][ny] = true;
                    map[nx][ny] = map[x][y] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        return map[n-1][m-1] > 1? map[n-1][m-1]-1 : -1;
    }

    public static boolean checkMap(int x, int y){
        if (0<=x && x<n && 0<=y && y<m && !visited[x][y] && map[x][y]==1)
            return true;
        return false;
    }
}