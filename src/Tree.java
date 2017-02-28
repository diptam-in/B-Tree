
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author diptam
 */
public class Tree {
    
    
    public static void main(String args[]) throws IOException
    {
        int elem,choice,n = 4;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Node btree = new Node(n);
        Vector<Integer> tempelem;
        Vector<Node> tempnode;
        
        while(true)
        {
            System.out.println("\n\n1.Insert\n2.Display\n3.Find\n4.Exit\n");
            choice = Integer.parseInt(reader.readLine());
            
            switch(choice)
            {
                case 1: System.out.println("Enter Element:\n");
                        elem = Integer.parseInt(reader.readLine());
                        Node newtree = insertElement(btree,elem);
                        if(newtree!=null)
                        {
                           tempelem = new Vector<>(n); 
                           tempnode = new Vector<>(n+1); 
                           tempelem.add(newtree.elements.get(0));
                           tempnode.add(btree);
                           tempnode.add(newtree);
                           if(!newtree.isLeaf())
                           {
                               newtree.elements.removeElementAt(0);
                               newtree.currdegree--;
                           }    
                           newtree = new Node(tempelem,tempnode,n);
                           btree= newtree;
                        }
                        break;
                case 2: displayTree(btree); break;
                case 3: System.out.println("Enter Element:\n");
                        elem = Integer.parseInt(reader.readLine());
                        if(isThere(btree,elem))
                            System.out.println("YES\n");
                        else
                            System.out.println("NO\n");
                        break;
                default : System.exit(0);    
            }
        }
    }

    private static void displayTree(Node btree) {
        Queue<Node> nodes = new LinkedList<>();
        Vector<Node> nextnodes;
        nodes.add(btree);
        Node node;
        int count=0;
        while(!nodes.isEmpty())
        {
           node = nodes.poll();
           ++count;
           if(!node.isLeaf())
           {
               nextnodes = node.getAllPointers();
               for(Node i : nextnodes){
                   if(i!=null)
                       nodes.add(i);
               }
           }
           System.out.println("Node Num: "+count + " "+node.getAllElements());
        }
    }

    private static Node insertElement(Node btree, int elem) {
       if(btree.isLeaf())
       {
           btree.addElement(elem);
           if(btree.currdegree < btree.maxdegree)
            return null;
           Node splittednode = new Node(new Vector<>(btree.elements.subList(btree.maxdegree/2,btree.maxdegree)),btree,null,btree.maxdegree);
           btree.elements.subList(btree.maxdegree/2,btree.maxdegree).clear();
           btree.currdegree = btree.maxdegree/2;
           btree.rightsibling=splittednode;
           return splittednode;
       }
       else
       {
           int i;
           for(i=0;i<btree.currdegree;i++)
           {
               if(elem<btree.elements.get(i))
                   break;
           }
           Node newtree = insertElement(btree.pointers.get(i),elem);
           if(newtree!=null)
           {
               int hold =newtree.elements.get(0);
               btree.addElement(newtree,hold);
               if(!newtree.isLeaf()){
                   newtree.elements.removeElementAt(0);
                   newtree.currdegree--;
               }
           }
           if(btree.currdegree < btree.maxdegree)
            return null;
           
           Node splittednode = new Node(new Vector<>(btree.elements.subList(btree.maxdegree/2,btree.maxdegree))
                   ,new Vector<>(btree.pointers.subList((btree.pointers.size()+1)/2, btree.pointers.size())),btree.maxdegree);
           btree.elements.subList(btree.maxdegree/2,btree.maxdegree).clear();
           btree.pointers.subList((btree.pointers.size()+1)/2, btree.pointers.size()).clear();
           btree.currdegree = btree.maxdegree/2;
           return splittednode;
       }
    }

    private static boolean isThere(Node btree, int elem) {
        Node curr = btree;
        int i;
        while(!curr.isLeaf())
        {
            for(i=0;i<curr.currdegree;i++)
            {
                if(elem < curr.elements.get(i))
                    break;
            }
            curr = curr.pointers.get(i);
        }
        return curr.elements.contains(elem);
    }
    
}
