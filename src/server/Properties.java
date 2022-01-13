package server;

class Properties {
	String type;
	int position;
	String name;
	int cost;
	int mortgage;
	boolean mortgaged = false;
	int houses = 0;
	int house;
	int rent;
	int rent1;
	int rent2;
	int rent3;
	int rent4;
	int rent5;
	int tax;
	String color;
	int owner = 0;
	String ownername = "Bank";

	public int payup(Player player2) {
		int pay = 0;
		if (type.equals("land")) {
			if (houses == 0) {
				if (color == "navy" || color == "brown") {
					if (player2.pp.get(color).size() == 2) {
						pay = rent * 2;
					} else {
						pay = rent;
					}
				} else {
					if (player2.pp.get(color).size() == 3) {
						pay = rent * 2;
					} else {
						pay = rent;
					}
				}
			} else if (houses == 1) {
				pay = rent1;
			} else if (houses == 2) {
				pay = rent2;
			} else if (houses == 3) {
				pay = rent3;
			} else if (houses == 4) {
				pay = rent4;
			} else if (houses == 5) {
				pay = rent5;
			}
			return pay;
		} else if (type.equals("station")) {
			if (player2.pp.get(color).size() == 1) {
				pay = rent;
			} else if (player2.pp.get(color).size() == 2) {
				pay = rent1;
			} else if (player2.pp.get(color).size() == 3) {
				pay = rent2;
			} else {
				pay = rent3;
			}
			return pay;
		} else {
			if (player2.pp.get(color).size() == 1) {
				pay = rent;
			} else {
				pay = rent1;
			}
			return pay;
		}
	}

	@Override
	public String toString() {
		StringBuilder sbname = new StringBuilder();
		for (int i = 0; i < (38 - name.length()) / 2; i++) {
			sbname.append(" ");
		}
		StringBuilder sbrent = new StringBuilder();
		for (int i = 0; i < 17; i++) {
			sbrent.append(" ");
		}
		StringBuilder sbrentfin = new StringBuilder();
		for (int i = 0; i < 35 - 17 - Integer.toString(rent).length(); i++) {
			sbrentfin.append(" ");
		}
		StringBuilder sbpos = new StringBuilder();
		for (int i = 0; i < 35 - 7 - Integer.toString(position).length(); i++) {
			sbpos.append(" ");
		}
		StringBuilder sbtype = new StringBuilder();
		for (int i = 0; i < 35 - 3 - type.length(); i++) {
			sbtype.append(" ");
		}
		StringBuilder sbcost = new StringBuilder();
		for (int i = 0; i < 35 - 3 - Integer.toString(cost).length(); i++) {
			sbcost.append(" ");
		}
		StringBuilder sbmort = new StringBuilder();
		for (int i = 0; i < 12; i++) {
			sbmort.append(" ");
		}
		StringBuilder sbmortfin = new StringBuilder();
		for (int i = 0; i < 35 - 22 - Integer.toString(mortgage).length(); i++) {
			sbmortfin.append(" ");
		}
		StringBuilder sbhouse = new StringBuilder();
		for (int i = 0; i < 11; i++) {
			sbhouse.append(" ");
		}
		StringBuilder sbhousefin = new StringBuilder();
		for (int i = 0; i < 35 - 23 - Integer.toString(house).length(); i++) {
			sbhousefin.append(" ");
		}
		StringBuilder sbhouses = new StringBuilder();
		for (int i = 0; i < 35 - 11 - Integer.toString(houses).length(); i++) {
			sbhouses.append(" ");
		}
		StringBuilder sbmortstat = new StringBuilder();
		for (int i = 0; i < 35 - 14 - Boolean.toString(mortgaged).length(); i++) {
			sbmortstat.append(" ");
		}
		StringBuilder sbrent1 = new StringBuilder();
		for (int i = 0; i < 35 - 12 - Integer.toString(rent1).length(); i++) {
			sbrent1.append(" ");
		}
		StringBuilder sbrent2 = new StringBuilder();
		for (int i = 0; i < 35 - 13 - Integer.toString(rent2).length(); i++) {
			sbrent2.append(" ");
		}
		StringBuilder sbrent3 = new StringBuilder();
		for (int i = 0; i < 35 - 13 - Integer.toString(rent3).length(); i++) {
			sbrent3.append(" ");
		}
		StringBuilder sbrent4 = new StringBuilder();
		for (int i = 0; i < 35 - 13 - Integer.toString(rent4).length(); i++) {
			sbrent4.append(" ");
		}
		StringBuilder sbrent5 = new StringBuilder();
		for (int i = 0; i < 13; i++) {
			sbrent5.append(" ");
		}
		StringBuilder sbrent5fin = new StringBuilder();
		for (int i = 0; i < 35 - 19 - Integer.toString(rent5).length(); i++) {
			sbrent5fin.append(" ");
		}
		StringBuilder sbowner = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			sbowner.append(" ");
		}
		StringBuilder sbownerfin = new StringBuilder();
		for (int i = 0; i < 35 - 23 - ownername.length(); i++) {
			sbownerfin.append(" ");
		}
		String type_cap = type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
		String mortgaged_cap = Boolean.toString(mortgaged).substring(0, 1).toUpperCase()
				+ Boolean.toString(mortgaged).substring(1).toLowerCase();
		if (tax > 0) {
			return "Position: " + position + "\n" + "Type: " + type + "\n" + "Name: " + name + "\n" + "Tax: " + tax;
		} else if (type.equals("land")) {
			StringBuilder sbcolor = new StringBuilder();
			for (int i = 0; i < 35 - 13 - color.length(); i++) {
				sbcolor.append(" ");
			}
			String color_cap = color.substring(0, 1).toUpperCase() + color.substring(1).toLowerCase();
			return "                                _________________________________________" + "\n"
					+ "                               " + "|  _____________________________________  |" + "\n"
					+ "                               " + "| |                                     | |" + "\n"
					+ "                               " + "| |             Title Deed              | |" + "\n"
					+ "                               " + "| |                                     | |" + "\n"
					+ "                               " + "| |" + sbname + name.toUpperCase() + sbname + "| |" + "\n"
					+ "                               " + "| |_____________________________________| |" + "\n"
					+ "                               " + "|                                         |" + "\n"
					+ "                               " + "|" + sbrent + "RENT $" + rent + sbrentfin + "|" + "\n"
					+ "                               " + "|                                         |" + "\n"
					+ "                               " + "|   " + "With 1 house " + sbrent1 + rent1 + "  |" + "\n"
					+ "                               " + "|   " + "With 2 houses " + sbrent2 + rent2 + "  |" + "\n"
					+ "                               " + "|   " + "With 3 houses " + sbrent3 + rent3 + "  |" + "\n"
					+ "                               " + "|   " + "With 4 houses " + sbrent4 + rent4 + "  |" + "\n"
					+ "                               " + "|                                         |" + "\n"
					+ "                               " + "|" + sbrent5 + "With HOTEL $" + rent5 + sbrent5fin + "|"
					+ "\n" + "                               " + "|" + sbmort + "Mortgage Value $" + mortgage
					+ sbmortfin + "|" + "\n" + "                               " + "|" + sbhouse + "Houses cost $"
					+ house + " each" + sbhousefin + "|" + "\n" + "                               "
					+ "|                                         |" + "\n" + "                               " + "|   "
					+ "Position" + sbpos + position + "  |" + "\n" + "                               " + "|   " + "Type"
					+ sbtype + type_cap + "  |" + "\n" + "                               " + "|   " + "Cost" + sbcost
					+ cost + "  |" + "\n" + "                               " + "|   " + "Mortgage Status" + sbmortstat
					+ mortgaged_cap + "  |" + "\n" + "                               " + "|   " + "Houses built"
					+ sbhouses + houses + "  |" + "\n" + "                               " + "|   " + "Property color"
					+ sbcolor + color_cap + "  |" + "\n" + "                               "
					+ "|                                         |" + "\n" + "                               " + "|"
					+ sbowner + ownername + " owns this property" + sbownerfin + "|" + "\n"
					+ "                               " + "|_________________________________________|";
		} else if (type.equals("station")) {
			sbrent1.setLength(0);
			for (int i = 0; i < 35 - 15 - Integer.toString(rent1).length(); i++) {
				sbrent1.append(" ");
			}
			sbrent2.setLength(0);
			for (int i = 0; i < 35 - 15 - Integer.toString(rent2).length(); i++) {
				sbrent2.append(" ");
			}
			sbrent3.setLength(0);
			for (int i = 0; i < 35 - 15 - Integer.toString(rent3).length(); i++) {
				sbrent3.append(" ");
			}
			return "                                _________________________________________" + "\n"
					+ "                               " + "|               ___         ____          |" + "\n"
					+ "                               " + "|             __\\ /________/   |          |" + "\n"
					+ "                               " + "|            /                /           |" + "\n"
					+ "                               " + "|            \\__            _|            |" + "\n"
					+ "                               " + "|              ()-()-()-()-()             |" + "\n"
					+ "                               " + "|  _____________________________________  |" + "\n"
					+ "                               " + "|                                         |" + "\n"
					+ "                               " + "|  " + sbname + name.toUpperCase() + sbname + "  |" + "\n"
					+ "                               " + "|  _____________________________________  |" + "\n"
					+ "                               " + "|                                         |" + "\n"
					+ "                               " + "|" + sbrent + "RENT $" + rent + sbrentfin + "|" + "\n"
					+ "                               " + "|                                         |" + "\n"
					+ "                               " + "|   " + "With 2 stations " + sbrent1 + rent1 + "  |" + "\n"
					+ "                               " + "|   " + "With 3 stations " + sbrent2 + rent2 + "  |" + "\n"
					+ "                               " + "|   " + "With 4 stations " + sbrent3 + rent3 + "  |" + "\n"
					+ "                               " + "|                                         |" + "\n"
					+ "                               " + "|" + sbmort + "Mortgage Value $" + mortgage + sbmortfin + "|"
					+ "\n" + "                               " + "|                                         |" + "\n"
					+ "                               " + "|   " + "Position" + sbpos + position + "  |" + "\n"
					+ "                               " + "|   " + "Type" + sbtype + type_cap + "  |" + "\n"
					+ "                               " + "|   " + "Cost" + sbcost + cost + "  |" + "\n"
					+ "                               " + "|   " + "Mortgage Status" + sbmortstat + mortgaged_cap
					+ "  |" + "\n" + "                               " + "|                                         |"
					+ "\n" + "                               " + "|" + sbowner + ownername + " owns this property"
					+ sbownerfin + "|" + "\n" + "                               "
					+ "|_________________________________________|";

		} else if (type.equals("utility")) {
			if (position == 12) {
				return "                                _________________________________________" + "\n"
						+ "                               " + "|                 _______                 |" + "\n"
						+ "                               " + "|                /       \\                |" + "\n"
						+ "                               " + "|               |         |               |" + "\n"
						+ "                               " + "|               \\   \\ /   /               |" + "\n"
						+ "                               " + "|                \\   |   /                |" + "\n"
						+ "                               " + "|                 \\__|__/                 |" + "\n"
						+ "                               " + "|                  |---|                  |" + "\n"
						+ "                               " + "|  _____________________________________  |" + "\n"
						+ "                               " + "|                                         |" + "\n"
						+ "                               " + "|  " + sbname + name.toUpperCase() + sbname + "  |"
						+ "\n" + "                               " + "|  _____________________________________  |"
						+ "\n" + "                               " + "|                                         |"
						+ "\n" + "                               " + "|    If one \"Utility\" is owned            |"
						+ "\n" + "                               " + "|  rent is 4 times amount shown on dice.  |"
						+ "\n" + "                               " + "|                                         |"
						+ "\n" + "                               " + "|    If both \"Utilities\" are owned        |"
						+ "\n" + "                               " + "|  rent is 10 times amount shown on       |"
						+ "\n" + "                               " + "|  dice.                                  |"
						+ "\n" + "                               " + "|                                         |"
						+ "\n" + "                               " + "|" + sbmort + "Mortgage Value $" + mortgage
						+ sbmortfin + "|" + "\n" + "                               "
						+ "|                                         |" + "\n" + "                               "
						+ "|   " + "Position" + sbpos + position + "  |" + "\n" + "                               "
						+ "|   " + "Type" + sbtype + type_cap + "  |" + "\n" + "                               "
						+ "|   " + "Cost" + sbcost + cost + "  |" + "\n" + "                               " + "|   "
						+ "Mortgage Status" + sbmortstat + mortgaged_cap + "  |" + "\n"
						+ "                               " + "|                                         |" + "\n"
						+ "                               " + "|" + sbowner + ownername + " owns this property"
						+ sbownerfin + "|" + "\n" + "                               "
						+ "|_________________________________________|";
			} else {
				return "                                _________________________________________" + "\n"
						+ "                               " + "|                                         |" + "\n"
						+ "                               " + "|                  o__^__o                |" + "\n"
						+ "                               " + "|           __       _|_                  |" + "\n"
						+ "                               " + "|          /__\\_____/___\\_______          |" + "\n"
						+ "                               " + "|         (_()______     _____  \\         |" + "\n"
						+ "                               " + "|          \\__/     `````     \\  |        |" + "\n"
						+ "                               " + "|                              |_|        |" + "\n"
						+ "                               " + "|  _____________________________________  |" + "\n"
						+ "                               " + "|                                         |" + "\n"
						+ "                               " + "|  " + sbname + name.toUpperCase() + sbname + "  |"
						+ "\n" + "                               " + "|  _____________________________________  |"
						+ "\n" + "                               " + "|                                         |"
						+ "\n" + "                               " + "|    If one \"Utility\" is owned            |"
						+ "\n" + "                               " + "|  rent is 4 times amount shown on dice.  |"
						+ "\n" + "                               " + "|                                         |"
						+ "\n" + "                               " + "|    If both \"Utilities\" are owned        |"
						+ "\n" + "                               " + "|  rent is 10 times amount shown on       |"
						+ "\n" + "                               " + "|  dice.                                  |"
						+ "\n" + "                               " + "|                                         |"
						+ "\n" + "                               " + "|" + sbmort + "Mortgage Value $" + mortgage
						+ sbmortfin + "|" + "\n" + "                               "
						+ "|                                         |" + "\n" + "                               "
						+ "|   " + "Position" + sbpos + position + "  |" + "\n" + "                               "
						+ "|   " + "Type" + sbtype + type_cap + "  |" + "\n" + "                               "
						+ "|   " + "Cost" + sbcost + cost + "  |" + "\n" + "                               " + "|   "
						+ "Mortgage Status" + sbmortstat + mortgaged_cap + "  |" + "\n"
						+ "                               " + "|                                         |" + "\n"
						+ "                               " + "|" + sbowner + ownername + " owns this property"
						+ sbownerfin + "|" + "\n" + "                               "
						+ "|_________________________________________|";
			}
		} else {
			return "Position: " + position + "\n" + "Type: " + type + "\n" + "Name: " + name + "\n";
		}
	}
}