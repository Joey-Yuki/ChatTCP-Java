package ChatTCP;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Ventana extends JFrame implements Runnable, ActionListener {
	JButton b;
	JTextField txt;
	Socket s;
	String nickname;
	DataOutputStream salida=null;
	DataInputStream entrada=null;
	JTextArea txts=HacerArea();
	Conversacion con=new Conversacion();
	@Override
	public void run() {
		while(true) {
			try {
				txts.setText(entrada.readUTF());//ESTO SI ACTUALIZA EN TIEMPO REAL, ESTO ESTÁ BIEN. CONFIRMADISIMO
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//WHILE
	}//RUN
	public Ventana(String nickname) {
		this.nickname=nickname;
		setSize(600,700);
		setLayout(null);
		setName(nickname);
		this.LayoutBoton();
		this.LayoutTextBox();
		this.LayoutCajamensajes();
		add(txts);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		Establecer_Socket();
		run();
	}//CONSTRUCTOR
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void LayoutBoton() {
		b=new JButton("click");
		b.setBounds(430,60,100, 40);
		b.setText("Dale");
		b.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				Cambiar_Texto();
            }
		});
		add(b);
	}

	public void LayoutTextBox() {
		txt=new JTextField();		
		txt.setBounds(20, 60, 400, 40);	
		txt.setVisible(true);
		add(txt);
	}//LAYOUT
	
	public void LayoutCajamensajes() {
		JTextArea cajamensajes=new JTextArea();
		cajamensajes.setBounds(20,110, 500, 500);
		cajamensajes.setVisible(true);
		cajamensajes.setBorder(BorderFactory.createLineBorder(Color.black));
		JScrollPane scroll=new JScrollPane(cajamensajes);
		scroll.setBounds(20,110, 500, 500);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scroll);
	}//CAJAMENSAJES

	public static JTextArea HacerArea() {
		JTextArea cajamensajes=new JTextArea();
		cajamensajes.setBounds(20,110, 500, 500);
		cajamensajes.setVisible(true);
		cajamensajes.setBorder(BorderFactory.createLineBorder(Color.black));
		return cajamensajes;
		
	}
	
	public void Establecer_Socket() {
		try {
			s=new Socket("localhost", 4444);
			salida=new DataOutputStream(s.getOutputStream());
			entrada=new DataInputStream(s.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//METODO

	public void Cambiar_Texto() {
		try {
			salida.writeUTF(nickname+" dice: "+txt.getText());
			txt.setText("");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
