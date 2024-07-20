package com.bankaya.pokeapi.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="ip")
	private String originIp;
	
	@Column(name="requestDate")
	private Date requestDate;
	
	@Column(name="method")
	private String method;
	
	@Column(name="activeTime")
	private String activeTime;
	
	@Column(name="request")
	private String request;
	
	@Column(name="response")
	private String response;
	
}
