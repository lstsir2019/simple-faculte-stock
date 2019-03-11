/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.model.service.impl;

import com.faculte.simplefacultestock.domain.bean.Magasin;
import com.faculte.simplefacultestock.domain.bean.Stock;
import com.faculte.simplefacultestock.domain.model.dao.StockDao;
import com.faculte.simplefacultestock.domain.model.service.MagasinService;
import com.faculte.simplefacultestock.domain.model.service.StockService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anas
 *
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;
    @Autowired
    private MagasinService magasinService;

    @Override
    public int create(Stock stock) {
        //Methode valideStock return 1 Or nbr<1 (cas de errer)
        int res = valideStock(stock);
        if (valideStock(stock) > 0) {
            stockDao.save(stock);
        }
        return res;
    }

    @Override
    public int create(List<Stock> stocks) {
        if (!valideListStocks(stocks)) {
            return -1;
        } else {
            stockDao.saveAll(stocks);
            return 1;
        }
    }

    @Override
    public List<Stock> findAll() {
        return stockDao.findAll();
    }

    @Override
    public int stockReception(String refMagasin, String refProduit, Integer newQte) {
        Stock s = findByMagasinReferenceAndReferenceProduit(refMagasin, refProduit);
        if (s == null) {
            return -1;
        } else if (newQte < 0) {
            return -2;
        } else {
            s.setQte(s.getQte() + newQte);
            stockDao.save(s);
            return 1;
        }
    }

    private int valideStock(Stock stock) {
        //Methode qui permet de Valide Si 
        //un seul Stock peut s'enregister au BD
        if (stock == null) {
            return -1;
        } else if (stock.getReferenceProduit() == null || stock.getReferenceProduit().isEmpty()) {
            return -2;
        } else if (stock.getReferenceReception() == null || stock.getReferenceReception().isEmpty()) {
            return -3;
        } else {
            
            Magasin magasin = magasinService.findByReference(stock.getMagasin().getReference());
            if (magasin == null) {
                return -4;
            } else {
                stock.setMagasin(magasin);
                return 1;
            }
        }
    }

    private boolean valideListStocks(List<Stock> stocks) {
        //Methode qui permet de Valide
        //Si La Liste des Stock peut s'enregister au BD 
        // sans problem
        int res = 0;
        res = stocks.stream()
                .map((stock) -> valideStock(stock))
                .reduce(res, Integer::sum);
        System.out.println("ressssssssssssssssssssssssssss" + res);
        return (res == stocks.size());
    }

    @Override
    public Stock findByMagasinReferenceAndReferenceProduit(String refMagasin, String refProduit) {
        return stockDao.findByMagasinReferenceAndReferenceProduit(refMagasin, refProduit);
    }

    @Override
    public Stock findByReference(String reference) {
        return stockDao.findByReference(reference);
    }

    @Override
    public int stockLivraison(String refMagasin, String refProduit, Integer qteLivre) {
        Stock s = findByMagasinReferenceAndReferenceProduit(refMagasin, refProduit);
        if (s == null) {
            return -1;
        } else if ((s.getQte() - s.getQteDeffectueuse()) < qteLivre || qteLivre < 0) {
            return -2;
        } else {
            s.setQte(s.getQte() - qteLivre);
            stockDao.save(s);
            return 1;
        }
    }

    @Override
    public int stockDefected(Stock stock) {
//        Stock s = findByMagasinReferenceAndReferenceProduit(stock.getMagasin().getReference(), stock.getReferenceProduit());
//        if (stock == null) {
//            return -1;
//        } else 
        if (stock.getQte() < stock.getQteDeffectueuse() || stock.getQteDeffectueuse() < 0) {
            return -2;
        } else if (stock.getSeuilAlert() < 0) {
            return -3;
        } else {
            stock.setQteDeffectueuse(stock.getQteDeffectueuse());
            stock.setSeuilAlert(stock.getSeuilAlert());
            stockDao.save(stock);
            return 1;
        }
    }

    ///Getter And Setter 
    public StockDao getStockDao() {
        return stockDao;
    }

    public void setStockDao(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    public MagasinService getMagasinService() {
        return magasinService;
    }

    public void setMagasinService(MagasinService magasinService) {
        this.magasinService = magasinService;
    }

}
