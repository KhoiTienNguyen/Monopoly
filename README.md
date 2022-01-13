# Monopoly Online

A complete text-based implementation of Monopoly, playable online using threads, ServerSocket, DataInputStream, DataOutputStream and other networking libraries in Java.

# Instructions to use

A server is required to host the clients. Export the server package to runnable .jar file or run `Server_X_Client.java` to start the server.
Similarly for the clients, export the client package to runnable .jar file or run `NetworkClient.java` to start the client-side.
Start the server first before starting the clients.

### Port-Forwarding

The person hosting the server will need to port-forward
Port-Forward Port: 1234

### Starting the game

Each client will need to type `!name 'some_name'` to name themselves.
Example: `!name Khoi`
Then once everybody has named themselves, each client will need to type !ready
The game will start when every player is ready.

### In the game

Once in game, simply type the commands seen without anything before it (no !).
Example: `roll` or `info`
When a player finishes their turn, type `end` to end their turn.
To chat to other players, type '/' before your sentence.
Example: `/Hello everyone!`
