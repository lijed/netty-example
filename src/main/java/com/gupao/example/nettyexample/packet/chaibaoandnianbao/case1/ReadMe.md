#实现场景：

## server
 监听客户端的连接，每收到client的消息，就返回一个uuid
 
## client
  1. 一旦connection建立，就发送消息给server端。
  2. 解析server端的response
  
  
## 问题
   server端把几次client的消息的处理结果一次返回。
  