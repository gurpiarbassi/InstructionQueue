package com.gurps.instructionqueue.service;

import java.util.PriorityQueue;
import java.util.Queue;

import com.gurps.instructionqueue.domain.InstructionMessage;
import com.gurps.instructionqueue.domain.InstructionMessageComparator;
import com.gurps.instructionqueue.domain.exception.InvalidMessageException;

public class InstructionQueueServiceImpl implements InstructionQueueService{

	/**
	 * Single Shared instance of the queue.
	 * 
	 * TODO Will need to take thread safety considerations into account as a separate exercise.
	 */
	private static Queue<InstructionMessage> instructionQueue = new PriorityQueue<InstructionMessage>(11, new InstructionMessageComparator());
	
	public InstructionQueueServiceImpl(){
		super();
	}
	
	@Override
	public void enQueue(final InstructionMessage msg) throws InvalidMessageException {
		this.validateMessage(msg);
		instructionQueue.add(msg);
	}

	@Override
	public InstructionMessage deQueue() {
		return instructionQueue.poll();
	}

	@Override
	public int getSize() {
		return instructionQueue.size();
	}

	@Override
	public boolean isEmpty() {
		return instructionQueue.isEmpty();
	}

	@Override
	public InstructionMessage peek() {
		return instructionQueue.peek();
	}
	
	
	
	@Override
	public void clear() {
		instructionQueue.clear();		
	}

	protected void validateMessage(final InstructionMessage message) throws InvalidMessageException{
		
		String error = null;
		
		if(message.getInstructionType() <= 0 || message.getInstructionType() >= 100){
			error = "Invalid instruction type detected";
		}
		else if(message.getProductCode() <= 0){
			error = "Invalid product code detected";
		}
		else if(message.getQuantity() <= 0){
			error = "Invalid quantity detected";
		}
		else if(message.getUnitOfMeasure() < 0 || message.getUnitOfMeasure() >= 256){
			error = "Invalid unit of measure detected";
		}
		else if(message.getTimestamp() <= 0){
			error = "Invalid timestamp detected";
		}
		
		if(error != null){
			throw new InvalidMessageException(message, error);
		}
	}
}
