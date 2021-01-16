package level4_20210116;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution2 {
	/*
	 * Ʈ�� �����Ͽ� BFS ���� (�Ƹ���)
	 * 
	 * Ʈ�� BFS �� ����Ͽ��� �� �ð����⵵���� ũ�� ������ �������� 
	 * ������ �κп��� ����ȭ�� �ȵǾ����� 1 Case ����� ���� 
	 * ���� ��� �ʿ� 
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
    	//edge �Ϸ� 
    	for (int[] ord : order) {
    		orders.put(ord[0], ord[1]); //������ ����
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
	    		if (orders.containsKey(temp)) { //����� ����
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
