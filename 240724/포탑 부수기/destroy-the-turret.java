import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Turret implements Comparable<Turret> {
    int x, y, r, p;

    public Turret(int x, int y, int r, int p) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.p = p;
    }

    public int compareTo(Turret t) {
        if(this.p != t.p) return this.p - t.p;
        if(this.r != t.r) return t.r - this.r;
        if(this.x + this.y != t.x + t.y) return (t.x + t.y) - (this.x + this.y);
        return t.y - this.y;
    }
}

public class Main {
    public static final int MAX_N = 10;
    
    public static int[] dx = new int[]{0, 1, 0, -1};
    public static int[] dy = new int[]{1, 0, -1, 0};
    public static int[] dx2 = new int[]{0, 0, 0, -1, -1, -1, 1, 1, 1};
    public static int[] dy2 = new int[]{0, -1, 1, 0, -1, 1, 0, -1, 1};
    
    public static int n, m, k, turn;
    public static int[][] board = new int[MAX_N][MAX_N];
    public static int[][] rec = new int[MAX_N][MAX_N];
    
    public static boolean[][] vis = new boolean[MAX_N][MAX_N];
    public static int[][] backX = new int[MAX_N][MAX_N];
    public static int[][] backY = new int[MAX_N][MAX_N];
    public static boolean[][] isActive = new boolean[MAX_N][MAX_N];
    public static ArrayList<Turret> liveTurret = new ArrayList<>();
    
    public static void init() {
        turn++;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) {
                vis[i][j] = false;
                isActive[i][j] = false;
            }
    }
    
    public static void awake() {
        Collections.sort(liveTurret);
    
        Turret weakTurret = liveTurret.get(0);
        int x = weakTurret.x;
        int y = weakTurret.y;
    
        board[x][y] += n + m;
        rec[x][y] = turn;
        weakTurret.p = board[x][y];
        weakTurret.r = rec[x][y];
        isActive[x][y] = true;
    
        liveTurret.set(0, weakTurret);
    }
    
    public static boolean laserAttack() {
        Turret weakTurret = liveTurret.get(0);
        int sx = weakTurret.x;
        int sy = weakTurret.y;
        int pow = weakTurret.p;
    
        Turret strongTurret = liveTurret.get(liveTurret.size() - 1);
        int ex = strongTurret.x;
        int ey = strongTurret.y;
    
        Queue<Pair> q = new LinkedList<>();
        vis[sx][sy] = true;
        q.add(new Pair(sx, sy));
    
        boolean canAttack = false;
    
        while(!q.isEmpty()) {
            int x = q.peek().x;
            int y = q.peek().y;
            q.poll();
    
            if(x == ex && y == ey) {
                canAttack = true;
                break;
            }
    
            for(int dir = 0; dir < 4; dir++) {
                int nx = (x + dx[dir] + n) % n;
                int ny = (y + dy[dir] + m) % m;
    
                if(vis[nx][ny]) 
                    continue;
                if(board[nx][ny] == 0) 
                    continue;
    
                vis[nx][ny] = true;
                backX[nx][ny] = x;
                backY[nx][ny] = y;
                q.add(new Pair(nx, ny));
            }
        }
    
        if(canAttack) {
            board[ex][ey] -= pow;
            if(board[ex][ey] < 0) 
                board[ex][ey] = 0;
            isActive[ex][ey] = true;
    
            int cx = backX[ex][ey];
            int cy = backY[ex][ey];
    
            while(!(cx == sx && cy == sy)) {
                board[cx][cy] -= pow / 2;
                if(board[cx][cy] < 0) 
                    board[cx][cy] = 0;
                isActive[cx][cy] = true;
    
                int nextCx = backX[cx][cy];
                int nextCy = backY[cx][cy];
    
                cx = nextCx;
                cy = nextCy;
            }
        }
        return canAttack;
    }
    
    public static void bombAttack() {
        Turret weakTurret = liveTurret.get(0);
        int sx = weakTurret.x;
        int sy = weakTurret.y;
        int pow = weakTurret.p;
    
        Turret strongTurret = liveTurret.get(liveTurret.size() - 1);
        int ex = strongTurret.x;
        int ey = strongTurret.y;
    
        for(int dir = 0; dir < 9; dir++) {
            int nx = (ex + dx2[dir] + n) % n;
            int ny = (ey + dy2[dir] + m) % m;
    
            if(nx == sx && ny == sy) 
                continue;
    
            if(nx == ex && ny == ey) {
                board[nx][ny] -= pow;
                if(board[nx][ny] < 0) 
                    board[nx][ny] = 0;
                isActive[nx][ny] = true;
            }
            else {
                board[nx][ny] -= pow / 2;
                if(board[nx][ny] < 0) 
                    board[nx][ny] = 0;
                isActive[nx][ny] = true;
            }
        }
    }
    
    public static void reserve() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(isActive[i][j]) 
                    continue;
                if(board[i][j] == 0) 
                    continue;
                board[i][j]++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                board[i][j] = sc.nextInt();

        while(k-- > 0) {
            liveTurret = new ArrayList<>();
            for(int i = 0; i < n; i++)
                for(int j = 0; j < m; j++)
                    if(board[i][j] > 0) {
                        Turret newTurret = new Turret(i, j, rec[i][j], board[i][j]);
                        liveTurret.add(newTurret);
                    }

            if(liveTurret.size() <= 1) 
                break;

            init();
            awake();

            boolean isSuc = laserAttack();
            if(!isSuc) 
                bombAttack();

            reserve();
        }

        int ans = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                ans = Math.max(ans, board[i][j]);

        System.out.print(ans);
    }
}