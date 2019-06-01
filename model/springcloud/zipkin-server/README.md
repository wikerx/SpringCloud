## zipkinServer

服务链路追踪
````
1.解决请求链路中各阶段时间延迟问题
````
````
2.延迟监控和时间消耗到关注
````
访问http://localhost:9411,可以看到zipkin管理页面

默认情况下，Elastic 只允许本机访问，如果需要远程访问，可以修改 Elastic 安装目录的config/elasticsearch.yml文件，去掉network.host的注释，将它的值改成0.0.0.0，然后重新启动 Elastic。
network.host: 0.0.0.0

后台运行
可以通过参数-d后台运行

[es@node1 elasticsearch-6.1.0]$ bin/elasticsearch -d
 curl localhost:9200
```` 
 1.下载elastic和elastic-head插件
 安装head 插件
 直接上传本地已经安装好的elasticsearch-head 文件夹就行
 ````
 ````
 2.运行elastic sh /bin/elastic -d 后台运行
 elasticsearch 不能用root启动
 Caused by: java.lang.RuntimeException: can not run elasticsearch as root
创建ES用户和组
groupadd elsearch
useradd elsearch -g elsearch
chown -R elsearch:elsearch elasticsearch-5.6.3  该命令是更改该文件夹下所属的用户组的权限

1.max file descriptors [4096] for elasticsearch process is too low, increase to at least [65536] 意思是说你的进程不够用了
 解决方案： 切到root 用户：进入到security目录下的limits.conf；执行命令 vim /etc/security/limits.conf 在文件的末尾添加下面的参数值：

    * soft nofile 65536

    * hard nofile 131072

    * soft nproc 2048

   * hard nproc 4096

前面的*符号必须带上，然后重新启动就可以了。执行完成后可以使用命令 ulimit -n 查看进程数      

 
2.[2]: max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]  需要修改系统变量的最大值了
  解决方案：切换到root用户修改配置/etc/sysctl.conf  增加配置值： vm.max_map_count=655360
　执行命令 sysctl -p   这样就可以了，然后重新启动ES服务 就可以了

3. centos 6.7 java.lang.UnsupportedOperationException: seccomp unavailable: CONFIG_SECCOMP not compiled into kernel
解决方法：

修改elasticsearch.yml 添加一下内容

bootstrap.memory_lock: false
bootstrap.system_call_filter: false
4. 如果报错不生效，来回切换一用户，在启动看看
su root 
su elsearch
 ````
 ````
 3.安装node，node -v
 下载Linux Binaries (x86/x64) 
 然后安装java一样配置环境变量，
 ````
 ```` 
 4.进去elastic-head 目录，执行npm install 安装
 执行npm run start 运行 ，访问localhost:9100
 ````
 ````
 5.vi config/elasticsearch.yml 添加跨域访问
 http.cors.enabled: true
 http.cors.allow-origin: "*"
````

windows 10 :
````
1.下载elastic和elastic-head插件(github 搜索elasticsearch-head)
````
````
2.安装node.js
````
```` 
3.解压elastic-head ,进入目录执行npm install,运行npm run start ,
````
````
4.启动elasticsearch, 访问http://localhost:9200,
````
````
5.访问http://localhost:9100
````

ELK环境搭建：
架构图：
````
 架构1：应对并发量小
 ````
    logstash  -> elasticsearch --> kibana
 ````
 架构2: 应对并发量大
 ````
 ````
 zuulserver  ->logstash-shipper1  --> redis --->logstash-indexer1 --> elasticsearch  --> kibana
 ````
 ````
 cpqserver  ->logstash-shipper2  --> redis --->logstash-indexer2 --> elasticsearch  --> kibana
 ````
 
````
1. 下载并解压elasticsearch.tar、logstash.tar、kibana.tar 
````
````
2. 在logstash 的config下面增加logstash.conf
````

**indexer**
``````
input {
	redis {
		data_type => "list"
		host => "localhost"
		port => 6379
		password => "123456"
		key => "zuulserver:logstash:redis"
	}
}

filter {

}

output {
	elasticsearch {
		hosts => ["localhost:9200"]
		index => "logstash-%{servicename}-%{+YYYY.MM.dd}"
	}
}
``````
**shipper**
``````
input {
	tcp {
		mode => "server"
		host => "localhost"
		port => 4560
		codec => "json_lines"
	}
}

filter {

}

output {
	redis {
		data_type => "list"
		host => ["localhost:6739"]
		password => "123456"
		key => "zuulserver:logstash:redis"
	}
}
``````
````
3. 修改kibana.yml文件  
````
server.port: 5601
server.host: "localhost"
elasticsearch.url: http://localhost:9200

````
4.启动logstash-shipper、logstash-indexer、kibana
````
logstash.bat -f ../config/logstash.conf
kibana.bat

后台启动kibana nohup ../bin/kibana &

````
5.访问kibana, http://localhost:5601
添加索引，
````