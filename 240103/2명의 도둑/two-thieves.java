import java.io.*;
import java.util.*;

public class Main {

    static int n, maxCol, maxVal, sumA=0, sumB=0, max=0;
    static int[][] board;
    static ArrayList<Integer> list = new ArrayList<>();
    static ArrayList<Integer> listA = new ArrayList<>();
    static ArrayList<Integer> listB = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        maxCol = Integer.parseInt(input[1]);
        maxVal = Integer.parseInt(input[2]);

        board = new int[n][n];
        for(int i=0; i<n; i++){
            String[] line = br.readLine().split(" ");
            for(int j=0; j<n; j++)
                board[i][j] = Integer.parseInt(line[j]);
        }

        selectA();
        System.out.println(max);
    }

    public static void selectA(){
        for(int i=0; i<n; i++){
            for(int j=0; j<=n-maxCol; j++){
                for(int k=j; k<j+maxCol; k++)
                    listA.add(board[i][k]);
                selectB(i, j);
                listA.clear();
            }
        }
    }

    public static void selectB(int x, int y){
        for(int i=x; i<n; i++){
            for(int j=0; j<=n-maxCol; j++){
                if (i==x && y-maxCol<j && j<y+maxCol)
                    continue;
                for(int k=j; k<j+maxCol; k++)
                    listB.add(board[i][k]);
                selectThings(1, 'A');
                selectThings(1, 'B');
                max = Math.max(max, sumA+sumB);
                listB.clear();
            }
        }
    }

    public static void selectThings(int t, char thief){
        if (t == maxCol+1){
            isPossible(thief);
            return;
        }

        list.add(0);
        selectThings(t+1, thief);
        list.remove(list.size()-1);

        list.add(1);
        selectThings(t+1, thief);
        list.remove(list.size()-1);
    }

    public static void isPossible(char thief){
        int count=0, sum=0;
        boolean check = true;
        
        for(int i=0; i<list.size(); i++){
            if (thief=='A' && list.get(i)==1){
                count += listA.get(i);
                if (count>maxVal){
                    check = false;
                    break;
                }
                sum += (listA.get(i) * listA.get(i));
            }
            else if (thief=='B' && list.get(i)==1){
                count += listB.get(i);
                if (count>maxVal){
                    check = false;
                    break;
                }
                sum += (listB.get(i) * listB.get(i));
            }
        }

        if (check && thief=='A')
            sumA = Math.max(sumA, sum);
        else if (check && thief=='B')
            sumB = Math.max(sumB, sum);
    }
}