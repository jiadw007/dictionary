package project;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * THE DEFINITION OF REDBLACKTREE
 * @author DAWEI JIA
 *
 * @param <T>
 * @param <V>
 */
public class RedBlackTree<T extends Comparable,V> extends TreeMap{
	
	public RedBlackTree(){
		
	}
	/**
	 * search pair in the red black tree
	 * @param key
	 */
	public void searchPair(T key){
		
		//System.out.println("start to search: "+key);
		
		boolean flag=false;
		flag=this.containsKey(key);
		if(flag==true){
			//System.out.println("find this pair: ("+key+","+this.get(key)+")");
		}else{
			System.out.println("unable to find this pair");
		}
	}
	/**
	 * insert a pair into red black tree
	 * @param key
	 * @param value
	 */
	public void insertPair(T key,V value){
		
		//System.out.println("start to insert: ("+key+","+value+")");
		
		this.put(key,value);
		
	}
	/*
	public static void main(String args[]){
		RedBlackTree<Integer> rbTree=new RedBlackTree<Integer>();
		
	}*/
}
