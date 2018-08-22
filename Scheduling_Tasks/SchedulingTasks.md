翻译自http://spring.io/guides/gs/scheduling-tasks/#_how_to_complete_this_guide  
To skip the basics, do the following:  
- Download and unzip the source repository for this guide, or clone it using Git: git clone https://github.com/spring-guides/gs-scheduling-tasks.git  
- cd into gs-scheduling-tasks/initial  
- Jump ahead to Create a scheduled task. 

### Create a scheduled task
```

src/main/java/hello/ScheduledTasks.java


package hello;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
```
Scheduled 注解定义了一个特殊的运行方法,指定了方法从开始运行到下一次运行的时间间隔。还有其他的选项如：fixedDelay，指定了完成任务后多长时间执行。可以使用@Scheduled(cron=". . .")来指定更复杂的定时任务。  
补充：  
https://blog.csdn.net/m0_37626813/article/details/78558010  
@component （把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>）  
### Enable Scheduling
```
package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }
}
```  
@EnableScheduling  确保了开启后台程序执行。如果没有添加这个注解，不会执行计划任务。  




