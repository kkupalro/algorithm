package B;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 수열 크기
		int M = Integer.parseInt(st.nextToken()); // 합이 같아야 하는 수
		st = new StringTokenizer(br.readLine(), " ");
		int matrix[] = new int[N+1];
		for(int i = 1; i <= N; i++)
		{
			matrix[i] = Integer.parseInt(st.nextToken());
		}
		int sum = 0, cnt = 0, tail = 1;
		int result = Integer.MAX_VALUE;
		for(int head = 1; head <= N; head++)
		{
			sum += matrix[head];
			cnt++;
			while(sum >= M)
			{
				result = Math.min(result, cnt);
				sum -= matrix[tail++];
				cnt--;
			}
		}
		if(result == Integer.MAX_VALUE)
		{
			bw.write("0\n");
		}
		else 
		{
			bw.write(Integer.toString(result) + "\n");
		}
		bw.flush();
		bw.close();
	}
}
