package lab5_1;
import java.util.LinkedList;

public class WeightedGraphFromList2 implements WeightedGraph{
	
	    
	        int vertices;
	        LinkedList<Edge> [] adjacencylist;

	        public WeightedGraphFromList2(int vertices, int edges) {
	        	this.vertices = vertices;
	            adjacencylist = new LinkedList[vertices];
	            //initialize adjacency lists for all the vertices
	            for (int i = 0; i <vertices ; i++) {
	                adjacencylist[i] = new LinkedList<>();
	            }
	            addEdge(0,2,1);
	            addEdge(2,3,1);
	            addEdge(0,1,2);
	            addEdge(2,4,1);
	            addEdge(4,1,1);
	            addEdge(1,3,2);//0-1-3 (4) ; 0-2-3(2)
	           // addEdge(0,3,1);
	            
//	            addEdge(2,0,1);
//	            addEdge(0,0,1);	      
//	            addEdge(0,2,1);
//	            addEdge(2,5,1);
//	            addEdge(0,1,1);		  
//	            addEdge(1,0,1);	
//	            addEdge(2,4,1);
//	            addEdge(4,1,1);
//	         
	            
	           
			}
	        static LinkedList<LinkedList<Object>> pathList = new LinkedList<LinkedList<Object>>();
	 
	        public static void main(String[] args) {
	            int vertices = 6;
	            WeightedGraphFromList2 graph = new WeightedGraphFromList2(vertices,20);
	            graph.printGraph();
	            
	            
	            int start=0;
	            int end=3;
	             //print out the pathlist
	            graph.getPaths(start,start, end);
	            //check pathList
	            graph.checkPathList(start,end);
	            int shortestPath=0;
	            if(pathList.size()>0)
	            	shortestPath = graph.getShortestPath(start,end);
	            //check shortest path
	            if(shortestPath>0) {
	            	// System.out.println("pathListInMain:"+pathList.size());
	 	           //printPaths();
	            	//check which is shortest
	            	System.out.println("\nshortest path from "+start+" to "+ end+" is "
	            	+"\n"+shortestPath+" edges long");
	            	graph.printShortestPaths(shortestPath,start,end);
	            
	            }else
	            	System.out.println("\nThere is no path from "+start+" to "+ end);  
	       //check for cheapest paths
	           
	           graph.getCheapestPath();
//	            System.out.println(
//	            "The cheapest path is weighted "+cheapestPath);
//	            graph.printCheapestPaths();
	          
	        }  
	        public void  checkPathList(int start,int end) {
	        	for(int i=0;i<pathList.size();i++) {
	        		for(int j=0;j<pathList.get(i).size();j++) {
	        			if(!pathList.get(i).contains(start) ||
	        					!pathList.get(i).contains(end) ) {
	        				pathList.remove(i);
	        			}
	        		}
	        	}
	        }
	        public void getCheapestPath() {
	        	int cheapestPath=0;
	        	for(int i=0;i<pathList.size();i++) {
	        		for(int j=0;j<pathList.get(i).size();j++) {
	        			
	        		}
	        	}
	        	printtCheapestPaths();
	        }
	        public static void printtCheapestPaths() {
	        	
	        }
	        public void printCheapestPaths() {
	        	
	        }
	        public void printShortestPaths(int shortestPath,int start,int end) {
	        	System.out.println("Paths:");
            	for(int i=0;i<pathList.size();i++) {
            		if(pathList.get(i).size()==shortestPath+1 ) {
            			for(int j=0;j<pathList.get(i).size();j++) {
            				System.out.print(pathList.get(i).get(j));
            				if(j!=pathList.get(i).size()-1)
            					System.out.print("->");
            			}
            			System.out.println();
            		}
            	}
	        }
	        public int getShortestPath(int start, int end) {
	        	LinkedList<Integer> shortPathList=new LinkedList<Integer>();
	        	int shortestSize=0;
	 	           for(int k=0;k<pathList.size();k++) {
	            		 if((shortestSize==0 || shortestSize>=pathList.get(k).size())) {
	            			 shortestSize= pathList.get(k).size();
	            		 }
	 	           }
	 	          return shortestSize-1;
	        }
	       
	        public void addEdge(int source, int destination, int weight) {
	            Edge edge = new Edge(source, destination, weight);
	            adjacencylist[source].addFirst(edge); //for directed graph
	        }

	        public void printGraph(){
	        	
	            for (int i = 0; i <vertices ; i++) {
	                LinkedList<Edge> list = adjacencylist[i];
	                for (int j = 0; j <list.size() ; j++) {
	                    System.out.println("vertex-" + i + " is connected to " +
	                            list.get(j).destination + " with weight " +  list.get(j).weight);
	                }
	            }
	        }
	        int length=0;
	        int path=0;
	        int pathCount=0;
	        public void getPaths(int start,int mid, int end) {
	        	LinkedList<Edge> list = adjacencylist[mid];
	        	// System.out.println(start+","+mid+","+end);
	        	printPaths();
		          //first check if we already got to the destination
		            if(mid==end) {
		        		pathCount++;//add a path
		        		return;//found
		        	}else if(mid == start) {	   
	    	        	pathList.add(new LinkedList<Object>());
	    	        	// pathList.get(pathCount).add(start);
	    	        //	 printPaths();
	        		}
		          //if too long or at the end
		            else if(list.size()==0 || 
		            		(pathList.size()>0 && 
		            (pathList.get(pathList.size()-1).size()>vertices
		            		)
		            )) {		            	
	  	        		 pathList.get(pathList.size()-1).removeLast();
	  	        		pathCount=pathList.size();
			        	return;
		            }
	        	for (int j = 0; j <list.size() ; j++) {
	        		//add another path with same starting path
	        		 if(pathCount>=pathList.size() && pathCount>0){
	        			 pathList.add(new LinkedList<Object>());
	    	            	 for(int k=0;k<pathList.get(pathCount-1).size();k++) {
	    	            		pathList.get(pathCount).add(pathList.get(pathCount-1).get(k));
	    	            		 if(list.get(j).source==(int)pathList.get(pathCount-1).get(k))break;	 
	    	 	            }
	        		 }
	        		 //if going in circle
	        		  if( pathList.get(pathList.size()-1).contains(list.get(j).destination)) {	   		  	        		//move on to next edge if has next edge	
			            	if(j==list.size()-1) {
			            		pathList.removeLast();
			            		pathCount=pathList.size();
			            		return;
			            	}
			            	continue;
		        		}	        		
	        		//just add a node
	        		pathList.get(pathCount).add(list.get(j).destination);
			        getPaths(start,list.get(j).destination, end);			  
			    }        
	        	return;
	        }
	        public static void printPaths() {
	        	System.out.println("path:");
		            for(int i=0;i<pathList.size();i++) {
		            	 for(int j=0;j<pathList.get(i).size();j++) {
		            		 System.out.print(pathList.get(i).get(j));
		            		 if(j<pathList.get(i).size()-1) 	 System.out.print("->");
		 	            }
		            	 System.out.println();
		            }
	        }
	        int w_length=0;
	        int w_path=0;
//	        Make a method to print out the path in a readable format. What class will these methods belong to?
//	        		Finally, write a method that takes a graph, picks two vertices at random, and finds the cheapest path between the two.
	       
//	        public int cheapestPath(int start, int end) {
//	        	System.out.print(start+"("+w_length+")"+"-");
//	        	if(start==end) {
//	        		if(w_path>w_length && w_length!=0 || w_path==0)w_path=w_length;
//	        		System.out.println();
//	        		return w_path;
//	        	}
//	        	w_length++;
//	        	if(w_length > vertices+1 ) return -1;
//	        	
//	        	int result=-4;
//	        	LinkedList<Edge> list = adjacencylist[start];
//	        	for (int j = 0; j <list.size() ; j++) {
//			          result = shortestPath(list.get(j).destination, end);
//			    
//			    }
//	        	w_length--;
//	        	return result;
//	        }
	        static class Edge {
		        int source;
		        int destination;
		        int weight;

		        public Edge(int source, int destination, int weight) {
		            this.source = source;
		            this.destination = destination;
		            this.weight = weight;
		        }
		    }
	    
	     
}
