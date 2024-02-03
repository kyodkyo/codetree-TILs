import java.io.*;
import java.util.*;


public class Main {
    static int n, k, m, max = 0;
    static int[][] map, points;
    static ArrayList<int[]> stones = new ArrayList<>();
    static ArrayList<int[]> selectStone = new ArrayList<>();
    static Queue<int[]> q = new LinkedList<>();
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);
        m = Integer.parseInt(input[2]);

        map = new int[n][n];
        visited = new boolean[n][n];

        for(int i=0; i<n; i++){
            String[] line = br.readLine().split(" ");
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(line[j]);
                
                if(map[i][j] == 1)
                    stones.add(new int[]{i, j});
            }
        }

        points = new int[m][2];
        for(int i=0; i<m; i++){
            String[] line = br.readLine().split(" ");
            points[i][0] = Integer.parseInt(line[0]) - 1;            
            points[i][1] = Integer.parseInt(line[1]) - 1;            
        }

        removeStone(0, 0);

        System.out.println(max);
    }

    public static void removeStone(int idx, int count){
        if (idx == stones.size()){
            if (count == m)
                max = Math.max(max, find());
            return;
        }

        selectStone.add(stones.get(idx));
        removeStone(idx+1, count+1);
        selectStone.remove(selectStone.size() - 1);

        removeStone(idx+1, count);
    }

    public static int find(){
        for(int i=0; i<m; i++){
            int[] pos = selectStone.get(i);
            map[pos[0]][pos[1]] = 0;
        }

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                visited[i][j] = false;

        for(int i=0; i<k; i++){
            int x = points[i][0];
            int y = points[i][1];
            q.add(new int[]{x, y});
            visited[x][y] = true;
        }

        bfs();

        for(int i=0; i<m; i++){
            int[] pos = selectStone.get(i);
            map[pos[0]][pos[1]] = 1;
        }

        int count = 0;
        for(boolean[] row : visited){
            for(boolean value : row){
                if (value)
                    count++;
            }
        }
        return count;
    }

    public static void bfs(){
        int[] dx = {0, 1, -1, 0};
        int[] dy = {1, 0, 0, -1};

        while (!q.isEmpty()){
            int[] pos = q.poll();

            for(int i=0; i<4; i++){
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];
                
                if (0<=nx && nx<n && 0<=ny && ny<n && !visited[nx][ny] && map[nx][ny]==0){
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }
}