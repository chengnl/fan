package wy.xjj.c.monitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wy.xjj.c.thrift.FanServiceBase;

/** 
 * @author chengnl  
 * @E-mail: chengnengliang@gmail.com
 * @date 2014年6月13日 下午3:22:30 
 * @version V1.0   
 * @Description: 服务功能动态代理处理类
 */
public class FanProxyHandler implements InvocationHandler{
	private static final Logger log = LoggerFactory.getLogger(FanProxyHandler.class.getName());
	private  Object  service;
    protected FanProxyHandler(Object  service){
    	this.service=service;
    }
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		FanServiceBase  fsb =  (FanServiceBase)service;
		ServiceMonitorInfo serviceInfo = fsb.getServiceInfo();
		if(log.isDebugEnabled()){
			if(args!=null)
				for(int i=0;i<args.length;i++){
				log.debug("service arg."+i+"="+(args[i]==null?"null":args[i].toString()));
				}
		}
		long startTime=System.currentTimeMillis();
		Object result=null;  
		try{
	        result=method.invoke(this.service, args);  
		}catch(Exception e){
			if(serviceInfo.isBizMethod(method.getName()))
				serviceInfo.updateBizMethodInvokeInfo(method.getName(),false, 0);
			throw e;
		}
		long endTime=System.currentTimeMillis();
		if(serviceInfo.isBizMethod(method.getName())){
			serviceInfo.updateBizMethodInvokeInfo(method.getName(),true, (endTime-startTime));
			if(log.isDebugEnabled())
				log.debug("call "+method.getName()+" cost time="+(endTime-startTime));
		}
		if(log.isDebugEnabled())
			log.debug("call "+method.getName()+"  result="+(result==null?"null":result.toString()));
		return result;
	}
    
}
