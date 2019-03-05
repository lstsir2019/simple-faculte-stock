/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.model.service;


import com.faculte.simplefacultestock.domain.bean.Stock;
import java.util.List;

/**
 *
 * @author Anas
 */
public interface StockService {

    public int create(Stock stock);

    public int create(List<Stock> stocks);
    
    public List<Stock> findAll();

    public int stockReception(String refMagasin, String refProduit, Integer newQte);

    public int stockLivraison(String refMagasin, String refProduit, Integer qteLivre);

    public int stockDefected(Stock stock);

    public Stock findByMagasinReferenceAndReferenceProduit(String refMagasin, String refProduit);

    public Stock findByReference(String codeStock);

}
