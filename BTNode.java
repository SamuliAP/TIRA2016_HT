/*
 * Class BTNode
 *
 * Contains basic binary tree node properties and methods.
 *
 */

public class BTNode {
    
    // Node properties
    private BTNode parent;
    private BTNode leftChild;
    private BTNode rightChild;
    private int key;
    private String data;
    
    /*
     *==========================================================================
     */
    
    // Constructor
    public BTNode(int key, String data) {
        if(key > 0){
            this.key = key;
            this.data = data;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }
    }
    
    /*
     *==========================================================================
     */
    
    // Destructor, possibly unnecessary because of java garbage collection
    public void destruct(){
        this.key = 0;
        this.data = null;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    /*
     *==========================================================================
     */
    
    // Getters and setters
    public BTNode getParent() {
        return parent;
    }

    public void setParent(BTNode parent) {
        this.parent = parent;
    }

    public BTNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BTNode leftChild) {
        this.leftChild = leftChild;
    }

    public BTNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BTNode rightChild) {
        this.rightChild = rightChild;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        if(key > 0){
            this.key = key;
        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
     
}
