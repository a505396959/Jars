package www.bend.test;

import java.awt.AWTException;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import www.bend.socket.SubmitClient;

public class Main {
	static int start2;
    public static void main(String[] args) throws AWTException {
        JFrame jf = new JFrame("���Դ���");
        jf.setSize(300, 300);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        // �����ı���ָ���ɼ�����Ϊ8��
        final JTextField textField = new JTextField(8);
        textField.setFont(new Font(null, Font.PLAIN, 20));
        panel.add(textField);

        // ����һ����ť��������ȡ�ı����е��ı�
        JButton btn = new JButton("�ύ");
        btn.setFont(new Font(null, Font.PLAIN, 20));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                System.out.println("�ύ: " + textField.getText());
//                SubmitClient submitClient = new SubmitClient();
//               submitClient.setStart(Integer.parseInt(textField.getText()));
//               start2 = submitClient.getStart2();
//                submitClient.run(start2);
//                new SubmitClient().start(); 
//                submitClient.run( Integer.parseInt(textField.getText()));
            }
        });
        panel.add(btn);
        
     // ����һ����ť��������ȡ�ı����е��ı�
        JButton btn2 = new JButton("ֹͣ");
        btn2.setFont(new Font(null, Font.PLAIN, 20));
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ֹͣ");
            }
        });
        panel.add(btn2);
        

        jf.setContentPane(panel);
        jf.setVisible(true);
        System.out.println(start2);
    }

}