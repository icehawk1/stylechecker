package de.mhaug.stylechecker.latex;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LatexRemoverTest {
	private LatexRemover instance;

	@Before
	public void setUp() {
		instance = new LatexRemover();
	}

	@Test
	public void testRemoveLatexCode_noLatexInput() {
		// Prepare
		String input = "Ich bin ein Satz ohne LaTeX-Code";
		String expected = input;

		// Operate
		String actual = instance.removeLatexCode(input);

		// Check
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testRemoveLatexCode_singleCommand() {
		// Prepare
		String input = "Ich bin ein Satz mit \\LaTeX-Code";
		String expected = "Ich bin ein Satz mit -Code";

		// Operate
		String actual = instance.removeLatexCode(input);

		// Check
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testRemoveLatexCode_multipleCommands() {
		// Prepare
		String input = "Ich bin ein \\textit{ Satz mit LaTeX-Code} abc";
		String expected = "Ich bin ein  Satz mit LaTeX-Code abc";

		// Operate
		String actual = instance.removeLatexCode(input);

		// Check
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testRemoveLatexCode_nestedCommands() {
		// Prepare
		String input = "Ich bin ein \\textit{ Satz mit \textrm{LaTeX-Code} mehr text}";
		String expected = "Ich bin ein  Satz mit LaTeX-Code mehr text";

		// Operate
		String actual = instance.removeLatexCode(input);

		// Check
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testRemoveLatexCode_comment() {
		// Prepare
		String input = "Ich bin ein Satz mit Kommentar % \\textit{abc} \nDie Zeile muss bleiben";
		String expected = "Ich bin ein Satz mit Kommentar \nDie Zeile muss bleiben";

		// Operate
		String actual = instance.removeLatexCode(input);

		// Check
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testRemoveLatexCode_escapedComment() {
		// Prepare
		String input = "Ich bin ein Satz mit Kommentar \\% \\textit{abc} \nDie Zeile muss bleiben";
		String expected = "Ich bin ein Satz mit Kommentar % abc \nDie Zeile muss bleiben";

		// Operate
		String actual = instance.removeLatexCode(input);

		// Check
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testRemoveLatexCode_environment() {
		// Prepare
		String input = "Ich bin ein Satz mit Environment \\begin{zeugs} \nDie Zeile muss raus\\end{zeugs}";
		String expected = "Ich bin ein Satz mit Environment \\begin{zeugs} \nDie Zeile muss raus\\end{zeugs}";

		// Operate
		String actual = instance.removeLatexCode(input);

		// Check
		Assert.assertEquals(expected, actual);
	}

}
