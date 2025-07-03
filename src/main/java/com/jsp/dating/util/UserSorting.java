package com.jsp.dating.util;

import java.util.Comparator;

import com.jsp.dating.dto.MatchingUser;

public class UserSorting implements Comparator<MatchingUser> {

	@Override
	public int compare(MatchingUser o1, MatchingUser o2) {
		if(o1.getAgeDiff() < o2.getAgeDiff())
			return -1;
		else if(o1.getAgeDiff() > o2.getAgeDiff())
			return 1;
		if(o1.getMatchInterestCount() < o2.getMatchInterestCount())
			return -1;
		return 1;
	}

}
