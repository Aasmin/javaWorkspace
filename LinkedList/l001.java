public class l001{
    
    public static class linkedlist{
        public class Node{
            int data=0;
            Node next=null;
    
            public Node(int data){
                this.data=data;
            }
        }

        Node head=null;
        Node tail=null;
        int size=0;

        //generalUtil.==================================================

        public int size(){
            return this.size;
        }

        public boolean isEmpty(){
            return size==0;
        }

        @Override
        public String toString(){
            Node curr=this.head;
            String str="";
            while(curr!=null){
                str+=(curr.data + " -> ");
                curr=curr.next;
            }
            return str;
        }

        public Node getNodeAt(int pos){
            Node curr=this.head;
            while(pos-- > 0){
                curr=curr.next;
            }
            return curr;
        }

        //Add_Function.===================================================

        private void addFirstNode(Node node){
            if(this.size==0){
                this.tail=node;
                this.head=node;
            }else{
                node.next=this.head;
                this.head=node;
            }
            this.size++;
        }

        public void addFirst(int data){
            Node node=new Node(data);
            addFirstNode(node);
        }

        private void addLastNode(Node node){
            if(this.size==0){
                this.tail=node;
                this.head=node;
            }else{
                tail.next=node;
                this.tail=node;
            }
            this.size++;
        }

        public void addLast(int data){
            Node node=new Node(data);
            addLastNode(node);
        }

        private void addAtNode(Node node,int pos){
            if(pos>=this.size || pos < 0){
                System.out.println("NullPointException!");
                return;
            }
            Node curr = head;
            while(--pos > 0){
                curr = curr.next;
            } 
            node.next = curr.next;
            curr.next = node;
        }

        public void addAt(int data,int pos){
            if(pos == 0){
                addFirst(data);
                return;
            }
            Node node=new Node(data);
            addAtNode(node,pos);
        }


        //remove_Function.===================================================

        public int removeFirst(){
            if(this.size==0){
                System.out.println("EmptyList!");
                return -1;
            }

            Node rnode=this.head;

            if(this.size==1){
                this.head=null;
                this.tail=null;
            }else{
                this.head=this.head.next;
                rnode.next=null;
            }

            this.size--;
            return rnode.data;   // delete rnode; 
        }
        
        public int removeLast(){
            if(this.size==0){
                System.out.println("EmptyList!");
                return -1;
            }

            Node rnode=this.tail;
            if(this.size==1){
                this.head=null;
                this.tail=null;
            }else{
                Node secondLastNode=getNodeAt(this.size()-2);
                secondLastNode.next=null;
                this.tail=secondLastNode;
            }

            this.size--;
            return rnode.data; 
        }

        public int removeAt(int pos){
            int data = -1;
            if(pos>=this.size || pos < 0){
                System.out.println("NullPointException!");
                return data;
            }
            if(pos == 0)
                return removeFirst();
            if(pos == this.size - 1)
                return removeLast();
                
            Node curr = head;
            while(--pos > 0){
                curr = curr.next;
            }
            if(curr.next.next != null && curr.next != null){
                data = curr.next.data;
                curr.next = curr.next.next;
            }
            this.size--;
            return data; 
        }

        //get_Function.======================================================
        
        public int getFirst(){
            if(this.size==0){
                System.out.println("EmptyList!");
                return -1;
            }
            return this.head.data;  
        }

        public int getLast(){
            if(this.size==0){
                System.out.println("EmptyList!");
                return -1;
            }
            return this.tail.data;  
        }

        public int getAt(int pos){
            if(pos>=this.size || pos < 0){
                System.out.println("NullPointException!");
                return -1;
            }
            Node curr = head;
            while(pos-- > 0){
                curr = curr.next;
            }
            return curr.data;  
        }
    }

    public static void main(String[] args){
        linkedlist ll=new linkedlist();
        for(int i=1;i<10;i++){
            ll.addFirst(i*10);
        }
        // for(int i=1;i<10;i++){
        //     ll.addLast(i*10);
        // }
        System.out.println(ll);
        System.out.println(ll.removeAt(8));
        System.out.println(ll);

    }   
}