package com.cphse.peasant;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.cphse.mailman.connection.MailConnectionDetails;
import com.cphse.orders.MailAccountOrder;
import com.cphse.orders.MailOrderProcessor;
import com.cphse.queue.OrderSubmitter;

public final class Peasant {
	public static void testMailOrder()  throws FileNotFoundException, IOException {
		MailOrderProcessor processor = new MailOrderProcessor(5);
		processor.start();
		MailAccountOrder order = new MailAccountOrder(new MailConnectionDetails("./testAccount.props"));
		OrderSubmitter.submitOrder(order);
	}
	public static void main(String[] args){
		Mob.getMob().joinMob();
	}
}
