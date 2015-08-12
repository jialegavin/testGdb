package com.njyb.gbdbas.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义锁
 * @author 贾红平
 *
 */
public class MyLock implements Lock{
	//注入异步实现类
	private AbstractQueuedSynchronizer sync;
	public MyLock() {
		sync=new LockQueuedSynchronizer();
	}

	/**
	 * 临界区加上锁
	 */
	@Override
	public void lock() {
		sync.acquire(1);
	}

	/**
	 * 试着获取锁 过程需要处理对应的异常
	 */
	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	/**
	 * 试着获取锁 如果有返回true 否则返回false
	 */
	@Override
	public boolean tryLock() {
		try {
			return sync.tryAcquireNanos(1, 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 试着获取锁 如果有返回true 否则返回false
	 */
	@Override
	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		return sync.tryAcquireNanos(1, TimeUnit.NANOSECONDS.convert(time, unit));
	}

	/**
	 * 释放锁
	 */
	@Override
	public void unlock() {
		sync.release(1);
	}

	 /**
	  * 锁产生条件
	  */
	@Override
	public Condition newCondition() {
		return sync.new ConditionObject();
	}

}
