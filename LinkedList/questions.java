public class questions {
    public class ListNode {
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
    public ListNode middleNode02(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    //Leetcode: 206. Reverse Linked List
    public ListNode reverseList(ListNode head) {
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
}