package com.gurps.instructionqueue.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;

import com.gurps.instructionqueue.domain.InstructionMessage;
import com.gurps.instructionqueue.domain.InstructionMessageImpl;
import com.gurps.instructionqueue.domain.InstructionPriority;
import com.gurps.instructionqueue.domain.exception.InvalidMessageException;

public class TestInstructionQueue {
	
	@After
	public void runAfterEveryTest() {
		InstructionQueueService queueService = new InstructionQueueServiceImpl();
		queueService.clear();
	}

	@Test
	/**
	 * Test to see if a valid message can be successfully enqueued
	 */
	public void testPutValidMessageOnQueue() {
		InstructionQueueService queueService = new InstructionQueueServiceImpl();
		InstructionMessage instructionMessage = new InstructionMessageImpl(10, 11,
			12, 13, 14);
		
		try{
			queueService.enQueue(instructionMessage);
		}catch(InvalidMessageException ime){
			fail("Exception should not have been thrown. The message was valid!");
		}
		assertEquals(1,queueService.getSize());
	}
	
	@Test
	/**
	 * Test to see if an exception is thrown when a message with an instruction
	 * type greater than the max permitted is enqueued
	 */
	public void testPutMessageOnQueueWithInstructionTypeOverflow() {
		InstructionQueueService queueService = new InstructionQueueServiceImpl();
		InstructionMessage instructionMessage = new InstructionMessageImpl(200, 11,
			12, 13, 14);
		
		try{
			queueService.enQueue(instructionMessage);
			fail("Exception should have been thrown. The instruction type was above the max allowed value!");
		}catch(InvalidMessageException ime){
			assertEquals(0, queueService.getSize());
		}
	}
	
	@Test	
	/**
	 * Test to see if an exception is thrown when a message with an instruction
	 * type less than the min permitted is enqueued
	 */
	public void testPutMessageOnQueueWithInstructionTypeUnderflow() {
		InstructionQueueService queueService = new InstructionQueueServiceImpl();
		InstructionMessage instructionMessage = new InstructionMessageImpl(0, 11,
			12, 13, 14);
		
		try{
			queueService.enQueue(instructionMessage);
			fail("Exception should have been thrown. The instruction type was below the min allowed value!");
		}catch(InvalidMessageException ime){
			assertEquals(0, queueService.getSize());
		}
	}
	
	@Test	
	/**
	 * Test to see if an exception is thrown when a message with product code
	 * below the min permitted is enqueued
	 */
	public void testPutMessageOnQueueWithProductCodeUnderflow() {
		InstructionQueueService queueService = new InstructionQueueServiceImpl();
		InstructionMessage instructionMessage = new InstructionMessageImpl(10, 0,
			12, 13, 14);
		
		try{
			queueService.enQueue(instructionMessage);
			fail("Exception should have been thrown. The product code below the min allowed value!");
		}catch(InvalidMessageException ime){
			assertEquals(0, queueService.getSize());
		}
	}
	
	@Test	
	/**
	 * Test to see if an exception is thrown when a message with quantity
	 * below the max permitted is enqueued
	 */
	public void testPutMessageOnQueueWithQuantityUnderflow() {
		InstructionQueueService queueService = new InstructionQueueServiceImpl();
		InstructionMessage instructionMessage = new InstructionMessageImpl(10, 11,
			0, 13, 14);
		
		try{
			queueService.enQueue(instructionMessage);
			fail("Exception should have been thrown. The quantity was below the min allowed value!");
		}catch(InvalidMessageException ime){
			assertEquals(0, queueService.getSize());
		}
	}
	
	
	@Test	
	/**
	 * Test to see if an exception is thrown when a message with unit of measure
	 * above the max permitted is enqueued
	 */
	public void testPutMessageOnQueueWithUnitOfMeasureOverflow() {
		InstructionQueueService queueService = new InstructionQueueServiceImpl();
		InstructionMessage instructionMessage = new InstructionMessageImpl(10, 11,
			12, 300, 14);
		
		try{
			queueService.enQueue(instructionMessage);
			fail("Exception should have been thrown. The unit of measure was above max allowed value!");
		}catch(InvalidMessageException ime){
			assertEquals(0, queueService.getSize());
		}
		
	}
	
	@Test	
	/**
	 * Test to see if an exception is thrown when a message with unit of measure
	 * below the min permitted is enqueued
	 */
	public void testPutMessageOnQueueWithUnitOfMeasureUnderflow() {
		InstructionQueueService queueService = new InstructionQueueServiceImpl();
		InstructionMessage instructionMessage = new InstructionMessageImpl(10, 11,
			12, -1, 14);
		
		try{
			queueService.enQueue(instructionMessage);
			fail("Exception should have been thrown. The unit of measure was below min allowed value!");
		}catch(InvalidMessageException ime){
			assertEquals(0, queueService.getSize());
		}
	}
	
	@Test	
	/**
	 * Test to see if an exception is thrown when a message with timestamp
	 * below the min permitted is enqueued
	 */
	public void testPutMessageOnQueueWithTimestampUnderflow() {
		InstructionQueueService queueService = new InstructionQueueServiceImpl();
		InstructionMessage instructionMessage = new InstructionMessageImpl(10, 11,
			12, 13, 0);
		
		try{
			queueService.enQueue(instructionMessage);
			fail("Exception should have been thrown. The timestamp was below min allowed value!");
		}catch(InvalidMessageException ime){
			assertEquals(0, queueService.getSize());
		}
		
	}
	
	@Test	
	/**
	 * Boundary test to ensure anything instruction type less
	 * than the lower boundary results in a unknown priority.
	 */
	public void testInstructionTypePriorityUnderflow() {
		
		InstructionMessage instructionMessage = new InstructionMessageImpl(0, 11,
			12, 13, 14);
		
		InstructionPriority priority = instructionMessage.getPriority();
		
		assertEquals(InstructionPriority.UNKNOWN, priority);
	}
	
	@Test	
	/**
	 * Boundary test to ensure an instruction type of 5
	 * results in a HIGH priority.
	 */
	public void testInstructionTypeHighPriority() {
		
		InstructionMessage instructionMessage = new InstructionMessageImpl(5, 11,
			12, 13, 14);
		
		InstructionPriority priority = instructionMessage.getPriority();
		
		assertEquals(InstructionPriority.HIGH, priority);
	}
	
	@Test	
	/**
	 * Boundary test to ensure an instruction type of 10 (upper boundary)
	 * results in a HIGH priority.
	 */
	public void testInstructionTypeHighPriorityMaxBoundary() {
		
		InstructionMessage instructionMessage = new InstructionMessageImpl(10, 11,
			12, 13, 14);
		
		InstructionPriority priority = instructionMessage.getPriority();
		
		assertEquals(InstructionPriority.HIGH, priority);
	}
	
	@Test
	/**
	 * Boundary test to ensure an instruction type of 11 
	 * results in a MEDIUM priority.
	 */
	public void testInstructionTypeMediumPriorityMinBoundary() {
		
		InstructionMessage instructionMessage = new InstructionMessageImpl(11, 11,
			12, 13, 14);
		
		InstructionPriority priority = instructionMessage.getPriority();
		
		assertEquals(InstructionPriority.MEDIUM, priority);
	}
	
	@Test
	/**
	 * Boundary test to ensure an instruction type of 15
	 * results in a MEDIUM priority.
	 */	
	public void testInstructionTypeMediumPriority() {
		
		InstructionMessage instructionMessage = new InstructionMessageImpl(15, 11,
			12, 13, 14);
		
		InstructionPriority priority = instructionMessage.getPriority();
		
		assertEquals(InstructionPriority.MEDIUM, priority);
	}
	
	@Test
	/**
	 * Boundary test to ensure an instruction type of 90 (upper boundary)
	 * results in a MEDIUM priority.
	 */	
	public void testInstructionTypeMediumPriorityMaxBoundary() {
		
		InstructionMessage instructionMessage = new InstructionMessageImpl(90, 11,
			12, 13, 14);
		
		InstructionPriority priority = instructionMessage.getPriority();
		
		assertEquals(InstructionPriority.MEDIUM, priority);
	}
	
	@Test
	/**
	 * Boundary test to ensure an instruction type of 91
	 * results in a LOW priority.
	 */	
	public void testInstructionTypeLowPriorityMinBoundary() {
		
		InstructionMessage instructionMessage = new InstructionMessageImpl(91, 11,
			12, 13, 14);
		
		InstructionPriority priority = instructionMessage.getPriority();
		
		assertEquals(InstructionPriority.LOW, priority);
	}
	
	@Test
	/**
	 * Boundary test to ensure an instruction type of 95
	 * results in a LOW priority.
	 */		
	public void testInstructionTypeLowPriority() {
		
		InstructionMessage instructionMessage = new InstructionMessageImpl(95, 11,
			12, 13, 14);
		
		InstructionPriority priority = instructionMessage.getPriority();
		
		assertEquals(InstructionPriority.LOW, priority);
	}
	
	@Test
	/**
	 * Boundary test to ensure an instruction type of 99
	 * results in a LOW priority.
	 */		
	public void testInstructionTypeLowPriorityMaxBoundary() {
		
		InstructionMessage instructionMessage = new InstructionMessageImpl(99, 11,
			12, 13, 14);
		
		InstructionPriority priority = instructionMessage.getPriority();
		
		assertEquals(InstructionPriority.LOW, priority);
	}
	
	@Test
	/**
	 * Boundary test to ensure an instruction type of 100
	 * results in a UNKNOWN priority.
	 */	
	public void testInstructionTypePriorityOverflow() {
		
		InstructionMessage instructionMessage = new InstructionMessageImpl(100, 11,
			12, 13, 14);
		
		InstructionPriority priority = instructionMessage.getPriority();
		
		assertEquals(InstructionPriority.UNKNOWN, priority);
	}
	
	@Test
	/**
	 * Test the getSize() method on the queue returns the correct number of messages
	 * when three valid messages are put onto the queue.
	 */
	public void testQueueSize(){
		
		InstructionQueueService queueService = new InstructionQueueServiceImpl();
		
		InstructionMessage instructionMessage1 = new InstructionMessageImpl(5, 11,
				12, 13, 14);
		InstructionMessage instructionMessage2 = new InstructionMessageImpl(6, 11,
				12, 13, 14);
		InstructionMessage instructionMessage3 = new InstructionMessageImpl(7, 11,
				12, 13, 14);
		
		
		try{
			queueService.enQueue(instructionMessage1);
			queueService.enQueue(instructionMessage2);
			queueService.enQueue(instructionMessage3);
		}catch(InvalidMessageException ime){
			fail("Exception should not have been thrown here. All three messages were valid");
		}
		
		assertEquals(3, queueService.getSize());
	}
	
	@Test
	/**
	 * Test the isEmpty() method to ensure True is returned for an empty queue
	 */
	public void testEmptyQueue(){
		InstructionQueueService queueService = new InstructionQueueServiceImpl();
		assertTrue(queueService.isEmpty());
	}
	
	@Test
	/**
	 * Test the isEmpty() method to ensure False is returned for an non-empty queue
	 */
	public void testNonEmptyQueue(){
		InstructionQueueService queueService = new InstructionQueueServiceImpl();
		
		InstructionMessage instructionMessage = new InstructionMessageImpl(5, 11,
				12, 13, 14);
		try{
			queueService.enQueue(instructionMessage);			
		}catch(InvalidMessageException ime){
			fail("Exception should not have been thrown here. The message was valid.");
		}
		
		assertFalse(queueService.isEmpty());
	}
	
	@Test
	/**
	 * Put three messages on the queue with Low, Medium and High priority
	 * and ensure the messages are dequeued with respect to their natural ordering.
	 */
	public void testMessagesAreDequeuedInPriorityOrder(){
		
		InstructionQueueService queueService = new InstructionQueueServiceImpl();
		
		
		InstructionMessage mediumPriorityMessage = new InstructionMessageImpl(15, 11,
				12, 13, 14);
		
		InstructionMessage highPriorityMessage = new InstructionMessageImpl(5, 11,
				12, 13, 14);
		
		InstructionMessage lowPriorityMessage = new InstructionMessageImpl(95, 11,
				12, 13, 14);
		
		try{
			queueService.enQueue(mediumPriorityMessage);
			queueService.enQueue(highPriorityMessage);	
			queueService.enQueue(lowPriorityMessage);	
		}catch(InvalidMessageException ime){
			fail("Exception should not have been thrown here. All three messages were valid.");
		}
		
		//now ensure that when the messages are dequeued, they come out in priority order.
		
		InstructionMessage msg1 = queueService.deQueue();
		InstructionMessage msg2 = queueService.deQueue();
		InstructionMessage msg3 = queueService.deQueue();
		
		assertEquals(InstructionPriority.HIGH, msg1.getPriority());
		assertEquals(InstructionPriority.MEDIUM, msg2.getPriority());
		assertEquals(InstructionPriority.LOW, msg3.getPriority());
	}
	
	@Test
	/**
	 * Put three messages on the queue with Low, Medium and High priority
	 * Test to see that when you inspect the queue, the top most element is the one
	 * with highest priority.
	 */
	public void testRetreivalOfTopMostMessageInQueue(){
		
		InstructionQueueService queueService = new InstructionQueueServiceImpl();
		
		
		InstructionMessage mediumPriorityMessage = new InstructionMessageImpl(15, 11,
				12, 13, 14);
		
		InstructionMessage highPriorityMessage = new InstructionMessageImpl(5, 11,
				12, 13, 14);
		
		InstructionMessage lowPriorityMessage = new InstructionMessageImpl(95, 11,
				12, 13, 14);
		
		try{
			queueService.enQueue(mediumPriorityMessage);
			queueService.enQueue(highPriorityMessage);
			queueService.enQueue(lowPriorityMessage);
		}catch(InvalidMessageException ime){
			fail("Exception should not have been thrown here. All three messages were valid.");
		}
		
		InstructionMessage topMostInstructionMesssage = queueService.deQueue();
		
		assertEquals(InstructionPriority.HIGH, topMostInstructionMesssage.getPriority());
		assertEquals(5, topMostInstructionMesssage.getInstructionType());
	}
}
