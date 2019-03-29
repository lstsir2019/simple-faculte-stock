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

    public int LivraisonStockUnique(String refReception, String refMagasin, String refProduit, Integer qteLivre);
//    public int stockReception(String refMagasin, String refProduit, Integer newQte);

    public int stockDefected(Stock stock);

    public int livraisonStockGlobal(String refcommande, String refProduit, String strategy, Integer qteLivre);

    public List<Stock> findStocksByMagasinAndReceptionAndProduit(String refMagasin, String reception, String refProduit);

    public List<Stock> findStocksByMagasinAndCommandeAndProduit(String refMagasin, String refCommande, String refProduit);

    public List<Stock> findStocksByCommandeAndProduit(String refCommande, String refProduit);

    public Stock findByReference(String codeStock);

    public List<Stock> findStocksByCommandeAndProduitAndStrategy(String refcommande, String refProduit, String strategy);

}
