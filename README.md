fan 

thrift service monitor

===
## 简介
   fan是监控thrift服务运行情况组件，监控功能包括：
   
   1、服务对外提供的业务方法，方法参数
   
   2、服务运行过程中调用总次数，每个业务方法调用次数，成功次数，失败次数，以及每个业务方法，执行成功的平均时间，最大、最小时间   
   
   3、提供服务停止，设置参数，初始化接口
   
   4、提供服务的业务方法详细的调用日志，传入参数，耗时，返回结果。
   
## 使用

### thrift文件

   1、进入`src/main/resources`目录找到fan.thrift（里面描述了提供所有的服务监控信息），将thrift文件拷贝到自己的thrift文件放置目录
   
   2、在自己的thrift文件中引入fan.thrift`include "fan.thrift"`,然后自己的业务服务`extends fan.FanService`。
   
### 搭建服务

   1、服务扩展`extends ServerBase`,实现服务基本方法，主要是停止，初始化功能。
   
   ```
      public class TestServer extends ServerBase
   
   ```
   
   2、在建立业务服务时，使用fan包装一下业务服务实现类`new Fan(this).wrapper(业务服务实现类)`，this表示步骤1里面服务类
   
   ```
     TestService.Iface  iface = (TestService.Iface)(new Fan(this).wrapper(new TestServiceImpl("testService", "1.0")));
     TestService.Processor processor = new TestService.Processor(iface);
	     
   ```
   3、将包装的业务服务实现类，放入业务服务的Processor处理中即可
   
### 服务监控信息获取

   1、日志，可以看后台输出业务方法调用详细日志
   
   2、监控信息，通过业务服务，调用监控提供的方法获取。
   
### 测试用例
   
   具体操作见`src/test/java`下的测试，可以直接运行使用。
   
