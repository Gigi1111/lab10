package lab10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;


public class WeightedGraphFromList implements WeightedGraphFromList_interface{
	HashMap<String, LinkedList<Edge> > hmap = new HashMap<String, LinkedList<Edge>>(); 
	
	boolean directed = false;
	LinkedList<LinkedList<Edge>> paths= new LinkedList<LinkedList<Edge>>();
	String result="";
	public void createGraphFromFile(String fileLocation) {
//		WeightedGraphFromList g = new WeightedGraphFromList();
        try{  
			File file=new File(fileLocation);    //creates a new file instance  
			FileReader fr=new FileReader(file);   //reads the file  
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
		
			String line="";
			while((line=br.readLine())!=null) {	
				//System.out.println(line); 
				String[] line_arr = line.split(",");
				//System.out.println(line_arr.length);
				if(line_arr.length<4)break;
				else if(!hasVertex(line_arr[0])) {
					addVertex(line_arr[0]);
				}
				if(!hasEdge(line_arr[0],line_arr[1],line_arr[2],Integer.parseInt(line_arr[3]))){
					addEdge(line_arr[0],line_arr[1],line_arr[2],Integer.parseInt(line_arr[3]));
					
				}
			}
			toString();
			getVertex();
			fr.close();    //closes the stream and release the resources  
		}catch(IOException e){  
			e.printStackTrace();  
		}  
	}
	 
    public String[] getVertex() {
    	 Set<String> s = hmap.keySet();
         String[] choices = new String[s.size()];
         int i=0;
         for (String x : hmap.keySet()) { 
             choices[i++]=x; 
             System.out.print(x+";"); 
         }
         return choices;
     }
    public void addVertex(String s) {
    	 hmap.put(s, new LinkedList<Edge>()); 
    }
    public void setGraphDirected(boolean dir) {
    	directed=dir;
    }
    public boolean isGraphDirected() {
    	return directed;
    }
    public void addEdge(String source,String destination,String trans,int time) {
    	 if (!hmap.containsKey(source)) 
             addVertex(source); 
   
         if (!hmap.containsKey(destination)) 
             addVertex(destination); 
   
         hmap.get(source).add(new Edge(source, destination,trans,time)); 
         if (!directed) { 
             hmap.get(destination).add(new Edge(destination,source,trans,time)); 
         } 
    }
    public int numOfVertex() {
    	 //System.out.println("The graph has " + hmap.keySet().size()  + " vertex"); 
    	 return hmap.keySet().size();
    }
    public int numOfEdges(String s) {
    	 int count = 0; 
         for (String ss : hmap.keySet()) { 
             count += hmap.get(ss).size(); 
         } 
         if (directed == false) { 
             count = count / 2; 
         } 
        // System.out.println("The graph has "+ count  + " edges."); 
         return count;
    }
    public boolean hasVertex(String s) {
    	if (hmap.containsKey(s)) { 
           // System.out.println("The graph contains " + s + " as a vertex."); 
            return true;
        } 
       
           // System.out.println("The graph does not contain "+ s + " as a vertex."); 
       return false;
    }
    public boolean hasEdge(String source,String destination,String trans,int time) {
    	
    	for (Edge e : hmap.get(source)) { 
    		 if (e.destination.equals(destination)
    				 && e.trans.equals(trans)
    				 && e.time==time) { 
    	            System.out.println("The graph has an edge between "
    	                               + source + " and " + destination + ".");
    	            return true;
    	        } 
          } 
    	 //System.out.println("The graph has no edge between " + source + " and " + destination + ".");
        return false;
    }
    @Override
    public String toString() {
    	 String result = ""; 
    	  
         for (String ss : hmap.keySet()) { 
             result+= ss + ": "; 
             for (Edge e : hmap.get(ss)) { 
                result+= e.destination + ", "; 
             } 
             result+="\n"; 
         } 
         System.out.println(result);
         return result; 
    }
    public static void main(String args[]) 
    { 
    	WeightedGraphFromList g = new WeightedGraphFromList(); 
    	g.createGraphFromFile("src/lab10/Routes.txt");
    	g.getVertex();
    }
    public void printPath() {
		if(paths.size()>0) {
			 System.out.println("Print Path of size "+paths.size()+":");

			 for(int k=0;k<paths.size();k++) {
				for(int i=0;i< paths.get(k).size();i++) {
					if(i==0)
						System.out.print(paths.get(k).get(i).source);
					System.out.print(" -> "+paths.get(k).get(i).destination);
			   }
				 System.out.println();
			}
		}else {
				System.out.println("can't print");
			}
	}
	public String getAllPaths(String start, String mid, String end, int layer, int numOfEdges, int nthEdge) {
		System.out.println("*****firstline:"+start+","+mid+","+end);
		System.out.println("layer:"+layer);
//		System.out.println("mid:"+mid+" has "+hmap.get(mid).size()+"edges");
		
		//if just start out
		if(layer==0 && start.equals(mid) && paths.size()>0) {
			paths= new LinkedList<LinkedList<Edge>>();
		}
		//if start and end is the same, no need to calculate paths
		if(start.equals(end) && paths.size()==0 && layer==0) {
			System.out.println("start=end");
			return "You are already at "+end;
		}
		//if this path has successfully gotten to end
		if(mid.equals(end)) {
				System.out.println("mid=end");
				//create a new path to continue the search
				paths.add(new LinkedList<Edge>());//if at the end one edge is empty, delete empty edge
				printPath();
				System.out.println("paths size:"+paths.size());
				System.out.println("nthEdge;numOfEdge:"+nthEdge+";"+hmap.get(mid).size());
				if(layer>0 && nthEdge<(hmap.get(mid).size()-1)&&paths.size()>=2) {//back up one layer to explore other routes
					//if we need to copy the route - last node of edge
					for(Edge e: paths.get(paths.size()-2)) {
						paths.getLast().add(e);
					}
					paths.getLast().removeLast();
					System.out.println("in copy path:");
					printPath();
				}
				return "Found one route and back up one layer to explore other routes";
		}
		//if just started out
		else if(start.equals(mid) && layer==0) {
				System.out.println("start=mid");
				paths.add(new LinkedList<Edge>());
		}
		System.out.println("*****edge loop for mid "+mid+"with "+hmap.get(mid).size()+" of edges");
		
			//if haven't reached end, going through each edge of next layer
			for(int i=0;i<hmap.get(mid).size();i++) {	
				System.out.println("***** "+i+"th edge:"+hmap.get(mid).get(i).source+"->"+hmap.get(mid).get(i).destination);
				boolean repeat=false;
				printPath();
				//check if there's a repetitive route
				for(int j=0;j<paths.getLast().size();j++) {
					System.out.println("checking "+j+"th edge: "+paths.getLast().get(j).source+"->"+paths.getLast().get(j).destination);
						if(//if this edge repeat, don't add edge, go to next edge
   paths.getLast().get(j).destination.equals(hmap.get(mid).get(i).destination)
|| paths.getLast().get(j).source.equals(hmap.get(mid).get(i).source)
|| paths.getLast().get(j).source.equals(hmap.get(mid).get(i).destination)) {
						System.out.println("repeat");
						repeat=true;
						
						
						System.out.println("!layer "+layer+" for mid "+mid);
						System.out.println("nthEdge;numOfEdge:"+nthEdge+";"+hmap.get(mid).size());
		
						//if repeat and 
						if(i==hmap.get(mid).size()-1
								&&
								paths.getLast().size()>0
								&&
								!paths.getLast().getLast().destination.equals(end)) {	
							System.out.println("i==paths.getLast().size()-1");
											
								paths.getLast().removeLast();
							
							printPath();
							
						}
						break;//break this checking loop to go to next edge of outer for loop
						
					}
				}
				//if not repeat, add edge and go to next layer
				if(!repeat) {
					System.out.println("in not repeat for edge: "+hmap.get(mid).get(i).source+"->"+hmap.get(mid).get(i).destination);
					printPath();
					paths.getLast().add(hmap.get(mid).get(i));
					System.out.println("add edge:");
					printPath();
					
					getAllPaths(start,hmap.get(mid).get(i).destination,end, layer+1, hmap.get(mid).size(),i);	
	
					System.out.println("!!!back to layer "+layer+" for mid "+mid);
					System.out.println("nthEdge;numOfEdge:"+nthEdge+";"+hmap.get(mid).size());
					if(layer>0 &&  (i+1)< hmap.get(mid).size()  
							&& paths.getLast().size()<1
							&& paths.size()>=2) {//back up one layer to explore other routes
						//if we need to copy the route - last node of edge
						for(Edge e: paths.get(paths.size()-2)) {
							
							paths.getLast().add(e);
							if(paths.getLast().getLast().destination.equals(mid)) {
								break;
							}
						}
						
						System.out.println("in copy path:");
						printPath();
					}
					printPath();
				}
				
				System.out.println("**************next loop*************");
				
			}
		
		//if back to layer 0 before return, check if there's empty edge and invalid edges
		if(layer==0) {
			System.out.println("before return check emty:");
			printPath();
			for(int i=0;i<paths.size();i++) {
			//	System.out.println("i:"+i+", size: "+paths.get(i).size());
				if(paths.get(i).size()>0 && !paths.get(i).getFirst().source.equals(start)) {
					paths.remove(i);
					i--;
				}
				else if(paths.get(i).size()>0 && !paths.get(i).getLast().destination.equals(end) || paths.get(i).size()<1) {
				    paths.remove(i);
					i--;
				}
			}
			System.out.println("after check emty:");
			printPath();
			String ss=PathsToString(paths);
			System.out.println("There are "+paths.size()+" paths:"+"\n"+ss);
			return "There are "+paths.size()+" paths:"+"\n"+ss;
		}
		System.out.println("not base layer yet");
		//before back to layer-1, need to check if last path is (1)to end 
		//if not to end, layer 0 should have last size=0
		if(paths.size()>0 && paths.getLast().size()>0
				&& !paths.getLast().getLast().destination.equals(end)
				&& paths.getLast().size()>(layer-1)) {
			paths.getLast().removeLast();
		}
		return "not base layer yet";
	}
 
	public String PathsToString(LinkedList<LinkedList<Edge>> l) {
		String s="";
		if(l.isEmpty())
			return "No connecting routes";
		
		for(int i=0; i < l.size();i++) {
			if(l.get(i).size()>0) {
				int time=0;
				s+=l.get(i).get(0).source;
				for(int j=0; j<l.get(i).size();j++) {
					s+="-"+l.get(i).get(j).trans+"->"+l.get(i).get(j).destination;
					time+=l.get(i).get(j).time;
				}
				s+="(Dur:"+time+"min)\n";
			}
		}
		return s;
	}

	public String getLightestPath() {
		int time=-1;
		LinkedList<LinkedList<Edge>> quickestPathList= new LinkedList<LinkedList<Edge>>();
		if(paths.isEmpty()) return "No connecting routes";
		for(int i=0;i<paths.size();i++) {
			int t=0;
			for(int j=0;j<paths.get(i).size();j++) {
				t+=paths.get(i).get(j).time;
			}
			if(time==-1 || t<time) {
				time=t;
			}
		}
		for(int i=0;i<paths.size();i++) {
			int t=0;
			for(int j=0;j<paths.get(i).size();j++) {
				t+=paths.get(i).get(j).time;
			}
			if (t==time) {
				quickestPathList.add(paths.get(i));
			}
		}
		return "You need "+time+" min or more.\n "
				+ "You have "+quickestPathList.size()+" quickest routes:\n"+PathsToString(quickestPathList);
	
	}
	
	public String getShortestPaths() {
		int shortest=-1;
		LinkedList<LinkedList<Edge>> shortPathList= new LinkedList<LinkedList<Edge>>();
		if(paths.isEmpty()) return "No connecting routes";
		for(int i=0;i<paths.size();i++) {
			int s = paths.get(i).size();
			if (shortest==-1 || shortest > s) {
				shortest=s;
			}
		}
		for(int i=0;i<paths.size();i++) {
			if (paths.get(i).size()==shortest) {
				shortPathList.add(paths.get(i));
			}
		}
		return "You need to transfer "+(shortest-1)+" time or more.\n"
				+ "You have "+shortPathList.size()+" shortest route(s):\n"+PathsToString(shortPathList);
	}
}

