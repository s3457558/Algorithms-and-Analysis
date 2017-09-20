package nearestNeigh;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public Point point ;
	public Node leftChild;
	public Node rightChild;
	public Node parent;
	public int direct;
	boolean isright;
	boolean isLeaf;
	
	public Node(){
		point = this.point;
		parent =null;
		leftChild=null;
		rightChild=null;
		direct=0;
		this.isLeaf = isLeaf;
		
	}
	public void setleftChild(Node leftChild){
		this.leftChild=leftChild;
		
	}
	public void setisright(boolean isright) {
		this.isright = isright;
	}
	public void setDirect(int direct){
		this.direct=direct;
		
	}
	public void setparent(Node parent){
		this.parent = parent;
	}
	public void setRightChild(Node rightChild){
		this.rightChild=rightChild;
	}
	public Node parent(){
		return parent;
	}
	public Point point(){
		return point;
	}
	public Node leftChild(){
		return leftChild;
	}
	public Node rightChild(){
		return rightChild;
	}
}
