//Codigo referenciado obtenido de: http://royerjava.es.tl/Sockets.htm
//Adaptado al requerimiento del laboratorio por: Andres Huertas/Sergio Villacres/ Grace Borja

import java.net.*;
import java.util.StringTokenizer;
import java.io.*;
public class ServidorSuma{

	public static void main(String [] args){
		int x,y,sum;
		String a,b;
		ServerSocket ss=null;   // Creamos un serversocket para extraer los daos que nos envia el cliente y procesarlos

		try{

			ss=new ServerSocket(5051); //por el puerto 5051 recibiremos la data
		
		}
			catch(IOException e){}
		try{
			Socket s1=ss.accept();
			InputStream is=s1.getInputStream();
			DataInputStream dis=new DataInputStream(is);   //almacenamos los datos en un string
			StringTokenizer tokens = new StringTokenizer(dis.readUTF(), ",");   // tokenizamos para obtener los dos numeros
			
			//extraemos los numeros en formato String
			a=tokens.nextToken(); 
			b=tokens.nextToken();
  
			// Realizamos la conversion a int para su suma
			x=Integer.parseInt(a);
			y=Integer.parseInt(b);
    
			sum=x+y;  // Realiza la suma

			// Para muestra de resultados inmediatos utilizamos como direccion del servidor el local host
			Socket sc=new Socket("localhost",5052);  // Crea un nuevo socket para el envio de la respuesta por el puerto 5052
			OutputStream os=sc.getOutputStream();
			DataOutputStream dos=new DataOutputStream(os); // creamos un paquete donde contendra nuestra respuesta
			dos.writeUTF("La suma total es:  " + sum); // enviamos la respuesta generando un String de resuesta

			// cerramos los sockets
			dos.close();
			s1.close();
			sc.close();
        
		}
			catch(IOException e){}

	}
}
