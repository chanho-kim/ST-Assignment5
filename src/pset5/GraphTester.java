package pset5;

import static org.junit.Assert.*;
import java.util.TreeSet;
import java.util.Set;
import org.junit.Test;

public class GraphTester {
	// tests for method "addEdge" in class "Graph"
	@Test
	public void tae0() {
		Graph g = new Graph();
		g.addEdge(0, 1);
		assertEquals("nodes: [0, 1]\nedges: {0=[1]}", g.toString());
	}

	// your tests for method "addEdge" in class "Graph" go here
	// you must provide at least 4 test methods;
	// each test method must have at least 1 invocation of addEdge;
	// each test method must have at least 1 test assertion;
	// your test methods must provide full statement coverage of your
	// implementation of addEdge and any helper methods
	// no test method directly invokes any method that is not
	// declared in the Graph class as given in this homework
	// tests for method "reachable" in class "Graph"
	
	@Test
	public void tae1(){
		Graph g = new Graph();
		g.addNode(0);
		g.addEdge(0, 1);
		assertEquals("nodes: [0, 1]\nedges: {0=[1]}", g.toString());
	}
	
	@Test
	public void tae2(){
		Graph g = new Graph();
		g.addNode(1);
		g.addEdge(0, 1);
		assertEquals("nodes: [0, 1]\nedges: {0=[1]}", g.toString());
	}
	
	@Test
	public void tae3(){
		Graph g = new Graph();
		g.addNode(0);
		g.addNode(1);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		assertEquals("nodes: [0, 1, 2]\nedges: {0=[1, 2]}", g.toString());
	}
	
	@Test
	public void tae4(){
		Graph g = new Graph();
		g.addEdge(0, 1);
		g.addEdge(2, 3);		
		assertEquals("nodes: [0, 1, 2, 3]\nedges: {0=[1], 2=[3]}", g.toString());	
	}
	
	@Test
	public void tr0() {
		Graph g = new Graph();
		g.addNode(0);
		Set<Integer> nodes = new TreeSet<Integer>();
		nodes.add(0);
		assertTrue(g.reachable(nodes, nodes));
	}
	// your tests for method "reachable" in class "Graph" go here
	// you must provide at least 6 test methods;
	// each test method must have at least 1 invocation of reachable;
	// each test method must have at least 1 test assertion;
	// at least 2 test methods must have at least 1 invocation of addEdge;
	// your test methods must provide full statement coverage of your
	// implementation of reachable and any helper methods
	// no test method directly invokes any method that is not
	// declared in the Graph class as given in this homework
	
	@Test
	public void tr1() {
		Graph g = new Graph();
		Set<Integer> nodes = new TreeSet<Integer>();
		assertTrue(g.reachable(nodes, nodes));
	}
	
	@Test
	public void tr2() {
		Graph g = new Graph();
		Set<Integer> nodes = new TreeSet<Integer>();
		nodes.add(0);
		assertFalse(g.reachable(nodes, nodes));
	}
	
	@Test
	public void tr3() {
		Graph g = new Graph();
		g.addNode(0);
		Set<Integer> sources = new TreeSet<Integer>();
		Set<Integer> targets = new TreeSet<Integer>();
		sources.add(0);
		targets.add(1);
		assertFalse(g.reachable(sources, targets));
	}	
	
	@Test
	public void tr4(){
		Graph g = new Graph();
		g.addEdge(0, 1);
		Set<Integer> sources = new TreeSet<Integer>();
		Set<Integer> targets = new TreeSet<Integer>();
		sources.add(0);
		targets.add(1);
		assertTrue(g.reachable(sources, targets));
	}
	
	@Test
	public void tr5(){
		Graph g = new Graph();
		g.addNode(0);
		g.addNode(1);
		Set<Integer> sources = new TreeSet<Integer>();
		Set<Integer> targets = new TreeSet<Integer>();
		sources.add(0);
		targets.add(1);		
		assertFalse(g.reachable(sources, targets));
	}
	
	@Test
	public void tr6(){
		Graph g = new Graph();
		g.addEdge(0,1);
		g.addEdge(1,2);
		g.addEdge(2,3);
		Set<Integer> sources = new TreeSet<Integer>();
		Set<Integer> targets = new TreeSet<Integer>();
		sources.add(0);
		targets.add(3);
		assertTrue(g.reachable(sources, targets));
	}
	
	@Test
	public void tr7(){
		Graph g = new Graph();
		g.addEdge(0,1);
		g.addEdge(1,2);
		g.addEdge(2,3);
		Set<Integer> sources = new TreeSet<Integer>();
		Set<Integer> targets = new TreeSet<Integer>();
		sources.add(3);
		targets.add(0);
		assertFalse(g.reachable(sources, targets));
	}
}