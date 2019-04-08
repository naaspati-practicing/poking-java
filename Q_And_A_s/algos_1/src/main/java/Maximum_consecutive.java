
/**
* <a href="https://www.geeksforgeeks.org/maximum-consecutive-ones-or-zeros-in-a-binary-array/">https://www.geeksforgeeks.org/maximum-consecutive-ones-or-zeros-in-a-binary-array/</a>
*
* <pre>
* Maximum consecutive one’s (or zeros) in a binary array
*
* Given binary array, find count of maximum number of consecutive 1’s present in the array.
* </pre>
*/


public class Maximum_consecutive {
	private final int find_value;

	public Maximum_consecutive(int find_value) {
		this.find_value = find_value;
	}

	public int find(int[] array) {
		int max = 0;
		int current = 0;

		for (int i : array) {
			if(i == find_value)
				current++;
			else {
				max = Math.max(current, max);
				current = 0;
			} 
		}

		return Math.max(current, max);
	}

}
