/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.rest.vo;

/**
 *
 * @author Anas
 */
public class StockGlobal {

    private String referenceCommande;
    private String referenceProduit;
    private String referenceMagasin;
    private Double qte;

    public StockGlobal() {
    }

    public StockGlobal(String referenceCommande, String referenceProduit, String referenceMagasin, Double qte) {
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

    public Double getQte() {
        return qte;
    }

    public void setQte(Double qte) {
        this.qte = qte;
    }

    @Override
    public String toString() {
        return "StockGlobalDTO{" + "referenceCommande=" + referenceCommande + ", referenceProduit=" + referenceProduit + ", referenceMagasin=" + referenceMagasin + ", qte=" + qte + '}';
    }

}
