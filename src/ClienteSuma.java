//Codigo referenciado obtenido de: http://royerjava.es.tl/Sockets.htm
//Adaptado al requerimiento del laboratorio por: Andres Huertas/Sergio Villacres/ Grace Borja


import java.net.*;

import javax.swing.JOptionPane;

import java.io.*;
public class ClienteSuma{
	private static int SERVER_PORT=5051;  
	
	public static void main(String []args){
		ServerSocket ss=null;
		try{

			ss=new ServerSocket(5052);  // instanciamos un socket por el que recibiremos la suma de los numeros
			
			//Pedimos los numeros en un solo String para envirlo y que el servidor se encargue de tokenizarlos
			String cad1=JOptionPane.showInputDialog("Escriba los numeros para que sume el servidor separado por coma");  
			
			Socket sc1=new Socket("localhost",SERVER_PORT); // establecemos conexion son el servidor en este caso para uso practico el localhost
			OutputStream os1=sc1.getOutputStream();	// creamos un output para enviar la cadena para que sea tokenizada
			DataOutputStream dos1=new DataOutputStream(os1);   //envia la cadena en datos primitivos hacia el servidor
			dos1.writeUTF(cad1);


			Socket s1=ss.accept();  // serealiza la conexion con el servidor por otro puerto para la recepcion de la respuesta
			
			//extraemos los datos y los presentamos por una ventana de mensaje con la suma de dos numeros
			InputStream is=s1.getInputStream();
			DataInputStream dis=new DataInputStream(is);
			String answer=dis.readUTF();
		    JOptionPane.showMessageDialog(null, answer);

	    
		    //cerramos las conexiones creadad
		    dis.close();
		    s1.close(); 
		    sc1.close();
		    dos1.close();
    
		}

		catch(IOException e){
			System.err.println("Error: no se encontro el servidor");
		}

	}
}

