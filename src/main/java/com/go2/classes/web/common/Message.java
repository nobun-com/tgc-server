package com.go2.classes.web.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Message implements Serializable {
	
	private static final long serialVersionUID = -6488696546322780424L;
	
	private MessageType type;
	
	private String message;
	
	private List<String> messageArguments = new ArrayList<String>();;

	public Message() {
	}
	
	public Message(MessageType type, String message) {
		this.type = type;
		this.message = message;
	}

	public Message(MessageType type, String message, String... messageArguments) {
		this.type = type;
		this.message = message;
		this.messageArguments.addAll(Arrays.asList(messageArguments));
	}

	public void addMessageArgument(String messageArgument) {
		this.messageArguments.add(messageArgument);
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getMessageArguments() {
		return messageArguments;
	}

	public void setMessageArguments(List<String> messageArguments) {
		this.messageArguments = messageArguments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((message == null) ? 0 : message.hashCode());
		result = (prime
				* result)
				+ ((messageArguments == null) ? 0 : messageArguments.hashCode());
		result = (prime * result) + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Message other = (Message) obj;
		if (message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!message.equals(other.message)) {
			return false;
		}
		if (messageArguments == null) {
			if (other.messageArguments != null) {
				return false;
			}
		} else if (!messageArguments.equals(other.messageArguments)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}

}
