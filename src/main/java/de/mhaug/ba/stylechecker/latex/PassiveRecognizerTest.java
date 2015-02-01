package de.mhaug.ba.stylechecker.latex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PassiveRecognizerTest {
	private PassiveRecognizer instance;

	@Before
	public void setUp() throws Exception {
		instance = new PassiveRecognizer();
	}

	@Test
	public void testExamples() throws FileNotFoundException {
		Scanner examples = new Scanner(new File("src/test/resources/PassivAktiv.txt"));

		while(examples.hasNextLine()) {
			String line = examples.nextLine();
			// Skip comments
			if(line.startsWith("#")) {
				continue;
			}
			String[] currentExample = line.split("\\|");
			currentExample[0] = currentExample[0].trim();
			currentExample[1] = currentExample[1].trim();

			boolean expected = Boolean.parseBoolean(currentExample[1]);
			float probability = instance.determineProbThatSentenceIsPassive(currentExample[0]);
			boolean actual = probability >= PassiveRecognizer.PASSIVE_SENTENCE_TRESHOLD;

			Assert.assertEquals("Der Satz '" + currentExample[0] + "' wurde falsch erkannt. Wahrscheinlichkeit="
					+ probability, expected, actual);
		}

		examples.close();
	}
}
