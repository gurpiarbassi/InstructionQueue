package com.gurps.instructionqueue.domain.exception;

import com.gurps.instructionqueue.domain.InstructionMessage;

/**
 * Exception class created to capture invalid messages added
 * to the Instruction Message Queue.
 * 
 * @author gurps
 *
 */
public class InvalidMessageException extends Exception {

	private static final long serialVersionUID = 1L;

     public InvalidMessageException(InstructionMessage instruction, String errorMessage)
     {
        super(errorMessage + " : " + instruction.toString());
     }
}
