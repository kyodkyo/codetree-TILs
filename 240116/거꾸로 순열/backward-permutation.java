import java.util.*;

public class Main {

    static int n;
    static boolean visited[];
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visited = new boolean[n+1];

        choose(1);
    }

    public static void choose(int num){
        if (num == n+1){
            printNums();
            return;
        }

        for(int i=n; i>0; i--){
            if (visited[i])
                continue;
            
            list.add(i);
            visited[i] = true;
            choose(num+1);
            list.remove(list.size()-1);
            visited[i] = false;
        }
    }

    public static void printNums(){
        for(int x : list)
            System.out.print(x + " ");
        System.out.println();
    }
}