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
public class StockVo {

    private String reference;
    private String referenceReception;
    private String referenceProduit;
    private String referenceCommande;
    private String qte;
    private String qteDeffectueuse;
    private String seuilAlert;
    private MagasinVo magasinVo;

    public StockVo() {
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReferenceReception() {
        return referenceReception;
    }

    public void setReferenceReception(String referenceReception) {
        this.referenceReception = referenceReception;
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

    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public String getQteDeffectueuse() {
        return qteDeffectueuse;
    }

    public void setQteDeffectueuse(String qteDeffectueuse) {
        this.qteDeffectueuse = qteDeffectueuse;
    }

    public String getSeuilAlert() {
        return seuilAlert;
    }

    public void setSeuilAlert(String seuilAlert) {
        this.seuilAlert = seuilAlert;
    }

    public MagasinVo getMagasinVo() {
        return magasinVo;
    }

    public void setMagasinVo(MagasinVo magasinVo) {
        this.magasinVo = magasinVo;
    }

    @Override
    public String toString() {
        return "StockVo{" + "reference=" + reference + ", referenceReception=" + referenceReception + ", referenceProduit=" + referenceProduit + ", qte=" + qte + ", qteDeffectueuse=" + qteDeffectueuse + ", seuilAlert=" + seuilAlert + ", magasinVo=" + magasinVo + '}';
    }
    
    
}
