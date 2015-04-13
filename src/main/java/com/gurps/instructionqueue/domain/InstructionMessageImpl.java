package com.gurps.instructionqueue.domain;

/**
 * Concrete implementation of IInstructionMessage interface.s
 * @author gurps
 *
 */
public class InstructionMessageImpl implements InstructionMessage {

	private int instructionType;
	private int productCode;
	private int quantity;
	private int unitOfMeasure;
	private int timestamp;
	
	public InstructionMessageImpl(int instructionType, int productCode,
			int quantity, int unitOfMeasure, int timestamp) {
		super();
		this.instructionType = instructionType;
		this.productCode = productCode;
		this.quantity = quantity;
		this.unitOfMeasure = unitOfMeasure;
		this.timestamp = timestamp;
	}

	/* (non-Javadoc)
	 * @see uk.travisperkins.assessment.domain.IInstructionMessage#getInstructionType()
	 */
	@Override
	public int getInstructionType() {
		return instructionType;
	}

	/* (non-Javadoc)
	 * @see uk.travisperkins.assessment.domain.IInstructionMessage#getProductCode()
	 */
	@Override
	public int getProductCode() {
		return productCode;
	}

	/* (non-Javadoc)
	 * @see uk.travisperkins.assessment.domain.IInstructionMessage#getQuantity()
	 */
	@Override
	public int getQuantity() {
		return quantity;
	}

	/* (non-Javadoc)
	 * @see uk.travisperkins.assessment.domain.IInstructionMessage#getUnitOfMeasure()
	 */
	@Override
	public int getUnitOfMeasure() {
		return unitOfMeasure;
	}

	/* (non-Javadoc)
	 * @see uk.travisperkins.assessment.domain.IInstructionMessage#getTimestamp()
	 */
	@Override
	public int getTimestamp() {
		return timestamp;
	}
	
	

	@Override
	/**
	 * Calculates the instruction priority of this instruction message
	 */
	public InstructionPriority getPriority() {
		
		InstructionPriority ip = null;
		
		if(getInstructionType() > 0 && getInstructionType() <= 10){
			ip = InstructionPriority.HIGH;
		}
		else if(getInstructionType() > 10 && getInstructionType() <= 90){
			ip = InstructionPriority.MEDIUM;
		}
		else if(getInstructionType() > 90 && getInstructionType() < 100){
			ip = InstructionPriority.LOW;
		}
		else{
			ip = InstructionPriority.UNKNOWN;
		}
		
		return ip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + instructionType;
		result = prime * result + productCode;
		result = prime * result + quantity;
		result = prime * result + timestamp;
		result = prime * result + unitOfMeasure;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstructionMessageImpl other = (InstructionMessageImpl) obj;
		if (instructionType != other.instructionType)
			return false;
		if (productCode != other.productCode)
			return false;
		if (quantity != other.quantity)
			return false;
		if (timestamp != other.timestamp)
			return false;
		if (unitOfMeasure != other.unitOfMeasure)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("InstructionMessage [");
		return sb.append("instructionType = ")
				 .append(instructionType)
				 .append(", productCode = ")
				 .append(productCode)
				 .append(", quantity = ")
				 .append(quantity)
				 .append(", unitOfMeasure = ")
				 .append(unitOfMeasure)
				 .append(", timestamp = ")
				 .append(timestamp + "]")
				 .toString();
	}
}
