package test.com.cphse.peasant;

import com.cphse.queue.OrderProcessor;
import com.cphse.queue.OrderType;

public final class TestOrderProcessor extends OrderProcessor<TestOrder> {

	public TestOrderProcessor() {
		super(OrderType.TEST, 1);
	}
	
	/* (non-Javadoc)
	 * @see com.cphse.queue.OrderProcessor#createTask(com.cphse.queue.Order)
	 */
	@Override
	protected TestTask createTask(final TestOrder order) {
		return new TestTask(order);
	}
}
