import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

enum Mode {
	TITLE,
	FIELD,
	BAG, STATUS, BOT, ITEM, EQUIP, MAP,
	BATTLE,
	DIALOG
}
enum Map {
	HOME,
	TOWN,
	DOWNTOWN
}
public class MainFrame extends JFrame implements KeyListener {
	private FieldPanel fieldpanel;
	private BattlePanel battlepanel;
	private BagPanel bagpanel;
	private DialogPanel dialogpanel;

	private REvent revent;
	private RSound bgm;
	private RSound home_bgm;
	private RSound town_bgm;
	private RSound downtown_bgm;

	private int keyPressed;
	private int keyReleased;
	private int keyTyped;

	private MasterPlayer player;
	private TestPlayer tester;

	public static void main(String[] argv) {
		MainFrame mainframe = new MainFrame();
	}

	public MainFrame() {
		super("Reevolution");

		setResizable(false);
		setSize(new Dimension(getToolkit().getScreenSize().width, getToolkit().getScreenSize().height));
		// setSize(new Dimension(1200, getToolkit().getScreenSize().height));
		//setSize(new Dimension(1200, 1000));
		// setLocation(100, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		getContentPane().setBackground(Color.black);
		getContentPane().setLayout(null);

		add(dialogpanel = new DialogPanel((getWidth()-800)/2, getHeight() * 0.65, 800, 180));
		add(bagpanel = new BagPanel(getWidth(), getHeight() * 0.9));
		add(battlepanel = new BattlePanel(70, (int)((getToolkit().getScreenSize().height - 700) / 2), 1060, 700));
		add(fieldpanel = new FieldPanel(getWidth(), getHeight() - 28, "image/Title.jpg"));

		player = new MasterPlayer(fieldpanel.blockWidth, fieldpanel.blockHeight);
		tester = new TestPlayer();

		addKeyListener(this);

		revent = new REvent();
		revent.start();

		bgm = new RSound("sound/background.wav");
		// home_bgm = new RSound("sound/background.wav");
		// town_bgm = new RSound("sound/BGM/GreatHonor.wav");
		// downtown_bgm = new RSound("sound/BGM/WindOfTragedy.wav");
		bgm.start();

		// pack();
		setVisible(true);
		// setExtendedState(MAXIMIZED_BOTH);

		Thread render = new Thread(new Runnable() {
			public void run() {
				while(true) {
					repaint();

					try {Thread.sleep(5);} catch (InterruptedException ie) {ie.printStackTrace();}
				}
			}
		});
		render.start();
	}

	public void keyPressed(KeyEvent k) {
		keyPressed = k.getKeyCode();
 	}
	public void keyReleased(KeyEvent k) {}
	public void keyTyped(KeyEvent k) {}

	class REvent extends Thread implements Runnable {
		private Mode mode;
		private Map map;
		/** true for player's turn */
		private boolean turn;
		private Point point;
		private int chapter;

		public REvent() {
			mode = Mode.TITLE;
			map = Map.HOME;
			point = player.point;
		}

		public void run() {
			while (true) {
				switch (mode) {
					case TITLE:
						// System.out.print("");
						if (keyPressed == KeyEvent.VK_ENTER) {
							fieldpanel.changeImage(0, 0, "image/Home", "image/Home.png", player, 4, 3);
							bgm.done();
							home_bgm = new RSound("sound/background.wav");
							home_bgm.start();
							mode = Mode.FIELD;
							map = Map.HOME;
							keyPressed = 0;
						}
					break;
					case FIELD:
						switch (map) {
							case HOME:
								if (point.x == 7 && point.y == 3) {
									talkMom();
								}
								else if (point.x == 7 && point.y == 6) {
									if (chapter == 0) {
										chapter0(0);
									}
									if (keyPressed == KeyEvent.VK_S) {
										if (player.lookAt() == RPlayer.DOWN) {
											fieldpanel.changeImage(0, 0, "image/Town", "image/Town.png", player, 3, 5);
											home_bgm.done();
											town_bgm = new RSound("sound/BGM/GreatHonor.wav");
											town_bgm.start();
											map = Map.TOWN;
										}
									}
								}
								else if (point.x == 8 && point.y == 6) {
									if (chapter == 0) {
										chapter0(1);
									}
									if (keyPressed == KeyEvent.VK_S) {
										if (player.lookAt() == RPlayer.DOWN) {
											fieldpanel.changeImage(0, 0, "image/Town", "image/Town.png", player, 3, 5);
											home_bgm.done();
											town_bgm = new RSound("sound/BGM/GreatHonor.wav");
											town_bgm.start();
											map = Map.TOWN;
										}
									}
								}
							break;
							case TOWN:
								if ( point.x == 19 && point.y == 2 || point.x == 20 && point.y == 2 ){
									if ( chapter == 4 ){
										chapter4();
									}
								}
								if (point.x == 18 && point.y == 17) {
									if (chapter == 1) {
										chapter1();
									}
								}
								if (point.x == 18 && point.y == 19) {
									if (keyPressed == KeyEvent.VK_S) {
										if (player.lookAt() == RPlayer.DOWN) {
											fieldpanel.changeImage(0, 0, "image/DownTown", "image/DownTown.png", player, 12, 0);
											town_bgm.done();
											downtown_bgm = new RSound("sound/BGM/WindOfTragedy.wav");
											downtown_bgm.start();
											map = Map.DOWNTOWN;
											keyPressed = 0;
										}
									}
								}
								if (point.x == 13 && point.y == 6 || point.x == 8 && point.y == 18 || point.x == 22 && point.y == 13) {
									talkSolider();
								}
								if ( point.x == 4 && point.y == 13 ){
									talkSolider2();
								}
								if ( point.x == 18 && point.y == 17 ){
									talkJohn();
								}
								if (point.x == 3 && point.y == 5) {
									if (keyPressed == KeyEvent.VK_W) {
										if (player.lookAt() == RPlayer.UP) {
											fieldpanel.changeImage(0, 0, "image/Home", "image/Home.png", player, 7, 6);
											town_bgm.done();
											home_bgm = new RSound("sound/background.wav");
											home_bgm.start();
											map = Map.HOME;
											keyPressed = 0;
										}
									}
								}
							break;
							case DOWNTOWN:
								if ( chapter == 2 && point.y >= 11 ){
									chapter2();
								}
								if ( chapter == 3 && point.x == 22 && point.y == 34 ){
									chapter3();
								}
								if ( point.x == 12 && point.y == 0 ){
									if (keyPressed == KeyEvent.VK_W) {
										if (player.lookAt() == RPlayer.UP) {
											fieldpanel.changeImage(0, 0, "image/Town", "image/Town.png", player, 18, 19);
											downtown_bgm.done();
											town_bgm = new RSound("sound/BGM/GreatHonor.wav");
											town_bgm.start();
											map = Map.TOWN;
											keyPressed = 0;
										}
									}
								}
							break;
						}

						if (keyPressed == KeyEvent.VK_W) {
							if (player.lookAt() == RPlayer.UP)
								fieldpanel.moveUp(player);
							else
								player.look(RPlayer.UP);

							keyPressed = 0;
						}
						else if (keyPressed == KeyEvent.VK_S) {
							if (player.lookAt() == RPlayer.DOWN)
								fieldpanel.moveDown(player);
							else
								player.look(RPlayer.DOWN);

							keyPressed = 0;
						}
						else if (keyPressed == KeyEvent.VK_A) {
							if (player.lookAt() == RPlayer.LEFT)
								fieldpanel.moveLeft(player);
							else
								player.look(RPlayer.LEFT);

							keyPressed = 0;
						}
						else if (keyPressed == KeyEvent.VK_D) {
							if (player.lookAt() == RPlayer.RIGHT)
								fieldpanel.moveRight(player);
							else
								player.look(RPlayer.RIGHT);

							keyPressed = 0;
						}
						else if (keyPressed == KeyEvent.VK_B) {
                            bagpanel.setVisible(true);

							mode = Mode.BAG;
							keyPressed = 0;
						}
						/** Testing BattlePanel*/
						else if (keyPressed == KeyEvent.VK_SPACE) {
							battlepanel.begin(player, tester);

							mode = Mode.BATTLE;
							turn = true;
							keyPressed = 0;
						}
					try {Thread.sleep(300);} catch (InterruptedException ie) {ie.printStackTrace();}
					break;
					case BAG:
						switch (keyPressed) {
							case KeyEvent.VK_W:
								bagpanel.move(false);

								keyPressed = 0;
							break;
							case KeyEvent.VK_S:
								bagpanel.move(true);

								keyPressed = 0;
							break;
							case KeyEvent.VK_ENTER:
								bagpanel.getstatuspanel().setmaster(player);
								mode = bagpanel.enter(mode);
								keyPressed = 0;
							break;
							case KeyEvent.VK_B:
								bagpanel.setVisible(false);

								mode = Mode.FIELD;
								keyPressed = 0;
							break;
						}
					try {Thread.sleep(100);} catch (InterruptedException ie) {ie.printStackTrace();}
					break;
					case STATUS:
							bagpanel.getstatuspanel().setmaster(player);  
						if (keyPressed == KeyEvent.VK_ESCAPE) {
							bagpanel.getstatuspanel().setVisible(false);
							bagpanel.getmenupanel().setVisible(true);
							mode = Mode.BAG;
						}
					try {Thread.sleep(100);} catch (InterruptedException ie) {ie.printStackTrace();}
					break;
					case BOT:
			             if(bagpanel.getbotpanel().isVisible());   //modifyed
	                    else
	                    {
							bagpanel.getbotpanel().updatebot(player);
							bagpanel.getbotpanel().setVisible(true);
						}
						switch (keyPressed) {
							case KeyEvent.VK_A:
								bagpanel.getbotpanel().changebot(false);
								keyPressed = 0;
							break;
							case KeyEvent.VK_D:
								bagpanel.getbotpanel().changebot(true);
								keyPressed = 0;
							break;
							case KeyEvent.VK_ESCAPE:
								bagpanel.getbotpanel().removeallbot(player);
								bagpanel.getbotpanel().setVisible(false);
								bagpanel.getmenupanel().setVisible(true);
								mode = Mode.BAG;
							break;
								case KeyEvent.VK_ENTER:                 //add
								bagpanel.getbotpanel().setfirstbot(player);
								keyPressed=0;
							break;
						}
					try {Thread.sleep(100);} catch (InterruptedException ie) {ie.printStackTrace();}
					break;
					case ITEM:
			             if(bagpanel.getitempanel().isVisible());   //modifyed
	                    else
	                    {
							bagpanel.getitempanel().updatebot(player);
							bagpanel.getitempanel().updateitem(player);
							bagpanel.getitempanel().setVisible(true);
						}
						switch (keyPressed) {
							case KeyEvent.VK_W:
								bagpanel.getitempanel().changeitem(player,false);
								keyPressed = 0;
							break;
							case KeyEvent.VK_S:
								bagpanel.getitempanel().changeitem(player,true);
								keyPressed = 0;
							break;
							case KeyEvent.VK_A:
								bagpanel.getitempanel().changebot(false);
								keyPressed = 0;
							break;
							case KeyEvent.VK_D:
								bagpanel.getitempanel().changebot(true);
								keyPressed = 0;
							break;
							case KeyEvent.VK_ENTER:
								bagpanel.getitempanel().useitem(player);
								keyPressed = 0;
							break;
							case KeyEvent.VK_ESCAPE:
								bagpanel.getitempanel().removeallbot(player);
								bagpanel.getitempanel().removeallitem(player);
								bagpanel.getmenupanel().setVisible(true);
								bagpanel.getitempanel().setVisible(false);
								mode = Mode.BAG;
							break;
						}
					try {Thread.sleep(100);} catch (InterruptedException ie) {ie.printStackTrace();}
					break;
					case EQUIP:
						if (keyPressed == KeyEvent.VK_ESCAPE) {
							bagpanel.getequippanel().setVisible(false);
							bagpanel.getmenupanel().setVisible(true);
							mode = Mode.BAG;
						}
					try {Thread.sleep(100);} catch (InterruptedException ie) {ie.printStackTrace();}
					break;
					case MAP:
						if (keyPressed == KeyEvent.VK_ESCAPE) {
							bagpanel.getmappanel().setVisible(false);
							bagpanel.getmenupanel().setVisible(true);
							mode = Mode.BAG;
						}
					try {Thread.sleep(100);} catch (InterruptedException ie) {ie.printStackTrace();}
					break;
					case BATTLE:
						if (turn) {
							switch (keyPressed) {
								case KeyEvent.VK_W:
									battlepanel.moveUp();

									keyPressed = 0;
								break;
								case KeyEvent.VK_S:
									battlepanel.moveDown();

									keyPressed = 0;
								break;
								case KeyEvent.VK_A:
									battlepanel.moveLeft();

									keyPressed = 0;
								break;
								case KeyEvent.VK_D:
									battlepanel.moveRight();

									keyPressed = 0;
								break;
								case KeyEvent.VK_ENTER:
									battlepanel.enter();

									keyPressed = 0;
								break;
							}
						}
						else {
						}
						if (keyPressed == KeyEvent.VK_SPACE) {
							battlepanel.end();

							mode = Mode.FIELD;
							keyPressed = 0;
						}
					try {Thread.sleep(100);} catch (InterruptedException ie) {ie.printStackTrace();}
					break;
				}
			}
		}

		public void chapter0(int mode){
			if (mode == 0) {
				fieldpanel.npc[0].look(RPlayer.DOWN);
				for (int i = 0; i < 3; i++) {
					fieldpanel.npc[0].move();
					try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
				}
				player.look(RPlayer.UP);
				try {Thread.sleep(100);} catch (InterruptedException ie) {ie.printStackTrace();}
				dialogpanel.changeImage("image/dialog/chapter0-0.png");
				try {Thread.sleep(4000);} catch (InterruptedException ie) {ie.printStackTrace();}
				dialogpanel.changeImage("image/dialog/chapter0-1.png");
				try {Thread.sleep(500);} catch (InterruptedException ie) {ie.printStackTrace();}
				fieldpanel.npc[0].look(RPlayer.UP);
				for (int i = 0; i < 3; i++) {
					fieldpanel.npc[0].move();
					try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
				}
				fieldpanel.npc[0].look(RPlayer.DOWN);
			}
			else {
				fieldpanel.npc[0].look(RPlayer.DOWN);
				for (int i = 0; i < 4; i++) {
					fieldpanel.npc[0].move();
					try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
				}
				fieldpanel.npc[0].look(RPlayer.RIGHT);
				player.look(RPlayer.LEFT);
				try {Thread.sleep(100);} catch (InterruptedException ie) {ie.printStackTrace();}
				dialogpanel.changeImage("image/dialog/chapter0-0.png");
				try {Thread.sleep(3000);} catch (InterruptedException ie) {ie.printStackTrace();}
				dialogpanel.changeImage("image/dialog/chapter0-1.png");
				try {Thread.sleep(500);} catch (InterruptedException ie) {ie.printStackTrace();}
				fieldpanel.npc[0].look(RPlayer.UP);
				for (int i = 0; i < 4; i++) {
					fieldpanel.npc[0].move();
					try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
				}
				fieldpanel.npc[0].look(RPlayer.DOWN);
			}
			chapter++;
		}
		public void chapter1(){
			player.look(RPlayer.RIGHT);
			try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
			dialogpanel.changeImage("image/dialog/chapter1-0.png");
			try {Thread.sleep(4000);} catch (InterruptedException ie) {ie.printStackTrace();}
			dialogpanel.changeImage("image/dialog/chapter1-1.png");

			// try {Thread.sleep(4000);} catch (InterruptedException ie) {ie.printStackTrace();}
			// dialogpanel.changeImage("image/dialog/chapter1-2.png");
			chapter++;
		}

		public void chapter2(){
			fieldpanel.npc[0].look(RPlayer.UP);
			for (int i = 0; i < 7; i++) {
				fieldpanel.npc[0].move();
				try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
			}
			fieldpanel.npc[0].look(RPlayer.LEFT);
			for (int i = 0; i < 7; i++) {
				fieldpanel.npc[0].move();
				try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
			}
			fieldpanel.npc[0].look(RPlayer.UP);
			for (int i = 0; i < 2; i++) {
				fieldpanel.npc[0].move();
				try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
			}
			player.look(RPlayer.DOWN);
			try {Thread.sleep(100);} catch (InterruptedException ie) {ie.printStackTrace();}
			dialogpanel.changeImage("image/dialog/chapter2-0.png");
			try {Thread.sleep(4000);} catch (InterruptedException ie) {ie.printStackTrace();}
			dialogpanel.changeImage("image/dialog/chapter2-1.png");
			try {Thread.sleep(4000);} catch (InterruptedException ie) {ie.printStackTrace();}
			dialogpanel.changeImage("image/dialog/chapter2-2.png");
			try {Thread.sleep(3000);} catch (InterruptedException ie) {ie.printStackTrace();}

			fieldpanel.npc[0].look(RPlayer.LEFT);
			fieldpanel.npc[0].move();
			try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
			fieldpanel.npc[0].look(RPlayer.UP);
			for (int i = 0; i < 4; i++) {
				fieldpanel.npc[0].move();
				try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
			}
			fieldpanel.npc[0].look(RPlayer.RIGHT);
			fieldpanel.npc[0].move();
			try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
			fieldpanel.npc[0].look(RPlayer.UP);
			for (int i = 0; i < 4; i++) {
				fieldpanel.npc[0].move();
				try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
			}

			fieldpanel.npc[0].look(RPlayer.RIGHT);
			for (int i = 0; i < 8; i++) {
				fieldpanel.npc[0].move();
				try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
			}

			fieldpanel.npc[0].look(RPlayer.UP);
			for (int i = 0; i < 4; i++) {
				fieldpanel.npc[0].move();
				try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
			}
			fieldpanel.npc[0].locate(-1, -1);
			chapter++;
		}

		public void chapter3(){
			player.look(RPlayer.UP);
			try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
			dialogpanel.changeImage("image/dialog/chapter3-0.png");
			chapter++;
		}

		public void chapter4(){
			player.look(RPlayer.UP);
			try {Thread.sleep(200);} catch (InterruptedException ie) {ie.printStackTrace();}
			dialogpanel.changeImage("image/dialog/chapter4-0.png");
			chapter++;
		}


		public void talkMom(){
			if (keyPressed == KeyEvent.VK_ENTER) {
				if (player.lookAt() == RPlayer.UP) {
					dialogpanel.changeImage("image/dialog/helloMom-0.png");
					try {Thread.sleep(4000);} catch (InterruptedException ie) {ie.printStackTrace();}
					dialogpanel.changeImage("image/dialog/helloMom-1.png");
					keyPressed = 0;
				}
			}
		}

		public void talkSolider(){
			if (keyPressed == KeyEvent.VK_ENTER) {
				if (player.lookAt() == RPlayer.UP) {
					// dialogpanel.changeImage("image/dialog/Hello.png");
					// try {Thread.sleep(4000);} catch (InterruptedException ie) {ie.printStackTrace();}
					dialogpanel.changeImage("image/dialog/solider1.png");
					keyPressed = 0;
				}
			}
		}

		public void talkSolider2(){
			if (keyPressed == KeyEvent.VK_ENTER) {
				if (player.lookAt() == RPlayer.LEFT) {
					// dialogpanel.changeImage("image/dialog/Hello.png");
					// try {Thread.sleep(4000);} catch (InterruptedException ie) {ie.printStackTrace();}
					dialogpanel.changeImage("image/dialog/solider2.png");
					keyPressed = 0;
				}
			}
		}

		public void talkJohn(){
			if (keyPressed == KeyEvent.VK_ENTER) {
				if (player.lookAt() == RPlayer.RIGHT) {
					dialogpanel.changeImage("image/dialog/Hello.png");
					try {Thread.sleep(4000);} catch (InterruptedException ie) {ie.printStackTrace();}
					dialogpanel.changeImage("image/dialog/John.png");
					keyPressed = 0;
				}
			}
		}

	}
}