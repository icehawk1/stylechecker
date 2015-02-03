package de.mhaug.stylechecker.latex;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MathRemoverTest {
	private MathRemover instance;

	@Before
	public void setUp() {
	}

	@Test
	public void testStartsWith_simple() {
		String input = "abcdef";
		String regex = "a";
		boolean expected = true;
		instance = new MathRemover(input);

		Assert.assertEquals(expected, instance.startsWith(regex));
	}

	@Test
	public void testStartsWith_simple2() {
		String input = "abcdef";
		String regex = "b";
		boolean expected = false;
		instance = new MathRemover(input);

		Assert.assertEquals(expected, instance.startsWith(regex));
	}

	@Test
	public void testStartsWith_withPosition() {
		String input = "abcdef";
		String regex = "c";
		boolean expected = true;
		instance = new MathRemover(input);
		instance.position = 2;

		Assert.assertEquals(expected, instance.startsWith(regex));
	}

	@Test
	public void testStartsWith_withLookbehind() {
		String input = "abcdef";
		String regex = "(?<!b)c";
		boolean expected = false;
		instance = new MathRemover(input);
		instance.position = 2;

		Assert.assertEquals(expected, instance.startsWith(regex));
	}

	@Test
	public void testStartsWith_withLookahead() {
		String input = "abcdef";
		String regex = "d(?=e)";
		boolean expected = true;
		instance = new MathRemover(input);
		instance.position = 3;

		Assert.assertEquals(expected, instance.startsWith(regex));
	}

	@Test(timeout = 5 * 1000)
	public void testRemoveLatexCode_mathe() {
		// Prepare
		String input = "Ich bin ein Satz mit Mathezeugs $raus \\$ damit$ und \\[ das \\\\] auch \\] weg";
		String expected = "Ich bin ein Satz mit Mathezeugs  und  weg";
		instance = new MathRemover(input);

		// Operate
		String actual = instance.removeMathe();

		// Check
		Assert.assertEquals(expected, actual);
	}
}
