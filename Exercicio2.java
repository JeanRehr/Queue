public class Exercicio2 {
	private static StaticQueue<Integer> f1 = new StaticQueue<Integer>(8);
	private static StaticQueue<Integer> f2 = new StaticQueue<Integer>(4);

	public static void enche() {
		for(int i = 1; i < 5; i++) {
			f1.enqueue(i);
		}
		for(int i = 5; i < 9; i++) {
			f2.enqueue(i);
		}
	}

	public static void prependFila(Queue<Integer> f1, Queue<Integer> f2) {
		StaticQueue<Integer> f3 = new StaticQueue<Integer>(f1.numElements());
		while(!f1.isEmpty())
			f3.enqueue(f1.dequeue());
		while(!f2.isEmpty())
			f1.enqueue(f2.dequeue());
		while(!f3.isEmpty())
			f1.enqueue(f3.dequeue());
	}

	public static void main(String[] args) {
		enche();
		prependFila(f1, f2);
		System.out.println(f1);
		System.out.println(f1.split(1));
		System.out.println(f1);
	}
}