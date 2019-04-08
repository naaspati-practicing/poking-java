package sam.learn.cdi;

import javax.inject.Named;

@Named
public class FrenchSpellChecker implements SpellChecker {

	@Override
	public void check(String s) {
		// TODO Auto-generated method stub
		defaultCheck(s);
	}

}
