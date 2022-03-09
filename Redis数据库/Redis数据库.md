# Redis数据库

 **Redis**（**Remote Dictionary Server** 远程字典服务器）是一个完全开源免费、用==C语言==编写和遵守`BSD`协议的高性能（Key/Value）分布式内存数据库。它基于内存运行并支持持久化的==NoSQL==数据库，是当前最热门的NoSQL数据库之一，同时也被人们称为**数据结构服务器**。<http://redis.io/>

[TOC]



# 一、NoSQL基础知识

- 早期数据库使用的技术
- 什么是NoSQL
- NoSQL的优点
- 互联网时代的“3V”和“3高”
- NoSQL数据库理论





## 1.1、早期数据库使用的技术

1. 单机MySQL

在90年代，一个网站的访问量都不大，可以用一个数据库轻松应对。 



2. Memcached(缓存) + MySQL + 垂直拆分

![Memcached(缓存) + MySQL + 垂直拆分](图片/垂直拆分.png)



随着访问量的上升，几乎大部分使用MySQL架构的网站在数据库上都开始出现了性能问题，Web程序不再仅仅关注在功能上，同时也在追求性能，程序员们开始大量的使用缓存技术来缓解数据库的压力，优化数据库的结构和索引。开始比较流行的是通过文件缓存来缓解数据库压力，当时当访问量过大的时候，多态WEB机器通过文件缓存，大量的小文件缓存也带了比较高的==IO==压力。在这个时候，``Memcached``就自然成为了一个非常时尚的技术产品。



3. MySQL主从读写分离

![读写分离](图片/读写分离.png)

由于数据库的写入压力增加，Memcached只能缓解数据库的读写压力。读写集中在一个数据库上让数据库不堪重负，大部分网站开始使用主从复制技术来达到读写分离，以提高读写性能和读库的可扩展性。MySQL的master-slave模式成为这时候的网站标配



4. 分库分表 + 水平拆分 + MySQL集群

![MySQL集群](图片/MySQL集群.png)

 

在Memcached的高速缓存，MySQL的主从复制，读写分离的基础之上，这是MySQL主库的写压力开始出现瓶颈，而数据量的持续猛增，由于MyISAM使用表锁，在高并发下会出现严重的锁问题，大量的高并发MySQL应用开始使用InnoDB引擎代替MyISAM。同时，开始流行使用分表来缓解压力和数据增长的扩展问题。这个时候，分表分库成了一个热门技术，是面试的热门问题也是业界讨论的热门技术问题。也就是在这个时候，MySQL推出了还不太稳定的表分区，这也给技术实力一般的公司带来了希望。虽然MySQL推出了MySQL Cluster集群，但是性能也不能很好满足互联网的要求，只是在高可靠性上提供了非常大的保证。



> MySQL的扩展性瓶颈

 MySQL数据库也经常存储一些大文本字段，导致数据库表非常的大，在做数据库回复的时候就导致非常的慢，不容易快速恢复数据库，比如1000万4KB大小的文本就接近40GB的大小，如果能把这些数据从MySQL中省去，MySQL将变得非常小，关系型数据库很强大，但是他不能很好地应付所有的应用场景。MySQL的扩展性差，大数据下IO压力大，表结构更改困难，正是当前使用MySQL的开发人员面临的问题



## 1.2、什么是NoSQL

> 简介

![Not only SQL](图片/Not only SQL.jpg)

 ==NoSQL（not only SQL），意为“不仅仅是SQL。泛指非关系型的数据库。==随着互联网WEB2.0网站的兴起，传统的关系数据库在应付WEB2.0网站， 特别是超大规模和高并发的SNS类型的WEB2.0纯动态网站已经显得力不从心。

![什么是NoSQL](图片/什么是NoSQL.png)



NoSQL数据库的产生就是为了解决大规模数据集合多重数据种类带来的挑战，尤其是大数据应用 难题，包括超大规模数据的存储。==这些类型的数据不需要固定的模式，无需多余的操作就可以横向扩展。(是什么、能干嘛、去哪下、怎么玩)==



## 1.3、NoSQL的优点

> 易扩展

NoSQL数据库种类繁多，但是有一个共同的特点都是去掉关系数据库的关系型特性，数据之间无关系，容易扩展。



> 大数据量高性能

NoSQL数据库都具有非常高的读写性能，尤其在大数据量下，同样表现优秀。这得益于它的无关性，数据库的结构简单



> 多样灵活的数据模型

NoSQL无需事先为要存储的数据建立字段，随时可以存储自定义的数据格式。而是在关系数据库里，增删字段是一件非常麻烦的事情。



> 传统RDBMNS ==VS== NoSQL

- RDBMNS：高度结构组织化数据、结构化查询语言、严格的一致性、基础事务、`CAID`定理

- NoSQL：不仅仅是SQL、没有声明性查询语言、没有预定义模式、`CAP`定理、键-值对存储



## 1.4、互联网时代的“3V”和“3高”

> 简介

- 大数据时代的3V：海量Volume、多样Variety、实时Velocity

- 互联网需求的3高：高并发、高可扩、高性能



## 1.5、NoSQL数据库理论

- NoSQL的应用场景

- NoSQL的数据模型

- NoSQL数据库的四大分类
- 分布式数据库CAP原理
- BASE理论
- 分布式系统和集群



### 1.5.1、NoSQL的应用场景

==阿里巴巴中文站架构发展历程：==

√	1999年，第一代网络架构：Perl、CGI、Oracle
√	2000年，Java、Servlet
√	2001-2004年，EJB（SLSB、CMP、MDB）
√	2005-2007 重构EJB，换成Spring+iBatis + Webx、Antx，底层架构：iSearch，MQ+ESB，数据挖掘，CMS
√	2008-2009 Memcached集群，MySQL + 数据切分 = Cobar，分布式存储，Hadoop，KV、CDN



### 1.5.2、NoSQL的数据模型

> 简介

以一个电商客户、订单、订购、地址模型来对比下关系型数据库和非关系型数据库聚合模型。     NoSQL使用BSON（Binary JSON：是一种类JSON的一种二进制形式的存储格式，和JSON一样，支持内嵌的文档对象和数组对象）画出构建的数据模型

![NoSQL数据模型](图片/NoSQL数据模型.png)



> 聚合模型

1. K-V 键值对
2. BSON
3. 列族：顾名思义，是按列存储数据的。最大的特点是方便存储结构话和半结构化数据，方便做数据压缩，针对某一列或者某几列的查询有非常的大的IO优势
4. 图形



### 1.5.3、NoSQL数据库的四大分类

> K-V 键值对（查找速度快）

- 新浪：BerkeleyDB + Redis

- 美团：Redis + Tair

- 阿里、百度：Memcache + Redis



> 文档型数据库（BSON比较多，数据结构要求不严谨，表结构可变）

- CouchDB

- MongoDB：是一个基于分布式文件存储的数据库，由C++语言编写，旨在为WEB应用提供可扩展的高性能数据存储解决方案，它还是一个介于关系型数据库和非关系型数据库之间的产品，是非关系型数据库中功能最丰富且最像关系型数据库的



> 列存储数据库（查找速度快，可扩展性强）

- Cassandra、HBase

- 分布式文件系统



> 图关系数据库（利用图结构算法）

- 社交网络，推荐系统等。专注于构建关系图谱

- Neo4J、InfoGrid



### 1.5.4、分布式数据库CAP原理

1. 传统的ACID原理

![ACID原理](图片/ACID原理.bmp)

`A（Atomicity）原子性`：**原子性很容易理解，也就是说事务里的所有操作要么全部做完，要么都不做**

`C（Consistency）一致性`：**数据库要一直处于一致的状态，事务的运行不会改变数据库原本的一致性约束**

`I（Isolation）独立性`：**并发的事务之间不会互相干扰**

`D（Durability）持久性`：**一旦事务提交后，它所做的修改将会永久的保存在数据库中，即使出现宕机也不会丢失**



2. CAP理论

![CAP理论](图片/CAP理论.bmp)

总共有C（Consistency）、强一致性、A（Availability）可用性和P（Partition tolereance）分区容错性这三个特性

> 理论核心

当一个分布式系统出现故障时，不能同时满足这三个需求，那么只能同时满足两个需求。

**CA：传统Oracle数据库**

AP：大多数网站架构的选择

CP：Redis、MongoDB



### 1.5.5、BASE理论

就是为了解决关系数据库强一致性引起的可用性降低的问题的解决方案。

它的思想是通过让系统放松对某一时刻数据一致性的要求来换取系统整体伸缩性和性能上改观。为什么这么说？因为大型系统往往由于地域分布和极高性能的要求，不可能采用分布式事务来完成这些指标，要想获得这些指标，我们必须采用另外一种方法来完成，

这里BASE就是解决问题的方法BASE：

（Basically Available）基本可用

（Soft state）软状态

（Eventually consistent）最终一致



### 1.5.6、分布式系统和集群

> 简介

 **分布式系统（Distributed System）**是由多台计算机和通信的软件组件通过计算机网络连接（本地网或局域网）组成。分布式系统是建立在网络之上的软件系统。正是因为软件的特性，所以分布式系统具有高度的内聚性和透明性。因此，网络和分布式系统之间的区别更多的在于高层软件，而不是硬件。分布式系统可以应用在PC、工作站、局域网和广域网上等。

- 分布式

  不同的多台服务器上面部署不同的模块，他们之间通过RPC/RMI通信和调用，对外提供服务和组内协调

- 集群

  不同的多台服务器上面部署相同的服务模块，通过分布式调度软件进行统一的调度，对外提供服务和访问。



# 二、Redis数据库入门

- Redis是什么
- Redis的特点



## 2.1、Redis是什么

> 简介

**Redis（Remote Dictionary Server 远程字典服务器）是一个完全开源免费、用C语言编写和遵守BSD协议的高性能（Key/Value）分布式内存数据库。它基于内存运行并支持持久化的NoSQL数据库，是当前最热门的NoSQL数据库之一，同时也被人们称为==数据结构服务器==。**

![Redis](图片/Redis.bmp)



## 2.2、Redis的特点

- 支持数据的持久化：Redis支持异步将内存中的数据写入到磁盘中，同时不影响服务

- 提供list、set、zset、hash等数据结构的存储

 * 支持master-slave模式的数据备份



## 2.3、下载和安装Redis

- Windows下安装
- Linux下安装

- Linux常用命令
- Linux的目录结构



原版网站：http://redis.io/

中文网站：http://www.redis.cn/Redis

安装教程：https://www.runoob.com/redis/redis-install.html



### 2.3.1、Windows下安装

> 下载

下载地址：http://github.com/dmajkic/redis/downloadshttps://github.com/tporadowski/redis/releases

下载的Redis支持32位或64位系统。根据系统实际情况选择



> 安装

将64bit的内容拷贝到安装目录下，打开CMD窗口，使用CD命令切换到安装目录下，

运行命令：`redis-server.exe redis.windows.conf`。



> 试运行

把Redis的路径放到系统path环境变量下。这时候再开一个CMD窗口，切换到Redis目录下运行命令：

```shell
redis-cli.exe -h 127.0.0.1 -p 6379
# 设置键值对 
$ set myKey abc
# 取出键值对 
$ get myKey
```



### 2.3.2、Linux下安装

![Liunx下安装Redis](图片/Liunx下安装Redis.png)

> 下载

官网下载最新版本的redis的tar.gz后缀的压缩文件，下载 完成后解压.gz格式的压缩文件

``` shell
# 解压命令：
$ tar -zxvf redis-3.0.4.tar.gz
```

> 安装

- 解压之后会出现redis-3.0.4目录，使用copy命令备份一份并放入我们指定的Linux目录：/opt
- 再进入目录： `cd redis-3.0.4`  并执行`make`命令

- 如果没有make命令，则：

```shell
$ sudo apt-get update
$ sudo apt-get install make

# 或者
$ yum install gcc-c++
```

- make命令完成后继续执行`make install`
- 查看默认安装目录：usr/local/bin
- 启动、helloworld，关闭



### 2.3.3、Linux常用命令

> 命令

- wget 下载外网的东西
- cd 目录名    转到目录
- ls -l     查看当前目录所有文件
- clear    清屏



> 以下是一些 Dpkg 的普通用法：

1. dpkg -i <package.deb>
   安装一个 Debian 软件包，如你手动下载的文件。
2. dpkg -c <package.deb>
   列出 <package.deb> 的内容。
3. dpkg -I <package.deb>
   从 <package.deb> 中提取包裹信息。
4. dpkg -r <package>
   移除一个已安装的包裹。
5. dpkg -P <package>
   完全清除一个已安装的包裹。和 remove 不同的是，remove 只是删掉数据和可执行文件，purge 另外还删除所有的配制文件。
6. dpkg -L <package>
   列出 <package> 安装的所有文件清单。同时请看 dpkg -c 来检查一个 .deb 文件的内容。
7. dpkg -s <package>
   显示已安装包裹的信息。同时请看 apt-cache 显示 Debian 存档中的包裹信息，以及 dpkg -I 来显示从一个 .deb 文件中提取的包裹信息。
8. dpkg-reconfigure <package>
   重新配制一个已经安装的包裹，如果它使用的是 debconf (debconf 为包裹安装提供了一个统一的配制界面)。
   ps -ef|grep redis



### 2.3.4、Linux的目录结构

/etc：所有的系统管理所需要的配置文件和子目录

/home：存放普通用户的主目录，在Linux中每个用户都有一个自己的目录，一般该目录名是以用户的账号命名的

/lib：系统开机所需要最基本的动态链接共享库，其作用类似于windowOS里面的DLL文件。

/lost + found：这个目录一般情况下是空的，当系统非正常关机后，这里会存储文件

/media：Linux系统会自动识别一些设备，例如U盘和光驱，当识别后会将其挂载到这个目录下

/mnt：系统提供该目录是为了让用户临时挂载别的文件系统，我们可以将光驱挂载在/mnt上

/opt：这是给主机额外安装软件所摆放的目录。比如你安装一个Oracle数据库就可以放到这个目录下

/proc：这是一个虚拟目录，它是系统内存的映射，我们可以通过直接访问这个目录来获取系统信息

/root：该目录为系统管理员，也称作超级权限者用户主目录

/sbin:

/selinux:

/srv:

/sys: 系统目录

/tmp: 临时目录

/usr: 用户目录

/var:



## 2.4、Redis的Helloworld

1. Windows下的helloworld

![Windows下的helloworld](图片/Windows下的helloworld.png)



2. Ubuntu界面下的helloworld

![Ubuntu界面下的helloworld](图片/Ubuntu界面下的helloworld.png)



## 2.5、Redis基础知识讲解

> 单进程

==单进程模型来处理客户端的请求，对读写等事件的响应是通过对epoll函数的包装来做到的==。Redis的实际处理速度完全依靠主进程的执行效率。

Epoll是Linux内核为处理大批量文件描述符而做了改进epoll，是Linux下多路复用IO接口select/poll的增强版本，它能显著提高程序在大量并发连接中只有少量活跃的情况下的系统CPU利用率



> 默认16个数据库       

 像数组一样下标从零开始，初始默认零号库

`select`命令切换数据库
`Dbsize`查看当前数据的key数量
`Flushdb`：清空当前库
`Flushall`：通杀全部库



> 统一密码管理，

16个库都是一样的密码，要么都连得上，要么一个也连不上



> 其他基础知识

Redis索引默认从零开始
为什么默认端口是6379：MERZ
常用命令可以用Tab自动补全大写



> Tips:
>
> - redis-banchmark
> - redis-check-aof
> - redis-cli
> - redis-sentinel
> - redis-server
>
> ```shell
> ====== SET ======
>   10000 requests completed in 0.10 seconds
>   50 parallel clients
>   3 bytes payload
>   keep alive: 1
> 
> 
> 97.80% <= 1 milliseconds
> 99.01% <= 2 milliseconds
> 100.00% <= 2 milliseconds
> 95238.10 requests per second
> 
> 
> ====== GET ======
>   10000 requests completed in 0.10 seconds
>   50 parallel clients
>   3 bytes payload
>   keep alive: 1
> 
> 
> 98.39% <= 1 milliseconds
> 100.00% <= 1 milliseconds
> 102040.82 requests per second
> ```



## 2.6、五大数据类型

- String（字符串）
- Hash（哈希）
- List（列表）
- Set（集合）
- zset(sorted set：有序集合)



### 2.6.1、String（字符串）

> 简介

**String 是 Redis 最基本的类型，你可以理解成与 Memcached 一模一样的类型，一个 key 对应一个 value。**String 类型是*二进制安全*的。意思是 Redis 的 String 可以包含任何数据。比如==.jpg图片==或者==序列化的对象==。String 类型是 Redis 最基本的数据类型，String 类型的值最大能存储 512MB。



> Key常用命令

`keys * `：选择当前库中所有key
`keys k?`： 通配当前库所有k开头的key
`move key db`：把键移到指定的数据库
`exists key`：判断某个 键是否存在
`expire key second`：为给定的key设置过期时间
`ttl key`：查看过期时间，-1表示永不过期，-2表示已过期
`type key`：查看key是什么 
`del key`：删除key
`dump key`：序列化给定key，并返回被序列化的值



> String常用命令

`set / get / del / append / strlen` ：这些是基础操作 

==设置键值对 / 获取键值对 / 删除键值对 / 附加字符到指定的键值对中 / 获取字符串的长度==

`incr / decr / incrby / decrby` ：键值对一定要是数字才能进行加减操作

==当前键值对+1 / 当前键值对-1 / 和某个键值对相加 / 和某个键值对相减==

`getrange/setrange` ：==获取指定区间内的值 / 设置指定区间内的值==

`setex(set with expire)键秒值 / setnx(set if not exist)`：

==设置键值对时同时设置过期时间 / 设置键值对时查看是否已存在，如果存在就不做设置==

`mset / mget / msetnx`：==在获取或设置key做批处理操作，批量设置 / 获取 / 查看是否已存在，如果存在就不做设置==
`getset` ： ==设置之后同时获取该键的值==

