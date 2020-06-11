public class l001 {
    public static class Node {
        int data;
        Node next;

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static class linkedlist {
        Node head;
        Node tail;
        int size;

        void addLast(int data) {
            Node nn = new Node(data, null);
            if (size == 0) {
                head = tail = nn;
            } else {
                tail.next = nn;
                tail = nn;
            }
            size++;
        }

        void display() {
            Node curr = head;
            while (curr != null) {
                System.out.print(curr.data + " ");
                curr = curr.next;
            }
            System.out.println();
        }

        int size() {
            return this.size;
        }

        void removeFirst() {
            if (this.size == 0) {
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
            if (this.size == 0) {
                System.out.println("List is empty!");
                return -1;
            } else
                return head.data;
        }

        public int getLast() {
            if (this.size == 0) {
                System.out.println("List is empty!");
                return -1;
            } else
                return tail.data;

        }

        public int getAt(int idx) {
            if (this.size == 0) {
                System.out.println("List is empty!");
                return -1;
            } else if (idx < 0 || idx >= this.size) {
                System.out.println("Invalid arguments");
                return -1;
            } else {
                Node curr = head;
                while (idx-- != 0)
                    curr = curr.next;
                return curr.data;
            }
        }

        public void addFirst(int data) {
            Node nn = new Node(data, head);
            if (this.size == 0) {
                head = tail = nn;
            } else {
                head = nn;
            }
            size++;
        }

        public void addAt(int idx, int val) {
            Node nn = new Node(val, null);
            if (idx < 0 || idx > size)
                System.out.println("Invalid arguments");
            else if (idx == 0)
                addFirst(val);
            else if (idx == size)
                addLast(val);
            else {
                Node curr = head;
                for (int i = 0; i < idx - 1; i++)
                    curr = curr.next;
                nn.next = curr.next;
                curr.next = nn;
                size++;
            }
        }

        void removeLast() { // O(n)
            if (size == 0)
                System.out.println("List is empty!");
            else if (size == 1) {
                head = tail = null;
                size = 0;
            } else {
                Node curr = head;
                for (int i = 0; i < size - 2; i++)
                    curr = curr.next;
                curr.next = null;
                tail = curr;
                size--;
            }
        }

        private Node getNodeAt(int idx) {
            if (this.size == 0) {
                System.out.println("List is empty!");
                return null;
            } else if (idx < 0 || idx >= this.size) {
                System.out.println("Invalid arguments");
                return null;
            } else {
                Node curr = head;
                while (idx-- != 0)
                    curr = curr.next;
                return curr;
            }
        }

        void reverseDataItr() {
            int i = 0, j = this.size - 1;
            while (i < j) {
                int temp = getNodeAt(i).data;
                getNodeAt(i).data = getNodeAt(j).data;
                getNodeAt(j).data = temp;
                i++;
                j--;
            }
        }

        void reversePtrItr() {
            if (this.size <= 1)
                return;

            Node prev = null;
            Node curr = head;
            while (curr != null) {
                Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            tail = head;
            head = prev;
        }

        void removeAt(int idx) {
            if (idx < 0 || idx >= size)
                System.out.println("Invalid arguments");
            else if (idx == 0)
                removeFirst();
            else if (idx == size - 1)
                removeLast();
            else {
                Node curr = getNodeAt(idx - 1);
                curr.next = curr.next.next;
                size--;
            }
        }

        int kthFromLast(int k) {
            Node fast = head;
            Node slow = head;

            for (int i = 0; i <= k; i++) // move fast k steps ahead
                fast = fast.next;

            while (fast != tail) { // move both ptrs parallely
                fast = fast.next;
                slow = slow.next;
            }
            return slow.data;
        }

        Node midLL() {
            Node fast = head;
            Node slow = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
        Node midLL2(Node H, Node T) {
            Node fast = H;
            Node slow = H;
            while (fast != T && fast.next != T) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        linkedlist mergeTwoSortedLL(linkedlist l1, linkedlist l2) {
            Node i = l1.head;
            Node j = l2.head;
            linkedlist l3 = new linkedlist();
            while (i != null && j != null)
                if (i.data < j.data) {
                    l3.addLast(i.data);
                    i = i.next;
                } else {
                    l3.addLast(j.data);
                    j = j.next;
                }
            while (i != null) {
                l3.addLast(i.data);
                i = i.next;
            }
            while (j != null) {
                l3.addLast(j.data);
                j = j.next;
            }
            return l3;
        }

        linkedlist mergeSort(Node start, Node end) {
            if(start == end) {
                linkedlist br = new linkedlist();
                br.addLast(start.data);
                return br;
            }
            Node midNode = midLL2(start, end);
            linkedlist l1 = mergeSort(start, midNode);
            linkedlist l2 = mergeSort(midNode.next, end);
            linkedlist l3 = mergeTwoSortedLL(l1, l2);
            return l3;
        }
    }

    public static void main(String[] args) {
        linkedlist l1 = new linkedlist();
        // linkedlist l2 = new linkedlist();
        // for (int i = 1; i <= 6; i++)
        //     l1.addLast(i * 10);
        // for (int i = 1; i <= 3; i++)
        //     l2.addLast(i * 7);
        // l1.display();
        // l2.display();
        // // System.out.println("Data: " + l1.kthFromLast(1));
        // // System.out.println("Mid: " + l1.midLL());
        // linkedlist l3 = l1.mergeTwoSortedLL(l1, l2);
        // l3.display();
        // linkedlist l3 = l1.mergeTwoSortedLL(l1, l2);
        // l3.display();
        l1.addLast(10);
        l1.addLast(-10);
        l1.addLast(15);
        l1.addLast(30);
        l1.addLast(20);
        l1.addLast(-20);
        l1.addLast(-30);
        l1.display();
        linkedlist l2 = l1.mergeSort(l1.head, l1.tail);
        l2.display();
    }
}