## Accessing data with MySQL  https://github.com/spring-guides/gs-accessing-data-mysql.git    
## 创建数据库   
创建名为db_example的数据库,并创建新用户,授权
```  
mysql> create database db_example; -- Create the new database
mysql> create user 'springuser'@'localhost' identified by 'ThePassword'; -- Creates the user
mysql> grant all on db_example.* to 'springuser'@'localhost'; -- Gives all the privileges to the new user on the newly created database  
```  
## 创建application.properties文件  
Spring Boot 默认使用数据库H2,如果你想使用其他的数据库必须配置连接属性  
src/main/resources/application.properties  
```  
spring.jpa.hibernate.ddl-auto=create
spring.datasource.url=jdbc:mysql://localhost:3306/db_example
spring.datasource.username=springuser
spring.datasource.password=ThePassword  
```  
在这里spring.jpa.hibernate.ddl-auto可以设置的属性有none, update, create, create-drop，更多解释可以查看Hibernate的官方文档  
none:MySQL 默认值,不对数据库的结构进行操作  
update：Hibernate会根据实体类信息更改数据库   
create：每一次都重新创建数据库,但是关闭数据库时不会执行删除操作    
create-drop：创建数据库，当SessionFactory关闭时,同时删除数据库   
     
我们当前使用参数create因为目前还没有数据结构,第一次执行结束时,可以根据需求修改为update或者none.当你需要对数据库的结构进行操作时使用update参数.  
H2数据库和其他嵌入式数据库的默认值是create-drop，其他的数据库像MySQL的默认值就是none   
     
为了安全,当你的数据库在生产环境中建议使用none参数并且仅仅授予连接人 SELECT, UPDATE, INSERT, DELETE的数据操作权利,这个会在文末详细说明.   
## 创建实体类 src/main/java/hello/User.java  
``` 
package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    ...
}
```  
Hibernate 会自动将这个实体类转为table  
## 创建仓库  src/main/java/hello/UserRepository.java  
```  
package hello;

import org.springframework.data.repository.CrudRepository;

import hello.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}   
```  



   



