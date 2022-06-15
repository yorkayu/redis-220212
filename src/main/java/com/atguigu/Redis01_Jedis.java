package com.atguigu;

import redis.clients.jedis.Jedis;

public class Redis01_Jedis {
    public static void main(String[] args) throws InterruptedException {
        //1.获取redis连接
        Jedis jedis = new Jedis("hadoop102", 6379);

//        System.out.println(jedis.ping());
        //写数据
//        jedis.set("220212", "最优秀的班级");

        //读数据
//        String s = jedis.get("220212");
//        System.out.println(s);

        //设置过期时间
        jedis.expire("220212", 20);

        //定义一个标志位，用来控制循环是否结束
        boolean isRunning = true;
        while (isRunning){
            Long ttl = jedis.ttl("220212");
            System.out.println("存活时间："+ttl);
            if (ttl==-2){
                isRunning = false;
            }
            Thread.sleep(1000);
        }

        //关闭redis连接
        jedis.close();
    }
}
