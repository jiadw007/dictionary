package project;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Random;

/**
 * CLASS THAT GENERATE THE RANDOM ARRAY OF KEY FROM 1 THROUGH N
 * @author DAWEI JIA
 *
 */
class randomNumber{
	private int size;
    
	/*constructor method*/
	public randomNumber(){
		
	}
	public randomNumber(int size){
		this.size=size;
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
	
	public int[] createKeys(){
		
		int[] sequence=new int[size];
		int[] output=new int[size];
		
		for(int i=1;i<=this.size;i++){
			sequence[i-1]=i;
		}
		
		Random key=new Random();
		
		int end=this.size-1;
			
        for(int i=0;i<this.size;i++){
        	int num=key.nextInt(end+1);
        	output[i]=sequence[num];
        	sequence[num]=sequence[end];
        	end--;
        	//System.out.println(output[i]);
        }
		return output;
	}
	
	/*
	public ArrayList<Integer> createKeys(int size){
		int number;
		Random key=new Random();
		ArrayList<Integer> keys=new ArrayList<Integer>();
		for(int i=0;i<size-1;i++){
			number=key.nextInt(size);
			keys.add(number);
			for(int j=0;j<i;j++){
				if(keys.get(j).compareTo(keys.get(i))==0){
					i--;
					break;
				}
			}
			
		}
		return keys;
	}
	*/
	
}
/**
 * THE INSERT AND SEARCH EXPERIMENT IN RANDOM MODE
 * @author DAWEI JIA
 *
 */
class randomMode{
	
	private int size;      //the size of random mode
	private int hashSize;  //the size of hash table
	private int order;     //the order of Btree
	private int[]keys;     
	private int[]value;
	
	/*constructor method*/
	public randomMode(){
		String str="";
		this.size=1000000;
		
        /*
		//input the size of the random mode 
		System.out.print("Please enter the size of the random mode: ");
		try{
			BufferedReader in=new BufferedReader(new InputStreamReader(System.in));     //输入一个数
            str=in.readLine();
		}catch(IOException e){}
		this.size=Integer.parseInt(str);
		*/
		
		//input the order of the btree
		System.out.print("Please enter the order of the btree: ");
		try{
			boolean noStr=true;
			while(noStr){
				BufferedReader in=new BufferedReader(new InputStreamReader(System.in));     //输入一个数
			    str=in.readLine();
			    if("".equals(str)){
			    	System.out.print("Please enter the order of the btree,try again: ");
			    }else{
			    	noStr=false;
			    }
			}
		}catch(IOException e){}
		this.order=Integer.parseInt(str);
				
		//input the size of the hash table
		System.out.print("Please enter the size of the hash: ");
		try{
			boolean noStr=true;
			while(noStr){
				BufferedReader in=new BufferedReader(new InputStreamReader(System.in));     //输入一个数
			    str=in.readLine();
			    if("".equals(str)){
			    	System.out.print("Please enter the size of hash,try again: ");
			    }else{
			    	noStr=false;
			    }
			}
		}catch(IOException e){}
		this.hashSize=Integer.parseInt(str);
		
		//generate a random permutation of the keys 1 through n
		int[] key=new int[this.size];        // the key array
		this.value=new int[this.size];      // the value array
		this.keys=new int[this.size];       // the keys array
		randomNumber rn=new randomNumber(size);
		key=rn.createKeys();
		for(int i=0;i<size;i++){
			keys[i]=key[i];
			value[i]=2*keys[i];
			//System.out.println(keys[i]);
		}
	}
	/**
	 * random mode execute method
	 */
	public void execute(){
        
		long start=0;  //time counter
        long stop=0;   //time counter
        long time=0;   //time counter
        
        
		//define the object of every data structure
		AVLTree<Integer,Integer> avltree=new AVLTree<Integer,Integer>();
		
		AVLHash<Integer,Integer> avlhash=new AVLHash<Integer,Integer>(hashSize);
		
		BTree<Integer,Integer> btree=new BTree<Integer,Integer>(order);
		
		BTreeHash<Integer,Integer> btreehash=new BTreeHash<Integer,Integer>(hashSize,order);
		
		RedBlackTree<Integer,Integer> rbtree=new RedBlackTree<Integer,Integer>();
		
		RedBlackHash<Integer,Integer> rbhash=new RedBlackHash<Integer,Integer>(hashSize);
		
		//execution time of inserting into AVLTree algorithm
		start=System.currentTimeMillis();
		System.out.println("start time of inserting into AVLTree: "+start);
		for(int i=0;i<size;i++){
			
			//if(i==size-1){
				//System.out.print(a[i]);
			//}else{
				//System.out.print(a[i]+",");
			//}

			avltree.insertANode(keys[i], value[i]);
		}	
		stop=System.currentTimeMillis();
		System.out.println("stop time of inserting into AVLTree: "+stop);
		time=stop-start;
		System.out.println("execution time of inserting into AVLTree algorithm: "+time);
        
		//execution time of searching a random key in AVLTree algorithm
		start=System.currentTimeMillis();
		System.out.println("start time of searching in AVLTree: "+start);
		for(int i=0;i<size;i++){
			avltree.searchValue(keys[i]);
		}
		stop=System.currentTimeMillis();
		System.out.println("stop time of searching in AVLTree: "+stop);
		time=stop-start;
		System.out.println("execution time of searching in AVLTree algorithm: "+time);
		
		//execution time of inserting into AVLHash algorithm
		start=System.currentTimeMillis();
		System.out.println("start time of inserting into AVLHash: "+start);
		for(int i=0;i<size;i++){
			avlhash.InsertIntoAVLTree(keys[i], value[i]);
		}
		stop=System.currentTimeMillis();
		System.out.println("stop time of inserting into AVLHash: "+stop);
		time=stop-start;
		System.out.println("execution time of inserting into AVLHash algorithm: "+time);
		
		//execution time of searching a random key in AVLHash algorithm
		start=System.currentTimeMillis();
		System.out.println("start time of searching in AVLHash: "+start);
		for(int i=0;i<size;i++){
			avlhash.SearchInAVLTree(keys[i]);
		}
		stop=System.currentTimeMillis();
		System.out.println("stop time of searching in AVLHash: "+stop);
		time=stop-start;
		System.out.println("execution time of searching in AVLHash algorithm: "+time);
		
		
		//execution time of inserting into RedBlackTree algorithm
		start=System.currentTimeMillis();
		System.out.println("start time of inserting into RedBlackTree: "+start);
		for(int i=0;i<size;i++){
			rbtree.insertPair(keys[i], value[i]);
		}
		stop=System.currentTimeMillis();
		System.out.println("stop time of inserting into RedBlackTree: "+stop);
		time=stop-start;
		System.out.println("execution time of inserting into RedBlackTree algorithm: "+time);
		
		//execution time of searching a random key in RedBlackTree algorithm
		start=System.currentTimeMillis();
		System.out.println("start time of searching in RedBlackTree: "+start);
		for(int i=0;i<size;i++){
			rbtree.searchPair(keys[i]);
		}
		stop=System.currentTimeMillis();
		System.out.println("stop time of searching in RedBlackTree: "+stop);
		time=stop-start;
		System.out.println("execution time of searching in RedBlackTree algorithm: "+time);
		
		//execution time of inserting into RedBlackTreeHash algorithm
		start=System.currentTimeMillis();
		System.out.println("start time of inserting into RedBlackTreeHash: "+start);
		for(int i=0;i<size;i++){
			rbhash.InsertIntoRedBlackTree(keys[i],value[i]);
		}
		stop=System.currentTimeMillis();
		System.out.println("stop time of inserting into RedBlackTreeHash: "+stop);
		time=stop-start;
		System.out.println("execution time of inserting into RedBlackTreeHash algorithm: "+time);
		
		//execution time of searching a random key in RedBlackTreeHash algorithm
		start=System.currentTimeMillis();
		System.out.println("start time of searching in RedBlackTreeHash: "+start);
		for(int i=0;i<size;i++){
			rbhash.SearchInRedBlackTree(keys[i]);
		}
		stop=System.currentTimeMillis();
		System.out.println("stop time of searching in RedBlackTreeHash: "+stop);
		time=stop-start;
		System.out.println("execution time of searching in RedBlackTreeHash algorithm: "+time);
		
		//execution time of inserting into BTree algorithm
		start=System.currentTimeMillis();
		System.out.println("start time of inserting into BTree: "+start);
		for(int i=0;i<size;i++){
			Node<Integer,Integer> pair=new Node<Integer,Integer>();
			pair.setKey(keys[i]);
		    pair.setValue(value[i]);
			btree.insertBNode(pair);
		}
		stop=System.currentTimeMillis();
		System.out.println("stop time of inserting into BTree: "+stop);
		time=stop-start;
		System.out.println("execution time of inserting into BTree algorithm: "+time);
		
		//execution time of searching a random key in BTree algorithm
		start=System.currentTimeMillis();
		System.out.println("start time of searching in BTree: "+start);
		for(int i=0;i<size;i++){
			btree.searchValue(keys[i]);
		}
		stop=System.currentTimeMillis();
		System.out.println("stop time of searching in BTree: "+stop);
		time=stop-start;
		System.out.println("execution time of searching in BTree algorithm: "+time);
		
		//execution time of inserting into BTreeHash algorithm
		start=System.currentTimeMillis();
		System.out.println("start time of inserting into BTreeHash	: "+start);
		for(int i=0;i<size;i++){
			btreehash.InsertIntoBtree(keys[i], value[i]);
		}
		stop=System.currentTimeMillis();
		System.out.println("stop time of inserting into BTreeHash : "+stop);
		time=stop-start;
		System.out.println("execution time of inserting into BTreeHash algorithm: "+time);
		
		
		//execution time of searching a random key in BTreeHash algorithm
		start=System.currentTimeMillis();
		System.out.println("start time of searching in BTreeHash: "+start);
		for(int i=0;i<size;i++){
			btreehash.searchInBtree(keys[i]);
		}
		stop=System.currentTimeMillis();
		System.out.println("stop time of searching in BTreeHash:"+stop);
		time=stop-start;
		System.out.println("execution time of searching in BTreeHash algorithm: "+time);
		
		//System.out.println();
		//avltree.printInOrder();
	}
	/**
	 * BTree execute method
	 */
	public void executeBTree(){
		
		long start=0;  //time counter
        long stop=0;   //time counter
        long time=0;   //time counter
        BTree<Integer,Integer> btree=new BTree<Integer,Integer>(order);
        
        BTreeHash<Integer,Integer> btreehash=new BTreeHash<Integer,Integer>(hashSize,order);
        
        //execution time of inserting into BTree algorithm
      	start=System.currentTimeMillis();
      	System.out.println("start time of inserting into BTree: "+start);
      	for(int i=0;i<size;i++){
      		Node<Integer,Integer> pair=new Node<Integer,Integer>();
      		pair.setKey(keys[i]);
      		pair.setValue(value[i]);
      		btree.insertBNode(pair);
      	}
      	stop=System.currentTimeMillis();
      	System.out.println("stop time of inserting into BTree: "+stop);
      	time=stop-start;
      	System.out.println("execution time of inserting into BTree algorithm: "+time);
      	
		//execution time of searching a random key in BTree algorithm
		start=System.currentTimeMillis();
		System.out.println("start time of searching in BTree: "+start);
		for(int i=0;i<size;i++){
			btree.searchValue(keys[i]);
		}
		stop=System.currentTimeMillis();
		System.out.println("stop time of searching in BTree: "+stop);
		time=stop-start;
		System.out.println("execution time of searching in BTree algorithm: "+time);
		/*
        //execution time of inserting a random key in BTreeHash algorithm
		start=System.currentTimeMillis();
		System.out.println("start time of inserting into BTreeHash	: "+start);
		for(int i=0;i<size;i++){
			btreehash.InsertIntoBtree(keys[i], value[i]);
		}
		stop=System.currentTimeMillis();
		System.out.println("stop time of inserting into BTreeHash : "+stop);
		time=stop-start;
		System.out.println("execution time of inserting into BTreeHash algorithm: "+time);
		
		
		//execution time of searching a random key in BTreeHash algorithm
		start=System.currentTimeMillis();
		System.out.println("start time of searching in BTreeHash: "+start);
		for(int i=0;i<size;i++){
			btreehash.searchInBtree(keys[i]);
		}
		stop=System.currentTimeMillis();
		System.out.println("stop time of searching in BTreeHash:"+stop);
		time=stop-start;
		System.out.println("execution time of searching in BTreeHash algorithm: "+time);
		*/
	}
}
/**
 * class that allows user input pairs from file and print the results of data structure to file
 * @author DAWEI JIA
 *
 */
class userInputMode{
	
	private String fileName;   //file name
	
	 /*constructor method*/
	public userInputMode(){
		System.out.print("file name (file in the project file Or input the path of file name, format:C:/path/filename): ");
		String str="";
		try{
			boolean noStr=true;
			while(noStr){
				BufferedReader in=new BufferedReader(new InputStreamReader(System.in));     //输入一个数
			    str=in.readLine();
			    if("".equals(str)){
			    	System.out.print("Please enter file name,try again: ");
			    }else{
			    	noStr=false;
			    }
			}
			
		}catch(IOException e){}
		fileName=str;
	}
	public userInputMode(String filename){
		fileName=filename;
	}
	/**
	 * user input mode execute method
	 */
	public void execute(){
		
		//define the object of every data structure
		AVLTree<Integer,Integer> avltree=new AVLTree<Integer,Integer>();
				
	    AVLHash<Integer,Integer> avlhash=new AVLHash<Integer,Integer>(3);
				
		BTree<Integer,Integer> btree=new BTree<Integer,Integer>(3);
				
		BTreeHash<Integer,Integer> btreehash=new BTreeHash<Integer,Integer>(3,3);
				
		RedBlackTree<Integer,Integer> rbtree=new RedBlackTree<Integer,Integer>();
				
		RedBlackHash<Integer,Integer> rbhash=new RedBlackHash<Integer,Integer>(3);
		
		int count=0;
		try{
			FileReader file=new FileReader(fileName);
			LineNumberReader in=new LineNumberReader(file);
			boolean eof=false;
			
			while(!eof){
				
				String s=in.readLine();
				
				if(s==null){
					eof=true;
					if(count==0){
						System.out.print("the file not exist or not valid");
					}
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
						/*insert operation*/
						
						//System.out.println(count+" user data being insert AVLTree");
						avltree.insertaNode((Integer)pair.getKey(),(Integer)pair.getValue());
						//System.out.println(count+ " user data being insert AVLHash");
						avlhash.InsertintoAVLTree((Integer)pair.getKey(), (Integer)pair.getValue());
						//System.out.println(count+" user data being insert Btree");
						btree.insertBNode(pair);
						//System.out.println(count+" user data being insert BTreeHash");
						btreehash.InsertIntoBtree((Integer)pair.getKey(), (Integer)pair.getValue());
						//System.out.println(count+" user data being insert RBTree");
						rbtree.insertPair((Integer)pair.getKey(), (Integer)pair.getValue());
						//System.out.println(count+" user data being insert RBHash");
						rbhash.InsertIntoRedBlackTree((Integer)pair.getKey(), (Integer)pair.getValue());
						count++;
					}
				}
			}
		}catch(IOException e){}
		
		//avltree output into file
		avltree.printInOrder();
		avltree.AVL_inorder();
		avltree.printPostOrder();
		avltree.AVL_postorder();
		
		//avlhash output into file
		avlhash.printInOrder();
		avlhash.printPostOrder();
		
		//rbhash output into file
		rbhash.printInOrder();
		rbhash.printPostOrder();
		
		//btree output into file
		btree.printSortedOrder();
		btree.printOutFile();
		btree.printLevelOrder();
		
		//btreehash output into file
		btreehash.printSortedOrder();
		btreehash.printLevelOrder();
		System.out.println("All output files have been generated!");
	}
}
/**
 * THE DICITIONARY 
 * @author DAWEI JIA
 *
 * @param <T>
 * @param <V>
 */
public class dicitionary<T extends Comparable,V> {
	
	public static void main(String args[]){
		
		String str="";
		System.out.print("Please choose your mode(1 for random mode, 2 for user input mode ): ");
		try{		
		    BufferedReader in=new BufferedReader(new InputStreamReader(System.in));     //输入一个数
			str=in.readLine(); 	
		}catch(IOException e){}
		
		
		if(str.equals("1")){
			
			/*test part for randomMode*/
			randomMode rm=new randomMode();
            
			for(int j=1;j<=11;j++){
				System.out.println("time information for the "+j+" time: ");
				rm.execute();
				System.out.println();
				
			}
		    
			/*
			for(int j=1;j<=11;j++){
				System.out.println("time information for the "+j+" time: ");
				rm.executeBTree();
				System.out.println();
				
			}
			*/
			//rm.execute();
			//rm.executeBTree();
		}else if(str.equals("2")){
			/* test part for userInputMode*/
			userInputMode uim=new userInputMode();
			uim.execute();
		}else{
			System.out.println("You have no accurate command!");
			System.out.println("Please run this program again");
		}     
	}

}
