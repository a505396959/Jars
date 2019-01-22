package www.bend.test;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import www.bend.socket.JdbcUtil;

import com.mysql.jdbc.Connection;

public class Test1 {

	@Test
	public void test() {
		long t1,t2,t3,t;
		
		Connection conn=JdbcUtil.getConnection();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i=0;i<10;i++){
			t1=System.currentTimeMillis();
			String time1=df.format(new Date());
			while(true){
				t2=System.currentTimeMillis();
				if(t2-t1>3500){
					String time2=df.format(new Date());
					String sql="insert into Bend(raise,raiseTime,fall,fallTime) values("+'"'+"Ì§¸Ë"+'"'+","+"'"+time1+"'"+","+'"'+"Âä¸Ë"+'"'+","+"'"+time2+"'"+")";
					System.out.println("SQLÓï¾ä£º"+sql);
					try {
						boolean num=conn.createStatement().execute(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					while(true){
						t3=System.currentTimeMillis();
						if(t3-t2>3500){
							break;
						}
					}
					break;
				}
			}
			
		}
          
	}

}


