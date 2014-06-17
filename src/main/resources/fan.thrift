namespace java wy.xjj.c.thrift

struct BizMethodInfo{
   /* method name*/
   1: string name;
   /* args num*/
   2: byte argsNum;
   /* args type*/
   3: list<string> argsType;
}

struct BizMethodInvokeInfo{
   /*Call the total number*/
   1: i64 totalCount;
   /*Call the success number*/
   2: i64 successCount;
   /*Call abnormal failure number*/
   3: i64 failureCount;
   /*The call is successful, on average, millisecond time units*/
   4: i64 successAverageTime;
   /*The call is successful, on minimum, millisecond time units*/
   5: i64 successMinTime;
   /*The call is successful, on maximum , millisecond time units*/
   6: i64 successMaxTime;
}


service FanService {
  /**
   * return service name
   */
  string getName()
  /**
   * return service version
   */
  string getVersion()
  /**
   *Get all the business  method of the service
   */
  list<BizMethodInfo> getServiceBizMethods()
  /**
   *Get all the business  method invocation of services provide information
   */
  map<string,BizMethodInvokeInfo> getBizMethodsInvokeInfo()
  /**
   *Get the business  method invocation of services provide information
   */
  BizMethodInvokeInfo getBizMethodInvokeInfo(1:string methodName)
  /**
   *Access to the service call total number
   */
  i64 getServiceCount()
  /**
   *Access to services run duration unit of seconds
   */
  i64 aliveSince()
  /**
   * Tell the server to reload its configuration, reopen log files, etc
   */
  oneway void reinitialize()
  /**
   * Suggest a shutdown to the server
   */
  oneway void shutdown()
  /**
   * Sets an option
   */
  void setOption(1: string key, 2: string value)
  /**
   * Gets all options
   */
  map<string, string> getOptions()
}
