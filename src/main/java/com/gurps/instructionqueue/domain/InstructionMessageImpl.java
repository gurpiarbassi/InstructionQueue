package com.gurps.instructionqueue.domain;

import static com.gurps.instructionqueue.domain.InstructionPriority.HIGH;
import static com.gurps.instructionqueue.domain.InstructionPriority.LOW;
import static com.gurps.instructionqueue.domain.InstructionPriority.MEDIUM;
import static com.gurps.instructionqueue.domain.InstructionPriority.UNKNOWN;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

/**
 * Concrete implementation of InstructionMessage
 * 
 * @author gurps
 *
 */
public class InstructionMessageImpl implements InstructionMessage {

	private final int instructionType;
	private final int productCode;
	private final int quantity;
	private final int unitOfMeasure;
	private final int timestamp;

	public InstructionMessageImpl(int instructionType, int productCode, int quantity, int unitOfMeasure, int timestamp) {
		this.instructionType = instructionType;
		this.productCode = productCode;
		this.quantity = quantity;
		this.unitOfMeasure = unitOfMeasure;
		this.timestamp = timestamp;
	}
	
	@Override
	public int getInstructionType() {
		return instructionType;
	}

	@Override
	public int getProductCode() {
		return productCode;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public int getUnitOfMeasure() {
		return unitOfMeasure;
	}

	@Override
	public int getTimestamp() {
		return timestamp;
	}

	@Override
	public InstructionPriority getPriority() {

		if (getInstructionType() > 0 && getInstructionType() <= 10) {
			return HIGH;
		}
		if (getInstructionType() > 10 && getInstructionType() <= 90) {
			return MEDIUM;
		} else if (getInstructionType() > 90 && getInstructionType() < 100) {
			return LOW;
		}
		return UNKNOWN;
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return reflectionToString(this);
	}
}
