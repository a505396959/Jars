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
	
	//创建按钮
	private static JButton btn ;
	private JButton nextPage;
	private JButton frontPage;
	private JButton jumpPage;
	
	//定义文本框
	final JTextField textIp;
	final JTextField textPort;
	final JTextField textRaise;
	final JTextField textFall;
	//页码文本框
	private JTextField textPage;
	
	//定义label
	private JLabel labelIp;
	private JLabel labelPort;
	private JLabel labelRaise;
	private JLabel labelFall;
	//定义帮助label
//	private static JLabel labelHelp;
	//档杆状态label
	private static JLabel labelBendState;
	//页码label
	private static JLabel labelPage;
	
	//未开发
	//定义label错误提示框
	private JLabel labelIpErro;
	private JLabel labelPortErro;
	private JLabel labelRaiseErro;
	private JLabel labelFallErro;
	//空白label
	private JLabel labelblank;
	//定义socket提示label
	private static JLabel labelErroSuccess;
	
	//定义表格
	private static JTable table;
	//定义表格标题
	private static String[] head;
	
	//定义滚动条
	private JScrollPane scrollPane;
	
	//
	private static DefaultTableModel tableModel;
	
	//分页页码
	static int page=1;
//	private Object [][]data;
//	private Paging paging;
	
//	private Boolean mark=true;
	private SubmitClient submitClient;
//	private static Vector<String> vdata;

	public SwingTest(){
		 	jf = new JFrame("档杆模拟器");
	        jf.setSize(504, 624);//窗口宽和高
	        jf.setResizable(false);//限制窗口不可缩放
	        jf.setLayout(null);//设置自定义布局
	        jf.setLocationRelativeTo(null);//设置窗口相对于指定组件的位置
	        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        // 使得标题栏和对话框跟随外观变化
//	        JFrame.setDefaultLookAndFeelDecorated(true);
	        
	        // Windows风格
//	        String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	        // Windows Classic风格
//	        String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
	        // Motif风格
//	        String lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	        // 当前系统的风格
//	        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
	        // 可跨平台的默认风格
//	        String lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
	        // Metal风格 (默认)
	      /*  String lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
	        try {
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (Exception e) {
				e.printStackTrace();
			}*/
	        panel = new JPanel();

	        // 创建文本框，指定可见列数为8列
	        textIp = new JTextField(8);
	        textPort = new JTextField(8);
	        textRaise = new JTextField(8);
	        textFall = new JTextField(8);
//	        textField.setFont(new Font(null, Font.PLAIN, 20));
	        //创建label
	        labelIp =new JLabel();
	        labelPort =new JLabel();
	        labelRaise =new JLabel();
	        labelFall =new JLabel();
	        
	        //创建label错误提示
	        labelIpErro =new JLabel();
	        labelPortErro =new JLabel();
	        labelRaiseErro =new JLabel();
	        labelFallErro =new JLabel();
	        
	        //文本框默认文本
	        textIp.setText("192.168.1.242");
	        textPort.setText("5000");
	        textRaise.setText("0");
	        textFall.setText("1");
	        
	        //label文本
	        labelIp.setText("监听端口IP：");
	        labelPort.setText("监听端口号：");
	        labelRaise.setText("抬杆指令位：");
	        labelFall.setText("落杆指令位：");
	        
	        //labelErro默认文本
	        labelIpErro.setText("        ");
	        labelPortErro.setText("        ");
	        labelRaiseErro.setText("        ");
	        labelFallErro.setText("        ");
	        
	        panel.add(labelIp);//panel.add把组件放进面板组件中
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
	        
	        
	        // 创建一个按钮，点击后获取文本框中的文本
	        btn = new JButton("开始");
	        btn.setPreferredSize(new Dimension(115,50));
	        btn.setFont(new Font(null, Font.PLAIN, 20));
	        btn.addActionListener(new ActionListener() {
	            @SuppressWarnings("deprecation")
				@Override
	            public void actionPerformed(ActionEvent e) {
	            	//判断是否存在监听socket线程，无则创建，有则中断
	            	if(submitClient!=null &&submitClient.isAlive()&&!submitClient.isInterrupted()){
	            		btn.setText("重新开始");
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
	            	    System.out.println("线程是否中断：" + submitClient.isInterrupted()); */
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
            	        System.out.println("线程是否中断：" + submitClient.isInterrupted()); 
            	        submitClient.soketConnectClose();//检查是否有socket未Close;
            	        if(submitClient!=null){
            	        	submitClient=null;
            	        }
	            	}else{
	            		submitClient = new SubmitClient();
	            		btn.setText("正在启动");
	            		if(labelErroSuccess!=null){
            		  	  	labelErroSuccess.setText("");
	            		}
	            		submitClient.setIp(textIp.getText());
	            		submitClient.setPort(Integer.parseInt(textPort.getText()));
	            		submitClient.setRaiseStart(Integer.parseInt(textRaise.getText()));
	            		submitClient.setFallStart(Integer.parseInt(textFall.getText()));
	            		submitClient.start();
//	                    System.out.println(submitClient.getId());
	            		System.out.println("线程开启标志："+submitClient.isAlive());//submitClient.isAlive());
//	                    System.out.println("线程终端标志4："+submitClient.isAlive());//submitClient.isAlive());
	            	}
	            }
	        });
	        
	        labelblank=new JLabel();
//	        labelHelp =new JLabel();
	        labelblank.setText("    ");
	        panel.add(btn);
	        panel.add(labelblank);
	        
	        //显示档杆状态
	        if(labelBendState==null){
				labelBendState = new JLabel();
				panel.add(labelBendState);
			}
//	        panel.add(labelHelp);
	        
	        //表格初始化
	        head=new String[] {"档杆抬杆", "抬杆时间", "档杆落杆","落杆时间"};
//	        Object[][] data =Paging.queryData(head.length);
//	        table = new JTable(data, head);//初始化表格
//	        table.getColumnModel().getColumn(0).setPreferredWidth(-50);//设置第一列宽
//	        table.getColumnModel().getColumn(1).setPreferredWidth(50);//设置第二列宽
//	        table.getColumnModel().getColumn(2).setPreferredWidth(-100);//设置第三列宽
//	        table.getColumnModel().getColumn(3).setPreferredWidth(50);//设置第四列宽
	        table = new JTable();
	        updateTable();
	        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();//单元格渲染器
	        tcr.setHorizontalAlignment(JLabel.CENTER);//表格居中显示
	        
	        table.setDefaultRenderer(Object.class, tcr);//设置渲染器
	        
	        scrollPane=new JScrollPane(table);
//	        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//设置水平滚动条总会出现
	        scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //设置垂直滚动条总会出现
	        panel.add(scrollPane);

	        
//	        labelPage = new JLabel("3");
//	        panel.add(labelPage);
			
	        //实例化上、下、跳转页面按钮
	        nextPage = new JButton(">");
	        frontPage = new JButton("<");
	        jumpPage = new JButton("跳转");
	        nextPage.setFont(new Font("",0,15));
	        frontPage.setFont(new Font("",0,15));
	        jumpPage.setFont(new Font("",0,15));
	        //点击nextPage，查询下一页数据
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
	        //点击frontPage，查询上一页数据
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
	        //点击jumpPage，查询textPage文本框中页码
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
	        
	        jf.setContentPane(panel);//把面板组件放进窗口容器中
	        jf.setVisible(true);//绘制窗口
	}
	/**
	 * 档杆状态提示
	 * @param state
	 */
	public static void bendState(String state){
		if(state.equals("抬杆")){
			labelBendState.setForeground(Color.red);
		}else if(state.equals("落杆")){
			labelBendState.setForeground(Color.black);
		}
//		labelBendState.setHorizontalAlignment(JLabel.RIGHT);//居右
//		labelBendState.setBounds(60,60,390,1003);
		labelBendState.setFont(new Font("",1,18));
		labelBendState.setText(state);
	}
	/**
	 * 更新表格
	 */
	public static void updateTable(){
//		table.setModel(Paging.queryData(head.length),head);
//		table.validate();
//		table.updateUI();
		//测试
        tableModel =new DefaultTableModel(head,0);
        List<BendEntity> list = Paging.getPageContentByApi();//初始值start=1,range=5
        Collections.reverse(list);//list数组倒序
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
        table.getColumnModel().getColumn(0).setPreferredWidth(-50);//设置第一列宽
        table.getColumnModel().getColumn(1).setPreferredWidth(50);//设置第二列宽
        table.getColumnModel().getColumn(2).setPreferredWidth(-100);//设置第三列宽
        table.getColumnModel().getColumn(3).setPreferredWidth(50);//设置第四列宽
		System.out.println("更新表格信息");
	}
	
	/**
	 * 更新最大页码
	 */
	public static void updatalabelPage(){
		labelPage.setText("/"+Integer.toString(Paging.lastPage));
		labelPage.setFont(new Font("",0,15));
	}
	/**
	 * sokert连接状态
	 * @param info
	 */
	public static void failOrSuccessInfo(String info){
//		if(info.equals("就绪")){
//			labelErroSuccess.setHorizontalAlignment(JLabel.RIGHT);//居右
//			labelErroSuccess.setBounds(60,60,390,1003);
//		}else{
//			labelErroSuccess.setFont(new Font("",0,11));
//			labelErroSuccess.setForeground(Color.red);
//		}
//		System.out.println("info："+info);
		if(info.equals("就绪")){
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
