import java.util.Stack;

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

    public static void reReOrderList(ListNode head) {
        if(head == null || head.next == null) return;

        //From oddEvenList Que
        ListNode head1 = head;
        ListNode head2 = head.next;

        ListNode curr1 = head1;    
        ListNode curr2 = head2;    

        while(curr1.next != null && curr2.next != null) {
            curr1.next = curr2.next;
            curr1 = curr2.next;

            curr2.next = curr1.next;
            curr2 = curr1.next;
        }

        head2 = reverseList(head2);

        curr1.next = head2;
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

    //Leetcode 328. Odd Even Linked List
    //TestCase: [1, 2, 3, 4, 5, 6, 7, 8] 
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null)   return head;

        ListNode head1 = head;
        ListNode head2 = head.next;

        ListNode curr1 = head1;    
        ListNode curr2 = head2;    

        while(curr1.next != null && curr2.next != null) {
            curr1.next = curr2.next;
            curr1 = curr2.next;

            curr2.next = curr1.next;
            curr2 = curr1.next;

        }

        curr1.next = head2;
        return head1;
    }

    //Leetcode 82. Remove Duplicates from Sorted List II
    public ListNode deleteDuplicates2(ListNode head) {
        if(head==null) return null;
        ListNode FakeHead=new ListNode(0);
        FakeHead.next=head;
        ListNode pre=FakeHead;
        ListNode cur=head;
        while(cur!=null){
            while(cur.next!=null&&cur.val==cur.next.val){
                cur=cur.next;
            }
            if(pre.next==cur){
                pre=pre.next;
            }
            else{
                pre.next=cur.next;
            }
            cur=cur.next;
        }
        return FakeHead.next;
    }

    //Leetcode 2. Add Two Numbers
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        
        ListNode dummyHead = new ListNode(-1);
        ListNode currD = dummyHead;

        int carry = 0;
        int sum = 0;
        while(curr1 != null && curr2 != null) {
            sum = curr1.val + curr2.val + carry;
            carry = sum / 10;
            
            currD.next = new ListNode(sum % 10);
            currD = currD.next;
            
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        while(curr1 != null) {
            sum = curr1.val + carry;
            carry = sum / 10;
            
            currD.next = new ListNode(sum % 10);
            currD = currD.next;
            
            curr1 = curr1.next;
        }

        while(curr2 != null) {
            sum = curr2.val + carry;
            carry = sum / 10;
            
            currD.next = new ListNode(sum % 10);
            currD = currD.next;
            
            curr2 = curr2.next;
        }
        
        if(carry != 0) 
            currD.next = new ListNode(carry);

        return dummyHead.next;
    }

    // //Leetcode 445. Add Two Numbers II
    // public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    //     return buildLinkedList(Integer.parseInt(l1) + Integer.parseInt(l2).toString);
    // }


    //Leetcode 141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null)   return false;
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)    return true;
        }

        return false;
    }
    
    //Leetcode 142. Linked List Cycle II
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null)   return null;
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {              //Step 1. Find the first meeting point
                slow = head;
                while(slow != fast)   {     //Step 2. Find the intersection point by moving both ptrs at same speed;
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }

        return null;
    }

    public static void display(ListNode node) {
        if(node == null) return;
        System.out.print(node.val + " ");
        display(node.next);
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        ListNode head = l1;
        // l1.next = new ListNode(2);
        ListNode l2 = new ListNode(5);
        ListNode head2 = l2;
        // l1.next = new ListNode(2);

        // l1.next = new ListNode(8);
        // l1 = l1.next;
        display(head);
        display(head2);
        // ListNode nhead = deleteDuplicates(head);
        
        // reorderList(head);
        // System.out.println();
        // display(head);
        
        // reReOrderList(head);
        // System.out.println();
        // display(head);

        ListNode add = addTwoNumbers(l1, l2);
        display(add);
    }
}