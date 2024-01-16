import java.util.*;

public class Main {

    static int n;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);    
        n = sc.nextInt();

        choose(1);
    }

    public static void choose(int num){
        if (num == n+1){
            printNums();
            return;
        }

        for(int i=1; i<=n; i++){
            if (list.contains(i))
                continue;

            list.add(i);
            choose(num+1);
            list.remove(list.size()-1);
        }
    }

    public static void printNums(){
        for(int t : list)
            System.out.print(t + " ");
        System.out.println();
    }
}