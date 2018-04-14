import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Ursi {
	private static final long mod = (long) Math.pow(10, 9) + 7;

	// vad numarul de ochi din text
	private static int numberEyes(String line) {
		int count = 0;
		for (int i = 0; i < line.length(); ++i) {
			if (line.charAt(i) == '^') {
				++count;
			}
		}
		return count;
	}

	// scrierea in fisier
	private static void writeFile(long n) {
		try {
			PrintWriter writer = new PrintWriter("ursi.out");
			writer.print(n);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* fac o matrice, in care coloanele reprezinta caracterele din string
	*  iar liniile reprezinta numarul de zambete deschise pana in momentul
	*  curent
	 */
	private static long findSol(String line, int eyes) {
		int rows = line.length() + 1; // linii
		int columns = eyes; // coloane
		//line = " " + line; // dummy char ca sa iasa iterarea ok
		long[][] table = new long[columns][rows]; // tabela DP
		// construiesc cazurile de baza aka cazurile de la care pot porni
		for (int i = 0; i < columns; ++i) {
			if (i == 0) {
				table[i][0] = 1 % mod;
			} else {
				table[i][0] = 0 % mod;
			}
		}
		// construiesc restul tabelei de modalitati de reprezentare a fetelor
		for (int i = 0; i < rows - 1; ++i) {
			for (int j = 0; j < columns; ++j) {
				if (line.charAt(i) == '_') {
					table[j][i + 1] = j * table[j][i] % mod;
				} else if (line.charAt(i) == '^') {
					if (j == 0) {
						table[j][i + 1] = (j + 1) * table[j + 1][i] % mod;
					} else if (j == columns - 1) {
						table[j][i + 1] = table[j - 1][i] % mod;
					} else {
						table[j][i + 1] = (j + 1) * table[j + 1][i] % mod
										+ table[j - 1][i] % mod;
					}
				}
			}
		}
		return table[0][rows - 1];
	}

	public static void main(String[] args) {
		MyScanner scanner = new MyScanner("ursi.in");
		String line = scanner.nextLine();
		long posibilitati = 0; // numarul de posibilitati
		int numarOchi = numberEyes(line);
		System.out.println(numarOchi);
		int numarLinii = line.length() - numarOchi;
		if (numarOchi % 2 == 1) {
			writeFile(posibilitati);
		} else {
			writeFile(findSol(line, numarOchi / 2 + 3));
		}
	}
}
