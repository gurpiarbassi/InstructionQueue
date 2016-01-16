package com.gurps.instructionqueue.domain;

import static com.gurps.instructionqueue.domain.InstructionPriority.HIGH;
import static com.gurps.instructionqueue.domain.InstructionPriority.LOW;
import static com.gurps.instructionqueue.domain.InstructionPriority.MEDIUM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;

import com.gurps.instructionqueue.domain.exception.InvalidMessageException;

public class InstructionQueueImplTest {

	private InstructionQueueImpl instructionQueue = new InstructionQueueImpl();

	@After
	public void clearQueue() {
		instructionQueue.clear();
	}

	@Test
	public void testPutValidMessageOnQueue() {
		InstructionMessage instructionMessage = new InstructionMessageImpl(10, 11, 12, 13, 14);
		try {
			instructionQueue.enQueue(instructionMessage);
		} catch (InvalidMessageException ime) {
			fail("Exception should not have been thrown. The message was valid!");
		}
		assertEquals(1, instructionQueue.getSize());
	}

	@Test(expected = InvalidMessageException.class)
	public void testPutMessageOnQueueWithInstructionTypeOverflow() throws InvalidMessageException {
		InstructionMessage instructionMessage = new InstructionMessageImpl(200, 11, 12, 13, 14);
		try {
			instructionQueue.enQueue(instructionMessage);
			fail("Exception should have been thrown. The instruction type was above the max allowed value!");
		} finally {
			assertEquals(0, instructionQueue.getSize());
		}
	}

	@Test(expected = InvalidMessageException.class)
	public void testPutMessageOnQueueWithInstructionTypeUnderflow() throws InvalidMessageException {
		InstructionMessage instructionMessage = new InstructionMessageImpl(0, 11, 12, 13, 14);
		try {
			instructionQueue.enQueue(instructionMessage);
			fail("Exception should have been thrown. The instruction type was below the min allowed value!");
		} finally {
			assertEquals(0, instructionQueue.getSize());
		}
	}

	@Test(expected = InvalidMessageException.class)
	public void testPutMessageOnQueueWithProductCodeUnderflow() throws InvalidMessageException {
		InstructionMessage instructionMessage = new InstructionMessageImpl(10, 0, 12, 13, 14);
		try {
			instructionQueue.enQueue(instructionMessage);
			fail("Exception should have been thrown. The product code below the min allowed value!");
		} finally {
			assertEquals(0, instructionQueue.getSize());
		}
	}

	@Test(expected = InvalidMessageException.class)
	public void testPutMessageOnQueueWithQuantityUnderflow() throws InvalidMessageException {
		InstructionMessage instructionMessage = new InstructionMessageImpl(10, 11, 0, 13, 14);
		try {
			instructionQueue.enQueue(instructionMessage);
			fail("Exception should have been thrown. The quantity was below the min allowed value!");
		} finally {
			assertEquals(0, instructionQueue.getSize());
		}
	}

	@Test(expected = InvalidMessageException.class)
	public void testPutMessageOnQueueWithUnitOfMeasureOverflow() throws InvalidMessageException {
		InstructionMessage instructionMessage = new InstructionMessageImpl(10, 11, 12, 300, 14);
		try {
			instructionQueue.enQueue(instructionMessage);
		} finally {
			assertEquals(0, instructionQueue.getSize());
		}

	}

	@Test(expected = InvalidMessageException.class)
	public void testPutMessageOnQueueWithUnitOfMeasureUnderflow() throws InvalidMessageException {
		InstructionMessage instructionMessage = new InstructionMessageImpl(10, 11, 12, -1, 14);
		try {
			instructionQueue.enQueue(instructionMessage);
		} finally {
			assertEquals(0, instructionQueue.getSize());
		}
	}

	public void testPutMessageOnQueueWithTimestampUnderflow() throws InvalidMessageException {
		InstructionMessage instructionMessage = new InstructionMessageImpl(10, 11, 12, 13, 0);
		try {
			instructionQueue.enQueue(instructionMessage);
		} finally {
			assertEquals(0, instructionQueue.getSize());
		}
	}

	@Test
	public void testQueueSize() {

		InstructionMessage instructionMessage1 = new InstructionMessageImpl(5, 11, 12, 13, 14);
		InstructionMessage instructionMessage2 = new InstructionMessageImpl(6, 11, 12, 13, 14);
		InstructionMessage instructionMessage3 = new InstructionMessageImpl(7, 11, 12, 13, 14);

		try {
			instructionQueue.enQueue(instructionMessage1);
			instructionQueue.enQueue(instructionMessage2);
			instructionQueue.enQueue(instructionMessage3);
		} catch (InvalidMessageException ime) {
			fail("Exception should not have been thrown here. All three messages were valid");
		}

		assertEquals(3, instructionQueue.getSize());
	}

	@Test
	public void testEmptyQueue() {
		InstructionQueueImpl instructionQueue = new InstructionQueueImpl();
		assertTrue(instructionQueue.isEmpty());
	}

	@Test
	public void testNonEmptyQueue() {
		InstructionMessage instructionMessage = new InstructionMessageImpl(5, 11, 12, 13, 14);
		try {
			instructionQueue.enQueue(instructionMessage);
		} catch (InvalidMessageException ime) {
			fail("Exception should not have been thrown here. The message was valid.");
		}

		assertFalse(instructionQueue.isEmpty());
	}

	@Test
	public void testMessagesAreDequeuedInPriorityOrder() {

		InstructionMessage mediumPriorityMessage = new InstructionMessageImpl(15, 11, 12, 13, 14);

		InstructionMessage highPriorityMessage = new InstructionMessageImpl(5, 11, 12, 13, 14);

		InstructionMessage lowPriorityMessage = new InstructionMessageImpl(95, 11, 12, 13, 14);

		try {
			instructionQueue.enQueue(mediumPriorityMessage);
			instructionQueue.enQueue(highPriorityMessage);
			instructionQueue.enQueue(lowPriorityMessage);
		} catch (InvalidMessageException ime) {
			fail("Exception should not have been thrown here. All three messages were valid.");
		}

		InstructionMessage msg1 = instructionQueue.deQueue();
		InstructionMessage msg2 = instructionQueue.deQueue();
		InstructionMessage msg3 = instructionQueue.deQueue();

		assertEquals(HIGH, msg1.getPriority());
		assertEquals(MEDIUM, msg2.getPriority());
		assertEquals(LOW, msg3.getPriority());
	}

	@Test
	/**
	 * Put three messages on the queue with Low, Medium and High priority
	 * Test to see that when you inspect the queue, the top most element is the one
	 * with highest priority.
	 */
	public void testRetreivalOfTopMostMessageInQueue() {

		InstructionMessage mediumPriorityMessage = new InstructionMessageImpl(15, 11, 12, 13, 14);

		InstructionMessage highPriorityMessage = new InstructionMessageImpl(5, 11, 12, 13, 14);

		InstructionMessage lowPriorityMessage = new InstructionMessageImpl(95, 11, 12, 13, 14);

		try {
			instructionQueue.enQueue(mediumPriorityMessage);
			instructionQueue.enQueue(highPriorityMessage);
			instructionQueue.enQueue(lowPriorityMessage);
		} catch (InvalidMessageException ime) {
			fail("Exception should not have been thrown here. All three messages were valid.");
		}

		InstructionMessage topMostInstructionMesssage = instructionQueue.deQueue();

		assertEquals(HIGH, topMostInstructionMesssage.getPriority());
		assertEquals(5, topMostInstructionMesssage.getInstructionType());
	}
}
