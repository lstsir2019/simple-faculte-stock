/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.model.service;

import com.faculte.simplefacultestock.domain.bean.Stock;
import com.faculte.simplefacultestock.domain.rest.vo.StockGlobal;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Anas
 */
public interface StockService {

    public int create(Stock stock);

    public int create(List<Stock> stocks);

    public List<StockGlobal> findStockGlobalByCommandeAndProduit(String refCommande, String refProduit);

    public List<StockGlobal> findAllStockGlobal();

    public List<Stock> findAll();

    public int LivraisonStockUnique(String refReception, String refMagasin, String refProduit, Double qteLivre);
//    public int stockReception(String refMagasin, String refProduit, Integer newQte);

    public List<Stock> findByCriteria(String reception, String commande, Date dateMin, Date dateMax);

    public int stockDefected(Stock stock);

    public int livraisonStockGlobal(String refcommande, String refProduit, String strategy, Double qteLivre);

    public List<Stock> findStocksByMagasinAndReceptionAndProduit(String refMagasin, String reception, String refProduit);

    public List<Stock> findStocksByMagasinAndCommandeAndProduit(String refMagasin, String refCommande, String refProduit);

    public List<Stock> findStocksByCommandeAndProduit(String refCommande, String refProduit);

//    public Stock findByReference(String codeStock);

    public List<Stock> findStocksByCommandeAndProduitAndStrategy(String refcommande, String refProduit, String strategy);

    public Double getStockBilan(String refCommande, String refProduit);
}
