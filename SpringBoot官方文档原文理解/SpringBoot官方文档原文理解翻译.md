# SpringBoot官方文档原文理解

[TOC]



# 第六章、 Developing with Spring Boot

本文将更详细介绍应该如何使用Spring Boot，覆盖了类似系统搭建、自动配置和如何运行应用程序的话题。除此之外，也有一些Spring Boot最佳实践。虽然Spring Boot没有什么过人之处(只不过就是另一个你可以使用的库)，但是下面有一些建议，可以让你的开发过程变得更简单。

如果你是从 Spring Boot 开始的，你应该在进入这一章之前先阅读 Getting Started 引导。

- **Build Systems**

- **Structuring Your Code**

- **Configuration Classes**

- **Auto-configuration**

  

  

  

## 6.4、 **Auto-configuration**

  - **Gradually Replacing Auto-configuration**
  - **Disabling Specific Auto-configuration Classes**



Spring Boot自动配置基于添加的 jar 包依赖试图自动地构建Spring应用程序。例如：如果 HSQLDB(Hyper SQL Database) 在类路径下，并且没有手动地配置任何数据库连接的 bean，那么 Spring Boot 自动配置一个内存中的数据库。

通过向其中一个 @Configuration 配置类中添加@EnableAutoConfiguration 或者 @SpringBootApplication 注解来选择加入自动配置。

> Tips:	你应该只添加一个 @SpringBootApplication 或者 @EnableAutoConfiguration 注解，通常建议只将其中一个添加到你的主配置类中





### 6.4.1、 **Gradually Replacing Auto-configuration**

自动配置是非侵入式的，在任何时候，都可以自定义配置更换特定的自动配置。例如：如果你添加自定义的数据库 bean，那么默认内置的数据库就会失效.

如果需要了解当前应用的自动配置及其原因，打开应用程序 “--debug” 开关，这样做可以启用核心日志器作日志调试并且会将情况输出到控制台。



### 6.4.2、Disabling Specific Auto-configuration Classes

如果发现应用程序中有不想使用的自动配置类，可以使用 @SpringBootAplication 注解的排除属性来禁用他们，如下面示例所示：

```java
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class MyApplication {
}
```

如果该类不在类路径下，还可以使用注解的“excludeName”属性并且指定类的全限定名。如果你更喜欢使用 @EnableAutoConfiguration 而不是 @SpringBootApplication注解,“exclude”和 “excludeName”属性也能使用。顺便一提，使用“spring.autoconfigure.exclude”属性，还能排除自动配置类的列表

> Tips：排除(不需要的自动配置类)不仅可以在注解上而且还能使用属性

[^Note]: 虽然自动配置类是公共的，但是这些API的公共部分只能用于禁用自动配置类。这些类的实际内容，就类似于嵌套配置类或者bean方法，只能内部使用，所以我们并不推荐直接接触这些内容

