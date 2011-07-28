package test.com.cphse.peasant;

import com.cphse.peasant.Mob;
import com.cphse.queue.OrderSubmitter;

public final class TestPeasant {
	public static void main(String[] args) {
		System.setProperty("hazelcast.config", "./test-hazelcast.xml");
		Mob.getMob().joinMob();
		if (Mob.getMob().iAmMaster()) {
			System.out.println("I am master!!!");
		}
		TestOrderProcessor processor = new TestOrderProcessor();
		processor.start();
		for (int i = 0; i < 1000; i++) {
			TestOrder order = new TestOrder();
			order.data = "What the fuck";
			OrderSubmitter.submitOrder(order);
		}
	}
}
