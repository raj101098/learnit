import java.io.*;
import java.net.*;
import java.util.*;
public class tcpServer
{
	public static void main(String args[]) throws Exception
	{
		Scanner in=new Scanner(System.in);
		System.out.println("Enter The Port Number");
		int a=in.nextInt();
		String x="";
		int b,c=0;
		try
		{
			ServerSocket skt=new ServerSocket(a);
			Socket sk=skt.accept();
			System.out.println("Connected");
			DataInputStream odis=new DataInputStream(sk.getInputStream());
			DataOutputStream odos=new DataOutputStream(sk.getOutputStream());

			Stack <Integer> stk =new Stack<>();

			while(true)
			{
				String s=odis.readUTF();
			if(s.charAt(0)=='5'||s.charAt(0)=='1')
				{
					x=s.substring(2);
					s=Character.toString(s.charAt(0));
					c=Integer.parseInt(x);
				}
				b=Integer.parseInt(s);
				switch(b)
				{
					case 1:
					{
						stk.push(c);
						odos.writeUTF(stk.toString());
						break;
					}case 2:
					{
						stk.pop();
						odos.writeUTF(stk.toString());
						break;
					}case 3:
					{
						odos.writeUTF(Integer.toString(stk.peek()));
						break;
					}case 4:
					{
						odos.writeUTF(String.valueOf(stk.isEmpty()));
						break;
					}case 5:
					{
						odos.writeUTF(Integer.toString(stk.search(c)));
						break;
					}case 6:
					{
						stk.sort(null);
						odos.writeUTF(stk.toString());
						break;
					}case 7:
					{
						Iterator it=stk.iterator();
						x="";
						while(it.hasNext())
						{
							Object ob=it.next();
							x+=ob.toString()+"\n";
						}
						odos.writeUTF(x);
						break;
					}case 8:
					{
						System.exit(0);
						sk.close();
					}
				}
			}
		}catch(EOFException exp){}
		catch(Exception ep){}
	}
}