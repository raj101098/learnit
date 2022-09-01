import java.io.*;
import java.util.*;
import java.net.*;
public class tcpClient
{
	public static void main(String args[]) throws Exception
	{
		Scanner in=new Scanner(System.in);
		System.out.println("Enter The Port Number");
		int a=in.nextInt();
		String x;
		try
		{
		Socket skt=new Socket("localhost",a);
		DataOutputStream odos=new DataOutputStream(skt.getOutputStream());
		DataInputStream odis=new DataInputStream(skt.getInputStream());

		BufferedReader obr=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			System.out.println("(1)Push,(2)Pop,(3)Peek,(4)isEmpty,(5)Search,(6)Sort(7)Iterate,(8)Exit");
			String s=obr.readLine();
			System.out.println(s);
			if(s.equals("1")||s.equals("5"))
			{
				System.out.println("Enter the value : ");
				x=obr.readLine();
				s+=","+x;
				odos.writeUTF(s);
			}
			else
				odos.writeUTF(s);

			if(s.equals("8"))
			{
				skt.close();
				break;
			}
			System.out.println(odis.readUTF());
		}
		}catch(ConnectException ex){System.out.println("Server Not Avaliable 404 Error Found");}
	}
}