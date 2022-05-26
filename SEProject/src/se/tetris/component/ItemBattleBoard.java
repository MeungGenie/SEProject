package se.tetris.component;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import se.tetris.setting.*;

public class ItemBattleBoard extends JFrame {

    private static InnerItemBoard player1;
    private static InnerItemBoard player2;
    private JPanel panel;
    private KeyListener playerKeyListener;
    
    final SettingValues setting = SettingValues.getInstance();

    public ItemBattleBoard() {
        super("SeoulTech SE Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        player1 = new InnerItemBoard();
        player2 = new InnerItemBoard();

        player1.setName("Player1");
        player2.setName("Player2");

        panel = new JPanel();

        panel.add(player1);
        panel.add(player2);

        add(panel);

        playerKeyListener = new PlayerKeyListener();
        addKeyListener(playerKeyListener);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);
        requestFocus();
    }


    public class PlayerKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_S:
                    player1.moveDown();
                    player1.drawBoard();
                    break;
                case KeyEvent.VK_D:
                	if(player1.notMove == false) {
                		player1.moveRight();
                	}
                    player1.drawBoard();
                    break;
                case KeyEvent.VK_A:
                	if(player1.notMove == false) {
                		player1.moveLeft();
                	}
                    player1.drawBoard();
                    break;
                case KeyEvent.VK_W:
                	if(player1.blockFix == false) {
                		player1.blockRotate();
                	}
                    player1.drawBoard();
                    break;
                case KeyEvent.VK_TAB:
                	while(true){
						player1.eraseCurr();
						if (player1.itemType == 12) {
							for (int i = player1.y; i < 20; i++) {
								for (int j = player1.x; j < player1.x + player1.curr.width(); j++) {
									player1.board[i][j] = 0;
								}
							}
							player1.x = 3;
							player1.y = 0;
							player1.curr = player1.next;
							player1.eraseNext();
							player1.next = player1.getRandomBlock(setting.modeChoose);
							player1.placeNext();
							player1.drawNext();
							player1.placeBlock();
							player1.drawBoard();
							player1.blockFix = false;
							player1.notMove = false;
							player1.itemType = 0;
							player1.itemFlag = false;
							break;
						}
						else {
							if (player1.collisionBottom()) {
								player1.collisionOccur();
								if (player1.whoAttacked) {
	                                player1.attackedFunction();
	                            }
								player1.lineRemove();
								if (player1.itemFlag == true) {
									player1.itemSet();
									player1.itemDrop = true;
								}
								if (!player1.isGameOver()) {
									player1.placeBlock();
									player1.drawBoard();
								}
								break;
							}
							else {
								player1.y++;
							}
							player1.lineRemove();
							player1.placeBlock();
							player1.drawBoard();
						}
					}
					break;
                case KeyEvent.VK_DOWN:
                    player2.moveDown();
                    player2.drawBoard();
                    break;
                case KeyEvent.VK_RIGHT:
                	if(player2.notMove == false) {
                		player2.moveRight();
                	}
                    player2.drawBoard();
                    break;
                case KeyEvent.VK_LEFT:
                	if(player2.notMove == false) {
                		player2.moveLeft();
                	}
                    player2.drawBoard();
                    break;
                case KeyEvent.VK_UP:
                	if(player2.blockFix == false) {
                		player2.blockRotate();
                	}
                    player2.drawBoard();
                    break;
                case KeyEvent.VK_ENTER:
                	while(true){
						player2.eraseCurr();
						if (player2.itemType == 12) {
							for (int i = player2.y; i < 20; i++) {
								for (int j = player2.x; j < player2.x + player2.curr.width(); j++) {
									player2.board[i][j] = 0;
								}
							}
							player2.x = 3;
							player2.y = 0;
							player2.curr = player2.next;
							player2.eraseNext();
							player2.next = player2.getRandomBlock(setting.modeChoose);
							player2.placeNext();
							player2.drawNext();
							player2.placeBlock();
							player2.drawBoard();
							player2.blockFix = false;
							player2.notMove = false;
							player2.itemType = 0;
							player2.itemFlag = false;
							break;
						}
						else {
							if (player2.collisionBottom()) {
								player2.collisionOccur();
								if (player2.whoAttacked) {
	                                player2.attackedFunction();
	                            }
								player2.lineRemove();
								if (player2.itemFlag == true) {
									player2.itemSet();
									player2.itemDrop = true;
								}
								if (!player2.isGameOver()) {
									player2.placeBlock();
									player2.drawBoard();
								}
								break;
							}
							else {
								player2.y++;
							}
							player2.lineRemove();
							player2.placeBlock();
							player2.drawBoard();
						}
					}
					break;
                case KeyEvent.VK_ESCAPE:
                    player1.timer.stop();
                    player2.timer.stop();
                    int choice = JOptionPane.showConfirmDialog(null, "������ �����Ͻðڽ��ϱ�?", "���� ����", JOptionPane.YES_NO_CANCEL_OPTION);
                    switch(choice) {
                        case 0:
                            int confirm = JOptionPane.showConfirmDialog(null, "���� �����Ͻðڽ��ϱ�?", "Ȯ��", JOptionPane.YES_NO_OPTION);
                            if (confirm == 0) {
                                dispose();
                            }
                            else {
                                player1.timer.restart();
                                player2.timer.restart();
                            }
                            break;
                        case 1:
                            player1.timer.restart();
                            player2.timer.restart();
                            break;
                        case 2:
                            player1.timer.restart();
                            player2.timer.restart();
                    }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public static void gameStop() {
        Timer player1Timer = player1.getTimer();
        Timer player2Timer = player2.getTimer();
        player1Timer.stop();
        player2Timer.stop();
    }
    
    public static void gameReset() {
    	player1.reset();
    	player2.reset();
    	Timer player1Timer = player1.getTimer();
        Timer player2Timer = player2.getTimer();
        player1Timer.restart();
        player2Timer.restart();
    }
    
    public static void gameClose() {
    	System.exit(0);
    }
    
    public static void placeAttack(ArrayList<Integer> attack) {
        if (player1.whoIs == true) {
            int[][] player1AttackBoard = player1.getAttackBoard();
            if (player1.alreadyAttacked == true) {
                System.out.println("counted");
                player1.attackLineCount = lineCounter(player1AttackBoard);
                System.out.println(player2.attackLineCount);
                for (int i = attack.size(); i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        player1AttackBoard[i - attack.size()][j] = player1AttackBoard[i][j];
                    }
                }
                for (int i = 0; i < attack.size(); i++) {
                    player1.attackLine.add(attack.get(i) - player1.lastY);
                }
                //int firstY = player1.attackY - player1.attackLineCount;
                player1.attackY -= attack.size();
                int firstY = player1.attackY;
                for (int i = 9; i > 9 - attack.size(); i--) {
                    for (int j = 0; j < player1AttackBoard[0].length; j++) {
                        player1AttackBoard[i][j] = 1;
                    }
                }

            } else {
                for (int i = 0; i < attack.size(); i++) {
                    player1.attackLine.add(attack.get(i) - player1.lastY);
                }
                int firstY = player1.attackY;
                for (int i = firstY; i > firstY - attack.size(); i--, player1.attackY--) {
                    for (int j = 0; j < player1AttackBoard[0].length; j++) {
                        player1AttackBoard[i][j] = 1;
                    }
                }
                player1.alreadyAttacked = true;
            }
        }
        if (player2.whoIs == true) {
            int[][] player2AttackBoard = player2.getAttackBoard();
            if (player2.alreadyAttacked == true) {
                System.out.println("counted");
                player2.attackLineCount = lineCounter(player2AttackBoard);
                System.out.println(player2.attackLineCount);
                for (int i = attack.size(); i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        player2AttackBoard[i - attack.size()][j] = player2AttackBoard[i][j];
                    }
                }
                for (int i = 0; i < attack.size(); i++) {
                    player2.attackLine.add(attack.get(i) - player2.lastY);
                }
                //int firstY = player2.attackY - player2.attackLineCount;
                player2.attackY -= attack.size();
                int firstY = player2.attackY;
                for (int i = 9; i > 9 - attack.size(); i--) {
                    for (int j = 0; j < player2AttackBoard[0].length; j++) {
                        player2AttackBoard[i][j] = 1;
                    }
                }
            } else {
                for (int i = 0; i < attack.size(); i++) {
                    player2.attackLine.add(attack.get(i) - player2.lastY);
                }
                int firstY = player2.attackY;
                for (int i = firstY; i > firstY - attack.size(); i--, player2.attackY--) {
                    for (int j = 0; j < player2AttackBoard[0].length; j++) {
                        player2AttackBoard[i][j] = 1;
                    }
                }
                player2.alreadyAttacked = true;
            }
        }
        //drawAttack();
    }

    public static void drawAttack() {
        if (player1.whoIs == true) {
            player1.sbByAttack = new StringBuffer();
            int[][] player1AttackBoard = player1.getAttackBoard();
            for (int i = 0; i < player1AttackBoard.length; i++) {
                for (int j = 0; j < player1AttackBoard[i].length; j++) {
                    if (player1AttackBoard[i][j] == 1) {
                        player1.sbByAttack.append("��");
                    }
                    else {
                        player1.sbByAttack.append(" ");
                    }
                }
                player1.sbByAttack.append("\n");
            }
            player2.attackArea.setText(player1.sbByAttack.toString());
            player2.attackDoc.setParagraphAttributes(0, player2.attackDoc.getLength(), player2.stylesetAk, false);
            player2.whoAttacked = true;
            lineCountByPlayer();
            player1.whoIs = false;
        } else if (player2.whoIs == true) {
            player2.sbByAttack = new StringBuffer();
            int[][] player2AttackBoard = player2.getAttackBoard();
            for (int i = 0; i < player2AttackBoard.length; i++) {
                for (int j = 0; j < player2AttackBoard[i].length; j++) {
                    if (player2AttackBoard[i][j] == 1) {
                        player2.sbByAttack.append("��");
                    } else {
                        player2.sbByAttack.append(" ");
                    }
                }
                player2.sbByAttack.append("\n");
            }
            player1.attackArea.setText(player2.sbByAttack.toString());
            player1.attackDoc.setParagraphAttributes(0, player1.attackDoc.getLength(), player1.stylesetAk, false);
            player1.whoAttacked = true;
            lineCountByPlayer();
            player2.whoIs = false;
        }
    }

    public static int lineCountByPlayer() {
        if (player1.whoIs == true) {
            int[][] player1AttackBoard = player1.getAttackBoard();
            player2.attackLineCount = lineCounter(player1AttackBoard);
            return player2.attackLineCount;
        }
        if (player2.whoIs == true) {
            int[][] player2AttackBoard = player2.getAttackBoard();
            player1.attackLineCount = lineCounter(player2AttackBoard);
            return player1.attackLineCount;
        }
        return 0;
    }

    public static void forAttack() {
        if (player1.whoAttacked) {
            player2.alreadyAttacked = false;
            int[][] player2AttackBoard = player2.getAttackBoard();
            int[][] player1Board = player1.getBoard();
            player1.attackLineCount = lineCounter(player2AttackBoard);
            for (int a = player1.attackLineCount; a < HEIGHT; a++) {
                for (int b = 0; b < WIDTH; b++) {
                    player1Board[a - player1.attackLineCount][b] = player1Board[a][b];
                }
            }
            for (int a = 0; a < player1.attackLineCount; a++) {
                for (int b = 0; b < player2AttackBoard[0].length; b++) {
                    player1Board[a + player1.HEIGHT - player1.attackLineCount][b] = player2AttackBoard[a + player1.HEIGHT - player1.attackLineCount - 10][b] * 13;
                }
            }
            player1.drawBoard();
            player2.sbByAttack.delete(0, player2.sbByAttack.toString().length());
            for (int a = 0; a < 10; a++) {
                for (int b = 0; b < 10; b++) {
                    player2AttackBoard[a][b] = 0;
                }
            }
            player1.attackArea.setText(player2.sbByAttack.toString());
            player1.attackDoc.setParagraphAttributes(0, player1.attackDoc.getLength(), player1.stylesetAk, false);
            player1.whoAttacked = false;
            //Timer player1Timer = player1.getTimer();
            //player1Timer.setDelay(10000);
        } else if (player2.whoAttacked) {
            player1.alreadyAttacked = false;
            int[][] player1AttackBoard = player1.getAttackBoard();
            int[][] player2Board = player2.getBoard();
            player2.attackLineCount = lineCounter(player1AttackBoard);
            for (int a = player2.attackLineCount; a < HEIGHT; a++) {
                for (int b = 0; b < WIDTH; b++) {
                    player2Board[a - player2.attackLineCount][b] = player2Board[a][b];
                }
            }
            for (int a = 0; a < player2.attackLineCount; a++) {
                for (int b = 0; b < player1AttackBoard[0].length; b++) {
                    player2Board[a + player2.HEIGHT - player2.attackLineCount][b] = player1AttackBoard[a + player2.HEIGHT - player2.attackLineCount - 10][b] * 13;
                }
            }
            player2.drawBoard();
            player1.sbByAttack.delete(0, player1.sbByAttack.toString().length());
            for (int a = 0; a < 10; a++) {
                for (int b = 0; b < 10; b++) {
                    player1AttackBoard[a][b] = 0;
                }
            }
            player2.attackArea.setText(player1.sbByAttack.toString());
            player2.attackDoc.setParagraphAttributes(0, player2.attackDoc.getLength(), player2.stylesetAk, false);
            player2.whoAttacked = false;
        }

    }

    public static int lineCounter(int[][] playerAttackBoard) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (playerAttackBoard[i][j] == 1) {
                    return 10 - i;
                }
            }
        }
        return 0;
    }
}