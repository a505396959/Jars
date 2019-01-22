package www.bend.socket;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * JDBCʵ�ֲ������ݿ�
 * @author lin
 *
 */
public class JdbcUtil{
    //����ȫ�ֱ���
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String driverClass = null;
    //��ȡ�����ļ����ݣ����ھ�̬������о��У���Ϊֻ��Ҫ����һ�ξͿ�����
    static{
        try{
            Properties props = new Properties();
            //ʹ����·�����صķ�ʽ��ȡ�����ļ�
            //��ȡ���ļ�·��Ҫ�ԡ�/����ͷ����Ϊ���ʹ�á�.���Ļ��������𵽷�������֮����Ҳ����ļ��ˣ�ʹ�á�/����ͷ��ֱ�Ӷ�λ�����̵�src·����
            InputStream in = JdbcUtil.class.getResourceAsStream("jdbc.properties");
            //���������ļ�
            props.load(in);
            //��ȡ�����ļ���Ϣ
            url = props.getProperty("url");
            user = props.getProperty("username");
            password = props.getProperty("password");
            driverClass = props.getProperty("driver");
            //ע����������
            Class.forName(driverClass);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("��������ע��ʧ�ܣ�����");
        }
    }

    //��ȡ���Ӷ���Connection
    public static Connection getConnection(){
        try{
            return (Connection) DriverManager.getConnection(url,user,password);
        }catch(SQLException e){
            e.printStackTrace();
            //�ܳ�����ʱ�쳣
            throw new RuntimeException();
        }
    }

    //�ر����ӵķ�������򿪵��ȹر�
    public static void close(Connection conn,Statement stmt,ResultSet rs){
        //�ر�ResultSet����
        if(rs != null){
            try{
                //�ر�rs������rs=null����Ϊjava�����Ȼ���ֵΪnull�ı���
                rs.close();
                rs = null;
            }catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        //�ر�Statement������ΪPrepareStatement��CallableStatement����Statement���ӽӿڣ���������ֻ��Ҫ�йر�Statement����ķ����Ϳ�����
        if(stmt != null){
            try{
                stmt.close();
                stmt = null;
            }catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        //�ر�Connection����
        if(conn != null){
            try{
                conn.close();
                conn = null;
            }catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }
}
