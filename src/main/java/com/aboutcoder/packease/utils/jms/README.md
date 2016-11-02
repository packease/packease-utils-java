PE.utils.jms
=============

> 提供了jms消息的适配层封装,提供了consumer和producer的自定义wrapper。

实践示例, 采用spring jms模块封装的实现办法:

#### 一、增加spring-jms配置

> spring-jms.xml 连接池

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.1.xsd">

    <bean id="connectionFactoryCache" class="org.springframework.jms.connection.CachingConnectionFactory">
        <property name="targetConnectionFactory">
            <bean class="org.apache.activemq.ActiveMQConnectionFactory">
                <property name="brokerURL">
                    <value>failover://(tcp://127.0.0.1:61616)?randomize=false&amp;jms.useAsyncSend=true</value>
                </property>
            </bean>
        </property>
        <property name="sessionCacheSize" value="1"/>
    </bean>

</beans>
```

> spring-jms.xml 消费者

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.1.xsd">

    <!-- 配置线程池以及消息队列监听器 -->
    <bean id="queueTaskPool" class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
        <property name="corePoolSize" value="4" />
        <property name="maxPoolSize" value="4" />
        <property name="queueCapacity" value="4" />
        <property name="threadNamePrefix" value="QueueTaskPool-" />
    </bean>
    <bean id="demoQueueListener"  class="org.springframework.jms.listener.DefaultMessageListenerContainer" lazy-init="true">
        <property name="connectionFactory" ref="connectionFactoryCache" />
        <property name="concurrentConsumers" value="4" />
        <property name="maxConcurrentConsumers" value="4" />
        <property name="destination" ref="demoQueueOfReceiver" />
        <property name="messageListener" ref="demoAmqReceiver" />
        <property name="receiveTimeout" value="10000" />
        <property name="taskExecutor" ref="queueTaskPool" />
        <property name="cacheLevel" value="3" />
        <property name="sessionTransacted" value="true" />
    </bean>

    <bean id="demoQueueOfReceiver" class="org.apache.activemq.command.ActiveMQQueue">
        <constructor-arg>
            <value>queue.demo</value>
        </constructor-arg>
    </bean>

    <!-- 配置JMS模板（Queue），Spring提供的JMS工具类，它接收消息。 -->
    <bean id="queueTemplateOfReceiver" class="org.springframework.jms.core.JmsTemplate">
        <property name="connectionFactory" ref="connectionFactoryCache" />
        <!--<property name="defaultDestinationName" value="subject" />-->
        <!--<property name="defaultDestination" ref="demoQueueOfReceiver" />-->
        <property name="deliveryPersistent" value="true" />
        <property name="pubSubDomain" value="false" />
        <!-- false p2p,true topic -->
        <property name="sessionAcknowledgeMode" value="1" />
        <property name="explicitQosEnabled" value="true" />
        <property name="timeToLive" value="604800000" />
        <property name="receiveTimeout" value="10000" />
    </bean>

    <!-- Consumer style - 01 -->
    <bean id="demoAmqReceiver" class="com.aboutcoder.packease.framework.common.jms.PEAmqMessageListener">
        <property name="amqMessageProcessor" ref="demoAmqProcessor" />
    </bean>
    <bean id="demoAmqProcessor" class="com.aboutcoder.packease.framework.demo.receiver.DemoAmqProcessor"/>

    <!-- Consumer style - 02 -->
    <!--<bean id="consumerService" class="guo.examples.mq02.queue.ConsumerServiceImpl">-->
        <!--<property name="jmsTemplate" ref="queueTemplateOfReceiver"></property>-->
    <!--</bean>-->

</beans>
```

> spring-jms.xml 生产者

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.1.xsd">

    <!-- 配置JMS模板（Queue），Spring提供的JMS工具类，它发送消息。 -->
    <bean id="queueTemplateOfSender" class="org.springframework.jms.core.JmsTemplate">
        <property name="connectionFactory" ref="connectionFactoryCache" />
        <!--<property name="defaultDestinationName" value="subject" />-->
        <!--<property name="defaultDestination" ref="demoQueueOfSender" />-->
        <property name="deliveryPersistent" value="true" />
        <property name="pubSubDomain" value="false" />
        <!-- false p2p,true topic -->
        <property name="sessionAcknowledgeMode" value="1" />
        <property name="explicitQosEnabled" value="true" />
        <property name="timeToLive" value="604800000" />
        <property name="receiveTimeout" value="10000" />
    </bean>

    <bean id="demoQueueOfSender" class="org.apache.activemq.command.ActiveMQQueue">
        <constructor-arg>
            <value>queue.demo.sender</value>
        </constructor-arg>
    </bean>

    <!-- Producer style - 01 -->
    <bean id="demoAmqSender" class="com.aboutcoder.packease.framework.common.jms.PEAmqMessageSender">
        <property name="jmsTemplate" ref="queueTemplateOfSender"/>
    </bean>

</beans>
```

#### 二、接收Queue p2p消息,实现消息监听器(示例如下)

```java
public class DemoAmqProcessor implements PEIAmqMessageProcessor {
    private final static Logger logger = LoggerFactory.getLogger(DemoAmqProcessor.class);

    /**
     * Define a process function for business logic.
     *
     * @param jsonMessage
     */
    public void process(String jsonMessage) {
        logger.info("DemoAmqProcessor receives the msg from AMQ===>" + jsonMessage);
    }
}
```

#### 三、生产Queue p2p消息,实现消息发送器(示例如下)

> Service implementation

```java
@Service
public class DemoSenderServiceImpl implements IDemoSenderService {

    @Resource
    private Destination demoQueueOfSender;

    @Resource
    private PEAmqMessageSender peAmqMessageSender;

    /**
     * Send message to queue.
     */
    public void sendMessageToQueue() {
        // Style 01:
        // Besides using spring DI, we could also define with create ActiveMQQueue instance.
        // ActiveMQQueue demoQueueOfSender = new ActiveMQQueue("queue.demo.sender");

        // Style 02:
        // Using spring DI tech.
        peAmqMessageSender.sendMessage(demoQueueOfSender, "Testing messages.");
    }
}
```

> Service interface definition

```java
public interface IDemoSenderService {
    /**
     * Send message to queue.
     */
    void sendMessageToQueue();
}
```
