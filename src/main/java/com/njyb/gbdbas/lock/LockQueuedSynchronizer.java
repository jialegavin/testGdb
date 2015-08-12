package com.njyb.gbdbas.lock;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
/**
 * 重写异步的各种子方法
 * @author 贾红平
 *
 */
public class LockQueuedSynchronizer extends AbstractQueuedSynchronizer {
	private static final long serialVersionUID = 1L;
	//声明原子量 来实现异步
	private AtomicInteger state;
	public LockQueuedSynchronizer() {
		state=new AtomicInteger(0);
	}
	
	 /**
	  * 类似锁的功能
	  */
	@Override
	protected boolean tryAcquire(int arg) {
		return state.compareAndSet(0, 1);
	}

	 /**
	  * 类似释放锁的功能
	  */
	@Override
	protected boolean tryRelease(int arg) {
		return state.compareAndSet(1, 0);
	}
}
