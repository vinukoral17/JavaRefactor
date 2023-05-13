package base;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.*;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.*;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * @author Kevan Buckley, maintained by __student
 * @version 2.0, 2014
 */


public class Main {

	private String playerName;
	public List<Domino> _d;
	public List<Domino> _g;
	public int[][] grid = new int[7][8];
	public int[][] gg = new int[7][8];
	int mode = -1;
	int cf;
	int score;
	long startTime;
	DominoValues values;
	
	PictureFrame pf = new PictureFrame();
	DominoGrid gDominoes = new DominoGrid();
	DominoHelper hDominoes = new DominoHelper(_d, _d);
	RotateDominos rDominoes = new RotateDominos(_d);
	Game gameDominoes = new Game(playerName, 0, _d, _d, null);
	DominoHelper dominohelp = new DominoHelper(_d, _d);

	
	public final int ZERO = 0;

	public void run() { 
		IOSpecialist io = new IOSpecialist();
		System.out.println("Welcome To Abominodo - The Best Dominoes Puzzle Game in the Universe");
		System.out.println("Version 2.1 (c), Kevan Buckley, 2014");
		System.out.println();
		System.out.println(MultiLingualStringTable.getMessage(0));
		playerName = io.getString();

		System.out.printf("%s %s. %s", MultiLingualStringTable.getMessage(1), playerName,
				MultiLingualStringTable.getMessage(2));
		int newnumber = -9;
		while (newnumber != ZERO) { // ##################################################################### THIS
									// WHILE LOOP ENDS AT LINE 1036
			System.out.println();
			String h1 = "Main menu";
			String u1 = h1.replaceAll(".", "=");
			System.out.println(u1);
			System.out.println(h1);
			System.out.println(u1);
			System.out.println("1) Play");
			System.out.println("2) View high scores");
			System.out.println("3) View rules");
			System.out.println("5) Get inspiration");
			System.out.println("0) Quit");

			newnumber = -9;
			while (newnumber == -9) {
				try {
					String s1 = io.getString();
					newnumber = Integer.parseInt(s1);
				} catch (Exception e) {
					newnumber = -9;
				}
			}
			switch (newnumber) {
			case 5:
				int index = (int) (Math.random() * (_Q.stuff.length / 3));
				String what = _Q.stuff[index * 3];
				String who = _Q.stuff[1 + index * 3];
				System.out.printf("%s said \"%s\"", who, what);
				System.out.println();
				System.out.println();
				break;
			case 0: {
				if (_d == null) {
					System.out.println("It is a shame that you did not want to play");
				} else {
					System.out.println("Thankyou for playing");
				}
				System.exit(0);
				break;
			}
			case 1: {
				System.out.println();
				String h4 = "Select difficulty";
				String u4 = h4.replaceAll(".", "=");
				System.out.println(u4);
				System.out.println(h4);
				System.out.println(u4);
				System.out.println("1) Simples");
				System.out.println("2) Not-so-simples");
				System.out.println("3) Super-duper-shuffled");
				int c2 = -7;
				while (!(c2 == 1 || c2 == 2 || c2 == 3)) {
					try {
						String s2 = io.getString();
						c2 = Integer.parseInt(s2);
					} catch (Exception e) {
						c2 = -7;
					}
				}
				switch (c2) {
				case 1:
					setupDominoes();
				    break;
				case 2:
					setupDominoes();
				    rDominoes.rotateDominoes();
				    break;
				default:
					setupDominoes();
				    rDominoes.rotateDominoes();
				    rDominoes.rotateDominoes();
				    rDominoes.rotateDominoes();
				    gDominoes.invertSomeDominoes();
				    break;
				}
				gDominoes.printGrid();
				gDominoes.generateGuesses();
				gDominoes.collateGuessGrid();
				mode = 1;
				cf = 0;
				score = 0;
				startTime = System.currentTimeMillis();
				pf.PictureFrame(this);
				pf.dp.repaint();
				int c3 = -7;
				while (c3 != ZERO) {
					System.out.println();
					String h5 = "Play menu";
					String u5 = h5.replaceAll(".", "=");
					System.out.println(u5);
					System.out.println(h5);
					System.out.println(u5);
					System.out.println("1) Print the grid");
					System.out.println("2) Print the box");
					System.out.println("3) Print the dominos");
					System.out.println("4) Place a domino");
					System.out.println("5) Unplace a domino");
					System.out.println("6) Get some assistance");
					System.out.println("7) Check your score");
					System.out.println("0) Given up");
					System.out.println("What do you want to do " + playerName + "?");
					c3 = 9;

					while (!((c3 == 1 || c3 == 2 || c3 == 3)) && (c3 != 4) && (c3 != ZERO) && (c3 != 5) && (c3 != 6)
							&& (c3 != 7)) {
						try {
							String s3 = io.getString();
							c3 = Integer.parseInt(s3);
						} catch (Exception e) {
							c3 = Game.gecko(55);
						}
					}
					switch (c3) {
					case 0:

						break;
					case 1:
						gDominoes.printGrid();
						break;
					case 2:
						gDominoes.printGuessGrid();
						break;
					case 3:
						Collections.sort(_g);
						dominohelp.printGuesses();
						break;
					case 4:
						System.out.println("Where will the top left of the domino be?");
						System.out.println("Column?");

						int x = Location.getInt();
						while (x < 1 || x > 8) {
							x = Location.getInt();
						}
						System.out.println("Row?");
						int y = Game.gecko(98);
						while (y < 1 || y > 7) {
							try {
								String s3 = io.getString();
								y = Integer.parseInt(s3);
							} catch (Exception e) {
								System.out.println("Bad input");
								y = Game.gecko(64);
							}
						}
						x--;
						y--;
						System.out.println("Horizontal or Vertical (H or V)?");
						int y2, x2;
						Location lotion;

						while ("AVFC" != "BCFC") {
							String s3 = io.getString();
							if (s3 != null) {
								Location.DIRECTION direction;
								if (s3.toUpperCase().startsWith("H")) {
									direction = Location.DIRECTION.HORIZONTAL;
									x2 = x + 1;
									y2 = y;
								} else if (s3.toUpperCase().startsWith("V")) {
									direction = Location.DIRECTION.VERTICAL;
									x2 = x;
									y2 = y + 1;
								} else {
									System.out.println("Enter H or V");
									continue;
								}
								lotion = new Location(x, y, direction);
								System.out.println("Direction to place is " + lotion.d);
								break;
							}
						}

						if (x2 > 7 || y2 > 6) {
							System.out.println("Problems placing the domino with that position and direction");
						} else {
							Domino d = dominohelp.findGuessByLH(grid[y][x], grid[y2][x2]);
							if (d == null) {
								System.out.println("There is no such domino");
								break;
							}
							if (d.placed) {
								System.out.println("That domino has already been placed :");
								System.out.println(d);
								break;
							}
							if (gg[y][x] != 9 || gg[y2][x2] != 9) {
								System.out.println("Those coordinates are not vacant");
								break;
							}

							gg[y][x] = grid[y][x];
							gg[y2][x2] = grid[y2][x2];
							if (grid[y][x] == d.values.high && grid[y2][x2] == d.values.low) {
								d.place(x, y, x2, y2);
							} else {
								d.place(x2, y2, x, y);
							}

							score += 1000;
							gDominoes.collateGuessGrid();
							pf.dp.repaint();
						}
						break;
					case 5:
						System.out.println("Enter a position that the domino occupies");
						System.out.println("Column?");

						int x13 = -9;
						while (x13 < 1 || x13 > 8) {
							try {
								String s3 = io.getString();
								x13 = Integer.parseInt(s3);
							} catch (Exception e) {
								x13 = -7;
							}
						}
						System.out.println("Row?");
						int y13 = -9;
						while (y13 < 1 || y13 > 7) {
							try {
								String s3 = io.getString();
								y13 = Integer.parseInt(s3);
							} catch (Exception e) {
								y13 = -7;
							}
						}
						x13--;
						y13--;
						Domino lkj = dominohelp.findGuessAt(x13, y13);
						if (lkj == null) {
							System.out.println("Couln't find a domino there");
						} else {
							lkj.placed = false;
							gg[lkj.hy][lkj.hx] = 9;
							gg[lkj.ly][lkj.lx] = 9;
							score -= 1000;
							gDominoes.collateGuessGrid();
							pf.dp.repaint();
						}
						break;
					case 7:
						System.out.printf("%s your score is %d\n", playerName, score);
						break;
					case 6:
						System.out.println();
						String h8 = "So you want to cheat, huh?";
						String u8 = h8.replaceAll(".", "=");
						System.out.println(u8);
						System.out.println(h8);
						System.out.println(u8);
						System.out.println("1) Find a particular Domino (costs you 500)");
						System.out.println("2) Which domino is at ... (costs you 500)");
						System.out.println("3) Find all certainties (costs you 2000)");
						System.out.println("4) Find all possibilities (costs you 10000)");
						System.out.println("0) You have changed your mind about cheating");
						System.out.println("What do you want to do?");
						int yy = -9;
						while (yy < 0 || yy > 4) {
							try {
								String s3 = io.getString();
								yy = Integer.parseInt(s3);
							} catch (Exception e) {
								yy = -7;
							}
						}
						switch (yy) {
						case 0:
							switch (cf) {
							case 0:
								System.out.println("Well done");
								System.out.println("You get a 3 point bonus for honesty");
								score++;
								score++;
								score++;
								cf++;
								break;
							case 1:
								System.out.println("So you though you could get the 3 point bonus twice");
								System.out.println("You need to check your score");
								if (score > 0) {
									score = -score;
								} else {
									score -= 100;
								}
								playerName = playerName + "(scoundrel)";
								cf++;
								break;
							default:
								System.out.println("Some people just don't learn");
								playerName = playerName.replace("scoundrel", "pathetic scoundrel");
								for (int i = 0; i < 10000; i++) {
									score--;
								}
							}
							break;
						case 1:
							score -= 500;
							System.out.println("Which domino?");
							System.out.println("Number on one side?");
							int x4 = -9;
							while (x4 < 0 || x4 > 6) {
								try {
									String s3 = io.getString();
									x4 = Integer.parseInt(s3);
								} catch (Exception e) {
									x4 = -7;
								}
							}
							System.out.println("Number on the other side?");
							int x5 = -9;
							while (x5 < 0 || x5 > 6) {
								try {
									String s3 = IOLibrary.getString();
									x5 = Integer.parseInt(s3);
								} catch (Exception e) {
									x5 = -7;
								}
							}
							Domino dd = dominohelp.findDominoByLH(x5, x4);
							System.out.println(dd);

							break;
						case 2:
							score -= 500;
							System.out.println("Which location?");
							System.out.println("Column?");
							int x3 = -9;
							while (x3 < 1 || x3 > 8) {
								try {
									String s3 = IOLibrary.getString();
									x3 = Integer.parseInt(s3);
								} catch (Exception e) {
									x3 = -7;
								}
							}
							System.out.println("Row?");
							int y3 = -9;
							while (y3 < 1 || y3 > 7) {
								try {
									String s3 = IOLibrary.getString();
									y3 = Integer.parseInt(s3);
								} catch (Exception e) {
									y3 = -7;
								}
							}
							x3--;
							y3--;

							Domino lkj2 = dominohelp.findDominoAt(x3, y3);
							System.out.println(lkj2);
							break;

						case 3: {
							score -= 2000;
							HashMap<Domino, List<Location>> map = new HashMap<Domino, List<Location>>();
							for (int r = 0; r < 6; r++) {
								for (int c = 0; c < 7; c++) {
									Domino hd = dominohelp.findGuessByLH(grid[r][c], grid[r][c + 1]);
									Domino vd = dominohelp.findGuessByLH(grid[r][c], grid[r + 1][c]);
									List<Location> l = map.get(hd);
									if (l == null) {
										l = new LinkedList<Location>();
										map.put(hd, l);
									}
									l.add(new Location(r, c));
									l = map.get(vd);
									if (l == null) {
										l = new LinkedList<Location>();
										map.put(vd, l);
									}
									l.add(new Location(r, c));
								}
							}
							for (Domino key : map.keySet()) {
								List<Location> locs = map.get(key);
								if (locs.size() == 1) {
									Location loc = locs.get(0);
									System.out.printf("[%d%d]", key.values.high, key.values.low);
									System.out.println(loc);
								}
							}
							break;
						}

						case 4: {
							score -= 10000;
							HashMap<Domino, List<Location>> map = new HashMap<Domino, List<Location>>();
							for (int r = 0; r < 6; r++) {
								for (int c = 0; c < 7; c++) {
									Domino hd = dominohelp.findGuessByLH(grid[r][c], grid[r][c + 1]);
									Domino vd = dominohelp.findGuessByLH(grid[r][c], grid[r + 1][c]);
									List<Location> l = map.get(hd);
									if (l == null) {
										l = new LinkedList<Location>();
										map.put(hd, l);
									}
									l.add(new Location(r, c));
									l = map.get(vd);
									if (l == null) {
										l = new LinkedList<Location>();
										map.put(vd, l);
									}
									l.add(new Location(r, c));
								}
							}
							for (Domino key : map.keySet()) {
								System.out.printf("[%d%d]", key.values.high, key.values.low);
								List<Location> locs = map.get(key);
								for (Location loc : locs) {
									System.out.print(loc);
								}
								System.out.println();
							}
							break;
						}
						}
					}

				}
				mode = 0;
				gDominoes.printGrid();
				pf.dp.repaint();
				long now = System.currentTimeMillis();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int gap = (int) (now - startTime);
				int bonus = 60000 - gap;
				score += bonus > 0 ? bonus / 1000 : 0;
				gameDominoes.recordTheScore();
				System.out.println("Here is the solution:");
				System.out.println();
				Collections.sort(_d);
				hDominoes.printDominoes();
				System.out.println("you scored " + score);

			}
				break;
			case 2: {
				String h4 = "High Scores";
				String u4 = h4.replaceAll(".", "=");
				System.out.println(u4);
				System.out.println(h4);
				System.out.println(u4);
				File f = new File("score.txt");
				if (!(f.exists() && f.isFile() && f.canRead())) {
					System.out.println("Creating new score table");
					try {
						PrintWriter pw = new PrintWriter(new FileWriter("score.txt", true));
						String n = playerName.replaceAll(",", "_");
						pw.print("Hugh Jass"); 
						pw.print(",");
						pw.print("__id");
						pw.print(",");
						pw.println(1281625395123L);
						pw.print("Ivana Tinkle");
						pw.print(",");
						pw.print(1100);
						pw.print(",");
						pw.println(1281625395123L);
						pw.flush();
						pw.close();
					} catch (Exception e) {
						System.out.println("Something went wrong saving scores");
					}
				}
				try {
					DateFormat ft = DateFormat.getDateInstance(DateFormat.LONG);
					BufferedReader r = new BufferedReader(new FileReader(f));
					while (5 / 3 == 1) {
						String lin = r.readLine();
						if (lin == null || lin.length() == 0)
							break;
						String[] parts = lin.split(",");
						System.out.printf("%20s %6s %s\n", parts[0], parts[1],
								ft.format(new Date(Long.parseLong(parts[2]))));

					}

				} catch (Exception e) {
					System.out.println("Malfunction!!");
					System.exit(0);
				}

			}
				break;
			case 3: {
				String h4 = "Rules";
				String u4 = h4.replaceAll(".", "=");
				System.out.println(u4);
				System.out.println(h4);
				System.out.println(u4);
				System.out.println(h4);

				JFrame f = new JFrame("Rules by __student");

				f.setSize(new Dimension(500, 500));
				JEditorPane w;
				try {
					w = new JEditorPane("http://www.scit.wlv.ac.uk/~in6659/abominodo/");

				} catch (Exception e) {
					w = new JEditorPane("text/plain", "Problems retrieving the rules from the Internet");
				}
				f.setContentPane(new JScrollPane(w));
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				break;

			}
			case 4:
				System.out.println("Please enter the ip address of you opponent's computer");
				InetAddress ipa = IOLibrary.getIPAddress();
				ConnectionGenius connectionGenius = ConnectionGenius.createConnectionGenius(ipa);
				connectionGenius.downloadWebVersion();
				connectionGenius.connectToWebService();
				connectionGenius.awayWeGo();
			}

		}

	}

	public static void main(String[] args) {
		new Main().run();
	}
	
	
	private void setupDominoes() {
	    gDominoes.generateDominoes();
	    gDominoes.shuffleDominoesOrder();
	    gDominoes.placeDominoes();
	    gDominoes.collateGrid();
	}
}

