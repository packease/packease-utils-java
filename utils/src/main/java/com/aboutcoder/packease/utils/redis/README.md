PE.utils.redis
=============

* PEJedisClient: 对jedis的客户端封装

* PEJedisDataSource: 对jedis连接池的封装

* PEJedisListVo: 用于序列化/反序列化list的vo介质

* PEJedisMapVo: 用于序列化/反序列化map的vo介质

##### 1.使用示例:
```java
String key1 = "test-key-01";
Demo ro1 = new Demo();
ro1.setStart(123);
peJedisClient.setSerializableValue(key1, ro1, 600);
Demo vo1 = peJedisClient.getSerializableValue(key1, Demo.class);

String key2 = "test-list-01";
List<Demo> ro2 = new ArrayList<Demo>();
Demo ro2Item1 = new Demo();
ro2Item1.setStart(456);
Demo ro2Item2 = new Demo();
ro2Item2.setStart(789);
ro2.add(ro2Item1);
ro2.add(ro2Item2);
peJedisClient.setSerializableList(key2, ro2, 600);
List<Demo> vo2 = peJedisClient.getSerializableList(key2);

String key3 = "test-map-01";
Map<String, Demo> ro3 = new HashMap<String, Demo>();
Demo item1 = new Demo();
item1.setStart(1);
Demo item2 = new Demo();
item2.setStart(2);
item2.setLimit(10);
ro3.put("item1", item1);
ro3.put("item2", item2);
peJedisClient.setSerializableMap(key3, ro3, 600);
Map vo3 = peJedisClient.getSerializableMap(key3);
```

##### 2.集成到Spring配置示例
```xml
<bean id="jedisConfig" class="redis.clients.jedis.JedisPoolConfig">
	<property name="maxTotal" value="100" />
	<property name="maxIdle" value="16" />
	<property name="minIdle" value="5" />
	<property name="testOnBorrow" value="true" />
	<property name="maxWaitMillis" value="2000" />
</bean>

<bean id="shardedJedisPool" class="redis.clients.jedis.ShardedJedisPool" scope="singleton">
	<constructor-arg index="0" ref="jedisConfig" />
	<constructor-arg index="1">
		<list>
			<bean class="redis.clients.jedis.JedisShardInfo">
				<constructor-arg index="0" value="${host}" />
				<constructor-arg index="1" value="${port}" />
				<constructor-arg index="2" value="${timeout}" />
			</bean>
		</list>
	</constructor-arg>
</bean>

<bean name="peJedisClient" class="com.aboutcoder.packease.utils.redis.PEJedisClient"/>
```