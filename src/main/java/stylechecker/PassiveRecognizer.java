package stylechecker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

public class PassiveRecognizer {
	public static final float PASSIVE_SENTENCE_TRESHOLD = 0.85f;

	public float determineProbThatSentenceIsPassive(String sentence) throws InvalidFormatException, IOException {
		float probability = 0.0f;

		String[] tokenizedSentence = tokenizeSentence(sentence);
		String[] tags = doPosTagingOnSentence(tokenizedSentence);

		if(containsWerden(tokenizedSentence, tags)) {
			probability += 0.6;
		}

		return probability;
	}

	private boolean containsWerden(String[] tokenizedSentence, String[] tags) {
		for(String token : tokenizedSentence) {
			CharSequence stemmedToken = stem(token);
			System.out.println(token + " => " + stemmedToken);
		}
		return false;
	}

	private CharSequence stem(CharSequence word) {
		GermanStemmer stemmer = new GermanStemmer();

		stemmer.setCurrent(word.toString());

		for(int i = 0; i < 100; i++) {
			stemmer.stem();
		}

		return stemmer.getCurrent();
	}

	private String[] tokenizeSentence(String sentence) throws InvalidFormatException, IOException {
		InputStream modelIn = new FileInputStream("src/main/resources/de-token.bin");
		TokenizerModel model = new TokenizerModel(modelIn);
		Tokenizer tokenizer = new TokenizerME(model);

		String[] result = tokenizer.tokenize(sentence);
		return result;
	}

	private String[] doPosTagingOnSentence(String[] tokenizedSentence) throws FileNotFoundException, IOException,
			InvalidFormatException {
		InputStream modelIn = new FileInputStream("src/main/resources/de-pos-maxent.bin");
		POSModel model = new POSModel(modelIn);
		POSTaggerME tagger = new POSTaggerME(model);

		String tags[] = tagger.tag(tokenizedSentence);
		return tags;
	}

	public boolean determineIfSentenceIsPassive(String sentence) throws InvalidFormatException, IOException {
		return determineProbThatSentenceIsPassive(sentence) >= PASSIVE_SENTENCE_TRESHOLD;
	}
}
