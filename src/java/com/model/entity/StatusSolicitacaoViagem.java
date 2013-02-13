package com.model.entity;

/**
 *
 * @author thiago
 */
public enum StatusSolicitacaoViagem {

    SOLICITADO("solicitado"),
    CANCELADO("cancelado"),
    EFETIVADO("efetivado");
    private String status;

    private StatusSolicitacaoViagem(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
