翻译自spring官网 https://spring.io/guides/gs/rest-service/ 学习用
## Building a RESTful Web Service  
创建一个service接受来自http://localhost:8080/greeting 的GET请求并返回一个json数据的问候信息  
```  
{"id":1,"content":"Hello, World!"}
```  
通过在请求url里定义参数name的值自定义问候信息：  
```  
http://localhost:8080/greeting?name=User  
```  
name参数会替换默认字符串"world"然后返回相应  
```
{"id":1,"content":"Hello, User!"}  
```
### What you’ll need  
- 大约15分钟  
- 比较喜欢的编辑器或者IDE  
- JDK1.8以上
- 可以直接将源码导入IDE  
    Spring Tool Suite (STS)  
    IntelliJ IDEA  

### How to complete this guide
- Download and unzip the source repository for this guide, or clone it using Git: git clone https://github.com/spring-guides/gs-rest-service.git
- cd into gs-rest-service/initial
- Jump ahead to Create a resource representation class.  
### Create a resource representation class  
service会处理来自/greeting的GET请求，请求路径可能含有可选参数name。响应成功应该返回200 ok的应答并同时返回一个含有问候信息的json数据，就像下列格式  
```  
{
    "id": 1,
    "content": "Hello, World!"
}
```   
id是该条问候信息的唯一标识符，content是问候信息文本。  
为了表示这条问候信息，创建一个实体类，在该java文件里含有数据，构造方法，id,content的GET方法：
```
src/main/java/hello/Greeting.java  
```  
```  
package hello;

public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}  
```  
> 之后的示例中将会看到spring 使用了Jackson JSON自动将Greeting类转换成JSON  
接下类需要创建控制层代码处理这些问候信息  
### Create a resource controller   
使用spring的方法构建一个RESTful网站服务,HTTP请求是由控制器进行处理的。通过添加注解
@RestController 可以很简单地识别出来，下面的GreetingController返回一个Greeting类的示例对象来实现处理/greeting的GET请求。  
```  
src/main/java/hello/GreetingController.java  
```  
```    
package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}  
```   
这个控制器是非常简单的，但是还需要深入理解调用模式。让我们一步一步拆开看。  
注解@RequestMapping确保了处理/greeting请求的方法是greeting()  

> 上面的例子并没有区分GET VS PUT,POST请求，因为@RequestMapping 默认处理所有的HTTP请求，可以使用@RequestMapping(method=GET)来进行限制  

@RequestParam 绑定了请求url里的字符串name的值传入greeting()的参数name,如果在请求里没有指定该参数值,默认会使用"World"  
执行完方法后会创建并返回一个新的Greeting对象，使用template格式化name字符串。  
传统的MVC控制器和RESTful控制器的最大不同之处在于HTTP响应数据是如何被创立的,不是依赖于视图技术展示将数据转成HTML,而是直接返回数据对象。该对象将会以JSON的格式响应。  

当前代码使用了Spring 4’s 的新注解@RestController，它标记了当前类是控制器,并返回一个实体数据而不是视图页面。

