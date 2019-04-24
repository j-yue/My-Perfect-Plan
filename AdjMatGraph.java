/** AdjMatGraph.java
  * Graph implementation using an adjacency matrix.
  * 
  * Modified to include isSink(), isSource(), allSinks(), allSources(),
  * getBreadth() and getDepth() methods.
  * 
  * Jeanette Yue and Sam Mincheva
  * CS 230 Fall 2014 Final Project
  * 
  * Written for: CS 230 PS7
  * Written on: 11/05/2014
  * 
  * @author Sam Mincheva, Mariya Getsova
  */

import java.util.*;
import java.io.*;
import javafoundations.*;

public class AdjMatGraph<T> implements Graph<T> {
  
  private int n;
  private T[] vertices;
  private boolean[][] arcs;
  private static final int DEFAULT_SIZE = 1; //small for testing purposes
  private static final int NOT_FOUND = -1;
  
  //-------------------------------------------------------------------------------
  /** Intitializes a new AdjMatGraph with no vertices.
    */
  public AdjMatGraph() {
    n = 0;
    vertices = (T[])(new Object[DEFAULT_SIZE]);
    arcs = new boolean[DEFAULT_SIZE][DEFAULT_SIZE];
  }
  
  //-------------------------------------------------------------------------------
  /** Returns true if this graph is empty, false otherwise. */
  public boolean isEmpty() {
    return n == 0;
  }
  
  
  //-------------------------------------------------------------------------------
  /** Returns the number of vertices in this graph. */
  public int n() {
    return n;
  }
  
  
  //-------------------------------------------------------------------------------
  /** Returns the number of arcs in this graph. */
  public int m() {
    int count = 0; //initialize count variable
    for (int i = 0; i < n; i++) { 
      for (int j = 0; j < n; j++) {
        if (arcs[i][j]) count++; //increment count everytime there is an arc
      }
    }
    return count; //return count
  }
  
  
  //-------------------------------------------------------------------------------
  /** Returns the index of the specified element in this graph's vertex array. If 
    * the element isn't found, returns NOT_FOUND value. Helper method. */
  private int getIndex(T vertex) {
    for (int i = 0; i < n; i++) {
      if (vertices[i].equals(vertex)) return i; //if found return index
    }
    return NOT_FOUND; //if not found return default
  }
  
  
  //-------------------------------------------------------------------------------
  /** Returns true iff a directed edge exists from v1 to v2. */
  public boolean isArc(T vertex1, T vertex2) {
    int ind1 = this.getIndex(vertex1); //stores vertex indices for readability
    int ind2 = this.getIndex(vertex2);
    
    if (ind1 != NOT_FOUND && ind2 != NOT_FOUND) //if both elements in the graph
      return arcs[ind1][ind2];
    
    else return false; //if elements not in the graph, no arc between them
  }
  
  
  //-------------------------------------------------------------------------------
  /** Returns true iff an edge exists between two given vertices which means that 
    * two corresponding arcs exist in the graph */
  public boolean isEdge(T vertex1, T vertex2) {
    int ind1 = this.getIndex(vertex1); //stores vertex indices for readability
    int ind2 = this.getIndex(vertex2);
    if (ind1 != NOT_FOUND && ind2 != NOT_FOUND) //if both elements in the graph
      return arcs[ind1][ind2] && arcs[ind2][ind1]; //checks both slots
    
    else return false; //if elements not in the graph, no arc between them
  }
  
  
  //-------------------------------------------------------------------------------
  /** Returns true IFF the graph is undirected, that is, for every pair of nodes 
    * i,j for which there is an arc, the opposite arc is also present in the 
    * graph. */
  public boolean isUndirected() {
    //Go through adjacency matrix
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (arcs[i][j] != arcs[j][i]) return false; //if any pair doesn't match
      }
    }
    return true; //if all pairs match
  }
  
  
  //-------------------------------------------------------------------------------
  /** Adds a vertex to this graph. If the vertexalready exists, nothing is 
    * inserted. */
  public void addVertex (T vertex) {
    if (this.getIndex(vertex) == NOT_FOUND) { //only if vertex is not in graph
      if (n >= vertices.length) this.expand(); //if out of space, expand
      vertices[n] = vertex; //add vertex
      n++; //increase vertex count
    }
  }
  
  
  //-------------------------------------------------------------------------------
  /** Doubles the size of this graph's vertex array and adjacency matrix. Helper
    * method. */
  private void expand() {
    
    int doubled = vertices.length * 2; //for readability
    T[] tempV = (T[])(new Object[doubled]); //temp array for vertices
    boolean[][] tempA = new boolean[doubled][doubled]; //temp array for arcs
    
    for (int i = 0; i < n; i++) { //nested for-loops
      tempV[i] = vertices[i]; //copies vertices in outer loop
      for (int j = 0; j < n; j++) {
        tempA[i][j] = arcs[i][j]; //copies arcs in inner loop
      }
    }
    
    //Reassigns the vertices and arcs arrays
    vertices = tempV;
    arcs = tempA;
  }
//  
//   /******************************************************************
//    Removes a single vertex with the given value from the graph.  
//    Uses equals() for testing equality.
//  ******************************************************************/
// public void removeVertex (T vertex) {
//  int index = getIndex(vertex);
//  if (index != NOT_FOUND) {
//   removeVertex(index);
//  }
// }
//
// /******************************************************************
//    Helper. Removes a vertex at the given index from the graph.   
//    Note that this may affect the index values of other vertices.
//    @throws IllegalArgumentException if the index is invalid.
//  ******************************************************************/
// protected void removeVertex (int index) {
//  n--;
//
//  // Remove vertex.
//  for (int i = index; i < n; i++) {
//   vertices[i] = vertices[i+1];
//  }
//
//  // Move rows up.
//  for (int i = index; i < n; i++) {
//   for (int j = 0; j <= n; j++) {
//    arcs[i][j] = arcs[i+1][j];
//   }
//  }
//
//  // Move columns left
//  for (int i = index; i < n; i++) {
//   for (int j = 0; j < n; j++) {
//    arcs[j][i] = arcs[j][i+1];
//   }
//  }
//  
//  // Erase last row and last column
//  for (int a = 0; a < n; a++) {
//   arcs[n][a] = false;
//   arcs[a][n] = false;
//  }
// }
  
  
  //-------------------------------------------------------------------------------
  /** Removes a single vertex with the given value from this graph. If the vertex
    * does not exist, it does not change the graph. */
  public void removeVertex (T vertex) {
    int index = this.getIndex(vertex); //vertex index for readability
    if (index != NOT_FOUND) { //if vertex is in graph
      n--;
      
      // Remove vertex.
      for (int i = index; i < n; i++) {
        vertices[i] = vertices[i+1];
      }
      
      // Move rows up.
      for (int i = index; i < n; i++) {
        for (int j = 0; j <= n; j++) {
          arcs[i][j] = arcs[i+1][j];
        }
      }
      
      // Move columns left
      for (int i = index; i < n; i++) {
        for (int j = 0; j < n; j++) {
          arcs[j][i] = arcs[j][i+1];
        }
      }
      
      // Erase last row and last column
      for (int a = 0; a < n; a++) {
        arcs[n][a] = false;
        arcs[a][n] = false;
      }
    }
//      n--;
//      for (int i = index; i < n; i++) {
//        vertices[i] = vertices[i+1]; //shift vertices left
//        
//        for (int j = 0; j <= n; j++) {
//          arcs[i][j] = arcs[i+1][j]; //shift rows up
//        }
//        
//        for (int j = 0; j < n; j++) {
//          arcs[j][i] = arcs[j][i+1]; //shift columns left
//        }
//        //erase last row and column of arcs
//        arcs[n][i] = false;
//        arcs[i][n] = false;
//      }

}


//-------------------------------------------------------------------------------
/**  Inserts an arc from vertex1 to vertex2.
  If the vertices exist. Else it does not change the graph. */
public void addArc (T vertex1, T vertex2) {
  int ind1 = this.getIndex(vertex1); //vertex indices for readability 
  int ind2 = this.getIndex(vertex2);
  if (ind1 != NOT_FOUND && ind2 != NOT_FOUND) {//if both vertices in graph
    arcs[ind1][ind2] = true; //add arc
  }
}


//-------------------------------------------------------------------------------
/** Removes an arc from vertex v1 to vertex v2,
  * if the vertices exist. Else it does not change the graph. */
public void removeArc (T vertex1, T vertex2) {
  int ind1 = this.getIndex(vertex1); //vertex indices for readability
  int ind2 = this.getIndex(vertex2);
  if (ind1 != NOT_FOUND && ind2 != NOT_FOUND) //if both vertices in graph
    arcs[ind1][ind2] = false; //remove arc
}


//-------------------------------------------------------------------------------
/** Inserts an edge between two vertices of this graph,
  * if the vertices exist. Else does not change the graph. */
public void addEdge (T vertex1, T vertex2) {
  int ind1 = this.getIndex(vertex1); //vertex indices for readability
  int ind2 = this.getIndex(vertex2);
  if (ind1 != NOT_FOUND && ind2 != NOT_FOUND) { //if both vertices in graph
    arcs[ind1][ind2] = true; //add edge
    arcs[ind2][ind1] = true;
  }
}


//-------------------------------------------------------------------------------
/** Removes an edge between two vertices of this graph,
  if the vertices exist, else does not change the graph. */
public void removeEdge (T vertex1, T vertex2) {
  int ind1 = this.getIndex(vertex1); //vertex indices for readability
  int ind2 = this.getIndex(vertex2);
  if (ind1 != NOT_FOUND && ind2 != NOT_FOUND) { //if both vertices in graph
    arcs[ind1][ind2] = false; //remove edge
    arcs[ind2][ind1] = false;
  }
}


//-------------------------------------------------------------------------------
/** Retrieve the vertices x following vertex v (v->x) and returns them onto a 
  * linked list */
public LinkedList<T> getSuccessors(T vertex) {
  int ind = this.getIndex(vertex); //vertex index for readability 
  LinkedList<T> result = new LinkedList<T>(); //result list
  if (ind != NOT_FOUND) { //if vertex is in graph
    for (int i = 0; i < n; i++) {
      if (arcs[ind][i]) result.add(vertices[i]); //append vertex if arc
    }
  }
  return result;
}


//-------------------------------------------------------------------------------
/** Retrieve the vertices x pointing to vertex v (x->v) and returns them onto a 
  * linked list */
public LinkedList<T> getPredecessors(T vertex) {
  int ind = this.getIndex(vertex); //vertex index for readability 
  LinkedList<T> result = new LinkedList<T>(); //result list
  if (ind != NOT_FOUND) { //if vertex is in graph 
    for (int i = 0; i < n; i++) {
      if (arcs[i][ind]) result.add(vertices[i]); //append vertex if arc
    }
  }
  return result;
}


//-------------------------------------------------------------------------------
/** Returns a string representation of the adjacency matrix. */
public String toString() {
  String result = "Arcs\n-----\ni "; //initializes the resulting string
  
  for (int i = 0; i < n; i++) //adds first row of objects
    result += vertices[i] + " ";
  result += "\n";
  
  //adds the arcs array, starting with the object on each line
  for (int i = 0; i < n; i++) { 
    result += vertices[i] + " "; //vertex
    
    for (int j = 0; j < n; j++) {
      result += (arcs[i][j] ? "1 " : "- "); //if there is an arc, adds 1
    }
    
    result += "\n"; //new line 
  }
  return result;
}


//-------------------------------------------------------------------------------
/** Saves this graph into a .tgf file. Prints out a message if it cannot save it
  * into the file.
  * 
  * @param tgf_file_name the name of the file to be saved
  */
public void saveTGF(String tgf_file_name) {
  try {
    //Creates a new PrintWriter object
    PrintWriter writer = new PrintWriter(new File(tgf_file_name));
    
    //Prints the numbered vertices of this graph
    for (int i = 0; i < n; i++) {
      writer.println((i + 1) + " " + vertices[i]);
    }
    
    writer.println("#"); //pound sign to distinguish between vertices and arcs
    //Prints the arcs of the graph
    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (arcs[i][j]) writer.println((i+1) + " " + (j+1));
      }
    }
    writer.close();
  } catch (IOException e) {
    System.out.println(e);
  }
}


//-------------------------------------------------------------------------------
/** Checks if a vertex is a sink, i.e. points to no other vertices.
  * 
  * @param vertex the potential sink vertex
  * @return true if vertex is a sink, false otherwise
  * @throws IllegalArgumentException if vertex not in graph
  */
public boolean isSink(T vertex) {
  int ind = this.getIndex(vertex);
  if (ind != NOT_FOUND) { //checks if vertex in graph
    for (int i = 0; i < n; i++) {
      //if there is any arc from that vertex, returns false
      if (arcs[ind][i]) return false;
    }
    return true; //if no arcs were found, returns true
  }
  //if vertex not in graph, throws exception
  else throw new IllegalArgumentException();
}


//-------------------------------------------------------------------------------
/** Retrieves all vertices in this graph that are sinks.
  * 
  * @return a LinkedList with all the sink vertices
  */
public LinkedList<T> allSinks() {
  LinkedList<T> sinks = new LinkedList<T>(); //result list
  for (int i = 0; i < n; i++) {
    T vertex = vertices[i];
    if (this.isSink(vertex)) sinks.add(vertex);
  }
  return sinks; //returns list
}


//-------------------------------------------------------------------------------
/** Checks if a vertex is a sink, i.e. no other vertices point to it.
  * 
  * @param vertex the potential source vertex
  * @return true if vertex is a source, false otherwise
  * @throws IllegalArgumentException if vertex not in graph
  */
public boolean isSource(T vertex) {
  int ind = this.getIndex(vertex);
  if (ind != NOT_FOUND) { //checks if vertex in graph
    for (int i = 0; i < n; i++) {
      //if there is any arc to that vertex, returns false
      if (arcs[i][ind]) return false;
    }
    return true; //if no arcs were found, returns true
  }
  //if vertex not in graph, throws exception
  else throw new IllegalArgumentException();
}


//-------------------------------------------------------------------------------
/** Retrieves all vertices in this graph that are sources.
  * 
  * @return a LinkedList with all the source vertices
  */
public LinkedList<T> allSources() {
  LinkedList<T> sources = new LinkedList<T>(); //result list
  for (int i = 0; i < n; i++) {
    T vertex = vertices[i];
    if (this.isSource(vertex)) {
      sources.add(vertex);
    }
  }
  return sources; //returns list
}


//-------------------------------------------------------------------------------
/** Checks if a vertex is isolated, i.e. no vertices point to it, and it points
  * to no vertices.
  * 
  * @param vertex the potential isolated vertex
  * @return true if vertex is isolated, false otherwise
  */
public boolean isIsolated(T vertex) {
  //Checks if vertex is both a sink and a source
  //Doesn't explicitly throw exception, since the other methods will
  return this.isSink(vertex) && this.isSource(vertex);
}


//-------------------------------------------------------------------------------
/** Returns the biggest number of consecutive successors a vertex has, i.e. how
  * far into the graph you can starting at that vertex.
  * 
  * @param vertex the starting vertex
  * @return the length of the vertex's longest chain of successors
  */
public int getDepth(T vertex) {
  if (this.getIndex(vertex) == NOT_FOUND) //if vertex not in graph
    throw new IllegalArgumentException();
  
  LinkedList<T> successors = this.getSuccessors(vertex); //get successors
  
  int result = 0; //initialize result to 0
  
  for (int i = 0; i < successors.size(); i++) { //go through each successors
    int next = 1; //initialize chain length to 1
    next += this.getDepth(successors.get(i)); //recurse to get depth
    
    //if this successor's depth is larger than so far, set it as the result
    if (next > result) result = next;
  }
  
  return result; //return result
}

//-------------------------------------------------------------------------------
/** Returns the number of immediate successors the specified vertex has.
  * 
  * @param vertex the starting vertex
  * @return the number of immediate successors to vertex
  */
public int getBreadth(T vertex) {
  if (this.getIndex(vertex) == NOT_FOUND) //if vertex not in graph
    throw new IllegalArgumentException();
  
  return this.getSuccessors(vertex).size(); //return number of successors
}
}
