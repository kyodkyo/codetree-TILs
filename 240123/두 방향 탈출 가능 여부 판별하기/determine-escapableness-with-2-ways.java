import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] board;
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        board = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            String[] line = br.readLine().split(" ");
            for(int j=0; j<m; j++)
                board[i][j] = Integer.parseInt(line[j]);
        }
        System.out.println(dfs(0, 0));
    }

    public static int dfs(int x, int y){
        if (x==n-1 && y==m-1)
            return 1;

        int[] dx = {0, 1};
        int[] dy = {1, 0};

        visited[x][y] = true;
        for(int i=0; i<2; i++){
            int currX = x + dx[i];
            int currY = y + dy[i];
            if (checkBoard(currX, currY)) {
                if (dfs(currX, currY) == 1)
                    return 1;
            }         
        }
        return 0;
    }

    public static boolean checkBoard(int x, int y){
        if (0 <= x && x < n && 0 <= y && y < m && board[x][y]==1 && !visited[x][y])
            return true;
        return false;
    }
}