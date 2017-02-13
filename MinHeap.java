/*
 * Class MinHeap
 *
 * Contains the properties and methods of a minimum heap used as a priority queue
 *
 */

public class MinHeap {
    
    // Heap properties
    private BTNode root;
    private BTNode last;
    private int size;
    
    // Stringbuilder for printing out the heap.
    private StringBuilder sb = new StringBuilder();
    
    /*
     *===============================================================================================
     */
    
    // Constructor
    public MinHeap(){
        this.root = null;
        this.last = null;
        this.size = 0;
        
    }
    
    /*
     *===============================================================================================
     */
    
    // Method size
    // Returns heap size.
    public int size(){
        return this.size;
    }
    
    /*
     *===============================================================================================
     */
    
    // Method isEmpty
    // Checks if the heap is empty.
    public boolean isEmpty(){
        return size == 0;
    }
    
    /*
     *===============================================================================================
     */
    
    // Method swap
    // Swaps node values among two nodes.
    private void swap(BTNode one, BTNode two){
        int keyTmp = one.getKey();
        String dataTmp = one.getData();
        one.setData(two.getData());
        one.setKey(two.getKey());
        two.setData(dataTmp);
        two.setKey(keyTmp);
    }
    
    /*
     *===============================================================================================
     */
    
    // Method insertItem
    // Inserts a key-data pair (a node) to the heap.
    public void insertItem(int key, String data){
        BTNode n = new BTNode(key, data);
        if(isEmpty()){
            root = last = n;
        }
        else{
            BTNode tmp = last;
            BTNode par = tmp.getParent();
            while(tmp != root && tmp != par.getLeftChild()){
                tmp = tmp.getParent();
                par = tmp.getParent();
            }
            if(tmp != root){
                tmp = tmp.getParent().getRightChild();
            }
            while(tmp != null){
                par = tmp;
                tmp = tmp.getLeftChild();
            }
            n.setParent(par);
            if(par.getLeftChild() == null){
                par.setLeftChild(n);
            }
            else{
                par.setRightChild(n);
            }
            last = n;
        }
        size++;
        percolateUp(last);
    }
    
    /*
     *===============================================================================================
     */
    
    // Mehtod removeMin
    // Removes and returns the root (the element with the smallest key value)
    // from the heap.
    public BTNode removeMin(){
        if(isEmpty()){
            return null;
        }
        BTNode ret = new BTNode(root.getKey(), root.getData());
        if(size() == 1){
            root.destruct();
        }
        else{
            swap(root, last);
            BTNode tmp = last;
            while(tmp != root && tmp != tmp.getParent().getRightChild()){
                tmp = tmp.getParent();
            }
            if(tmp != root){
                tmp = tmp.getParent().getLeftChild();
            }
            while (tmp.getRightChild() != null){
                tmp = tmp.getRightChild();
            }
            if(tmp.getLeftChild() != null){
                tmp = tmp.getLeftChild();
            }
            if(last == last.getParent().getRightChild()){
                last.getParent().setRightChild(null);
                last.destruct();
            }
            else{
                last.getParent().setLeftChild(null);
                last.destruct();
            }
            last = tmp;
        }
        size--;
        percolateDown(root);
        return ret;
    }
    
    /*
     *===============================================================================================
     */
    
    // Method percolateUp
    // Restores the heap property with recursion after inserting a node.
    private BTNode percolateUp(BTNode node){
        if(node != root && node.getKey() < node.getParent().getKey()){
            swap(node, node.getParent());
            node = node.getParent();
            return(percolateUp(node));
        }
        return node;
    }
    
    /*
     *===============================================================================================
     */
    
    // Method percolateDown
    // Restores the heap property with recusrion after removing a node.
    private BTNode percolateDown(BTNode node){
        if(size() <= 1){
            return node;
        }
        else if(size() == 2 && node.getLeftChild().getKey() < node.getKey()){
            swap(node, node.getLeftChild());
            return node;
        }
        else if(node.getLeftChild() != null && node.getRightChild() != null){
            if(node.getLeftChild().getKey() < node.getKey() && node.getLeftChild().getKey() < node.getRightChild().getKey()){
                swap(node, node.getLeftChild());
                percolateDown(node.getLeftChild());
                
            }
            else if(node.getRightChild().getKey() < node.getKey()){
                swap(node, node.getRightChild());
                percolateDown(node.getRightChild());
            }
        }
        else if(node.getLeftChild() != null && node.getLeftChild().getKey() < node.getKey()){
            swap(node, node.getLeftChild());
            percolateDown(node.getLeftChild());
        }
        return node;
    }
    
    /*
     *===============================================================================================
     */
    
    // Method minKey
    // returns the key of the root node, which has the smallest key of the heap
    public int minKey(){
        return root.getKey();
    }

    // Method minElem
    // returns the data of the troot node
    public String minElem(){
        return root.getData();
    }
    
    /*
     *===============================================================================================
     */
    
    // Mehtod preorderPrint
    // Calls the recursive PreorderPrintRec, saves the completed heap to a String,
    // and wipes the StringBuilder clean for later use.
    public String preorderPrint(){
        preorderPrintRec(root, 0);
        String ret = sb.toString();
        sb = new StringBuilder();
        return ret;
    }
    

    // Mehtod preorderPrintRec
    // Recursively appends the heap to StringBuilder sb, using spaces to indicate
    // node level height.
    private BTNode preorderPrintRec(BTNode node, int height){
        String space = "";
        if(height > 0){
            space = String.format("%" + height + "s", " ");
        }
        if(node != null){
            sb.append(space).append(node.getKey()).append("\r\n");
            preorderPrintRec(node.getLeftChild(), height + 3);
            preorderPrintRec(node.getRightChild(), height + 3);
        }
        return node;
    }
}
