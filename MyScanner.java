import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/*

sursa: https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
+ testele practice de la PA de pe HackerRank (am facut acolo si de acolo am luat
clasa cu citirea)

*/
public class MyScanner {
	BufferedReader br = null;
	StringTokenizer st;

	public MyScanner(String filename) {
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}

	int nextInt() {
		return Integer.parseInt(next());
	}

	long nextLong() {
		return Long.parseLong(next());
	}

	double nextDouble() {
		return Double.parseDouble(next());
	}

	String nextLine() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}