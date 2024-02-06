
> 当构建复杂的企业级应用程序时，数据一致性和可靠性是至关重要的。Spring 框架提供了强大而灵活的事务管理机制，成为开发者处理事务的首选工具。本文将深入探讨 Spring 事务的使用和原理，为大家提供全面的了解和实际应用的指导。

## 本文概览

- 首先，我们将从事务的基础出发，介绍其概念、生命周期、隔离级别、传播行为。
- 其次，我们再介绍在 Spring 中，如何应用声明式和编程式两种事务管理方式。
- 最后，我们将深入研究 Spring 事务的原理，了解其核心组件和关键类，解析其工作原理，探索它是如何做到将事务的控制与业务逻辑进行解耦的。

## 事务基础

### 事务简介

在数据库和软件开发领域，事务是`一组相关的操作`，被视为不可分割的执行单位。事务具有四个关键数据，简称 `ACID` 属性：

- 原子性（Atomicity）：事务是原子的，它要么全部执行成功，要么完全不执行。如果事务的任何部分失败，整个事务将回滚到初始状态，不会留下部分完成的结果。
- 一致性（Consistency）：事务在执行前后，数据库的状态应保持一致。这意味着事务的执行不会破坏数据库的完整性约束，如唯一性约束、外键约束等。
- 隔离性（Isolation）：多个事务并发执行时，每个事务都应该被隔离，以防止彼此之间的干扰。数据库系统通过事务隔离级别来定义事务之间的隔离程度。
- 持久性（Durability）：一旦事务成功完成，其结果应该是持久的，即使在系统故障或重启后也应该保持。数据库系统通常通过将事务的结果写入日志文件来实现持久性。



事务的生命周期通常包括一下阶段：

- 开始：事务开始时，系统记录数据库的初始状态。
- 执行：事务执行相关的数据库操作，可能包括插入、更新、删除等。
- 提交：如果事务成功执行，将对数据库的更改提交，使其成为永久性的。
- 回滚：如果在事务执行期间发生错误或者事务被显示混滚，系统将撤销事务中的所有更改，回复数据库到事务开始时的状态。



下面我们通过一些例子来深入理解下事务的生命周期过程：

案例一：开启事务并插入一条数据，执行成功并提交事务

```sql
-- 开始事务
BEGIN;
-- 执行数据库操作，向 `user` 表中插入一条数据
INSERT INTO `user` (name,age,address) VALUE ("帅气的小张",25,"山东菏泽");
-- 提交事务
COMMIT;
```

案例二：开启事务插入两条数据，其中第二条数据执行异常，事务发生回滚，那么第一条数据并没有生效

```sql
-- 开始事务
BEGIN;
-- 执行操作，向 `user` 表中插入一条数据
INSERT INTO `user` (name,age,address) VALUE ("帅气的小张",25,"山东菏泽");
-- 执行一条异常操作 address 字段拼错
INSERT INTO `user` (name,age,adress) VALUE ("帅气的小张",25,"山东菏泽");
-- 回滚事务
ROLLBACK;
```

可以看到，在 MySQL 里，执行事务的操作包括 BEGIN（开启）、COMMIT（提交）、ROLLBACK（回滚）

案例三：使用 Spring 框架时，进行声明式事务管理

```java
package com.markus.spring.transaction.service;

import com.markus.spring.data.jdbc.domain.entity.User;
import com.markus.spring.data.jdbc.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/2
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void processUser() {
        User user = new User();
        user.setName("markus zhang unique time :" + System.currentTimeMillis());
        user.setAge(25);
        user.setAddress("山东菏泽");

        // 1. 先向数据库中插入一条数据
        userDao.insertUser(user);

        // 故意抛出一个异常，验证下 第一步 的操作是否会回滚
        int i = 1 / 0;

        // 2. 再查询该数据
        User queryUserByName = userDao.queryUserByName(user.getName());
        if (Objects.isNull(queryUserByName)) {
            return;
        }
        // 3. 再更新该数据到数据库中
        queryUserByName.setAddress("北京朝阳");
        userDao.updateUser(queryUserByName);
    }
}

```

案例四：使用 Spring 框架时，进行编程式事务管理

```java
package com.markus.spring.transaction.service;

import com.markus.spring.data.jdbc.domain.entity.User;
import com.markus.spring.data.jdbc.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Objects;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/2
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public void processUserByProgram() {
        User user = new User();
        user.setName("markus zhang unique time :" + System.currentTimeMillis());
        user.setAge(25);
        user.setAddress("山东菏泽");
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    processUser();
                } catch (Exception e) {
                    // 如果发生异常，回滚事务
                    status.setRollbackOnly();
                    throw e;
                }
            }
        });
    }

    public void processUser() {
        User user = new User();
        user.setName("markus zhang unique time :" + System.currentTimeMillis());
        user.setAge(25);
        user.setAddress("山东菏泽");

        // 1. 先向数据库中插入一条数据
        userDao.insertUser(user);

        // 故意抛出一个异常，验证下 第一步 的操作是否会回滚
        int i = 1 / 0;

        // 2. 再查询该数据
        User queryUserByName = userDao.queryUserByName(user.getName());
        if (Objects.isNull(queryUserByName)) {
            return;
        }
        // 3. 再更新该数据到数据库中
        queryUserByName.setAddress("北京朝阳");
        userDao.updateUser(queryUserByName);
    }

    public List<User> queryAllUsers() {
        List<User> users = userDao.queryUsers(-1);
        return users;
    }
}
```

### 事务的隔离级别

上面在讲`隔离性`的时候提到数据库通过`事务隔离级别`来实现隔离性，那什么是`事务隔离级别`呢？



它定义了多个事务之间相互影响的程度，以及它们能否同时运行。在数据库中，有四个标准的隔离级别，分别是读未提交（Read Uncommitted）、读已提交（Read Committed）、可重复读（Repeatable Read）和串行化（Serializable）。

#### 读未提交（Read Uncommitted）

- 允许事务读取其他事务未提交的数据
- 它是最低的事务隔离级别，存在`脏读`问题，即一个事务读取到了另一个事务未提交的数据。

我们通过一个示例来理解一下：

```sql
# session one
-- 开始事务
BEGIN;
-- 执行数据库操作，向 `user` 表中插入一条数据
INSERT INTO `user` (name,age,address) VALUE ("帅气的小张",25,"山东菏泽");
-- 提交事务
COMMIT;

# session two
SELECT * FROM `user`
```

按照下图执行顺序可以看到，会话二的查询操作执行时，可以读到会话一还未提交的数据。

![image-20240203172425647](https://img.markuszhang.com/img/image-20240203172425647.png)

#### 读已提交（Read Committed）

- 保证一个事务提交后才被其他事务读取。
- 解决了`脏读`的问题，但仍存在`不可重复读`问题，即一个事务（A）在两次读取之间，另一个事务（B）修改了数据，导致 A 两次读取的数据不一致。

我们先来看下 `读已提交`解决`脏读`的场景

![image-20240203172856676](https://img.markuszhang.com/img/image-20240203172856676.png)

可以看出，再次执行事务一的插入操作后（未提交事务），事务二执行查询逻辑时，并没有查询到数据。



我们再来看下产生`不可重复读`的问题，也就是在同一事务中前后读取的数据不一致。

![image-20240203173835615](https://img.markuszhang.com/img/image-20240203173835615.png)

#### 可重复读（Repeatable Read）

- 保证一个事务在其生命周期内多次读取同一数据时，得到的结果是一致的。
- 解决了不可重复读的问题，但仍然可能存在`幻读`问题，即一个事务在两次查询之间，另一个事务插入了新的数据。

如下图所示，可以看出，`可重复读`事务隔离级别可以解决同一事务中读取到不同的数据问题，但实际上，这可能也是一种虚假的数据，也就是幻读。

![image-20240203175706512](https://img.markuszhang.com/img/image-20240203175706512.png)

#### 串行化（Serializable）

- 这是最高的事务隔离级别，确保事务之间不会发生脏读、不可重复读和幻读。
- 通过对事务进行串行化来避免并发问题，但可能导致性能下降，因为他阻塞了其他事务的并发执行。

![image-20240203181908920](https://img.markuszhang.com/img/image-20240203181908920.png)

#### Spring 中的应用

在 Spring 中，事务隔离级别通过 Isolation 接口表示。

```java
public enum Isolation {

	DEFAULT(TransactionDefinition.ISOLATION_DEFAULT),

	READ_UNCOMMITTED(TransactionDefinition.ISOLATION_READ_UNCOMMITTED),

	READ_COMMITTED(TransactionDefinition.ISOLATION_READ_COMMITTED),

	REPEATABLE_READ(TransactionDefinition.ISOLATION_REPEATABLE_READ),

	SERIALIZABLE(TransactionDefinition.ISOLATION_SERIALIZABLE);


	private final int value;


	Isolation(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}

}
```

在 Spring 中，事务隔离级别可以通过`@Transactional`注解或者`TransactionDefinition`接口进行设置。例如：

通过注解：

```java
@Transactional(isolation = Isolation.READ_COMMITTED)
public void someTransactionalMethod() {
    // 事务处理逻辑
}
```

通过编程：

```java
 public void processUserByProgram() {
      User user = new User();
      user.setName("markus zhang unique time :" + System.currentTimeMillis());
      user.setAge(25);
      user.setAddress("山东菏泽");
   		// 设置 事务隔离级别
      transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
      // 执行 事务
      transactionTemplate.execute(new TransactionCallbackWithoutResult() {
          @Override
          protected void doInTransactionWithoutResult(TransactionStatus status) {
              try {
                  processUser();
              } catch (Exception e) {
                  // 如果发生异常，回滚事务
                  status.setRollbackOnly();
                  throw e;
              }
          }
      });
  }
```

### 事务的传播行为

事务的传播行为定义了当一个事务方法被另一个事务方法调用时，他们之间的交互方式，以及新事务如何与已有事务进行关联。Spring 框架引入了事务传播行为的概念，并提供了灵活的事务管理机制，使得开发者可以根据具体需求配置事务的传播行为。可以通过`@Transactional`注解或者编程式事务管理，Spring 允许开发这选择合适的传播行为，以适应各种业务场景。下面我们来学习下几种常见的事务传播行为。

#### REQUIRED（默认值）

- 如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
- 这是最常见的传播行为，适用于大多数情况。

```java
@Transactional(propagation = Propagation.REQUIRED)
public void transactionalMethod() {
    // 事务处理逻辑
}

```

#### REQUIRED_NEW

- 总是创建一个新的事务，如果当前存在事务，则将其挂起。
- 适用于需要独立事务运行的情况。

```java
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void transactionalMethod() {
    // 事务处理逻辑
}
```

#### SUPPORTS

- 如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式执行。
- 适用于不需要事务支持的场景。

```java
@Transactional(propagation = Propagation.SUPPORTS)
public void transactionalMethod() {
    // 事务处理逻辑
}
```

#### NOT_SUPPORTED

- 以非事务的方式执行，如果当前存在事务，则将其挂起。
- 适用于不希望在事务中执行的场景。

```java
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public void transactionalMethod() {
    // 事务处理逻辑
}
```

#### NEVER

- 以非事务的方式执行，如果当前存在事务，则抛出异常。
- 适用于不希望在事务中执行，则确保不会存在事务的场景。

```java
@Transactional(propagation = Propagation.NEVER)
public void transactionalMethod() {
    // 事务处理逻辑
}
```

#### MANDATORY

- 如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
- 适用于要求在已存在的事务中运行的场景。

```java
@Transactional(propagation = Propagation.MANDATORY)
public void transactionalMethod() {
    // 事务处理逻辑
}
```

#### NESTED

- 如果当前存在事务，则创建一个嵌套事务，它是当前事务的子事务；如果当前没有事务，则行为类似于 `REQUIRED`。
- 适用于需要嵌套事务支持的场景。

```java
@Transactional(propagation = Propagation.NESTED)
public void transactionalMethod() {
    // 事务处理逻辑
}
```



## Spring 事务使用

在 Spring 中，事务的实现有两种：一种是声明式事务管理，也就是通过`@Transactional`注解声明；另一种是编程式事务管理，也就是通过程序实现。但不管是声明式事务管理还是编程式事务管理，都需要做的事情就是：

- 配置 DataSource 数据源
- 配置 TransactionManagement 事务管理器

代码就不在这里罗列了，可以参考一下我的[github项目](https://github.com/markuszcl99/guide-spring) 中`TransactionModuleApplicationConfig`类。

### 声明式事务管理

#### @Transactional 详解

`@Transactional` 是 Spring 框架中用于声明事务属性的注解。我们可以通过在方法或类上添加`@Transactional`注解，来定义事务的行为，如隔离级别、传播行为、超时的。



我们先来来看下该注解的接口定义：

```java
package org.springframework.transaction.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.TransactionDefinition;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Transactional {

	@AliasFor("transactionManager")
	String value() default "";

	@AliasFor("value")
	String transactionManager() default "";

	String[] label() default {};

	Propagation propagation() default Propagation.REQUIRED;

	Isolation isolation() default Isolation.DEFAULT;

	int timeout() default TransactionDefinition.TIMEOUT_DEFAULT;

	String timeoutString() default "";

	boolean readOnly() default false;

	Class<? extends Throwable>[] rollbackFor() default {};

	String[] rollbackForClassName() default {};

	Class<? extends Throwable>[] noRollbackFor() default {};

	String[] noRollbackForClassName() default {};

}

```

- value 与 transaction : 用于指定事务管理器的名称，表示使用哪个事务管理器。这对于配置多个事务管理器的场景很有用。
- label : 用于定义事务的标签，这是在 Spring 5.3 版本引入的新特性。具体如何使用标签取决于实现的事务管理器实现。
- propagation : 用于指定事务方法的传播行为，决定事务方法如何与已存在的事务进行交互。
    - 可选的事务传播行为包括 REQUIRED,REQUIRED_NEW,SUPPORTS,NOT_SUPPORTS,NEVER,MANDATORY,NESTED。
    - 默认为 REQUIRED。
- isolation : 用于指定事务的隔离级别，控制多个事务之间的相互影响。
    - 可选的事务隔离级别包括 READ_UNCOMMITTED,READ_COMMITTED,REPEATABLE_READ,SERIALIZABLE。
    - 默认为 DEFAULT,取决于 JDBC 的事务隔离级别，依赖于数据库。
- timeout : 指定事务的超时时间，单位为秒。如果事务执行时间超过设定的超时时间，将会被回滚。默认为`-1`，表示没有超时时间。
- timeoutString : 允许将超时时间表示为字符串，例如使用占位符。作用同 timeout ，控制事务的执行时间。
- readOnly : 用于指定事务是否为只读事务。如果设置为`true`，表示只进行读取数据库操作，可以优化事务（无需锁定资源、减少回滚风险、提高并发性能、降低资源消耗、数据库优化器的选择）。
- rollbackFor 与 rollbackForClassName : 用于指定在哪些异常情况下会回滚事务，可以执行异常类型的类型数组。
- noRollbackFor 与 noRollbackForClassName : 用于指定哪些异常下不会回滚事务，可以指定异常的类型数组。

#### 使用示例

我们来看下声明式事务管理的示例：

```java
package com.markus.spring.transaction.service;

import com.markus.spring.data.jdbc.domain.entity.User;
import com.markus.spring.data.jdbc.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: markus
 * @date: 2024/2/3 10:19 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Service
public class SpringTransactionService {

    @Autowired
    private UserDao userDao;

    // 事务传播行为 默认 REQUIRED
    @Transactional(rollbackFor = IllegalArgumentException.class, noRollbackFor = IllegalStateException.class)
    public void method() {
        User user = new User();
        long currentTime = System.currentTimeMillis();
        System.out.println("currentTime : " + currentTime);
        user.setName("帅气的小张 " + currentTime);
        user.setAge(25);
        user.setAddress("山东菏泽");
        // 向数据库中插入一条数据
        userDao.insertUser(user);

        // 抛出该异常会回滚
//        throw new IllegalArgumentException("违规参数");

        // 抛出该异常不会回滚
        throw new IllegalStateException("违规状态");
    }

    @Transactional(readOnly = true)
    public List<User> queryUsers() {
        return userDao.queryUsers(0);
    }

}
```

```java
package com.markus.spring.transaction.service;

import com.markus.spring.data.jdbc.domain.entity.User;
import com.markus.spring.transaction.config.TransactionModuleApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author: markus
 * @date: 2024/2/3 10:22 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TransactionModuleApplicationConfig.class)
public class TransactionServiceTest {

    @Autowired
    private SpringTransactionService springTransactionService;

    @Test
    public void testMethod() {
        springTransactionService.method();
    }

    @Test
    public void testQueryUsers() {
        List<User> users = springTransactionService.queryUsers();
        users.forEach(System.out::println);
    }
}
```

### 编程式事务管理

`编程式事务管理`是通过编写代码显示管理事务的一种方式，相对于`声明式事务管理`，它更加灵活，但也需要我们更深入地理解事务管理的细节。对于一些底层 API 我们不在此处赘述，重点讲述如何通过代码来显示管理事务。

#### 如何使用

我们直接上代码:

```java
package com.markus.spring.transaction.service;

import com.markus.spring.data.jdbc.domain.entity.User;
import com.markus.spring.data.jdbc.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import static com.markus.spring.data.jdbc.domain.entity.User.createUser;

/**
 * @author: markus
 * @date: 2024/2/4 12:17 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Service
public class ProgrammaticTransactionService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public void method() {
        User user = createUser();
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    userDao.insertUser(user);
                } catch (Exception e) {
                    // 捕获异常，并将事务回滚
                    status.setRollbackOnly();
                    // 并将异常跑出去
                    throw e;
                }
            }
        });
    }
}
```

在上面`声明式事务管理`中，我们可以指定一些属性；同样地，`编程式事务管理`也可以通过`TransactionTemplate`设置。

![image-20240204002314546](https://img.markuszhang.com/img/image-20240204002314546.png)

## Spring 事务实现

大家在阅读完上面的内容后，应该对`事务`有了一定的了解和如何使用。想必大家对其底层的实现机制有一定的兴趣，接下来，我们将深入探讨 Spring 事务管理的核心组件和关键类，解析其工作原理，通过深入源码的阅读和分析，我们将更好地的理解 Spring 事务管理的内部机制，为更高效、更安全地应用事务管理功能提供基础。

在学习源码前有必要提及的是：你需要掌握 Spring AOP 以及 Bean 生命周期的相关知识。因为 Spring 事务是在 Bean 生命周期环节对符合条件的 Bean 进行代理，通过 AOP 对类或者方法进行增强。

### 核心组件和关键类

#### TransactionManagement

它是在 Spring 5.2 版本被提出来的一个用来统一表示传统事务管理和响应式事务管理的标记接口。其实现类如下所示：

![image-20240206144037433](https://img.markuszhang.com/img/image-20240206144037433.png)

我们重点来看 PlatformTransactionManagement 及其相关派生类。PlatformTransactionManagement 定义了 事务管理 的基本操作。

```java
public interface PlatformTransactionManager extends TransactionManager {

	/**
	 * 获取事务
	 */
	TransactionStatus getTransaction(@Nullable TransactionDefinition definition)
			throws TransactionException;

	/**
	 * 事务提交
	 */
	void commit(TransactionStatus status) throws TransactionException;

	/**
	 * 事务回滚
	 */
	void rollback(TransactionStatus status) throws TransactionException;

}
```

对于几个比较关键类派生类介绍：

- DataSourceTransactionManagement：它是基于 JDBC 的事务管理器，适用于单一的 JDBC 数据源
    - JdbcTransactionManagement：它继承与DataSourceTransactionManagement，与之不同的是，它更加灵活，可以与多个不同的 JDBC 数据源进行交互。
- JtaTransactionManagement：它是基于 JTA（Java Transaction API）的事务管理器，支持分布式事务。比如多个不同的事务资源，如数据库、消息队列等。

#### InfrastructureAdvisorAutoProxyCreator

InfrastructureAdvisorAutoProxyCreator 用于自动创建 AOP 的代理来应用通用的基础设施增强器（Advisors），通常用于声明式事务管理、安全性检查、性能监控方面。本篇文章介绍的正是他对于声明式事务管理方面的应用。

![image-20240206145517564](https://img.markuszhang.com/img/image-20240206145517564.png)

通过上图可以看到，InfrastructureAdvisorAutoProxyCreator 是 AOP 核心组件 AbstraAutoProxyCreator 的实现之一，目的就是创建 AOP 代理。

#### TransactionInterceptor

作用如其名，事务拦截器。是的，它实现了 AOP 的概念，用于在方法调用前后织入事务管理的逻辑。

![image-20240206145826965](https://img.markuszhang.com/img/image-20240206145826965.png)

#### AnnotationTransactionAttributeSource

AnnotationTransactionAttributeSource 是 Spring 框架用于解析 @Transaction 注解的类之一，它实现了 TransactinAttributeSource 接口，用于从注解中解析事务属性。

![image-20240206151034838](https://img.markuszhang.com/img/image-20240206151034838.png)

#### BeanFactoryTransactionAttributeSourceAdvisor

BeanFactoryTransactionAttributeSourceAdvisor 是 Spring 框架中用于基于 BeanFactory 的事务属性源的增强器（Advisor）。它的作用是根据配置的事务属性源（TransactionAttributeSource）和事务拦截器（MethodInterceptor），为目标方法生成事务增强器，实现方法的事务管理。

![image-20240206174840185](https://img.markuszhang.com/img/image-20240206174840185.png)

### 工作原理

简单用一句话描述原理就是：Spring 框架通过 AOP 实现了对业务方法执行的事务管理。详细来讲就比较复杂了，需要大家掌握 Spring Bean 生命周期、AOP 拦截、事务生命周期等。

由于比较复杂，所以对于 Bean 何时以及如何被代理，我就简单说下即可，来重点讲述下 Spring 框架如何进行的事务管理。



对于本小节的叙述，如以下所示：

- 简单说下目标 Bean 何时以及如何被代理
- 详细说下事务构建和事务执行
- TransactionInterceptor#invoke() 流程介绍

##### 自动代理

Spring 通过 InfrastructureAdvisorAutoProxyCreator 实现对目标 Bean 对象的代理。具体入口在 org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#postProcessAfterInitialization，下面来简单介绍下该方法的执行逻辑。

```java
@Override
public Object postProcessAfterInitialization(@Nullable Object bean, String beanName) {
  if (bean != null) {
    // 获取 cacheKey，设计了缓存来加速 Advisor 的构建。
    // 可以参考 
    // 		private final Map<Object, Class<?>> proxyTypes = new ConcurrentHashMap<>(16);
    // 		private final Map<Object, Boolean> advisedBeans = new ConcurrentHashMap<>(256);
    Object cacheKey = getCacheKey(bean.getClass(), beanName);
    if (this.earlyProxyReferences.remove(cacheKey) != bean) {
      // 构造 Advisor 集合并创建出代理
      return wrapIfNecessary(bean, beanName, cacheKey);
    }
  }
  return bean;
}
protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
  // 如果 beanName 不为空并且设置了自定义的目标对象，则不需要被代理。
  if (StringUtils.hasLength(beanName) && this.targetSourcedBeans.contains(beanName)) {
    return bean;
  }
  // 缓存优化，历史处理过该 Bean 并且知道不需要被代理，直接返回。
  if (Boolean.FALSE.equals(this.advisedBeans.get(cacheKey))) {
    return bean;
  }
  // 如果是 Spring Framework 基础设施 Bean 或者 判断该 Bean 需要跳过，则不需要被代理
  if (isInfrastructureClass(bean.getClass()) || shouldSkip(bean.getClass(), beanName)) {
    this.advisedBeans.put(cacheKey, Boolean.FALSE);
    return bean;
  }

  // 获取 Advice 数据集合并创建最终的代理
  // getAdvicesAndAdvisorsForBean 方法是一个查找 IoC 容器中 Advice Bean 的方法。
  // 而我们前面说的 BeanFactoryTransactionAttributeSourceAdvisor 就是在这里获取到的。
  Object[] specificInterceptors = getAdvicesAndAdvisorsForBean(bean.getClass(), beanName, null);
  if (specificInterceptors != DO_NOT_PROXY) {
    this.advisedBeans.put(cacheKey, Boolean.TRUE);
    // 创建代理。
    Object proxy = createProxy(
        bean.getClass(), beanName, specificInterceptors, new SingletonTargetSource(bean));
    this.proxyTypes.put(cacheKey, proxy.getClass());
    // 返回代理。
    return proxy;
  }

  this.advisedBeans.put(cacheKey, Boolean.FALSE);
  return bean;
}
```

上面我们看到了 Spring 是如何给 Bean 进行代理的，可能大家会有疑问：怎么判断当前 Bean 是否可以被代理呢？

这就是 AOP 框架中 Pointcut 组件来决策的，针对于事务功能上，对应了 BeanFactoryTransactionAttributeSourceAdvisor，我们前面已经介绍过，它是为目标方法生成事务增强器，其中它就包含 Pointcut 实现即 TransactionAttributeSourcePointcut。我们来看下它的内部组成，来探究下：它是如何判断当前 Bean 是否应该被代理。

```java
abstract class TransactionAttributeSourcePointcut extends StaticMethodMatcherPointcut implements Serializable {

	protected TransactionAttributeSourcePointcut() {
		setClassFilter(new TransactionAttributeSourceClassFilter());
	}
  
  // 判断 是否被代理 的核心方法
	@Override
	public boolean matches(Method method, Class<?> targetClass) {
   	// tas.getTransactionAttribute 获取 @Transactional 注解元信息，如果该方法携带相关数据，说明需要被事务管理。
		TransactionAttributeSource tas = getTransactionAttributeSource();
		return (tas == null || tas.getTransactionAttribute(method, targetClass) != null);
	}

	/**
	 * Obtain the underlying TransactionAttributeSource (may be {@code null}).
	 * To be implemented by subclasses.
	 */
	@Nullable
	protected abstract TransactionAttributeSource getTransactionAttributeSource();


	/**
	 * 用于过滤不需要事务管理的类
	 */
	private class TransactionAttributeSourceClassFilter implements ClassFilter {

		@Override
		public boolean matches(Class<?> clazz) {
			if (TransactionalProxy.class.isAssignableFrom(clazz) ||
					TransactionManager.class.isAssignableFrom(clazz) ||
					PersistenceExceptionTranslator.class.isAssignableFrom(clazz)) {
				return false;
			}
      // 判断 当前类是否是携带 @Transactional 注解的候选类（在类型、方法上）
			TransactionAttributeSource tas = getTransactionAttributeSource();
			return (tas == null || tas.isCandidateClass(clazz));
		}
	}

}
```

通过 TransactionAttributeSourcePointcut 的处理，我们可以得出上面的结论，即 Spring 框架通过 TransactinAttributeSourcePointcut 来筛选目标方法，来对方法进行事务管理逻辑增强。



通过上面的讨论，在自动代理环节，我们能得出这样一个结论：Spring 框架通过 AOP 实现了目标 Bean 的方法增强，增加事务管理的逻辑。我们把其核心类串一下：

1. Spring 向 IoC 容器注册 InfrastructureAdvisorAutoProxyCreator Bean，
2. 该基础设施 Bean 可以在`业务Bean`生命周期的`初始化后(postProcessAfterInitialization)`环节，对目标业务 Bean 进行拦截，获取容器注册过的事务增强器（BeanFactoryTransactionAttributeSourceAdvisor）并为目标 Bean 生成代理，完成事务管理逻辑增强。
3. 而 BeanFactoryTransactionAttributeSourceAdvisor 中的 TransactionAttributeSourcePointcut 完成目标 Bean 及其方法的筛选拦截。

##### 执行事务

目前，我们已经清楚业务 Bean 如何被事务管理增强，接下来我们继续讨论当应用程序调用被事务管理的方法时，事务是如何被执行的。

在核心组件介绍 BeanFactoryTransactionAttributeSourceAdvisor 时，它是一个 PoicutcutAdvisor，也就是说它是由 Pointcut 和 Advice 组成。在前面说目标方法筛选时`Pointcut`起到了作用。那么 Advice 则是在执行事务时起到作用，其实现则是 TransactionInterceptor。为什么说是它的，我们可以在这段代码中找到答案。

```java
private static class AopAutoProxyConfigurer {

		public static void configureAutoProxyCreator(Element element, ParserContext parserContext) {
			AopNamespaceUtils.registerAutoProxyCreatorIfNecessary(parserContext, element);

			String txAdvisorBeanName = TransactionManagementConfigUtils.TRANSACTION_ADVISOR_BEAN_NAME;
			if (!parserContext.getRegistry().containsBeanDefinition(txAdvisorBeanName)) {
				Object eleSource = parserContext.extractSource(element);

				// Create the TransactionAttributeSource definition.
				RootBeanDefinition sourceDef = new RootBeanDefinition(
						"org.springframework.transaction.annotation.AnnotationTransactionAttributeSource");
				sourceDef.setSource(eleSource);
				sourceDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
				String sourceName = parserContext.getReaderContext().registerWithGeneratedName(sourceDef);

				// Create the TransactionInterceptor definition.
				RootBeanDefinition interceptorDef = new RootBeanDefinition(TransactionInterceptor.class);
				interceptorDef.setSource(eleSource);
				interceptorDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
				registerTransactionManager(element, interceptorDef);
				interceptorDef.getPropertyValues().add("transactionAttributeSource", new RuntimeBeanReference(sourceName));
				String interceptorName = parserContext.getReaderContext().registerWithGeneratedName(interceptorDef);

				// Create the TransactionAttributeSourceAdvisor definition.
				RootBeanDefinition advisorDef = new RootBeanDefinition(BeanFactoryTransactionAttributeSourceAdvisor.class);
				advisorDef.setSource(eleSource);
				advisorDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
				advisorDef.getPropertyValues().add("transactionAttributeSource", new RuntimeBeanReference(sourceName));
				advisorDef.getPropertyValues().add("adviceBeanName", interceptorName);
				if (element.hasAttribute("order")) {
					advisorDef.getPropertyValues().add("order", element.getAttribute("order"));
				}
				parserContext.getRegistry().registerBeanDefinition(txAdvisorBeanName, advisorDef);

				CompositeComponentDefinition compositeDef = new CompositeComponentDefinition(element.getTagName(), eleSource);
				compositeDef.addNestedComponent(new BeanComponentDefinition(sourceDef, sourceName));
				compositeDef.addNestedComponent(new BeanComponentDefinition(interceptorDef, interceptorName));
				compositeDef.addNestedComponent(new BeanComponentDefinition(advisorDef, txAdvisorBeanName));
				parserContext.registerComponent(compositeDef);
			}
		}
	}
```

至此，我们已经清晰代理方法的入口了，即 org.springframework.transaction.interceptor.TransactionInterceptor#invoke。接下来，我们就重点分析该方法。

```java
@Override
@Nullable
public Object invoke(MethodInvocation invocation) throws Throwable {
  // Work out the target class: may be {@code null}.
  // The TransactionAttributeSource should be passed the target class
  // as well as the method, which may be from an interface.
  Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);

  // Adapt to TransactionAspectSupport's invokeWithinTransaction...
  return invokeWithinTransaction(invocation.getMethod(), targetClass, new CoroutinesInvocationCallback() {
    @Override
    @Nullable
    public Object proceedWithInvocation() throws Throwable {
      return invocation.proceed();
    }
    @Override
    public Object getTarget() {
      return invocation.getThis();
    }
    @Override
    public Object[] getArguments() {
      return invocation.getArguments();
    }
  });
}

@Nullable
protected Object invokeWithinTransaction(Method method, @Nullable Class<?> targetClass,
    final InvocationCallback invocation) throws Throwable {

  // If the transaction attribute is null, the method is non-transactional.
  TransactionAttributeSource tas = getTransactionAttributeSource();
  final TransactionAttribute txAttr = (tas != null ? tas.getTransactionAttribute(method, targetClass) : null);
  final TransactionManager tm = determineTransactionManager(txAttr);

  if (this.reactiveAdapterRegistry != null && tm instanceof ReactiveTransactionManager) {
    // 针对 响应式编程 的处理，我们不关注，先删除，大家有兴趣可以去看下
  }

  // 获取事务管理器
  PlatformTransactionManager ptm = asPlatformTransactionManager(tm);
  // 获取目标业务方法
  final String joinpointIdentification = methodIdentification(method, targetClass, txAttr);

  if (txAttr == null || !(ptm instanceof CallbackPreferringPlatformTransactionManager)) {
    // 这里就是声明式事务管理的处理逻辑 @Transactional 注解
    // Standard transaction demarcation with getTransaction and commit/rollback calls.
    // 获取 事务，这里面包括了事务的传播行为、事务隔离级别以及事务的提交、回滚等信息
    TransactionInfo txInfo = createTransactionIfNecessary(ptm, txAttr, joinpointIdentification);

    Object retVal;
    try {
      // This is an around advice: Invoke the next interceptor in the chain.
      // This will normally result in a target object being invoked.
      // 通常的 拦截器不只有一个，是一个链式的。如上面作者所述，这是一个 Around Advice，将会调用拦截器链中下一个拦截器。
      // 但 返回的结果 retVal 是 目标方法的执行结果。
      retVal = invocation.proceedWithInvocation();
    }
    catch (Throwable ex) {
      // target invocation exception
      // 这里的处理则是 对目标异常进行回滚。目标异常外的异常不进行回滚
      completeTransactionAfterThrowing(txInfo, ex);
      throw ex;
    }
    finally {
      // 清除事务信息在当前线程下
      cleanupTransactionInfo(txInfo);
    }

    if (retVal != null && vavrPresent && VavrDelegate.isVavrTry(retVal)) {
      // Set rollback-only in case of Vavr failure matching our rollback rules...
      TransactionStatus status = txInfo.getTransactionStatus();
      if (status != null && txAttr != null) {
        retVal = VavrDelegate.evaluateTryFailure(retVal, txAttr, status);
      }
    }
    // 目标方法执行成功后返回结果 进行事务提交
    commitTransactionAfterReturning(txInfo);
    // 将 目标值 返回。
    return retVal;
  }

  else {
    // 针对 CallbackPreferringPlatformTransactionManager 事务管理的处理，多处理 编程式事务管理，我们暂时不关注，先删除。
  }
}
```

## 本文总结

我们总结一下，本篇文章从`事务基础`介绍开始，讲述了什么是事务、隔离级别、传播行为，接着又讲述了 Spring 事务的使用即`声明式事务管理`以及`编程式事务管理`，最后又讲述的 Spring 的 事务实现原理包括核心组件、关键类以及事务的工作原理。

至此，关于事务的知识点就讲述完了。如果还有其他没有覆盖到的地方，欢迎交流。😄