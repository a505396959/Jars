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
        File file1=new File("D:/workspaces-wangxiaobao/DYDZ-CMMI-CD-KKQD-003用户操作手册.doc");
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

	            in = fi.getChannel();//方法返回与此文件输入流关联的唯一文件通道对象。

	            out = fo.getChannel();//方法返回与此文件输出流关联的唯一文件通道对象。

	            in.transferTo(0, in.size(), out);//通过通道复制文件

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

