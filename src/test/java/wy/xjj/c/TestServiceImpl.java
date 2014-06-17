package wy.xjj.c;

import org.apache.thrift.TException;

import wy.xjj.c.thrift.FanServiceBase;

/** 
 * @author chengnl  
 * @E-mail: chengnengliang@gmail.com
 * @date 2014年6月13日 下午5:37:21 
 * @version V1.0   
 * @Description: TODO 类描述
 */
public class TestServiceImpl extends FanServiceBase implements TestService.Iface{
	protected TestServiceImpl(String name, String version) {
		super(name, version);
	}

	@Override
	public void createTicket(LoginBean lb, String pwd) throws TException {
		 if(pwd.equals("0")){
			 throw new TException("dddd");
		 }
	     for(int i=0;i<100000;i++){
	    	 System.out.println(i);
	     }
	}

	@Override
	public byte verifyTicket(long userID, String ticket) throws TException {
		return 0;
	}

}
