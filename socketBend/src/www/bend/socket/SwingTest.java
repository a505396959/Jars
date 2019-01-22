package www.bend.socket;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SwingTest extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame jf;
	private static JPanel panel ;
	
	//������ť
	private static JButton btn ;
	private JButton nextPage;
	private JButton frontPage;
	private JButton jumpPage;
	
	//�����ı���
	final JTextField textIp;
	final JTextField textPort;
	final JTextField textRaise;
	final JTextField textFall;
	//ҳ���ı���
	private JTextField textPage;
	
	//����label
	private JLabel labelIp;
	private JLabel labelPort;
	private JLabel labelRaise;
	private JLabel labelFall;
	//�������label
//	private static JLabel labelHelp;
	//����״̬label
	private static JLabel labelBendState;
	//ҳ��label
	private static JLabel labelPage;
	
	//δ����
	//����label������ʾ��
	private JLabel labelIpErro;
	private JLabel labelPortErro;
	private JLabel labelRaiseErro;
	private JLabel labelFallErro;
	//�հ�label
	private JLabel labelblank;
	//����socket��ʾlabel
	private static JLabel labelErroSuccess;
	
	//������
	private static JTable table;
	//���������
	private static String[] head;
	
	//���������
	private JScrollPane scrollPane;
	
	//
	private static DefaultTableModel tableModel;
	
	//��ҳҳ��
	static int page=1;
//	private Object [][]data;
//	private Paging paging;
	
//	private Boolean mark=true;
	private SubmitClient submitClient;
//	private static Vector<String> vdata;

	public SwingTest(){
		 	jf = new JFrame("����ģ����");
	        jf.setSize(504, 624);//���ڿ�͸�
	        jf.setResizable(false);//���ƴ��ڲ�������
	        jf.setLayout(null);//�����Զ��岼��
	        jf.setLocationRelativeTo(null);//���ô��������ָ�������λ��
	        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        // ʹ�ñ������ͶԻ��������۱仯
//	        JFrame.setDefaultLookAndFeelDecorated(true);
	        
	        // Windows���
//	        String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	        // Windows Classic���
//	        String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
	        // Motif���
//	        String lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	        // ��ǰϵͳ�ķ��
//	        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
	        // �ɿ�ƽ̨��Ĭ�Ϸ��
//	        String lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
	        // Metal��� (Ĭ��)
	      /*  String lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
	        try {
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (Exception e) {
				e.printStackTrace();
			}*/
	        panel = new JPanel();

	        // �����ı���ָ���ɼ�����Ϊ8��
	        textIp = new JTextField(8);
	        textPort = new JTextField(8);
	        textRaise = new JTextField(8);
	        textFall = new JTextField(8);
//	        textField.setFont(new Font(null, Font.PLAIN, 20));
	        //����label
	        labelIp =new JLabel();
	        labelPort =new JLabel();
	        labelRaise =new JLabel();
	        labelFall =new JLabel();
	        
	        //����label������ʾ
	        labelIpErro =new JLabel();
	        labelPortErro =new JLabel();
	        labelRaiseErro =new JLabel();
	        labelFallErro =new JLabel();
	        
	        //�ı���Ĭ���ı�
	        textIp.setText("192.168.1.242");
	        textPort.setText("5000");
	        textRaise.setText("0");
	        textFall.setText("1");
	        
	        //label�ı�
	        labelIp.setText("�����˿�IP��");
	        labelPort.setText("�����˿ںţ�");
	        labelRaise.setText("̧��ָ��λ��");
	        labelFall.setText("���ָ��λ��");
	        
	        //labelErroĬ���ı�
	        labelIpErro.setText("        ");
	        labelPortErro.setText("        ");
	        labelRaiseErro.setText("        ");
	        labelFallErro.setText("        ");
	        
	        panel.add(labelIp);//panel.add������Ž���������
	        panel.add(textIp);
	        panel.add(labelIpErro);
	        
	        panel.add(labelPort);
	        panel.add(textPort);
	        panel.add(labelPortErro);
	        
	        panel.add(labelRaise);
	        panel.add(textRaise);
	        panel.add(labelRaiseErro);
	        
	        panel.add(labelFall);
	        panel.add(textFall);
	        panel.add(labelFallErro);
//	        final Thread thread = new Thread(submitClient);
	        
	        
	        // ����һ����ť��������ȡ�ı����е��ı�
	        btn = new JButton("��ʼ");
	        btn.setPreferredSize(new Dimension(115,50));
	        btn.setFont(new Font(null, Font.PLAIN, 20));
	        btn.addActionListener(new ActionListener() {
	            @SuppressWarnings("deprecation")
				@Override
	            public void actionPerformed(ActionEvent e) {
	            	//�ж��Ƿ���ڼ���socket�̣߳����򴴽��������ж�
	            	if(submitClient!=null &&submitClient.isAlive()&&!submitClient.isInterrupted()){
	            		btn.setText("���¿�ʼ");
	            		if(labelBendState!=null){
	            			labelBendState.setText("");
	            		}
	            		if(labelErroSuccess!=null){
	            			labelErroSuccess.setText("");
	            		}
	            		System.out.println(submitClient.getId());
	            		/*try {
							Thread.sleep(1000);
//							Thread.sleep(3000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
	            	    System.out.println( "Interrupting thread..." ); 
	            	    System.out.println("�߳��Ƿ��жϣ�" + submitClient.isInterrupted()); */
            	        try {
//	            	        submitClient.setExit(false);
            	        	submitClient.stop();
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} 
            	      /*  try {
							Thread.sleep( 1000 );
//							Thread.sleep( 3000 );
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}*/
            	        System.out.println("Stopping application..." ); 
            	        System.out.println("�߳��Ƿ��жϣ�" + submitClient.isInterrupted()); 
            	        submitClient.soketConnectClose();//����Ƿ���socketδClose;
            	        if(submitClient!=null){
            	        	submitClient=null;
            	        }
	            	}else{
	            		submitClient = new SubmitClient();
	            		btn.setText("��������");
	            		if(labelErroSuccess!=null){
            		  	  	labelErroSuccess.setText("");
	            		}
	            		submitClient.setIp(textIp.getText());
	            		submitClient.setPort(Integer.parseInt(textPort.getText()));
	            		submitClient.setRaiseStart(Integer.parseInt(textRaise.getText()));
	            		submitClient.setFallStart(Integer.parseInt(textFall.getText()));
	            		submitClient.start();
//	                    System.out.println(submitClient.getId());
	            		System.out.println("�߳̿�����־��"+submitClient.isAlive());//submitClient.isAlive());
//	                    System.out.println("�߳��ն˱�־4��"+submitClient.isAlive());//submitClient.isAlive());
	            	}
	            }
	        });
	        
	        labelblank=new JLabel();
//	        labelHelp =new JLabel();
	        labelblank.setText("    ");
	        panel.add(btn);
	        panel.add(labelblank);
	        
	        //��ʾ����״̬
	        if(labelBendState==null){
				labelBendState = new JLabel();
				panel.add(labelBendState);
			}
//	        panel.add(labelHelp);
	        
	        //����ʼ��
	        head=new String[] {"����̧��", "̧��ʱ��", "�������","���ʱ��"};
//	        Object[][] data =Paging.queryData(head.length);
//	        table = new JTable(data, head);//��ʼ�����
//	        table.getColumnModel().getColumn(0).setPreferredWidth(-50);//���õ�һ�п�
//	        table.getColumnModel().getColumn(1).setPreferredWidth(50);//���õڶ��п�
//	        table.getColumnModel().getColumn(2).setPreferredWidth(-100);//���õ����п�
//	        table.getColumnModel().getColumn(3).setPreferredWidth(50);//���õ����п�
	        table = new JTable();
	        updateTable();
	        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();//��Ԫ����Ⱦ��
	        tcr.setHorizontalAlignment(JLabel.CENTER);//��������ʾ
	        
	        table.setDefaultRenderer(Object.class, tcr);//������Ⱦ��
	        
	        scrollPane=new JScrollPane(table);
//	        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//����ˮƽ�������ܻ����
	        scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //���ô�ֱ�������ܻ����
	        panel.add(scrollPane);

	        
//	        labelPage = new JLabel("3");
//	        panel.add(labelPage);
			
	        //ʵ�����ϡ��¡���תҳ�水ť
	        nextPage = new JButton(">");
	        frontPage = new JButton("<");
	        jumpPage = new JButton("��ת");
	        nextPage.setFont(new Font("",0,15));
	        frontPage.setFont(new Font("",0,15));
	        jumpPage.setFont(new Font("",0,15));
	        //���nextPage����ѯ��һҳ����
	        nextPage.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if(Paging.lastPage>=page+1){
	            		page++;
	            		textPage.setText(Integer.toString(page));
	            		updateTable();
	            	}	
	            	
	            }
	        });
	        //���frontPage����ѯ��һҳ����
	        frontPage.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if(page>=2){
	            		page--;
	            		textPage.setText(Integer.toString(page));
	            		updateTable();
	            	}
	            }
	        });
	        //���jumpPage����ѯtextPage�ı�����ҳ��
	        jumpPage.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	int i =Integer.parseInt(textPage.getText());
	            	if(i>Paging.lastPage){
	            		i=Paging.lastPage;
	            	}else if(i<1){
	            		i=1;
	            	}
	            	page = i;
	            	textPage.setText(Integer.toString(page));
	            	updateTable();
	            }
	        });
	        
	        labelPage = new JLabel();
	        updatalabelPage();
	        textPage = new JTextField(2);
	        textPage.setText(Integer.toString(page));
	        textPage.setHorizontalAlignment(JTextField.CENTER);
	        
	        panel.add(frontPage);
	        panel.add(textPage);
	        panel.add(labelPage);
	        panel.add(nextPage);
	        panel.add(jumpPage);
	        
	        labelErroSuccess =new JLabel();
	        panel.add(labelErroSuccess);
	        
	        jf.setContentPane(panel);//���������Ž�����������
	        jf.setVisible(true);//���ƴ���
	}
	/**
	 * ����״̬��ʾ
	 * @param state
	 */
	public static void bendState(String state){
		if(state.equals("̧��")){
			labelBendState.setForeground(Color.red);
		}else if(state.equals("���")){
			labelBendState.setForeground(Color.black);
		}
//		labelBendState.setHorizontalAlignment(JLabel.RIGHT);//����
//		labelBendState.setBounds(60,60,390,1003);
		labelBendState.setFont(new Font("",1,18));
		labelBendState.setText(state);
	}
	/**
	 * ���±��
	 */
	public static void updateTable(){
//		table.setModel(Paging.queryData(head.length),head);
//		table.validate();
//		table.updateUI();
		//����
        tableModel =new DefaultTableModel(head,0);
        List<BendEntity> list = Paging.getPageContentByApi();//��ʼֵstart=1,range=5
        Collections.reverse(list);//list���鵹��
        for(BendEntity s : list)
        {
        Vector<String> vdata = new Vector<String>();
        vdata.add(s.getRaise());
        vdata.add(s.getRaiseTime());
        vdata.add(s.getFall());
        vdata.add(s.getFallTime());
        tableModel.addRow(vdata);
        }
        table.setModel(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(-50);//���õ�һ�п�
        table.getColumnModel().getColumn(1).setPreferredWidth(50);//���õڶ��п�
        table.getColumnModel().getColumn(2).setPreferredWidth(-100);//���õ����п�
        table.getColumnModel().getColumn(3).setPreferredWidth(50);//���õ����п�
		System.out.println("���±����Ϣ");
	}
	
	/**
	 * �������ҳ��
	 */
	public static void updatalabelPage(){
		labelPage.setText("/"+Integer.toString(Paging.lastPage));
		labelPage.setFont(new Font("",0,15));
	}
	/**
	 * sokert����״̬
	 * @param info
	 */
	public static void failOrSuccessInfo(String info){
//		if(info.equals("����")){
//			labelErroSuccess.setHorizontalAlignment(JLabel.RIGHT);//����
//			labelErroSuccess.setBounds(60,60,390,1003);
//		}else{
//			labelErroSuccess.setFont(new Font("",0,11));
//			labelErroSuccess.setForeground(Color.red);
//		}
//		System.out.println("info��"+info);
		if(info.equals("����")){
			labelErroSuccess.setForeground(Color.black);
			labelErroSuccess.setFont(new Font("",0,15));
		}else{
			labelErroSuccess.setForeground(Color.red);
			labelErroSuccess.setFont(new Font("",0,11));
		}
		labelErroSuccess.setText(info);
	}
	
	public static void erroBtn(String btnInfo){
		btn.setText(btnInfo);
	}
	
//	public static void labelSocket(String info){
//		labelHelp.setText(info);
//	}
	
	public static void main (String[] args){
//		SwingTest swingTest =new SwingTest();
		new SwingTest();
	}

}
