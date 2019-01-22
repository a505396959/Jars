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
			J.setTitle("航班信息");
			J.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			J.setVisible(true);
			J.setBounds(230, 130, 620, 400);
			AbstractTableModel tm;
	 
			final String[] title = { "航班号", "起飞时间", "始发地", "目的地", "总票数", "头等舱票数",
					"商务舱票数", "经济舱票数" };
			JScrollPane jsp;
	 
			@SuppressWarnings({ "rawtypes", "unchecked" })
			final Vector<Vector<Comparable>> vect = new Vector();// 实例化向量
			tm = new AbstractTableModel() {// 实现AbstractTableModel的抽象方法
				/**
			 * 
			 */
				private static final long serialVersionUID = 1L;
	 
				public int getColumnCount() {
					return title.length;
				}
	 
				public int getRowCount() {
					// TODO 自动生成的方法存根
					return vect.size();
				}
	 
				public Object getValueAt(int row, int column) {
					// TODO 自动生成的方法存根
					if (!vect.isEmpty())
						return (((Vector<?>) vect.elementAt(row)).elementAt(column));
					else
						return null;
				}
	 
				public String getColumnName(int column) {
					return title[column];// 设置表格列名
				}
	 
				public void setValueAt(Object value, int row, int column) {
				}
	 
				public Class<? extends Object> getColumnClass(int c) {
					return getValueAt(0, c).getClass();
				}// 取得所属对象类
	 
				public boolean isCellEditable(int row, int column) {
	 
					return false;
				}// 设置单元格不可编辑
			};
			table = new JTable(tm);
			table.setToolTipText("显示所有的数据");
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 设置表格调整尺寸模式
			table.setCellSelectionEnabled(false);// 设置单元格选择方式
			table.setShowVerticalLines(true);// 设置是否显示单元格间的分割线
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
	 
			vect.removeAllElements();// 初始化向量对象
			tm.fireTableStructureChanged();// 更新表格内容
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
