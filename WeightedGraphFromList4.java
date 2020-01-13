package lab10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class WeightedGraphFromList4{
	 HashMap<String, LinkedList<Edge>> hmap = new HashMap<String, LinkedList<Edge>>();
     
	public WeightedGraphFromList4() {
		
	}
	public void createGraph() {
	            try{  
	    			File file=new File("src/lab10/Routes.txt");    //creates a new file instance  
	    			FileReader fr=new FileReader(file);   //reads the file  
	    			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
	    		
	    			String line="";
	    			while((line=br.readLine())!=null) {	
	    				//System.out.println(line); 
	    				String[] line_arr = line.split(",");
	    				//System.out.println(line_arr.length);
	    				if(!hmap.containsKey(line_arr[0]))
	    					hmap.put(line_arr[0],new LinkedList<Edge>());
		            	hmap.get(line_arr[0]).add(new Edge(line_arr[0],line_arr[1],line_arr[2],Integer.parseInt(line_arr[3])));
		            	if(!hmap.containsKey(line_arr[1]))
	    					hmap.put(line_arr[1],new LinkedList<Edge>());
		            	hmap.get(line_arr[1]).add(new Edge(line_arr[1],line_arr[0],line_arr[2],Integer.parseInt(line_arr[3])));
		            }
	    			printGraph();
	    			fr.close();    //closes the stream and release the resources  
	    		}catch(IOException e){  
	    			e.printStackTrace();  
	    		}  
			}
	public void printPath() {
		if(list.size()>0) {
			 System.out.println("Print Path of size "+list.size()+":");

			 for(int k=0;k<list.size();k++) {
				for(int i=0;i< list.get(k).size();i++) {
					if(i==0)
						System.out.print(list.get(k).get(i).source);
					System.out.print(" -> "+list.get(k).get(i).destination);
			   }
				 System.out.println();
			}
		}else {
				System.out.println("can't print");
			}
	}
	public void printGraph() {
		 System.out.println("Print Graph:");
		//System.out.println("key: "+k+" value:"+v.forEach(edge->System.out.print( "1"))));
		for(Map.Entry<String, LinkedList<Edge>> entry : hmap.entrySet()) {
		    String key = entry.getKey();
		    LinkedList<Edge> value = entry.getValue();
		    System.out.print(key+" -> ");
		    value.forEach(edge->System.out.print(edge.destination+"("+edge.trans+"_"+edge.time+")"+","));
		    System.out.println();
		}
	}
	String s="";
	LinkedList<LinkedList<Edge>> list= new LinkedList<LinkedList<Edge>>();
	public String getAllRoutes(String start, String mid, String end, int layer,int numOfEdges, int nthEdge) {
		System.out.println("*****firstline:"+start+","+mid+","+end);
		System.out.println("layer:"+layer);
//		System.out.println("mid:"+mid+" has "+hmap.get(mid).size()+"edges");
		
		//if just start out
		if(layer==0 && start.equals(mid) && list.size()>0) {
			list= new LinkedList<LinkedList<Edge>>();
		}
		//if start and end is the same, no need to calculate paths
		if(start.equals(end) && list.size()==0 && layer==0) {
			System.out.println("start=end");
			return "You are already at "+end;
		}
		//if this path has successfully gotten to end
		if(mid.equals(end)) {
				System.out.println("mid=end");
				//create a new path to continue the search
				list.add(new LinkedList<Edge>());//if at the end one edge is empty, delete empty edge
				printPath();
				System.out.println("list size:"+list.size());
				System.out.println("nthEdge;numOfEdge:"+nthEdge+";"+hmap.get(mid).size());
				if(layer>0 && nthEdge<(hmap.get(mid).size()-1)&&list.size()>=2) {//back up one layer to explore other routes
					//if we need to copy the route - last node of edge
					for(Edge e: list.get(list.size()-2)) {
						list.getLast().add(e);
					}
					list.getLast().removeLast();
					System.out.println("in copy path:");
					printPath();
				}
				return "Found one route and back up one layer to explore other routes";
		}
		//if just started out
		else if(start.equals(mid) && layer==0) {
				System.out.println("start=mid");
				list.add(new LinkedList<Edge>());
		}
		System.out.println("*****edge loop for mid "+mid+"with "+hmap.get(mid).size()+" of edges");
		
			//if haven't reached end, going through each edge of next layer
			for(int i=0;i<hmap.get(mid).size();i++) {	
				System.out.println("***** "+i+"th edge:"+hmap.get(mid).get(i).source+"->"+hmap.get(mid).get(i).destination);
				boolean repeat=false;
				printPath();
				//check if there's a repetitive route
				for(int j=0;j<list.getLast().size();j++) {
					System.out.println("checking "+j+"th edge: "+list.getLast().get(j).source+"->"+list.getLast().get(j).destination);
						if(//if this edge repeat, don't add edge, go to next edge
   list.getLast().get(j).destination.equals(hmap.get(mid).get(i).destination)
|| list.getLast().get(j).source.equals(hmap.get(mid).get(i).source)
|| list.getLast().get(j).source.equals(hmap.get(mid).get(i).destination)) {
						System.out.println("repeat");
						repeat=true;
						
						
						System.out.println("!layer "+layer+" for mid "+mid);
						System.out.println("nthEdge;numOfEdge:"+nthEdge+";"+hmap.get(mid).size());
		
						//if repeat and 
						if(i==hmap.get(mid).size()-1
								&&
								list.getLast().size()>0
								&&
								!list.getLast().getLast().destination.equals(end)) {	
							System.out.println("i==list.getLast().size()-1");
											
								list.getLast().removeLast();
							
							printPath();
							
						}
						break;//break this checking loop to go to next edge of outer for loop
						
					}
				}
				//if not repeat, add edge and go to next layer
				if(!repeat) {
					System.out.println("in not repeat for edge: "+hmap.get(mid).get(i).source+"->"+hmap.get(mid).get(i).destination);
					printPath();
					list.getLast().add(hmap.get(mid).get(i));
					System.out.println("add edge:");
					printPath();
					
					getAllRoutes(start,hmap.get(mid).get(i).destination,end, layer+1, hmap.get(mid).size(),i);	
	
					System.out.println("!!!back to layer "+layer+" for mid "+mid);
					System.out.println("nthEdge;numOfEdge:"+nthEdge+";"+hmap.get(mid).size());
					if(layer>0 &&  (i+1)< hmap.get(mid).size()  
							&& list.getLast().size()<1
							&& list.size()>=2) {//back up one layer to explore other routes
						//if we need to copy the route - last node of edge
						for(Edge e: list.get(list.size()-2)) {
							
							list.getLast().add(e);
							if(list.getLast().getLast().destination.equals(mid)) {
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
			for(int i=0;i<list.size();i++) {
			//	System.out.println("i:"+i+", size: "+list.get(i).size());
				if(list.get(i).size()>0 && !list.get(i).getFirst().source.equals(start)) {
					list.remove(i);
					i--;
				}
				else if(list.get(i).size()>0 && !list.get(i).getLast().destination.equals(end) || list.get(i).size()<1) {
				    list.remove(i);
					i--;
				}
			}
			System.out.println("after check emty:");
			printPath();
			String ss=listToString(list);
			System.out.println("There are "+list.size()+" paths:"+"\n"+ss);
			return "There are "+list.size()+" paths:"+"\n"+ss;
		}
		System.out.println("not base layer yet");
		//before back to layer-1, need to check if last path is (1)to end 
		//if not to end, layer 0 should have last size=0
		if(list.size()>0 && list.getLast().size()>0
				&& !list.getLast().getLast().destination.equals(end)
				&& list.getLast().size()>(layer-1)) {
			list.getLast().removeLast();
		}
		return "not base layer yet";
	}
	public String listToString(LinkedList<LinkedList<Edge>> l) {
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
	//equals shortest path 
	public String getLeastTransferRoutes() {
		int shortest=-1;
		LinkedList<LinkedList<Edge>> shortPathList= new LinkedList<LinkedList<Edge>>();
		if(list.isEmpty()) return "No connecting routes";
		for(int i=0;i<list.size();i++) {
			int s = list.get(i).size();
			if (shortest==-1 || shortest > s) {
				shortest=s;
			}
		}
		for(int i=0;i<list.size();i++) {
			if (list.get(i).size()==shortest) {
				shortPathList.add(list.get(i));
			}
		}
		return "You need to transfer "+(shortest-1)+" time or more.\n"
				+ "You have "+shortPathList.size()+" shortest route(s):\n"+listToString(shortPathList);
	}
	//equals lightest weighted path becuz weight is the duration of transportation
	public String getQuickestRoutes() {
		int time=-1;
		LinkedList<LinkedList<Edge>> quickestPathList= new LinkedList<LinkedList<Edge>>();
		if(list.isEmpty()) return "No connecting routes";
		for(int i=0;i<list.size();i++) {
			int t=0;
			for(int j=0;j<list.get(i).size();j++) {
				t+=list.get(i).get(j).time;
			}
			if(time==-1 || t<time) {
				time=t;
			}
		}
		for(int i=0;i<list.size();i++) {
			int t=0;
			for(int j=0;j<list.get(i).size();j++) {
				t+=list.get(i).get(j).time;
			}
			if (t==time) {
				quickestPathList.add(list.get(i));
			}
		}
		return "You need "+time+" min or more.\n "
				+ "You have "+quickestPathList.size()+" quickest routes:\n"+listToString(quickestPathList);
	
	}
}
