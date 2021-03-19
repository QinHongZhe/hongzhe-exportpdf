
利用OAuth2实现单点登录

接下来，只讲跟本例相关的一些配置，不讲原理，不讲为什么

众所周知，在OAuth2在有授权服务器、资源服务器、客户端这样几个角色，当我们用它来实现SSO的时候是不需要资源服务器这个角色的，有授权服务器和客户端就够了。

授权服务器当然是用来做认证的，客户端就是各个应用系统，我们只需要登录成功后拿到用户信息以及用户所拥有的权限即可

之前我一直认为把那些需要权限控制的资源放到资源服务器里保护起来就可以实现权限控制，其实是我想错了，权限控制还得通过Spring Security或者自定义拦截器来做



一、Spring Security 、OAtuh2、JWT、SSO
在本例中，一定要分清楚这几个的作用

首先，SSO是一种思想，或者说是一种解决方案，是抽象的，我们要做的就是按照它的这种思想去实现它

其次，OAuth2是用来允许用户授权第三方应用访问他在另一个服务器上的资源的一种协议，它不是用来做单点登录的，但我们可以利用它来实现单点登录。在本例实现SSO的过程中，受保护的资源就是用户的信息（包括，用户的基本信息，以及用户所具有的权限），而我们想要访问这这一资源就需要用户登录并授权，OAuth2服务端负责令牌的发放等操作，这令牌的生成我们采用JWT，也就是说JWT是用来承载用户的Access_Token的

最后，Spring Security是用于安全访问的，这里我们我们用来做访问权限控制



二、Maven依赖
认证服务器配置
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.3.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.cjs.sso</groupId>
    <artifactId>oauth2-sso-auth-server</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>oauth2-sso-auth-server</name>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security.oauth.boot</groupId>
            <artifactId>spring-security-oauth2-autoconfigure</artifactId>
            <version>2.1.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.8.1</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.56</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

这里面最重要的依赖是：spring-security-oauth2-autoconfigure