import java.io.*;
import java.util.*;

public class Main {

    static int n, max = 0;
    static int[][] board;
    static boolean[] visited;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        board = new int[n][n];

        for(int i=0; i<n; i++){
            String[] temp = br.readLine().split(" ");
            for(int j=0; j<n; j++)
                board[i][j] = Integer.parseInt(temp[j]);
        }

        choose(0);
        System.out.println(max);
    }

    public static void choose(int row){
        if (row == n){
            max = Math.max(max, checkMinNum());
            return;
        }

        for(int i=0; i<n; i++){
            if (visited[i])
                continue;

            visited[i] = true;
            list.add(board[row][i]);
            choose(row+1);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }

    public static int checkMinNum(){
        int min = 10000;
        for(int c : list)
            min = Math.min(min, c);
        return min;
    }
}