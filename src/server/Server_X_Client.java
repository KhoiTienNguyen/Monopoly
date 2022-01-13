package server;


public class Server_X_Client {

	public static void main(String args[]) {
		AllServer start = new AllServer();
		start.start();
		System.out.println("Server Thread started");
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int readycount = 0;
			for (int i = 0; i < Serverclass.ClientID; i++) {
				if (Serverclass.hmthreads.get(i).ready) {
					readycount++;
				}
			}
			if (readycount == Serverclass.ClientID && Serverclass.ClientID >= 1) {
				break;
			}
		}
		System.out.println("Welcome to Monopoly");
		for (int i = 0; i < Serverclass.ClientID; i++) {
			GameServer.playerlist
					.add(new Player(Serverclass.hmthreads.get(i).getPlayer(), Serverclass.hmthreads.get(i).getID(),
							Serverclass.hmthreads.get(i).getAddress(), Serverclass.hmthreads.get(i).getPort()));
		}
		// Set player number
		for (int i = 0; i < GameServer.playerlist.size(); i++) {
			GameServer.playerlist.get(i).num = i + 1;
		}

		GameServer.main();

		Serverclass.broadcast("Congratulations to " + GameServer.playerlist.get(0) + " for winning");
	}
}

class AllServer extends Thread {
	@Override
	public void run() {
		Serverclass asdf = new Serverclass();
		asdf.Trymethod();
	}
}
