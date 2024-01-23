import java.util.*;
import java.io.*;

public class Main {

    static int n, min = Integer.MAX_VALUE;
    static int[][] board;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for(int i=0; i<n; i++){
            String[] line = br.readLine().split(" ");
            for(int j=0; j<n; j++)
                board[i][j] = Integer.parseInt(line[j]);
        }

        list.add(0);
        getSeq(0);
        System.out.println(min);
    }

    public static void getSeq(int count){
        if (count == n-1){
            min = Math.min(min, totalExp());
            return;
        }

        for(int i=1; i<n; i++){
            if (!list.contains(i)){
                list.add(i);
                getSeq(count+1);
                list.remove(list.size() - 1);
            }
        }
    }

    public static int totalExp(){
        int sum = 0;

        list.add(0);
        for(int t=0; t<list.size()-1; t++)
            sum += board[list.get(t)][list.get(t+1)];
        list.remove(list.size()-1);

        return sum;
    }
}