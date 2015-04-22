package pset5;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.Set;

public class Graph {
	private Set<Integer> nodes; // set of nodes in the graph
	private Map<Integer, TreeSet<Integer>> edges;
	// map between a node and a set of nodes that are connected to it by
	// outgoing edges
	// class invariant: fields "nodes" and "edges" are non-null;
	// "this" graph has no node that is not in "nodes"
	public Graph() {
		nodes = new TreeSet<Integer>();
		edges = new TreeMap<Integer, TreeSet<Integer>>();
	}

	public String toString() {
		return "nodes: " + nodes + "\n" + "edges: " + edges;
	}

	public void addNode(int n) {
		// postcondition: adds the node "n" to this graph
		nodes.add(n);
	}

	public void addEdge(int from, int to) {
		// postcondition: adds a directed edge "from" -> "to" to this graph
		
		//if either of the target nodes doesn't exist, add those nodes first!
		if(!nodes.contains(from)){
			addNode(from);
		}
		if(!nodes.contains(to)){
			addNode(to);
		}
		
		//get the edges the 'from' node is connected to
		TreeSet<Integer> e = edges.get(from);
		
		//if the set hasn't been initialized yet, then initialize it
		if(e == null) e = new TreeSet<Integer>();
		
		//add the new edge
		e.add(to);		
		edges.put(from, e);		
	}

	public boolean reachable(Set<Integer> sources, Set<Integer> targets) {
		if (sources == null || targets == null)
			throw new IllegalArgumentException();
		// postcondition: returns true if (1) "sources" is a subset of "nodes",
		// (2) "targets" is
		// a subset of "nodes", and (3) for each node "m" in set "targets",
		// there is some node "n" in set "sources" such that there is a
		// directed path that starts at "n" and ends at "m" in "this"; and
		// false otherwise
		
		//condition 0, are the sets empty?
		if(sources.isEmpty() && targets.isEmpty()) return true;

		//condition 1, all of 'sources' are in 'nodes'
		Iterator<Integer> iterator = sources.iterator();
		while(iterator.hasNext()){
			if(!nodes.contains(iterator.next())) return false;
		}
		
		//condition 2, all of 'targets' are in 'nodes'
		iterator = targets.iterator();
		while(iterator.hasNext()){
			if(!nodes.contains(iterator.next())) return false;
		}
		
		//condition 3
		Object[] temp = nodes.toArray();
		Integer[] nodesArray = Arrays.copyOf(temp, temp.length, Integer[].class);
		
		iterator = targets.iterator();
		while(iterator.hasNext()){
			boolean checked = false;
			int target = iterator.next();
			Iterator<Integer> t = sources.iterator();
			
			while(t.hasNext()){
				checked = false;
				int source = t.next();
				boolean[] visited = new boolean[nodesArray.length];
				ArrayDeque<Integer> q = new ArrayDeque<Integer>();
			
				for(int i=0; i<visited.length; i++) visited[i] = false;
				
				q.add(source);
				
				while(!q.isEmpty()){
					int next = q.remove();
					if(next == target){
						checked = true;
						break;
					}
					int index = indexOf(nodesArray,next);
					if(!visited[index]){
						visited[index] = true;
						TreeSet<Integer> e = edges.get(next);
						if(e != null){
							Iterator<Integer> iter = e.iterator();
							while(iter.hasNext()){
								int n = iter.next();
								int nIndex = indexOf(nodesArray,n);
								if(!visited[nIndex]){
									q.add(n);
								}
							}
						}
					}
				}
				if(checked) break;
			}
			if(!checked) return false;
		}
		return true;
	}
	
	private int indexOf(Integer[] array, int i){
		int result = -1;
		Integer in = new Integer(i);
		for(int j=0; j<array.length; j++){
			if(array[j].equals(in)) return j;
		}
		return result;
	}
}