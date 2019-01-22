package www.bend.test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TimeX {
	 private static long _TEN_THOUSAND=10000;
	public static void main(String[] args) {
        long times=1000*_TEN_THOUSAND;
        long t1=System.currentTimeMillis();
        File file1=new File("D:/workspaces-wangxiaobao/DYDZ-CMMI-CD-KKQD-003�û������ֲ�.doc");
        File file2=new File("D:/test.doc");
        fileChannelCopy(file1,file2);
        long t2=System.currentTimeMillis();
        System.out.println(t2-t1);
        
    }
	
	  public static void fileChannelCopy(File s, File t) {

	        FileInputStream fi = null;

	        FileOutputStream fo = null;

	        FileChannel in = null;

	        FileChannel out = null;

	        try {

	            fi = new FileInputStream(s);

	            fo = new FileOutputStream(t);

	            in = fi.getChannel();//������������ļ�������������Ψһ�ļ�ͨ������

	            out = fo.getChannel();//������������ļ������������Ψһ�ļ�ͨ������

	            in.transferTo(0, in.size(), out);//ͨ��ͨ�������ļ�

	        } catch (IOException e) {

	            e.printStackTrace();

	        } finally {

	            try {

	                fi.close();

	                in.close();

	                fo.close();

	                out.close();

	            } catch (IOException e) {

	                e.printStackTrace();

	            }

	        }

	    }
}

