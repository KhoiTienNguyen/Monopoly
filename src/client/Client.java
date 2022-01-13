package client;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Random;

import java.util.Scanner;

public class Client {
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


	public static void main() {

		bank.name = "Bank";
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

	}
}