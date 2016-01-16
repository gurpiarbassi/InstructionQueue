package com.gurps.instructionqueue.domain.exception;

import com.gurps.instructionqueue.domain.InstructionMessage;

public class InvalidMessageException extends Exception {

	private static final long serialVersionUID = 1L;

     public InvalidMessageException(InstructionMessage instruction, String message)
     {
        super(message + " : " + instruction.toString());
     }
     
     public InvalidMessageException(InstructionMessage instruction, String message, Throwable throwable) {
         super(message + " : " + instruction.toString(), throwable);
     }
}
