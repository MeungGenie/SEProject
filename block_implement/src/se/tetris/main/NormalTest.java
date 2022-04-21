package se.tetris.main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class NormalTest {

	@Test
	void testPercent() {
		Random rnd;
		int block;
		
		int normalI = 0;
		int normalJ = 0;
		int normalT = 0;
		int normalO = 0;
		int normalL = 0;
		int normalZ = 0;
		int normalS = 0;
		double normalPercent;
		double normalSum = 0;
		double normalExpected;
		
		for(int i =0; i < 1000; i++) {
			rnd = new Random(System.currentTimeMillis());
			block = rnd.nextInt(7);
			switch(block) {
				case 0:
					normalJ++;
					break;
				case 1:
					normalL++;
					break;
				case 2:
					normalZ++;
					break;
				case 3:
					normalS++;
					break;
				case 4:
					normalT++;
					break;
				case 5:
					normalO++;
					break;
				case 6:
					normalI++;
					break;
				}
			normalSum = (normalI+ normalJ+normalL+normalS+normalT+normalO+normalZ);
		}
		normalPercent = (double)normalSum / 7 / 10;
		normalExpected = (double)100/7;
		System.out.println("NORMAL MODE");
		System.out.println("모든 블럭의 등장 확률 합 : " + normalExpected + "   I의 실제 등장 확률 : " + normalPercent);
		System.out.println("오차 범위 : " + (normalExpected - normalPercent));
		Normal normal = new Normal();
		assertEquals(0, normal.percent(normalPercent, normalExpected), 5);
	}
}
