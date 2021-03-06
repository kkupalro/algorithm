import java.io.*;
import java.util.*;

public class Main {
    static int H; // 세로, [1, 1000]
    static int W; // 가로, [1, 1000]
    static int N; // [1, 10^7]
    static long[][] dp;
    static long[][] ct;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        dp = new long[1002][1002];
        ct = new long[1002][1002];
        for (int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= W; j++) {
                ct[i][j] = 0;
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        } 

        ct[1][1] = N-1; // 테스트 케이스의 경우 시작지점(1,1)에서 3번 값변경
        for(int i=1;i<=H;i++) {
            for(int j=1;j<=W;j++)
            {
                if(ct[i][j]==0)continue;
                ct[i+1][j] += ct[i][j]/2; // 아
                ct[i][j+1] += ct[i][j]/2; // 오
                if(ct[i][j]%2 > 0) // 값변경
                {
                    if(dp[i][j] > 0) { // 아래쪽 진행시
                        ct[i][j+1]++; // 오른쪽으로 진행
                    } else { // 오른쪽 진행시
                        ct[i+1][j]++; //  아래쪽으로 진행
                    }
                }
                dp[i][j] = (dp[i][j]+ct[i][j])%2;
            }
        }
        int r=1,c=1;
        while(r<=H&&c<=W)
        {
            if(dp[r][c] > 0) {
                c++;
            }
            else {
                r++;
            }
        }
        bw.write(r+" "+c);
        bw.flush();
        bw.close();
    }
}
