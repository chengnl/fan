package wy.xjj.c.server;

import java.util.HashMap;
import java.util.Map;
/** 
 * @author chengnl  
 * @E-mail: chengnengliang@gmail.com
 * @date 2014年6月13日 下午7:18:50 
 * @version V1.0   
 * @Description: 服务器启动基类，用于服务参数控制
 */
public abstract class ServerBase {
	private final Map<String, String> options = new HashMap<String, String>();
	public  void setOption(String key, String value){
		this.options.put(key, value);
	}
	public Map<String, String> getOptions(){
		return this.options;
	}
	/**
	 * reload params
	 * @param options  服务参数
	 */
	public abstract void reinitialize();
	/**
	 * 停止
	 */
	public abstract void stop();
}
