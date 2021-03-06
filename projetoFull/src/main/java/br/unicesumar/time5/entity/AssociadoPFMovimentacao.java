package br.unicesumar.time5.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name="tbopr_associado_pessoa_fisica_movimentacao")
public class AssociadoPFMovimentacao extends SuperEntidade {

	@Column(name="data_pagamento")
	private Date dataPagamento;
	
	@Column(name="valor_pago")
	private float valorPago;
	
	@Column(name="is_pago")
	private Boolean isPago;
	
	@ManyToOne
	@JoinColumn(name="id_associado")
	private AssociadoPF associadoPF;

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		 dataPagamento.setDate(dataPagamento.getDate() + 1);
	        this.dataPagamento = dataPagamento;
	}

	public float getValorPago() {
		return valorPago;
	}

	public void setValorPago(float valorPago) {
		this.valorPago = valorPago;
	}

	public Boolean getIsPago() {
		return isPago;
	}

	public void setIsPago(Boolean isPago) {
		this.isPago = isPago;
	}

	public AssociadoPF getAssociadoPF() {
		return associadoPF;
	}

	public void setAssociadoPF(AssociadoPF associadoPF) {
		this.associadoPF = associadoPF;
	}
}
