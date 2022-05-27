package se.tetris.component;

import javax.swing.*;
import javax.swing.border.LineBorder;

import se.tetris.component.Start.BackPanel;
import se.tetris.component.Start.SButton;
import se.tetris.data.DBCalls;
import se.tetris.setting.SettingCode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.ConstructorProperties;

public class StartBattle extends JFrame {

    int KeyCount = 0;
    int KeyFoucus = 0;

    DBCalls dataCalls = new DBCalls();

    int Window = dataCalls.getWindowSetting();
    SettingCode setting = new SettingCode();


    ImageIcon backImg = new ImageIcon("./img/bg_one.png");
    Image rebackFill = backImg.getImage().getScaledInstance(400, 600, Image.SCALE_SMOOTH);

    ImageIcon backImg2 = new ImageIcon("./img/bg_two.png");
    Image rebackFill2 = backImg.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);

    ImageIcon backImg3 = new ImageIcon("./img/tettrisbg.png");
    Image rebackFill3 = backImg.getImage().getScaledInstance(SettingCode.screenWidth, SettingCode.screenHeight,
            Image.SCALE_SMOOTH);

    int backX = 0;

    class BackPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (Window == 0) {
                g.drawImage(rebackFill, 0, 0, this);
            } else if (Window == 1) {
                g.drawImage(rebackFill2, 0, 0, this);
            } else {
                g.drawImage(rebackFill3, 0, 0, this);
            }

        }
    }

    class SButton extends JButton {

        Color Impact = new Color(106, 215, 255);
        Color Normal = new Color(0, 0, 0);

        public SButton() {
            super();
            Default();
        }

        @ConstructorProperties({ "text" })
        public SButton(String text) {
            super(text, null);
            Default();
        }

        public void Active() {
            setBackground(Impact);
            setBorder(new LineBorder(Impact));
            setForeground(Color.BLACK);
        }

        public void Default() {
            setBackground(Color.BLACK);
            setBorder(new LineBorder(Impact));

            setForeground(Impact);

            if (Window == 0) {
                setPreferredSize(new Dimension(200, 35));
                setFont(new Font("고딕", Font.CENTER_BASELINE, 12));
            } else if (Window == 1) {
                setPreferredSize(new Dimension(220, 45));
                setFont(new Font("고딕", Font.CENTER_BASELINE, 14));
            } else {
                setPreferredSize(new Dimension(300, 50));
                setFont(new Font("고딕", Font.CENTER_BASELINE, 16));
            }

        }
    }

    public StartBattle(){
        setTitle("SeoulTech SE Tettris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane layerPane = new JLayeredPane();

        BackPanel backPanel = new BackPanel();

        ImageIcon titleImg = new ImageIcon("./img/btitle.png");

        Image retitleFill = null;

        if (Window == 0) {
            layerPane.setSize(400, 600);
            backPanel.setSize(400, 600);
            retitleFill = titleImg.getImage().getScaledInstance(160, 280, Image.SCALE_SMOOTH);
        } else if (Window == 1) {
            layerPane.setSize(800, 800);
            backPanel.setSize(800, 800);
            titleImg = new ImageIcon("./img/btitle.png");
            retitleFill = titleImg.getImage().getScaledInstance(320, 300, Image.SCALE_SMOOTH);
        } else {
            layerPane.setSize(SettingCode.screenWidth, SettingCode.screenHeight);
            backPanel.setSize(SettingCode.screenWidth, SettingCode.screenHeight);
            titleImg = new ImageIcon("./img/btitle3.png");
            retitleFill = titleImg.getImage().getScaledInstance(480, 450, Image.SCALE_SMOOTH);
        }

        layerPane.setLayout(null);

        ImageIcon retitleImg = new ImageIcon(retitleFill);
        JLabel title = new JLabel(retitleImg);

        JPanel buttonPanel = new JPanel();
        JPanel innerPane = new JPanel();

        layerPane.add(backPanel);

        backPanel.setLayout(new BorderLayout());

        backPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));

        backPanel.add(title, BorderLayout.NORTH);

        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(new Color(0, 0, 0, 0));

        innerPane.setOpaque(true);
        innerPane.setBackground(new Color(0, 0, 0, 0));

        GridLayout grid = new GridLayout(3, 1, 0, 10);

        buttonPanel.setSize(380, 400);

        buttonPanel.add(innerPane);

        innerPane.setLayout(grid);

        SButton general = new SButton("기본 모드");
        SButton item = new SButton("아이템 모드");
        SButton timeLimit = new SButton("시간 제한 모드");

        innerPane.add(general);
        innerPane.add(item);
        innerPane.add(timeLimit);

        backPanel.add(buttonPanel, BorderLayout.CENTER);

        setLayout(null);

        add(layerPane);


        general.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                BattleBoard battle = new BattleBoard();
                if (Window == 0) {
                    battle.setSize(800, 650);
                } else if (Window == 1) {
                    battle.setSize(1200, 800);
                } else {
                    battle.setSize(SettingCode.screenWidth, SettingCode.screenHeight);
                }
                battle.setVisible(true);
            }
        });

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ItemBattleBoard itembattle = new ItemBattleBoard();
                if (Window == 0) {
                    itembattle.setSize(800, 650);
                } else if (Window == 1) {
                    itembattle.setSize(1200, 800);
                } else {
                    itembattle.setSize(SettingCode.screenWidth, SettingCode.screenHeight);
                }
                itembattle.setVisible(true);
            }
        });

        timeLimit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                TimeBattleBoard timebattle = new TimeBattleBoard();
                if (Window == 0) {
                    timebattle.setSize(800, 600);
                } else if (Window == 1) {
                    timebattle.setSize(1200, 800);
                } else {
                    timebattle.setSize(SettingCode.screenWidth, SettingCode.screenHeight);
                }
                timebattle.setVisible(true);
            }
        });

        innerPane.addKeyListener(new KeyListener() {

            public void allBtnNotActive() {
                general.Default();
                item.Default();
                timeLimit.Default();
            }


            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(KeyCount == 0) {
                    allBtnNotActive();
                    general.Active();
                    KeyFoucus = 0;
                }

                if(e.getKeyCode() == 40) {
                    if(KeyFoucus == 0 && KeyCount>0) {
                        allBtnNotActive();
                        item.Active();
                        KeyFoucus = 1;
                    }else if(KeyFoucus ==1) {
                        timeLimit.setBackground(new Color(106,215,255));
                        allBtnNotActive();
                        timeLimit.Active();
                        KeyFoucus = 2;
                    }
                    KeyCount++;
                }

                if(e.getKeyCode() == 38) {
                    if(KeyFoucus == 0) {
                        allBtnNotActive();
                        general.Active();
                        KeyFoucus = 0;
                    }else if(KeyFoucus ==1) {
                        allBtnNotActive();
                        general.Active();
                        KeyFoucus = 0;
                    }else if(KeyFoucus ==2) {
                        allBtnNotActive();
                        item.Active();
                        KeyFoucus = 1;
                    }else if(KeyFoucus ==3) {
                        allBtnNotActive();
                        timeLimit.Active();
                        KeyFoucus = 2;
                    }
                    KeyCount++;
                }

                if(e.getKeyCode() == 10) {
                    if(KeyFoucus == 0) {
                        general.doClick();
                    }else if(KeyFoucus ==1) {
                        item.doClick();
                    }else if(KeyFoucus ==2) {
                        timeLimit.doClick();
                    }
                }

            }
        });

        innerPane.setFocusable(true);

    }
}