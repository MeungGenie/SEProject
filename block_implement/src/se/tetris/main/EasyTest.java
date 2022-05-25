package se.tetris.main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class EasyTest {

	@Test
	void testPercent() {
		double min;
		double max;
		double percentage;
		int block;
		
		int easyI = 0;
		int easyJ = 0;
		int easyT = 0;
		int easyO = 0;
		int easyL = 0;
		int easyZ = 0;
		int easyS = 0;
		
		double easySum = 0;
		
		for(int i =0; i < 1000; i++) {
			min = 1;
			max = 100;
			percentage = Math.random() * (max - min) + min;
			if (percentage <= (double)100 / 720 * 100 * 1.2)
				easyI++;	
			else {
				block = (int)(Math.random() * 6);
				switch(block) {
				case 0:
					easyJ++;
					break;
				case 1:
					easyL++;
					break;
				case 2:
					easyZ++;
					break;
				case 3:
					easyS++;
					break;
				case 4:
					easyT++;
					break;
				case 5:
					easyO++;
					break;
				}
				easySum = (easyJ+easyL+easyS+easyT+easyO+easyZ);
			}	
		}
		double easyPercent = easyI / easySum * 100;
		double easyExpected = (double)100 / 720 * 100 * 1.2;
		System.out.println("\nEASY MODE에서의 I블록 등장 확률 확인");
		System.out.println("I가 등장해야 하는 확률 : " + easyExpected + "   I의 실제 등장 확률 : " + easyPercent);
		System.out.println("오차 범위 : " + (easyExpected - easyPercent));
		Easy easy = new Easy();
		assertEquals(0, easy.percent(easyPercent, easyExpected), 5);
	}
}
