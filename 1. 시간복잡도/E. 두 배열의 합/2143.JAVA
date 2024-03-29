import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int lower_bound(int left, int right, int key, ArrayList<Integer> as)
	{
		while(left < right)
		{
			int mid = (left + right) / 2;
			if(key > as.get(mid))
			{
				left = mid + 1;
			}
			else
			{
				right = mid;
			}
		}
		return right;
	}
	static int upper_bound(int left, int right, int key, ArrayList<Integer> as)
	{
		while(left < right)
		{
			int mid = (left + right) / 2;
			if(key >= as.get(mid))
			{
				left = mid + 1;
			}
			else
			{
				right = mid;
			}
		}
		return right;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine()); // 배열의 크기
		int N = Integer.parseInt(br.readLine()); // A 배열의 크기
		int a[] = new int[N];
		// 입력값 저장
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++)
		{
			a[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine()); // B 배열의 크기
		int b[] = new int[M];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < M; i++)
		{
			b[i] = Integer.parseInt(st.nextToken());
		}
		// 부분합 저장
		ArrayList<Integer> aa = new ArrayList<Integer>();
		for(int i = 0; i < N; i++)
		{
			int sum = 0;
			for(int j = i; j < N; j++)
			{
				sum += a[j];
				aa.add(sum);
			}
		}
		ArrayList<Integer> bb = new ArrayList<Integer>();
		for(int i = 0; i < M; i++)
		{
			int sum = 0;
			for(int j = i; j < M; j++)
			{
				sum += b[j];
				bb.add(sum);
			}
		}
		aa.sort(null);
		bb.sort(null);
		int left = 0;
		int right = bb.size();
		long result = 0;
		for(int i = 0; i < aa.size(); i++)
		{
			int low = lower_bound(left, right, T - aa.get(i), bb);
			int high = upper_bound(left, right, T - aa.get(i), bb);
			result += (long)(high - low);
		}
		bw.write(Long.toString(result) + "\n");
		bw.flush();
		bw.close();
	}
}
