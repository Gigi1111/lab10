package lab5_1;
import java.util.LinkedList;

public class WeightedGraphFromList{
	
	    
	        int vertices;
	        static LinkedList<Edge> [] adjacencylist;

	        public WeightedGraphFromList(int vertices, int edges) {
	        	this.vertices = vertices;
	            adjacencylist = new LinkedList[vertices];
	            //initialize adjacency lists for all the vertices
	            for (int i = 0; i <vertices ; i++) {
	                adjacencylist[i] = new LinkedList<>();
	            }
	            addEdge(0,2,3);
	            addEdge(2,3,3);
	            addEdge(0,1,2);
	            addEdge(2,4,1);
	            addEdge(4,1,1);
	            addEdge(1,3,2);//0-1-3 (4) ; 0-2-3(6)
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
	            WeightedGraphFromList graph = new WeightedGraphFromList(vertices,20);
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
	        	int path=0;
	        	LinkedList<Edge> list;
	        	for(int i=0;i<pathList.size();i++) {
	        		//first path
	        		for(int j=1;j<pathList.get(i).size();j++) {
		        		list = adjacencylist[(int) pathList.get(i).get(j-1)];
	        			  for (int k = 0; k <list.size() ; k++) {
			                   if(list.get(k).destination ==(int)pathList.get(i).get(j)) {
			                	   path+=list.get(k).weight;
			                   }
			                }
	        		}
	        		if(cheapestPath==0 || cheapestPath >path) {
	        			cheapestPath=path;
	        			path=0;
	        		}
	        	}
	        	System.out.println("Cheapest path has a weight of "+cheapestPath);
	        	printtCheapestPaths(cheapestPath);
	        }
	        public static void printtCheapestPaths(int cheapestPath) {
	        	int path=0;
	        	LinkedList<Edge> list;
	        	for(int i=0;i<pathList.size();i++) {
	        		//first path
	        		for(int j=1;j<pathList.get(i).size();j++) {
		        		list = adjacencylist[(int) pathList.get(i).get(j-1)];
	        			  for (int k = 0; k <list.size() ; k++) {
			                   if(list.get(k).destination ==(int)pathList.get(i).get(j)) {
			                	   path+=list.get(k).weight;
			                   }
			                }
	        		}
	        		if(path==cheapestPath) {
	        			for(int j=0;j<pathList.get(i).size();j++) {
	        				System.out.print(pathList.get(i).get(j));
	        				if(j!=pathList.get(i).size()-1)
	        					System.out.print("->");
	        			}
	        		}
	        	}
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
	    	        	 pathList.get(pathCount).add(start);
	    	        	 printPaths();
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
