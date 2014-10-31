package com.powdermonkey.boggle;

public abstract class AbstractDictionary implements IDictionary {

	protected int lookupcount;
	protected int comparecount;
	protected int immediatereject;
	

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Dictionary stats: ");
		sb.append(comparecount);
		sb.append(" ");
		sb.append(lookupcount);
		sb.append(" ");
		sb.append(comparecount / lookupcount);
		sb.append(" ");
		sb.append(immediatereject);
		return sb.toString();
	}



	public void resetStats() {
		lookupcount = 0;
		comparecount = 0;
		immediatereject = 0;
	}
	
}
