package BSTree;

public class node {//base node class
    private String title;
    private int available;
    private int copies;
    private node left;
    private node right;
    //constructors
    public node(){title = ""; available = 0; copies = 0; left = null; right = null;}
    public node(String title, int available,int copies, node left, node right){this.title = title; this.available = available; this.copies = copies; this.left = left; this.right = right;}
    //accessors
    public String getTitle(){return title;}
    public int getAvailable(){return available;}
    public int getCopies(){return copies;}
    public node getLeft(){return left;}
    public node getRight(){return right;}
    //mutators
    public void setTitle(String title){this.title = title;}
    public void setAvailable(int available){this.available = available;}
    public void setCopies(int copies){this.copies = copies;}
    public void setRight(node right){this.right = right;}
    public void setLeft(node left){this.left = left;}
}
