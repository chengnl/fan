fan
===
## 简介
   fan是监控thrift服务运行情况组件，监控功能包括：
   
   1、服务对外提供的业务方法，方法参数
   
   2、服务运行过程中调用总次数，每个业务方法调用次数，成功次数，失败次数，以及每个业务方法，执行成功的平均时间，最大、最小时间   
   
   3、提供服务停止，设置参数，初始化接口
   
   4、提供服务的业务方法详细的调用日志，传入参数，耗时，返回结果。
   
## 使用

### thrift文件

   1、进入'src/main/resources'目录找到fan.thrift（里面描述了提供所有的服务监控信息），将thrift文件拷贝到自己的thrift文件放置目录
   2、在自己的thrift文件中引入fan.thrift'include "fan.thrift"'

thrift service monitor
