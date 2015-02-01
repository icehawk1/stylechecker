package de.mhaug.ba.stylechecker.latex;

public class PassiveRecognizer {
	public static final float PASSIVE_SENTENCE_TRESHOLD = 0.85f;

	public float determineProbThatSentenceIsPassive(String sentence) {
		return 0.0f;
	}

	public boolean determineIfSentenceIsPassive(String sentence) {
		return determineProbThatSentenceIsPassive(sentence) >= PASSIVE_SENTENCE_TRESHOLD;
	}
}
