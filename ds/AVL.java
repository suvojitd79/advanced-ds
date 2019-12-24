/**
 * 
 * AVL - opeartion costs O(log(n))
 * 
 */

public class AVL {

    class Node{

        int val;
        Node left, right;
        int height;

        Node(int val) {
            this.val = val;
            this.left = this.right = null;
            this.height = 1;
        }

    }

    AVL() {
    }


    /**
     * 
     * @param key - node data
     * @return reference to the updated root(if needed) node
     */


    public Node insert(Node root, int key) {

        if(root == null)
            return new Node(key);
        else if(root.val > key)
            root.left = insert(root.left, key);
        else
            root.right = insert(root.right, key);


        // update the height of the ancestors
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        int bf = getBF(root);

        // L-L
        if(bf > 1 && root.left.val > key)
            return rightRotation(root);
        // L-R
        else if(bf > 1 && root.left.val < key)
        {
            root.left = leftRotation(root.left);
            return rightRotation(root);
        }
        // R-R
        else if(bf < -1 && root.right.val < key)
            return leftRotation(root);
        // R-L
        else if(bf < -1 && root.right.val > key)
        {

            root.right = rightRotation(root.right);
            return leftRotation(root);
        }

        return root;
    }

    // O(1) operation
    public Node rightRotation(Node root){

        Node l = root.left;
        Node l_r = l.right;

        l.right = root;
        root.left = l_r;

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        l.height = 1 + Math.max(getHeight(l.left), getHeight(l.right));

        return l;
    }



    // O(1) operation
    public Node leftRotation(Node root){

        Node r = root.right;
        Node r_l = r.left;

        r.left = root;
        root.right = r_l;

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        r.height = 1 + Math.max(getHeight(r.left), getHeight(r.right));

        return r;
    }


    // balance factor
    public int getBF(Node root){

        return root == null ? 0 : getHeight(root.left) - getHeight(root.right);
    }


    public int getHeight(Node root){

        return root == null ? 0 : root.height;   

    }


    public String inorder(Node root){
        if(root == null)
            return "";
        return root.val + " " + inorder(root.left).trim() + " "+ inorder(root.right).trim();    
    }

    public static void main(String[] args){

        AVL avl = new AVL();
        Node root = avl.insert(null, 1);
        root = avl.insert(root, 2);
        root = avl.insert(root, 3);

        System.out.println(avl.inorder(root).trim());
    }



}
