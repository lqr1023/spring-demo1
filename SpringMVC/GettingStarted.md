git clone https://github.com/spring-guides/gs-serving-web-content.git
### Serving Web Content with Spring MVC
用spring创建web应用的时候，Http请求是通过控制器来进行处理的，你可以用@Controller注解来标识，在下面的例子中，GreetingController处理了来自/greeting的Get请求并返回了一个greeting视图，这个视图通常用来渲染一个网页  
src/main/java/hello/GreetingController.java  
```   
package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

}  
```   
@GetMapping("/greeting")注解确保了来自/greeting的Get请求由greeting()方法来处理,@RequestParam绑定了请求字符串name的值到greeting()方法的name参数里，这个参数不是必须的,如果缺少了这个参数就会使用默认的defaultvalue的值在这里是"world",这个值被添加到model对象里,最后可以由视图模板读取到。  
这个方法体的实现依赖于视图技术，在这里使用的是thymeleaf来完成html页面的渲染工作,Thymeleaf 解析了greeting.html模板，解析到th:text 表达式替换为控制层配置的name参数值。  
src/main/resources/templates/greeting.html
```  
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <p th:text="'Hello, ' + ${name} + '!'" />
</body>
</html>  
```    
### Add a Home Page  
静态资源，像HTML,javascript或者css，如果放到正确的资源路径下，可以很容易的被Spring Boot应用获取到,默认情况下 Spring Boot会在/static或/public下加载静态页面。index.html是比较特殊的资源文件，因为这个文件经常被用作主页。也就是说如果存在这个文件那么将会被当做项目的根目录,比如当前示例中的http://localhost:8080/ 所以我们创建下列文件：  
src/main/resources/static/index.html  
``` 
<!DOCTYPE HTML>
<html>
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <p>Get your greeting <a href="/greeting">here</a></p>
</body>
</html>   
```  
如果重启这个页面会在http://localhost:8080/路径下看到这个文件




