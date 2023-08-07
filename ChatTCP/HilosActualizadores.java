package ChatTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HilosActualizadores extends Thread {
	Socket cliente;
	DataInputStream dis;
	DataOutputStream dos;
	String apoyo="";
	public HilosActualizadores(Socket iicliente) {
		cliente=iicliente;
		try {
			dis=new DataInputStream(cliente.getInputStream());
			dos=new DataOutputStream(cliente.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//CATCH	
	}
	@Override
	public void run() {//ESTO FUNCIONA, NO SE COMO, PERO DE ALGUNA MANERA HE LOGRADO QUE FUNCIONE
		while(true) {
			try {
				apoyo=dis.readUTF();
				Conversacion.setConversacion(Conversacion.getConversacion()+apoyo+"\n");	
			} catch (IOException e) {
				
			}
		}//WHILE
		
	}//RUN
}
