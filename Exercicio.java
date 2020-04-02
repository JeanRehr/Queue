public class Exercicio {
	static StaticQueue<Character> f1 = new StaticQueue<Character>(5);

	public static void encher() {
		f1.enqueue('e');
		f1.enqueue('d');
		f1.enqueue('c');
		f1.enqueue('b');
		f1.enqueue('a');
	}

	public static <E> void invertLetters(Queue<E> f) {
		StaticStack<E> aux = new StaticStack<E>(f.numElements());

		while (!f.isEmpty())
			aux.push(f.dequeue());

		while (!aux.isEmpty())
			f.enqueue(aux.pop());
	}

	public static <E> void invert(Queue<E> f) {
		if (f.isEmpty())
			return;

		E a1 = (E) new Object();
		a1 = f.dequeue();

		invert(f);

		f.enqueue(a1);
	}

	public static Character[] invertLetters(StaticQueue<Character> letters, String word) {
		if (word.length() != letters.numElements())
			return null;

		StaticStack<Character> aux1 = new StaticStack<Character>(letters.numElements());
		Character[] c1 = new Character[word.length()];
		char c;
		boolean flag = true;

		for (int i = 0; i < word.length(); i++) {
			c = letters.dequeue();

			if (c != word.charAt(i))
				flag = false;

			letters.enqueue(c);
		}

		if (flag) {
			while (!aux1.isFull()) {
				c = letters.dequeue();
				aux1.push(c);
				letters.enqueue(c);
			}


			int i = 0;
			while(!aux1.isEmpty()) {
				c1[i] = aux1.pop();
				i++;
			}

			// so pra teste
			for (int j = 0; j < c1.length; j++) {
				System.out.println(c1[j]);
			}

			return c1;
		}

		return null;
	}

	public static void main(String[] args) {
		encher();
		String word = "edcba";
		invertLetters(f1, word);
	}
}