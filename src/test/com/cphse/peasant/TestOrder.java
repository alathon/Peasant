package test.com.cphse.peasant;

import java.util.concurrent.TimeUnit;

import com.cphse.queue.Order;
import com.cphse.queue.OrderType;
import com.cphse.time.TimeDelay;

public final class TestOrder extends Order {
	private static final long serialVersionUID = -3763551499867521310L;
	public String data;

	@Override
	public TimeDelay getNextExecutionDelay() {
		return new TimeDelay(0, TimeUnit.SECONDS);
	}

	@Override
	public OrderType getOrderType() {
		return OrderType.TEST;
	}
}
