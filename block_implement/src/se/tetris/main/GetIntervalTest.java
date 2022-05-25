package se.tetris.main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GetIntervalTest {

	@Test
	void testSpeed() {
		double easyRate = (1 - 0.1) + (0.1 * 0.2);
		double normalRate = (1 - 0.1);
		double hardRate = (1 - 0.1) - (0.1 * 0.2);
		
		GetInterval interval = new GetInterval();
		for(int eraseCnt = 0; eraseCnt <31; eraseCnt++) {
			if (eraseCnt < 5 && eraseCnt >= 0) {
				if((eraseCnt + 1) % 5 == 0) {
					System.out.println("지워진 줄 개수 : " + (eraseCnt + 1));
					assertEquals(interval.interval(easyRate, normalRate), easyRate - normalRate);
					assertEquals(interval.interval(normalRate, hardRate), normalRate - hardRate);
					System.out.println("easy 속도 : " + String.format("%.6f",easyRate) +
							"\tnormal 속도 : " + String.format("%.6f",normalRate)+ "\thard 속도 : " + String.format("%.6f",hardRate));
					easyRate *= (1 - 0.1) + (0.1 * 0.2);
					normalRate *= (1 - 0.1);
					hardRate *= (1 - 0.1) - (0.1 * 0.2);
				}
	        }
			else if (eraseCnt < 10 && eraseCnt >= 5) {
				if((eraseCnt + 1) % 5 == 0) {
					System.out.println("지워진 줄 개수 : " + (eraseCnt + 1));
					assertEquals(interval.interval(easyRate, normalRate), easyRate - normalRate);
					assertEquals(interval.interval(normalRate, hardRate), normalRate - hardRate);
					System.out.println("easy 속도 : " + String.format("%.6f",easyRate) +
							"\tnormal 속도 : " + String.format("%.6f",normalRate)+ "\thard 속도 : " + String.format("%.6f",hardRate));
					easyRate *= (1 - 0.1) + (0.1 * 0.2);
					normalRate *= (1 - 0.1);
					hardRate *= (1 - 0.1) - (0.1 * 0.2);
				}
	        }
			else if (eraseCnt < 15 && eraseCnt >= 10) {
				if((eraseCnt + 1) % 5 == 0) {
					System.out.println("지워진 줄 개수 : " + (eraseCnt + 1));
					assertEquals(interval.interval(easyRate, normalRate), easyRate - normalRate);
					assertEquals(interval.interval(normalRate, hardRate), normalRate - hardRate);
					System.out.println("easy 속도 : " + String.format("%.6f",easyRate) +
							"\tnormal 속도 : " + String.format("%.6f",normalRate)+ "\thard 속도 : " + String.format("%.6f",hardRate));
					easyRate *= (1 - 0.1) + (0.1 * 0.2);
					normalRate *= (1 - 0.1);
					hardRate *= (1 - 0.1) - (0.1 * 0.2);
				}
	        }
			else if (eraseCnt < 20 && eraseCnt >= 15) {
				if((eraseCnt + 1) % 5 == 0) {
					System.out.println("지워진 줄 개수 : " + (eraseCnt + 1));
					assertEquals(interval.interval(easyRate, normalRate), easyRate - normalRate);
					assertEquals(interval.interval(normalRate, hardRate), normalRate - hardRate);
					System.out.println("easy 속도 : " + String.format("%.6f",easyRate) +
							"\tnormal 속도 : " + String.format("%.6f",normalRate)+ "\thard 속도 : " + String.format("%.6f",hardRate));
					easyRate *= (1 - 0.1) + (0.1 * 0.2);
					normalRate *= (1 - 0.1);
					hardRate *= (1 - 0.1) - (0.1 * 0.2);
				}
	        }
			else if (eraseCnt < 25 && eraseCnt >= 20) {
				if((eraseCnt + 1) % 5 == 0) {
					System.out.println("지워진 줄 개수 : " + (eraseCnt + 1));
					assertEquals(interval.interval(easyRate, normalRate), easyRate - normalRate);
					assertEquals(interval.interval(normalRate, hardRate), normalRate - hardRate);
					System.out.println("easy 속도 : " + String.format("%.6f",easyRate) +
							"\tnormal 속도 : " + String.format("%.6f",normalRate)+ "\thard 속도 : " + String.format("%.6f",hardRate));
					easyRate *= (1 - 0.1) + (0.1 * 0.2);
					normalRate *= (1 - 0.1);
					hardRate *= (1 - 0.1) - (0.1 * 0.2);
				}
	        }
			else if (eraseCnt < 30 && eraseCnt >= 25) {
				if((eraseCnt + 1) % 5 == 0) {
					System.out.println("지워진 줄 개수 : " + (eraseCnt + 1));
					assertEquals(interval.interval(easyRate, normalRate), easyRate - normalRate);
					assertEquals(interval.interval(normalRate, hardRate), normalRate - hardRate);
					System.out.println("easy 속도 : " + String.format("%.6f",easyRate) +
							"\tnormal 속도 : " + String.format("%.6f",normalRate)+ "\thard 속도 : " + String.format("%.6f",hardRate));
					easyRate *= (1 - 0.1) + (0.1 * 0.2);
					normalRate *= (1 - 0.1);
					hardRate *= (1 - 0.1) - (0.1 * 0.2);
				}
	        }
			else if (eraseCnt >= 30) {
				if((eraseCnt + 1) % 5 == 0) {
					System.out.println("지워진 줄 개수 : " + (eraseCnt + 1));
					assertEquals(interval.interval(easyRate, normalRate), easyRate - normalRate);
					assertEquals(interval.interval(normalRate, hardRate), normalRate - hardRate);
					System.out.println("easy 속도 : " + String.format("%.6f",easyRate) +
							"\tnormal 속도 : " + String.format("%.6f",normalRate)+ "\thard 속도 : " + String.format("%.6f",hardRate));
					easyRate *= (1 - 0.1) + (0.1 * 0.2);
					normalRate *= (1 - 0.1);
					hardRate *= (1 - 0.1) - (0.1 * 0.2);
				}
			}
		}
		
		
		
		
		
	}

}
