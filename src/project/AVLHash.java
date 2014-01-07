package project;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * DEFINITION OF  AVLTREE HASHTABLE
 * @author DAWEI JIA
 *
 * @param <T>
 * @param <V>
 */
public class AVLHash<T extends Comparable,V> {
	
	private int size ;     //size of hash table
	
	private AVLTree[] avlHash;
	
	/* the constructor method*/
	public AVLHash(){
		
	}
	public AVLHash(int size){
		if(size%2==0){
			this.size=size+1;
		}else{
			this.size=size;
		}
		
		this.avlHash=new AVLTree[this.size];
		for(int i=0;i<this.size;i++){
			avlHash[i]=new AVLTree<T,V>();
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
     * @return avlHash
     */
	public AVLTree[] getAvlHash() {
		return avlHash;
	}
    /**
     * class attribute getter
     * @param avlHash
     */
	public void setAvlHash(AVLTree[] avlHash) {
		this.avlHash = avlHash;
	}
	/**
	 * class that get hash code for key
	 * @param key
	 * @return keyIndex
	 */
	public int hashTable(T key){
		
		int keyIndex=key.hashCode()%this.size;
		
		return keyIndex;
	}
	/**
	 * class that insert a key into the proper AVLTree
	 * @param key
	 */
	public void InsertIntoAVLTree(T key,V value){
		
		int keyIndex;
		keyIndex=this.hashTable(key);
		//System.out.println("start to insert: ("+key+","+value+")");
        avlHash[keyIndex].insertANode(key,value);

	}

	/**
	 * class that search a key in a proper AVLTree
	 * @param key
	 */
	public void SearchInAVLTree(T key){
		//System.out.println("start to search: "+key);
		int keyIndex;
		keyIndex=this.hashTable(key);
		avlHash[keyIndex].searchValue(key);
		
	}
	/**
	 * AVLHash_inorder.out
	 */
	public void printInOrder(){
		try{
			FileWriter fw=new FileWriter("AVLHash_inorder.out");
			for(int i=0;i<size;i++){
				avlHash[i].printInOrder();
				Iterator p=avlHash[i].getValues().iterator();
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
	 * AVLHash_postorder.out
	 */
	public void printPostOrder(){
		try{
			FileWriter fw=new FileWriter("AVLHash_postorder.out");
			for(int i=size-1;i>=0;i--){
				avlHash[i].printPostOrder();
				Iterator p=avlHash[i].getValues().iterator();
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
	public void InsertintoAVLTree(T key,V value){
		
		int keyIndex;
		keyIndex=this.hashTable(key);
		//System.out.println("start to insert: ("+key+","+value+")");
        avlHash[keyIndex].insertaNode(key,value);

	}
}
