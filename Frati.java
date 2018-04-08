import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Premiu {
	public int jocuri, reviste, index;

	public Premiu(int jocuri, int reviste, int index) {
		this.jocuri = jocuri;
		this.reviste = reviste;
		this.index = index;
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

	public int getIndex() {
		return index;
	}
}

public class Frati {
	public static void main(String[] args) {
		List<Premiu> prizesForJon = new ArrayList<>();
		List<Premiu> prizesForSam = new ArrayList<>();
		MyScanner scan = new MyScanner("frati.in");
		int n = scan.nextInt();
		int jon = 0, sam = 0;
		for (int i = 0; i < n; ++i) {
			int jocuri = scan.nextInt();
			int reviste = scan.nextInt();
			Premiu lel = new Premiu(jocuri, reviste, i);
			prizesForJon.add(lel);
			prizesForSam.add(lel);
		}
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
		boolean[] visited = new boolean[n];
		boolean turnJon = true;
		int indexJon = 0, indexSam = 0;
		for (int index = 0; index < n; ++index) {
			if (index % 2 == 0) {
				while (visited[prizesForJon.get(indexJon).getIndex()]) {
					++indexJon;
				}
				visited[prizesForJon.get(indexJon).getIndex()] = true;
				jon += prizesForJon.get(indexJon).getJocuri();
			} else {
				while (visited[prizesForSam.get(indexSam).getIndex()]) {
					++indexSam;
				}
				visited[prizesForSam.get(indexSam).getIndex()] = true;
				sam += prizesForSam.get(indexSam).getReviste();
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