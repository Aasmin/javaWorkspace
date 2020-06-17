class l001{
    static class MyStack {
        private int N = 10000;
        private int[] arr;
        private int tos = -1;
        private int sz = 0;
        

        MyStack() {
            this.arr = new int[N];
        }

        MyStack(int size) {
            this.arr = new int[size];
            this.N = size;
        }

        int size() {
            return this.sz;
        }

        boolean empty() {
            return sz == 0;
        }

        public void push(int data) {
            this.tos++;  this.sz++;
            arr[tos] = data;
        }

        public void pop() {
            if(this.empty()){
                System.out.println("Stack underflow!");
            } else {
                this.tos--; this.sz--;
            }
        }

        public int top() {
            if(this.empty()){
                System.out.println("Stack underflow!");
                return -1;
            } else {
                int val = arr[tos];
                return val;
            }
        }

    }
    public static void main(String[] args) {
        MyStack st = new MyStack(10);
        for(int i = 1; i <= 10; i++) 
            st.push(i * 10);

        int size = st.size();
        System.out.println(size);
        for(int i = 1; i <= size; i++) {
            System.out.print(st.top() + " ");
            st.pop();
        }
        System.out.println(st.size());
        System.out.println(st.empty());
    }
}