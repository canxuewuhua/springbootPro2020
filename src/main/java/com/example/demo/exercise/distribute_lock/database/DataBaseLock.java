package com.example.demo.exercise.distribute_lock.database;

public class DataBaseLock {

    /**
     *
     *
     * for update是一种行级锁，又叫排它锁，一旦用户对某个行施加了行级加锁，则该用户可以查询也可以更新被加锁的数据行，
     * 其它用户只能查询但不能更新被加锁的数据行．如果其它用户想更新该表中的数据行，则也必须对该表施加行级锁．即使多个用户对一个表均使用了共享更新，
     * 但也不允许两个事务同时对一个表进行更新，真正对表进行更新时，是以独占方式锁表，一直到提交或复原该事务为止。行锁永远是独占方式锁。
     *
     *
     * select … for update 语句是我们经常使用手工加锁语句。在数据库中执行select … for update ,大家会发现会对数据库中的表或某些行数据进行锁表，
     * 在mysql中，如果查询条件带有主键，会锁行数据，如果没有，会锁表。
     *
     * 例1: (明确指定主键，并且数据真实存在，row lock)
     * SELECT * FROM user WHERE id=3 FOR UPDATE;
     * SELECT * FROM user WHERE id=3 and name='Tom' FOR UPDATE;
     *
     * 例2: (明确指定主键，但数据不存在，无lock)
     * SELECT * FROM user WHERE id=0 FOR UPDATE;
     *
     * 例3: (主键不明确，table lock)
     * SELECT * FROM user WHERE id<>3 FOR UPDATE;
     * SELECT * FROM user WHERE id LIKE '%3%' FOR UPDATE;
     *
     * 例4: (无主键，table lock)
     * SELECT * FROM user WHERE name='Tom' FOR UPDATE;
     *
     * 什么时候需要使用for update？
     * 借助for update语句，我们可以在应用程序的层面手工实现数据加锁保护操作。就是那些需要业务层面数据独占时，可以考虑使用for update。
     * 场景上，比如火车票订票，在屏幕上显示有票，而真正进行出票时，需要重新确定一下这个数据没有被其他客户端修改。所以，在这个确认过程中，可以使用for update。
     *
     * for update悲观锁
     * 悲观锁：总是假设最坏的情况，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁，这样别人想拿这个数据就会阻塞直到它解锁。
     *
     * 乐观锁：顾名思义，就是很乐观，每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据，
     * 可以使用版本号等机制。乐观锁适用于多读的应用类型，这样可以提高吞吐量，
     */

    // 对比：db操作性能差，并且有锁表的风险；非阻塞，操作失败后，需要轮询，占用cpu资源；长时间不commit或者长时间轮询，可能会占用较多连接资源
    // redis 锁删除失败 过期时间不好控制；非阻塞，操作失败后，需要轮询，占用cpu资源
    // zk锁 性能不如redis实现，主要原因是写操作（获取锁释放锁）都需要在Leader节点上执行，然后同步到follower

    // zk锁 有较好的性能和可靠性
}
