package level4_20210116;

public class Solution1 {
/*
 * DP ���� �ΰͿ� ������ �������� DP �迭 ������ ���� �ǹ��� �ִ� ����
 * 
 *  2 X 2 X N �迭 = �ð� �ʰ�
 *  2 X N �迭 2�� = �ð� �ʰ�
 *  2 XN �迭 �ϳ��� �ʱ�ȭ �� �ι� ��� = �ð� ��� 
 *  
 *  �� DP �� �ϴ� ������ ������ ����
 */
	   public int solution(int[] money) {
	       
	        int length = money.length;
	        int[][] dp1 = new int[length-1][2]; 
	        int[][] dp2 = new int[length][2]; 
	        //ù���б�/���б� X ������ �б�/���б�
	        int max1, max2;
	        //ù���� �ʹ� ���
	        dp1[0][1] = money[0] + 1;
	        
	        //������ ���� ���ϴ� 
	        for (int i = 1; i<length-1; i++) {
	        	//�� ���� ���о����� �Ѵ� �б� ���б� �Ѵٰ���
	        	if (dp1[i-1][0] != 0) {
	        		dp1[i][0] = dp1[i-1][0] ;
	        		dp1[i][1] = dp1[i-1][0] + money[i] ;
	        	}
	        	//�о����� ���б⸸ ����
	        	if (dp1[i-1][1] != 0) {
	        		if (dp1[i][0] == 0) dp1[i][0] = dp1[i-1][1];
	        		else dp1[i][0] = Math.max(dp1[i][0], dp1[i-1][1]);
	        	}
	        }
	        
	        max1 = Math.max(dp1[length-2][0], dp1[length-2][1]) -1;
	        
	        //ù���� ���ʹ� ���
	        dp2[0][0] = 1;
	        
	        for (int i =1; i<length; i++) {
	        	//�� ���� ���о����� �Ѵ� �б� ���б� �Ѵٰ���
	        	if (dp2[i-1][0] != 0) {
	        		dp2[i][0] = dp2[i-1][0] ;
	        		dp2[i][1] = dp2[i-1][0] + money[i] ;
	        	}
	        	//�о����� ���б⸸ ����
	        	if (dp2[i-1][1] != 0) {
	        		if (dp2[i][0] == 0) dp2[i][0] = dp2[i-1][1];
	        		else dp2[i][0] = Math.max(dp2[i][0], dp2[i-1][1]);
	        	}
	        }
	        
	        max2= Math.max(dp2[length-1][0], dp2[length-1][1]) -1;
	        
	        return Math.max(max1, max2);
	    }
}
