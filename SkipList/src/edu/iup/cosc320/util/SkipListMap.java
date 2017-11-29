package edu.iup.cosc320.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SkipListMap<K extends Comparable<K>, V> implements Map<K, V> {
	private int maxHeight = 6;
	private RandomHeight randomHeight = new RandomHeight(maxHeight);
	private int noElements;
	private Node<K,V> head = new Node<K,V>(null, null, maxHeight);

	
	private static class Node<K,V> {
		K key;
		V value;
		Node<K,V>[] nexts;
		
		@SuppressWarnings("unchecked")
		public Node(K key, V value, int height) {
			super();
			this.key = key;
			this.value = value;
			this.nexts = new Node[height];
		}
	}
	
	public void setHeights(int[] initHeights) {
		randomHeight.setHeights(initHeights);
	}

	@Override
	public int size() {
		return noElements;
	}

	@Override
	public boolean isEmpty() {
		return noElements == 0;
	}
	
	private Node<K,V>[] findPrevs(Object key) {
		@SuppressWarnings("unchecked")
		Node<K,V>[] prevs = new Node[maxHeight];
				
		Node<K,V> current = head;
		
		@SuppressWarnings("unchecked")
		Comparable<? super K> keyComparable = (Comparable<? super K>)key;

		for (int i = prevs.length - 1; i >= 0; i--) {        

			while (current.nexts[i] != null && keyComparable.compareTo(current.nexts[i].key) > 0) {
				current = current.nexts[i];
			}
			
			prevs[i] = current;
		}
		
		return prevs;
		
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object key) {
		Node<K,V>[] prevs = findPrevs(key);
		
		if (prevs[0].nexts[0] != null && prevs[0].nexts[0].key.equals(key) ) {
			return  (V) prevs[0].nexts[0].value;
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		Node<K,V>[] prevs = findPrevs(key);
		
		if (prevs[0].nexts[0] != null && prevs[0].nexts[0].key.equals(key) ) {
			V lastValue =  (V) prevs[0].nexts[0].value;
			prevs[0].nexts[0].value = value;
			return lastValue;
		} 
		
		Node<K,V> newNode = new Node<K,V>(key, value, randomHeight.nextRandomHeight());
		
		System.out.println("Adding " + key + " height " + newNode.nexts.length);
		
		for (int i = 0; i < newNode.nexts.length;  i++) {
			newNode.nexts[i] = prevs[i].nexts[i];
			prevs[i].nexts[i] = newNode;
		}
		
		noElements++;
		
		return null;
	}

	@Override
	public V remove(Object key) {
		Node<K,V>[] prevs = findPrevs(key);

		if (prevs[0].nexts[0] != null && prevs[0].nexts[0].key.equals(key) ) {
			Node<K,V> node = prevs[0].nexts[0];
			for (int i = 0; i < node.nexts.length;  i++) {
				prevs[i].nexts[i] = node.nexts[i];
			}
			noElements--;
			return node.value;
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new TreeSet<K>();
		Node curr = head.nexts[0];
		while (curr != null) {
			set.add((K) curr.key);
			curr = curr.nexts[0];
		}
		
		return set;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
