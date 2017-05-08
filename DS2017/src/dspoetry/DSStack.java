package dspoetry;

class DSStack{
    // Default size of stack
    final int N = 1000;
    
    // Array to store the stack's items
    String[] items;

    // pointer to the top element in the stack
    int indexOfTop;

    /**
     * Constructor
     *
     */
    public DSStack(){
	items = new String[N];
	indexOfTop = -1; // Nothing in the stack
    }


    public void push(String newItem){
	indexOfTop++;
	items[indexOfTop] = newItem;
    }


    public String pop(){
	    String rv = items[indexOfTop];
	    indexOfTop--;
	    return rv;
    }

    public boolean isEmpty(){
	return (indexOfTop == -1);
    }


}
