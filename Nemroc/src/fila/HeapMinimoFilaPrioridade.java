package fila;

import excecoes.HeapUnderflowException;
import excecoes.AtualizacaoPrioridadeException;

public class HeapMinimoFilaPrioridade extends MinHeap implements FilaPrioridadeMinima {
	public HeapMinimoFilaPrioridade() {
		super();
	}

	protected void exchange(int i, int j) {
		((Handle) array[i]).index = j;
		((Handle) array[j]).index = i;
		super.exchange(i, j);
	}

	public Object insert(ElementoDinamico x) {
		if (array == null) {
			array = new Comparable[1];
			heapSize = 0;
		} else if (heapSize >= array.length) {
			Comparable[] temp = new Comparable[heapSize * 2];

			for (int i = 0; i < heapSize; i++)
				temp[i] = array[i];

			array = temp;
		}
		Handle handle = new Handle(heapSize, x);
		array[heapSize] = handle;
		heapSize++;
		bubbleUp(heapSize - 1);
		return handle;
	}

	public ElementoDinamico minimum() throws HeapUnderflowException {
		if (heapSize > 0)
			return ((Handle) array[0]).info;
		else {
			throw new HeapUnderflowException();
		}
	}

	public ElementoDinamico extractMin() {
		if (heapSize < 1)
			throw new HeapUnderflowException();
		ElementoDinamico min = ((Handle) array[0]).info;

		array[0] = array[heapSize - 1];
		((Handle) array[0]).index = 0;
		array[heapSize - 1] = null;
		heapSize--;

		heapify(0);

		return min;
	}

	public void decreaseKey(Object element, Comparable newKey)
			throws AtualizacaoPrioridadeException {
		Handle handle = (Handle) element;

		if (newKey.compareTo(handle.info.getKey()) > 0)
			throw new AtualizacaoPrioridadeException();

		handle.info.setKey(newKey);
		bubbleUp(handle.index);
	}

	private void bubbleUp(int i) {
		while (i > 0 && array[parent(i)].compareTo(array[i]) > 0) {
			exchange(i, parent(i));
			i = parent(i);
		}
	}
}