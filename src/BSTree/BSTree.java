package BSTree;

import java.io.PrintWriter;

public class BSTree {
    private node root;
    
    public BSTree(){root = null;}
    public BSTree(node root){this.root = root;}
    
    public node getRoot(){return root;}
    public void setRoot(node root){this.root = root;}

    public void print(node n, PrintWriter out) {//recursive print function
        if(n !=  null) {
            print(n.getLeft(), out);//go down the left
            print(n.getRight(), out);//go down the right
            out.printf("%25s%5d%5d",n.getTitle(), n.getAvailable(),  n.getCopies());//print it with the correct formatting
            out.println();//go to next line
        }
    }
    public void print(node n) {//recursive print function to console
        if(n !=  null) {
            print(n.getLeft());
            print(n.getRight());
            System.out.printf("%25s%5d%5d",n.getTitle(), n.getAvailable(),  n.getCopies());
            System.out.println();
        }
    }

    public void addNode(node n) { //addNode recursive helper function
        root = addNode(root, n, null); 
    } 
    private node addNode(node root, node n, node parent){ //add node function for adding new nodes and modifying current ones
        System.out.println("\n\n" + n.getTitle() + "    " + n.getAvailable() + "\n");
        if (root == null)//if null, return the node as the new root
            return n; 
        else if (n.getTitle().compareTo(root.getTitle()) > 0)//else if title is greater in the alphabet, go to the right
            root.setRight(addNode(root.getRight(), n, root)); 
        else if (n.getTitle().compareTo(root.getTitle()) < 0)//else if the title is lesser in the alphabet, go to the left
            root.setLeft(addNode(root.getLeft(), n, root)); 
        else if (n.getTitle().compareTo(root.getTitle()) == 0){//else if it is the same as a current node
            if (root.getAvailable()+n.getAvailable() == 0 && root.getCopies() == 0 && n.getAvailable() < 0 && n.getCopies() == 0){//if removing is needed
                        parent = remove(root, parent);
                        return parent;
            }
            else{//else, alter the values inside with the requested numbers
                root.setAvailable(root.getAvailable() + n.getAvailable());
                root.setCopies(root.getCopies() + n.getCopies());
            }
        }
        return root; 
    }
    public boolean find(node n, String title, node parent){ //find a node recursively to remove
        if(n == null) 
            return false;
        if(n.getTitle().equals(title)){
                remove(n, parent);
                return true;
        }
        return find(n.getLeft(), title, n) || find(n.getRight(), title, n); 
    }
    public node remove(node n, node parent){//remove node function

        if(n == root) //if its the root node
        {
            //get last house on the left on the right!
            //it becomes the new root
            parent = n;
            n = getLastHouseOnTheLeft(n);
            if(n != null)
            {
                n.setLeft( parent.getLeft() );
                n.setRight( parent.getRight() );
                return n;
            }
        }
        else if(n.getLeft() == null && n.getRight() == null)
        {
            if(n == parent.getLeft() )
            {
                parent.setLeft(null);
            }
            else
            {
                parent.setRight(null);
            }
        }
        else if (n.getLeft() != null && n.getRight() != null) //two children, some shuffling
        {
            if(n == parent.getLeft() )
            {
                parent.setLeft( n.getRight() );
                parent.getLeft().setLeft( n.getLeft() );
            }
            else
            {
                parent.setRight( n.getRight() );
                parent.getRight().setLeft( n.getLeft() );
            }
        }
        else    //one child is simpler
        {
            if (n.getLeft() == null)
            {
                if(n == parent.getLeft() )
                {
                    parent.setLeft( n.getLeft() );
                }
                else
                {
                    parent.setRight( n.getLeft() );
                }
            }
            else
            {
                if(n == parent.getLeft() )
                {
                    parent.setLeft( n.getRight() );
                }
                else
                {
                    parent.setRight( n.getRight() );
                }
            }
        }
        return this.root;
    }
    private node getLastHouseOnTheLeft(node start)
    {
        node candidate = null;
        node parent = null;
        node node = start;

        while (node != null)
        {
            if ( node.getLeft() != null )
            {
                parent = node;
                candidate = node.getLeft();
            }

            node = node.getLeft();
        }

        if (parent != null)
        {
            parent.setLeft(null);
        }

        return candidate;
    }
}
