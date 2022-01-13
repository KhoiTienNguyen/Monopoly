package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

class ServerThread extends Thread{
	private int port;
	private InetAddress address;
	private Socket s = null;
	private String name;
	private int id;
	private Serverclass serverclass;
	public boolean ready = false;
	public DataInputStream din = null;
	public DataOutputStream dout = null;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public ServerThread(Socket s, int id, int port, Serverclass serverclass) throws SocketException {
		this.s = s;
		this.id = id;
		this.serverclass = serverclass;

	}

	public InetAddress getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	public int getID() {
		return id;
	}

	public String getPlayer() {
		return name;
	}

	@Override
	public void run() {
		Serverclass.hmthreads.get(id).address = s.getInetAddress();
		Serverclass.hmthreads.get(id).port = s.getPort();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			din = new DataInputStream(s.getInputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			dout = new DataOutputStream(s.getOutputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String message = "";
		try {

			while (true) {
				try {
					message = din.readUTF();
				} catch (IOException e) {
					e.printStackTrace();
				}
				while (message.length() == 0) {
					Thread.sleep(1000);
				}
//				message = message.substring(0, message.indexOf("\\e"));
				System.out.println(message);
				if (!ready) {
					if (message.startsWith("!name")) {
						Serverclass.hmthreads.get(id).name = message.substring(message.indexOf("!") + 6);
						System.out.println("Player " + name + " has joined the game");
						System.out.println(Serverclass.sbinfo());
						Serverclass.broadcast("Player " + name + " has joined the game");
					} else if (message.startsWith("/")) {
						message = message.substring(1);
						message = Serverclass.hmthreads.get(id).name + ": " + message;
						Serverclass.broadcast(message);
					} else if (message.startsWith("!info")) {
						Serverclass.send(
								Serverclass.hmthreads.get(id).name + " - " + address.toString() + ":"
										+ Serverclass.hmthreads.get(id).getPort(), dout);
					} else if (message.startsWith("!ready")) {
						ready = !ready;
						if (ready) {
							System.out.println("Player " + name + " is ready");
							Serverclass.broadcast("Player " + name + " is ready");
						} else {
							System.out.println("Player " + name + " is not ready");
							Serverclass.broadcast("Player " + name + " is not ready");
						}
					}
				} else {
					if (message.startsWith("/")) {
						message = message.substring(1);
						message = Serverclass.hmthreads.get(id).name + ": " + message;
						Serverclass.broadcast(message);
					} else if (GameServer.currentbidding) {
						for (int i = 0; i < GameServer.bidlist2.size(); i++) {
							if (GameServer.bidlist2.get(i).id == getID()) {
								if (GameServer.bidlist2.get(i).bidtalk) {
									Serverclass.talk(message);
									break;
								}
							} else {
								Serverclass.send("Sorry it is not your turn", dout);
							}
						}
					} else if (GameServer.currenttrading) {
						if (GameServer.tradelean >= 1) {
							if ((GameServer.choose(GameServer.tradelean).id == getID())) {
								Serverclass.talk(message);
							}
						}
					} else if (getID() == GameServer.playerlist.get(0).id) {
						Serverclass.talk(message);
					} else {
						Serverclass.send("Sorry it is not your turn", dout);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}