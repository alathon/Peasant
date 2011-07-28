package com.cphse.peasant;

import java.util.List;

import com.hazelcast.core.Hazelcast;

public final class Mob {
	private static final Mob ourMob = new Mob();
	
	public static Mob getMob() {
		return ourMob;
	}
	
	public void joinMob() {
		Hazelcast.init(null);
		final List<Integer> members = Hazelcast.getList("Members");
		members.add(getMyID());
		Hazelcast.getCluster().addMembershipListener(new MobMembershipHandler(this));
	}
	public void leftMob(final Integer peasantID) {
		final List<Integer> members = Hazelcast.getList("Members");
		members.remove(peasantID);
	}
	
	
	public boolean iAmMaster() {
		return getMasterID() == getMyID();
	}
	
	public int getMyID() {
		return Hazelcast.getCluster().getLocalMember().hashCode();
	}
	
	/**
	 * Gets the ID of the master peasant.
	 * 
	 * @return The ID of the master.
	 * @throws NoMasterException If there is currently no master of the mob.
	 */
	public long getMasterID() {
		final List<Integer> members = Hazelcast.getList("Members");
		if (members.isEmpty())
			throw new NoMasterException();
		return members.get(0);
	}
}
