package sk.server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_GUI extends JFrame {

	private JPanel root;
	private JTextArea textArea1;
	private JButton button1;
	private JTextField textField1;

	private ServerSocket server;
	private Socket socket;

	private BufferedReader input;
	private PrintWriter out;

	private String message = " ";

	public Server_GUI() {

		super("ServerWindow");
		setContentPane(root);
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		button1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionEvent) {

				out.println(textField1.getText());
				textArea1.append("<UserName> " + textField1.getText() + "\n");
				textField1.setText("");

			}

		});

		setUpServer();
		setUpScanners();

		while (true) {


			try {

				message = input.readLine();

			} catch (IOException e) {
				e.printStackTrace();
			}

			if (message != null) {

				textArea1.append(message + "\n");

			}

		}

	}

	private void setUpServer() {
		try {
			server = new ServerSocket(1997);
			socket = server.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setUpScanners() {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Server_GUI();
	}

}
