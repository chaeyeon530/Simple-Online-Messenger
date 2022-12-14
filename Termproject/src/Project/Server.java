package Project;
import java.io.*;
import java.net.*;
import java.security.Provider;
import java.util.concurrent.*;
import java.util.*;

public class Server
{
	ServerSocket ss = null;


	Vector<Client> alluser;
	Vector<Client> waituser;
	Vector<Room> room;

	public static void main(String[] args)
	{
		Server server = new Server();

		server.alluser = new Vector<>();
		server.waituser = new Vector<>();
		server.room = new Vector<>();

		try
		{
			server.ss = new ServerSocket(1228);
			System.out.println("[Server] server start");

			while(true)
			{
				Socket socket = server.ss.accept();
				Client c = new Client(socket, server);

				c.start();
			}
		} catch(SocketException e)
		{
			System.out.println("[Server] SocketException> " + e.toString());
		} catch(IOException e)
		{
			System.out.println("[Server] IOException> " + e.toString());
		}
	}
}