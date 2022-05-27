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
			System.out.println("\nNORMAL MODE������ I��� ���� Ȯ�� Ȯ��");
			
			Normal normal = new Normal();
			assertEquals(0, normal.percent((double)normalI / 1000, normalExpected), 5);
			assertEquals(0, normal.percent((double)normalJ / 1000, normalExpected), 5);
			assertEquals(0, normal.percent((double)normalL / 1000, normalExpected), 5);
			assertEquals(0, normal.percent((double)normalS / 1000, normalExpected), 5);
			assertEquals(0, normal.percent((double)normalT / 1000, normalExpected), 5);
			assertEquals(0, normal.percent((double)normalO / 1000, normalExpected), 5);
			assertEquals(0, normal.percent((double)normalZ / 1000, normalExpected), 5);
			
			System.out.println("�� ���� �����ؾ� �ϴ� Ȯ�� : " + String.format("%.6f",normalExpected * 100) + " (���� ����)");
			System.out.print("I�� ���� ���� Ȯ�� : " + (double)normalI / 10);
			System.out.println("\tI�� ���� ���� : " + String.format("%.6f",normal.percent((double)normalI / 1000, normalExpected* 10)));
			System.out.print("J�� ���� ���� Ȯ�� : " + (double)normalJ / 10);
			System.out.println("\tJ�� ���� ���� : " + String.format("%.6f",normal.percent((double)normalJ / 1000, normalExpected* 10)));
			System.out.print("L�� ���� ���� Ȯ�� : " + (double)normalL / 10);
			System.out.println("\tL�� ���� ���� : " + String.format("%.6f",normal.percent((double)normalL / 1000, normalExpected* 10)));
			System.out.print("S�� ���� ���� Ȯ�� : " + (double)normalS / 10);
			System.out.println("\tS�� ���� ���� : " + String.format("%.6f",normal.percent((double)normalS / 1000, normalExpected* 10)));
			System.out.print("T�� ���� ���� Ȯ�� : " + (double)normalT / 10);
			System.out.println("\tT�� ���� ���� : " + String.format("%.6f",normal.percent((double)normalT / 1000, normalExpected* 10)));
			System.out.print("O�� ���� ���� Ȯ�� : " + (double)normalO / 10);
			System.out.println("\tO�� ���� ���� : " + String.format("%.6f",normal.percent((double)normalO / 1000, normalExpected* 10)));
			System.out.print("Z�� ���� ���� Ȯ�� : " + (double)normalZ / 10);
			System.out.println("\tZ�� ���� ���� : " + String.format("%.6f",normal.percent((double)normalZ / 1000, normalExpected* 10)));
		}
}
