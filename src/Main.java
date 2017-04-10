import java.io.File;
import java.util.Scanner;
import BSTree.BSTree;
import BSTree.node;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.regex.*;

public class Main {
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args)throws FileNotFoundException{
        BSTree tree = new BSTree();
        PrintWriter error = new PrintWriter("error.log");
        try {
            String line, title;
            Scanner fileS = new Scanner(new File("inventory.dat"));
            while(fileS.hasNext()) {
                line = fileS.nextLine();
                Scanner in = new Scanner(line);
                in.useDelimiter(",");
                title = in.next();
                title = title.substring(1,title.length()-1);
                tree.addNode(new node(title, in.nextInt(), in.nextInt(), null, null));
            }
            fileS.close();
            tree.print(tree.getRoot());
        } catch (FileNotFoundException e){System.out.println("File not found" + e);}//catch error if file not found

        Scanner t = new Scanner(new File("transaction.log"));
        while(t.hasNext()){
            String line = t.nextLine();
                switch(line.substring(0, 3))
                {
                case "add":
                        if(Pattern.compile("[A-Za-z]+\\s+\\\"+[A-za-z0-9\\s\\:\\,\\;\\'\\\\\\.\\?\\!\\(\\)\\{\\}\\[\\]]+\\\"+\\,+[0-9]").matcher(line).find()){
                            line = line.substring(0,5);
                            String title = line.substring(0, line.indexOf('\"'));
                            line = line.substring(line.indexOf("\"")+2);
                            tree.addNode(new node(title, Integer.parseInt(line), 0,null,null));
                        }
                        else
                            error.println(line);
                        break;
                case "rem":
                        if(Pattern.compile("[A-Za-z]+\\s+\\\"+[A-za-z0-9\\s\\:\\,\\;\\'\\\\\\.\\?\\!\\(\\)\\{\\}\\[\\]]+\\\"+\\,+[0-9]").matcher(line).find()){
                            line = line.substring(0,8);
                            String title = line.substring(0, line.indexOf('\"'));
                            line = line.substring(line.indexOf("\"")+2);
                            tree.addNode(new node(title, Integer.parseInt(line)*-1, 0,null,null));
                        }
                        else
                            error.println(line);
                        break;
                case "ren":
                        if(Pattern.compile("[A-Za-z]+\\s+\\\"+[A-za-z0-9\\s\\:\\,\\;\\'\\\\\\.\\?\\!\\(\\)\\{\\}\\[\\]]+\\\"").matcher(line).find()){
                            line = line.substring(0,6);
                            tree.addNode(new node(line.substring(0, line.indexOf('\"')), -1, -1,null,null));
                        }
                        else
                            error.println(line);
                        break;
                case "ret":
                        if(Pattern.compile("[A-Za-z]+\\s+\\\"+[A-za-z0-9\\s\\:\\,\\;\\'\\\\\\.\\?\\!\\(\\)\\{\\}\\[\\]]+\\\"").matcher(line).find()){
                            line = line.substring(0,8);
                            tree.addNode(new node(line.substring(0, line.indexOf('\"')), +1, -1,null,null));
                        }
                        else
                            error.println(line);
                        break;
                default:
                        System.out.println("error line");
                        error.println(line);

            }
        }
        PrintWriter out = new PrintWriter("redbox_kiosk.txt");
        tree.print(tree.getRoot(), out);
    }
}
