package sam.examples;

import javax.inject.Inject;

import com.google.inject.Guice;
import com.google.inject.Injector;

import sam.learn.cdi.Emailer;
import sam.learn.cdi.EnglishSpellChecker;

//gradle run --args Example002
public class Example001 implements Runnable {
	
	@Override
	public void run() {
		Injector injector = Guice.createInjector();
		Emailer emailer = injector.getInstance(Emailer2.class);
		
		emailer.send("msg");
	}
	
	private static class Emailer2 extends Emailer {
		@Inject
		public Emailer2(EnglishSpellChecker spellChecker) {
			super(spellChecker);
		}
		
	}
}
