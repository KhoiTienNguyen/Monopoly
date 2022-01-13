package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class NetworkClient {

	public static DatagramSocket socket = null;
//	public static DataOutputStream dout = null;

	static Scanner scan = new Scanner(System.in);

	public static Player findplayer(int port) {
		for (Player i : Client.playerlist) {
			if (i.port == port) {
				return i;
			}
		}
		return Client.playerlist.get(0); // doublecheck
	}

	public static void send(String message, DataOutputStream dout) {
		try {
			dout.writeUTF(message);
			dout.flush();
//			System.out.println("Sent message to " + address + ":" + port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception {

		System.out.println("Input Server IP Address");
		String address2 = scan.nextLine();
		InetAddress address = InetAddress.getByName(address2);
		Socket s1 = null;
		String line = null;
		BufferedReader br = null;

//		int port = 1234;

		try {

			s1 = new Socket(address, 1234);

		} catch (IOException e) {
			e.printStackTrace();
			System.err.print("IO Exception");
		}
		DataInputStream din = new DataInputStream(s1.getInputStream());
		DataOutputStream dout = new DataOutputStream(s1.getOutputStream());
		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter Data to echo Server ( Enter QUIT to end):");

		try {
			Thread listenThread = new Thread("Listener") {
				public void run() {
					try {
						String message = "";
						while (true) {
							message = din.readUTF();
//							System.out.println(message);
							while (message.length() == 0) {
								Thread.sleep(1000);
							}
							if (message.startsWith("lSh1s8zwst:{")) {
								String properties = message.substring(message.indexOf("Properties"));
								String player = message.substring(message.indexOf("Player:{") + 8,
										message.indexOf("Properties"));
								int playeramount = Integer.parseInt(message.substring(12, 13));
								for (int i = 0; i < playeramount; i++) {
//									player = player.substring(player.indexOf("("));
									int position = Integer
											.parseInt(player.substring(player.indexOf(":") + 1, player.indexOf(",")));
									// System.out.println(position);
									player = player.substring(player.indexOf(",") + 1);
									int money = Integer
											.parseInt(player.substring(player.indexOf(":") + 1, player.indexOf(",")));
									// System.out.println(money);
									player = player.substring(player.indexOf(",") + 1);
									boolean jailed = Boolean.parseBoolean(
											player.substring(player.indexOf(":") + 1, player.indexOf(",")));
									// System.out.println(jailed);
									player = player.substring(player.indexOf(",") + 1);
									boolean turn = Boolean.parseBoolean(
											player.substring(player.indexOf(":") + 1, player.indexOf(",")));
									// System.out.println(turn);
									player = player.substring(player.indexOf(",") + 1);
									int jailcount = Integer
											.parseInt(player.substring(player.indexOf(":") + 1, player.indexOf(",")));
									// System.out.println(jailcount);
									player = player.substring(player.indexOf(",") + 1);
									boolean done = Boolean.parseBoolean(
											player.substring(player.indexOf(":") + 1, player.indexOf(",")));
									// System.out.println(done);
									player = player.substring(player.indexOf(",") + 1);
									boolean bid = Boolean.parseBoolean(
											player.substring(player.indexOf(":") + 1, player.indexOf(",")));
									// System.out.println(bid);
									player = player.substring(player.indexOf(",") + 1);
									int debt = Integer
											.parseInt(player.substring(player.indexOf(":") + 1, player.indexOf(",")));
									// System.out.println(debt);
									player = player.substring(player.indexOf(",") + 1);
									int curdebt = Integer
											.parseInt(player.substring(player.indexOf(":") + 1, player.indexOf(",")));
									// System.out.println(curdebt);
									player = player.substring(player.indexOf(",") + 1);
									int curmoney = Integer
											.parseInt(player.substring(player.indexOf(":") + 1, player.indexOf(",")));
									// System.out.println(curmoney);
									player = player.substring(player.indexOf(",") + 1);
									boolean bidtalk = Boolean.parseBoolean(
											player.substring(player.indexOf(":") + 1, player.indexOf(",")));
									// System.out.println(bidtalk);
									player = player.substring(player.indexOf(",") + 1);
									Client.playerlist.get(i).position = position;
									Client.playerlist.get(i).money = money;
									Client.playerlist.get(i).jailed = jailed;
									Client.playerlist.get(i).turn = turn;
									Client.playerlist.get(i).jailcount = jailcount;
									Client.playerlist.get(i).done = done;
									Client.playerlist.get(i).bid = bid;
									Client.playerlist.get(i).debt = debt;
									Client.playerlist.get(i).curdebt = curdebt;
									Client.playerlist.get(i).curmoney = curmoney;
									Client.playerlist.get(i).bidtalk = bidtalk;

								}
								for (int i = 0; i < Client.hm.size(); i++) {
									properties = properties.substring(properties.indexOf("("));
									boolean mortgaged = Boolean.parseBoolean(properties
											.substring(properties.indexOf("mortgaged:", properties.indexOf(","))));
									// System.out.println(mortgaged);
									int houses = Integer.parseInt(properties
											.substring(properties.indexOf("houses:", properties.indexOf(","))));
									// System.out.println(houses);
									int owner = Integer.parseInt(properties
											.substring(properties.indexOf("owner:", properties.indexOf(","))));
									// System.out.println(owner);
									Client.hm.get(i).mortgaged = mortgaged;
									Client.hm.get(i).houses = houses;
									Client.hm.get(i).owner = owner;

								}
							} else if (message.startsWith("qsHdaET2lh:{")) {
								// System.out.println(message.substring(12, 13));
								int playeramount = Integer.parseInt(message.substring(12, 13));
								if ((message.indexOf("}}") - message.indexOf("Player:{")) > 1) {
									for (int i = 0; i < playeramount; i++) {
										message = message.substring(message.indexOf("("));
										String name = message.substring(message.indexOf(":") + 1, message.indexOf(","));
										message = message.substring(message.indexOf(",") + 1);
										// System.out.println(name);
										int num = Integer.parseInt(
												message.substring(message.indexOf(":") + 1, message.indexOf(",")));
										message = message.substring(message.indexOf(",") + 1);
										// System.out.println(num);
										message = message.substring(message.indexOf(",") + 1);
										// System.out.println(playeraddress);
										int playerport = Integer.parseInt(
												message.substring(message.indexOf(":") + 1, message.indexOf(",")));
										message = message.substring(message.indexOf(",") + 1);
										// System.out.println(playerport);
										int id = Integer.parseInt(
												message.substring(message.indexOf(":") + 1, message.indexOf(",")));
										message = message.substring(message.indexOf(",") + 1);
										// System.out.println(id);
										Client.playerlist.add(new Player(name, id, null, playerport));
										Client.playerlist.get(i).num = num;
									}
								}
							} else {
								System.out.println(message);
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Error");
					}

				}
			};
			listenThread.start();
			line = br.readLine();
			while (line.compareTo("QUIT") != 0) {

				send(line, dout);
				line = br.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Socket read Error");
		} finally {
			br.close();
			s1.close();
			System.out.println("Connection Closed");

		}

	}
}