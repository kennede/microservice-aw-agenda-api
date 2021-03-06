/**
 * 
 */
package com.github.sepulvida.agendaapi.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kenne
 *
 */
@Entity
@Data
@NoArgsConstructor
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nome;
	@Column
	private String email;
	@Column
	private Boolean favorito;
	@Column
	@Lob
	private byte[] foto;
}
