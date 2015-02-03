package de.mhaug.stylechecker.latex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MathRemover {
	MatheStates state;
	String input;
	int position;
	String output;
	public static final String BACKSLASH_NEG_LOOKBEHIND = "(?<!\\\\)";

	public MathRemover(String input) {
		this.input = input;
		this.position = 0;
		this.state = MatheStates.OUT;
		this.output = "";
	}

	public String removeMathe() {
		/*
		 * while(input.hasNext()) { switch(state) { case OUT: if(input.hasNext(BACKSLASH_NEG_LOOKBEHIND + "\\$")) {
		 * state = MatheStates.DollarMath; input.next("\\$"); } else if(input.hasNext(BACKSLASH_NEG_LOOKBEHIND +
		 * "\\\\\\[")) { state = MatheStates.ParenMath; input.next("\\\\\\["); } else { output += input.next("."); }
		 * break; case DollarMath: if(input.hasNext(BACKSLASH_NEG_LOOKBEHIND + "\\$")) { state = MatheStates.OUT;
		 * input.next("\\$"); } else break; case ParenMath: break; } }
		 */

		return "";
	}

	boolean startsWith(String regex) {
		Matcher matcher = Pattern.compile(regex).matcher(input);
		matcher.useTransparentBounds(true);
		matcher.region(position, input.length());

		boolean result = matcher.lookingAt();
		return result;
	}
}

enum MatheStates {
	OUT, DollarMath, ParenMath
}