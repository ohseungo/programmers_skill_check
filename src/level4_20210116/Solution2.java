package level4_20210116;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution2 {
	/*
	 * 트리 구현하여 BFS 문제 (아마도)
	 * 
	 * 트리 BFS 로 계산하였을 때 시간복잡도에는 크게 문제가 없었으나 
	 * 자잘한 부분에서 최적화가 안되었는지 1 Case 통과를 못함 
	 * 좀더 고민 필요 
	 */
    public boolean solution(int n, int[][] path, int[][] order) {
       
    	Set<Integer>[] edges = new HashSet[n];
    	Map<Integer, Integer> orders = new HashMap<>();
    	boolean[] isLocked= new boolean[n];
    	for (int i = 0; i<n; i++) {
    		edges[i] = new HashSet<>();
    	}
    	
    	for (int[] edge : path) {
    	 edges[edge[0]].add(edge[1]);
    	 edges[edge[1]].add(edge[0]);
    	}
    	//edge 완료 
    	for (int[] ord : order) {
    		orders.put(ord[0], ord[1]); //조건이 존재
    		isLocked[ord[1]] = true;
    	}
    	
    	Queue<Integer> q = new LinkedList<>();
    	q.add(0);
    	boolean[] isVisited = new boolean[n];
    	boolean isChanged = false;
    	int temp;
    	int size;
    	while(!q.isEmpty()) {
    		isChanged = false;
    		size = q.size();
    		
    		for (int i =1; i<=size; i++) {
    			temp = q.poll();
	    		if (orders.containsKey(temp)) { //잠금이 해제
					isLocked[orders.get(temp)] = false;
					orders.remove(temp);
					isChanged = true;
				}
	    		
	    		if (isLocked[temp]) {
	    			q.offer(temp);
	    		}else {
	    			isVisited[temp] = true;
	        		for (int next : edges[temp]) {
	        			if (isVisited[next]) continue;
	        			
	        			q.offer(next);
	        			isChanged = true;
	        		}
	    		}
    		}
    		if (!isChanged) break;
    	}
    	for (boolean b : isVisited) {
    		if (!b) return false;
    	}
    	
        return true;
    }
}
