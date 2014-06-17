package wy.xjj.c;

import org.apache.thrift.transport.TTransportException;

/** 
 * @author chengnl  
 * @E-mail: chengnengliang@gmail.com
 * @version V1.0   
 * @Description: TODO 类描述
 */
public class StartServer {
         public static void main(String[] args) throws TTransportException{
        	 TestServer  server  = new TestServer();
        	 server.run();
         }
}
