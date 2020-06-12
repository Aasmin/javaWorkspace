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

        void removeDuplicates() {    //Space: O(constant)    Time: O(n)
            linkedlist res = new linkedlist();
            
            while(this.size > 0) {
                int val = this.getFirst();
                this.removeFirst();
                if(res.size() == 0 || res.tail.data != val)     res.addLast(val);
            }

            this.head = res.head;
            this.tail = res.tail;
            this.size = res.size;
        }

        void oddEven() {    //Space: O(constant)    Time: O(n)
            linkedlist even = new linkedlist();
            linkedlist odd = new linkedlist();

            while(this.size() > 0) {
                int val = this.getFirst();
                this.removeFirst();
                if(val % 2 == 0) {  //even
                    even.addLast(val);
                } else {
                    odd.addLast(val);
                }
            }
            
            if(even.size() > 0 && odd.size() > 0) {
                odd.tail.next = even.head;
                this.head = odd.head;
                this.tail = even.tail;
                this.size = odd.size() + even.size();
            } else if(even.size() > 0) {    //no odd ele in the list
                this.head = even.head;
                this.tail = even.tail;
                this.size = even.size();
            } else if(odd.size() > 0) {
                this.head = odd.head;
                this.tail = odd.tail;
                this.size = odd.size();
            }
        }

        void kReverse(int k) {    //Space: O(constant)    Time: O(linear)
            linkedlist prev = null;

            while(this.size() > 0) {
                linkedlist curr = new linkedlist();
                if(this.size() >= k) {
                    for(int i = 0; i < k; i++) {
                        int val = this.getFirst();
                        this.removeFirst();
                        curr.addFirst(val);
                    }
                } else {
                    int os = this.size();
                    while(os-- != 0) {
                        int val = this.getFirst();
                        this.removeFirst();
                        curr.addLast(val);
                    }
                }
                
                if(prev == null)
                    prev = curr;
                else {
                    prev.tail.next = curr.head;
                    prev.tail = curr.tail;
                    prev.size += curr.size;
                }
            }
            this.head = prev.head;
            this.tail = prev.tail;
            this.size = prev.size;
        }

        private void displayReverseHelper(Node node) {
            if(node == null)    return;
            displayReverseHelper(node.next);
            System.out.print (node.data + " ");
        }

        public void displayReverse() {
            displayReverseHelper(head);
            System.out.println();
        }
        
        private void reversePtrHelper(Node node) {
            if(node == null)    return;

            reversePtrHelper(node.next);
            if(node == tail) {
                //do nothing  
            } else {
                node.next.next = node;
            }
        }

        public void reversePtr() {
            reversePtrHelper(head);
            this.head.next = null;
            Node temp = head;
            this.head = tail;
            tail = temp;
        }

        public void reverseDataRecHelper(Node rNode, int floor) {
            if(rNode == null)   return;
            reverseDataRecHelper(rNode.next, floor + 1);
            
            if(floor >= size / 2) {
                int temp = rNode.data;
                rNode.data = lNode.data;
                lNode.data = temp;
                lNode = lNode.next;
            }

        }

        Node lNode;
        public void reverseDataRec(Node rNode) {
            lNode = head;
            reverseDataRecHelper(rNode, 0);
        }

        //Leetcode: 234.
        boolean isPallindromeHelper(Node rnode) {
            if(rnode == null)   return true;

            boolean res = isPallindromeHelper(rnode.next);
            if(!res)    return false;

            if(rnode.data != lNode.data)
                return false;
            lNode = lNode.next;

            return true;
        }

        public boolean isPallindrome() {
            lNode = head;
            return isPallindromeHelper(head);
        }

        private void foldLLHelper(Node rnode, int floor) {
            if(rnode == null)  return;

            foldLLHelper(rnode.next, floor + 1);

            if(floor > size / 2) {
                Node temp = lNode.next;
                lNode.next = rnode;
                rnode.next = temp;
                lNode = temp;
            } else if(floor == size / 2) {
                tail = rnode;
                tail.next = null;
            }
        }

        public void foldLL(Node rnode) {
            lNode = head;
            foldLLHelper(rnode, 0);
        }

        //addition of two LL
        private int addTwoLinkedListHelper(Node one, int pv1, Node two, int pv2, linkedlist ans) {
            if(one == null && two == null)  return 0;
            int data = 0;
            if(pv1 > pv2) {
                int oc = addTwoLinkedListHelper(one.next, pv1 - 1, two, pv2, ans);
                data = one.data + oc;
            } else if(pv2 > pv1) {
                int oc = addTwoLinkedListHelper(one, pv1, two.next, pv2 - 1, ans);
                data = two.data + oc;
            } else {
                int oc = addTwoLinkedListHelper(one.next, pv1 - 1, two.next, pv2 - 1, ans);
                data = one.data + two.data + oc;
            }
            ans.addFirst(data % 10);
            return data / 10;   //carry
        }

        public linkedlist addTwoLinkedList(linkedlist one, linkedlist two) {
            linkedlist res = new linkedlist();
            int oc = addTwoLinkedListHelper(one.head, one.size, two.head, two.size, res);
            if(oc > 0) 
                res.addFirst(oc);
            return res;
        }

        public int intersectionPoint(linkedlist one, linkedlist two) {
            Node t1 = one.head;
            Node t2 = two.head;

            int delta = Math.abs(one.size - two.size);

            if(one.size > two.size) {
                for(int i = 0; i < delta; i++)
                    t1 = t1.next;
            } else {
                for(int i = 0; i < delta; i++)
                    t2 = t2.next;
            }
             
            while(t1 != t2) {
                t1 = t1.next;
                t2 = t2.next;
            } 

            return t1.data;
        }
    }

    public static void main(String[] args) {
        linkedlist l1 = new linkedlist();
        linkedlist l2 = new linkedlist();
        // for (int i = 1; i <= 4; i++)
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
        // l1.addLast(5);
        // l1.addLast(2);
        // l1.addLast(15);
        // l1.addLast(4);
        // l1.addLast(41);
        // l1.addLast(150);
        // l1.addLast(22);
        // l1.display();
        // linkedlist l2 = l1.mergeSort(l1.head, l1.tail);
        // l1.removeDuplicates();
        // l1.oddEven();
        // l1.kReverse(3);
        // l1.display();
        // l1.displayReverse();
        // l1.reversePtr();
        // l1.reverseDataRec(l1.head);
        // System.out.println(l1.isPallindrome());
        // l1.foldLL(l1.head);
        // [4,1,8,4,5]
        // [5,0,1,8,4,5]
        l1.addLast(4);
        l1.addLast(1);
        l1.addLast(8);
        l1.addLast(4);
        l1.addLast(5);
        // l2.addLast(5);
        l2.addLast(0);
        l2.addLast(1);
        l2.addLast(8);
        l2.addLast(4);
        l2.addLast(5);
        l1.display();
        l2.display();
        // linkedlist l3 = l1.addTwoLinkedList(l1, l2);
        // l3.display();

        System.out.println(l1.intersectionPoint(l1, l2));
    }
}