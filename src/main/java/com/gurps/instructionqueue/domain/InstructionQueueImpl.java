package com.gurps.instructionqueue.domain;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import com.gurps.instructionqueue.domain.exception.InvalidMessageException;

public class InstructionQueueImpl implements InstructionQueue{

	private static Queue<InstructionMessage> instructionQueue = new PriorityBlockingQueue<InstructionMessage>(11, new InstructionMessageComparator());
	
	public InstructionQueueImpl(){
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
