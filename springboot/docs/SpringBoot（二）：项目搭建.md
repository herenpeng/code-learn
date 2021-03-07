# zero-admin
>zero-admin管理系统是作者在github上面的一个开源项目，这个开源项目使用了如今市场上Web开发的各种常用技术，包括但不限于SpringBoot，MyBatisPlus，Jwt，Swagger等等，作者会通过SpringBoot的系列博客的方式，将项目中使用的技术一一展示出来，供同学们学习。
>该项目作者已经托管至github开源平台，有兴趣的同学可以前往github平台浏览，也欢迎每一个同学给该项目一个`star`。  

>项目演示地址：[http://zeroadmin.herenpeng.com](http://zeroadmin.herenpeng.com) （用户名/密码：user/111111）  
github下载地址：[https://github.com/herenpeng/zero-admin.git](https://github.com/herenpeng/zero-admin.git)  
gitee地址：[https://gitee.com/herenpeng/zero-admin.git](https://gitee.com/herenpeng/zero-admin.git)

## 项目模块

zero-admin是传统的单体架构，多模块开发，该项目一共分为一下模块：
```java
-- zero-admin
	|--zero-web
	|--zero-sys
	|--zero-common
	|--zero-code-generation
	|--zero-upload
	|--zero-mail
	|--zero-auth
```



-  **zero-admin** 项目的父项目，管理了项目的依赖及依赖版本
-  **zero-web** 启动模块，该模块包含了SpringBoot的启动类，以及一些系统级别的功能。
-  **zero-sys** 系统模块，该模块包含了一些系统底层功能，例如菜单控制，系统日志等等。
-  **zero-common** 通用模块，该模块包含了项目的通用功能，比如一些功能配置，通用工具类，统一响应结构，统一异常等等。
- **zero-code-generation** 通用代码生成模块，包含了系统的通用代码生成功能。
- **zero-upload** 系统资源上传模块，该模块用于上传系统的静态资源。
- **zero-mail** 系统邮件服务模块，该模块用于系统邮件的发送。
- **zero-auth** 系统授权模块，该模块包含了系统的授权，鉴权等功能。



## 项目依赖

### 项目依赖版本
```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <maven.compiler.encoding>UTF-8</maven.compiler.encoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <java.version>1.8</java.version>
    <zero-admin.version>1.0-SNAPSHOT</zero-admin.version>
    <spring-boot.version>2.3.8.RELEASE</spring-boot.version>
    <mybatis-plus.version>3.4.0</mybatis-plus.version>
    <jjwt.version>0.9.0</jjwt.version>
    <easypoi.version>4.0.0</easypoi.version>
    <commons-lang3.version>3.7</commons-lang3.version>
    <commons-io.version>2.6</commons-io.version>
    <springfox.version>3.0.0</springfox.version>
    <oshi-core.version>5.1.1</oshi-core.version>
    <httpclient.version>4.5.6</httpclient.version>
</properties>
```


### SpringBoot版本
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.8.RELEASE</version>
    <relativePath/>
</parent>
```
### 项目依赖

```xml
<dependencies>
    <!--SpringMVC的启动器-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!--SpringSecurity的依赖-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <!--SpringWebSocket的依赖-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>
    <!--SpringMail发送邮件的依赖-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
    <!--freemarker模板引擎-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-freemarker</artifactId>
    </dependency>
    <!--jjwt，Java对JWT的封装工具依赖-->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>${jjwt.version}</version>
    </dependency>
    <!--SpringDataRedis的启动器-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <!--Spring AOP的启动器依赖-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>
    <!--Spring单元测试的启动器-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <!--lombok的依赖-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    <!--MySQL数据库驱动-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <!--MyBatis-Plus的依赖-->
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>${mybatis-plus.version}</version>
    </dependency>
    <!--easypoi相关依赖，用于操作Excel文件-->
    <dependency>
        <groupId>cn.afterturn</groupId>
        <artifactId>easypoi-spring-boot-starter</artifactId>
        <version>${easypoi.version}</version>
    </dependency>
    <!--apache-lang3的通用工具类-->
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>${commons-lang3.version}</version>
    </dependency>
    <!--用来处理文件上传的一个通用工具类-->
    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>${commons-io.version}</version>
    </dependency>
    <!--swagger的依赖-->
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-boot-starter</artifactId>
        <version>${springfox.version}</version>
    </dependency>
    <!--oshi的依赖-->
    <dependency>
        <groupId>com.github.oshi</groupId>
        <artifactId>oshi-core</artifactId>
        <version>${oshi-core.version}</version>
    </dependency>
    <!--httpclient，用于发送http请求-->
    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpclient</artifactId>
        <version>${httpclient.version}</version>
    </dependency>
</dependencies>
```


# 项目技术

## SpringBoot
Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.8.RELEASE</version>
    <relativePath/>
</parent>
```
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```


## MyBatisPlus

MyBatis-Plus（简称 MP）是一个 MyBatis 的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。

```xml
<!--MyBatis-Plus的依赖-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>${mybatis-plus.version}</version>
</dependency>
```

## JWT

Json web token (JWT)，是为了在网络应用环境间传递声明而执行的一种基于JSON的开放标准（(RFC 7519).该token被设计为紧凑且安全的，特别适用于分布式站点的单点登录（SSO）场景。JWT的声明一般被用来在身份提供者和服务提供者间传递被认证的用户身份信息，以便于从资源服务器获取资源，也可以增加一些额外的其它业务逻辑所必须的声明信息，该token也可直接被用于认证，也可被加密。

```xml
<!--jjwt，Java对JWT的封装工具依赖-->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>${jjwt.version}</version>
</dependency>
```

## WebSocket

WebSocket 是 HTML5 开始提供的一种在单个 TCP 连接上进行全双工通讯的协议。  
WebSocket 使得客户端和服务器之间的数据交换变得更加简单，允许服务端主动向客户端推送数据。在 WebSocket API 中，浏览器和服务器只需要完成一次握手，两者之间就直接可以创建持久性的连接，并进行双向数据传输。

```xml
<!--SpringWebSocket的依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```

## Swagger

Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 restful 风格的 Web 服务。  
目标是使客户端和文件系统作为服务器以同样的速度来更新。文件的方法、参数和模型紧密集成到服务器端的代码，允许 API 来始终保持同步。

```xml
<!--swagger的依赖-->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>${springfox.version}</version>
</dependency>
```

## FreeMarker

FreeMarker 是一款 模板引擎： 即一种基于模板和要改变的数据， 并用来生成输出文本(HTML网页，电子邮件，配置文件，源代码等)的通用工具。 它不是面向最终用户的，而是一个Java类库，是一款程序员可以嵌入他们所开发产品的组件。

```xml
<!--freemarker模板引擎-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
```

## SpringDataRedis

SpringDataRedis是spring大家族中的一部分，提供了在spring应用中通过简单的配置访问redis服务，对redis底层开发包(Jedis,JRedis,andRJC)进行了高度封装，RedisTemplate提供了redis各种操作，异常处理及序列化，支持发布订阅。

```xml
<!--SpringDataRedis的启动器-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

## easyPoi

easypoi功能如同名字easy，主打的功能就是容易，让一个没见接触过poi的人员就可以方便的写出Excel导出，Excel模板导出，Excel导入，Word模板导出，通过简单的注解和模板语言(熟悉的表达式语法)，完成以前复杂的写法。

```xml
<!--easypoi相关依赖，用于操作Excel文件-->
<dependency>
    <groupId>cn.afterturn</groupId>
    <artifactId>easypoi-spring-boot-starter</artifactId>
    <version>${easypoi.version}</version>
</dependency>
```

## oshi

一款用于获取服务器主机的CPU、内存、磁盘、网络、线程、JVM内存、JVM GC 等维度的监控框架，兼容Windows和Linux操作系统。

```xml
<!--oshi的依赖-->
<dependency>
    <groupId>com.github.oshi</groupId>
    <artifactId>oshi-core</artifactId>
    <version>${oshi-core.version}</version>
</dependency>
```

## 其他

### lombok

Lombok能通过注解的方式，在编译时自动为属性生成构造器、getter/setter、equals、hashcode、toString方法。

```xml
<!--lombok的依赖-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

### commons-lang3

Apache Commons 团队提供的一个通用的工具类框架。

```xml
<!--apache-lang3的通用工具类-->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>${commons-lang3.version}</version>
</dependency>
```

### SpringMail

Spring提供的一个邮件发送服务的API框架。

```xml
<!--SpringMail发送邮件的依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```

# 最后

> 本文**Github** [https://github.com/herenpeng/code-learn](https://github.com/herenpeng/code-learn) 已收录，欢迎**Star**。