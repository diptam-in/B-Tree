
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
public class Node {
    Vector<Integer> elements;
    Vector<Node> pointers;
    int currdegree;
    int maxdegree;
    boolean isleaf;
    Node leftsibling;
    Node rightsibling;
    
    Node(int n)
    {
        maxdegree = n;
        currdegree=0;
        elements = new Vector<>(n);
        pointers = new Vector<> (n+1);
        isleaf = true;
        leftsibling = null;
        rightsibling = null;
    }
    Node(Vector<Integer> elem,Node ls, Node rs, int n)
    {
        maxdegree = n;
        elements = elem;
        pointers = new Vector<> (n+1);
        this.leftsibling = ls;
        this.rightsibling = rs;
        isleaf = true;
        currdegree=elem.size();
    }
    
    Node(Vector<Integer> elem, Vector<Node> ptr ,int n)
    {
        maxdegree = n;
        elements = elem;
        pointers = ptr;
        this.leftsibling = null;
        this.rightsibling = null;
        isleaf = false;
        currdegree=elem.size();
    }
    
    String getAllElements()
    {
        return elements.toString() + " " + "degree: "+ currdegree;
    }
    
    Vector<Node> getAllPointers()
    {
        return pointers;
    }
    boolean isLeaf()
    {
        return isleaf;
    }
    
    void addElement(int num)
    {
        int size = elements.size();
        int i;
        for(i=0;i<size;i++)
        {
            if(elements.get(i)>num)
                break;
        }
        currdegree++;
        elements.insertElementAt(num, i);
    }
    
    void addElement(Node newnode,int num)
    {
        int size = elements.size();
        int i;
        for(i=0;i<size;i++)
        {
            if(elements.get(i)>num)
                break;
        }
        currdegree++;
        elements.insertElementAt(num, i);
        pointers.insertElementAt(newnode, i);
    }
    
}
