package test.com.cphse.peasant;

import com.cphse.peasant.Mob;
import com.cphse.queue.Task;

public final class TestTask extends Task<TestOrder> {
	public TestTask(final TestOrder order) {
		super(order);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println(Mob.getMob().getMyID() + " processed Order " + order.getOrderID() + " with data " + order.data);
	}
}
