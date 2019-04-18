/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.model.dao;

import com.faculte.simplefacultestock.domain.bean.Stock;
import com.faculte.simplefacultestock.domain.rest.vo.StockGlobal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anas
 */
@Repository
public interface StockDao extends JpaRepository<Stock, Long> {

    public final String QUERY_ALL_STOCK_GLOBAL = "SELECT NEW com.faculte.simplefacultestock.domain.rest.vo.StockGlobal(s.referenceCommande,s.referenceProduit,s.magasin.reference,SUM(s.qte)) FROM Stock s group by s.referenceCommande,s.referenceProduit,s.magasin.reference ORDER BY s.referenceCommande,s.referenceProduit";

    public final String QUERY_ONE_STOCK_GLOBAL = "SELECT NEW com.faculte.simplefacultestock.domain.rest.vo.StockGlobal(s.referenceCommande,s.referenceProduit,s.magasin.reference,SUM(s.qte)) FROM Stock s WHERE s.referenceCommande =?1 AND s.referenceProduit =?2 GROUP BY s.magasin.reference";

    @Query(QUERY_ALL_STOCK_GLOBAL)
    public List<StockGlobal> findAllByCommande();

    @Query(QUERY_ONE_STOCK_GLOBAL)
    public List<StockGlobal> findStockGlobalByCommandeAndProduit(String refCommande, String refProduit);

   // public Stock findByReference(String reference);

    public List<Stock> findByMagasinReferenceAndReferenceCommandeAndReferenceProduit(String refMagasin, String refCommande, String refProduit);

    public List<Stock> findByMagasinReferenceAndReferenceReceptionAndReferenceProduit(String refMagasin, String refReception, String refProduit);

    public List<Stock> findByReferenceCommandeAndReferenceProduitOrderByDateReceptionDesc(String refCommande, String refProduit);

    public List<Stock> findByReferenceCommandeAndReferenceProduitOrderByDateReceptionAsc(String refCommande, String refProduit);

    public List<Stock> findByReferenceCommandeAndReferenceProduit(String refCommande, String refProduit);

}
