package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.util.Random;

import java.util.Scanner;

public class GameServer {
	public static boolean currenttrading = false;
	public static int tradelean = -99999;
	public static boolean currentbidding = false;
	public static boolean currentscan = true;
	public static int chosenint = -99999;
	public static String chosenstring = "zxcvbm,./";
	public static Scanner scan = new Scanner(System.in);
	public static Random rand = new Random();
	public static int double_count = 0;
	public static boolean jail = false;
	public static HashMap<Integer, Properties> hm = new HashMap<Integer, Properties>();
	public static Player bank = new Player(null, 1, null, 1);
	public static int dice;
	public static int dice2;
	public static boolean end = false;
	public static int playercount;
	public static ArrayList<Integer> random2 = new ArrayList<Integer>();
	public static ArrayList<Integer> random3 = new ArrayList<Integer>();
	public static ArrayList<Player> playerlist = new ArrayList<Player>();
	public static ArrayList<Player> bidlist2;

	public static int scanInt() {
		currentscan = true;
		chosenint = -99999;
		while (true) {
			if (chosenint != -99999) {
				break;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return chosenint;
	}

	public static String scanString() {
		currentscan = false;
		chosenstring = "zxcvbm,./";
		while (true) {
			if (!chosenstring.equals("zxcvbm,./")) {
				break;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return chosenstring;
	}

	public static void next() {
		gameturn(playerlist.get(0));
		Collections.rotate(playerlist, -1);
		Serverclass.update();
	}

	public static Player choose(int owner) {
		int n = -999;
		for (int i = 0; i < playerlist.size(); i++) {
			if (playerlist.get(i).num == owner) {
				n = i;
				break;
			}
		}
		return playerlist.get(n);
	}

	public static void gameturn(Player player) {
		Serverclass.update();
		Properties postemp = hm.get(player.position);
		player.turn = true;
		Serverclass.broadcast(player.name + "'s turn. You are in " + postemp.name + " - Position " + player.position
				+ " - Money: $" + player.money + " - Jailed: " + player.jailed + " - Turn: " + player.turn);
		Serverclass.broadcast("\n");
		Serverclass.broadcast(
				"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  ");
		Serverclass.broadcast(
				"               Roll   -   Info   -   Buy   -   Mortgage   -   Houses   -   Trade   -   End          ");
		Serverclass.broadcast(
				"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		Serverclass.broadcast("\n");
		String move = scanString();
		while (!move.equals("end") || player.turn == true) {
			Serverclass.update();
			if (move.equals("roll")) {
				if (player.turn == true) {
					roll(player);
				} else {
					Serverclass.broadcast("You are out of rolls");
				}
			} else if (move.equals("info")) {
				Serverclass.broadcast("\n");
				info(player);
			} else if (move.equals("buy")) {
				Serverclass.broadcast("\n");
				buy(player);
				debtminus(player);
			} else if (move.equals("mortgage")) {
				Serverclass.broadcast("\n");
				mortgage(player);
				debtminus(player);
			} else if (move.equals("houses")) {
				Serverclass.broadcast("\n");
				debtminus(player);
				houses(player);
			} else if (move.equals("trade")) {
				Serverclass.broadcast("\n");
				debtminus(player);
				trade(player);
			} else if (move.equals("money")) {
				Serverclass.broadcast("\n");
				debtminus(player);
				cheat(player);
			} else if (move.equals("turn")) {
				Serverclass.broadcast("\n");
				turn(player);
			} else if (move.equals("buytest")) {
				Serverclass.broadcast("\n");
				buytest(player);
			} else if (move.equals("chance")) {
				Serverclass.broadcast("\n");
				int num = scanInt();
				chancetest(player, num);
			} else if (move.equals("chest")) {
				Serverclass.broadcast("\n");
				int num = scanInt();
				chesttest(player, num);
			} else if (move.equals("jail")) {
				Serverclass.broadcast("\n");
				jail(player);
			} else if (move.equals("inflict")) {
				Serverclass.broadcast("\n");
				dice = 0;
				dice2 = 0;
				inflict(player, 1);
			} else if (move.equals("position")) {
				Serverclass.broadcast("\n");
				int a = scanInt();
				player.position = a;
			}
			postemp = hm.get(player.position);
			Serverclass.broadcast("\n");
			Serverclass.broadcast(player.name + "'s turn. You are in " + postemp.name + " - Position " + player.position
					+ " - Money: $" + player.money + " - Jailed: " + player.jailed + " - Turn: " + player.turn);
			Serverclass.broadcast("\n");
			Serverclass.broadcast(
					"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  ");
			Serverclass.broadcast(
					"               Roll   ~   Info   ~   Buy   ~   Mortgage   ~   Houses   ~   Trade   ~   End          ");
			Serverclass.broadcast(
					"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
			Serverclass.broadcast("\n");
			move = scanString();
		}
		if (player.money < 0) {
			if (player.debt == 0) {
				for (int i = 0; i < player.all.size(); i++) {
					bid(player, 0, player.all.get(i).position);
				}
				player.all.clear();
				player.pp.clear();
			} else {
				Player n = choose(player.num);
				for (int i = 0; i < player.all.size(); i++) {
					n.all.add(player.all.get(i));
					n.pp.get(player.all.get(i).color).add(player.all.get(i));
				}
				player.all.clear();
				player.pp.clear();
			}
			playerlist.remove(player);
			Serverclass.broadcast(player.name + " is out");
		}
		Serverclass.update();
	}

	public static void roll(Player player) {
		Serverclass.update();
		dice = rand.nextInt(6) + 1;
		dice2 = rand.nextInt(6) + 1;
		Serverclass.broadcast(dice + " and " + dice2);
		player.turn = false;
		if (dice == dice2) {
			if (player.jailed == true) {
				player.jailed = false;
				Serverclass.broadcast("You rolled a double, you are free");
			} else if (player.jailed == false) {
				Serverclass.broadcast("You rolled a double, roll again");
				player.turn = true;
				double_count++;
			}
		} else {
			double_count = 0;
		}
		if (double_count == 3) {
			double_count = 0;
			player.jailed = true;
			player.position = 10;
			Serverclass.broadcast("jailtime");
		}
		if (player.jailed == true) {
			if (player.jailcount == 2) {
				Serverclass.broadcast("3 turns are over. You paid $50 to bail out of jail");
				if (player.money < 50) {
					debt(player, 50, bank);
				} else {
					player.money -= 50;
					player.jailed = false;
					player.jailcount = 0;
				}
			} else {
				Serverclass.broadcast("You are in jail. Would you like to pay $50 to bail?");
				Serverclass.broadcast("yes or no");
				String bailean = scanString();
				while (!bailean.equals("yes") && !bailean.equals("no")) {
					Serverclass.broadcast("Input error");
					Serverclass.broadcast("You are in jail. Would you like to pay $50 to bail?");
					Serverclass.broadcast("yes or no");
					bailean = scanString();
				}
				if (bailean.equals("yes")) {
					if (player.money >= 50) {
						player.jailed = false;
					} else {
						Serverclass.broadcast("You lack the required amount of money");
						Serverclass.broadcast("Would you like to mortgage, sell houses, trade to pay for the bail?");
						Serverclass.broadcast("Yes or No");
						String b = "";
						while (!b.equals("yes")) {
							b = scanString();
							while (!b.equals("no")) {
								Serverclass.broadcast("Mortgage or sell houses or trade?");
								String a = scanString();
								if (a.equals("mortgage")) {
									mortgage(player);
								} else if (a.equals("houses")) {
									int n = 0;
									for (Properties i : player.all) {
										n += i.houses;
									}
									if (n == 0) {
										Serverclass.broadcast("You have no houses to sell");
									} else {
										houses(player);
									}
								} else if (a.equals("trade")) {
									trade(player);
								} else {
									Serverclass.broadcast("Stay in jail");
									player.jailcount++;
								}
								Serverclass.broadcast("Would you like to continue mortgaging/selling houses/trading?");
								Serverclass.broadcast("Yes or No");
								b = scanString();
							}
							if (player.money >= 50) {
								Serverclass.broadcast("You have $" + player.money + ". Would you like to bail?");
								b = scanString();
								if (b.equals("yes")) {
									player.money -= 50;
									player.jailed = false;
								} else {
									Serverclass.broadcast("Stay in jail");
									player.jailcount++;
								}
							} else {
								Serverclass.broadcast(
										"You have $" + player.money + " which is lower than what is needed to bail.");
								Serverclass.broadcast("Would you like to continue mortgaging/selling houses/trading?");
								String c = scanString();
								if (c.equals("yes")) {
									b = "";
								} else {
									Serverclass.broadcast("Stay in jail");
									player.jailcount++;
									b = "yes";
								}
							}
						}
					}
				} else {
					Serverclass.broadcast("Stay in jail");
					player.jailcount++;
				}
			}
		}
		if (player.jailed == false) {
			inflict(player, 1);
		}
		Serverclass.update();
	}

	public static void inflict(Player player, int chance) {
		Serverclass.update();
		if ((player.position + dice + dice2 > 39)) {
			player.position = (dice + dice2) - (39 - player.position) - 1;
			player.money += 200;
			Serverclass.broadcast("\nYou passed through Go and received $200");
		} else if (player.position + dice + dice2 < 0) {
			player.position = (40 + player.position) + (dice + dice2);
		} else {
			player.position += dice + dice2;
		}
		Properties temp = hm.get(player.position);
		Serverclass.broadcast("\nYou landed on " + temp.name + " - Position " + temp.position);
		if (temp.type.equals("land") || temp.type.equals("station") || temp.type.equals("utility")) {
			if (temp.owner == 0) {
				Serverclass.broadcast("\nThis land is unowned");
				buy(player);
			} else if (temp.owner == player.num) {
				Serverclass.broadcast("\nYou own this land");
			} else if (temp.mortgaged == true) {
				Serverclass.broadcast("\nThis land is mortgaged");
			} else {
				Player i = choose(temp.owner);
				Serverclass.broadcast("Player " + i.name + " owns this land");
				if (temp.type.equals("utility")) {
					if ((temp.payup(i) * (dice + dice2)) > player.money) { // made a change
						debt(player, (temp.payup(i) * (dice + dice2)), i);
					} else {
						Serverclass.broadcast("You paid " + i.name + " $" + (temp.payup(i) * (dice + dice2)));
						player.money -= (temp.payup(i) * (dice + dice2));
						i.money += (temp.payup(i) * (dice + dice2));
					}
				} else {
					if (temp.payup(i) * chance > player.money) { // made more change
						debt(player, temp.payup(i), i);
					} else {
						// Serverclass.broadcast("You paid " + i.name + " $" + (temp.payup(i) * (dice +
						// dice2)));
						Serverclass.broadcast("You paid " + i.name + " $" + (temp.payup(i)) * chance);
						player.money -= temp.payup(i) * chance; // change
						i.money += temp.payup(i) * chance;
					}

				}
			}
		} else if (temp.type.equals("tax")) {
			Serverclass.broadcast("You lost $" + temp.tax);
			if (temp.tax > player.money) {
				debt(player, temp.tax, bank);
			} else {
				player.money -= temp.tax;
			}
		} else if (temp.type.equals("gojail")) {
			player.position = 10;
			player.jailed = true;
			Serverclass.broadcast("You got sent to Jail");
		} else if (temp.type.equals("card1")) {
			chest(player, -1);
		} else if (temp.type.equals("card2")) {
			chance(player, -1);
		}
		Serverclass.update();
	}

	public static void debt(Player player, int pay, Player i) {
		Serverclass.update();
		if (pay > player.money) {
			player.curmoney = player.money;
			player.curdebt = (-1 * (pay - player.curmoney));
			Serverclass.broadcast("\n");
			Serverclass.broadcast("You have to pay $" + pay + " but only have $" + player.curmoney + " currently");
			Serverclass.broadcast("You are $" + player.curdebt + " in debt to " + i.name);
			player.money -= pay;
			i.money += player.curmoney;
			player.debt = i.num;
		}
		Serverclass.update();
	}

	public static void debtminus(Player player) {
		Serverclass.update();
		Player n = choose(player.num);
		if (player.curdebt < 0) {
			if (player.money > player.curdebt) {
				int i = player.money - player.curdebt;
				if (i > (-1 * player.curdebt)) {
					Serverclass.broadcast("You are paying the " + (-1 * player.curdebt) + " that you owe to " + n.name);
					n.money += (-1 * player.curdebt);
					player.money -= (-1 * player.curdebt);
					player.curdebt = 0;
				} else {
					Serverclass.broadcast("You are paying " + n.name + " the $" + i + " that you gained");
					player.money = player.curdebt;
					n.money += i;
					player.curdebt += i;
				}
			}
		}
		Serverclass.update();
	}

	public static void bid(Player player, int cost, int land) {
		Serverclass.update();
		currentbidding = true;
		int winner = -1;
		player.bid = false;
		bidlist2 = new ArrayList<Player>();
		for (Player x : playerlist) {
			if (x.bid == true) {
				bidlist2.add(x);
			}
		}
		if (bidlist2.size() > 1) {
			while (bidlist2.size() > 1) {
				int size = bidlist2.size();
				for (int i = 0; i < size; i++) {
					Serverclass.update();
					if (bidlist2.get(i).bid == true) {
						bidlist2.get(i).bidtalk = true;
						Serverclass.broadcast(
								bidlist2.get(i).name + ", how much would you like to bid? Minimum: " + (cost + 1));
						int bidlean = scanInt();
						bidlist2.get(i).bidtalk = false;
						if (bidlean > cost) {
							cost = bidlean;
							Serverclass.broadcast("You have increased the bid to $" + cost);
							winner = bidlist2.get(i).num;
						} else {
							Serverclass.broadcast("You have lost the bid");
							bidlist2.get(i).bid = false;
							bidlist2.clear();
							for (Player x : playerlist) {
								if (x.bid == true) {
									bidlist2.add(x);
								}
							}
//							if (bidlist2.size() == 1) {
//								break;
//							}
						}
					}
				}
			}
		} else {
			Serverclass.broadcast(
					bidlist2.get(0).name + " - How much would you like to bid for this property? Minimum: " + cost);
			bidlist2.get(0).bidtalk = true;
			int a = scanInt();
			if (a < cost) {
			} else {
				winner = bidlist2.get(0).num;
			}
		}
		bidlist2.get(0).bidtalk = false;
		if (winner == -1) {
			Serverclass.broadcast("Nobody bidded for this property");
			hm.get(land).owner = 0;
		} else {
			Player n = choose(winner);
			hm.get(land).owner = n.num;
			hm.get(land).ownername = n.name;
			n.money -= cost;
			n.pp.get(hm.get(land).color).add(hm.get(land));
			n.all.add(hm.get(land));
			Serverclass.broadcast("The winner is " + n.name + " who paid $" + cost + " for " + hm.get(land).name);
		}
		for (Player i : playerlist) {
			i.bid = true;
		}
		currentbidding = false;
		Serverclass.update();
	}

	public static void buy(Player player) {
		Serverclass.update();
		if (player.turn == false || double_count > 0) {
			Properties buytemp = hm.get(player.position);
			Serverclass.broadcast("You have $" + player.money);
			Serverclass.broadcast("\n");
			Serverclass.broadcast(buytemp.toString());
			if (buytemp.owner == 0 && (buytemp.type.equals("land") || buytemp.type.equals("utility")
					|| buytemp.type.equals("station"))) {
				Serverclass.broadcast("\nWould you like to buy " + buytemp.name + "?");
				Serverclass.broadcast("Yes or No");
				String buylean = scanString();
				while (!buylean.equals("yes") && !buylean.equals("no")) {
					Serverclass.broadcast("Input error");
					Serverclass.broadcast("\nWould you like to buy " + buytemp.name + "?");
					Serverclass.broadcast("Yes or No");
					buylean = scanString();
				}
				if (buylean.equals("yes")) {
					if (player.money >= buytemp.cost) {
						player.money -= buytemp.cost;
						buytemp.owner = player.num;
						buytemp.ownername = player.name;
						player.pp.get(buytemp.color).add(buytemp);
						player.all.add(buytemp);
						Serverclass.broadcast("You bought " + buytemp.name + ". You have $" + player.money + " left");
					} else {
						Serverclass.broadcast("You lack the required amount of money");
					}
				} else {
					Serverclass.broadcast("The other players can start bidding for this property");
					bid(player, buytemp.cost, buytemp.position);
				}
			} else {
				Serverclass.broadcast("You can't buy this property");
			}
		} else {
			Serverclass.broadcast("You have to roll before you buy");
			return;
		}
		Serverclass.update();
	}

	public static void info(Player player) {
		Serverclass.broadcast("1. Current Property - 2. Specific Property - 3. Every Property - 4. Player info");
		int intfo = scanInt();
		while (intfo < 1 || intfo > 4) {
			Serverclass.broadcast("Input error");
			Serverclass.broadcast("1. Current Property - 2. Specific Property - 3. Every Property - 4. Player info");
			intfo = scanInt();
		}
		if (intfo == 1) {
			Serverclass.broadcast(hm.get(player.position).toString());
		} else if (intfo == 2) {
			Serverclass.broadcast("Enter property number");
			int num = scanInt();
			while (num < 0 || num > 39) {
				Serverclass.broadcast("Input Error");
				Serverclass.broadcast("Enter property number");
				num = scanInt();
			}
			Serverclass.broadcast(hm.get(num).toString());
		} else if (intfo == 3) {
			for (int i = 0; i < 40; i++) {
				Serverclass.broadcast("\n");
				Serverclass.broadcast(hm.get(i).toString());
			}
		} else {
			for (int i = 0; i < playerlist.size(); i++) {
				Serverclass.broadcast((i + 1) + ". " + playerlist.get(i).name);
			}
			Serverclass.broadcast("Choose a player");
			int num = scanInt();
			while (num < 1 || num > playerlist.size()) {
				Serverclass.broadcast("Input Error");
				Serverclass.broadcast("Choose a player");
				num = scanInt();
			}
			if (num == 1) {
				Serverclass.broadcast(playerlist.get(0).toString());
			} else if (num == 2) {
				Serverclass.broadcast(playerlist.get(1).toString());
			} else if (num == 3) {
				Serverclass.broadcast(playerlist.get(2).toString());
			} else if (num == 4) {
				Serverclass.broadcast(playerlist.get(3).toString());
			} else if (num == 5) {
				Serverclass.broadcast(playerlist.get(4).toString());
			} else {
				Serverclass.broadcast(playerlist.get(5).toString());
			}
		}
	}

	public static void mortgage(Player player) {
		Serverclass.update();
		if (player.all.size() > 0) {
			for (int i = 0; i < player.all.size(); i++) {
				Serverclass.broadcast((i + 1) + ". " + player.all.get(i).name + " - Mortgage price: $"
						+ player.all.get(i).mortgage + " - Mortgage status: " + player.all.get(i).mortgaged);
			}
			Serverclass.broadcast("Which property would you like to invert mortgage status? Type 666 to exit");
			int mortlean = scanInt();
			while ((mortlean != 666 && mortlean > player.all.size()) || mortlean <= 0) {
				Serverclass.broadcast("Invalid input, please try again");
				Serverclass.broadcast("Which property would you like to invert mortgage status? Type 666 to exit");
				mortlean = scanInt();
			}
			if (mortlean == 666) {
				return;
			} else if (player.all.get(mortlean - 1).mortgaged == false) {
				player.money += player.all.get(mortlean - 1).mortgage;
				player.all.get(mortlean - 1).mortgaged = true;
				Serverclass.broadcast("You mortgaged " + player.all.get(mortlean - 1).name + " and gained $"
						+ player.all.get(mortlean - 1).mortgage);
			} else {
				player.money -= player.all.get(mortlean - 1).mortgage;
				player.all.get(mortlean - 1).mortgaged = false;
				Serverclass.broadcast("You unmortgaged " + player.all.get(mortlean - 1).name + " and lost $"
						+ player.all.get(mortlean - 1).mortgage);
			}
		} else {
			Serverclass.broadcast("You have no property mortgage");
		}
		Serverclass.update();
	}

	public static void houses(Player player) {
		Serverclass.update();
		Serverclass.broadcast("Would you like build or sell houses");
		String b = scanString();
		if (b.equals("sell")) {
			int houseCount = 0;
			ArrayList<Properties> sell = new ArrayList<Properties>();
			for (int i = 0; i < player.all.size(); i++) {
				if (player.all.get(i).houses > 0) {
					houseCount += player.all.get(i).houses;
					sell.add(player.all.get(i));
					Serverclass.broadcast(
							(i + 1) + ". " + player.all.get(i).name + " : " + player.all.get(i).houses + " houses");
				}
			}
			if (houseCount == 0) {
				Serverclass.broadcast("You have no houses to sell");
				return;
			}
			Serverclass.broadcast("Which property do you want to sell houses on?");
			int c = scanInt();
			while (c > (sell.size()) || c < 1) {
				Serverclass.broadcast("Input error, please try again");
				c = scanInt();
			}
			Serverclass.broadcast("How many houses would you like to sell?");
			int d = scanInt();
			while (d > sell.get(c - 1).houses || d < 1) {
				Serverclass.broadcast("Input error, please try again");
				d = scanInt();
			}
			sell.get(c - 1).houses -= d;
			player.money += (sell.get(c - 1).house * d * 0.5);
		} else {
			ArrayList<String> buildable = new ArrayList<String>();
			if (player.pp.get("brown").size() == 2) {
				buildable.add("brown");
			}
			if (player.pp.get("navy").size() == 2) {
				buildable.add("navy");
			}
			if (player.pp.get("sky").size() == 3) {
				buildable.add("sky");
			}
			if (player.pp.get("pink").size() == 3) {
				buildable.add("pink");
			}
			if (player.pp.get("orange").size() == 3) {
				buildable.add("orange");
			}
			if (player.pp.get("red").size() == 3) {
				buildable.add("red");
			}
			if (player.pp.get("yellow").size() == 3) {
				buildable.add("yellow");
			}
			if (player.pp.get("green").size() == 3) {
				buildable.add("green");
			}
			if (buildable.size() > 0) {
				for (int i = 0; i < buildable.size(); i++) {
					Serverclass.broadcast((i + 1) + ". " + buildable.get(i).toUpperCase());
				}
				Serverclass.broadcast("Which color would you like to build on?");
				int builean = scanInt();
				ArrayList<Properties> a = player.pp.get(buildable.get(builean - 1));
				if (a.get(0).house > player.money) {
					Serverclass.broadcast("You have $" + player.money + " and a house here costs $" + a.get(0).house);
					Serverclass.broadcast("You don't have enough money to buy a house here");
					return;
				}
				for (int i = 0; i < a.size(); i++) {
					if (a.get(i).houses < 5) {
						Serverclass.broadcast((i + 1) + ". " + a.get(i));
					}
				}
				Serverclass.broadcast("Which property would you like to build on?");
				int builean2 = scanInt();
				Serverclass.broadcast("How many houses would you like to buy?");
				int builean3 = scanInt();
				while (builean3 < 0 || (builean3 + a.get(builean2 - 1).houses) > 5) {
					Serverclass.broadcast("Input error, please try again");
					builean3 = scanInt();
				}
				a.get(builean2 - 1).houses += builean3;
				player.money -= (a.get(builean2 - 1).house * builean3);
				Serverclass.broadcast("You built " + builean3 + " houses on " + a.get(builean2 - 1).name + "and lost $"
						+ (a.get(builean2 - 1).house * builean3));
			} else {
				Serverclass.broadcast("You need to own all properties of same color to build houses");
			}
		}
		Serverclass.update();
	}

	public static void trade(Player player) {
		Serverclass.update();
		Serverclass.broadcast("Who would you like to trade with?");
		for (int i = 0; i < playerlist.size(); i++) {
			Serverclass.broadcast("Player " + (playerlist.get(i).num) + ": " + playerlist.get(i).name);
		}
		tradelean = scanInt();
		Player replace = choose((tradelean));
		while (replace.num == player.num) {
			Serverclass.broadcast("You can't trade with yourself, please choose someone else");
			Serverclass.broadcast("Who would you like to trade with?");
			for (int i = 0; i < playerlist.size(); i++) {
				Serverclass.broadcast("Player " + (playerlist.get(i).num) + ": " + playerlist.get(i).name);
			}
			tradelean = scanInt();
			replace = choose((tradelean));
		}
		int selectDigit = 0;
		if (player.all.size() != 0) {
			for (int i = 0; i < player.all.size(); i++) {
				Serverclass.broadcast((i + 1) + ". \n" + player.all.get(i));
			}
			Serverclass.broadcast(player.name + ": Which property would you like to trade? Type 0 for none");
			selectDigit = scanInt();
		} else {
			Serverclass.broadcast("You have no properties to trade");
		}
		if (selectDigit == 0) {
		} else {
			Properties a = player.all.get(selectDigit - 1);
			a.owner = replace.num;
			a.ownername = replace.name;
			replace.all.add(a);
			replace.pp.get(a.color).add(a);
			player.all.remove(a);
			player.pp.get(a.color).remove(a);
			Serverclass.broadcast("You traded away " + a.name);
		}
		Serverclass.broadcast("How much money would you like to trade? You have " + player.money);
		selectDigit = scanInt();
		while (selectDigit < 0 || selectDigit > player.money) {
			Serverclass.broadcast("Input error");
			Serverclass.broadcast("How much money would you like to trade? You have " + player.money);
			selectDigit = scanInt();
		}
		replace.money += selectDigit;
		player.money -= selectDigit;
		Serverclass.broadcast("You traded $" + selectDigit);
		if (replace.all.size() != 0) {
			for (int i = 0; i < replace.all.size(); i++) {
				Serverclass.broadcast((i + 1) + ". " + replace.all.get(i));
			}
			currenttrading = true;
			Serverclass.broadcast(replace.name + ": Which property would you like to trade? Type 0 for none");
			selectDigit = scanInt();
		} else {
			selectDigit = 0;
			Serverclass.broadcast("You have no properties to trade with");
		}
		if (selectDigit == 0) {
		} else {
			Properties a = replace.all.get(selectDigit - 1);
			a.owner = replace.num;
			a.ownername = replace.name;
			player.all.add(a);
			player.pp.get(a.color).add(a);
			replace.all.remove(a);
			replace.pp.get(a.color).remove(a);
			Serverclass.broadcast("You traded away " + a.name);
		}
		Serverclass.broadcast("How much money would you like to trade? You have " + replace.money);
		selectDigit = scanInt();
		while (selectDigit < 0 || selectDigit > replace.money) {
			Serverclass.broadcast("Input error");
			Serverclass.broadcast("How much money would you like to trade? You have " + replace.money);
			selectDigit = scanInt();
		}
		player.money += selectDigit;
		replace.money -= selectDigit;
		Serverclass.broadcast("You traded $" + selectDigit);
		currenttrading = false;
		tradelean = -99999;
		Serverclass.update();
	}

	public static void cheat(Player player) {
		int a = scanInt();
		player.money = a;
	}

	public static void turn(Player player) {
		player.turn = true;
	}

	public static void buytest(Player player) {
		int a = scanInt();
		player.all.add(hm.get(a));
		player.pp.get(hm.get(a).color).add(hm.get(a));
		hm.get(a).owner = player.num;
	}

	public static void jail(Player player) {
		if (player.jailed == false) {
			player.position = 10;
			player.jailed = true;
		} else {
			player.jailed = false;
		}
	}

	public static void chancetest(Player player, int num) {
		chance(player, num);
	}

	public static void chesttest(Player player, int num) {
		chest(player, num);
	}

	public static void chance(Player player, int num) {
		Serverclass.update();
		Serverclass.broadcast("\n");
		Serverclass.broadcast("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		Serverclass.broadcast("Chance:");
		int random = random2.get(0);
		if (num != -1) {
			random = num;
		}
		random2.add(0, random2.get(15));
		random2.remove(16);
		if (random == 1) {
			Serverclass.broadcast("Advance to go, collect $200");
			player.position = 0;
			player.money += 200;
			Serverclass.broadcast("You collected $200");
		} else if (random == 2) {
			Serverclass.broadcast("Advance to Tralfalgar Square, if you pass Go, collect $200");
			if (player.position >= 24) {
				player.money += 200;
				Serverclass.broadcast("You collected $200");
			}
			player.position = 24;
			dice = 0;
			dice2 = 0;
			inflict(player, 1);
		} else if (random == 3) {
			Serverclass.broadcast("Advance to Pall Mall, if you pass Go, collect $200");
			if (player.position >= 11) {
				player.money += 200;
				Serverclass.broadcast("You collected $200");
			}
			dice = 0;
			dice2 = 0;
			player.position = 11;
			inflict(player, 1);
		} else if (random == 4) {
			Serverclass.broadcast("You have won a crossword competition, collect $200");
			player.money += 100;
			Serverclass.broadcast("You collected $200");
		} else if (random == 5) {
			Serverclass.broadcast("Your building and loan matures, collect $150");
			player.money += 150;
			Serverclass.broadcast("You collected $150");
		} else if (random == 6) {
			Serverclass.broadcast("You have been elected Chairman of the Board. Pay each player $50");
			if (player.money < ((playerlist.size() - 1) * 50)) {
				debt(player, ((playerlist.size() - 1) * 50), bank);
			} else {
				for (Player i : playerlist) {
					player.money -= 50;
					Serverclass.broadcast("You lost $50");
					i.money += 50;
					Serverclass.broadcast(i.name + " collected $50");
				}
			}
		} else if (random == 7) {
			Serverclass.broadcast("Advance to Boardwalk");
			player.position = 39;
			dice = 0;
			dice2 = 0;
			inflict(player, 1);
		} else if (random == 8) {
			Serverclass.broadcast("Advance to Kings Cross Station, if you pass Go, collect $200");
			if (player.position >= 5) {
				player.money += 200;
				Serverclass.broadcast("You collected $200");
			}
			dice = 0;
			dice2 = 0;
			player.position = 5;
			inflict(player, 1);
		} else if (random == 9) {
			Serverclass.broadcast("Pay poor tax of $15");
			player.money -= 15;
		} else if (random == 10) {
			Serverclass.broadcast("Go to jail, do not collect $200");
			player.position = 10;
			player.jailed = true;
			Serverclass.broadcast("You are jailed");
		} else if (random == 11) {
			Serverclass.broadcast("Get out of jail free card, aka collect $50");
			player.money += 50;
			Serverclass.broadcast("You collected $50");
		} else if (random == 12) {
			Serverclass.broadcast("For each house pay $25, for each hotel pay $100");
			int repair = 0;
			int repair2 = 0;
			for (int i = 0; i < player.all.size(); i++) {
				if (player.all.get(i).houses == 5) {
					repair2++;
				} else {
					repair += player.all.get(i).houses;
				}
			}
			Serverclass.broadcast("You own " + repair + " houses and " + repair2 + " hotels. You have to pay $"
					+ ((repair * 25) + (repair2 * 100)));
			if (player.money < ((repair * 25) + (repair2 * 100))) {
				debt(player, ((repair * 25) + (repair2 * 100)), bank);
			} else {
				player.money -= ((repair * 25) + (repair2 * 100));
			}
		} else if (random == 13) {
			Serverclass.broadcast("Bank pays you dividend of $50");
			player.money += 50;
			Serverclass.broadcast("You collected $50");
		} else if (random == 14) {
			Serverclass.broadcast("Go back 3 spaces");
			dice = -3;
			dice2 = 0;
			inflict(player, 1);
			Serverclass.broadcast("You got sent back 3 spaces");
		} else if (random == 15) {
			Serverclass.broadcast("Advance to the nearest railroad. If unowned, you may buy it from the Bank");
			Serverclass.broadcast("If owned, pay owner twice the rental to which they are otherwise entitled");
			if (player.position < 5) {
				player.position = 5;
				Serverclass.broadcast("You travelled to " + hm.get(5).name);
				dice = 0;
				dice2 = 0;
				inflict(player, 2);
			} else if (player.position >= 35) {
				player.position = 5;
				dice = 0;
				dice2 = 0;
				player.money += 200;
				Serverclass.broadcast("You passed Go, collect $200");
				Serverclass.broadcast("You travelled to " + hm.get(5).name);
				inflict(player, 2);
			} else if (player.position < 15 || player.position >= 5) {
				player.position = 15;
				Serverclass.broadcast("You travelled to " + hm.get(15).name);
				dice = 0;
				dice2 = 0;
				inflict(player, 2);
			} else if (player.position < 25 || player.position >= 15) {
				player.position = 25;
				Serverclass.broadcast("You travelled to " + hm.get(25).name);
				dice = 0;
				dice2 = 0;
				inflict(player, 2);
			} else if (player.position < 35 || player.position >= 25) {
				player.position = 35;
				Serverclass.broadcast("You travelled to " + hm.get(35).name);
				dice = 0;
				dice2 = 0;
				inflict(player, 2);
			}
		} else if (random == 16) {
			Serverclass.broadcast("Advance to the nearest utility. If unowned, you may buy it from the Bank");
			Serverclass.broadcast("If owned, pay owner 10 times their dice roll");
			if (player.position < 12) {
				player.position = 12;
				Serverclass.broadcast("You travelled to " + hm.get(12).name);
			} else if (player.position >= 28) {
				player.position = 12;
				Serverclass.broadcast("You passed Go, collect $200");
				Serverclass.broadcast("You travelled to " + hm.get(12).name);
				player.money += 200;
			} else {
				player.position = 28;
				Serverclass.broadcast("You travelled to " + hm.get(28).name);
			}
			Player replace = choose(hm.get(player.position).owner);
			int a = rand.nextInt(12) + 1;
			if (hm.get(player.position).owner != 0 && hm.get(player.position).owner != player.num) {
				Serverclass.broadcast(replace.name + " owns this property");
				Serverclass.broadcast("You have to roll the dice and pay 10 times the dice roll");
				// scan.nextLine();
				Serverclass.broadcast("We rolled the dice for you. You rolled a " + a);
				player.money -= (a * 10);
				replace.money += (a * 10);
			} else {
				dice = 0;
				dice2 = 0;
				inflict(player, 1);
			}
		}
		Serverclass.update();
		Serverclass.broadcast("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
	}

	public static void chest(Player player, int number) {
		Serverclass.update();
		Serverclass.broadcast("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		Serverclass.broadcast("\nCommunity Chest:");
		int num = random3.get(0);
		if (number != -1) {
			num = number;
		}
		random3.add(0, random3.get(15));
		random3.remove(16);
		if (num == 1) {
			Serverclass.broadcast("Advance to Go, collect $200");
			player.position = 0;
			player.money += 200;
			Serverclass.broadcast("You collected $200");
		} else if (num == 2) {
			Serverclass.broadcast("Bank error in your favour, collect $200");
			player.money += 200;
			Serverclass.broadcast("You collected $200");
		} else if (num == 3) {
			Serverclass.broadcast("Doctor's fee, pay $50");
			if (player.money < 50) {
				debt(player, 50, bank);
			} else {
				player.money -= 50;
				Serverclass.broadcast("You lost $50");
			}
		} else if (num == 4) {
			Serverclass.broadcast("From sale of stock you got $45");
			player.money += 45;
			Serverclass.broadcast("You collected $45");
		} else if (num == 5) {
			Serverclass.broadcast("Get out of jail free card, which is basically $50. I'm too lazy");
			player.money += 50;
			Serverclass.broadcast("You collected $50");
		} else if (num == 6) {
			Serverclass.broadcast("Go to jail, do not pass go, do not collect $200");
			player.position = 10;
			player.jailed = true;
			Serverclass.broadcast("You have been jailed");
		} else if (num == 7) {
			Serverclass.broadcast("Grand Opera Night. Collect $50 from each player for opening seats night");
			for (Player i : playerlist) {
				if (i != player) {
					if (i.money < 50) {
						debt(i, 50, player);
					} else {
						i.money -= 50;
						Serverclass.broadcast(i.name + " lost $50");
						player.money += 50;
						Serverclass.broadcast("You collected $50");
					}
				}
			}
		} else if (num == 8) {
			Serverclass.broadcast("Holiday fund matures. Receive $100");
			player.money += 100;
			Serverclass.broadcast("You collected $100");
		} else if (num == 9) {
			Serverclass.broadcast("Income tax refund, collect $20");
			player.money += 20;
			Serverclass.broadcast("You collected $20");
		} else if (num == 10) {
			Serverclass.broadcast("Life insurance matures, collect $100");
			player.money += 100;
			Serverclass.broadcast("You collected $100");
		} else if (num == 11) {
			Serverclass.broadcast("Hospital fees, pay $100");
			if (player.money < 100) {
				debt(player, 100, bank);
			} else {
				player.money -= 100;
				Serverclass.broadcast("You lost $100");
			}
		} else if (num == 12) {
			Serverclass.broadcast("School fees, pay $150");
			if (player.money < 150) {
				debt(player, 150, bank);
			} else {
				player.money -= 150;
				Serverclass.broadcast("You lost $150");
			}
		} else if (num == 13) {
			Serverclass.broadcast("Receive  $25 consultancy fee");
			player.money += 25;
			Serverclass.broadcast("You collected $25");
		} else if (num == 14) {
			Serverclass.broadcast(
					"You are assessed for street repairs. Pay $40 for each house and $115 for each hotel you own.");
			int a = 0;
			int b = 0;
			for (int i = 0; i < player.all.size(); i++) {
				if (player.all.get(i).houses == 5) {
					b += 1;
				} else {
					a += player.all.get(i).houses;
				}
			}
			Serverclass.broadcast("You own " + a + " houses and " + b + " hotels. You paid $" + ((b * 115) + (a * 40)));
			if (player.money < ((b * 115) + (a * 40))) {
				debt(player, ((b * 115) + (a * 40)), bank);
			} else {
				player.money -= ((b * 115) + (a * 40));
			}
		} else if (num == 15) {
			Serverclass.broadcast("You have won second prize in a beauty contest, collect $10");
			player.money += 10;
			Serverclass.broadcast("You collected $10");
		} else if (num == 16) {
			Serverclass.broadcast("You inherit $100");
			player.money += 100;
			Serverclass.broadcast("You collected $100");
		}
		Serverclass.update();
		Serverclass.broadcast("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
	}

	public static void main() {

		bank.name = "Bank";
//		p1.name = "Player 1";
//		p2.name = "Player 2";
//		p3.name = "Player 3";
//		p4.name = "Player 4";
//		p5.name = "Player 5";
//		p6.name = "Player 6";

		Properties Go = new Properties();

		Go.name = "Go";
		Go.position = 0;
		Go.type = "go";

		Properties Kent = new Properties();
		Kent.name = "Old Kent Road";
		Kent.position = 1;
		Kent.type = "land";
		Kent.cost = 60;
		Kent.mortgage = 30;
		Kent.house = 50;
		Kent.rent = 2;
		Kent.rent1 = 10;
		Kent.rent2 = 30;
		Kent.rent3 = 90;
		Kent.rent4 = 160;
		Kent.rent5 = 250;
		Kent.color = "brown";

		Properties Chest1 = new Properties();
		Chest1.name = "Community Chest";
		Chest1.type = "card1";
		Chest1.position = 2;

		Properties Chapel = new Properties();
		Chapel.name = "White Chapel Road";
		Chapel.position = 3;
		Chapel.type = "land";
		Chapel.cost = 60;
		Chapel.mortgage = 30;
		Chapel.house = 50;
		Chapel.rent = 4;
		Chapel.rent1 = 20;
		Chapel.rent2 = 60;
		Chapel.rent3 = 180;
		Chapel.rent4 = 360;
		Chapel.rent5 = 450;
		Chapel.color = "brown";

		Properties Tax1 = new Properties();
		Tax1.name = "Income Tax";
		Tax1.type = "tax";
		Tax1.position = 4;
		Tax1.tax = 200;

		Properties Kings = new Properties();
		Kings.name = "Kings Cross Station";
		Kings.position = 5;
		Kings.type = "station";
		Kings.cost = 200;
		Kings.mortgage = 100;
		Kings.rent = 25;
		Kings.rent1 = 50;
		Kings.rent2 = 100;
		Kings.rent3 = 200;
		Kings.color = "station";

		Properties Angel = new Properties();
		Angel.name = "The Angel Islington";
		Angel.position = 6;
		Angel.type = "land";
		Angel.cost = 100;
		Angel.mortgage = 50;
		Angel.house = 50;
		Angel.rent = 6;
		Angel.rent1 = 30;
		Angel.rent2 = 90;
		Angel.rent3 = 270;
		Angel.rent4 = 400;
		Angel.rent5 = 550;
		Angel.color = "sky";

		Properties Chance1 = new Properties();
		Chance1.name = "Chance";
		Chance1.type = "card2";
		Chance1.position = 7;

		Properties Euston = new Properties();
		Euston.name = "Euston Road";
		Euston.position = 8;
		Euston.type = "land";
		Euston.cost = 100;
		Euston.mortgage = 50;
		Euston.house = 50;
		Euston.rent = 4;
		Euston.rent1 = 30;
		Euston.rent2 = 90;
		Euston.rent3 = 270;
		Euston.rent4 = 400;
		Euston.rent5 = 550;
		Euston.color = "sky";

		Properties Penton = new Properties();
		Penton.name = "Pentonville Road ";
		Penton.position = 9;
		Penton.type = "land";
		Penton.cost = 120;
		Penton.mortgage = 60;
		Penton.house = 50;
		Penton.rent = 8;
		Penton.rent1 = 40;
		Penton.rent2 = 100;
		Penton.rent3 = 300;
		Penton.rent4 = 450;
		Penton.rent5 = 600;
		Penton.color = "sky";

		Properties inJail = new Properties();
		inJail.name = "In Jail/Just Visiting";
		inJail.type = "jail";
		inJail.position = 10;

		Properties Pall = new Properties();
		Pall.name = "Pall Mall";
		Pall.position = 11;
		Pall.type = "land";
		Pall.cost = 140;
		Pall.mortgage = 70;
		Pall.house = 100;
		Pall.rent = 10;
		Pall.rent1 = 50;
		Pall.rent2 = 150;
		Pall.rent3 = 450;
		Pall.rent4 = 625;
		Pall.rent5 = 750;
		Pall.color = "pink";

		Properties Electric = new Properties();
		Electric.name = "Electric Company ";
		Electric.position = 12;
		Electric.type = "utility";
		Electric.cost = 150;
		Electric.mortgage = 75;
		Electric.rent = 4;
		Electric.rent1 = 10;
		Electric.color = "utility";

		Properties Whitehall = new Properties();
		Whitehall.name = "Whitehall";
		Whitehall.position = 13;
		Whitehall.type = "land";
		Whitehall.cost = 140;
		Whitehall.mortgage = 70;
		Whitehall.house = 100;
		Whitehall.rent = 10;
		Whitehall.rent1 = 50;
		Whitehall.rent2 = 150;
		Whitehall.rent3 = 450;
		Whitehall.rent4 = 625;
		Whitehall.rent5 = 750;
		Whitehall.color = "pink";

		Properties North = new Properties();
		North.name = "Northumberland Avenue";
		North.position = 14;
		North.type = "land";
		North.cost = 160;
		North.mortgage = 80;
		North.house = 100;
		North.rent = 12;
		North.rent1 = 180;
		North.rent2 = 180;
		North.rent3 = 500;
		North.rent4 = 700;
		North.rent5 = 900;
		North.color = "pink";

		Properties Mary = new Properties();
		Mary.name = "Marylebone Station ";
		Mary.position = 15;
		Mary.type = "station";
		Mary.cost = 200;
		Mary.mortgage = 100;
		Mary.rent = 25;
		Mary.rent1 = 50;
		Mary.rent2 = 100;
		Mary.rent3 = 200;
		Mary.color = "station";

		Properties Bow = new Properties();
		Bow.name = "Bow Street ";
		Bow.position = 16;
		Bow.type = "land";
		Bow.cost = 180;
		Bow.mortgage = 90;
		Bow.house = 100;
		Bow.rent = 14;
		Bow.rent1 = 70;
		Bow.rent2 = 200;
		Bow.rent3 = 550;
		Bow.rent4 = 750;
		Bow.rent5 = 950;
		Bow.color = "orange";

		Properties Chest2 = new Properties();
		Chest2.name = "Chest";
		Chest2.type = "card1";
		Chest2.position = 17;

		Properties Marl = new Properties();
		Marl.name = "Marlborough Street ";
		Marl.position = 18;
		Marl.type = "land";
		Marl.cost = 180;
		Marl.mortgage = 90;
		Marl.house = 100;
		Marl.rent = 14;
		Marl.rent1 = 70;
		Marl.rent2 = 200;
		Marl.rent3 = 550;
		Marl.rent4 = 750;
		Marl.rent5 = 950;
		Marl.color = "orange";

		Properties Vine = new Properties();
		Vine.name = "Vine Street";
		Vine.position = 19;
		Vine.type = "land";
		Vine.cost = 200;
		Vine.mortgage = 100;
		Vine.house = 100;
		Vine.rent = 16;
		Vine.rent1 = 80;
		Vine.rent2 = 220;
		Vine.rent3 = 600;
		Vine.rent4 = 800;
		Vine.rent5 = 1000;
		Vine.color = "orange";

		Properties Parking = new Properties();
		Parking.name = "Free Parking";
		Parking.type = "parking";
		Parking.position = 20;

		Properties Strand = new Properties();
		Strand.name = "Strand ";
		Strand.position = 21;
		Strand.type = "land";
		Strand.cost = 220;
		Strand.mortgage = 110;
		Strand.house = 150;
		Strand.rent = 18;
		Strand.rent1 = 90;
		Strand.rent2 = 250;
		Strand.rent3 = 700;
		Strand.rent4 = 875;
		Strand.rent5 = 1050;
		Strand.color = "red";

		Properties Chance2 = new Properties();
		Chance2.name = "Chance";
		Chance2.type = "card2";
		Chance2.position = 22;

		Properties Fleet = new Properties();
		Fleet.name = "Fleet Street ";
		Fleet.position = 23;
		Fleet.type = "land";
		Fleet.cost = 220;
		Fleet.mortgage = 110;
		Fleet.house = 150;
		Fleet.rent = 18;
		Fleet.rent1 = 90;
		Fleet.rent2 = 250;
		Fleet.rent3 = 700;
		Fleet.rent4 = 875;
		Fleet.rent5 = 1050;
		Fleet.color = "red";

		Properties Trafalgar = new Properties();
		Trafalgar.name = "Trafalgar Square ";
		Trafalgar.position = 24;
		Trafalgar.type = "land";
		Trafalgar.cost = 240;
		Trafalgar.mortgage = 120;
		Trafalgar.house = 150;
		Trafalgar.rent = 20;
		Trafalgar.rent1 = 100;
		Trafalgar.rent2 = 300;
		Trafalgar.rent3 = 750;
		Trafalgar.rent4 = 925;
		Trafalgar.rent5 = 1100;
		Trafalgar.color = "red";

		Properties Fenchurch = new Properties();
		Fenchurch.name = "Fenchurch St. Station";
		Fenchurch.position = 25;
		Fenchurch.type = "station";
		Fenchurch.cost = 200;
		Fenchurch.mortgage = 100;
		Fenchurch.rent = 25;
		Fenchurch.rent1 = 50;
		Fenchurch.rent2 = 100;
		Fenchurch.rent3 = 200;
		Fenchurch.color = "station";

		Properties Leicester = new Properties();
		Leicester.name = "Leicester Square ";
		Leicester.position = 26;
		Leicester.type = "land";
		Leicester.cost = 260;
		Leicester.mortgage = 130;
		Leicester.house = 150;
		Leicester.rent = 22;
		Leicester.rent1 = 110;
		Leicester.rent2 = 330;
		Leicester.rent3 = 800;
		Leicester.rent4 = 975;
		Leicester.rent5 = 1150;
		Leicester.color = "yellow";

		Properties Coventry = new Properties();
		Coventry.name = "Coventry Street";
		Coventry.position = 27;
		Coventry.type = "land";
		Coventry.cost = 260;
		Coventry.mortgage = 130;
		Coventry.house = 150;
		Coventry.rent = 22;
		Coventry.rent1 = 110;
		Coventry.rent2 = 330;
		Coventry.rent3 = 800;
		Coventry.rent4 = 975;
		Coventry.rent5 = 1150;
		Coventry.color = "yellow";

		Properties Water = new Properties();
		Water.name = "Water Works";
		Water.position = 28;
		Water.type = "utility";
		Water.cost = 150;
		Water.mortgage = 75;
		Water.rent = 4;
		Water.rent1 = 10;
		Water.color = "utility";

		Properties Picca = new Properties();
		Picca.name = "Piccadilly ";
		Picca.position = 29;
		Picca.type = "land";
		Picca.cost = 280;
		Picca.mortgage = 140;
		Picca.house = 150;
		Picca.rent = 24;
		Picca.rent1 = 120;
		Picca.rent2 = 360;
		Picca.rent3 = 850;
		Picca.rent4 = 1025;
		Picca.rent5 = 1200;
		Picca.color = "yellow";

		Properties goJail = new Properties();
		goJail.name = "Go to Jail";
		goJail.type = "gojail";
		goJail.position = 30;

		Properties Regent = new Properties();
		Regent.name = "Regent Street";
		Regent.position = 31;
		Regent.type = "land";
		Regent.cost = 300;
		Regent.mortgage = 150;
		Regent.house = 200;
		Regent.rent = 26;
		Regent.rent1 = 130;
		Regent.rent2 = 390;
		Regent.rent3 = 900;
		Regent.rent4 = 1100;
		Regent.rent5 = 1275;
		Regent.color = "green";

		Properties Oxford = new Properties();
		Oxford.name = "Oxford Street";
		Oxford.position = 32;
		Oxford.type = "land";
		Oxford.cost = 300;
		Oxford.mortgage = 150;
		Oxford.house = 200;
		Oxford.rent = 26;
		Oxford.rent1 = 130;
		Oxford.rent2 = 390;
		Oxford.rent3 = 900;
		Oxford.rent4 = 1100;
		Oxford.rent5 = 1275;
		Oxford.color = "green";

		Properties Chest3 = new Properties();
		Chest3.name = "Chest";
		Chest3.type = "card1";
		Chest3.position = 33;

		Properties Bond = new Properties();
		Bond.name = "Bond Street";
		Bond.position = 34;
		Bond.type = "land";
		Bond.cost = 320;
		Bond.mortgage = 160;
		Bond.house = 200;
		Bond.rent = 28;
		Bond.rent1 = 150;
		Bond.rent2 = 450;
		Bond.rent3 = 1000;
		Bond.rent4 = 1200;
		Bond.rent5 = 1400;
		Bond.color = "green";

		Properties Liver = new Properties();
		Liver.name = "Liverpool St. Station";
		Liver.position = 35;
		Liver.type = "station";
		Liver.cost = 200;
		Liver.mortgage = 50;
		Liver.rent = 25;
		Liver.rent1 = 50;
		Liver.rent2 = 100;
		Liver.rent3 = 200;
		Liver.color = "station";

		Properties Chance3 = new Properties();
		Chance3.name = "Chance";
		Chance3.type = "card2";
		Chance3.position = 36;

		Properties Park = new Properties();
		Park.name = "Park Lane";
		Park.position = 37;
		Park.type = "land";
		Park.cost = 350;
		Park.mortgage = 175;
		Park.house = 200;
		Park.rent = 35;
		Park.rent1 = 175;
		Park.rent2 = 500;
		Park.rent3 = 1100;
		Park.rent4 = 1300;
		Park.rent5 = 1500;
		Park.color = "navy";

		Properties Tax2 = new Properties();
		Tax2.name = "Super Tax";
		Tax2.type = "tax";
		Tax2.position = 38;
		Tax2.tax = 100;

		Properties Mayfair = new Properties();
		Mayfair.name = "Mayfair";
		Mayfair.position = 39;
		Mayfair.type = "land";
		Mayfair.cost = 400;
		Mayfair.mortgage = 200;
		Mayfair.house = 200;
		Mayfair.rent = 50;
		Mayfair.rent1 = 200;
		Mayfair.rent2 = 600;
		Mayfair.rent3 = 1400;
		Mayfair.rent4 = 1700;
		Mayfair.rent5 = 2000;
		Mayfair.color = "navy";

		hm.put(0, Go);
		hm.put(1, Kent);
		hm.put(2, Chest1);
		hm.put(3, Chapel);
		hm.put(4, Tax1);
		hm.put(5, Kings);
		hm.put(6, Angel);
		hm.put(7, Chance1);
		hm.put(8, Euston);
		hm.put(9, Penton);
		hm.put(10, inJail);
		hm.put(11, Pall);
		hm.put(12, Electric);
		hm.put(13, Whitehall);
		hm.put(14, North);
		hm.put(15, Mary);
		hm.put(16, Bow);
		hm.put(17, Chest2);
		hm.put(18, Marl);
		hm.put(19, Vine);
		hm.put(20, Parking);
		hm.put(21, Strand);
		hm.put(22, Chance2);
		hm.put(23, Fleet);
		hm.put(24, Trafalgar);
		hm.put(25, Fenchurch);
		hm.put(26, Leicester);
		hm.put(27, Coventry);
		hm.put(28, Water);
		hm.put(29, Picca);
		hm.put(30, goJail);
		hm.put(31, Regent);
		hm.put(32, Oxford);
		hm.put(33, Chest3);
		hm.put(34, Bond);
		hm.put(35, Liver);
		hm.put(36, Chance3);
		hm.put(37, Park);
		hm.put(38, Tax2);
		hm.put(39, Mayfair);

		for (Player i : playerlist) {
			i.pp.put("brown", i.brown);
			i.pp.put("sky", i.sky);
			i.pp.put("pink", i.pink);
			i.pp.put("orange", i.orange);
			i.pp.put("red", i.red);
			i.pp.put("yellow", i.yellow);
			i.pp.put("green", i.green);
			i.pp.put("navy", i.navy);
			i.pp.put("station", i.station);
			i.pp.put("utility", i.utility);
		}

		for (int i = 1; i < 17; i++) {
			random2.add(i);
		}
		Collections.shuffle(random2);

		for (int i = 1; i < 17; i++) {
			random3.add(i);
		}
		Collections.shuffle(random3);

		Serverclass.broadcast("\n\n\n\n\n");
		Serverclass.broadcast(
				"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		Serverclass.broadcast(
				" ___               ___         ___________       _____         ___       ___________       ___________         ___________       ___             _____        _____                  ");
		Serverclass.broadcast(
				"|   \\             /   |       /           \\     |     \\       |   |     /           \\     |           \\       /           \\     |   |            \\    \\      /    /           ");
		Serverclass.broadcast(
				"|    \\           /    |      /   _______   \\    |      \\      |   |    /   _______   \\    |    ____    \\     /   _______   \\    |   |             \\    \\    /    /                   ");
		Serverclass.broadcast(
				"|     \\         /     |     /   /       \\   \\   |       \\     |   |   /   /       \\   \\   |   |    \\    |   /   /       \\   \\   |   |              \\    \\  /    /              ");
		Serverclass.broadcast(
				"|      \\       /      |    |   |         |   |  |   |\\   \\    |   |  |   |         |   |  |   |____/    |  |   |         |   |  |   |               \\    \\/    /             ");
		Serverclass.broadcast(
				"|       \\     /       |    |   |         |   |  |   | \\   \\   |   |  |   |         |   |  |            /   |   |         |   |  |   |                \\        /                 ");
		Serverclass.broadcast(
				"|        \\   /        |    |   |         |   |  |   |  \\   \\  |   |  |   |         |   |  |    _______/    |   |         |   |  |   |                 \\      /               ");
		Serverclass.broadcast(
				"|   |\\    \\_/    /|   |    |   |         |   |  |   |   \\   \\ |   |  |   |         |   |  |   |            |   |         |   |  |   |                  |    |                  ");
		Serverclass.broadcast(
				"|   | \\         / |   |     \\   \\_______/   /   |   |    \\   \\|   |   \\   \\_______/   /   |   |             \\   \\_______/   /   |   |___________       |    |                 ");
		Serverclass.broadcast(
				"|   |  \\_______/  |   |      \\             /    |   |     \\       |    \\             /    |   |              \\             /    |               |      |    |                   ");
		Serverclass.broadcast(
				"|___|             |___|       \\___________/     |___|      \\______|     \\___________/     |___|               \\___________/     |_______________|      |____|                       ");
		Serverclass.broadcast(
				"                                                                                                                                                                        ");

		Serverclass.broadcast("\n");
		Serverclass.broadcast("                                      WELCOME TO MONOPOLY");
		Serverclass.broadcast("\n");
		Serverclass.broadcast(
				"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		Serverclass.broadcast("\n\n\n");

		Serverclass.broadcast("Randomly choosing player turns");
		Collections.shuffle(playerlist);
		for (int i = 0; i < playerlist.size(); i++) {
			Serverclass.broadcast((i + 1) + ". " + playerlist.get(i).name);
		}
		Serverclass.playerUpdate();
		Serverclass.update();
		while (playerlist.size() > 1) {
			next();
		}

	}
}