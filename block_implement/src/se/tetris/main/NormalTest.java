package se.tetris.main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class NormalTest {

	@Test
	void testPercent() {
		int block;
		
		int normalI = 0;
		int normalJ = 0;
		int normalT = 0;
		int normalO = 0;
		int normalL = 0;
		int normalZ = 0;
		int normalS = 0;
		
		for(int j = 0; j < 10; j++)
		{
			for(int i =0; i < 1000; i++) {
				block = (int)(Math.random() * 7);
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
			}
			double normalExpected = (double)1000/7000;
			System.out.println("\nNORMAL MODE에서의 I블록 등장 확률 확인");
			
			Normal normal = new Normal();
			assertEquals(0, normal.percent((double)normalI / 1000, normalExpected), 5);
			assertEquals(0, normal.percent((double)normalJ / 1000, normalExpected), 5);
			assertEquals(0, normal.percent((double)normalL / 1000, normalExpected), 5);
			assertEquals(0, normal.percent((double)normalS / 1000, normalExpected), 5);
			assertEquals(0, normal.percent((double)normalT / 1000, normalExpected), 5);
			assertEquals(0, normal.percent((double)normalO / 1000, normalExpected), 5);
			assertEquals(0, normal.percent((double)normalZ / 1000, normalExpected), 5);
			
			System.out.println("각 블럭이 등장해야 하는 확률 : " + String.format("%.6f",normalExpected) + " (전부 동일)");
			System.out.print("I의 실제 등장 확률 : " + (double)normalI / 100);
			System.out.println("\tI의 오차 범위 : " + String.format("%.6f",normal.percent((double)normalI / 1000, normalExpected)));
			System.out.print("J의 실제 등장 확률 : " + (double)normalJ / 100);
			System.out.println("\tJ의 오차 범위 : " + String.format("%.6f",normal.percent((double)normalJ / 1000, normalExpected)));
			System.out.print("L의 실제 등장 확률 : " + (double)normalL / 100);
			System.out.println("\tL의 오차 범위 : " + String.format("%.6f",normal.percent((double)normalL / 1000, normalExpected)));
			System.out.print("S의 실제 등장 확률 : " + (double)normalS / 100);
			System.out.println("\tS의 오차 범위 : " + String.format("%.6f",normal.percent((double)normalS / 1000, normalExpected)));
			System.out.print("T의 실제 등장 확률 : " + (double)normalT / 100);
			System.out.println("\tT의 오차 범위 : " + String.format("%.6f",normal.percent((double)normalT / 1000, normalExpected)));
			System.out.print("O의 실제 등장 확률 : " + (double)normalO / 100);
			System.out.println("\tO의 오차 범위 : " + String.format("%.6f",normal.percent((double)normalO / 1000, normalExpected)));
			System.out.print("Z의 실제 등장 확률 : " + (double)normalZ / 100);
			System.out.println("\tZ의 오차 범위 : " + String.format("%.6f",normal.percent((double)normalZ / 1000, normalExpected)));
		}
	}
}
