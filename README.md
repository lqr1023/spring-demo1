### spring-demo1
### 总结spring 学习笔记 示例来自spring官方网站
### 测试idea编辑器使用git  
### 用到的一些知识
### Consuming a RESTful Web Service  
- jdk8 Lambda表达式  https://www.cnblogs.com/franson-2016/p/5593080.html
基本语法:
(parameters) -> expression  
(parameters) ->{ statements; }
```
// 1. 不需要参数,返回值为 5  
() -> 5  
  
// 2. 接收一个参数(数字类型),返回其2倍的值  
x -> 2 * x  
  
// 3. 接受2个参数(数字),并返回他们的差值  
(x, y) -> x – y  
  
// 4. 接收2个int型整数,返回他们的和  
(int x, int y) -> x + y  
  
// 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)  
(String s) -> System.out.print(s)  
```  

- RestTemplate 详解  http://www.54tianzhisheng.cn/2017/12/03/RestTemplate/#EXECUTE  

