public class questions {
    public class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }
    
    //Leetcode: getMid 876
    public ListNode middleNode(ListNode head) { //mid node upperBound
        ListNode slow = head;
        ListNode fast = head;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    //Leetcode: 206


    //Leetcode: 19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(n == 0 || head == null)    return head;
        if(n == 1 && head.next == null) return null;

        ListNode fast = head;
        ListNode slow = head;
        while(n-- > 0){
            fast = fast.next;
            if(fast == null && n > 0)   return head;    //do nothing here as n > size()
        }
        
        if(fast == null)    return slow.next;

        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        ListNode forw = slow.next;
        slow.next = slow.next.next;
        forw.next = null;   //just to make sure that removed node is not pointing to any other node

        return head;
    }

    //leetcode 143
    public void reorderList(ListNode head) {
        ListNode midNode = middleNode(head);
        
        ListNode lbMiddle=midNode;
        ListNode ubMiddle=midNode.next;
        while(ubMiddle.next!=null){
            ListNode current=ubMiddle.next;
            ubMiddle.next=current.next;
            current.next=ubMiddle.next;
            ubMiddle.next=current;
        }

        ListNode p1=head;
        ListNode p2=lbMiddle.next;
        while(p1!=lbMiddle){
            lbMiddle.next=p2.next;
            p2.next=p1.next;
            p1.next=p2;
            p1=p2.next;
            p2=lbMiddle.next;
        }
    }

}