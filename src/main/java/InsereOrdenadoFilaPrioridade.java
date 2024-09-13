public class InsereOrdenadoFilaPrioridade implements FilaPrioridade {

	private Pair[] fila;
	private int head;
	private int last;

	public boolean isEmpty(){
		return this.head == -1 && this.last == -1;
	}

	public boolean isFull(){
		return this.head == (this.last + 1) % this.fila.length;
	}

	public InsereOrdenadoFilaPrioridade(int capacidade) {
		this.fila = new Pair[capacidade];
		this.last = -1;
		this.head = -1;
	}
	
	// criar um Pair e inserir de forma ordenada decrescente no array.
	public void add(String elemento, int prioridade) {
		if (isFull()){
			Pair[] newFila = new Pair[2 * this.fila.length];
			for (int i = 0; i < this.fila.length; i++){
				newFila[i] = this.fila[i];
			}

			this.fila = newFila;
		}

		if (isEmpty()){
			this.head = 0;
		}

		this.last = (this.last + 1) % this.fila.length;
		this.fila[this.last] = new Pair(elemento, prioridade);
	
		int i = this.last;
		while (i > 0 && this.fila[i-1].getPrioridade() < this.fila[i].getPrioridade()){
			Pair aux = this.fila[i];
			this.fila[i] = this.fila[i-1];
			this.fila[i-1] = aux;
			i--;
		}
	}


	// remover e retornar o primeiro elemento do array, que é o de maior prioridade. lembrar manipular head e tail
	// para ser uma fila circular. assim a remoção fica O(1)
	public String removeNext() {
		if (isEmpty()) throw new RuntimeException();
		Pair pair = fila[this.head];

		if (this.head == this.last){
			this.head = -1;
			this.last = -1;
		} else {
			this.head = (this.head + 1) % this.fila.length;
		}

		return pair.getElemento();
	}

}
