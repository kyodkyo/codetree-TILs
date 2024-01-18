import java.util.*;
import java.io.*;

public class Main {
    
    static int n, m, count=0;
    static int[][] board;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        board = new int[n+1][n+1];
        visited = new boolean[n+1];

        for(int i=0; i<m; i++){
            String[] line = br.readLine().split(" ");
            board[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = 1;
            board[Integer.parseInt(line[1])][Integer.parseInt(line[0])] = 1;
        }

        dfs(1);
        System.out.println(count-1);
    }

    public static void dfs(int num){
        for(int i=1; i<=n; i++){
            if(board[num][i]==1 && !visited[i]){
                count++;
                visited[i] = true;
                dfs(i);
            }
        }
    }
}