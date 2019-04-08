package sam.learn.cdi.annoted;

import java.lang.annotation.Annotation;

import com.google.inject.AbstractModule;

import sam.learn.cdi.EnglishSpellChecker;
import sam.learn.cdi.FrenchSpellChecker;
import sam.learn.cdi.SpellChecker;

public class SpellingModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(EnglishSpellChecker.class, English.class);
		bind(FrenchSpellChecker.class, French.class);
	}
	private void bind(Class<? extends SpellChecker> implementation, Class<? extends Annotation> identifier) {
		bind(SpellChecker.class)
		 .annotatedWith(identifier)
		 .to(implementation);
	}

}
