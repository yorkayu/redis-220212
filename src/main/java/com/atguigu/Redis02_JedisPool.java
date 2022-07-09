package com.atguigu;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Redis02_JedisPool {
    //定义连接池
    public static JedisPool poll = null;

    //获取连接&连接池的方法
    public static Jedis getJedis(){
        //判断连接池是否为空，如果是空的话则创建连接池
        if (poll==null){
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(10); //最大可用连接数
            jedisPoolConfig.setMaxIdle(5); //最大闲置连接数
            jedisPoolConfig.setMinIdle(5); //最小闲置连接数
            jedisPoolConfig.setBlockWhenExhausted(true); //连接耗尽是否等待
            jedisPoolConfig.setMaxWaitMillis(2000); //等待时间
            jedisPoolConfig.setTestOnBorrow(true); //取连接的时候进行一下测试 ping pong

            poll = new JedisPool(jedisPoolConfig,"hadoop102");
        }
        //获取连接
        return poll.getResource();
    }

    public static void main(String[] args) {
        //通过连接池获取连接
        Jedis jedis = getJedis();
        System.out.println(jedis.ping());
        //关闭连接
        jedis.close();
        System.out.println("helloworld");

    }
}
