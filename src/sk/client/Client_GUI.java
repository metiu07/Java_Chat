package sk.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Mato on 24.12.2013.
 */

public class Client_GUI extends JFrame {

	private JPanel root;
	private JButton button;
	private JTextField TextField;
	private JTextArea textArea1;

	private Socket socket;
	private PrintWriter out;
	private BufferedReader input;

	private String message = " ";

	public Client_GUI() {

		super("ClientWindow");
		setContentPane(root);
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		setUpSocket();
		setUpScanners();

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionEvent) {

				out.println(TextField.getText());
				textArea1.append("<UserName> " + TextField.getText() + "\n");
				TextField.setText("");

				try {

					message = input.readLine();

				} catch (IOException e) {
					e.printStackTrace();
				}


				System.out.println(message);

			}

		});

	}

	private void setUpSocket() {
		try {
			socket = new Socket("localhost", 1997);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setUpScanners() {
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Client_GUI();
	}

}
