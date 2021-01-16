package level4_20210116;

public class Solution1 {
/*
 * DP 문제 인것엔 여지가 없었으나 DP 배열 생성에 조금 의문이 있던 문제
 * 
 *  2 X 2 X N 배열 = 시간 초과
 *  2 X N 배열 2개 = 시간 초과
 *  2 XN 배열 하나로 초기화 후 두번 사용 = 시간 통과 
 *  
 *  원 DP 는 일단 조건을 나누고 생각
 */
	   public int solution(int[] money) {
	       
	        int length = money.length;
	        int[][] dp1 = new int[length-1][2]; 
	        int[][] dp2 = new int[length][2]; 
	        //첫집털기/안털기 X 지금집 털기/안털기
	        int max1, max2;
	        //첫집을 터는 경우
	        dp1[0][1] = money[0] + 1;
	        
	        //마지막 집은 못턴다 
	        for (int i = 1; i<length-1; i++) {
	        	//전 집을 안털었으면 둘다 털기 안털기 둘다가능
	        	if (dp1[i-1][0] != 0) {
	        		dp1[i][0] = dp1[i-1][0] ;
	        		dp1[i][1] = dp1[i-1][0] + money[i] ;
	        	}
	        	//털었으면 안털기만 가능
	        	if (dp1[i-1][1] != 0) {
	        		if (dp1[i][0] == 0) dp1[i][0] = dp1[i-1][1];
	        		else dp1[i][0] = Math.max(dp1[i][0], dp1[i-1][1]);
	        	}
	        }
	        
	        max1 = Math.max(dp1[length-2][0], dp1[length-2][1]) -1;
	        
	        //첫집을 안터는 경우
	        dp2[0][0] = 1;
	        
	        for (int i =1; i<length; i++) {
	        	//전 집을 안털었으면 둘다 털기 안털기 둘다가능
	        	if (dp2[i-1][0] != 0) {
	        		dp2[i][0] = dp2[i-1][0] ;
	        		dp2[i][1] = dp2[i-1][0] + money[i] ;
	        	}
	        	//털었으면 안털기만 가능
	        	if (dp2[i-1][1] != 0) {
	        		if (dp2[i][0] == 0) dp2[i][0] = dp2[i-1][1];
	        		else dp2[i][0] = Math.max(dp2[i][0], dp2[i-1][1]);
	        	}
	        }
	        
	        max2= Math.max(dp2[length-1][0], dp2[length-1][1]) -1;
	        
	        return Math.max(max1, max2);
	    }
}
