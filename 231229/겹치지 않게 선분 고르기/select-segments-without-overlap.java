import java.util.*;

public class Main {

    static int n, max=0;
    static int[] tempLines;
    static int[][] lines;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        lines = new int[n][2];
        for(int i=0; i<n; i++){
            lines[i][0] = sc.nextInt();
            lines[i][1] = sc.nextInt();
        }

        chooseLines(1);
        System.out.println(max);
    }

    public static void chooseLines(int k){
        if (k == n+1){
            countLines();
            return;
        }

        list.add(0);
        chooseLines(k+1);
        list.remove(list.size()-1);

        list.add(1);
        chooseLines(k+1);
        list.remove(list.size()-1);
    }

    public static void countLines(){
        tempLines = new int[1001];

        int count = 0;
        for(int i=0; i<list.size(); i++){
            if (list.get(i) == 1){
                count++;
                for(int t=lines[i][0]; t<=lines[i][1]; t++){
                    tempLines[t]++;
                }
            }
        }
        
        if (isDuplicate(tempLines))
            max = Math.max(max, count);
    }

    public static boolean isDuplicate(int[] line){
        for(int i=1; i<=1000; i++){
            if (line[i] >= 2)
                return false;
        }
        return true;
    }
}