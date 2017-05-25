PE.utils.db
=============

* PEDbContextHolder

存放线程局部变量, 内容是Spring所配置的DB数据源名称(The key field)。

* PEDynamicDataSource

该类继承Spring的AbstractRoutingDataSource, 将数据源切换的点开放出来, 提供按需切换。该切换是请求线程级别的。

* PEDataSourceSwitcher

该类应用于数据库"一主一从"模式下的读写分离场景, 为动态或者按要求进行主从库切换提供了可能。
目前需要与Spring AOP结合使用, 需要新增的Aspect配置代码如下:

```xml
<!-- 定义事务管理器及其数据源引用 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="${数据源名, 可支持高级定义的Spring jdbc配置的targetDataSources}"/>
</bean>
<tx:advice id="txAdvice" transaction-manager="transactionManager">
    <tx:attributes>
        <tx:method name="get*" read-only="true" />
        <tx:method name="set*" read-only="true" />
        <tx:method name="query*" read-only="true" />
        <tx:method name="find*" read-only="true" />
        <tx:method name="load*" read-only="true" />
        <tx:method name="count*" read-only="true" />
        <tx:method name="save*" rollback-for="Exception" />
        <tx:method name="add*" rollback-for="Exception" />
        <tx:method name="update*" rollback-for="Exception" />
        <tx:method name="delete*" rollback-for="Exception" />
        <tx:method name="merge*" rollback-for="Exception" />
    </tx:attributes>
</tx:advice>

<!-- 定义读写分离开关bean及其属性 -->
<bean id="dataSourceSwitcher" class="PEDataSourceSwitcher">
    <property name="masterDB" value="${masterDbSourceName}"/>
    <property name="slaveDB" value="${slaveDbSourceName}"/>
</bean>

<!-- 配置读写分离的aop:config -->
<bean id="dataSourceSwitcher" class="PEDataSourceSwitcher" />
    <aop:config>
        <!-- 定义pointcut切入点需扫描的类文件 -->
        <aop:pointcut id="servicePointcut" expression="execution(* com.aboutcoder.packease.utils..*Service.*(..))" />
        <aop:advisor advice-ref="txAdvice" pointcut-ref="servicePointcut" order="9" />
        <aop:aspect ref="dataSourceSwitcher">
            <!-- 从库切入 -->
            <aop:pointcut id="toSlave" expression="@annotation(PESlaveDB)"  />
            <aop:before method="toSlave" pointcut-ref="toSlave" />
            <aop:after method="toMaster" pointcut-ref="toSlave" />
            <aop:after-throwing method="toMaster" pointcut-ref="toSlave" throwing="ex" />
            <!-- 主库切入 -->
            <aop:pointcut id="toMaster" expression="@annotation(PEMasterDB)"  />
            <aop:before method="toMaster" pointcut-ref="toMaster" />
        </aop:aspect>
    </aop:config>
</bean>
```

切入点定义见: `PE.utils.annotation.db`。

