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
            if(this.size == 0) {System.out.println("List is empty!");   return -1;}
            else return head.data;
        }
        
        public int getLast() {
            if(this.size == 0) {System.out.println("List is empty!");   return -1;}
            else return tail.data;
            
        }
        
        public int getAt(int idx) {
            if(this.size == 0) {System.out.println("List is empty!");   return -1;}
            else if(idx < 0 || idx >= this.size){System.out.println("Invalid arguments");   return -1;}
            else {
                Node curr = head;
                while(idx-- != 0) curr = curr.next;
                return curr.data;
            }
        }
        
        public void addFirst(int data) {
            Node nn = new Node(data, head);
            if(this.size == 0) {
                head = tail = nn;
            } else {
                head = nn;
            } 
            size++;
        }
        
        public void addAt(int idx, int val) {
            Node nn = new Node(val, null);
            if(idx < 0 || idx > size)  System.out.println("Invalid arguments");
            else if(idx == 0)    addFirst(val);
            else if(idx == size)    addLast(val);
            else {
                Node curr = head;
                for(int i = 0; i < idx -  1; i++)
                curr = curr.next;
                nn.next = curr.next;
                curr.next = nn;
                size++;
            }
        }
        
        void removeLast() {
            if(size == 0) System.out.println("List is empty!");
            else if(size == 1) {head = tail = null; size = 0;}
            else {
                Node curr = head;
                for(int i = 0; i < size - 2; i++)   curr = curr.next;
                curr.next = null;
                tail = curr; 
                size--;
            }
        }
        
        private Node getNodeAt(int idx) {
            if(this.size == 0) {System.out.println("List is empty!");   return null;}
            else if(idx < 0 || idx >= this.size){System.out.println("Invalid arguments");   return null;}
            else {
                Node curr = head;
                while(idx-- != 0) curr = curr.next;
                return curr;
            }
        }
        void reverseDataItr() {
            int i = 0, j = this.size - 1;
            while(i < j) {
                int temp = getNodeAt(i).data;
                getNodeAt(i).data = getNodeAt(j).data;
                getNodeAt(j).data = temp;
                i++;    j--;
            }
        }
    }
    public static void main(String[] args) {
        
    }
}