import java.io.*;
import java.util.*;

public class Main {

    static int n, m, c, maxVal, result=0;
    static int[][] board;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        c = Integer.parseInt(input[2]);

        board = new int[n][n];
        for(int i=0; i<n; i++){
            String[] line = br.readLine().split(" ");
            for(int j=0; j<n; j++)
                board[i][j] = Integer.parseInt(line[j]);
        }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                for(int x=0; x<n; x++){
                    for(int y=0; y<n; y++){
                        if (isPossible(i, j, x, y))
                            result = Math.max(result, findMax(i, j) + findMax(x, y));
                    }
                }
            }
        }
        System.out.println(result);
    }

    public static boolean isPossible(int i, int j, int x, int y){
        int end1 = j+m-1;
        int end2 = y+m-1;
        
        if (end1 >= n || end2 >= n)
            return false;
        
        if (i==x && !(end1 < y || end2 < j))
            return false;

        return true;
    }

    public static int findMax(int x, int y){
        arr = new int[m];

        for(int i=y; i<=y+m-1; i++)
            arr[i-y] = board[x][i];

        maxVal = 0;
        findMaxSum(0, 0, 0);
        
        return maxVal;
    }

    public static void findMaxSum(int idx, int sum, int value){
        if (idx == m){
            if (sum <= c)
                maxVal = Math.max(maxVal, value);
            return;
        }

        findMaxSum(idx+1, sum, value);
        findMaxSum(idx+1, sum+arr[idx], value+(arr[idx]*arr[idx]));
    }
}