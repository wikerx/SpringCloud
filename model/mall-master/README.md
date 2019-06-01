# mall-master

>自娱自乐闲时项目

#### **介绍**
分布式电商系统，采用SpringCloud分布式框架.
* 利用consul完成服务注册与发现
* 利用Rocket实现消息队列
* 采用solr搜索系统
* 利用Redis做缓存
* 基于Mybaits-plus,完成代码自动生成及开发。

#### **软件架构**
SpringCloud基础框架。
jar包库利用gradle管理。
jdk需要1.8，需引入scala-sdk-2.11.0。

#### **安装所需服务**

1. consul
2. solr
3. redis

#### 主要模板介绍
* 公共API   **common-api**
* cloud api网关 **cloud-zuul-server**
* 配置中心 **cloud-config-server**
* 公共Mybatis-plus代码处理器 **common-generators**
* 链路追踪服务端 **cloud-zipkin-server**
* RocketMQ **common-mq-api**
* Feign **common-feign-api**
* 商品子系统 **property-client-service**
* 搜索子系统 **property-solr-service**

#### 总体逻辑图
![Image text](http://118.24.112.225:8000/img/mall.jpg)

#### 不断完善
* 个人博客：https://www.updatecg.xin
* 简书：https://www.jianshu.com/u/1da14750b0ba

#### 参考文献
common-mq-api 参考了https://gitee.com/jollyfly/rocketmq-spring-boot-starter ，剖析了源码，简化成注解形式，很不错，测试过。