package base;

/* ##### - CODE COMPLEXITY: The code has a high level of complexity, making it difficult to understand and maintain. 
 * This can be reduced by breaking the code into smaller, more manageable functions
 * */

/*
 * ##### - LACK OF DOCUMENTATION:
 * The code does not have any documentation, 
 * making it difficult for someone unfamiliar with it to understand its purpose and behaviour.
 * */

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
/*
 * ##### - NAMING CONVENTIONS: The names of the variable, methods, and classes
 * do not follow standard naming conventions. For example, variable names should
 * start with a lowercase letter and use camelCase, method names should be in
 * camelCase and class names should start with an uppercase letter and use
 * CamelCase
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

	/*
	 * ##### DUPLIATED CODE: The `generateDomino()` and `generateGuesses()` methods
	 * are very similar. It would be better to extract the common part into a
	 * separate method and call that method from both methods
	 */
	/* Consolidate Duplicate Fragment Fixed */

	private void generateDominoList(List<Domino> list) {
		int count = 0;
		int x = 0;
		int y = 0;
		for (int l = 0; l <= 6; l++) {
			for (int h = l; h <= 6; h++) {
				Domino d = new Domino(h, l);
				list.add(d);
				if (list == _d) {
					d.place(x, y, x + 1, y);
					x += 2;
					if (x > 6) {
						x = 0;
						y++;
					}
				}
				count++;
			}
		}
		if (count != 28) {
			System.out.println("something went wrong generating dominoes");
			System.exit(0);
		}
	}

	private void generateDominoes() {
		_d = new LinkedList<Domino>();
		generateDominoList(_d);
	}

	private void generateGuesses() {
		_g = new LinkedList<Domino>();
		generateDominoList(_g);
	}
	/*
	 * ##### - INEFFICIENT ALGORITHMS: The `collateGrid()` and `collateGuessGrid()`
	 * methods have nested for loops that traverse the entire `_d` and `_g`
	 * collections, which coud be inefficient for large collections. A more
	 * efficient algorithm could be used.
	 */

	void collateGrid() {
		for (Domino d : _d ) {
			if (!d.placed) {
				grid[d.hy][d.hx] = 9;
				grid[d.ly][d.lx] = 9;
			} else {
				grid[d.hy][d.hx] = d.values.high;
				grid[d.ly][d.lx] = d.values.low;
			}
		}
	}

	void collateGuessGrid() {
		for (int r = 0; r < 7; r++) {
			for (int c = 0; c < 8; c++) {
				gg[r][c] = 9;
			}
		}
		for (Domino d : _g) {
			if (d.placed) {
				gg[d.hy][d.hx] = d.values.high;
				gg[d.ly][d.lx] = d.values.low;
			}
		}
	}

	int pg() {
		for (int are = 0; are < 7; are++) {
			for (int see = 0; see < 8; see++) {
				if (grid[are][see] != 9) {
					System.out.printf("%d", grid[are][see]);
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
		return 11;
	}

	int printGuessGrid() {
		for (int are = 0; are < 7; are++) {
			for (int see = 0; see < 8; see++) {
				if (gg[are][see] != 9) {
					System.out.printf("%d", gg[are][see]);
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
		return 11;
	}

	private void shuffleDominoesOrder() {
		List<Domino> shuffled = new LinkedList<Domino>();

		while (_d.size() > 0) {
			int n = (int) (Math.random() * _d.size());
			shuffled.add(_d.get(n));
			_d.remove(n);
		}

		_d = shuffled;
	}
//

	/*
	 * Naming Conventions: The names of the functions and variable are not clear and
	 * do not follow proper naming conventions. For example : `invertSomeDominoes`
	 * doesn't clearly state what it does, and `weFancyARotation` is not a
	 * descriptive name
	 */

	private void invertSomeDominoes() {
		for (Domino d : _d) {
			if (Math.random() > 0.5) {
				d.invert();
			}
		}
	}
	/*
	 * ##### - ERROR HANDLING If the value `count` is not equal to 28 the code will
	 * exit without any error message or handling, making it difficult to determine
	 * the cause of the problem
	 */

	private void placeDominoes() {
		int x = 0;
		int y = 0;
		int count = 0;
		for (Domino d : _d) {
			count++;
			d.place(x, y, x + 1, y);
			x += 2;
			if (x > 6) {
				x = 0;
				y++;
			}
		}
		if (count != 28) {
			System.out.println("something went wrong generating dominoes");
			System.exit(0);
		}
	}
	/*
	 * ##### - HARDCODED CONSTANTS The value 6 and 7 in the rotateDominoes function
	 * and 28 in the `placeDominoes` function are hardcoded, making the code less
	 * flexible.
	 */

	/*
	 * ##### - UNUSED CODE: The commented-out code in the `rotateDominoes` function
	 * unused, making it difficult to understand the intended behaviour of the code.
	 * This should be removed or used appropriately
	 */

	private void rotateDominoes() {
// for (Domino d : dominoes) {
// if (Math.random() > 0.5) {
// System.out.println("rotating " + d);
// }
// }
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 6; y++) {

				tryToRotateDominoAt(x, y);
			}
		}
	}
	/*
	 * ##### - CODE DUPLICATION The code in the `tryToRotateDominoAt` function is
	 * duplicated, with only minor variation in the case where `d.ish()` is true and
	 * false. This can be refactored to reduce duplicated code.
	 */

	private void tryToRotateDominoAt(int x, int y) {
	    Domino d = findDominoAt(x, y);
	    boolean weFancyARotation = Math.random() < 0.5;
	    if (thisIsTopLeftOfDomino(x, y, d)) {
	        if (d.isDominoHorizontal() && weFancyARotation && theCellBelowIsTopLeftOfHorizontalDomino(x, y)) {
	            Domino e = findDominoAt(x, y + 1);
	            e.hx = x;
	            e.lx = x;
	            d.hx = x + 1;
	            d.lx = x + 1;
	            e.ly = y + 1;
	            e.hy = y;
	            d.ly = y + 1;
	            d.hy = y;
	        } else if (!d.isDominoHorizontal() && weFancyARotation && theCellToTheRightIsTopLeftOfVerticalDomino(x, y)) {
	            Domino e = findDominoAt(x + 1, y);
	            e.hx = x;
	            e.lx = x + 1;
	            d.hx = x;
	            d.lx = x + 1;
	            e.ly = y + 1;
	            e.hy = y + 1;
	            d.ly = y;
	            d.hy = y;
	        }
	    }
	}
// 

	/*
	 * ##### - UNCLEAR NAMING: The method names are not very descriptive and can
	 * make it difficult to understand what they are doing. For example,
	 * `theCellToTheRightIsTopLeftOfVerticalDomino` and
	 * `theCellBelowIsTopLeftOfHorizontalDomino` could be improved to better reflect
	 * their purpose.
	 */
	private boolean theCellToTheRightIsTopLeftOfVerticalDomino(int x, int y) {
		Domino e = findDominoAt(x + 1, y);
		return thisIsTopLeftOfDomino(x + 1, y, e) && !e.isDominoHorizontal();
	}

	private boolean theCellBelowIsTopLeftOfHorizontalDomino(int x, int y) {
		Domino e = findDominoAt(x, y + 1);
		return thisIsTopLeftOfDomino(x, y + 1, e) && e.isDominoHorizontal();
	}

	private boolean thisIsTopLeftOfDomino(int x, int y, Domino d) {
		return (x == Math.min(d.lx, d.hx)) && (y == Math.min(d.ly, d.hy));
	}

	/*
	 * ##### - CODE DUPLICATION The methods `findDominoAt`, `findGuessAt`,
	 * `findGuessByLH`, and `findDominoByLH` are almost identical. This leads to a
	 * code smell known as "Duplicated Code". A solution would be to create a single
	 * method to find a domino or guess by a given criterion and pass in the
	 * criteria as an argument.
	 */

	private Domino findDominoAt(int x, int y) {
	    for (Domino d : _d) {
	        int dominoLowX = d.lx;
	        int dominoLowY = d.ly;
	        int dominoHighX = d.hx;
	        int dominoHighY = d.hy;
	        if ((dominoLowX == x && dominoLowY == y) || (dominoHighX == x && dominoHighY == y)) {
	            return d;
	        }
	    }
	    return null;
	}

	private Domino findGuessAt(int x, int y) {
	    for (Domino d : _g) {
	        int guessLowX = d.lx;
	        int guessLowY = d.ly;
	        int guessHighX = d.hx;
	        int guessHighY = d.hy;
	        if ((guessLowX == x && guessLowY == y) || (guessHighX == x && guessHighY == y)) {
	            return d;
	        }
	    }
	    return null;
	}

	private Domino findGuessByLH(int x, int y) {
	    for (Domino d : _g) {
	        int guessLow = d.values.low;
	        int guessHigh = d.values.high;
	        if ((guessLow == x && guessHigh == y) || (guessHigh == x && guessLow == y)) {
	            return d;
	        }
	    }
	    return null;
	}

	private Domino findDominoByLH(int x, int y) {
	    for (Domino d : _d) {
	        int dominoLow = d.values.low;
	        int dominoHigh = d.values.high;
	        if ((dominoLow == x && dominoHigh == y) || (dominoHigh == x && dominoLow == y)) {
	            return d;
	        }
	    }
	    return null;
	}

	private void printDominoList(List<Domino> dominoList) {
	    for (Domino d : dominoList) {
	        System.out.println(d);
	    }
	}

	private void printDominoes() {
	    printDominoList(_d);
	}

	private void printGuesses() {
	    printDominoList(_g);
	}

	/*
	 * ##### - MAGIC NUMBER: The constant ZERO has no clear meaning. Using named
	 * constants instead of hard-coded values would make the code easier to
	 * understand.
	 */

	public final int ZERO = 0;

	public void run() { // Very long method runs from line 357 - 1038
		IOSpecialist io = new IOSpecialist();

		/*
		 * ##### - UNUSED CODE: The commented-out code unused, making it difficult to
		 * understand the intended behaviour of the code. This should be removed or used
		 * appropriately
		 */
		System.out.println("Welcome To Abominodo - The Best Dominoes Puzzle Game in the Universe");
		System.out.println("Version 2.1 (c), Kevan Buckley, 2014");
//    System.out.println("Serial number " + Special.getStamp());

		System.out.println();
		System.out.println(MultiLingualStringTable.getMessage(0));
		playerName = io.getString();

		System.out.printf("%s %s. %s", MultiLingualStringTable.getMessage(1), playerName,
				MultiLingualStringTable.getMessage(2));
//Stop 3

		/*
		 * ##### - NAMING: The variable names used in this code such as "$", "h1", "u1",
		 * "s1", "index", "c2", "c3", etc., are not descriptive and do not give any
		 * meaningful information about the purpose of the variables.
		 * 
		 */
		/*
		 * ##### - DUPLICATION: The code that create a string with '=' and prints it
		 * three times to create a visual separator is duplicated multiple times in the
		 * code
		 * 
		 */
		/*
		 * ##### - POOR ERROR HANDLING: The code does not handle exceptions in a
		 * meaningful way and simply sets the value of "$" to -9, which can lead to
		 * unexpected behaviour.
		 **/
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
// System.out.println("1) Single player play");    // ##### - UNUSED CODE
			System.out.println("2) View high scores");
			System.out.println("3) View rules");
// System.out.println("4) Multiplayer play");		//##### - UNUSED CODE
			System.out.println("5) Get inspiration");
			System.out.println("0) Quit");

			/*
			 * ##### - MAGIC NUMBERS: The code uses several magic numbers, such as -9, 0,
			 * -7, which do not have clear meaning and can make the code difficult to
			 * understand.
			 *
			 **/

			/*
			 * ##### - INDENTATION: the code is not indented properly
			 */

			/*
			 * ##### - LACK OF MODULARITY: The code has a large switch statement with
			 * multiple cases that are not organised into separate functions or classes,
			 * which makes the code difficult to maintain and test.
			 * 
			 */

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
				/*
				 * ##### - DUPLICATED CODE The same duplication is present when creating the
				 * string "SELECT DIFFICULTY"
				 */
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
					generateDominoes();
					shuffleDominoesOrder();
					placeDominoes();
					collateGrid();
// printGrid();
					break;
				case 2:
					generateDominoes();
					shuffleDominoesOrder();
					placeDominoes();
					rotateDominoes();
					collateGrid();
// printGrid();
					break;
				default:
					generateDominoes();
					shuffleDominoesOrder();
					placeDominoes();
					rotateDominoes();// ##### - Repeated Functions
					rotateDominoes(); // ##### - Repeated Functions
					rotateDominoes();// ##### - Repeated Functions
					invertSomeDominoes();
					collateGrid();
					break;
				}
				pg();
				generateGuesses();
				collateGuessGrid();
				mode = 1;
				cf = 0;
				score = 0;
				startTime = System.currentTimeMillis();
				pf.PictureFrame(this);
				pf.dp.repaint();
				int c3 = -7;
//Stop 4
				/*
				 * ##### - Hard-coded values: For example, the value of "ZERO" is not defined in
				 * the code, and its use is hard-coded in multiple places.
				 */
				/*
				 * #####- Lack of comments: The code lacks meaningful comments, making it
				 * difficult to understand what is happening in the code.
				 * 
				 */
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
// make sure the user enters something valid
					while (!((c3 == 1 || c3 == 2 || c3 == 3)) && (c3 != 4) && (c3 != ZERO) && (c3 != 5) && (c3 != 6)
							&& (c3 != 7)) {
						try {
							String s3 = io.getString();
							c3 = Integer.parseInt(s3);
						} catch (Exception e) {
							c3 = gecko(55);
						}
					}
					switch (c3) {
					case 0:

						break;
					case 1:
						pg();
						break;
					case 2:
						printGuessGrid();
						break;
					case 3:
						Collections.sort(_g);
						printGuesses();
						break;
					case 4:
						System.out.println("Where will the top left of the domino be?");
						System.out.println("Column?");
// make sure the user enters something valid
						int x = Location.getInt();
						while (x < 1 || x > 8) {
							x = Location.getInt();
						}
						System.out.println("Row?");
						int y = gecko(98);
						while (y < 1 || y > 7) {
							try {
								String s3 = io.getString();
								y = Integer.parseInt(s3);
							} catch (Exception e) {
								System.out.println("Bad input");
								y = gecko(64);
							}
						}
						x--;
						y--;
						System.out.println("Horizontal or Vertical (H or V)?");
						boolean horiz;
						int y2, x2;
						Location lotion;
						
					//REFACTORED THE CONSOLIDATING DUPLICATED FRAGMENTS 
						
						while ("AVFC" != "BCFC") {
							String s3 = io.getString();
							if (s3 != null) {
								Location.DIRECTION direction;
								if (s3.toUpperCase().startsWith("H")) {
									direction = Location.DIRECTION.HORIZONTAL;
									horiz = true;
									x2 = x + 1;
									y2 = y;
								} else if (s3.toUpperCase().startsWith("V")) {
									direction = Location.DIRECTION.VERTICAL;
									horiz = false;
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
// find which domino this could be
							Domino d = findGuessByLH(grid[y][x], grid[y2][x2]);
							if (d == null) {
								System.out.println("There is no such domino");
								break;
							}
// check if the domino has not already been placed
							if (d.placed) {
								System.out.println("That domino has already been placed :");
								System.out.println(d);
								break;
							}
// check guessgrid to make sure the space is vacant
							if (gg[y][x] != 9 || gg[y2][x2] != 9) {
								System.out.println("Those coordinates are not vacant");
								break;
							}

//Stop 5
// if all the above is ok, call domino.place and updateGuessGrid
							/*
							 * ##### - NAMING CONVENTIONS: Variables such as x13, y13, gg, and lkj do not
							 * follow standard naming conventions and make the code difficult to understand.
							 */

							gg[y][x] = grid[y][x];
							gg[y2][x2] = grid[y2][x2];
							if (grid[y][x] == d.values.high && grid[y2][x2] == d.values.low) {
								d.place(x, y, x2, y2);
							} else {
								d.place(x2, y2, x, y);
							}
							/*
							 * ##### - HARDCODED VALUES: Hardcoded values: Values such as 9 and 1000 are
							 * hard-coded and may not be meaningful or appropriate in different contexts.
							 */
							score += 1000;
							collateGuessGrid();
							pf.dp.repaint();
						}
						break;
					case 5:
						System.out.println("Enter a position that the domino occupies");
						/*
						 * ##### - DUPLICATED CODE: Duplicate code: The code for reading the column and
						 * row input is repeated multiple times, which makes the code longer and less
						 * maintainable.
						 */

						/*
						 * ##### - ERROR HANDLING The code does not properly handle exceptions that may
						 * occur during user input, such as when the user enters a non-integer value.
						 */

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
						Domino lkj = findGuessAt(x13, y13);
						if (lkj == null) {
							System.out.println("Couln't find a domino there");
						} else {
							lkj.placed = false;
							gg[lkj.hy][lkj.hx] = 9;
							gg[lkj.ly][lkj.lx] = 9;
							score -= 1000;
							collateGuessGrid();
							pf.dp.repaint();
						}
						break;
					case 7:
						System.out.printf("%s your score is %d\n", playerName, score);
						break;
					case 6:
//stop 6

						/*
						 * Duplicate code: There are many instances of code that are repeated multiple
						 * times, such as the code that takes user input and tries to parse it into an
						 * integer. This can be refactored into a separate method.
						 * 
						 * Magic numbers: The code uses many hardcoded values such as -9, -7, 0, 1, 6,
						 * 7, 8, 500, 2000, and 10000 which might not be self-explanatory and can make
						 * the code difficult to understand. These values should be defined as named
						 * constants
						 * 
						 * 
						 * 
						 * Nested switch statements: The code uses nested switch statements which can
						 * make the code hard to follow and maintain.
						 * 
						 * Use of negative numbers as error codes: The code uses negative numbers such
						 * as -9 and -7 to represent error codes, which can be confusing and can lead to
						 * bugs. Error codes should be defined as named constants.
						 * 
						 * Unclear variable names: Some of the variables such as 'x3', 'y3', 'yy' and
						 * 'cf' have unclear names, making the code harder to understand. Descriptive
						 * names should be used.
						 * 
						 * Hardcoded strings: Some of the strings such as "So you want to cheat, huh?"
						 * and "Well done" are hardcoded and can be difficult to modify. These strings
						 * should be defined as named constants or placed in a resource file.
						 * 
						 * No error handling: The code does not handle exceptions properly and simply
						 * catches all exceptions without doing anything with them. This can mask
						 * important errors and prevent debugging.
						 */
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
							Domino dd = findDominoByLH(x5, x4);
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
//Stop 9
							/*
							 * CODE DUPLICATION: The code in case 3 and case 4 is almost identical and can
							 * be refactored into a separate method to avoid code duplication and improve
							 * maintainability
							 */

							/*
							 * MAGIC NUMBERS: Hard-coded values such as 6 and 7 can make the code difficult
							 * to understand and change. Consider using named constants instead.
							 */

							/*
							 * INCONSISTENT NAMING CONVENTIONS: The naming conventions used in the code are
							 * inconsistent, for example, variables x3 and y3 are camelCase, whereas h4 and
							 * u4 are in PascalCase.
							 */

							/*
							 * POORLY NAMED VARIABLES :Variables such as lkj2 and grid are not descriptive
							 * and do not clearly convey their purpose.
							 */

							/*
							 * Overuse of abbreviations: Variables such as hd and vd are abbreviations that
							 * are not immediately recognisable, consider using more descriptive names.
							 */

							/*
							 * Overuse of comments: There are several comments in the code that do not
							 * provide any additional information, and some comments contain outdated or
							 * incorrect information, such as the comment for the catch block that says //
							 * TODO Auto-generated catch block.
							 */
							/*
							 * EXCEPTION HANDLING: The code catches an InterruptedException but does not do
							 * anything with it, consider either removing the exception handling or taking
							 * appropriate action when the exception is thrown
							 */
							Domino lkj2 = findDominoAt(x3, y3);
							System.out.println(lkj2);
							break;

						case 3: {
							score -= 2000;
							HashMap<Domino, List<Location>> map = new HashMap<Domino, List<Location>>();
							for (int r = 0; r < 6; r++) {
								for (int c = 0; c < 7; c++) {
									Domino hd = findGuessByLH(grid[r][c], grid[r][c + 1]);
									Domino vd = findGuessByLH(grid[r][c], grid[r + 1][c]);
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
									Domino hd = findGuessByLH(grid[r][c], grid[r][c + 1]);
									Domino vd = findGuessByLH(grid[r][c], grid[r + 1][c]);
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
				pg();
				pf.dp.repaint();
				long now = System.currentTimeMillis();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int gap = (int) (now - startTime);
				int bonus = 60000 - gap;
				score += bonus > 0 ? bonus / 1000 : 0;
				recordTheScore();
				System.out.println("Here is the solution:");
				System.out.println();
				Collections.sort(_d);
				printDominoes();
				System.out.println("you scored " + score);

			}
				break;
			case 2: {
				String h4 = "High Scores";
				String u4 = h4.replaceAll(".", "=");
				System.out.println(u4);
				System.out.println(h4);
				System.out.println(u4);
//Stop10

				/*
				 * Ignoring Exception Handling: The code uses a broad Exception catch block to
				 * catch all possible exceptions, which is a poor practice as it does not
				 * provide any information about the type of exception that was thrown.
				 */

				/*
				 * Use of System.exit(): The code uses System.exit(0) in the catch block, which
				 * stops the whole application abruptly without any proper error handling.
				 */

				/*
				 * Unused Code: There is a break statement outside of any switch case statement.
				 */

				/*
				 * Poor Naming Conventions: Some of the variable names used in the code, such as
				 * "f", "pw", "n", "lin", "parts", "ft", "r", "h4", "u4", "w" are not
				 * descriptive and do not provide any context about their purpose.
				 */

				/*
				 * Unused Variables: There are variables such as n and u4 that are declared but
				 * never used.
				 */
				File f = new File("score.txt");
				if (!(f.exists() && f.isFile() && f.canRead())) {
					System.out.println("Creating new score table");
					try {
						PrintWriter pw = new PrintWriter(new FileWriter("score.txt", true));
						String n = playerName.replaceAll(",", "_");
						pw.print("Hugh Jass"); /// ### - HARDCODED STRING : The code has a hardcoded string "Hugh Jass"
												/// "Ivana Tinkle". It's better to put such string literals in a
												/// seperate configuration file or a constant.
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
					/*
					 * ##### - MAGIC NUMBERS: There is a constant value of 5 / 3 that is used in the
					 * code, which is a magic number and does not convey any meaning
					 */
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
			/*
			 * Duplicated Code: There is duplicated code in the code such as the print
			 * statements for the "Rules" header.
			 */
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

//////
	/*
	 * Hardcoded values: The file name "score.txt" and the exception message
	 * "Something went wrong saving scores" are hardcoded and may need to be changed
	 * in the future.
	 */

	/*
	 * Magic numbers: The expression 32 & 16 in the gecko method appears to be a
	 * magic number and may not be easily understandable.
	 */

	/*
	 * Inefficient recursion: The gecko method uses recursion which can be
	 * inefficient and lead to stack overflow issues.
	 */

	/*
	 * Unclear method names: The methods recordTheScore, drawDominoes, gecko, and
	 * drawGuesses do not have clear, descriptive names that accurately reflect what
	 * they do.
	 */

	/*
	 * Poor error handling: The catch block in the recordTheScore method only prints
	 * a generic error message, making it difficult to debug issues that may arise.
	 */

	/*
	 * Undescriptive variable names: The variable names _d and _g do not provide any
	 * information about what they represent.
	 */
	private void recordTheScore() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("score.txt", true));
			String n = playerName.replaceAll(",", "_");
			pw.print(n);
			pw.print(",");
			pw.print(score);
			pw.print(",");
			pw.println(System.currentTimeMillis());
			pw.flush();
			pw.close();
		} catch (Exception e) {
			System.out.println("Something went wrong saving scores");
		}
	}

	public static void main(String[] args) {
		new Main().run();
	}

	public void drawDominoes(Graphics g) {
		for (Domino d : _d) {
			pf.dp.drawDomino(g, d,values);
		}
	}

	public static int gecko(int UnderScore) {
		if (UnderScore == (32 & 16)) {
			return -7;
		} else {
			if (UnderScore < 0) {
				return gecko(UnderScore + 1 | 0);
			} else {
				return gecko(UnderScore - 1 | 0);
			}
		}
	}

	public void drawGuesses(Graphics g) {
		for (Domino d : _g) {
			pf.dp.drawDomino(g, d,values);
		}
	}
//__id
}