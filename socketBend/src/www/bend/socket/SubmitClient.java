package www.bend.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.Connection;

/**
 * 监听socket
 * @author lin
 *
 */
public class SubmitClient extends Thread {

	private int raiseStart;//用户输入的抬杆位0~3
	private int fallStart;//用户输入的落杆位0~3
	private String ip ;//用户输入的监听socket 地址
	private int port;//用户输入的监听socket 端口
	private Socket socket;
	private String stateSuccess;
	Connection conn;
	private String  raiseBendCommand ;//抬杆位指令
    private String  fallBendCommand ;//落杆位指令
    private int infoNub;//服务端发送信息的长度
    private String btnInfo;//btn文本信息
    BendEntity bendEntity;
    
    public void soketConnectClose(){
    	if(socket!=null){
    		try {
				socket.close();
				System.out.println("关闭socket连接");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
   /* //判断是否有socket被创建
    public boolean socketConnect(){
    	if(socket==null){
    		return false;
    	}else{
    		return true;
    	}
    }
    //关闭socket
	public  void socketClose(){
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//	public static void main(String[] args) throws Exception {
//		new SubmitClient().start();
//	}
	
	public void run() {
		try {
			System.out.println("抬杆指令位："+raiseStart);
			System.out.println("落杆指令位"+fallStart);
			while (true) {
//				socket=new Socket(ip,port);
				socket=new Socket();
				socket.connect(new InetSocketAddress(ip,port), 1000);
				if(socket!=null){
					btnInfo="正在运行";
					SwingTest.erroBtn(btnInfo);
					stateSuccess="就绪";
					SwingTest.failOrSuccessInfo(stateSuccess);
					System.out.println("连接socket成功");
				}
				 //创建Socket对象
	            //判断socket是否连接上
	            InputStream inputStream=socket.getInputStream();//获取一个输入流，接收服务端的信息
	            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//包装成字符流，提高效率
	            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//缓冲区
	            String info=null;//临时变量
	            String raise = null;//抬杆指令
	            String fall = null;//落杆指令
	            String raiseTime = null ;//抬杆时间
	            String fallTime = null ;//落杆时间
//	            int infoNub=0;//服务端发送信息的长度
//	            String  raiseBendCommand = null;
//	            String  fallBendCommand = null;
//	            int start=3;//设置杆的端口位0~3——从右往左，第一位为0
//	            String instructions=null;//拼接指令
//	            int raiseF=raiseStart+1;//端口位实际位置——从右往左
//	            int fallF =fallStart+1;
	            while((info=bufferedReader.readLine())!=null ){
	            	System.out.println("客户端接收服务端发送信息："+info);
	            	BendCommand(info);
//	                infoNub=info.length();
//	                //单socket指令判断档杆状态
//	                raiseBendCommand =info.substring(infoNub-raiseStart-1,infoNub-raiseStart);
//	                fallBendCommand =info.substring(infoNub-fallStart-1,infoNub-fallStart);
	                if(raiseBendCommand.equals("1")&&fallBendCommand.equals("1")){
	                	String Erro="   Erro:本次指令既抬杆，又落杆，请检查继电器是否复位   ";
	                	SwingTest.bendState("");
	                	SwingTest.failOrSuccessInfo(Erro);
	                	System.out.println(Erro);
	                	while(( info=bufferedReader.readLine())!=null ){
	                		System.out.println("客户端接收服务端发送信息："+info);
	                		BendCommand(info);
//	                		info=temp;
//	                		infoNub=info.length();
//	                		raiseBendCommand =info.substring(infoNub-raiseStart-1,infoNub-raiseStart);
//	    	                fallBendCommand =info.substring(infoNub-fallStart-1,infoNub-fallStart);
	                		if(raiseBendCommand.equals("0")&&fallBendCommand.equals("0")){
	                			SwingTest.failOrSuccessInfo(stateSuccess);
	                			//初始化抬杆时间
	                			if(raiseTime!=null){
	                				raiseTime=null;
	                			}
	                			//初始化落杆时间
	                			if(fallTime!=null){
	                				fallTime=null;
	                			}
	                			break;
	                		}
	                	}
	                }else if(raiseBendCommand.equals("1")){
	                	raise="抬杆";
	                	SwingTest.bendState(raise);
//	                	SwingTest.labelSocket(info);
	                	if(raiseTime!=null){
	                		Paging.sqlInsert(raise,fall, raiseTime, fallTime);
	                		SwingTest.updateTable();
	                		SwingTest.updatalabelPage();
	                	}
	                	raiseTime=df.format(new Date());
                		System.out.println(raise);
	                }else if(fallBendCommand.equals("1")){
//	                	SwingTest.labelSocket(info);
	                	fall="落杆";
	                	SwingTest.bendState(fall);
	                	fallTime=df.format(new Date());
	                	System.out.println(fall);
	                	Paging.sqlInsert(raise,fall, raiseTime, fallTime);
	                	SwingTest.updateTable();
	                	break;
	                }
	                
                	//组合socket指令判断档杆状态
//                	instructions=info.substring(infoNub-fallStart-1,infoNub-fallStart)+info.substring(infoNub-raiseStart-1,infoNub-raiseStart);
//	                System.out.println("客户端接收服务端发送信息："+info);
//	                System.out.println("截取后的字符串："+instructions);
//	                if(instructions.equals("01")){
//	                	raise="抬杆";
//	                	SwingTest.bendState(raise);
//	                	if(raiseTime!=null){
//	                		Paging.sqlInsert(raise,fall, raiseTime, fallTime);
//	                		SwingTest.updateTable();
//	                		SwingTest.updatalabelPage();
//	                	}
//	                	raiseTime=df.format(new Date());
//                		System.out.println(raise);
//	                }else if (instructions.equals("11") || instructions.equals("10")){
//	                	fall="落杆";
//	                	SwingTest.bendState(fall) ;
//	                	fallTime=df.format(new Date());
//	                	System.out.println(fall);
//	                	Paging.sqlInsert(raise,fall, raiseTime, fallTime);
//	                	SwingTest.updateTable();
//	                	break;
//	                }
	                
	            }
	            bufferedReader.close();
	            inputStream.close();
			}

		} catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            String info="  Erro:连接socket失败，请检查设置是否错误，再重新启动  ";
            SwingTest.failOrSuccessInfo(info);
            btnInfo="重新启动";
            SwingTest.erroBtn(btnInfo);
            System.out.println(info);
        }
	}
	//得到抬杆和落杆指令位数据
	public void BendCommand(String info){
		infoNub=info.length();
		raiseBendCommand =info.substring(infoNub-raiseStart-1,infoNub-raiseStart);
        fallBendCommand =info.substring(infoNub-fallStart-1,infoNub-fallStart);
	}
	

	public String getIp() {
		return ip;
	}
	
	public int getRaiseStart() {
		return raiseStart;
	}

	public void setRaiseStart(int raiseStart) {
		this.raiseStart = raiseStart;
	}

	public int getFallStart() {
		return fallStart;
	}

	public void setFallStart(int fallStart) {
		this.fallStart = fallStart;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}

}


