import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] board;
    static boolean[][] visited;
    static ArrayList<Integer> list = new ArrayList<>();
    static int[] dx = {0, 1, -1, 0};
    static int[] dy = {1, 0, 0, -1};

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

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j])
                    list.add(dfs(i, j, board[i][j]));
            }
        }

        int max = 0, count = 0;
        for(int num : list){
            if (num >= 4)
                count++;
            max = Math.max(max, num);

        }
        System.out.printf("%d %d", count, max);
    }

    public static int dfs(int x, int y, int num){
        visited[x][y] = true;

        int count = 1;
        for(int i=0; i<4; i++){
            int currX = x + dx[i];
            int currY = y + dy[i];
            
            if (checkBoard(currX, currY, num))
                count += dfs(currX, currY, num);
        }

        return count;
    }

    public static boolean checkBoard(int x, int y, int num){
        if (0<=x && x<n && 0<=y && y<n && board[x][y]==num && !visited[x][y])
            return true;
        return false;
    }
}