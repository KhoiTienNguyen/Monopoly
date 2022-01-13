package client;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

class Player {
	boolean ready = false;
	int port;
	InetAddress address;
	int id;
	String name;

	int position = 0;
	int money = 1500;

	boolean jailed = false;
	boolean turn = true;
	int jailcount = 0;
	boolean done = false;
	int num;
	boolean bid = true;
	int debt;
	int curdebt;
	int curmoney;
	boolean bidtalk = false;
	ArrayList<Properties> brown = new ArrayList<Properties>();
	ArrayList<Properties> sky = new ArrayList<Properties>();
	ArrayList<Properties> pink = new ArrayList<Properties>();
	ArrayList<Properties> orange = new ArrayList<Properties>();
	ArrayList<Properties> red = new ArrayList<Properties>();
	ArrayList<Properties> yellow = new ArrayList<Properties>();
	ArrayList<Properties> green = new ArrayList<Properties>();
	ArrayList<Properties> navy = new ArrayList<Properties>();
	ArrayList<Properties> station = new ArrayList<Properties>();
	ArrayList<Properties> utility = new ArrayList<Properties>();
	HashMap<String, ArrayList<Properties>> pp = new HashMap<String, ArrayList<Properties>>();

	ArrayList<Properties> all = new ArrayList<Properties>();

	public Player(String name, int id, InetAddress address, int port) {
		this.name = name;
		this.id = id;
		this.address = address;
		this.port = port;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(port);
		sb.append("\n");
		sb.append(address);
		sb.append("\n");
		sb.append("\nBrown:\n");
		for (int i = 0; i < brown.size(); i++) {
			sb.append("\n");
			sb.append(brown.get(i));
			sb.append("\n");
		}
		sb.append("\nSky:\n");
		for (int i = 0; i < sky.size(); i++) {
			sb.append("\n");
			sb.append(sky.get(i));
			sb.append("\n");
		}
		sb.append("\nPink:\n");
		for (int i = 0; i < pink.size(); i++) {
			sb.append("\n");
			sb.append(pink.get(i));
			sb.append("\n");
		}
		sb.append("\nOrange:\n");
		for (int i = 0; i < orange.size(); i++) {
			sb.append("\n");
			sb.append(orange.get(i));
			sb.append("\n");
		}
		sb.append("\nRed:\n");
		for (int i = 0; i < red.size(); i++) {
			sb.append("\n");
			sb.append(red.get(i));
			sb.append("\n");
		}
		sb.append("\nYellow:\n");
		for (int i = 0; i < yellow.size(); i++) {
			sb.append("\n");
			sb.append(yellow.get(i));
			sb.append("\n");
		}
		sb.append("\nGreen:\n");
		for (int i = 0; i < green.size(); i++) {
			sb.append("\n");
			sb.append(green.get(i));
			sb.append("\n");
		}
		sb.append("\nNavy:\n");
		for (int i = 0; i < navy.size(); i++) {
			sb.append("\n");
			sb.append(navy.get(i));
			sb.append("\n");
		}
		sb.append("\nStations:\n");
		for (int i = 0; i < station.size(); i++) {
			sb.append("\n");
			sb.append(station.get(i));
			sb.append("\n");
		}
		sb.append("\nUtility:\n");
		for (int i = 0; i < utility.size(); i++) {
			sb.append("\n");
			sb.append(utility.get(i));
			sb.append("\n");
		}
		return "Player name: " + name + "\n" + "Player position: " + position + "\n" + "Money: " + money + "\n"
				+ "In Jail: " + jailed + "\n" + "Properties: " + "\n" + sb + "\n";

	}
}