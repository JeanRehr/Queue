public class Exercicio3 {
	private static StaticQueue<Character> f1 = new StaticQueue<Character>(5);

	public static void encheChar(Queue<Character> f) {
		f.enqueue('a');
		f.enqueue('a');
		f.enqueue('a');
		f.enqueue('b');
		f.enqueue('c');
	}

	public static void exterminateFromQueue(Queue<Character> f, char element) {
		int n = f.numElements();
		for (int i = 0; i < n; i++) {
			char var = f.dequeue();
			if (var == element) {
			} else
				f.enqueue(var);
		}

		System.out.println(f);
	}

	public static void main(String[] args) {
		encheChar(f1);
		f1.flip();
		exterminateFromQueue(f1, 'a');
	}
}