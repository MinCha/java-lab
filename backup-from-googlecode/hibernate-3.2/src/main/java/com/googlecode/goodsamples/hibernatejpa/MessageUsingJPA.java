package com.googlecode.goodsamples.hibernatejpa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "JPA_MESSAGES")
class MessageUsingJPA {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="message")
	private String text;
	@ManyToOne(cascade = CascadeType.ALL)
	private MessageUsingJPA nextMessage;

	@SuppressWarnings("unused")
	private MessageUsingJPA() {
	}

	public MessageUsingJPA(String text) {
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public MessageUsingJPA getNextMessage() {
		return nextMessage;
	}

	public void setNextMessage(MessageUsingJPA nextMessage) {
		this.nextMessage = nextMessage;
	}
}