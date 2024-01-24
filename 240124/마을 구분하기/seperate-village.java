import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] board;
    static boolean[][] visited;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        visited = new boolean[n][n];

        for(int i=0; i<n; i++){
            String[] line = br.readLine().split(" ");
            for(int j=0; j<n; j++)
                board[i][j] = Integer.parseInt(line[j]);
        }

        int total = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if (board[i][j]==1 && !visited[i][j]){
                    total++;
                    list.add(dfs(i, j));
                }
            }
        }
        System.out.println(total);
        
        Collections.sort(list);
        for(int t : list)
            System.out.println(t);
    }

    public static int dfs(int x, int y){
        int count = 1;
        visited[x][y] = true;

        int[] dx = {0, 1, -1, 0};
        int[] dy = {1, 0, 0, -1};

        for(int i=0; i<4; i++){
            int currX = x + dx[i];
            int currY = y + dy[i];
            if (checkBoard(currX, currY))
                count += dfs(currX, currY);
        }
        return count;
    }
    
    public static boolean checkBoard(int x, int y){
        if (0<=x && x<n && 0<=y && y<n && board[x][y]==1 && !visited[x][y])
            return true;
        return false;
    }
}