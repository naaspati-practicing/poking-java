package sam.learn.cdi;

import javax.inject.Inject;

public class Emailer {
	private SpellChecker spellChecker;
	
	@Inject
	public Emailer(SpellChecker spellChecker) {
		this.spellChecker = spellChecker;
	}
	public void send(String msg) {
		spellChecker.check(msg);
		// send of ok
		
		System.out.println(getClass().getSimpleName()+": messages sent \n"+msg);
	}
}
