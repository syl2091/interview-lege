+# Java基础



# JVM
## jvm内存模型
![img.png](img.png)
![img_1.png](img_1.png)
![img_2.png](img_2.png)
## 堆内存基本结构
![img_3.png](img_3.png)
## 内存分配原则
+ 对象优先再eden区分配 eden区没有足够空间会进行一次 minjor gc
+ 大对象直接进入老年代 字符串 数组
+ 长期存货对象直接进入老年代
## 主要进行gc的区域
### 部分收集 Partial GC
+ 新生代收集:minjor gc ：只对新生代进行垃圾收集
+ 老年代收集:Major GC 只对老年代进行收集
+ 混合收集 mixed gc 对整个新生代和部分老年代进行垃圾收集
### 整堆收集
收集整个 Java 堆和方法区
## 死亡对象判断方法
+ 引用计数法
+ 可达性分析算法
### 哪些对象可以作为 GC Roots 呢？
+ 虚拟机栈(栈帧中的本地变量表)中引用的对象
+ 本地方法栈(Native 方法)中引用的对象
+ 方法区中类静态属性引用的对象
+ 方法区中常量引用的对象
+ 所有被同步锁持有的对象
## 引用类型总结
+ 强引用 不会回收 报出异常
+ 软引用 内存不足会回收
+ 弱引用 不管内存足不足都会回收
## 垃圾收集算法
+ 标记清除
+ 复制算法 为了解决标记清楚会产生内存碎片问题
+ 标记整理 标记过程与标记清楚一样 后续步骤不是直接对可回收对象回收，而是让所有存活的对象向一端移动，然后直接清理掉端边界以外的内存。
+ 分代收集算法
## 垃圾收集器
+ Serial 收集器 Serial OLD 老年代版本 单线程 `新生代采用标记-复制算法，老年代采用标记-整理算法`。
+ ParNew  Serial 收集器多线程版本`新生代采用标记-复制算法，老年代采用标记-整理算法`
+ Parallel Scavenge  收集器 Parallel OLD  Parallel Scavenge 收集器关注点是吞吐量（高效率的利用 CPU）。CMS 等垃圾收集器的关注点更多的是用户线程的停顿时间（提高用户体验）`新生代采用标记-复制算法，老年代采用标记-整理算法。` 1.8默认收集器
+ CMS收集器 真正意义上的并发收集器 
  - 初始标记
  - 并发标记
  - 重新标记
  - 并发清除
+ G1收集器
  - 初始标记
  - 并发标记
  - 最终标记
  - 筛选回收
## 类加载过程
![img_4.png](img_4.png)

# Spring & SpringBoot
## 谈谈自己对于 Spring IoC 的了解
IoC（Inversion of Control:控制反转 将创建对象交给容器 ioc容器去管理依赖 
+ 控制：指的是对象创建（实例化、管理）的权力
+ 反转：控制权交给外部环境（Spring 框架、IoC 容器）
## Bean的作用域有哪些
- singleton：单例
- prototype：每次get都会新建
- request ：每一个http请求都会新建
- session ：每一个新的session都会新建
- application/global-session 每个 Web 应用在启动时创建一个 Bean
- websocket 每一个websocket会话新建

## 单例 Bean 的线程安全问题了解吗？
+ 在 Bean 中尽量避免定义可变的成员变量。
+ 在类中定义一个 ThreadLocal 成员变量，将需要的可变成员变量保存在 ThreadLocal 中（推荐的一种方式）。
## Bean 的生命周期了解么?
+ Bean 容器找到配置文件中 Spring Bean 的定义。
+ 利用反射创建一个实例
+ 如果涉及到一些属性值 利用 set()方法设置一些属性值。
+ 如果 Bean 实现了 BeanNameAware 接口，调用 setBeanName()方法，传入 Bean 的名字
+ 如果 Bean 实现了 BeanClassLoaderAware 接口，调用 setBeanClassLoader()方法，传入 ClassLoader对象的实例。
+ 如果 Bean 实现了 BeanFactoryAware 接口，调用 setBeanFactory()方法，传入 BeanFactory对象的实例。
+ 与上面的类似，如果实现了其他 *.Aware接口，就调用相应的方法。
+ 如果有和加载这个 Bean 的 Spring 容器相关的 BeanPostProcessor 对象，执行postProcessBeforeInitialization() 方法
+ 如果 Bean 实现了InitializingBean接口，执行afterPropertiesSet()方法。
+ 如果 Bean 在配置文件中的定义包含 init-method 属性，执行指定的方法。
+ 如果有和加载这个 Bean 的 Spring 容器相关的 BeanPostProcessor 对象，执行postProcessAfterInitialization() 方法
+ 当要销毁 Bean 的时候，如果 Bean 实现了 DisposableBean 接口，执行 destroy() 方法。
+ 要销毁 Bean 的时候，如果 Bean 在配置文件中的定义包含 destroy-method 属性，执行指定的方法。

## Spring AOP 和 AspectJ AOP 有什么区别？
Spring AOP 属于运行时增强，而 AspectJ 是编译时增强。 
## AspectJ 定义的通知类型有哪些？
+ Before 前置通知
+ After  后置通知
+ AfterReturning 返回通知 
+ AfterThrowing 异常通知
+ Around 环绕通知
## 多个切面的执行顺序如何控制？
@Order注解 或者实现 Order接口重写getOrder方法

## Spring MVC 的核心组件有哪些？
+ DispatcherServlet：核心的中央处理器，负责接收请求、分发，并给予客户端响应。
+ HandlerMapping：处理器映射器，根据 uri 去匹配查找能处理的 Handler ，并会将请求涉及到的拦截器
+ HandlerAdapter：处理器适配器，根据 HandlerMapping 找到的 Handler ，适配执行对应的 Handler；
+ Handler：请求处理器，处理实际请求的处理器
+ ViewResolver：视图解析器，根据 Handler 返回的逻辑视图 / 视图，解析并渲染真正的视图，并传递给 DispatcherServlet 响应客户端

## SpringMVC 工作原理了解吗?
1. 客户端（浏览器）发送请求， DispatcherServlet拦截请求。
2. DispatcherServlet 根据请求信息调用 HandlerMapping 。HandlerMapping 根据 uri 去匹配查找能处理的 Handler
3. DispatcherServlet 调用 HandlerAdapter适配执行 Handler 。
4. Handler 完成对用户请求的处理后，会返回一个 ModelAndView 对象给DispatcherServlet，ModelAndView
5. ViewResolver 会根据逻辑 View 查找实际的 View。
6. DispaterServlet 把返回的 Model 传给 View

## 统一异常怎么做？
+ @ControllerAdvice + @ExceptionHandler

## Spring 框架中用到了哪些设计模式？
+ 工厂设计模式
+ 代理设计模式
+ 单例设计模式
+ 模板方法模式
+ 包装器设计模式 多个数据源
+ 观察者模式: Spring 事件驱动模型就是观察者模式很经典的一个应用。
+ 适配器模式 : Spring AOP 的增强或通知(Advice)使用到了适配器模式、spring MVC 中也是用到了适配器模式适配Controller。
## Spring 事务中哪几种事务传播行为?
+ REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
+ EQUIRES_NEW 创建一个新的事务，如果当前存在事务，则把当前事务挂起。 开启的事务相互独立，互不干扰
+ NESTED ： 如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，创建事务
+ MANDATORY 如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常
+ SUPPORTS 如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
+ NOT_SUPPORTED: 以非事务方式运行，如果当前存在事务，则把当前事务挂起。
+ NEVER: 以非事务方式运行，如果当前存在事务，则抛出异常

## Spring 事务中的隔离级别有哪几种?
+ ISOLATION_DEFAULT 用后端数据库默认的隔离级别，MySQL 默认采用的 REPEATABLE_READ 隔离级别 Oracle 默认采用的 READ_COMMITTED 隔离级别.
+ READ_UNCOMMITTED :最低的隔离级别，使用这个隔离级别很少，因为它允许读取尚未提交的数据变更，
+ ISOLATION_READ_COMMITTED : 允许读取并发事务已经提交的数据
+ n.ISOLATION_SERIALIZABLE : 最高的隔离级别，完全服从 ACID 的隔离级别

## @SpringBootApplication注解
+ @EnableAutoConfiguration 启动自动配置  AutoConfigurationImportSelector类 加载对象到容器中
+ @ComponentScan 扫描类装配对象
+ @Configuration 允许上下文注册额外的bean 和配置文件

# Dubbo
## Dubbo核心角色有哪些
+ Container： 服务运行容器，负责加载、运行服务提供者。必须。
+ Provider： 暴露服务的服务提供方，会向注册中心注册自己提供的服务。必须。
+ Consumer： 调用远程服务的服务消费方，会向注册中心订阅自己所需的服务。必须。
+ Registry： 服务注册与发现的注册中心。注册中心会返回服务提供者地址列表给消费者。非必须。
+ Monitor： 统计服务的调用次数和调用时间的监控中心。服务消费者和提供者会定时发送统计数据到监控中心。 非必须
## Dubbo 中的 Invoker 概念了解么？
Invoker 就是 Dubbo 对远程调用的抽象。
## Dubbo 的工作原理了解么？
+ config 配置层：Dubbo 相关的配置。
+ proxy 服务代理层：调用远程方法像调用本地的方法一样简单的一个关键
+ registry 注册中心层：封装服务地址的注册与发现。
+ cluster 路由层：封装多个提供者的路由及负载均衡
+ monitor 监控层：RPC 调用次数和调用时间监控
+ protocol 远程调用层：封装 RPC 调用，
+ exchange 信息交换层：封装请求响应模式，同步转异步
+ transport 网络传输层：抽象 mina 和 netty 为统一接口
+ serialize 数据序列化层：对需要在网络传输的数据进行序列化。

## Dubbo 提供的负载均衡策略有哪些？
+ RandomLoadBalance 根据权重随机选择（对加权随机算法的实现）。这是 Dubbo 默认采用的一种负载均衡策略。
+ LeastActiveLoadBalance 最小活跃数负载均衡
+ ConsistentHashLoadBalance 一致性 Hash 负载均衡策略
+ RoundRobinLoadBalance 加权轮询负载均衡

## Dubbo 支持哪些序列化方式呢？
JDK 自带的序列化、hessian2、JSON、Kryo、FST、Protostuff，ProtoBu

# Rabbitmq
## 交换机类型
+ fanout
+ direct
+ topic
+ headers
## 什么是死信队列？如何导致的？
+ 消息被拒
+ 消息 TTL 过期
+ 队列满了，无法再添加。
## 什么是延迟队列？RabbitMQ 怎么实现延迟队列？
+ rabbitmq-delayed-message-exchange
## 优先级队列
通过x-max-priority
## 如何保证消息的可靠性？
+ 生产者到 RabbitMQ：事务机制和 Confirm 机制，注意：事务机制和 Confirm 机制是互斥的，两者不能共存，会导致 RabbitMQ 报错。
+ RabbitMQ 自身：持久化
+ RabbitMQ 到消费者：basicAck 机制
## 如何保证 RabbitMQ 消息的顺序性？
+ 拆分多个 queue(消息队列)，每个 queue(消息队列) 一个 consumer(消费者)，
+ 
# SpringCloud 
## Nacos注册中心原理
+ 注册前
  -  客户端，即我们的微服务启动时，会将服务的ip，端口等信息封装成一个instance实例对象，准备向Nacos服务端注册
  - ，并创建一个定时的心跳连接机制
  - 隔一段时间都会向Nacos服务端发送PUT请求，Nacos服务端在接收到心跳请求后，会检查当前服务列表是否有这个服务实例，如果有，则刷新该实例的心跳时间，如果没有的话，Nacos服务端开始创建实例。
+ 注册中
  -  在Nacos服务端接收到心跳请求后，会使用Post请求将客户端相关的实例对象信息注册进Nacos服务端
  - 然后服务端将这个服务信息存储进服务端中一个叫ConcurrentHashMap的数组
  - 如果心跳机制超过15秒，服务状态为不健康，超过30秒，这个实例直接被删除。
+ 注册后
  - 在服务注册成功后，客户端会通过定时心跳任务来从服务端获取信息并存储进本地缓存，然后刷新该服务实例的心跳时间。服务端在接收到心跳后，会定时向客户端推送相关的数据给客户端，一般来说是10秒推送一次。
  
## Nacos配置中心原理

1. 当配置人员在Nacos里修改配置时，Nacos会将配置信息更新并存储到相应的数据库里
2. 我们的服务注册到Nacos时，其实是有一个定时心跳任务的，它每10秒会向Nacos发送心跳信息并获取相对应的配置数据。
3. 客户端获取到服务端的配置信息时，会比较配置信息的MD5数据，当获取到的MD5数据与客户端本地缓存的数据不匹配时，客户端则会重新从服务端来获取新的配置信息并将它缓存在客户端的本地缓存中。

## Ribbon负载均衡策略
+ 轮询策略
+ 权重策略
+ 随机策略
+ 最小连接数策略
+ 重试策略
+ 可用性敏感策略
+ 区域敏感策略