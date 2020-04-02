 // Interface que define o comportamento de uma Fila.
public interface Queue<E> {
	public boolean isEmpty();

	public boolean isFull();

	public int numElements();

	// Insere um elemento na fila
	public void enqueue(E element) throws OverflowException;

	// Retira um elemento da fila
	public E dequeue() throws UnderflowException;

	// Retorna o primeiro elemento da fila
	public E front() throws UnderflowException;

	// Retorna o ultimo elemento da fila
	public E back() throws UnderflowException;
}
