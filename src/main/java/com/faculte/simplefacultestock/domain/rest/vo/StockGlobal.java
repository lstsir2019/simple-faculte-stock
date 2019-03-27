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

    private String referenceProduit;
    private String referenceCommande;
    private String referenceMagasin;
    private String qte;

    public StockGlobal() {
    }

    public StockGlobal(String referenceCommande, String referenceMagasin, String referenceProduit) {
        this.referenceProduit = referenceProduit;
        this.referenceCommande = referenceCommande;
        this.referenceMagasin = referenceMagasin;
    }

    public StockGlobal(String referenceProduit, String referenceCommande) {
        this.referenceProduit = referenceProduit;
        this.referenceCommande = referenceCommande;
    }

    public String getReferenceProduit() {
        return referenceProduit;
    }

    public void setReferenceProduit(String referenceProduit) {
        this.referenceProduit = referenceProduit;
    }

    public String getReferenceCommande() {
        return referenceCommande;
    }

    public void setReferenceCommande(String referenceCommande) {
        this.referenceCommande = referenceCommande;
    }

    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public String getReferenceMagasin() {
        return referenceMagasin;
    }

    public void setReferenceMagasin(String referenceMagasin) {
        this.referenceMagasin = referenceMagasin;
    }

    @Override
    public String toString() {
        return "StockGlobal{" + "referenceProduit=" + referenceProduit + ", referenceCommande=" + referenceCommande + ", referenceMagasin=" + referenceMagasin + ", qte=" + qte + '}';
    }

  

}
