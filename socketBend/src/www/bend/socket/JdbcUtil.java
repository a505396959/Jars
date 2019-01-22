package www.bend.socket;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * JDBC实现操作数据库
 * @author lin
 *
 */
public class JdbcUtil{
    //定义全局变量
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String driverClass = null;
    //读取配置文件内容，放在静态代码块中就行，因为只需要加载一次就可以了
    static{
        try{
            Properties props = new Properties();
            //使用类路径加载的方式读取配置文件
            //读取的文件路径要以“/”开头，因为如果使用“.”的话，当部署到服务器上之后就找不到文件了，使用“/”开头会直接定位到工程的src路径下
            InputStream in = JdbcUtil.class.getResourceAsStream("jdbc.properties");
            //加载配置文件
            props.load(in);
            //读取配置文件信息
            url = props.getProperty("url");
            user = props.getProperty("username");
            password = props.getProperty("password");
            driverClass = props.getProperty("driver");
            //注册驱动程序
            Class.forName(driverClass);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("驱动程序注册失败！！！");
        }
    }

    //获取连接对象Connection
    public static Connection getConnection(){
        try{
            return (Connection) DriverManager.getConnection(url,user,password);
        }catch(SQLException e){
            e.printStackTrace();
            //跑出运行时异常
            throw new RuntimeException();
        }
    }

    //关闭连接的方法，后打开的先关闭
    public static void close(Connection conn,Statement stmt,ResultSet rs){
        //关闭ResultSet对象
        if(rs != null){
            try{
                //关闭rs，设置rs=null，因为java会优先回收值为null的变量
                rs.close();
                rs = null;
            }catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        //关闭Statement对象，因为PrepareStatement和CallableStatement都是Statement的子接口，所以这里只需要有关闭Statement对象的方法就可以了
        if(stmt != null){
            try{
                stmt.close();
                stmt = null;
            }catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        //关闭Connection对象
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
