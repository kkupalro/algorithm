import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        
        while(T-- > 0)
        {
        	// ÆÄ½ºÄ®ÀÇ »ï°¢Çü
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            // N(C)K
            long dp[][] = new long[K+1][];
            for(int i = 0; i <= K; i++)
            {
            	dp[i] = new long[i+1];
            	dp[i][0] = 1;
            	dp[i][i] = 1;
            }
            for(int i = 1; i < dp.length; i++) {
            	for(int j = 1; j < dp[i].length-1; j++)
            	{
            		dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            	}
            }
            bw.write(dp[K][N] + "\n");
            bw.flush();
        }
        bw.close();
	}
}
