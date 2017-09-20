package nearestNeigh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * This class is required to be implemented.  Kd-tree implementation.
 *
 * @author Jeffrey, Youhan
 */
public class KDTreeNN implements NearestNeigh{
	Category edu=Category.EDUCATION;
	Category hos=Category.HOSPITAL;
	Category res=Category.RESTAURANT;
	List<Point> allList = new ArrayList<Point>();
	List<Point> resList =new ArrayList<Point>();
	List<Point> hosList =new ArrayList<Point>();
	List<Point> eduList =new ArrayList<Point>();
	List<Point> remainList=new ArrayList<Point>();
	public Node resroot;
	public Node hosroot;
	public Node eduroot;
	public Node allroot;
	public Node leafnode;
	public Node temp;
	public Node compare;
	public KDTreeNN(){
		allroot=null;
		resroot=null;
		hosroot=null;
		eduroot=null;
		leafnode=null;
		temp =null;
		compare = null;
		
	}
	
    @Override
    public void buildIndex(List<Point> points) {
    	
    	for(int i=0;i<points.size();i++){
    		allList.add(points.get(i));
    	}
    	
    	for(int i=0; i<points.size();i++){
    		if(points.get(i).cat.equals(edu)){
    			eduList.add(points.get(i));
    		}
    	}
    	for(int i=0;i<points.size();i++){
    		if(points.get(i).cat.equals(hos)){
    			hosList.add(points.get(i));
    		}
    	}
    	for(int i=0;i<points.size();i++){
    		if(points.get(i).cat.equals(res)){
    			resList.add(points.get(i));
    		}
    	}
    	
    	boolean Dm= true;
    	resroot=buildTree(resList,temp,Dm);
    	hosroot=buildTree(hosList,temp,Dm);
    	
    	eduroot=buildTree(eduList,temp,Dm);
    	allroot = buildTree(allList,temp,Dm);
    	
    	
 
    	
    }
  
    public Node buildTree(List<Point> points,Node temp,boolean Dm){
    	int median;
    	
    	List<Point> sortedPoints= new ArrayList<Point>();
    	/*List<Point> leftList = new ArrayList<Point>();
    	List<Point> rightList = new ArrayList<Point>();*/
    	
    	Node leftChild = null ,rightChild=null;
    	
    	flip(points,Dm);
    	sortedPoints = points;
    	median=findMedian(sortedPoints);
    	//System.out.print(median);
    
    	
    	Node currentNode = new Node();
    	
    	currentNode.point=sortedPoints.get(median);
    	currentNode.setparent(temp);
    	//System.out.println(sortedPoints);
    	//System.out.println(sortedPoints.get(median));
    	//System.out.println(currentNode.point);
    	
    	//System.out.println(leftList);
    	
    	
    	//System.out.println(leftList);
    	if(Dm ==true){
    		currentNode.setDirect(0);
    		Dm =false;
    	}
    	else{
    		currentNode.setDirect(1);
    		Dm =true;
    	}
    	
    	
    	
    	//System.out.println(median);
    		if(median>0){
    			temp= currentNode;
    			
    			leftChild = buildTree(flip(leftPointList(points,median),Dm),temp,Dm);
 
    			currentNode.setleftChild(leftChild);
    			currentNode.setisright(false);
    			
    		}
    		//System.out.println(median);
    	/*	System.out.println(points.size());
    		System.out.println(median);*/
    	/*System.out.println("hello");*/
    	
    		if(median+1<points.size()){
    			
    			/*System.out.println(Dm);*/
    			temp=currentNode;
    			
    			rightChild =buildTree(flip(rightPointList(points,median+1),Dm),temp,Dm);
    		
    			currentNode.setRightChild(rightChild);
    			currentNode.setisright(true);
    			
    			//System.out.println(currentNode.point);
    		}
    	return currentNode;
    	
    }
 

    public  List<Point> flip(List<Point> points,boolean p){
    	if (p == true){
    		
    		 return  AllsortedX(points);
    	}
    	else{
    		
    		return AllsortedY(points);
    	}
    	
    }
  
    public List<Point> leftPointList(List<Point> points,int median){
    	List <Point> leftList = new ArrayList<Point>();
    	for(int i=0;i<median;i++){
    		leftList.add(points.get(i));
    	}
    	return leftList;
    }
    public List<Point> rightPointList(List<Point> points, int median){
    	List<Point> rightList = new ArrayList<Point>();
    	for(int i = median; i<points.size();i++){
    		rightList.add(points.get(i));
    	}
    
    	return rightList;
    }
    public int findMedian(List<Point> points){
    	int median=0;
    	/*if(median%2==0){
    		median = ((points.size()/2) + (points.size()/2-1))/2;
    		
    	}
    	else{*/
    	
    	
    		median = points.size()/2;
    	/*}*/
    	
    	return median;
    }
    public List<Point> AllsortedX(List<Point> points){
    	for(int i=0;i<points.size();i++){
    		for(int j=0;j<points.size()-1;j++){
    			if(points.get(j).lat>points.get(j+1).lat){
    				Collections.swap(points, j, j+1);		
    				}
    		}
    	}
    	
    	return points;
    }
    public List<Point> AllsortedY(List<Point> points){
    	for(int i=0;i<points.size();i++){
    		for(int j=0;j<points.size()-1;j++){
    			if(points.get(j).lon>points.get(j+1).lon){
    				Collections.swap(points, j, j+1);		
    				}
    		}
    	}
    	return points;
    }
    @Override
    public List<Point> search(Point searchTerm, int k) {
    	List<Point> nearestPoint = new ArrayList<Point>();
    	double math;
    	boolean check =true;
    	Node leaf = new Node();
    	Stack<Node> path = new Stack<Node>();
    	List<Point> sortedList = new ArrayList<Point>();
    	
    	if(searchTerm.cat.equals(res))
    	{
    		leaf=findLeafNode(resroot,searchTerm,check,path);
    		nearestPoint.add(leaf.point);
    	}
    	else if(searchTerm.cat.equals(edu)){
    		leaf=findLeafNode(eduroot,searchTerm,check,path);
    		nearestPoint.add(leaf.point);
    	}
    	else if(searchTerm.cat.equals(hos)){
    		
    		leaf=findLeafNode(hosroot,searchTerm,check,path);
    		
    		nearestPoint.add(leaf.point);
    		
    	}
    	else{
    		leaf=findLeafNode(allroot,searchTerm,check,path);
    		nearestPoint.add(leaf.point);
    	}
    	
    	
    		
    		while(path.size()>0){
    			Node myNode = path.pop();
    			if(myNode.point.equals(leaf.point)){
    				continue;
    			}
    			
    			nearestPoint.add(myNode.point);
    			if(myNode.direct==0){
    				math=myNode.point.lat-searchTerm.lat;
    				if(math<myNode.point.distTo(searchTerm)){
    					if(leaf.point.lat<myNode.point.lat){
    						findLeafNode(myNode.rightChild,searchTerm,check,path);
    					}
    					else{
    						findLeafNode(myNode.leftChild,searchTerm,check,path);
    					}
    					
    				}		
    			}
    			if(myNode.direct==1){
    				math=myNode.point.lon-searchTerm.lon;
    				if(math<myNode.point.distTo(searchTerm)){
    					if(leaf.point.lon<myNode.point.lon){
    						findLeafNode(myNode.rightChild,searchTerm,check,path);
    					}
    					else{
    						findLeafNode(myNode.leftChild,searchTerm,check,path);
    					}
    					
    				}	
    			}	
    		}		
        	for(int i=0;i<nearestPoint.size();i++){
        		for(int j=0;j<nearestPoint.size()-1;j++){
        			if(nearestPoint.get(j).distTo(searchTerm)>nearestPoint.get(j+1).distTo(searchTerm)){
        				Collections.swap(nearestPoint, j, j+1);		
        				}
        		}
        	}
        	for(int j=0;j<k;j++){
        		sortedList.add(nearestPoint.get(j));
        	}
      
        // To be implemented.
        return sortedList;
    }
    public Node findLeafNode(Node root,Point points,Boolean checklat,Stack<Node> path){
    	if(root==null){
    		return null;
    	}
    	if(checklat==true){
    		
    		if(points.lat>root.point.lat){
    			findLeafNode(root.rightChild,points,!checklat,path);
    			if(root.rightChild==null){
    				findLeafNode(root.leftChild,points,!checklat,path);
    			}
    			 			
    			if(root.leftChild==null &&root.rightChild==null){		
    				leafnode=root;
    				
    				return leafnode;
    			}
    			
    		}
    		if(points.lat<root.point.lat){
    			findLeafNode(root.leftChild,points,!checklat,path);
    			if(root.leftChild==null){
    				findLeafNode(root.rightChild,points,!checklat,path);
    			}
    			
    			if(root.leftChild==null &&root.rightChild==null){
    				leafnode=root;
    				
    				return leafnode;
    				
    				
    				
    			}
    			
    		}
    	}
    	path.add(root);
    	if(checklat==false){
    		if(points.lon>root.point.lon){
    			
    			
    			findLeafNode(root.rightChild,points,!checklat,path);
    			if(root.rightChild==null){
    				findLeafNode(root.leftChild,points,!checklat,path);
    			}
    			
    			
    			if(root.leftChild==null &&root.rightChild==null){
    				leafnode=root;
    				
    				return leafnode;
    				}
    				
    				
    				
    			}
    			if(points.lon<root.point.lon){
    		
    			
    				findLeafNode(root.leftChild,points,!checklat,path);
    				if(root.rightChild==null){
        				findLeafNode(root.leftChild,points,!checklat,path);
        			}
    			
    			if(root.leftChild==null &&root.rightChild==null){
    				leafnode=root;
    				
    				return leafnode;
    			}
    		}
    		}
    		return leafnode;
    	}
   
    public void addNode(Node root,Point point,Boolean check){
    	
    	Node setnode=new Node();
    	setnode.point=point;
    	
    	if(root==null){
    		return;
    	}
  
			
		
    		if(check==true){	
        		if(point.lat>root.point.lat){
        			addNode(root.rightChild,point,!check);
        			
        			if(root.rightChild==null){
        				addNode(root.leftChild,point,!check);
        				
        			}
        			 			
        			if(root.leftChild==null &&root.rightChild==null){		
        				root.setRightChild(setnode);
        				allList.add(setnode.point);
        				
       	
        			}
        			 		
        			}
        		if(point.lat<root.point.lat){
        			addNode(root.leftChild,point,!check);
        			if(root.leftChild==null){
        				addNode(root.rightChild,point,!check);
        			}
        			 			
        			if(root.leftChild==null &&root.rightChild==null){		
        				root.setleftChild(setnode);
        				allList.add(setnode.point);
        			
        			}
        			
        		}
    		}
    		
    		if(check==false){
    			if(point.lon>root.point.lon){
    				addNode(root.rightChild,point,!check);
    				if(root.rightChild==null){
        				addNode(root.leftChild,point,!check);
        			}
        			 			
        			if(root.leftChild==null &&root.rightChild==null){		
        				root.setRightChild(setnode);
        				allList.add(setnode.point);
        				
        			}
    			
    			}
    			if(point.lon<root.point.lon){
    				addNode(root.leftChild,point,!check);
    				if(root.leftChild==null){
        				addNode(root.rightChild,point,!check);
        			}
        			 			
        			if(root.leftChild==null &&root.rightChild==null){		
        				root.setleftChild(setnode);
        				allList.add(setnode.point);
        			
        			}
    				
    			}
    		}
    }       
      
    @Override
    public boolean addPoint(Point point){

    	if(isPointIn(point)==true){
    		return false;
    	}
    	boolean check= true;
    	
    	addNode(allroot,point,check);
        return true;
    }
 

    @Override
    public boolean deletePoint(Point point) {
    	for(int i= 0; i<allList.size();++i)
    	{
    		if(allList.get(i).equals(point))
    		{
    			allList.remove(i);	
    			return true;
    		}
    	}
        return false;
    }

    @Override
    public boolean isPointIn(Point point) {
    	for(int i= 0; i<allList.size();++i)
    	{
    		if(point.equals(allList.get(i)))
    		{   			
    			return true;
  				}
    		}
   	
        return false;
    }
}
