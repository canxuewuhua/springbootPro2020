package com.example.demo.exercise.distribute_lock.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class ZookeeperDistrubuteLock {
    /**
     * 基于zookeeper实现分布式锁
     * zookeeper是一个为分布式应用提供一致性服务的开源组件，它内部是一个分层的文件系统目录树结构，规定同一个目录下只能有一个唯一文件名
     * 基于zookeeper实现分布式锁的步骤如下
     * （1）创建一个目录mylock （2）线程A想获取锁就在mylock目录下创建临时顺序节点
     * （3）获取mylock目录下所有的子节点，然后获取比自己小的兄弟节点，如果不存在，则说明线程顺序号最小，获取锁；
     * （4）线程B获取所有节点，判断自己不是最小节点，设置监听比自己次小的节点
     * （5）线程A处理完，删除自己的节点，线程B监听到变更事件，判断自己是不是最小的节点，如果是则获得锁。
     *
     * 这里推荐一个Apache的开源库Curator
     * 它是一个Zookeeper客户端，Curator提供的InterProcessMutex是分布式锁的实现，acquire方法用于获取锁，release方法用于释放锁。
     *
     * 两大特征
     * 文件系统数据结构   事件监听机制
     * ------------>四种类型节点<----------------
     *  持久化节点 临时节点 持久化顺序节点 临时顺序节点
     */

    @Autowired
    private CuratorFramework curatorFramework;

    /**
     * 它是一个Zookeeper客户端，Curator提供的InterProcessMutex是分布式锁的实现，acquire方法用于获取锁，release方法用于释放锁
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/deductstock")
    public Object reduceStock(Integer id) throws Exception {
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/"+id);

        try{
            lock.acquire();
            // 业务代码处理
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.release();
        }

        return "ok:" + "";
    }
}
