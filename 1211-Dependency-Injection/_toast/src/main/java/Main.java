public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(args.length == 0)
			System.out.println("no class specified");
		else {
			for (String s : args) {
				String cls = "sam.examples."+s;
				System.out.println("running: "+cls);
				System.out.println("---------------");
				((Runnable)Class.forName(cls).newInstance()).run();
				System.out.println();
			}
		}
	}

}
