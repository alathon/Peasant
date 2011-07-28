package com.cphse.peasant;

import java.util.List;

import com.hazelcast.core.Hazelcast;

public final class Mob {
	private static final Mob ourMob = new Mob();
	
	public static Mob getMob() {
		return ourMob;
	}
	
	private boolean iAmMaster = false;
	
	public void joinMob() {
		Hazelcast.init(null);
		List<Integer> members = Hazelcast.getList("Members");
		members.add( myID() );
		if ( members.get( 0 ) == myID() ) {
			iAmMaster = true;
			Hazelcast.getCluster().addMembershipListener(new MobMembershipHandler(this));
		}
	}
	
	public boolean iAmMaster() {
		return iAmMaster;
	}
	
	public int myID() {
		return Hazelcast.getCluster().getLocalMember().hashCode();
	}
}
