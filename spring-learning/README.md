# spring boot 
### webjars
https://www.webjars.org/

### 静态资源
只要静态资源放在类路径下： called /static (or /public or /resources or /META-INF/resources
访问 ： 当前项目根路径/ + 静态资源名


### @Configuration属性proxyBeanMethods详解
https://www.cnblogs.com/dreamroute/p/15944957.html



Outline
1、SpringBoot特点
1.1、依赖管理
1.2、自动配置
2、容器功能
2.1、组件添加
1、@Configuration
2、@Bean、@Component、@Controller、@Service、@Repository
3、@ComponentScan、@Import
4、@Conditional
2.2、原生配置文件引入
1、@ImportResource
2.3、配置绑定
1、@ConfigurationProperties
2、@EnableConfigurationProperties + @ConfigurationProperties
3、@Component + @ConfigurationProperties
3、自动配置原理入门
3.1、引导加载自动配置类
1、@SpringBootConfiguration
2、@ComponentScan
3、@EnableAutoConfiguration
1、@AutoConfigurationPackage
2、@Import(AutoConfigurationImportSelector.class)
3.2、按需开启自动配置项
3.3、修改默认配置
3.4、最佳实践
4、开发小技巧
4.1、Lombok
4.2、dev-tools
4.3、Spring Initailizr（项目初始化向导）
0、选择我们需要的开发场景
1、自动依赖引入
2、自动创建项目结构
3、自动编写好主配置类

### 线程 jstack pid 
```shell
jstack -l 22284
2023-07-13 18:18:06
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.351-b10 mixed mode):

"DestroyJavaVM" #29 prio=5 os_prio=0 tid=0x00000231512c5000 nid=0x5750 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
        - None

"Blocked-2" #28 prio=5 os_prio=0 tid=0x000002316f720800 nid=0x2c10 waiting for monitor entry [0x000000510beff000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.my.concurrent.MultiThread$Blocked.run(MultiThread.java:25)
        - waiting to lock <0x000000066c065c60> (a java.lang.Class for com.my.concurrent.MultiThread$Blocked)
        at java.lang.Thread.run(Thread.java:750)

   Locked ownable synchronizers:
        - None

"Blocked-1" #27 prio=5 os_prio=0 tid=0x000002316f720000 nid=0x3bc waiting on condition [0x000000510bcff000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(Native Method)
        at java.lang.Thread.sleep(Thread.java:342)
        at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
        at com.my.concurrent.MultiThread$Blocked.run(MultiThread.java:25)
        - locked <0x000000066c065c60> (a java.lang.Class for com.my.concurrent.MultiThread$Blocked)
        at java.lang.Thread.run(Thread.java:750)

   Locked ownable synchronizers:
        - None

"Waiting" #26 prio=5 os_prio=0 tid=0x000002316f7d7000 nid=0x4e84 in Object.wait() [0x000000510fbff000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x000000066c062b20> (a java.lang.Class for com.my.concurrent.MultiThread$Waiting)
        at java.lang.Object.wait(Object.java:502)
        at com.my.concurrent.MultiThread$Waiting.run(MultiThread.java:40)
        - locked <0x000000066c062b20> (a java.lang.Class for com.my.concurrent.MultiThread$Waiting)
        at java.lang.Thread.run(Thread.java:750)

   Locked ownable synchronizers:
        - None

"TimeWaiting" #25 prio=5 os_prio=0 tid=0x000002316f7d6000 nid=0x4e80 waiting on condition [0x000000510faff000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(Native Method)
        at java.lang.Thread.sleep(Thread.java:342)
        at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
        at com.my.concurrent.MultiThread$TimeWaiting.run(MultiThread.java:54)
        at java.lang.Thread.run(Thread.java:750)

   Locked ownable synchronizers:
        - None
```