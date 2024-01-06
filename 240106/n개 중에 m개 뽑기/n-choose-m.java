import java.util.*;

public class Main {

    static int n, m;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        comb(0, 1);        
    }

    public static void comb(int prev, int count){
        if (count == m+1){
            printList();
            return;
        }

        for(int i=prev+1; i<=n; i++){
            list.add(i);
            comb(i, count+1);
            list.remove(list.size()-1);
        }
    }

    public static void printList(){
        for(int num : list)
            System.out.print(num + " ");
        System.out.println();
    }
}