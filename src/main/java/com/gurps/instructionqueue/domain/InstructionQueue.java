package com.gurps.instructionqueue.domain;

import com.gurps.instructionqueue.domain.exception.InvalidMessageException;

public interface InstructionQueue {

	/**
	 * Puts a message onto the queue
	 * @param msg the InstructionMessage instance to put onto the queue
	 * @throws InvalidMessageException if the message contains bad data
	 */
	void enQueue(InstructionMessage msg)throws InvalidMessageException;
	
	/**
	 * Takes a message off the queue and returns it
	 * @return the InstructionMessage that is taken off the queue or null if the queue is empty
	 */
	InstructionMessage deQueue();
	
	/**
	 * Returns the number of messages currently on the queue
	 * @return number of messages on the queue
	 */
	int getSize();
	
	/**
	 * Returns true if the queue is empty, false otherwise.
	 * @return
	 */
	boolean isEmpty();
	
	
	/**
	 * Finds the message at the front of the queue and returns it.
	 * It does not remove the message from the queue
	 * @return the InstructionMessage at the front of the queue.
	 */
	InstructionMessage peek();

	/**
	 * Clears all messages on the queue
	 */
	void clear();
}
