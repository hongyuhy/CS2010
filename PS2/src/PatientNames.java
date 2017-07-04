// Copy paste this Java Template and save it as "PatientNames.java"
import java.util.*;
import java.io.*;

// write your matric number here: A0139024M
// write your name here: Tan Hong Yu
// write list of collaborators here: Clarence Chee
// year 2017 hash code: DZAjKugdE9QiOQKGFbut (do NOT delete this line)

class PatientNames {

    // --------------------------------------------

    BST male;
    BST female;

    // --------------------------------------------

    public PatientNames() {
        // Write necessary code during construction;
        //
        // write your answer here

        // --------------------------------------------

        male = new BST();
        female = new BST();

        // --------------------------------------------
    }

    void AddPatient(String patientName, int gender) {
        // You have to insert the information (patientName, gender)
        // into your chosen data structure
        //
        // write your answer here

        // --------------------------------------------
        if(gender == 1) {
            male.insert(patientName);
        }
        else {
            female.insert(patientName);
        }
        // --------------------------------------------
    }

    void RemovePatient(String patientName) {
        // You have to remove the patientName from your chosen data structure
        //
        // write your answer here

        // --------------------------------------------
        male.delete(patientName);
        female.delete(patientName);

        // --------------------------------------------
    }

    int Query(String START, String END, int gender) {

        // You have to answer how many patient name starts
        // with prefix that is inside query interval [START..END)
        //
        // write your answer here

        // --------------------------------------------

        if(gender == 1) {
            return male.subSet(START, END);
        }
        else if ( gender == 2) {
            return female.subSet(START, END);
        }
        else
            return male.subSet(START, END) + female.subSet(START, END);

        // --------------------------------------------
    }

    void run() throws Exception {
        // do not alter this method to avoid unnecessary errors with the automated judging
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 0) // end of input
                break;
            else if (command == 1) // AddPatient
                AddPatient(st.nextToken(), Integer.parseInt(st.nextToken()));
            else if (command == 2) // RemovePatient
                RemovePatient(st.nextToken());
            else // if (command == 3) // Query
                pr.println(Query(st.nextToken(), // START
                        st.nextToken(), // END
                        Integer.parseInt(st.nextToken()))); // GENDER
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception {
        // do not alter this method to avoid unnecessary errors with the automated judging
        PatientNames ps2 = new PatientNames();
        ps2.run();
    }
}

//Every vertex in this BST is a Java Class
class BSTVertex {
    // all these attributes remain public to slightly simplify the code
    public BSTVertex parent, left, right;
    public String key;
    public int height; // will be used in AVL lecture
    public int size;

    BSTVertex(String v) { key = v; parent = left = right = null; height = 0; size = 1;}

}

class BST {
    protected BSTVertex root;

    public BST() { root = null; }

    // method called to search for a value v 
    public BSTVertex search(String v) {
        BSTVertex res = search(root, v);
        return res == null ? null : res;
    }

    // overloaded recursive method to perform search
    protected BSTVertex search(BSTVertex T, String v) {
        if (T == null)  return null;                     // not found
        else if (T.key.equals(v)) return T;                        // found
        else if (T.key.compareTo(v) < 0)  return search(T.right, v);       // search to the right
        else                 return search(T.left, v);        // search to the left
    }

    // public method called to find Minimum key value in BST
    public BSTVertex findMin() { return findMin(root); }

    // overloadded recursive method to perform findMin
    protected BSTVertex findMin(BSTVertex T) {
        if (T == null)      throw new NoSuchElementException("BST is empty, no minimum");
        else if (T.left == null) return T;                    // this is the min
        else                     return findMin(T.left);           // go to the left
    }

    // public method called to find Maximum key value in BST
    public BSTVertex findMax() { return findMax(root); }

    // overloadded recursive method to perform findMax
    protected BSTVertex findMax(BSTVertex T) {
        if (T == null)       throw new NoSuchElementException("BST is empty, no maximum");
        else if (T.right == null) return T;                   // this is the max
        else                      return findMax(T.right);        // go to the right
    }

    // public method to find successor to given value v in BST
    public BSTVertex successor(String v) { 
        BSTVertex vPos = search(root, v);
        return vPos == null ? null : successor(vPos);
    }

    // overloaded recursive method to find successor to for a given vertex T in BST
    protected BSTVertex successor(BSTVertex T) {
        if (T.right != null)                       // this subtree has right subtree
            return findMin(T.right);  // the successor is the minimum of right subtree
        else {
            BSTVertex par = T.parent;
            BSTVertex cur = T;
            // if par(ent) is not root and cur(rent) is its right children
            while ((par != null) && (cur.key.equals(par.right.key))) {
                cur = par;                                         // continue moving up
                par = cur.parent;
            }
            return par == null ? null : par;           // this is the successor of T
        }
    }

    // public method to find predecessor to given value v in BST
    public BSTVertex predecessor(String v) { 
        BSTVertex vPos = search(root, v);
        return vPos == null ? null : predecessor(vPos);
    }

    // overloaded recursive method to find predecessor to for a given vertex T in BST
    protected BSTVertex predecessor(BSTVertex T) {
        if (T.left != null)                         // this subtree has left subtree
            return findMax(T.left);  // the predecessor is the maximum of left subtree
        else {
            BSTVertex par = T.parent;
            BSTVertex cur = T;
            // if par(ent) is not root and cur(rent) is its left children
            while ((par != null) && (cur.key.equals(par.left.key))) { 
                cur = par;                                         // continue moving up
                par = cur.parent;
            }
            return par == null ? null : par;           // this is the successor of T
        }
    }
    // method called to insert a new key with value v into BST
    public void insert(String v) { root = insert(root, v); }

    // overloaded recursive method to perform insertion of new vertex into BST
    protected BSTVertex insert(BSTVertex T, String v) {
        if (T == null) {
            BSTVertex newly = new BSTVertex(v);
            return newly;          // insertion point is found
        }

        if (T.key.compareTo(v) < 0) {                                      // search to the right
            T.right = insert(T.right, v);
            T.right.parent = T;
        }
        else {                                                 // search to the left
            T.left = insert(T.left, v);
            T.left.parent = T;
        }
        efficientHeightUpdate(T);
        efficientSizeUpdate(T);       
        T = balance(T);
        return T;                                          // return the updated BST
    }  

    // public method to delete a vertex containing key with value v from BST
    public void delete(String v) { root = delete(root, v); }

    // overloaded recursive method to perform deletion 
    protected BSTVertex delete(BSTVertex T, String v) {
        if (T == null)  return T;              // cannot find the item to be deleted

        if (T.key.compareTo(v) < 0)                                    // search to the right
            T.right = delete(T.right, v);
        else if (T.key.compareTo(v) > 0)                               // search to the left
            T.left = delete(T.left, v);
        else {                                            // this is the node to be deleted
            if (T.left == null && T.right == null) {                 // this is a leaf
                T = null;                                      // simply erase this node
                return T;
            }
            else if (T.left == null && T.right != null) {   // only one child at right        
                T.right.parent = T.parent;
                T = T.right;                                                 // bypass T        
            }
            else if (T.left != null && T.right == null) {    // only one child at left        
                T.left.parent = T.parent;
                T = T.left;                                                  // bypass T        
            }
            else {                                 // has two children, find successor
                BSTVertex successorV = successor(v);
                T.key = successorV.key;         // replace this key with the successor's key
                T.right = delete(T.right, successorV.key);      // delete the old successorV
            }
        }
        efficientHeightUpdate(T);
        efficientSizeUpdate(T);
        T = balance(T);
        return T;                                          // return the updated BST
    }

    private void efficientSizeUpdate(BSTVertex T) {
        int leftSize = 0;
        int rightSize = 0;

        if(T!=null) {
            if(T.left == null)
                leftSize = 0;
            else 
                leftSize = T.left.size;

            if(T.right == null)
                rightSize = 0;
            else
                rightSize = T.right.size;

            T.size = leftSize + rightSize + 1;
        }

    }

    private void efficientHeightUpdate(BSTVertex T) {
        int leftHeight = 0;
        int rightHeight = 0;

        if(T!=null) {
            if(T.left == null) 
                leftHeight = -1;
            else
                leftHeight = T.left.height;

            if(T.right == null)
                rightHeight = -1;
            else
                rightHeight = T.right.height;

            T.height = Math.max(leftHeight, rightHeight) + 1;
        }

    }
    public BSTVertex rotateLeft(BSTVertex T) {
        BSTVertex w = T.right;
        w.parent = T.parent;
        T.parent = w;
        T.right = w.left;
        if(w.left != null) 
            w.left.parent = T;
        w.left = T;
        efficientHeightUpdate(T);
        efficientSizeUpdate(T);
        efficientHeightUpdate(w);
        efficientSizeUpdate(w);
        return w;
    }

    public BSTVertex rotateRight(BSTVertex T) {
        BSTVertex w = T.left;
        w.parent = T.parent;
        T.parent = w;
        T.left = w.right;
        if(w.right != null) 
            w.right.parent = T;
        w.right = T;
        efficientHeightUpdate(T);
        efficientSizeUpdate(T);
        efficientHeightUpdate(w);
        efficientSizeUpdate(w);
        return w;
    }

    public int getBalFac(BSTVertex T) {        
        int leftSubTree = 0;
        int rightSubTree = 0;
        if(T!=null) {
            if(T.left != null )
                leftSubTree = T.left.height;
            else
                leftSubTree = -1;
            if(T.right != null)
                rightSubTree = T.right.height;
            else
                rightSubTree = -1;

            return leftSubTree - rightSubTree;
        }
        else
            return 0;     
    } 

    public BSTVertex balance(BSTVertex T) {
        int balanceFactor = getBalFac(T);

        if(balanceFactor == -2) {
            if(getBalFac(T.right) == 1) {
                T.right = rotateRight(T.right);
                T = rotateLeft(T);
            }
            else  {
                T = rotateLeft(T);
            }
        }
        else if (balanceFactor == 2) {
            if(getBalFac(T.left) == -1) {
                T.left = rotateLeft(T.left);
                T = rotateRight(T);
            }
            else {
                T = rotateRight(T);              
            }
        }
        return T;
    }

    public int getRank(BSTVertex T,String v) {
        int rank = 1;
        while(T!=null) {
            if(v.compareTo(T.key) == 0) {
                if(T.left!=null) 
                    return rank + T.left.size;
                else
                    return rank;
            }
            else if (v.compareTo(T.key) > 0) {
                if(T.left !=null)
                    rank += 1 + T.left.size;
                else
                    rank += 1;
                T = T.right;
            }
            else
                T = T.left;
        }
        return -1;
    }

    public int subSet(String START, String END) {      
        BSTVertex startSearch = search(root,START);
        BSTVertex endSearch = search(root,END);
        if(startSearch != null && endSearch != null) {
            return getRank(root,END) - getRank(root,START);
        }
        else if(startSearch != null && endSearch == null) {
            insert(END);
            int endRank = getRank(root,END);
            delete(END);
            return endRank - getRank(root,START);            
        }
        else if(startSearch == null && endSearch != null) {
            insert(START);
            int startRank = getRank(root,START);
            delete(START);
            return getRank(root,END) - startRank;
        }
        else {
            insert(START);
            insert(END);
            int startRank = getRank(root,START);
            int endRank = getRank(root,END);
            delete(START);
            delete(END);
            return endRank - startRank - 1;                    
        }
    }
}