import java.io.*;
import java.util.*;

public class Main {

    static int n, m, currX=0, currY=0;
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

        dfs(currX, currY);
        if (currX==n-1 && currY==m-1)
            System.out.println(1);
        else
            System.out.println(0);
    }

    public static void dfs(int x, int y){
        
        if (isPossible(x+1, y) && checkBoard(x+1, y)){
            visited[x+1][y] = true;
            currX = x+1;
            currY = y;
            dfs(x+1, y);
            visited[x+1][y] = false;
        }
        if (isPossible(x, y+1) && checkBoard(x, y+1)){
            visited[x][y+1] = true;
            currX = x;
            currY = y+1;
            dfs(x, y+1);
            visited[x][y+1] = false;
        }
        return;
    }

    public static boolean isPossible(int x, int y){
        if (0 <= x && x < n && 0 <= y && y < m)
            return true;
        return false;
    }

    public static boolean checkBoard(int x, int y){
        if (board[x][y] == 0)
            return false;
        if (visited[x][y] == true)
            return false;
        return true;
    }
}