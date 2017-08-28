package kan.number2.Helpers;

public abstract class RunnableWrapper implements Runnable {

	protected int __x;
	protected int __y;
	public RunnableWrapper(int x, int y) {
		__x = x;
		__y = y;
	}
	
}
