import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 100001;
    public static final int MAX_D = 22;

    public static int n, q;
    public static int[] a = new int[MAX_N];
    public static int[] p = new int[MAX_N];
    public static int[] val = new int[MAX_N];
    public static boolean[] noti = new boolean[MAX_N];
    public static int[][] nx = new int[MAX_N][MAX_D];

    public static void init(Scanner sc) {
        for (int i = 1; i <= n; i++) 
            p[i] = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            if (a[i] > 20) a[i] = 20;
        }
        
        for (int i = 1; i <= n; i++) {
            int cur = i;
            int x = a[i];
            nx[cur][x]++;

            while (p[cur] != 0 && x != 0) {
                cur = p[cur];
                x--;
                if (x != 0) nx[cur][x]++;
                val[cur]++;
            }
        }
    }

    public static void toggle_noti(int chat) {
        if (noti[chat]) {
            int cur = p[chat];
            int num = 1;

            while (cur != 0) {
                for (int i = num; i <= 21; i++) {
                    val[cur] += nx[chat][i];
                    if (i > num) nx[cur][i - num] += nx[chat][i];
                }
                if (noti[cur]) break;
                cur = p[cur];
                num++;
            }
            noti[chat] = false;
        } else {
            int cur = p[chat];
            int num = 1;

            while (cur != 0) {
                for (int i = num; i <= 21; i++) {
                    val[cur] -= nx[chat][i];
                    if (i > num) nx[cur][i - num] -= nx[chat][i];
                }
                if (noti[cur]) break;
                cur = p[cur];
                num++;
            }
            noti[chat] = true;
        }
    }

    public static void change_power(int chat, int power) {
        int bef_power = a[chat];
        power = Math.min(power, 20);  
        a[chat] = power;

        nx[chat][bef_power]--;
        if (!noti[chat]) {
            int cur = p[chat];
            int num = 1;

            while (cur != 0) {
                if (bef_power >= num) val[cur]--;
                if (bef_power > num) nx[cur][bef_power - num]--;
                if (noti[cur]) break;
                cur = p[cur];
                num++;
            }
        }

        nx[chat][power]++;
        if (!noti[chat]) {
            int cur = p[chat];
            int num = 1;

            while (cur != 0) {
                if (power >= num) val[cur]++;
                if (power > num) nx[cur][power - num]++;
                if (noti[cur]) break;
                cur = p[cur];
                num++;
            }
        }
    }

    public static void change_parent(int chat1, int chat2) {
        boolean bef_noti1 = noti[chat1];
        boolean bef_noti2 = noti[chat2];

        if (!noti[chat1]) toggle_noti(chat1);
        if (!noti[chat2]) toggle_noti(chat2);

        int temp = p[chat1];
        p[chat1] = p[chat2];
        p[chat2] = temp;

        if (!bef_noti1) toggle_noti(chat1);
        if (!bef_noti2) toggle_noti(chat2);
    }

    public static void print_count(int chat) {
        System.out.println(val[chat]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        q = sc.nextInt();
        
        while (q-- > 0) {
            int query = sc.nextInt();
            if (query == 100) {
                init(sc);
            } else if (query == 200) {
                int chat = sc.nextInt();
                toggle_noti(chat);
            } else if (query == 300) {
                int chat = sc.nextInt();
                int power = sc.nextInt();
                change_power(chat, power);
            } else if (query == 400) {
                int chat1 = sc.nextInt();
                int chat2 = sc.nextInt();
                change_parent(chat1, chat2);
            } else if (query == 500) {
                int chat = sc.nextInt();
                print_count(chat);
            }
        }
    }
}