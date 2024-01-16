import java.util.*;

class Point{
    int x;
    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int n, m, dis = Integer.MAX_VALUE;
    static Point[] p;
    static ArrayList<Point> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        m = sc.nextInt();
        p = new Point[n];
        for(int i=0; i<n; i++)
            p[i] = new Point(sc.nextInt(), sc.nextInt());
        
        choose(0);
        System.out.println(dis);
    }

    public static void choose(int num){
        if (list.size() == m){
            dis = Math.min(dis, checkDist());
            return;
        }

        for(int i=num; i<p.length; i++){
            list.add(p[i]);
            choose(i+1);
            list.remove(list.size()-1);
        }
    }

    public static int checkDist(){
        int max = 0;

        for(int i=0; i<list.size()-1; i++){
            for(int j=i+1; j<list.size(); j++){
                int dx = Math.abs(list.get(i).x - list.get(j).x);
                int dy = Math.abs(list.get(i).y - list.get(j).y);
                max = Math.max(max, (int)(Math.pow(dx, 2) + Math.pow(dy, 2)));
            }
        }

        return max;
    }
}