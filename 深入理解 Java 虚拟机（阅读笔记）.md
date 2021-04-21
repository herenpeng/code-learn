# 深入理解 Java 虚拟机（阅读笔记）

> 在本篇阅读笔记中，我会在每个小节使用一些文字对小节的内容重要性进行一些描述，从内容重要性由低到高可以分为：  
> 
了解 -> 熟悉 -> 掌握 -> 重点

## 概述

### Java 的优势（了解）

- Java 是一门结构严谨，面向对象的编程语言
- Java 摆脱了硬件平台的束缚，实现了“一次编写，到处运行”的理想
- Java 提供了一种相对安全的内存管理和访问机制，避免了绝大部分内存泄露和指针越界问题
- Java 虚拟机实现了热点代码检测和运行时编译及优化，使得 Java 应用能随着运行时间的增长而获得更高的性能
- Java 有一套完善的应用程序接口，还有无数来自商业机构和开源社区的第三方类库帮助用户实现各种各样的功能


### Java 技术体系（了解）

从传统意义上来看，JCP(`Java Community Process，Java 社区，这是一个由业界多家技术巨头组成的社区组织，用于定义和发展 Java 的技术规范`) 官方所定义的 Java 技术体系包括了以下几个组成部分：

- Java 程序设计语言
- 各种硬件平台上的 Java 虚拟机实现
- Class 文件格式
- Java 类库 API
- 来自商业机构和开源社区的第三方 Java 类库


我们可以把 Java 程序设计语言、Java 虚拟机、Java 类库这三部分统称为 JDK(Java Development Kit)，JDK 是用于支持 Java 程序开发的最小环境。

Java 类库 API 中的 Java SE API 子集和 Java 虚拟机这两部分统称为 JRE(Java Runtime Environment)，JRE 是支持 Java 程序运行的标准环境。



### Java 发展史（了解）

**1991年4月**：James Gosling 博士领导的绿色计划（Green Project）开始启动，此计划最初的目标是开发一种能够在各种消费性电子产品（如机顶盒、冰箱、收音机等）上运行的程序架构。这个计划的产品就是 Java 语言的前身：Oak（得名于 James Gosling 办公室外的一棵橡树）。Oak 当时在消费品市场上并不算成功，但随着 1995 年互联网潮流的兴起，Oak 迅速找了最适合自己发展的市场定位，并蜕变成为了 Java 语言。

**1995年5月23日**：Oak 语言改名为 Java，并在 SunWorld 大会上正式发布了 Java 1.0 版本。Java 语言第一次提出了“`Write Once,Run Anywhere`”的口号。

**1996年1月23日**:JDK 1.0 发布，Java 语言有了第一个正式版本的运行环境，JDK 1.0 提供了一个纯解释执行的 Java 虚拟机实现（Sun Classic VM）。JDK 1.0 版本的代表技术包括：Java 虚拟机、Applet、AWT 等。


**1996年4月**：十个最主要的操作系统和计算机供应商申明将在其产品中嵌入 Java 技术。5 月底，Sun 公司于美国旧金山举行了首届 JavaOne 大会，从此 JavaOne 成为了全世界数百万 Java 语言开发者每年一度的技术盛会。

**1997年2月19日**：Sun 公司发布了 JDK 1.1，Java 里许多最基础的技术支撑点（如 JDBC 等）都是在 JDK 1.1 版本中提出的，JDK 1.1 版本的技术代表有：JAR 文件格式、JDBC、JavaBeans、RMI 等。Java 语言的语法也有了一定的增强，如内部类（Inner Class）和反射（Reflection）都是在这时候出现的。  
一直到**1999年4月8日**，JDK 一共发布了 1.1.0 至 1.1.8 这 9 个版本。从 1.1.4 以后，每个 JDK 版本都有一个属于自己的名字（工程代号），分别为：JDK 1.1.4-Sparkler（宝石）、JDK 1.1.5-Pumpkin（南瓜）、JDK 1.1.6-Abigail（阿比盖尔，女子名）、JDK 1.1.7-Brutus（布鲁图，古罗马政治家和将军）和 JDK 1.1.8-Chelsea（切尔西，城市名）。


**1998年12月4号**：JDK 迎来了一个里程碑式的重要版本：工程代号为 Play ground （竞技场）的 JDK 1.2，Sun 在这个版本把 Java 技术体系拆分为三个方向，分别是面向桌面应用开发的 J2SE、面向企业级开发的 J2EE 和面向手机等移动终端开发的 J2ME。

JDK 1.2 版本的更新：

- 代表性技术有 EJB、Java Plug-in、Java IDL、Swing等
- 第一次在 Java 虚拟机中内置了 JIT(Just In Time) 即时编译器
- 内置了三个虚拟机：Classic VM、HotSpot VM 和 Exact VM
- Java API 层面，添加了 `strictfp` 关键字，类库中添加了 Collection 集合类


**1999年4月27日**：`HotSpot` 虚拟机诞生。HotSpot 最初由一家名为“Longview Techno-logies”的小公司开发，由于 HotSpot 的优异表现，这家公司在 1997 年被 Sun 公司收购。HotSpot 虚拟机刚发布时是作为 JDK 1.2 的附加程序提供的，后来它成为了 JDK 1.3 及之后所有 JDK 版本的默认 Java 虚拟机。


**2000年5月8日**：工程代号为 Kestrel（美洲红隼）的 JDK 1.3 版本发布。相对于 JDK 1.2，JDK 1.3 的改进主要体现在 Java 类库上。

JDK 1.3 版本的更新：

- 数学运算和新的 Timer API
- JNDI 服务从 JDK 1.3 开始被作为一项平台级服务提供


自从JDK 1.3开始，Sun公司维持着稳定的研发节奏：大约每隔两年发布一个JDK的主版本，以动物命名，期间发布的各个修正版本则以昆虫作为工程代号。

 
**2002年2月13日**：JDK 1.4 发布，工程代号为 Merlin（灰背隼）。JDK 1.4 是标志着 Java 真正走向成熟的一个版本，Compaq、Fujitsu、SAS、Symbian、IBM 等著名公司都有参与功能规划，甚至实现了自己独立发行的 JDK 1.4。

JDK 1.4 版本的更新：

- 正则表达式、异常链、NIO、日志类、XML 解析器和 XSLT 转换器等等


**2004年9月30日**：JDK 5 发布，工程代号 Tiger（老虎）。Sun 公司从这个版本开始放弃了谦逊的 “JDK 1.x” 的命名方式，将产品版本号修改成了 “JDK x”。从 JDK 1.2 以来，Java 在语法层面上的变动一直很小，而 JDK 5 在 Java 语法易用性上做出了非常大的改进。

JDK 5 版本的更新：

- 自动拆装箱、泛型、动态注解、枚举、可变长参数、遍历循环（foreach循环）
- 改进了 Java 的内存模型、提供了 `java.util.concurrent` 并发包

JDK 5 的官方申明可以支持 Windows 9x 操作系统的最后一个 JDK 版本。


**2006年12月11日**：JDK 6 发布，工程代号为 Mustang（野马）。在这个版本中，Sun 公司终结了从 JDK 1.2 开始已经有八年历史的J2EE、J2SE、J2ME的产品线命名方式，启用 JavaEE 6、Java SE6、JavaME6 的新命名来替代。


JDK 6 版本的更新：

- 提供初步的动态语言支持
- 提供编译器注解处理器和微型的 HTTP 服务器 API
- 改进 Java 虚拟机内部，包括锁与同步、垃圾收集、类加载等方面


**2006年11月13日**：JavaOne 大会上，Sun 公司宣布计划要把 Java 开源，在随后的一年多时间内，它陆续地将 JDK 的各个部分在 GPLv2（GNU General Public License v2） 协议下公开了源码，并建立了 OpenJDK 组织对这些源码进行独立管理。除了极少量的产权代码（Encumbered Code，这部分代码所有权不属于 Sun 公司，Sun 本身也无权进行开源处理）外，OpenJDK 几乎拥有了当时 SunJDK 7 的全部代码，OpenJDK 的质量主管曾经表示在 JDK 7 中，SunJDK 和 OpenJDK 除了代码文件头的版权注释之外，代码几乎是完全一样的，所以 OpenJDK 7与 SunJDK 7 本质上就是同一套代码库出来的产品。

JDK 6 发布以后，由于代码复杂性的增加、Java 开源、开发 JavaFX、世界经济危机 Oracle 对 Sun 的收购案等原因，Sun 公司在发展 Java 以外的事情上耗费了太多精力和资源，JDK 的更新没有能够继续维持两年发布一个主版本的研发速度，这导致了 JDK 6 的生命周期异常的长，一共发布了 211 个更新升级补丁，最后的版本为 Java SE 6 Update 211，于2018年10月18日发布。


**2009年2月19日**：工程代号为 Dolphin（海豚）的 JDK 7 完成了其第一个里程碑版本。按照 JDK 7 最初的功能规划，一共会设置十个里程碑。最后一个里程碑版本原计划定于2010年9月9日结束，但由于各种原因，JDK 7 最终无法按计划完成。

JDK 7最原始的功能清单，包含了许多重要的改进，其中规划的子项目包括：

- Lambda 项目：支持 Lambda 表达式，支持函数式编程（JDK 8 完成）
- Jigsaw 项目：虚拟机层面的模块化支持（JDK 8 完成）
- 动态语言支持：Java 是静态语言，为其他运行在 Java 虚拟机上的动态语言提供了支持（JDK 7 完成，JDK 11 改进）
- Garbage-First 收集器（JDK 7 完成）
- Coin 项目：Java 语法细节进化（JDK 8 完成）


**2009年4月20日**：Oracle 公司以每股 9.5 美元的价格收购 Sun。该交易价值约为74亿美元。同时，Oracle 公司获得了 Java 商标的版权。

**2011年7月28日**: Oracle 公司正式发布了 JDK 7 版本。

JDK 7 版本的更新：

- 提供新的 G1 收集器（G1在发布时依然处于 Experimental 状态，直至2012年4月的 Update 4 中才正式商用）
- 加强对非 Java 语言的调用支持（JSR-292，这项特性在 JDK 11 还有改动）
- 可并行的类加载架构


JDK 7 计划维护至 2022 年，迄今（面向付费用户）已发布了超过两百个更新补丁，最新版本为 JDK 7 Update 221。


**2014年3月18日**：JDK 8 正式发布。

JDK 8 版本的更新：

- Lambda 表达式的支持，这让 Java 语言拥有了流畅的函数式表达能力
- 内置 Nashorn JavaScript 引擎的支持
- 新的时间、日期 API
- 彻底移除了 HotSpot 的永久代