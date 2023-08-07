package ChatTCP;
//TO DO YAMAS O MENOS TENGO QUE UN PRIMER CLIENTE-VENTANA LANCE MENSAJES Y SE VISUALICEN EN UNA TEXTAREA
//COMPARTIDA DEBERIA SER UNA PROPIEDAD ESTATICA DE VENTANA Y NO DE CLIENTE PORQUE LA IDEA ES TENER VARIAS CLASES CLIENTES QUE LANCEN VENTANAS
//ARREGLARE ESE PROBLEMA Y EMPEZARÉ A PROGRAMAR HILOS DESPUES PARA ATENDER A VARIOS EN OTRO MOMENTO ADEMAS EL CODIGO ESTÁ UN POCO DESORDENADO
//VALE TODO ESTA MAL PLANTEADO, NO TIENE SENTIDO UTILIZAR UNA CLASE EXTERNA, NECESITO QUE EL SERVER MANDE EL LOG CON LOS MENSAJES, SI NO, NO TIENE
//PUTISIMO SENTIDO NADA. TENGO QUE REESTRUCTURAR EL CODIGO DE UNA MANERA BESTIAL SI QUIERO QUE ESTO TENGA UN MINIMO DE SENTIDO
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	static final int MAXIMO=3;
	static int PUERTO=4444;
	static ServerSocket servidor=null;
	static int contadordehilos=0;
	static Socket[] s=new Socket[3];
	public static void main(String[] args) {
		Lanzar_Hilos();
	}//MAIN
	
	public static void Lanzar_Hilos() {
		try {
			servidor=new ServerSocket(PUERTO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(contadordehilos<3) {
			try {
				s[contadordehilos]=servidor.accept();
			} catch (IOException e) {
				System.out.println("No hay gente conectada");
				try {
					servidor.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					try {
						servidor.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
			HilosChateadores hc=new HilosChateadores(s[contadordehilos]);
			HilosActualizadores ha=new HilosActualizadores(s[contadordehilos]);
			contadordehilos++;
			try {
			hc.start();	
			ha.start();
			}catch(Exception e) {System.out.println("A ver si asi no jode");}
		}//WHILE
	}
}
