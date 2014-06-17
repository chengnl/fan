package wy.xjj.c.monitor;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wy.xjj.c.server.ServerBase;
import wy.xjj.c.thrift.BizMethodInfo;
import wy.xjj.c.thrift.FanService;
import wy.xjj.c.thrift.FanServiceBase;

/** 
 * @author chengnl  
 * @E-mail: chengnengliang@gmail.com
 * @date 2014年6月13日 下午3:16:08 
 * @version V1.0   
 * @Description: 服务功能动态代理类
 */
public class Fan {
	   private static final Logger log = LoggerFactory.getLogger(Fan.class.getName());
	   private ServiceMonitorInfo serviceInfo = new ServiceMonitorInfo();
	   private ServerBase  server;
	   public Fan(ServerBase  server){
		   this.server=server;
	   }
	   /**
	    * 生成代理类
	    * @param service
	    * @return
	    */
       public Object wrapper(Object service){
    	   FanServiceBase  fsb =  (FanServiceBase)service;
    	   fsb.setServiceInfo(serviceInfo);
    	   fsb.setServer(server);
    	   registerServiceInfo(service);
    	   return Proxy.newProxyInstance( 
    			   service.getClass().getClassLoader(), 
    			   service.getClass().getInterfaces(), 
                   new FanProxyHandler(service)); 
       }
       /**
        * 注册服务方法信息，主要是是业务方法，便于后面监控
        * @param service
        */
       private void registerServiceInfo(Object service){
    	   Class<?>[] interfaces = service.getClass().getInterfaces();
    	   for(int i=0;i<interfaces.length;i++){
    		   Class<?>  iface = interfaces[i];
			   Method[] methods = iface.getMethods();
		 	   for (Method m:methods) {  
		 		     if(isFanServiceIfaceMethod(m))
		 		    	 continue;
		 			 String methodName = m.getName();
		 			 Class<?>[]  type=m.getParameterTypes();
		 			 BizMethodInfo biz = new BizMethodInfo();
		 			 biz.setName(methodName);
		 			 biz.setArgsNum((byte)type.length);
		 			 biz.setArgsType(getArgsType(type));
		 			 if(log.isDebugEnabled())
		 				log.debug("service registerServiceInfo="+biz.toString());
		 			 serviceInfo.addServiceBizMethod(biz);
		 		}  
    	   }
       }
       private List<String> getArgsType(Class<?>[]  types){
    	   List<String> list = new ArrayList<String>();
    	   for(int i=0;i<types.length;i++){
    		   list.add(types[i].getName());
    	   }
    	   return list;
       }
       
       private  Class<FanService.Iface> iface=FanService.Iface.class;
       private boolean isFanServiceIfaceMethod(Method m){
    	  Method[]  methods= iface.getMethods();
    	  for(int i=0;i<methods.length;i++){
    		  if(methods[i].equals(m))
    			  return true;
    	  }
    	  return false;
       }
}
