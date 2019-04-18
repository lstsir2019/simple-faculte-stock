/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.model.service.dto;

/**
 *
 * @author Anas
 */
public class StockGlobalDTO {

    private String referenceCommande;
    private String referenceProduit;
    private String referenceMagasin;
    private Long qte;

    public StockGlobalDTO() {
    }

    public StockGlobalDTO(String referenceCommande, String referenceProduit, String referenceMagasin, Long qte) {
        this.referenceCommande = referenceCommande;
        this.referenceProduit = referenceProduit;
        this.referenceMagasin = referenceMagasin;
        this.qte = qte;
    }

    public String getReferenceCommande() {
        return referenceCommande;
    }

    public void setReferenceCommande(String referenceCommande) {
        this.referenceCommande = referenceCommande;
    }

    public String getReferenceProduit() {
        return referenceProduit;
    }

    public void setReferenceProduit(String referenceProduit) {
        this.referenceProduit = referenceProduit;
    }

    public String getReferenceMagasin() {
        return referenceMagasin;
    }

    public void setReferenceMagasin(String referenceMagasin) {
        this.referenceMagasin = referenceMagasin;
    }

    public Long getQte() {
        return qte;
    }

    public void setQte(Long qte) {
        this.qte = qte;
    }

    @Override
    public String toString() {
        return "StockGlobalDTO{" + "referenceCommande=" + referenceCommande + ", referenceProduit=" + referenceProduit + ", referenceMagasin=" + referenceMagasin + ", qte=" + qte + '}';
    }

    
}
