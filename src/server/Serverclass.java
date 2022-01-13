package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

class Serverclass {
	public static HashMap<Integer, ServerThread> hmthreads = new HashMap<Integer, ServerThread>();
	public static int ClientID = -1;
	public static final int port = 1234;
	public static DatagramSocket socket;
	public static StringBuilder sbupdate = new StringBuilder();
	Socket s = null;
	ServerSocket ss2 = null;

	public static void playerUpdate() {
		sbupdate.setLength(0);
		sbupdate.append("qsHdaET2lh:{");
		sbupdate.append(GameServer.playerlist.size());
		sbupdate.append("Player:{");
		for (Player i : GameServer.playerlist) {
			sbupdate.append("(");
			sbupdate.append("name:" + i.name + ",");
			sbupdate.append("num:" + i.num + ",");
			sbupdate.append("address:" + i.address + ",");
			sbupdate.append("port:" + i.port + ",");
			sbupdate.append("id:" + i.id + ",");
			sbupdate.append(")");
		}
		sbupdate.append("}}");
		broadcast(sbupdate.toString());
	}

	public static void update() {
		sbupdate.setLength(0);
		sbupdate.append("lSh1s8zwst:{");
		sbupdate.append(GameServer.playerlist.size());
		sbupdate.append("Player:{");
		for (Player i : GameServer.playerlist) {
			sbupdate.append("(");
			sbupdate.append("position:" + i.position + ",");
			sbupdate.append("money:" + i.money + ",");
			sbupdate.append("jailed:" + i.jailed + ",");
			sbupdate.append("turn:" + i.turn + ",");
			sbupdate.append("jailcount:" + i.jailcount + ",");
			sbupdate.append("done:" + i.done + ",");
			sbupdate.append("bid:" + i.bid + ",");
			sbupdate.append("debt:" + i.debt + ",");
			sbupdate.append("curdebt:" + i.curdebt + ",");
			sbupdate.append("curmoney:" + i.curmoney + ",");
			sbupdate.append("bidtalk:" + i.bidtalk + ",");
			sbupdate.append(")");

		}
		sbupdate.append("},");
		sbupdate.append("Properties:{");
		for (int i = 0; i < GameServer.hm.size(); i++) {
			sbupdate.append("(");
			sbupdate.append("mortgaged:" + GameServer.hm.get(i).mortgaged + ",");
			sbupdate.append("houses:" + GameServer.hm.get(i).houses + ",");
			sbupdate.append("owner:" + GameServer.hm.get(i).owner);
			sbupdate.append(")");

		}
		sbupdate.append("}}");
		broadcast(sbupdate.toString());
	}

	public static void talk(String message) {
		if (GameServer.currentscan) {
			GameServer.chosenint = Integer.parseInt(message);
		} else {
			GameServer.chosenstring = message;
		}
	}

	public static StringBuilder sbinfo() {
		StringBuilder sbinfo = new StringBuilder();
		for (int i = 0; i < Serverclass.hmthreads.size(); i++) {
			sbinfo.append("Name: " + hmthreads.get(i).getPlayer() + " - ");
			sbinfo.append("ID: " + hmthreads.get(i).getID() + " - ");
			sbinfo.append("Address: " + hmthreads.get(i).getAddress() + " - ");
			sbinfo.append("Port: " + hmthreads.get(i).getPort() + " - ");
			sbinfo.append("\n");
		}
		return sbinfo;
	}

	public static void broadcast(String message) {
		for (int i = 0; i < Serverclass.hmthreads.size(); i++) {
			send(message, Serverclass.hmthreads.get(i).dout);
		}
	}

	public static void send(String str2, DataOutputStream dout) {
		// Send to client
		try {
			dout.writeUTF(str2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Trymethod() {
		System.out.println("Server Listening...");
		try {
			ss2 = new ServerSocket(port);
			socket = new DatagramSocket(port);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Server error");

		}

		while (true) {

			try {
				ClientID++;
				s = ss2.accept();
				System.out.println("connection Established");
				ServerThread st = new ServerThread(s, ClientID, port, this);
				hmthreads.put(ClientID, st);
				st.start();

			}

			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Connection Error");

			}
			if (hmthreads.size() > 10) {
				break;
			}
		}
		System.out.println("done");

	}

}