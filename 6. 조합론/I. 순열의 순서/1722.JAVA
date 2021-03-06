import java.io.*;
import java.util.*;

public class Main {

    static int N; // 1 ≤ N ≤ 20
    static int OP; // 1 or 2
    static int[] A; // OP 2일 경우 몇번째 수열
    static int[] ans; // OP 1일 경우 K번째 수열을 나타내는 N개의 수
    static int[] visit;
    static long K;
    static long fac[]; // N!

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        A = new int[21];
        ans = new int[21];
        visit = new int[21];
        fac = new long[21];

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        OP = Integer.parseInt(st.nextToken());
        // N! 계산
        fac[0] = 1;
        for (int i = 1; i <= N; i++) {
            fac[i] = fac[i - 1] * i;
        }
        if (OP == 1) {
            K = Long.parseLong(st.nextToken());
            for (int i = 1; i <= N; i++) {
                int cnt = 1;
                for (int j = 1; j <= N; j++) {
                    if (visit[j] > 0) {
                        continue;
                    }
                    if (K <= cnt * fac[N - i]) {
                        K -= ((cnt - 1) * fac[N - i]);
                        ans[i] = j;
                        visit[j] = 1;
                        break;
                    }
                    cnt++;
                }
            }
            // 출력 부문
            for (int i = 1; i <= N; i++){
                bw.write(ans[i] + " ");
            }
        } else {

            K = 1;

            for (int i = 1; i <= N; i++) {
                A[i] = Integer.parseInt(st.nextToken());

                int cnt = 0;
                for (int j = 1; j < A[i]; j++) {
                    if (visit[j] == 0) cnt++;
                }

                K += cnt * fac[N - i];
                visit[A[i]] = 1;
            }

            bw.write(K + " ");
        }
        bw.flush();
        bw.close();

    }
}
