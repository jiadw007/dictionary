package project;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
/**
 * THE TYPE OF NODE IN AVL TREE
 * @author Dawei Jia
 *
 * @param <T>
 * @param <V>
 */
class ANode<T extends Comparable, V>{
	
	private ANode<T,V> leftChild;        //left child
	
	private ANode<T,V> rightChild;        //right child
	
	private ANode<T,V> parent;        //parent
	
	private T key;        //key
	
	private V value;      //value
	
	private int bf;        //balance factor
	
	/*constructor method*/
	public ANode(){

	}
	public ANode(T key,V value){
		this.key=key;
		this.value=value;
	}
	
	/**
	 * class attribute key
	 * @return key
	 */
	public T getKey() {
		return key;
	}
	/**
	 * class attribute value
	 * @param key
	 */
	public void setKey(T key) {
		this.key = key;
	}
	/**
	 * class attribute getter
	 * @return value
	 */
	public V getValue(){
		return value;
	}
	/**
	 * class attribute setter
	 * @param value
	 */
	public void setValue(V value) {
		this.value = value;
	}
	/**
	 * class attribute getter
	 * @return leftChild
	 */
	public ANode<T,V> getLeftChild() {
		return leftChild;
	}
	/**
	 * class attribute setter
	 * @param leftChild
	 */
	public void setLeftChild(ANode<T,V> leftChild) {
		this.leftChild = leftChild;
	}
	/**
	 * class attribute getter
	 * @return rightChild
	 */
	public ANode<T,V> getRightChild() {
		return rightChild;
	}
	/**
	 * class attribute setter
	 * @param rightChild
	 */
	public void setRightChild(ANode<T,V> rightChild) {
		this.rightChild = rightChild;
	}
	/**
	 * class attribute getter
	 * @return the parent
	 */
	public ANode<T,V> getParent() {
		return parent;
	}
	/**
	 * class attribute setter
	 * @param parent
	 */
	public void setParent(ANode<T,V> parent) {
		this.parent = parent;
	}
	/**
	 * class attribute getter
	 * @return balance factor
	 */
	public int getBf() {
		return bf;
	}
	/**
	 * class attribute setter
	 */
	public void setBf() {
		this.bf=this.leftHeight()-this.rightHieght();
	}

	public void setBf(int bf) {
		this.bf = bf;
	}
	/* method for the height of left tree and right tree*/
	public int leftHeight(){
		return calculateHeight(leftChild);
	}
	public int rightHieght(){
		return calculateHeight(rightChild);
	}
	/**
	 *calculate the height of this tree 
	 *@return the height of this tree
	 */
	public int calculateHeight(){
		return calculateHeight(this);
	}
	/**
	 * calculate the height of tree in parameter
	 * @param tree
	 * @return the height of tree in parameter
	 */
	public int calculateHeight(ANode<T,V> tree){
		if(tree==null){
			return 0;
		}
		int leftHeight=calculateHeight(tree.leftChild);
		int rightHeight=calculateHeight(tree.rightChild);
		
		//return 1+(leftHeight > rightHeight?leftHeight:rightHeight);
		if(leftHeight>rightHeight){
			return leftHeight+1;
		}else{
			return rightHeight+1;
		}	
	}
}

/**
 * DEFINITION OF AVL TREE
 * @author DAWEI JIA
 *
 * @param <T>
 * @param <V>
 */
public class AVLTree<T extends Comparable,V> {
	
	private ANode<T,V> root;       //root

	private ArrayList<V> values;    // the list of values
	
	public AVLTree(){
		
	}
	
	/**
	 * class attribute getter
	 * @return values
	 */
	public ArrayList<V> getValues() {
		return values;
	}

    /**
     * class attribute setter
     * @param values
     */
	public void setValues(ArrayList<V> values) {
		this.values = values;
	}


	/**
	 *  insert method
	 * @param value
	 */
	public void insertANode(T key,V value){
		//System.out.println("start to insert: ("+key+","+value+")");
		if(root==null){
			root=new ANode(key,value);
			return;
		}else{
			root=insertANode(root,key,value);
		}
	}
	/**
	 * the logic process for insert a node
	 * @param parent
	 * @param value
	 * @return the complete tree
	 */
	public ANode<T,V> insertANode(ANode<T,V> parent, T key,V value){
		/*if parent is null, it means it will insert a new node.
		 *the new node is a leaf.
		 * */
		//System.out.println(parent.getValue());
		if(parent==null){
			ANode<T,V> newNode=new ANode<T,V>(key,value);
			newNode.setBf();
			return newNode;
		}
		if(parent.getKey().compareTo(key)==0){
			return parent;
		}
		if(parent.getKey().compareTo(key)>0){
			parent.setLeftChild(insertANode(parent.getLeftChild(), key, value));
			//parent.setBf();
		}else{
			parent.setRightChild(insertANode(parent.getRightChild(), key, value));
			//parent.setBf();
		}
		
		/*leftHeight is 2 more than rightHeight*/
        if(parent.getBf()>=2){
        	parent=LRotation(parent);
        	parent.setBf();
        
        }else if(parent.getBf()<=-2){ /* rightHeight i s 2 more than leftHeight*/
        	parent=RRotation(parent);
        	parent.setBf();
        } 
        return parent;
        
        
        /*System.out.println("("+parent.getKey()+","+parent.getValue()+")"+
        		           "  >>>  leftHeight:"+parent.leftHeight()+
        		           "   rightHeight:"+parent.rightHieght()+
        		           "   balanceFactor:"+parent.getBf());
        		           */
	}
	
	
	public void insertaNode(T key,V value){
		//System.out.println("start to insert: ("+key+","+value+")");
		if(root==null){
			root=new ANode(key,value);
			return;
		}else{
			root=insertaNode(root,key,value);
		}
	}
	public ANode<T,V> insertaNode(ANode<T,V> parent, T key,V value){
		/*if parent is null, it means it will insert a new node.
		 *the new node is a leaf.
		 * */
		//System.out.println(parent.getValue());
		if(parent==null){
			ANode<T,V> newNode=new ANode<T,V>(key,value);
			newNode.setBf();
			return newNode;
		}
		if(parent.getKey().compareTo(key)==0){
			return parent;
		}
		if(parent.getKey().compareTo(key)>0){
			parent.setLeftChild(insertaNode(parent.getLeftChild(), key, value));
			parent.setBf();
		}else{
			parent.setRightChild(insertaNode(parent.getRightChild(), key, value));
			parent.setBf();
		}
		
		/*leftHeight is 2 more than rightHeight*/
        if(parent.getBf()>=2){
        	parent=LRotation(parent);
        	parent.setBf();
        
        }else if(parent.getBf()<=-2){ /* rightHeight is 2 more than leftHeight*/
        	parent=RRotation(parent);
        	parent.setBf();
        } 
        return parent;
        
        
        /*System.out.println("("+parent.getKey()+","+parent.getValue()+")"+
        		           "  >>>  leftHeight:"+parent.leftHeight()+
        		           "   rightHeight:"+parent.rightHieght()+
        		           "   balanceFactor:"+parent.getBf());
        		           */
	}
	/**
	 * search method
	 * @param value
	 */
	public void searchValue(T key){
		
		//System.out.println("start to search: "+key);
		ANode<T,V> flag=null;
		ANode<T,V> parent=this.root;
		if(root==null){
			return;
		}
		while(parent!=null){
			if(key.compareTo(parent.getKey())==0){
				//System.out.println("find this (key,value): ("+parent.getKey()+","+parent.getValue()+")");
				break;
			}
			if(key.compareTo(parent.getKey())<0){
				parent=parent.getLeftChild();
			}
			if(key.compareTo(parent.getKey())>0){
				parent=parent.getRightChild();
			}
		}
		if(parent==null){
			System.out.println("unable to find this (key,value)");
		}
		
	}
	
	/**
	 * delete method
	 * @param value
	 */
	public void delete(T key){
		ANode<T,V> nowRoot=new ANode<T,V>();
		System.out.println("delete the node: "+key);
		this.root=delete(this.root,key);
		nowRoot=this.root;
		System.out.println("the root of the AVL tree now is:("
		                   + nowRoot.getKey()+","+nowRoot.getValue()+")");
	}
	/**
	 * the logic process for delete a node
	 * @param parent
	 * @param value
	 * @return 
	 */
	public ANode<T,V> delete(ANode<T,V> parent, T key){
		if(parent==null){
			return null;
		}
		System.out.println("delete method in key : "+parent.getKey());
		/*to look for the node to delete*/
		
		//if the value of parent is less than value
		if(parent.getKey().compareTo(key)<0){
			
			parent.setRightChild(delete(parent.getRightChild(),key));
		}
		
		//if the value of parent is more than value
		if(parent.getKey().compareTo(key)>0){
			parent.setLeftChild(delete(parent.getLeftChild(),key));
		}
		
		//if we find the node to delete
		if(parent.getKey().compareTo(key)==0){
			// if the right subtree of parent is null
			if(parent.getRightChild()==null){
				return parent.getLeftChild();
			}
			
			//if the left subtree of parent is null
			if(parent.getLeftChild()==null){
				return parent.getRightChild();
			}
			/*now the right and left subtree of parent is not null
			 * we should find the max node of left subtree using LDR process 
			 * */
			ANode<T,V> maxLeftNode=parent.getLeftChild(); // max node in left subtree
			ANode<T,V> newParentNode=maxLeftNode;       
			while(maxLeftNode!=null){
				maxLeftNode= maxLeftNode.getRightChild();
				if(maxLeftNode!=null){
					newParentNode=maxLeftNode;
				}
			}
			
			//we should make the value of parent the value of deleteNode
			parent.setValue(newParentNode.getValue());
			
			//delete the newParentNode recursively
			parent.setLeftChild(delete(parent.getLeftChild(),newParentNode.getKey()));
			//System.out.println(newParentNode.getValue());
			//return parent;
		}
		parent.setBf();
		int balanceFactor=parent.getBf();
		if(balanceFactor>=2){
			parent=LRotation(parent);
		}else if (balanceFactor<=-2){
			parent=RRotation(parent);
		}
		return parent;
		
	}
	/**
	 * LeftRotation
	 * @param parent
	 * @return the new balance tree
	 */
	public ANode<T,V> LRotation(ANode<T,V> parent){
		ANode<T,V> leftChild=parent.getLeftChild();
		//LL rotation, need one left rotation
		if(leftChild.leftHeight()>leftChild.rightHieght()){
			
			parent=leftRotate(parent,leftChild);
            //parent.setBf();
		}else{// LR rotation, need one right rotation and one left rotation
			
			parent.setLeftChild(rightRotate(parent.getLeftChild(),parent.getLeftChild().getRightChild()));
			parent=leftRotate(parent,parent.getLeftChild());
			//parent.setBf();
		}
		return parent;
	}
	/**
	 * RightRotation
	 * @param parent
	 * @return the new balance tree
	 */
	public ANode<T,V> RRotation(ANode<T,V> parent){

		ANode<T,V> rightChild=parent.getRightChild();
		//RR rotation, need one right rotation
		if(rightChild.rightHieght()>rightChild.leftHeight()){
			
			parent=rightRotate(parent,rightChild);
			//parent.setBf();
		}else{  //RL rotation, need one left rotation and one right rotation
			
			parent.setRightChild(leftRotate(parent.getRightChild(),parent.getRightChild().getLeftChild()));
			parent=rightRotate(parent,parent.getRightChild());
			//parent.setBf();
		}
		return parent;
	}
	/**
	 *  LL or LR rotation method
	 * @param parent
	 * @param child
	 * @return child, which is the new parent
	 */
	public ANode<T,V> leftRotate(ANode<T,V> parent, ANode<T,V> child){
		if(child!=null){
			ANode<T,V> childRight=child.getRightChild();
			child.setRightChild(parent);
			parent.setLeftChild(childRight);
			//parent.setBf();

		}
		return child;
	}
	/**
	 * RR or RL rotation method
	 * @param parent
	 * @param child
	 * @return child, which is the new parent
	 */
	public ANode<T,V> rightRotate(ANode<T,V> parent, ANode<T,V> child){
		if(child!=null){
			ANode<T,V> childLeft=child.getLeftChild();
			child.setLeftChild(parent);
			parent.setRightChild(childLeft);
			//parent.setBf();
		}
		return child;
	}
	/**
	 * LDR process
	 */
	public void printInOrder(){
		values=new ArrayList<V>(); 
		printInOrder(root);
		
		System.out.println("");
	}
	/**
	 * LDR Logic Process
	 * @param root
	 */
	public void printInOrder(ANode<T,V> root){
		if(root==null){
			return;
		}
			printInOrder(root.getLeftChild());
			//root.setBf();
			
			//System.out.println("("+root.getKey()+","+root.getValue()+")  "+
					           //"(leftChild: ("+(root.getLeftChild()==null?"null":root.getLeftChild().getKey())+
					           //","+(root.getLeftChild()==null?"null":root.getLeftChild().getValue())+
					           //"),  rightChild: ("+(root.getRightChild()==null?"null":root.getRightChild().getKey())+
					           //","+(root.getRightChild()==null?"null":root.getRightChild().getValue())+
					          //"))  balance factor: "+root.getBf());
			//System.out.print(root.getValue()+",");
			values.add(root.getValue());
			printInOrder(root.getRightChild());
	
	}
	/**
	 * AVL_inorder.out
	 */
	public void AVL_inorder(){
		try{
			FileWriter fw=new FileWriter("AVL_inorder.out");
			Iterator p=values.iterator();
			String str=new String();
			while(p.hasNext()){
				str=""+p.next();
				fw.write(str);
				fw.write(",");
				//System.out.print(str+",");
			}
			fw.close();
		
		}catch(IOException e){}
		//System.out.println();
	}
	/**
	 * LRD process
	 */
	public void printPostOrder(){
		values=new ArrayList<V>(); 
		printPostOrder(root);
		
		System.out.println();
	}
	/**
	 * LRD logic process
	 * @param root
	 */
	public void printPostOrder(ANode<T,V> root){
		if(root==null){
			return;
		}
		printPostOrder(root.getLeftChild());
		printPostOrder(root.getRightChild());
		//System.out.println("("+root.getKey()+","+root.getValue()+")  "+
		           //"(leftChild: ("+(root.getLeftChild()==null?"null":root.getLeftChild().getKey())+
		           //","+(root.getLeftChild()==null?"null":root.getLeftChild().getValue())+
		           //"),  rightChild: ("+(root.getRightChild()==null?"null":root.getRightChild().getKey())+
		           //","+(root.getRightChild()==null?"null":root.getRightChild().getValue())+
		          //"))");
		values.add(root.getValue());
		//System.out.print(root.getValue()+",");
	}
	/**
	 * AVL_postorder.out
	 */
	public void AVL_postorder(){
		try{
			FileWriter fw=new FileWriter("AVL_postorder.out");
			Iterator p=values.iterator();
			String str=new String();
			while(p.hasNext()){
				str=""+p.next();
				fw.write(str);
				fw.write(",");
				//System.out.print(str+",");
			}
			fw.close();
		}catch(IOException e){}
	}
	/* test part*/
	/*
	public static void main(String args[]){
		AVLTree<Integer,Integer> tree = new AVLTree<Integer,Integer>();
		tree.insertANode(3,6);
		tree.insertANode(5,10);
		tree.insertANode(4,8);
		tree.insertANode(10,20);
		tree.insertANode(7,14);
		tree.insertANode(11,22);
		tree.insertANode(1,2);
		tree.insertANode(2,4);
		tree.insertANode(14,28);
		tree.insertANode(15,30);
		//tree.printInOrder();
		//tree.printPostOrder();
		//tree.searchValue(15);
		tree.delete(14);                    
        tree.printInOrder(); 
	}
	*/
	
	/*test part*/
	
	public static void main(String args[]){
		int max = 20;  
        List<Integer> values = new ArrayList<Integer>();  
        for(int i = 1; i <= max; i++){  
            values.add(i);  
        }  
        Collections.shuffle(values);  
  
        Integer[] testValues = {3,5,4,10,6,12,29,31,14,2};  
        values = Arrays.asList(testValues);  
          
        for(Integer value:values)  
            System.out.print(value+"    ");  
          
        System.out.println("");  
          
        AVLTree<Integer,Integer> tree = new AVLTree<Integer,Integer>();  
        for(Integer value:values)  
            tree.insertaNode(value,value); 
        		
        System.out.println("printInOrder:");
        tree.printInOrder(); 
        System.out.println("printPostOrder:");
        tree.printPostOrder();
        /*
        tree.searchValue(16);
        System.out.println(System.currentTimeMillis()+"    "+new Date().getTime());
        
        tree.delete(14);                    
        tree.printInOrder();                 
        tree.delete(15);                    
        tree.printInOrder();   
        tree.delete(10);                    
        tree.printInOrder(); 
        tree.delete(7);                    
        tree.printInOrder(); 
        tree.delete(11);                    
        tree.printInOrder(); 
        //tree.delete(1);
        //tree.printInOrder();
        
        //System.out.println(tree.hashCode());
         */
	}


}
