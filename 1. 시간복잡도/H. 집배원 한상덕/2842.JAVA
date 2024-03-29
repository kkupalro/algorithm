package I;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static final int SIZE = 51;
	static char map[][] = new char[SIZE][SIZE];
	static int H[][] = new int[SIZE][SIZE];
	static int V[][] = new int[SIZE][SIZE];
	static int Hlist[] = new int[SIZE * SIZE];
	static int temp[];
	static int kcnt, sr, sc, minH, maxH, minK, maxK, hcnt;
	static int dr[] = {-1,-1,-1, 0, 0, 1, 1, 1}; // 좌상, 상, 우상, 좌, 우, 좌하, 하, 우하
	static int dc[] = {-1, 0, 1,-1, 1,-1, 0, 1}; // '\ , ^, /',  <, >, ./,  v, \.
	static int bfs(int l, int h)
	{
		if(H[sr][sc] < l || H[sr][sc] > h) return 0;
		int ret = 0, cr, cc;
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				V[i][j] = 0;
			}
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(sr);
		q.add(sc);
		V[sr][sc] = 1;
		while(!q.isEmpty())
		{
			cr = q.poll();
			cc = q.poll();
			for(int k = 0, nr, nc; k < 8; k++)
			{
				nr = cr + dr[k];
				nc = cc + dc[k];
				if(nr < 0 || nr == N || nc < 0 || nc == N) continue;
				if(V[nr][nc] == 1) continue;
				if(H[nr][nc] < l || H[nr][nc] > h)
				{
					if(map[nr][nc] == 'K') return 0;
					continue;
				}
				V[nr][nc] = 1; // 방문
				q.add(nr);
				q.add(nc);
				if(map[nr][nc] == 'K') ret++;
			}
		}
		return ret;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		sr = sc = 1;
		hcnt = kcnt = 0;
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			map[i] = st.nextToken().toCharArray();
			for(int j = 0; j < N; j++)
			{
				if(map[i][j] == 'K') {
					kcnt++;
				}
				else if(map[i][j] == 'P') {
					sr = i; sc = j;
				}
			}
		}
		minK = minH = 100001;
		maxK = maxH = -1;
		temp = new int[N*N];
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++)
			{
				H[i][j] = Integer.parseInt(st.nextToken());
				temp[hcnt++] = H[i][j];
				if(H[i][j] < minH) minH = H[i][j];
				if(H[i][j] > maxH) maxH = H[i][j];
				if(map[i][j] != '.' && minK > H[i][j]) minK = H[i][j];
				if(map[i][j] != '.' && maxK < H[i][j]) maxK = H[i][j];
			}
		}
		Arrays.sort(temp);
		
		hcnt = 0;
		Hlist[hcnt++] = temp[0];
		for(int i = 1; i < N * N; i++)
		{
			if(Hlist[hcnt - 1] == temp[i]) continue;
			Hlist[hcnt++] = temp[i];
		}
		int result = 1000001;
		for(int i = 0, left, right, mid; i < hcnt; i++)
		{
			left = i;
			right = hcnt - 1;
			while(left <= right)
			{
				mid = (left + right) / 2;
				if(kcnt  == bfs(Hlist[i], Hlist[mid]))
				{
					if(Hlist[mid] - Hlist[i] < result) result = Hlist[mid] - Hlist[i];
					right = mid - 1;
				}
				else 
					left = mid + 1;
			}
		}
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
}
