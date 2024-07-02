import java.util.*;

public class Main {
    public static int L, Q;

    static class Query {
        public int cmd, t, x, n;
        public String name;

        public Query(int cmd, int t, int x, String name, int n) {
            this.cmd = cmd;
            this.t = t;
            this.x = x;
            this.name = name;
            this.n = n;
        }
    }

    public static List<Query> queries = new ArrayList<>();
    public static Set<String> names = new HashSet<>();
    public static Map<String, List<Query>> p_queries = new HashMap<>();
    public static Map<String, Integer> entry_time = new HashMap<>();
    public static Map<String, Integer> position = new HashMap<>();
    public static Map<String, Integer> exit_time = new HashMap<>();

    public static boolean cmp(Query q1, Query q2) {
        if(q1.t != q2.t)
            return q1.t < q2.t;
        return q1.cmd < q2.cmd;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        L = sc.nextInt();
        Q = sc.nextInt();
        for(int i = 0; i < Q; i++) {
            int cmd = -1;
            int t = -1, x = -1, n = -1;
            String name = "";
            cmd = sc.nextInt();
            if(cmd == 100) {
                t = sc.nextInt();
                x = sc.nextInt();
                name = sc.next();
            }
            else if(cmd == 200) {
                t = sc.nextInt();
                x = sc.nextInt();
                name = sc.next();
                n = sc.nextInt();
            } 
            else 
                t = sc.nextInt();

            queries.add(new Query(cmd, t, x, name, n));

            if(cmd == 100)
                p_queries.computeIfAbsent(name, k -> new ArrayList<>()).add(new Query(cmd, t, x, name, n));
            else if(cmd == 200) {
                names.add(name);
                entry_time.put(name, t);
                position.put(name, x);
            }
        }

        for(String name : names) {
            exit_time.put(name, 0);

            for(Query q: p_queries.get(name)) {
                int time_to_removed = 0;
                if(q.t < entry_time.get(name)) {
                    int t_sushi_x = (q.x + (entry_time.get(name) - q.t)) % L;
                    int additionl_time = (position.get(name) - t_sushi_x + L) % L;
                    time_to_removed = entry_time.get(name) + additionl_time;
                }
                else {
                    int additionl_time = (position.get(name) - q.x + L) % L;
                    time_to_removed = q.t + additionl_time;
                }
                exit_time.put(name, Math.max(exit_time.get(name), time_to_removed));
                queries.add(new Query(111, time_to_removed, -1, name, -1));
            }
        }

        for(String name : names)
            queries.add(new Query(222, exit_time.get(name), -1, name, -1));

        queries.sort((q1, q2) -> cmp(q1, q2) ? -1 : 1);

        int people_num = 0, sushi_num = 0;
        for(int i = 0; i < queries.size(); i++) {
            if(queries.get(i).cmd == 100)
                sushi_num++;
            else if(queries.get(i).cmd == 111) 
                sushi_num--;
            else if(queries.get(i).cmd == 200) 
                people_num++;
            else if(queries.get(i).cmd == 222) 
                people_num--;
            else
                System.out.println(people_num + " " + sushi_num);
        }
    }
}