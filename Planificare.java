import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
class Utils {
	public static boolean checkForLastContest(int index, int concurs, int pauza,
	                                          List<Integer> probe) {
		int sum = 0;
		for (int i = index; i < probe.size(); ++i) {
			sum += probe.get(i);
			if (i != probe.size() - 1) {
				sum += pauza;
			}
		}

		return (concurs >= sum);
	}
} */

public class Planificare {
	public static void main(String[] args) {
		int numarProbe, durataConcurs, pauza;
		ArrayList<Integer> probe = new ArrayList<>();
		MyScanner scanner = new MyScanner("planificare.in");
		numarProbe = scanner.nextInt();
		durataConcurs = scanner.nextInt();
		pauza = scanner.nextInt();
		for (int i = 0; i < numarProbe; ++i) {
			int proba = scanner.nextInt();
			probe.add(proba);
		}
		int pierderiTotale = 0, concursuri = 0;
		int[][] matrix = new int[numarProbe][numarProbe];
		try {
			PrintWriter writer = new PrintWriter("planificare.out");
			writer.print(pierderiTotale + " " + concursuri);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}