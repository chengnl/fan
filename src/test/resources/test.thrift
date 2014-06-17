include "fan.thrift"
namespace java wy.xjj.c

struct LoginBean {
  1: string loginAccount; 
  2: byte deviceType;   
}

service TestService extends fan.FanService{
  void createTicket(1: LoginBean lb, 2: string pwd)
  byte verifyTicket(1: i64 userID, 2: string ticket)
}
