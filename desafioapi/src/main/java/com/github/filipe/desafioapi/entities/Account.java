package com.github.filipe.desafioapi.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tb_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String number;

	private String agency;

	@Column(precision = 13, scale = 2)
	private BigDecimal balance = BigDecimal.ZERO;

	@Column(name = "additional_limit", precision = 13, scale = 2)
	private BigDecimal limit = BigDecimal.ZERO;

}
