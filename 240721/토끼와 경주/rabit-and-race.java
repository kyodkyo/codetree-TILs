import java.util.Scanner;
import java.util.HashMap;
import java.util.PriorityQueue;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Rabbit implements Comparable<Rabbit> {
    int x, y, j, id;

    public Rabbit(int x, int y, int j, int id) {
        this.x = x;
        this.y = y;
        this.j = j;
        this.id = id;
    }

    public int compareTo(Rabbit rb) {
        if(this.j != rb.j) 
            return this.j - rb.j;
        if(this.x + this.y != rb.x + rb.y) 
            return (this.x + this.y) - (rb.x + rb.y);
        if(this.x != rb.x) 
            return this.x - rb.x;
        if(y != rb.y) 
            return this.y - rb.y;
        return this.id - rb.id;
    }
}

public class Main {
    public static final int MAX_N = 2000;
    public static int n, m, p;
    
    public static int[] id = new int[MAX_N + 1];
    public static int[] pw = new int[MAX_N + 1];
    public static int[] jumpCnt = new int[MAX_N + 1];
    public static long[] result = new long[MAX_N + 1];
    public static Pair[] point = new Pair[MAX_N + 1];
    public static HashMap<Integer, Integer> idToIdx = new HashMap<>();
    public static boolean[] isRunned = new boolean[MAX_N + 1];
    public static long totalSum;

    public static boolean cmp(Rabbit a, Rabbit b) {
        if(a.x + a.y != b.x + b.y) 
            return a.x + a.y < b.x + b.y;
        if(a.x != b.x) 
            return a.x < b.x;
        if(a.y != b.y) 
            return a.y < b.y;
        return a.id < b.id;
    }
    
    public static void init(Scanner sc) {
        n = sc.nextInt();
        m = sc.nextInt();
        p = sc.nextInt();
        for(int i = 1; i <= p; i++) {
            id[i] = sc.nextInt();
            pw[i] = sc.nextInt();
            idToIdx.put(id[i], i);
            point[i] = new Pair(1, 1);
        }
    }
    
    public static Rabbit getUpRabbit(Rabbit curRabbit, int dis) {
        Rabbit upRabbit = curRabbit;
        dis %= 2 * (n - 1);
    
        if(dis >= upRabbit.x - 1) {
            dis -= (upRabbit.x - 1);
            upRabbit.x = 1;
        }
        else {
            upRabbit.x -= dis;
            dis = 0;
        }
    
        if(dis >= n - upRabbit.x) {
            dis -= (n - upRabbit.x);
            upRabbit.x = n;
        }
        else {
            upRabbit.x += dis;
            dis = 0;
        }
    
        upRabbit.x -= dis;
    
        return upRabbit;
    }
    
    public static Rabbit getDownRabbit(Rabbit curRabbit, int dis) {
        Rabbit downRabbit = curRabbit;
        dis %= 2 * (n - 1);
    
        if(dis >= n - downRabbit.x) {
            dis -= (n - downRabbit.x);
            downRabbit.x = n;
        }
        else {
            downRabbit.x += dis;
            dis = 0;
        }
    
        if(dis >= downRabbit.x - 1) {
            dis -= (downRabbit.x - 1);
            downRabbit.x = 1;
        }
        else {
            downRabbit.x -= dis;
            dis = 0;
        }
        
        downRabbit.x += dis;
    
        return downRabbit;
    }
    
    public static Rabbit getLeftRabbit(Rabbit curRabbit, int dis) {
        Rabbit leftRabbit = curRabbit;
        dis %= 2 * (m - 1);
    
        if(dis >= leftRabbit.y - 1) {
            dis -= (leftRabbit.y - 1);
            leftRabbit.y = 1;
        }
        else {
            leftRabbit.y -= dis;
            dis = 0;
        }
    
        if(dis >= m - leftRabbit.y) {
            dis -= (m - leftRabbit.y);
            leftRabbit.y = m;
        }
        else {
            leftRabbit.y += dis;
            dis = 0;
        }
    
        leftRabbit.y -= dis;
    
        return leftRabbit;
    }
    
    public static Rabbit getRightRabbit(Rabbit curRabbit, int dis) {
        Rabbit rightRabbit = curRabbit;
        dis %= 2 * (m - 1);
    
        if(dis >= m - rightRabbit.y) {
            dis -= (m - rightRabbit.y);
            rightRabbit.y = m;
        }
        else {
            rightRabbit.y += dis;
            dis = 0;
        }
    
        if(dis >= rightRabbit.y - 1) {
            dis -= (rightRabbit.y - 1);
            rightRabbit.y = 1;
        }
        else {
            rightRabbit.y -= dis;
            dis = 0;
        }
        
        rightRabbit.y += dis;
    
        return rightRabbit;
    }
    
    public static void startRound(Scanner sc) {
        int k = sc.nextInt();
        int bonus = sc.nextInt();
        PriorityQueue<Rabbit> rabbitPq = new PriorityQueue<>();
    
        for(int i = 1; i <= p; i++) 
            isRunned[i] = false;
    
        for(int i = 1; i <= p; i++) {
            Rabbit newRabbit = new Rabbit(point[i].x, point[i].y, jumpCnt[i], id[i]);
            rabbitPq.add(newRabbit);
        }
    
        while(k-- > 0) {
            Rabbit curRabbit = rabbitPq.poll();
    
            int dis = pw[idToIdx.get(curRabbit.id)];
            Rabbit nexRabbit = new Rabbit(curRabbit.x, curRabbit.y, curRabbit.j, curRabbit.id);
            nexRabbit.x = 0;
            nexRabbit.y = 0;
    
            Rabbit upRabbit = getUpRabbit(new Rabbit(curRabbit.x, curRabbit.y, curRabbit.j, curRabbit.id), dis);
            if(cmp(nexRabbit, upRabbit)) 
                nexRabbit = upRabbit;
    
            Rabbit downRabbit = getDownRabbit(new Rabbit(curRabbit.x, curRabbit.y, curRabbit.j, curRabbit.id), dis);
            if(cmp(nexRabbit, downRabbit)) 
                nexRabbit = downRabbit;
    
    
            Rabbit leftRabbit = getLeftRabbit(new Rabbit(curRabbit.x, curRabbit.y, curRabbit.j, curRabbit.id), dis);
            if(cmp(nexRabbit, leftRabbit)) 
                nexRabbit = leftRabbit;
    
    
            Rabbit rightRabbit = getRightRabbit(new Rabbit(curRabbit.x, curRabbit.y, curRabbit.j, curRabbit.id), dis);
            if(cmp(nexRabbit, rightRabbit)) 
                nexRabbit = rightRabbit;
    
    
            nexRabbit.j++;
            rabbitPq.add(nexRabbit);
            
            int nexIdx = idToIdx.get(nexRabbit.id);
            point[nexIdx] = new Pair(nexRabbit.x, nexRabbit.y);
            jumpCnt[nexIdx]++;
    
            isRunned[nexIdx] = true;
    
            result[nexIdx] -= (nexRabbit.x + nexRabbit.y);
            totalSum += (nexRabbit.x + nexRabbit.y);
        }
    
        Rabbit bonusRabbit = new Rabbit(0, 0, 0, 0);
        while(!rabbitPq.isEmpty()) {
            Rabbit curRabbit = rabbitPq.poll();
    
            if(!isRunned[idToIdx.get(curRabbit.id)]) 
                continue;
            if(cmp(bonusRabbit, curRabbit))
                bonusRabbit = curRabbit;
        }
    
        result[idToIdx.get(bonusRabbit.id)] += bonus;
    }
    
    public static void powerUp(Scanner sc) {
        int id = sc.nextInt();
        int t = sc.nextInt();
        int idx = idToIdx.get(id);
        pw[idx] *= t;
    }
    
    public static void printResult() {
        long ans = 0;
        for(int i = 1; i <= p; i++)
            ans = Math.max(ans, result[i] + totalSum);
    
        System.out.print(ans);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();

        while(q-- > 0) {
            int query = sc.nextInt();

            if(query == 100) 
                init(sc);
            if(query == 200) 
                startRound(sc);
            if(query == 300) 
                powerUp(sc);
            if(query == 400) 
                printResult();
        }
    }
}