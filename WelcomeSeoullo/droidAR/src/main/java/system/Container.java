package system;

import util.EfficientList;

public interface Container<T> {

	public void clear();

	public void removeEmptyItems();

	public boolean isCleared();

	public int length();

	public EfficientList<T> getAllItems();

	public boolean add(T newElement);

	public boolean remove(T x);

	public boolean insert(int pos, T item);

}
