package ChatTCP;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Cliente {
	static String nickname="Papa Emeritus Zero";//El vocalista de Ghost, no la he puesto vacia porque me hace gracia que ahi se quede 
	public static void main(String[] args) {
		
		Ventana v= new Ventana(new String(JOptionPane.showInputDialog(
                "¿Cual estu nickname?", null)));
		v.setVisible(true);
		//v.run();
	}//MAIN
}
