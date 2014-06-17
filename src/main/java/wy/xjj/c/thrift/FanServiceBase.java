package wy.xjj.c.thrift;

import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;

import wy.xjj.c.monitor.ServiceMonitorInfo;
import wy.xjj.c.server.ServerBase;

/**
 * @author chengnl
 * @E-mail: chengnengliang@gmail.com
 * @date 2014年6月13日 下午2:02:56
 * @version V1.0
 * @Description: 服务监控基类
 */
public abstract class FanServiceBase implements FanService.Iface {
	private String serviceName;
	private String serviceVersion;
	private long serviceStartTime;
	private ServiceMonitorInfo serviceInfo;
	private ServerBase  server;
	protected FanServiceBase(){
		init("","");
	}
	protected FanServiceBase(String name, String version) {
		init(name,version);
	}

	public String getName() throws TException {
		return this.serviceName;
	}

	public String getVersion() throws TException {
		return this.serviceVersion;
	}

	public List<BizMethodInfo> getServiceBizMethods() throws TException {
		return this.serviceInfo.getBizMethodInfoList();
	}

	public Map<String, BizMethodInvokeInfo> getBizMethodsInvokeInfo()
			throws TException {
		return this.serviceInfo.getBizMethodInvokeInfoMap();
	}

	public BizMethodInvokeInfo getBizMethodInvokeInfo(String methodName)
			throws TException {
		return this.serviceInfo.getBizMethodInvokeInfoMap().get(methodName);
	}

	public long getServiceCount() throws TException {
		return this.serviceInfo.getServiceCount();
	}

	public long aliveSince() throws TException {
		return (System.currentTimeMillis() / 1000) - this.serviceStartTime;
	}
	
	public void reinitialize(){
		this.server.reinitialize();
	}
	
	public void shutdown(){
		this.server.stop();;
	}
	
	public void setOption(String key,String value){
		this.server.setOption(key, value);
	}
	
	public Map<String,String> getOptions(){
		return this.server.getOptions();
	}
	
	
	public ServerBase getServer() {
		return server;
	}
	public void setServer(ServerBase server) {
		this.server = server;
	}
	public ServiceMonitorInfo getServiceInfo() {
		return serviceInfo;
	}
	public void setServiceInfo(ServiceMonitorInfo serviceInfo) {
		this.serviceInfo = serviceInfo;
	}
	private void init(String name, String version){
		this.serviceName = name;
		this.serviceVersion = version;
		this.serviceStartTime = System.currentTimeMillis() / 1000;
	}
	
}
