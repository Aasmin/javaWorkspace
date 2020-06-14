public class questions {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //Leetcode: 876. Middle of the Linked List(return the second middle node) - upperbound
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    //(return the first middle node) - lowerbound
    public static ListNode middleNode02(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    //Leetcode: 206. Reverse Linked List
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)   return head;
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode frwd = curr.next;
            curr.next = prev;
            prev = curr;
            curr = frwd;
        }
        return prev;
    }
    
    //reverse LL data
    public void reverseListData(ListNode head) {
        if(head == null || head.next == null)   return;
        
        ListNode curr = head;
        ListNode midNode = middleNode02(head);
        ListNode nhead = midNode.next;
        
        midNode.next = null;    //break the list 
        nhead = reverseList(nhead); //reverse the new list

        ListNode curr1 = nhead;
        while(curr != null && curr1 != null) {   //swap the data until any of the list reaches end
            int val = curr.val;
            curr.val = curr1.val;
            curr1.val = val;

            curr = curr.next;
            curr1 = curr1.next;
        }

        nhead = reverseList(nhead); //reverse again the new list
        midNode.next = nhead;   //connect both the list
    }

    //Leetcode: 234. Palindrome Linked List (SAME AS reverse LL data)
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)   return true;
        
        ListNode curr = head;
        ListNode midNode = middleNode02(head);
        ListNode nhead = midNode.next;
        
        midNode.next = null;    //break the list 
        nhead = reverseList(nhead); //reverse the new list

        ListNode curr1 = nhead;
        while(curr != null && curr1 != null) {   //swap the data until any of the list reaches end
            if(curr.val != curr1.val)   return false;

            curr = curr.next;
            curr1 = curr1.next;
        }

        return true;
    }

    //Leetcode 203. Remove Linked List Elements 
    //test case: [1,2,6,3,4,5,6] for 6
    //test case : [6, 5, 4] for 6
    //test case : [6, 6, 4] for 6
    public ListNode removeElements(ListNode head, int val) {
        ListNode curr = head;
        ListNode prev = null;

        while(curr != null) {
            ListNode frwd = curr.next;
            if(curr.val == val) {
                if(curr == head) {  //val found at head
                    head = frwd;
                } else {
                    prev.next = frwd;
                    curr.next = null;
                }
            } else 
                prev = curr;
            curr = frwd;
        }
        return head;
    }
    
    public ListNode removeElements2(ListNode head, int val) { //[EASY AND BETTER]
        if(head == null)    
            return head;
        
        ListNode nhead = new ListNode(-1);  //make a dummy list
        ListNode curr = nhead;
        
        while(head != null) {
            if(head.val != val) {  
                curr.next = new ListNode(head.val);
                curr = curr.next;
            }
            head = head.next;
        }
        return nhead.next;
    }
    
    //Leetcode 83. Remove Duplicates from Sorted List
    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)   return head;

        ListNode dummy = new ListNode(head.val);  //make a dummy list
        ListNode curr = head;
        ListNode currD = dummy;

        while(curr != null) {
            if(currD.val != curr.val) {
                currD.next = new ListNode(curr.val);
                currD = currD.next;
            }
            curr = curr.next;
        }
        return dummy;
    }
    
    //Leetcode 19. Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || head.next == null)    return null;
        ListNode right = head;
        ListNode left = head;

        while(n-- > 0){
            right = right.next;
            // if(right == null && n > 0)  return false; // this means n is inappropriate
        }
        
        if(right == null) { //Testcase: [1, 2] n = 2
            head = head.next;
            return head;
        }
            
        while(right.next != null) {
            right = right.next;
            left = left.next;
        }

        left.next = left.next.next;
        return head;
    }

    //Leetcode 143. Reorder List    [SAME AS REVERSE DATA]
    public static void reorderList(ListNode head) {
        if(head == null || head.next == null)   return;
        
        ListNode curr = head;
        ListNode midNode = middleNode02(head);
        ListNode nhead = midNode.next;
        
        midNode.next = null;    //break the list 
        nhead = reverseList(nhead); //reverse the new list

        ListNode curr1 = nhead;
        while(curr != null && curr1 != null) {   
            ListNode forw1 = curr.next;
            ListNode forw2 = curr1.next;

            curr.next = curr1;
            curr1.next = forw1;

            curr = forw1;
            curr1 = forw2;
        }
    }
    

    public static void display(ListNode node) {
        if(node == null) return;
        System.out.print(node.val + " ");
        display(node.next);
    }
     
    //Leetcode 21. Merge Two Sorted Lists
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null)    return l1 == null ? l2 : l1; //if any of the list is null

        ListNode dummyH = new ListNode(-1);
        ListNode prev = dummyH;

        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                prev.next = l1;
                prev = prev.next;
                l1 = l1.next;
            } else {
                prev.next = l2;
                prev = prev.next;
                l2 = l2.next;
            }
        }

        if(l1 != null) {
            prev.next = l1;
        }

        if(l2 != null) {
            prev.next = l2;
        }

        return dummyH.next; //DO NOT RETURN THE DUMMY
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode head = l1;
        l1.next = new ListNode(2);
        l1 = l1.next;
        l1.next = new ListNode(3);
        l1 = l1.next;
        l1.next = new ListNode(4);
        l1 = l1.next;
        l1.next = new ListNode(5);
        l1 = l1.next;
        display(head);
        // ListNode nhead = deleteDuplicates(head);
        reorderList(head);

        System.out.println();
        display(head);
    }
}