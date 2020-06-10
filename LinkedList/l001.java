public class l001 {
    public static class Node {
        int data;
        Node next;
        Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
    }
    public static class linkedlist{
        Node head;  
        Node tail;
        int size;

        void addLast(int data) {
            Node nn = new Node(data, null);
            if(size == 0) { 
                head = tail = nn;
            } else {
            tail.next = nn;
            tail = nn;
            }
            size++;
        }

        void display() {
            Node curr = head;
            while(curr!=null) {
                System.out.print(curr.data + " ");
                curr = curr.next;
            }
            System.out.println();
        }

        int size() {
            return this.size;
        }

        void removeFirst() {
            if(this.size == 0){
                System.out.println("List is empty!");
            } else if (this.size == 1) {
                head = tail = null;
                size = 0; 
            } else {
                head = head.next;
                size--;
            }
        }

        public int getFirst() {
            
        }

        public int getLast() {

        }

        public int getAt(int idx) {

        }
    }
    public static void main(String[] args) {
        
    }
}