import java.util.*;

public class Main {

    static int k, n;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        k = sc.nextInt();
        n = sc.nextInt();

        simple(1);
    }

    public static void simple(int t){
        if(t == n+1){
            printList();
            return;
        }

        for(int i=1; i<=k; i++){
            list.add(i);
            simple(t+1);
            list.remove(list.size() - 1);
        }
    }

    public static void printList(){
        for(int i=0; i<list.size(); i++)
            System.out.print(list.get(i) + " ");
        System.out.println();
    }
}