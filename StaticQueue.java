// Implementacao de uma Fila com armazenamento estatico,
// baseado em array.
public class StaticQueue<E> implements Queue<E> {
	protected int first;
	protected int last;

	protected E elements[];

	@SuppressWarnings("unchecked")
	public StaticQueue(int maxSize) {
		elements = (E[])new Object[maxSize];
		first = last = -1;
	}

	public boolean isEmpty() {
		return first == -1;
	}

	public boolean isFull() {
		// se o primeiro elemento eh igual ao ultimo + 1 / pelo
		// resto da divisao do array
		if (first == ((last + 1) % elements.length))
			return true;
		else
			return false;
	}

	public int numElements() {
		if (isEmpty())
			return 0;
		else {
			int n = elements.length; // p/ legibilidade da expressao abaixo
			return ((n + last - first) % n) + 1;
		}
	}

	public void enqueue(E element) throws OverflowException {
		if (isFull())
			throw new OverflowException();
		else {
			if (last == -1)
				first = last = 0;
			else
				last = (last + 1) % elements.length;
			elements[last] = element;
		}
	}

	public E dequeue() throws UnderflowException {
		if (isEmpty())
			throw new UnderflowException();
		E element = elements[first];
		elements[first] = null; // p/ coleta de lixo
		if (first == last)
			first = last = -1;
		else
			first = (first + 1) % elements.length;

		return element;
	}

	public E front() throws UnderflowException {
		if (isEmpty())
			throw new UnderflowException();
		return elements[first];
	}

	public E back() throws UnderflowException {
		if (isEmpty())
			throw new UnderflowException();
		return elements[last];
	}

	// exercicio 4
	public boolean contains(E element) {
		for (int i = 0; i < elements.length; i++) {
			if(elements[i] == element)
				return true;
			else if(elements[i].equals(element))
				return true;
		}
		return false;
	}

	// exercicio 5
	public void flip() {
		int i = 0;
		int j = elements.length;
		@SuppressWarnings("unchecked")
		E[] aux = (E[]) new Object[j];

		while (!this.isEmpty()) {
			aux[i] = this.dequeue();
			i++;
		}
		i--;

		while (i > -1) {
			this.enqueue(aux[i]);
			i--;
		}
	}

	// exercicio 6
	public void enqueue(StaticQueue<E> element) throws OverflowException {
		if (isFull())
			throw new OverflowException();
		else if(element.numElements() > elements.length - numElements()) {
			throw new OverflowException();
		}
		
		int n = numElements();
		for (int i = 0; i < n; i++) {
			enqueue(element.dequeue());
		}
	}

	// exercicio 7
	public void enqueueWithPriority(E element) {
		StaticQueue<E> aux = new StaticQueue<E>(this.elements.length + 1);

		aux.enqueue(element);

		while (!this.isEmpty())
			aux.enqueue(this.dequeue());
		while (!aux.isEmpty())
			this.enqueue(aux.dequeue());
	}

	// exercicio 8
	public boolean equals(StaticQueue<E> f) {
		if (this.numElements() != f.numElements())
			return false;

		StaticQueue<E> aux1 = new StaticQueue<E>(this.numElements());
		StaticQueue<E> aux2 = new StaticQueue<E>(this.numElements());
		E a1 = (E) new Object();
		E a2 = (E) new Object();

		boolean flag = true;

		while (this.isEmpty()) {
			a1 = this.dequeue();
			a2 = f.dequeue();

			if (!a1.equals(a2)) 
				flag = false;

			aux1.enqueue(a1);
			aux2.enqueue(a2);
		}

		while (aux2.isEmpty()) {
			this.enqueue(aux1.dequeue());
			f.enqueue(aux2.dequeue());
		}


		return flag;

	}

	// exercicio 9 
	public StaticQueue<E> clone(StaticQueue<E> lista) {
		int n = lista.numElements();
		StaticQueue<E> clone = new StaticQueue<E>(n);

		for (int i = 0; i < n; i++) {
			clone.enqueue(lista.dequeue());
		}

		System.out.println(clone.toString());
		return clone;
	}

	// exercicio 11
	public Queue<E> split(E element) {
		StaticQueue<E> aux1 = new StaticQueue<E>(elements.length);
		StaticQueue<E> aux2 = new StaticQueue<E>(elements.length);
		E a1 = (E) new Object();
		boolean flag = false;

		while (!this.isEmpty()) {
			a1 = this.dequeue();
			if (!flag)
				aux1.enqueue(a1);
			if (flag)
				aux2.enqueue(a1);
			if (a1.equals(element))
				flag = true;

			while (!aux1.isEmpty())
				this.enqueue(aux1.dequeue());
		}
		
		return aux2;
	}

	// exercicio 12
	public void moveToBackAllOccurrencesOf(E element) {
		StaticQueue<E> aux1 = new StaticQueue<E>(elements.length);
		StaticQueue<E> aux2 = new StaticQueue<E>(elements.length);
		E a1 = (E) new Object();

		while (!this.isEmpty()) {
			a1 = this.dequeue();
			if (a1.equals(element))
				aux2.enqueue(a1);
			else
				aux1.enqueue(a1);
		}

		while (!aux1.isEmpty())
			this.enqueue(aux1.dequeue());
		while (!aux2.isEmpty())
			this.enqueue(aux2.dequeue());
	}

	// exercicio 13
	public void ensureCapacity(int capacity) {
		StaticQueue<E> aux1 = new StaticQueue<E>(elements.length);
		int oldCapacity = this.elements.length;

		while (!this.isEmpty())
			aux1.enqueue(this.dequeue());

		this.elements = (E[]) new Object[oldCapacity + capacity];

		while (!aux1.isEmpty())
			this.enqueue(aux1.dequeue());
	}

	public String toString() {
		if (isEmpty())
			return "[Empty]";
		else {
			String s = "[" + elements[first];
			int n = numElements();
			for (int i = 1; i < n; i++) {
				int k = (first + i) % elements.length;
				s += ", " + elements[k];
			}
			s += "]";
			return s;
		}
	}
}
