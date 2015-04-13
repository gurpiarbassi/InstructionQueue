package com.gurps.instructionqueue.domain;

/**
 * 
 * @author gurps bassi
 * Interface representation of an InstructionMessage
 *
 */
public interface InstructionMessage {

	int getInstructionType();

	int getProductCode();

	int getQuantity();

	int getUnitOfMeasure();

	int getTimestamp();
	
	InstructionPriority getPriority();
}