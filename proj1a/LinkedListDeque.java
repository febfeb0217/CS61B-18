public class LinkedListDeque<T> {
    private IntNode sentinel;
    private IntNode last;
    public int size;

    public LinkedListDeque(){
        sentinel = new IntNode(null,null,null);
        sentinel.next =last;
        last.prev = sentinel;
        size = 0;
    }

    public class IntNode{
        public IntNode prev;
        public T item;
        public IntNode next;
        public IntNode(IntNode prev, T item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }
    public void addFirst(T t){
        sentinel.next= new IntNode(sentinel,t,sentinel.next);
        size ++;
    }

    public void addLast(T t){
        last.next = new IntNode(last,t,sentinel);
        last = last.next;
        size ++;
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
        }else{
            IntNode p = sentinel.next;
            sentinel.next =p.next;
            size--;
            return p.item;
        }
    }

    public T removeLast(){
        if (size == 0){
            return null;
        }else{
            IntNode p = last;
            last = p.prev;
            size--;
            return p.item;
        }

    }

    public T get(int index){
        if(index>size-1){
            return null;
        }else{
            IntNode b=sentinel;
            for(int i = 0; i<=index;i++){
                b = b.next;
            }
            return b.item;
        }
    }

    public void printDeque(){
        IntNode b=sentinel;
        for(int i = 0; i< size+1; i++){
            b=b.next;
            System.out.print(b.item+" ");
        }
        System.out.println("----------------");
    }

    public LinkedListDeque(LinkedListDeque other){
        sentinel = new IntNode(null,null,null);
        sentinel.prev =sentinel;
        sentinel.next =sentinel;
        last = sentinel;
        size = 0;
        for(int i =0; i<= other.size;i++) {
            this.addLast((T) other.get(i));
        }
    }

    public T getRecursive(int index){
        if(index>size-1){
            return null;
        }else if(index == 0){
            return sentinel.next.item;
        }else{
            return getIntNode(index).item;
        }
    }

    public IntNode getIntNode(int index){
        if(index>size-1){
            return null;
        }else if (index == 0){
            return sentinel.next;
        }else{
            return getIntNode(index-1).next;
        }
    }
}
