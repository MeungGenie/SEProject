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
		Random rnd;
		int block;
		
		int easyI = 0, hardI = 0;
		int easyJ = 0, hardJ = 0;
		int easyT = 0, hardT = 0;
		int easyO = 0, hardO = 0;
		int easyL = 0, hardL = 0;
		int easyZ = 0, hardZ = 0;
		int easyS = 0, hardS = 0;
		double easyPercent, hardPercent;
		double easySum = 0, hardSum = 0;
		double easyExpected, hardExpected;
		
		for(int i =0; i < 1000; i++) {
			min = 1;
			max = 100;
			percentage = Math.random() * (max - min) + min;
			if (percentage <= (double)100 / 720 * 100 * 1.2)
				easyI++;	
			else {
				rnd = new Random(System.currentTimeMillis());
				block = rnd.nextInt(6);
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
		easyPercent = easyI / easySum * 100;
		easyExpected = (double)100 / 720 * 100 * 1.2;
		Easy easy = new Easy();
		assertEquals(0, easy.percent(easyPercent, easyExpected), 5);
	}
}
