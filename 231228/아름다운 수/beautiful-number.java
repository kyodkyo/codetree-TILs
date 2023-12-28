import java.util.*;

public class Main {
    
    static int n, count=0;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        findNum(1);
        System.out.println(count);
    }

    public static void findNum(int pos){
        if (pos == n+1){
            isPossible();
            return;
        }
        
        for(int i=1; i<=4; i++){
            list.add(i);
            findNum(pos+1);
            list.remove(list.size() - 1);
        }
    }

    public static void isPossible(){
        boolean check = true;

        for(int i=0; i<list.size(); i++){
            if (list.get(i) == 1)
                continue;
            
            for(int j=i; j<i+list.get(i); j++){
                if (j >= list.size() || list.get(i) != list.get(j)){
                    check = false;
                    break;
                }
            }
            i += (list.get(i) - 1);
        }

        if (check)
            count++;
    }
}