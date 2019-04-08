package sam.learn.cdi;

@FunctionalInterface
public interface SpellChecker {

	void check(String s);
	
	default void defaultCheck(String s) {
		System.out.println(getClass().getSimpleName()+": spelling checked \n"+s);
	} 

}
