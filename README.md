tomcat 版本：9.0.16
启动方式：
直接运行main方法(使用内部tomcat)，
使用外部tomcat

注意：
如果是外部tomcat启动需要注意：如果没有使用resources下声明的logback-spring.xml,在properties中指定logback位置时，需要将logback-prod.xml
logback-test.xml 放到另外文件夹，不能放到resources根目录，否则启动tomcat一直打印jndi异常信息,正确示例：
logging.config=classpath:logconfig/logback-prod.xml

定时任务cron表达式：
Cron表达式是一个字符串，字符串以5或6个空格隔开，分为6或7个域，每一个域代表一个含义，Cron有如下两种语法格式：
Seconds Minutes Hours DayofMonth Month DayofWeek Year或
Seconds Minutes Hours DayofMonth Month DayofWeek
详解： https://www.cnblogs.com/linjiqin/p/3178452.html

quartz定时器更加强大（定时器启动，停止，状态查询功能，也支持分布式），但也复杂

参考：
日志配置：
https://blog.csdn.net/inke88/article/details/75007649

devtools实现热部署：
https://www.cnblogs.com/liu2-/p/9118393.html

jsp使用：
https://blog.csdn.net/kxj19980524/article/details/85228846

同时使用jsp,freemarker
https://blog.csdn.net/jsplove/article/details/80755247

事务：
https://blog.csdn.net/qq_27384769/article/details/79691218
https://blog.csdn.net/catoop/article/details/50595702（自定义数据源需要声明事务管理器）

tomat 部署到服务器3种启动方式：
https://blog.csdn.net/qq_29992111/article/details/78749469
