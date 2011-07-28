package com.cphse.peasant;

import com.cphse.queue.Order;
import com.cphse.queue.OrderSubmitter;
import com.cphse.queue.RunningOrderTracker;
import com.hazelcast.core.MembershipEvent;
import com.hazelcast.core.MembershipListener;

public final class MobMembershipHandler implements MembershipListener {
	private final Mob 					ourMob;
	private final RunningOrderTracker 	tracker = new RunningOrderTracker();
	
	public MobMembershipHandler(final Mob ourMob) {
		this.ourMob = ourMob;
	}

	/* (non-Javadoc)
	 * @see com.hazelcast.core.MembershipListener#memberAdded(com.hazelcast.core.MembershipEvent)
	 */
	@Override
	public void memberAdded(MembershipEvent arg0) {
		
	}

	/* (non-Javadoc)
	 * @see com.hazelcast.core.MembershipListener#memberRemoved(com.hazelcast.core.MembershipEvent)
	 */
	@Override
	public void memberRemoved(MembershipEvent arg0) {
		final int peasantID = arg0.getMember().hashCode();
		ourMob.leftMob(peasantID);
		if (ourMob.iAmMaster()) {
			for (final Order order : tracker.recoverPeasant(peasantID)) {
				OrderSubmitter.submitOrder(order);
			}
		}
	}

}
