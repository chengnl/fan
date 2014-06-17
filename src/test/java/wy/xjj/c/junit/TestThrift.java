package wy.xjj.c.junit;


import junit.framework.TestCase;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wy.xjj.c.TestService;
import wy.xjj.c.TestService.Client;
public class TestThrift  extends TestCase{
	private TestService.Client  testClient;
	private TTransport transport;
	@Before
	public void setUp() throws Exception {
        transport = new TFramedTransport(new TSocket("localhost", 10005)); 
        transport.open(); 
        // 设置传输协议为 TBinaryProtocol 
        TProtocol protocol = new TBinaryProtocol(transport); 
         testClient=new TestService.Client(protocol);
	}
	@Test
    public void testCallMethod() throws TException{
		testClient.verifyTicket(1, "1");
		for(int i=10;i>=0;i--){
			testClient.createTicket(null,""+i+"");	
		}
    }
	@Test
    public void testGetMonitor() throws TException{
		System.out.println(testClient.getName());
		System.out.println(testClient.getVersion());
		System.out.println(testClient.aliveSince());
		System.out.println(testClient.getServiceCount());
		System.out.println(testClient.getServiceBizMethods());
		System.out.println(testClient.getBizMethodInvokeInfo("createTicket"));
		System.out.println(testClient.getBizMethodsInvokeInfo());
    }	
	
	@Test
    public void testOptions() throws TException{
		System.out.println(testClient.getOptions());
		testClient.setOption("ddd", "rrrr");
		System.out.println(testClient.getOptions());
    }	
	@Test
    public void testReinitialize() throws TException{
		testClient.reinitialize();
    }	
	@Test
    public void testStop() throws TException{
		testClient.shutdown();
    }	
	
	@After
	public void tearDown() throws Exception {
        transport.close();
	}
}
