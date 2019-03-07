###SpringCloud基础理解 By - Mr.薛

###一、概念定义
	Spring Cloud是一个微服务框架，相比Doubbo等RPC框架，Spring Cloud提供的全套的分布式系统解决方案。
	Spring Cloud对微服务基础框架Netflix的多个开源组件进行了封装，同时又实现了和云端平台以及和Spring Cloud开发框架的集成。
	Spring Cloud为微服务架构开发涉及的配置管理，服务治理，熔断机制，智能路由，微代理，控制总线，一次性Token，全局一致性锁，leader选举，分布式session，集群状态管理等操作提供了一种简单的开发方式。
	Spring Cloud为开发者提供了快速构建分布式系统的工具，开发者可以快速的启动服务或构建应用，同时能够快速和云平台资源进行对接。

###二、Spring Cloud的项目的位置
	·Spring Cloud是Sping的一个顶级项目，Spring的顶级项目列表如下：
	·Spring IO platform：用于系统部署，是可集群的，构建现代化应用的版本平台，具体来说当你使用maven dependency引入spring jar包时他就在工作了。
	·Spring Boot旨在简化创建产品级的Spring应用和服务，简化了配置文件，使用嵌入式web服务器，含有诸多开箱即用的微服务功能，可以和Spring Cloud联合部署。
	·Spring Framework：即通常所说的Spring框架，是一个开源的Java/Java EE全功能栈应用程序框架，其他Spring项目如Spring Boot也是依赖此框架。
	·Spring Cloud：微服务工具包，为开发者提供了在分布式系统的配置管理、服务发现、断路器、智能路由、微代理
	控制总线等开发工具包。
	·Spring XD：是一种运行时环境（服务器软件，非开发框架），组合spring技术，如spring batch、spring boot、spring data，采集大数据并处理。
	·Spring Data：是一个数据访问及操作的工具包，封装了很多种数据及数据库的访问相关技术，包括：jdbc、Redis、MongoDB、Neo4j等。
	·Spring Batch：批处理框架，或说是批量任务执行管理器，功能包括任务调度、日志记录/跟踪等。
	·Spring Security：是一个能够为基于Spring的企业应用系统提供声明式的安全访问控制解决方案的安全框架。
	·Spring Integration：面向企业应用集成（EAI/ESB）的编程框架，支持的通信方式包括HTTP、FTP、TCP/UDP、JMS、RabbitMQ、Email等。
	·Spring Social：一组工具包，一组连接社交服务API，如Twitter、Facebook、LinkedIn、GitHub等，有几十个。
	·Spring AMQP：消息队列操作的工具包，主要是封装了RabbitMQ的操作。
	·Spring HATEOAS：是一个用于支持实现超文本驱动的 REST Web 服务的开发库。
	·Spring Mobile：是Spring MVC的扩展，用来简化手机上的Web应用开发。
	·Spring for Android：是Spring框架的一个扩展，其主要目的在乎简化Android本地应用的开发，提供RestTemplate来访问Rest服务。
	·Spring Web Flow：目标是成为管理Web应用页面流程的最佳方案，将页面跳转流程单独管理，并可配置。
	·Spring LDAP：是一个用于操作LDAP的Java工具包，基于Spring的JdbcTemplate模式，简化LDAP访问。
	·Spring Session：session管理的开发工具包，让你可以把session保存到redis等，进行集群化session管理。
	·Spring Web Services：是基于Spring的Web服务框架，提供SOAP服务开发，允许通过多种方式创建Web服务。
	·Spring Shell：提供交互式的Shell可让你使用简单的基于Spring的编程模型来开发命令，比如Spring Roo命令。
	·Spring Roo：是一种Spring开发的辅助工具，使用命令行操作来生成自动化项目，操作非常类似于Rails。
	·Spring Scala：为Scala语言编程提供的spring框架的封装（新的编程语言，Java平台的Scala于2003年底/2004年初发布）。
	·Spring BlazeDS Integration：一个开发RIA工具包，可以集成Adobe Flex、BlazeDS、Spring以及Java技术创建RIA。
	·Spring Loaded：用于实现java程序和web应用的热部署的开源工具。
	·Spring REST Shell：可以调用Rest服务的命令行工具，敲命令行操作Rest服务。

###三、Spring Cloud的子项目
	·Spring Cloud包含了汗多子项目，如：
	·Spring Cloud Config：配置管理工具，支持使用Git存储配置内容，支持应用配置的外部存储，支持客户端配置信息刷新、加密配置内容等。
	·Spring Cloud Bus：事件、消息总线，用于在集群（例如：配置变化事件）中传播状态变化，可与Spring Cloud Config连个实现热部署。
	·Spring Cloud Netflix：针对多种Netflix组件提供的开发工具包，其中包括Eureka、Hystrix、Zuul、Archaius等。
	                      Netflix Eureka：一个基于rest服务的服务治理组件，包括服务注册中心、服务注册与服务发现机制的实现，实现了云端负载均衡和中间层服务器的故障转移。
	                      Netflix Hystrix：容错管理工具，实现断路器模式，通过控制服务的节点,从而对延迟和故障提供更强大的容错能力。
	                      Netflix Ribbon：客户端负载均衡的服务调用组件。
	                      Netflix Feign：基于Ribbon和Hystrix的声明式服务调用组件。
	                      Netflix Zuul：微服务网关，提供动态路由，访问过滤等服务。
	                      Netflix Archaius：配置管理API，包含一系列配置管理API，提供动态类型化属性、线程安全配置操作、轮询框架、回调机制等功能。
	·Spring Cloud for Cloud ·Foundry：通过Oauth2协议绑定服务到CloudFoundry，CloudFoundry是VMware推出的开源PaaS云平台。
	·Spring Cloud Sleuth：日志收集工具包，封装了Dapper,Zipkin和HTrace操作。
	·Spring Cloud Data Flow：大数据操作工具，通过命令行方式操作数据流。
	·Spring Cloud Security：安全工具包，为你的应用程序添加安全控制，主要是指OAuth2。
	·Spring Cloud Consul：封装了Consul操作，consul是一个服务发现与配置工具，与Docker容器可以无缝集成。
	·Spring Cloud Zookeeper：操作Zookeeper的工具包，用于使用zookeeper方式的服务注册和发现。
	·Spring Cloud Stream：数据流操作开发包，封装了与Redis,Rabbit、Kafka等发送接收消息。
	·Spring Cloud CLI：基于 Spring Boot CLI，可以让你以命令行方式快速建立云组件。

###四、Spring Cloud的版本
	Spring Cloud不像其他Spring子项目那样相对独立，他是一个用友诸多子项目的大型综合项目。
	Spring Cloud可以说是微服务架构解决方案套件组合，其包含的子项目也都独立进行着内容更新与迭代，各自都维护着自己的发布版本号。因此每个Spring Cloud版本，包含着不同版本的子项目，为了管理每个版本的子项目清单，避免Spring Cloud版本号与其子项目版本号混淆，没有采用版本号方式，而是采用命名方式。
	这些版本的名字采用了伦敦地铁站的名字，根据字母表顺序来对应版本时间顺序,如：Angel.SR6,Brixton.SR5,Brixton.SR7,Camden.M1.

####注意：使用Brixton版本要注意SpringBoot的对应版本，必须要使用1.3.x，而不能使用1.4.x


###Spring Cloud组成
	·Spring Cloud的子项目，大致可分成两类，一类是对现有成熟框架”Spring Boot化”的封装和抽象，也是数量最多的项目；第二类是开发了一部分分布式系统的基础设施的实现，如Spring Cloud Stream扮演的就是kafka, ActiveMQ这样的角色。对于我们想快速实践微服务的开发者来说，第一类子项目就已经足够使用，如：
	·Spring Cloud Netflix
	　　是对Netflix开发的一套分布式服务框架的封装，包括服务的发现和注册，负载均衡、断路器、REST客户端、请求路由等。
	·Spring Cloud Config
	　　将配置信息中央化保存, 配置Spring Cloud Bus可以实现动态修改配置文件
	·Spring Cloud Bus
	　　分布式消息队列，是对Kafka, MQ的封装
	·Spring Cloud Security
	　　对Spring Security的封装，并能配合Netflix使用
	·Spring Cloud Zookeeper
	　　对Zookeeper的封装，使之能配置其它Spring Cloud的子项目使用
	·Spring Cloud Eureka
	·Spring Cloud Eureka 是 Spring Cloud Netflix 微服务套件中的一部分，它基于Netflix Eureka 做了二次封装，主要负责完成微服务架构中的服务治理功能。

###Spring Cloud前景
	·Spring Cloud对于中小型互联网公司来说是一种福音，因为这类公司往往没有实力或者没有足够的资金投入去开发自己的分布式系统基础设施，
	使用Spring Cloud一站式解决方案能在从容应对业务发展的同时大大减少开发成本。
	同时，随着近几年微服务架构和Docker容器概念的火爆，也会让Spring Cloud在未来越来越“云”化的软件开发风格中立有一席之地，
	尤其是在目前五花八门的分布式解决方案中提供了标准化的、全站式的技术方案，意义可能会堪比当年Servlet规范的诞生，有效推进服务端软件系统技术水平的进步。