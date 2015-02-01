package de.mhaug.ba.stylechecker.latex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private static final int MAX_WORD_LENGTH = 20;
	private static final int MAX_SENTENCE_LENGTH = 25;
	File batxt;
	Scanner bascanner;
	Scanner stdin;

	public static void main(String[] args) throws FileNotFoundException {
		Main main = new Main();
		main.findLongWords();
	}

	public Main() throws FileNotFoundException {
		batxt = new File("C:\\Users\\HAM\\myrepo\\bachelorarbeit\\ba.txt");
		bascanner = new Scanner(batxt);
		stdin = new Scanner(System.in);
	}

	public void findLongWords() {
		bascanner.useDelimiter("[-_\\s]+");

		while(bascanner.hasNext()) {
			String wort = bascanner.next();

			if(wort.length() > MAX_WORD_LENGTH && istEchtesWort(wort)) {
				System.out.println(wort.length() + " Zeichen:");
				System.out.println(wort.trim());
				System.out.println();
				stdin.nextLine();
			}
		}
	}

	public void findLongSentences() throws FileNotFoundException {
		bascanner.useDelimiter("[\\w)“][.!?:]\\s");

		while(bascanner.hasNext()) {
			String satz = bascanner.next();

			String[] woerter = satz.split(" ");

			if(woerter.length > MAX_SENTENCE_LENGTH) {
				System.out.println(woerter.length + " Wörter:");
				System.out.println(satz.trim());
				System.out.println();
				stdin.nextLine();
			}
		}
	}

	private boolean istEchtesWort(String wort) {
		return !wort.contains(":") && !wort.contains("/") && !wort.contains(".") && !wort.contains("(");
	}
}
