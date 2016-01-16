package com.gurps.instructionqueue.domain;

public enum InstructionPriority {

	HIGH(1),
	MEDIUM(2),
	LOW(3),
	UNKNOWN(4);
	
	private final int priority;
	
	private InstructionPriority(int priority){
		this.priority = priority;
	}
	
	public int getPriority(){
		return this.priority;
	}
}
