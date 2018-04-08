import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Premiu {
	public int jocuri, reviste;

	public Premiu(int jocuri, int reviste) {
		this.jocuri = jocuri;
		this.reviste = reviste;
	}

	public String toString() {
		return "Jocuri " + jocuri + " Reviste " + reviste + "\n";
	}

	public int getJocuri() {
		return jocuri;
	}

	public int getReviste() {
		return reviste;
	}

	public int getSuma() {
		return jocuri + reviste;
	}

	public boolean equals(Premiu premiu) {
		return ((this.getReviste() == premiu.getReviste())
				&& (this.getJocuri() == premiu.getJocuri()));
	}
}

public class Frati {
	public static void main(String[] args) {
		List<Premiu> prizesForJon = new ArrayList<>();
		List<Premiu> prizesForSam = new ArrayList<>();
		//List<Premiu> prizesForSam = new ArrayList<>();
		MyScanner scan = new MyScanner("frati.in");
		// public_tests/frati/in/0.
		int n = scan.nextInt();
		int jon = 0, sam = 0;
		for (int i = 0; i < n; ++i) {
			int jocuri = scan.nextInt();
			int reviste = scan.nextInt();
			prizesForJon.add(new Premiu(jocuri, reviste));

		}
		List<Premiu> prizesForSam = new ArrayList<>(prizesForJon);
		Collections.sort(prizesForJon, new Comparator<Premiu>() {
			public int compare(Premiu one, Premiu two) {
				if (one.getSuma() == two.getSuma()) {
					return two.getJocuri() - one.getJocuri();
				} else {
					return two.getSuma() - one.getSuma();
				}
			}
		});
		Collections.sort(prizesForSam, new Comparator<Premiu>() {
			public int compare(Premiu one, Premiu two) {
				if (one.getSuma() == two.getSuma()) {
					return two.getReviste() - one.getReviste();
				} else {
					return two.getSuma() - one.getSuma();
				}
			}
		});
		boolean turnJon = true, turnSam = false;
		Premiu prize;
		while (!prizesForJon.isEmpty() && !prizesForSam.isEmpty()) {
			if (turnJon && !prizesForJon.isEmpty()) {
				prize = prizesForJon.remove(0);
				jon += prize.getJocuri();
				prizesForSam.remove(prize);
				turnJon = false;
				turnSam = true;
			} else if (turnSam && !prizesForSam.isEmpty()) {
				prize = prizesForSam.remove(0);
				sam += prize.getReviste();
				prizesForJon.remove(prize);
				turnJon = true;
				turnSam = false;
			}
		}
		try {
			PrintWriter writer = new PrintWriter("frati.out");
			writer.print(jon + " " + sam);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}