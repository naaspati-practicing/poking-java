package sam.examples;

import javax.inject.Inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;

import sam.learn.cdi.Emailer;
import sam.learn.cdi.SpellChecker;
import sam.learn.cdi.annoted.English;
import sam.learn.cdi.annoted.French;
import sam.learn.cdi.annoted.SpellingModule;

// gradle run --args Example002 
public class Example002 implements Runnable {

	@Override
	public void run() {
		Injector injector = Guice.createInjector(new SpellingModule());
		Emailer en1 = injector.getInstance(EngEmailer.class);
		Emailer fr1 = injector.getInstance(FrenchEmailer.class);
		
		en1.send("en1");
		fr1.send("fr1");
	}

	private static class EngEmailer extends Emailer {

		@Inject 
		public EngEmailer(@English SpellChecker spellChecker) {
			super(spellChecker);
		}
	}

	private static class FrenchEmailer extends Emailer {
		
		@Inject
		public FrenchEmailer( @French SpellChecker spellChecker) {
			super(spellChecker);
		}
	}
}
