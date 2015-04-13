package com.gurps.instructionqueue.domain;

import java.util.Comparator;

public class InstructionMessageComparator implements Comparator<InstructionMessage>{
	    
	public int compare(InstructionMessage msg1, InstructionMessage msg2) {
		
	        return msg1.getPriority().getPriority() - msg2.getPriority().getPriority();
	    }
}
