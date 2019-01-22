package www.bend.socket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


/**
 * SQL ���
 * @author lin
 *
 */
public class Paging {
	static BendEntity bendEntity;
	static Connection conn=null;
	static int lastPage = 0;
//	static int page=0;
	/**
	 * ��ȡ�б�
	 * @return
	 */
	public static List<BendEntity> getPageContentByApi(){
		int count=0;//����Ϣ����
		int number=25;//һҳ���ɴ��25������
		int start =SwingTest.page*number;//�����ҳ��count-start==��ʼλ
		Statement st=null;
		ResultSet stcount=null;
		ResultSet rs=null;
		String sqlPaging =null;
		Connection conn=null;
		String sqlCount=null;
		try {
			conn=JdbcUtil.getConnection();
			st = (Statement) conn.createStatement();
			sqlCount="select * from Bend";
			stcount=st.executeQuery(sqlCount);
			//��ѯ��Ϣ����
			while(stcount.next()){
				count++;
			}
			if((count/number)+1>=lastPage){
				lastPage=(count/number)+1;
			}
			//�жϷ�ҳ��ѯ����ѯλ���Ƿ������Ϣ����
			if(count<start){
				sqlPaging = "select * from Bend  limit "+0+","+count%number;
				System.out.println("������һҳ��sql��䣺"+sqlPaging);
			}else{
				sqlPaging = "select * from Bend  limit "+(count-start)+","+number;
				System.out.println("�����sql��䣺"+sqlPaging);
			}
//			else{
//				if(count>=number){
//					sqlPaging = "select * from Bend  limit "+(count-start)+","+number;
//					System.out.println("�����sql��䣺"+sqlPaging);
//				}else{
//					sqlPaging ="select * from Bend";
//					System.out.println("�����sql��䣺"+sqlPaging);
//				}
//			}
			rs = st.executeQuery(sqlPaging);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        System.out.println("����Statement�ɹ�!");
        System.out.println("�������ݱ�ɹ�!");
        System.out.println("��Ϣ������"+count);
//        System.out.println("----------------!");
        List<BendEntity> list = new ArrayList<BendEntity>();
        try {
        	while(rs.next())
			  {
				BendEntity bendEntity =new BendEntity();
				bendEntity.setRaise(rs.getString("raise"));
				bendEntity.setRaiseTime(rs.getString("raiseTime"));
				bendEntity.setFall(rs.getString("fall"));
				bendEntity.setFallTime(rs.getString("fallTime"));
//			    System.out.print(rs.getString("raise") + "\t");
//			    System.out.print(rs.getString("raiseTime")+"\t");
//			    System.out.print(rs.getString("fall") + "\t");
//			    System.out.println(rs.getString("fallTime"));
			    list.add(bendEntity); 
			  }
//			System.out.println("----------------!");
//			for(BendEntity bendEntity2:list){
//				System.out.print(bendEntity2.getRaise() + "    ");
//				System.out.print(bendEntity2.getFall() + "    ");
//				System.out.println(bendEntity2.getTime());
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return list;
	}
	/**
	 * ��ñ����ݣ���������list����ת��Ϊ������
	 * @param length
	 * @return
	 */
	 public static Object[][] queryData(int length){
		    //�õ�������
	        List<BendEntity> list=getPageContentByApi();
	        Object [][]data=new Object[list.size()][length];
	        //��listת��Ϊ��ά����
	        int j=list.size();//list�������
	        System.out.println(list.size());
	        //����
	        for(int i=0;i<list.size();i++){
                data[j-i-1][0]=list.get(i).getRaise();
                data[j-i-1][1]=list.get(i).getRaiseTime();
                data[j-i-1][2]=list.get(i).getFall();
                data[j-i-1][3]=list.get(i).getFallTime();
	        }
	        //����
//	        for(int i=list.size()-1;0<=i;i--){
//	            data[i][0]=list.get(i).getRaise();
//	            data[i][1]=list.get(i).getRaiseTime();
//	            data[i][2]=list.get(i).getFall();
//	            data[i][3]=list.get(i).getFallTime();
//    }
	        return data;
	    }
	/**
	 * �����ݿ����̧�˺������Ϣ
	 * @param raise
	 * @param fall
	 * @param raiseTime
	 * @param fallTime
	 */
	 public static void sqlInsert(String raise,String fall,String raiseTime,String fallTime){
		 bendEntity=new BendEntity();
         bendEntity.setRaise(raise);
         bendEntity.setFall(fall);
         bendEntity.setRaiseTime(raiseTime);
         bendEntity.setFallTime(fallTime);
//         Connection connection=null;
//         PreparedStatement pst = null;
//         ResultSet rs = null;
//         conn=jdbcUtil.getConnection();
         Connection conn=JdbcUtil.getConnection();
         String sql="insert into Bend(raise,raiseTime,fall,fallTime) values("+'"'+bendEntity.getRaise()+'"'+","+"'"+bendEntity.getRaiseTime()+"'"+","+'"'+bendEntity.getFall()+'"'+","+"'"+bendEntity.getFallTime()+"'"+")";
         System.out.println("SQL���Ϊ��"+sql);
         try {
			boolean num=conn.createStatement().execute(sql);
			if(num){
				System.out.println("�ɹ�����sql");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	 }
//	public static void main(String[] args) {
//		getPageContentByApi();
//	}
}
