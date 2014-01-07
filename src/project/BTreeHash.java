package project;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * DEFITION OF B-TREE HASH TABLE
 * @author DAWEI JIA
 *
 * @param <T>
 * @param <V>
 */
public class BTreeHash <T extends Comparable,V>{
	
	private int size;      //size of hash table
	private int order;     // order of btree
	
	private BTree[] bTreeHash;
	
	/*the constructor method*/
	public BTreeHash(){
		
	}
	
	public BTreeHash(int size,int order){
		if(size%2==0){
			this.setSize(size+1);
		}else{
			this.setSize(size);
		}
		this.order=order;
		this.bTreeHash=new BTree[this.size];
		for(int i=0;i<this.size;i++){
			bTreeHash[i]=new BTree<T,V>(this.order);
		}
		
	}
	
    /**
     * class attribute getter
     * @return size
     */
	public int getSize() {
		return size;
	}
	
    /**
     * class attribute setter
     * @param size
     */
	public void setSize(int size) {
		this.size = size;
	}
	
    /**
     * class attribute getter
     * @return bTreeHash
     */
	public BTree[] getbTreeHash() {
		return bTreeHash;
	}
	
    /**
     * class attribute setter
     * @param bTreeHash
     */
	public void setbTreeHash(BTree[] bTreeHash) {
		this.bTreeHash = bTreeHash;
	}
	/**
	 * class that get hash code for key
	 * @param comparable
	 * @return keyIndex
	 */
	public int hashTable(T key){
		
		int keyIndex=key.hashCode()%this.size;
		
		return keyIndex;
	}
	/**
	 * class that insert a key into the proper BTree
	 * @param key
	 */
	public void InsertIntoBtree(T key,V value){
		Node pair=new Node();
		pair.setKey(key);
		pair.setValue(value);
		int keyIndex;
		keyIndex=this.hashTable(key);
		//System.out.println("start to insert: ("+key+","+value+")");
		bTreeHash[keyIndex].insertBNode(pair);
	}
	/**
	 * class that search a key in a proper BTree
	 * @param key
	 */
	public void searchInBtree(T key){
		//System.out.println("start to search: "+key);
		int keyIndex;
		keyIndex=this.hashTable(key);
		bTreeHash[keyIndex].searchValue(key);
		
	}
	/**
	 * BTreeHash_sorted.out
	 */
	public void printSortedOrder(){
		try{
			FileWriter fw=new FileWriter("BTreeHash_sorted.out");
			for(int i=0;i<size;i++){
				bTreeHash[i].printSortedOrder();
				Iterator p=bTreeHash[i].getValues().iterator();
				String str=new String();
				while(p.hasNext()){
					str=""+p.next();
					fw.write(str);
					fw.write(",");
					//System.out.print(str+",");
				}
			}
			fw.close();
		}catch(IOException e){}
	}
	/**
	 * BTreeHash_level.out
	 */
	public void printLevelOrder(){
		try{
			FileWriter fw=new FileWriter("BTreeHash_level.out");
			for(int i=0;i<size;i++){
				bTreeHash[i].BTreeHashLevel();
				Iterator p=bTreeHash[i].getValues().iterator();
				String str=new String();
				while(p.hasNext()){
					str=""+p.next();
					fw.write(str);
					fw.write(",");
					//System.out.print(str+",");
				}
			}
			fw.close();
		}catch(IOException e){}
	}
	
	/*
	public static void main(String args[]){
		
	}
	*/
}
