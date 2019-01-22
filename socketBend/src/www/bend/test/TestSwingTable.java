package www.bend.test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class TestSwingTable extends JFrame{
	   private JFrame J;
		private DefaultTableModel model;
		private JTable table;
	 
	 
		void Hangban() throws ClassNotFoundException ,SQLException{
	 
			J = new JFrame();
			J.setTitle("������Ϣ");
			J.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			J.setVisible(true);
			J.setBounds(230, 130, 620, 400);
			AbstractTableModel tm;
	 
			final String[] title = { "�����", "���ʱ��", "ʼ����", "Ŀ�ĵ�", "��Ʊ��", "ͷ�Ȳ�Ʊ��",
					"�����Ʊ��", "���ò�Ʊ��" };
			JScrollPane jsp;
	 
			@SuppressWarnings({ "rawtypes", "unchecked" })
			final Vector<Vector<Comparable>> vect = new Vector();// ʵ��������
			tm = new AbstractTableModel() {// ʵ��AbstractTableModel�ĳ��󷽷�
				/**
			 * 
			 */
				private static final long serialVersionUID = 1L;
	 
				public int getColumnCount() {
					return title.length;
				}
	 
				public int getRowCount() {
					// TODO �Զ����ɵķ������
					return vect.size();
				}
	 
				public Object getValueAt(int row, int column) {
					// TODO �Զ����ɵķ������
					if (!vect.isEmpty())
						return (((Vector<?>) vect.elementAt(row)).elementAt(column));
					else
						return null;
				}
	 
				public String getColumnName(int column) {
					return title[column];// ���ñ������
				}
	 
				public void setValueAt(Object value, int row, int column) {
				}
	 
				public Class<? extends Object> getColumnClass(int c) {
					return getValueAt(0, c).getClass();
				}// ȡ������������
	 
				public boolean isCellEditable(int row, int column) {
	 
					return false;
				}// ���õ�Ԫ�񲻿ɱ༭
			};
			table = new JTable(tm);
			table.setToolTipText("��ʾ���е�����");
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���ñ������ߴ�ģʽ
			table.setCellSelectionEnabled(false);// ���õ�Ԫ��ѡ��ʽ
			table.setShowVerticalLines(true);// �����Ƿ���ʾ��Ԫ���ķָ���
			table.setShowHorizontalLines(true);
	 
			jsp = new JScrollPane(table);
	 
			J.add(jsp);
	 
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
	 
			Class.forName("com.hxtt.sql.access.AccessDriver");
			con = (Connection) DriverManager.getConnection("jdbc:odbc:tourist_access");
			stmt = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("select * from flight ");
	 
			vect.removeAllElements();// ��ʼ����������
			tm.fireTableStructureChanged();// ���±������
			while (rs.next()) {
				@SuppressWarnings("rawtypes")
				Vector<Comparable> v = new Vector<Comparable>();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getInt(5));
				v.add(rs.getInt(7));
				v.add(rs.getInt(6));
				v.add(rs.getInt(8));
				vect.add(v);
				tm.fireTableStructureChanged();
			}
	 
			
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		}
}
