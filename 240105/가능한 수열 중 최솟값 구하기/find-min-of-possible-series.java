import java.util.*;

public class Main {

    static int n;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        makeNums(1);
    }

    public static void makeNums(int num){
        if(num == n+1){
            printList();
            System.exit(0);
        }

        for(int i=4; i<=6; i++){
            list.add(i);
            if(isPossible())
                makeNums(num+1);
            list.remove(list.size()-1);
        }
    }

    public static boolean isPossible(){
        int len = 1;

        while(true){
            int start1 = list.size() - len;
            int end1 = list.size() - 1;
            int start2 = start1 - len;
            int end2 = start1 - 1;

            if (start2 < 0)
                break;

            if (isSame(start1, end1, start2, end2))
                return false;
            
            len++;
        }         

        return true;     
    }

    public static boolean isSame(int start1, int end1, int start2, int end2){
        for(int i=0; i<=end1-start1; i++){
            if (list.get(start1 + i) != list.get(start2 + i))
                return false;
        }
        return true;
    }

    public static void printList(){
        for(int n : list)
            System.out.print(n);
    }
}