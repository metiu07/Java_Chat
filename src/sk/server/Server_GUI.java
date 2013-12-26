package sk.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

		super("MythingChat");
		setContentPane(root);
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		button1.setForeground(new Color(0xFF0000));
		textArea1.setFocusable(false);

		setUpServer();
		setUpScanners();

		textField1.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent keyEvent) {
			}

			@Override
			public void keyPressed(KeyEvent keyEvent) {

			}

			@Override
			public void keyReleased(KeyEvent keyEvent) {
				if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
					out.println(textField1.getText());
					textArea1.append("<Mything> " + textField1.getText() + "\n");
					textField1.setText("");
				}
			}
		});

		button1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionEvent) {

				out.println(textField1.getText());
				textArea1.append("<Mything> " + textField1.getText() + "\n");
				textField1.setText("");

			}

		});

		while (true) {

			try {

				message = input.readLine();

			} catch (IOException e) {
				e.printStackTrace();
			}

			if (message != null) {

				textArea1.append("<Mato> " + message + "\n");

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
