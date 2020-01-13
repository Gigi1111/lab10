package lab10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public interface WeightedGraphFromList_interface{
	HashMap<String, LinkedList<Edge> > hmap = new HashMap<String, LinkedList<Edge>>(); 
	LinkedList<LinkedList<Edge>> allPath= new LinkedList<LinkedList<Edge>>();

	public String[] getVertex();
    public void addVertex(String s);
    public void addEdge(String source,String destination,String trans,int time);
    public int numOfVertex();
    public int numOfEdges(String s);
    public boolean hasVertex(String s);
    public boolean hasEdge(String source,String destination,String trans,int time); 
    public String toString();
    public void setGraphDirected(boolean dir);
    public boolean isGraphDirected();
  //  public void printPath();
	 public String getAllPaths(String start, String mid, String end, int layer,int numOfEdges, int nthEdge);
		public String getLightestPath();
		public String getShortestPaths();
}

