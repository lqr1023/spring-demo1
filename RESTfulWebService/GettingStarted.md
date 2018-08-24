###  Consuming a RESTful Web Service  
## How to complete this guide  
To skip the basics, do the following:

    Download and unzip the source repository for this guide, or clone it using Git: git clone https://github.com/spring-guides/gs-consuming-rest.git

    cd into gs-consuming-rest/initial

    Jump ahead to Fetch a REST resource.  
    

## Fetch a REST resource 获取一个REST资源 
 http://gturnquist-quoters.cfapps.io/api/random 提供了一个随机获取Spring Boot字符串并以JSON的格式返回  
 ```
 {
   type: "success",
   value: {
      id: 10,
      quote: "Really loving Spring Boot, makes stand alone Spring apps easy."
   }
}
```  
RestTemplate 可以简单的将Rest服务绑定成为实体类。 
创建实体类 和 内部实体类
src/main/java/hello/Quote.java src/main/java/hello/Value.java    
@JsonIgnoreProperties注解表示会忽略掉任何在当前类中找不到的属性  
为了确保返回值能正确的转换成用户指定的实体类，必须确保成员变量名称和JSON的键名一致，如果名称不一致可以添加@JsonProperty注解将变量名指定为JSON的键名.  
## Make the application executable  
src/main/java/hello/Application.java  
将REST服务路径作为参数传递给restTemplate，restTemplate会将该条JSON信息转换成实体对象。不仅仅支持GET请求，同样支持POST,PUT,DELETE。  
## Managing the Application Lifecycle with Spring Boot  
目前为止，我们还没有在项目里使用到Spring Boot，使用Spring Boot会很便利并且具有很多的优势。其中一个优点就是我们可以让Spring Boot来管理RestTemplate的信息转化。  
```
package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject(
					"http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			log.info(quote.toString());
		};
	}
}  
```  


