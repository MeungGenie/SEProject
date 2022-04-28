package se.tetris.main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class HardTest {

	@Test
	void testPercent() {
		double min;
		double max;
		double percentage;
		Random rnd;
		int block;
		
		int hardI = 0;
		int hardJ = 0;
		int hardT = 0;
		int hardO = 0;
		int hardL = 0;
		int hardZ = 0;
		int hardS = 0;
		double hardPercent;
		double hardSum = 0;
		double hardExpected;
		
		for(int i =0; i < 1000; i++) {
			min = 1;
			max = 100;
			percentage = Math.random() * (max - min) + min;
			if (percentage <= (double)100 / 680 * 100 * 0.8)
				hardI++;	
			else {
				rnd = new Random(System.currentTimeMillis());
				block = rnd.nextInt(6);
				switch(block) {
					case 0:
						hardJ++;
						break;
					case 1:
						hardL++;
						break;
					case 2:
						hardZ++;
						break;
					case 3:
						hardS++;
						break;
					case 4:
						hardT++;
						break;
					case 5:
						hardO++;
						break;
				}
				hardSum = (hardJ+hardL+hardS+hardT+hardO+hardZ);
			}	
		}
		hardPercent = hardI / hardSum * 100;
		hardExpected = (double)100 / 680 * 100 * 0.8;
		System.out.println("HARD MODE");
		System.out.println("I가 등장해야 하는 확률 : " + hardExpected + "   I의 실제 등장 확률 : " + hardPercent);
		System.out.println("오차 범위 : " + (hardExpected - hardPercent));
		Hard hard = new Hard();
		assertEquals(0, hard.percent(hardPercent, hardExpected), 5);
	}
}
