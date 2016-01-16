package com.gurps.instructionqueue.domain;

import static com.gurps.instructionqueue.domain.InstructionPriority.HIGH;
import static com.gurps.instructionqueue.domain.InstructionPriority.LOW;
import static com.gurps.instructionqueue.domain.InstructionPriority.MEDIUM;
import static com.gurps.instructionqueue.domain.InstructionPriority.UNKNOWN;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InstructionMessageImplTest {

	@Test
	public void testInstructionTypeHighPriority() {

		InstructionMessage instructionMessage = new InstructionMessageImpl(5, 11, 12, 13, 14);

		InstructionPriority priority = instructionMessage.getPriority();

		assertEquals(HIGH, priority);
	}

	@Test
	public void testInstructionTypeHighPriorityMaxBoundary() {

		InstructionMessage instructionMessage = new InstructionMessageImpl(10, 11, 12, 13, 14);

		InstructionPriority priority = instructionMessage.getPriority();

		assertEquals(HIGH, priority);
	}

	@Test
	public void testInstructionTypeMediumPriorityMinBoundary() {

		InstructionMessage instructionMessage = new InstructionMessageImpl(11, 11, 12, 13, 14);

		InstructionPriority priority = instructionMessage.getPriority();

		assertEquals(MEDIUM, priority);
	}

	@Test
	public void testInstructionTypeMediumPriority() {

		InstructionMessage instructionMessage = new InstructionMessageImpl(15, 11, 12, 13, 14);

		InstructionPriority priority = instructionMessage.getPriority();

		assertEquals(MEDIUM, priority);
	}

	@Test
	public void testInstructionTypeMediumPriorityMaxBoundary() {

		InstructionMessage instructionMessage = new InstructionMessageImpl(90, 11, 12, 13, 14);

		InstructionPriority priority = instructionMessage.getPriority();

		assertEquals(MEDIUM, priority);
	}

	@Test
	public void testInstructionTypeLowPriorityMinBoundary() {

		InstructionMessage instructionMessage = new InstructionMessageImpl(91, 11, 12, 13, 14);

		InstructionPriority priority = instructionMessage.getPriority();

		assertEquals(LOW, priority);
	}

	@Test
	public void testInstructionTypeLowPriority() {

		InstructionMessage instructionMessage = new InstructionMessageImpl(95, 11, 12, 13, 14);

		InstructionPriority priority = instructionMessage.getPriority();

		assertEquals(InstructionPriority.LOW, priority);
	}

	@Test
	public void testInstructionTypeLowPriorityMaxBoundary() {

		InstructionMessage instructionMessage = new InstructionMessageImpl(99, 11, 12, 13, 14);

		InstructionPriority priority = instructionMessage.getPriority();

		assertEquals(LOW, priority);
	}

	@Test
	public void testInstructionTypePriorityOverflow() {

		InstructionMessage instructionMessage = new InstructionMessageImpl(100, 11, 12, 13, 14);

		InstructionPriority priority = instructionMessage.getPriority();

		assertEquals(UNKNOWN, priority);
	}
	
	@Test
	public void testInstructionTypePriorityUnknown() {

		InstructionMessage instructionMessage = new InstructionMessageImpl(0, 11, 12, 13, 14);

		InstructionPriority priority = instructionMessage.getPriority();

		assertEquals(UNKNOWN, priority);
	}
}
