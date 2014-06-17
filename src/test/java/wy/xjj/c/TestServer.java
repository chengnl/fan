package wy.xjj.c;

import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TNonblockingServer.Args;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wy.xjj.c.monitor.Fan;
import wy.xjj.c.server.ServerBase;

/** 
 * @author chengnl  
 * @E-mail: chengnengliang@gmail.com
 * @date 2014年6月16日 上午11:19:40 
 * @version V1.0   
 * @Description: TODO 类描述
 */
public class TestServer extends ServerBase{
	  private static final Logger log = LoggerFactory.getLogger(TestServer.class.getName());
    private  TNonblockingServerTransport serverTransport;
    private  TServer server;
    public TestServer(){
    	this.setOption("MINWORKERTHREADS", "5");
    	this.setOption("MAXWORKERTHREADS", "5000");
    }
	@Override
	public void reinitialize() {
		
	}
	public void run() {
		try {
			serverTransport = new TNonblockingServerSocket(10005);
	        TestService.Iface  iface = (TestService.Iface)(new Fan(this).wrapper(new TestServiceImpl("testService", "1.0")));
	        TestService.Processor processor = new TestService.Processor(iface);
	        Factory proFactory = new TBinaryProtocol.Factory();
	        Args params = new Args(serverTransport);
	        params.processor(processor);
	        params.protocolFactory(proFactory);
	        //this.getOptions();  加载参数 如果有的话，比如TThreadPoolServer
		    server = new TNonblockingServer(params); 
		    System.out.println("Start server on port 10005 ..."); 
		    server.serve(); 
		} catch (TTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void stop() {
		if(server.isServing())
			server.stop();		
		System.out.println(222);
	}

}
