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
 * ����socket
 * @author lin
 *
 */
public class SubmitClient extends Thread {

	private int raiseStart;//�û������̧��λ0~3
	private int fallStart;//�û���������λ0~3
	private String ip ;//�û�����ļ���socket ��ַ
	private int port;//�û�����ļ���socket �˿�
	private Socket socket;
	private String stateSuccess;
	Connection conn;
	private String  raiseBendCommand ;//̧��λָ��
    private String  fallBendCommand ;//���λָ��
    private int infoNub;//����˷�����Ϣ�ĳ���
    private String btnInfo;//btn�ı���Ϣ
    BendEntity bendEntity;
    
    public void soketConnectClose(){
    	if(socket!=null){
    		try {
				socket.close();
				System.out.println("�ر�socket����");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
   /* //�ж��Ƿ���socket������
    public boolean socketConnect(){
    	if(socket==null){
    		return false;
    	}else{
    		return true;
    	}
    }
    //�ر�socket
	public  void socketClose(){
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
//	public static void main(String[] args) throws Exception {
//		new SubmitClient().start();
//	}
	
	public void run() {
		try {
			System.out.println("̧��ָ��λ��"+raiseStart);
			System.out.println("���ָ��λ"+fallStart);
			while (true) {
//				socket=new Socket(ip,port);
				socket=new Socket();
				socket.connect(new InetSocketAddress(ip,port), 1000);
				if(socket!=null){
					btnInfo="��������";
					SwingTest.erroBtn(btnInfo);
					stateSuccess="����";
					SwingTest.failOrSuccessInfo(stateSuccess);
					System.out.println("����socket�ɹ�");
				}
				 //����Socket����
	            //�ж�socket�Ƿ�������
	            InputStream inputStream=socket.getInputStream();//��ȡһ�������������շ���˵���Ϣ
	            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//��װ���ַ��������Ч��
	            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//������
	            String info=null;//��ʱ����
	            String raise = null;//̧��ָ��
	            String fall = null;//���ָ��
	            String raiseTime = null ;//̧��ʱ��
	            String fallTime = null ;//���ʱ��
//	            int infoNub=0;//����˷�����Ϣ�ĳ���
//	            String  raiseBendCommand = null;
//	            String  fallBendCommand = null;
//	            int start=3;//���ø˵Ķ˿�λ0~3�����������󣬵�һλΪ0
//	            String instructions=null;//ƴ��ָ��
//	            int raiseF=raiseStart+1;//�˿�λʵ��λ�á�����������
//	            int fallF =fallStart+1;
	            while((info=bufferedReader.readLine())!=null ){
	            	System.out.println("�ͻ��˽��շ���˷�����Ϣ��"+info);
	            	BendCommand(info);
//	                infoNub=info.length();
//	                //��socketָ���жϵ���״̬
//	                raiseBendCommand =info.substring(infoNub-raiseStart-1,infoNub-raiseStart);
//	                fallBendCommand =info.substring(infoNub-fallStart-1,infoNub-fallStart);
	                if(raiseBendCommand.equals("1")&&fallBendCommand.equals("1")){
	                	String Erro="   Erro:����ָ���̧�ˣ�����ˣ�����̵����Ƿ�λ   ";
	                	SwingTest.bendState("");
	                	SwingTest.failOrSuccessInfo(Erro);
	                	System.out.println(Erro);
	                	while(( info=bufferedReader.readLine())!=null ){
	                		System.out.println("�ͻ��˽��շ���˷�����Ϣ��"+info);
	                		BendCommand(info);
//	                		info=temp;
//	                		infoNub=info.length();
//	                		raiseBendCommand =info.substring(infoNub-raiseStart-1,infoNub-raiseStart);
//	    	                fallBendCommand =info.substring(infoNub-fallStart-1,infoNub-fallStart);
	                		if(raiseBendCommand.equals("0")&&fallBendCommand.equals("0")){
	                			SwingTest.failOrSuccessInfo(stateSuccess);
	                			//��ʼ��̧��ʱ��
	                			if(raiseTime!=null){
	                				raiseTime=null;
	                			}
	                			//��ʼ�����ʱ��
	                			if(fallTime!=null){
	                				fallTime=null;
	                			}
	                			break;
	                		}
	                	}
	                }else if(raiseBendCommand.equals("1")){
	                	raise="̧��";
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
	                	fall="���";
	                	SwingTest.bendState(fall);
	                	fallTime=df.format(new Date());
	                	System.out.println(fall);
	                	Paging.sqlInsert(raise,fall, raiseTime, fallTime);
	                	SwingTest.updateTable();
	                	break;
	                }
	                
                	//���socketָ���жϵ���״̬
//                	instructions=info.substring(infoNub-fallStart-1,infoNub-fallStart)+info.substring(infoNub-raiseStart-1,infoNub-raiseStart);
//	                System.out.println("�ͻ��˽��շ���˷�����Ϣ��"+info);
//	                System.out.println("��ȡ����ַ�����"+instructions);
//	                if(instructions.equals("01")){
//	                	raise="̧��";
//	                	SwingTest.bendState(raise);
//	                	if(raiseTime!=null){
//	                		Paging.sqlInsert(raise,fall, raiseTime, fallTime);
//	                		SwingTest.updateTable();
//	                		SwingTest.updatalabelPage();
//	                	}
//	                	raiseTime=df.format(new Date());
//                		System.out.println(raise);
//	                }else if (instructions.equals("11") || instructions.equals("10")){
//	                	fall="���";
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
            String info="  Erro:����socketʧ�ܣ����������Ƿ��������������  ";
            SwingTest.failOrSuccessInfo(info);
            btnInfo="��������";
            SwingTest.erroBtn(btnInfo);
            System.out.println(info);
        }
	}
	//�õ�̧�˺����ָ��λ����
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


