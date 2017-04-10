package BSTree;

import java.io.PrintWriter;

public class BSTree {
    private node root;
    
    public BSTree(){root = null;}
    public BSTree(node root){this.root = root;}
    
    public node getRoot(){return root;}
    public void setRoot(node root){this.root = root;}

    public void print(node n, PrintWriter out) {
        if(n !=  null) {
            print(n.getLeft(), out);
            print(n.getRight(), out);
            out.printf("%25s%5d%5d",n.getTitle(), n.getAvailable(),  n.getCopies());
            out.println();
        }
    }
    public void print(node n) {
        if(n !=  null) {
            print(n.getLeft());
            print(n.getRight());
            System.out.printf("%25s%5d%5d",n.getTitle(), n.getAvailable(),  n.getCopies());
            System.out.println();
        }
    }

    public void addNode(node n) { 
        root = addNode(root, n, null); 
    } 
    private node addNode(node root, node n, node parent) { 
        if (root == null)
            return n; 
        else if (n.getTitle().compareTo(root.getTitle()) > 0)
            root.setRight(addNode(root.getRight(), n, root)); 
        else if (n.getTitle().compareTo(root.getTitle()) < 0)
            root.setLeft(addNode(root.getLeft(), n, root)); 
        else if (n.getTitle().compareTo(root.getTitle()) == 0){
            if (root.getAvailable() == 0 && root.getCopies() == 0 && n.getAvailable() < 0 && n.getCopies() == 0){
                        remove(root, parent);
                        return parent;
            }
            else{
                root.setAvailable(root.getAvailable() + n.getAvailable());
                root.setCopies(root.getCopies() + n.getCopies());
            }
        }
        return root; 
    }
    public boolean find(node n, String title, node parent){ 
        if(n == null) 
            return false;
        if(n.getTitle().equals(title)){
                remove(n, parent);
                return true;
        }
        return find(n.getLeft(), title, n) || find(n.getRight(), title, n); 
    }
    public void remove(node n, node parent){

        if (n == null)
            return;

        boolean isLeft = (n == parent.getLeft() );

        if (n == root) //if its the root node
        {
            //get last house on the left on the right!
            //it becomes the new root
            n = getLastHouseOnTheLeft( parent.getRight() );
            if (n != null)
            {
                n.setLeft( parent.getLeft() );
                n.setRight( parent.getRight() );
                root = n;
            }
        }
        else if (n.getLeft() == null && n.getRight() == null)
        {
            if (isLeft)
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
            if (isLeft)
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
                if (isLeft)
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
                if (isLeft)
                {
                    parent.setLeft( n.getRight() );
                }
                else
                {
                    parent.setRight( n.getRight() );
                }
            }
        }
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
