package project;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * THE TYPE OF NODE IN B-TREE
 * @author DAWEI JIA
 *
 * @param <T>
 * @param <V>
 */
class Node<T extends Comparable,V>{
	private T key;      //key
	private V value;    //value
	/*constructor method*/
	public Node(){
		
	}
	public Node(T key,V value){
		this.key=key;
		this.value=value;
	}
	/**
	 * class attribute getter
	 * @return key
	 */
	public T getKey() {
		return key;
	}
	/**
	 * class attribute setter
	 * @param key
	 */
	public void setKey(T key) {
		this.key = key;
	}
	/**
	 * class attribute getter
	 * @return value
	 */
	public V getValue() {
		return value;
	}
	/**
	 * class attribute setter
	 * @param value
	 */
	public void setValue(V value) {
		this.value = value;
	}
	
}
class BNode<T extends Comparable,V>{
	
	private int order;       //the order of the tree
	
	private int keynum;      //the number of pairs in the B-tree
	
	private ArrayList<Node> pairs; // the array of the pairs
	
	private ArrayList<BNode> children; // the array of children
	
	private ArrayList<T> key;      // the array of the key
	
	private BNode parent;          //parent of node
	
	private int position;          // position in the children of parent
	
	
	/*constructor method*/
	public BNode(int order, Node pair){
		
		this.order=order;
		this.pairs=new ArrayList<Node>();
		pairs.add(pair);
		this.children=null;
		this.parent=null;
		this.position=-1;
		this.key=new ArrayList<T>();
		key.add((T) pair.getKey());
		
	}
	public BNode(int order){
		
		this.order=order;
		this.pairs=new ArrayList<Node>();
		this.children=null;
		this.parent=null;
		this.position=-1;
		this.key=new ArrayList<T>(); 
	}
	/**
	 * class attribute getter
	 * @return order
	 */
	public int getOrder() {
		return order;
	}
	/**
	 * class attribute setter
	 * @param order
	 */
	public void setOrder(int order) {
		this.order = order;
	}
	/**
	 * class attribute getter
	 * @return Keynum
	 */
	public int getKeynum() {
		return keynum;
	}
	/**
	 * class attribute setter
	 * @param keynum
	 */
	public void setKeynum(int keynum) {
		this.keynum = keynum;
	}
	/**
	 * class attribute getter
	 * @return the parent
	 */
	public BNode getParent() {
		return parent;
	}
	/**
	 * class attribute setter
	 * @param parent
	 */
	public void setParent(BNode parent) {
		this.parent = parent;
	}
	/**
	 * class attribute getter
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * class attribute setter
	 * @param position
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	/**
	 * class attribute getter
	 * @return the pairs arrayList
	 */
	public ArrayList<Node> getPairs() {
		return pairs;
	}
	/**
	 * class attribute setter
	 * @param pairs
	 */
	public void setPairs(ArrayList<Node> pairs) {
		this.pairs = pairs;
	}
	/**
	 * class attribute getter
	 * @param key
	 */
	public ArrayList<T> getKey() {
		return key;
	}
	/**
	 * class attribute setter
	 * @param key
	 */
	public void setKey(ArrayList<T> key) {
		this.key = key;
	}
	/**
	 * class attribute getter
	 * @return children list
	 */
	public ArrayList<BNode> getChildren() {
		return children;
	}
	/**
	 * class attribute setter
	 * @param children
	 */
	public void setChildren(ArrayList<BNode> children) {
		this.children = children;
	}
	/**
	 * class attribute getter
	 * @return the size of the key list
	 */
	public int getSize(){
		return pairs.size();
	}
	/**
	 * sort all pairs
	 * @return new pairs ArrayList
	 */
	public ArrayList<Node> sortPairs(){
		Node temp=new Node();
		for(int i=0;i<this.getSize();i++){
			for(int j=i+1;j<this.getSize();j++){
				
				if(this.getPairs().get(i).getKey().compareTo(this.getPairs().get(j).getKey())>0){
					temp.setKey(this.getPairs().get(i).getKey());
					temp.setValue(this.getPairs().get(i).getValue());
					this.getPairs().get(i).setKey(this.getPairs().get(j).getKey());
					this.getPairs().get(i).setValue(this.getPairs().get(j).getValue());
				    this.getPairs().get(j).setKey(temp.getKey());
				    this.getPairs().get(j).setValue(temp.getValue());
				}
			}
		}
		return this.getPairs();
	}



}
/**
 * DEFINITION OF B-TREE
 * @author DAWEI JIA
 *
 * @param <T>
 * @param <V>
 */
public class BTree<T extends Comparable,V> {
	
	private int order;        // the order of the tree
	
	private ArrayList<V> values;
	/**
	 * class attribute getter
	 * @return
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
	private BNode root;
	/*BTree constructor*/
	public BTree(){
		this.order=3;
		this.root=null;
	}
	public BTree(int order){
		this.order=order;
		this.root=null;
	}
	/**
	 * class attribute getter
	 * @return order
	 */
	public int getOrder() {
		return order;
	}
	/**
	 * class attribute setter
	 * @param order
	 */
	public void setOrder(int order) {
		this.order = order;
	}
	/**
	 * insertBNode method
	 * @param value
	 */
	public void insertBNode(Node pair){
		
		if(root==null){ //value is the first element in the tree
			BNode bn=new BNode(this.order,pair);
			root=bn;
		}else{  //there is a existing tree
			insertToLeaf(this.root,pair);
		}
			
	}
	/**
	 * new node being inserted to leaf 
	 * @param bn
	 * @param value
	 */
	public void insertToLeaf(BNode bn,Node pair){
		
        if(bn.getKey().contains(pair.getKey())){ //value is already existing in the tree
    			return;
        }
        
		if(bn.getChildren()==null){  //bn is a leaf
			
			bn.getKey().add(pair.getKey());
			bn.getPairs().add(pair);
			//sort all the pair
			bn.setPairs(bn.sortPairs());
			//Collections.sort(bn.getKey()); //sort all keys
			//check bn to find if need to split
			//for(int i=0;i<bn.getKey().size();i++){
			//	System.out.println(bn.getKey().get(i));
			//}
			checkAndSplit(bn);
			
		}else{   //bn is not leaf, need to insert to proper child node
			Node temp=new Node();
			int pointer=0;
			while(pointer<bn.getSize()){
				temp=(Node) bn.getPairs().get(pointer);
				if(pair.getKey().compareTo(temp.getKey())<0){   //the key of pair compare to key in the list
					break;
				}
				
				pointer++;
			}
			insertToLeaf((BNode)bn.getChildren().get(pointer),pair);
		}
	}
	
	/**
	 *  check bn whether it should be split or not
	 * @param bn
	 */
	public void checkAndSplit(BNode bn){
		BNode parentNode=bn.getParent();
		if(bn.getPairs().size()>=bn.getOrder()){
			split(bn);
			if(parentNode!=null){
				checkAndSplit(bn.getParent());
			}
		}
	}
	/**
	 * the split method
	 * @param bn
	 */
	public void split(BNode bn){
		//when child and key are placed properly
		BNode parentNode=bn.getParent();
		int pointer=0;
		/* get the middle key*/
		int midKey=bn.getOrder()/2;
		if(bn.getOrder()%2==0){
			midKey--;             
		}
		
		//System.out.println("midkey "+midKey);
		//for(int i=0;i<bn.getKey().size();i++){
			//System.out.println(bn.getKey().get(i));
		//}
		//System.out.println("midkey number"+bn.getKey().get(midKey));
		
		BNode leftBranch=new BNode(bn.getOrder());   //the smaller key subtree
		BNode rightBranch=new BNode(bn.getOrder());  //the bigger key subtree
		
		while(pointer<midKey){
			leftBranch.getPairs().add(bn.getPairs().get(pointer));
			pointer++;
		}
		pointer++;      //the middle key
		while(pointer<bn.getOrder()){
			rightBranch.getPairs().add(bn.getPairs().get(pointer));
			pointer++;
		}
		
		/*for(int i=0;i<leftBranch.getKey().size();i++){
		    System.out.println("leftBanch:"+leftBranch.getKey().get(i));
	    }
		for(int i=0;i<rightBranch.getKey().size();i++){
		    System.out.println("rightBranch:"+rightBranch.getKey().get(i));
	    }*/
		
		/* the relationship of children*/
		if(bn.getChildren()!=null){ //bn is not a leaf
			pointer=0;
			ArrayList<BNode> leftChildren=new ArrayList<BNode>();
			while(pointer<=midKey){
				BNode child=(BNode)bn.getChildren().get(pointer);
				leftChildren.add(child);
				child.setPosition(pointer);
				child.setParent(leftBranch);
				pointer++;
			}
			leftBranch.setChildren(leftChildren);
			ArrayList<BNode> rightChildren=new ArrayList<BNode>();
			while(pointer<=bn.getOrder()){
				BNode child=(BNode)bn.getChildren().get(pointer);
				rightChildren.add(child);
				child.setPosition(pointer-midKey-1);
				child.setParent(rightBranch);
				pointer++;
			}
			rightBranch.setChildren(rightChildren);
		}
		if(parentNode==null){   //reach the root
			BNode newNode=new BNode(bn.getOrder());
			newNode.getPairs().add(bn.getPairs().get(midKey));
			ArrayList<BNode> children=new ArrayList<BNode>();
			children.add(leftBranch);
			children.add(rightBranch);
			leftBranch.setParent(newNode);
			rightBranch.setParent(newNode);
			leftBranch.setPosition(0);
			rightBranch.setPosition(1);
			newNode.setChildren(children);
			this.root=newNode;
			
		}else{ //not reach the root
			ArrayList<Node> currentParentPairs=parentNode.getPairs();
			/*for(int i=0;i<currentParentKey.size();i++){
			    System.out.println("currentParentKey:"+currentParentKey.get(i));
		    }
			System.out.println("bn position:"+bn.getPosition());*/
			/*if(bn.getPosition()==bn.getOrder()-1){
				System.out.println("end");
				currentParentKey.add((T) bn.getKey().get(midKey));
				parentNode.getChildren().remove(bn.getPosition());
				parentNode.getChildren().add(leftBranch);
				parentNode.getChildren().add(rightBranch);
				leftBranch.setParent(parentNode);
				rightBranch.setParent(parentNode);
				for(int i=0;i<parentNode.getChildren().size();i++){
					((BNode) parentNode.getChildren().get(i)).setPosition(i);
				}
			}else{*/
				currentParentPairs.add(bn.getPosition(),(Node)bn.getPairs().get(midKey));
				parentNode.getChildren().remove(bn.getPosition());
				parentNode.getChildren().add(bn.getPosition(), leftBranch);
				parentNode.getChildren().add(bn.getPosition()+1, rightBranch);
				leftBranch.setParent(parentNode);
				rightBranch.setParent(parentNode);
				for(int i=0;i<parentNode.getChildren().size();i++){
					((BNode) parentNode.getChildren().get(i)).setPosition(i);
				}
			//}
			}
	}
	/**
	 * search value method
	 * @param value
	 */
	public void searchValue(T key){
		//System.out.println("start to search: "+key);
		int pointer;
		int size;
		boolean flag=false;
		BNode<T,V> bn=this.root;
		while(bn!=null){
			pointer=0;
			size=bn.getSize();
			for(;pointer<size;pointer++){
				if(key.compareTo(bn.getPairs().get(pointer).getKey())==0){
					//System.out.println("find this pair: ("+bn.getPairs().get(pointer).getKey()+","+bn.getPairs().get(pointer).getValue()+")");
					flag=true;
					break;
				}
				if(key.compareTo(bn.getPairs().get(pointer).getKey())<0){
					//System.out.println(bn.getPairs().get(pointer).getKey());
					if(bn.getChildren()!=null){
						bn=bn.getChildren().get(pointer);
						break;
					}else{
						bn=null;
						break;
					}
				}
				if(key.compareTo(bn.getPairs().get(pointer).getKey())>0){
					//System.out.println(bn.getPairs().get(pointer).getKey());
					if(pointer==bn.getSize()-1){
						if(bn.getChildren()!=null){
							bn=bn.getChildren().get(pointer+1);
							break;
						}else{
							bn=null;
							break;
						}
					}
				}
			}
			if(flag==true){
				break;
			}

		}
		if(bn==null){
			System.out.println("unable to this pair");
		}
		
	}
    /**
     * printOrder for the test program
     */
	public void printOrder(){
		printOrder(root);
	}
	public void printOrder(BNode<T,V> root){
		int count;
		if(root==null){
			return;
		}
		
		for(count=0;count<root.getSize();count++){
			System.out.print("/("+root.getPairs().get(count).getKey()+","+root.getPairs().get(count).getValue()+")");
			if(count==root.getSize()-1){
				System.out.print("/");
			}
		}
		System.out.println();
		if(root.getChildren()!=null){
			for(count=0;count<root.getChildren().size();count++){
				System.out.print("No."+root.getChildren().get(count).getPosition()+"child  ");
				printOrder(root.getChildren().get(count));
			}
		}
	}
	/**
	 * print sorted order
	 */
	public void printSortedOrder(){
		
		this.values=new ArrayList<V>();
		printSortedOrder(root);
		
		System.out.print("");
	}
	/**
	 * print osrted order
	 * @param root
	 */
	public void printSortedOrder(BNode<T,V> root){
		
		if(root==null){
			return;
		}
		if(root.getChildren()!=null){
			for(int i=0;i<root.getChildren().size();i++){
				printSortedOrder(root.getChildren().get(i));
				if(i<root.getSize()){
					values.add((V) root.getPairs().get(i).getValue());
					//System.out.print(root.getPairs().get(i).getValue()+",");
				}
			}
		}else{
			for(int i=0;i<root.getPairs().size();i++){
			values.add((V) root.getPairs().get(i).getValue());
			//System.out.print(root.getPairs().get(i).getValue()+",");
		    }
		}	
	}
	/**
	 * BTree_sorted.out
	 */
	public void printOutFile(){
		
		try{
			FileWriter fw=new FileWriter("BTree_sorted.out");
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
	
	public void printLevelOrder(){
		printLevelOrder(root);
	}
	/**
	 * BTree_level.out
	 * @param root
	 */
	public void printLevelOrder(BNode<T,V> root){
		
		Queue<BNode> queue=new LinkedList <BNode>();
		queue.offer(root);
		String str=new String();
		try{
			FileWriter fw=new FileWriter("BTree_level.out");
			while(!queue.isEmpty()){
				BNode bn=queue.poll();
				for(int i=0;i<bn.getPairs().size();i++){
					Node pair=(Node) bn.getPairs().get(i);
					//System.out.print(pair.getValue()+",");
					str=""+pair.getValue();
					fw.write(str);
					fw.write(",");
				}
				if(bn.getChildren()!=null){
					for(int i=0;i<bn.getChildren().size();i++){
						queue.offer((BNode) bn.getChildren().get(i));
					}
				}
			}
			fw.close();
		}catch(IOException e){}
		
	}
	
	public void BTreeHashLevel(){
		this.values=new ArrayList<V>();
		BTreeHashLevel(root);
	}
	/**
	 * BTreeHash_Level.out
	 * @param root
	 */
	public void BTreeHashLevel(BNode<T,V> root){
		if(root==null){
			return;
		}
		Queue<BNode> queue=new LinkedList <BNode>();
		queue.offer(root);
		while(!queue.isEmpty()){
			BNode bn=queue.poll();
			for(int i=0;i<bn.getPairs().size();i++){
				Node pair=(Node) bn.getPairs().get(i);
				//System.out.print(pair.getValue()+",");
				values.add((V) pair.getValue());
			}
			if(bn.getChildren()!=null){
				for(int i=0;i<bn.getChildren().size();i++){
					queue.offer((BNode) bn.getChildren().get(i));
				}
			}
		}
	}
	/**
	 * test method
	 * @param args
	 */
    /*
	public static void main(String args[]){
		BTree<Integer,Integer> b1=new BTree<Integer,Integer>(3);
		int count=0;
		//System.out.println(b1.getOrder());
		/* random mode test part
		randomNumber rn=new randomNumber(100);
		
		int[] key=rn.createKeys();
		for(int i=0;i<100;i++){
			Node pair=new Node();
			pair.setKey(key[i]);
			pair.setValue(key[i]*2);
			System.out.println(pair.getKey());
			b1.insertBNode(pair);
		}
		
		File myFile=new File("/123.txt");
		try{
			FileReader file=new FileReader(myFile);
			FileWriter fw=new FileWriter("output.txt");
			LineNumberReader in=new LineNumberReader(file);
			boolean eof=false;
			while(!eof){
				String s=in.readLine();
				if(s==null){
					eof=true;
				}else{
					if(count==0){
						//System.out.println(Integer.parseInt(s));
						//b1.setOrder(Integer.parseInt(s));
						count++;
					}else{
						String[] split=s.split(" ");
						Node pair=new Node();
						for(int j=0;j<split.length;j++){
							//System.out.print(Integer.parseInt(split[j])+" ");
							//if(j==1){
								//System.out.println();
							//}
					        if(j==0){
					    	     pair.setKey(Integer.parseInt(split[j]));
					        }else{
							     pair.setValue(Integer.parseInt(split[j]));
					        }
						}
						b1.insertBNode(pair);
					}
				}
			}
		}catch(IOException e){}
		b1.printOrder();
	    b1.searchValue(2);
	}
    */
}
