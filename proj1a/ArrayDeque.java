public class ArrayDeque<T> {
    T[] items;
    int size;
    int nextFirst;
    int nextLast;
    Double R= (double) (size/ items.length);

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    public void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        for (int i = 0;i<items.length;i++){
            int j = nextFirst;
            a[i] = items[j+1];
            j+=1;
            if(j>size){
                j=0;
            }
        }
        items = a;
        nextFirst = items.length-1;
        nextLast = size;
    }
    public void addFirst(T t){
        if (size == items.length) {
            resize(size *4 );
        }
        items[nextFirst] = t;
        size ++;
        nextFirst--;
        if (nextFirst<0){
            nextFirst=items.length-1;
        }
    }
    public void addLast(T t){
        if (size == items.length) {
            resize(size *4 );
        }
        items[nextLast] = t;
        size ++;
        nextLast++;
        if (nextLast>items.length-1){
            nextLast=0;
        }
    }
    public boolean isEmpty(){
        return (size == 0);
    }
    public int size(){
        return size;
    }
    public T removeFirst(){
        if (size == 0){
            return null;
        }else if(nextFirst == items.length-1) {
            T a = get(0);
            nextFirst = 0;
            size--;
            if (size > 16 && R < 0.25) {
                resize(size*4);
            }
            return a;
        }else{
            T b = get(nextFirst+1);
            nextFirst++;
            size--;
            if (size > 16 && R < 0.25) {
                resize(size*4);
            }
            return b;
        }
    }

    public T removeLast(){
        if (size == 0){
            return null;
        }else if(nextLast == 0) {
            T a = get(size-1);
            nextLast = size-1;
            size--;
            if (size > 16 && R < 0.25) {
                resize(size*4);
            }
            return a;
        }else{
            T b = get(nextLast-1);
            nextLast--;
            size--;
            if (size > 16 && R < 0.25) {
                resize(size*4);
            }
            return b;
        }
    }
    public T get(int index){
        if(index>size-1){
            return null;
        }else{
            return items[index];
        }
    }

    public void printDeque(){
        for(int i = 0; i< size; i++){
            int j = nextFirst;
            System.out.print(items[j+1]+" ");
            j++;
            if(j>size){
                j=0;
            }
        }
        System.out.println("----------------");
    }

    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[other.size];
        size = other.size();
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        for(int i = 0; i<size;i++){
            items[i] = (T) other.get(i);
        }
    }
}
