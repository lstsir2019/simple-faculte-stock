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
import java.util.stream.Collectors;
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
        return (res == stocks.size());
    }

    @Override
    public Stock findByReference(String reference) {
        return stockDao.findByReference(reference);
    }

    @Override
    public List<Stock> findStocksByCommandeAndProduit(String refCommande, String refProduit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Stock> findStocksByMagasinAndCommandeAndProduit(String refMagasin, String refCommande, String refProduit) {
        return stockDao.findByMagasinReferenceAndReferenceCommandeAndReferenceProduit(refMagasin, refCommande, refProduit)
                .stream()
                .filter(stock -> stock.getQte() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public int stockLivraison(String refReception, String refMagasin, String refProduit, Integer qteLivre) {
        List<Stock> stocks = findStocksByMagasinAndReceptionAndProduit(refMagasin, refReception, refProduit);
        if (stocks == null || stocks.isEmpty()) {
            return -1;
        } else if (!verifierQte(stocks, qteLivre)) {
            return -2;
        } else {
            for (Stock stock : stocks) {
                if (stock.getQte() > qteLivre) {
                    stock.setQte(stock.getQte() - qteLivre);
                    stockDao.save(stock);
                    break;
                } else {
                    if (qteLivre > 0) {
                        qteLivre = qteLivre - stock.getQte();
                        stock.setQte(0);
                        stockDao.save(stock);
                    } else {
                        break;
                    }
                }
            }
            return 1;
        }
    }

    private boolean verifierQte(List<Stock> stocks, Integer qteLivre) {
        Integer qteTotale = 0; //= stocks.forEach(i->i)
        qteTotale = stocks.stream()
                .map((stock) -> stock.getQte())
                .reduce(qteTotale, Integer::sum);
        return qteTotale >= qteLivre;
    }

    @Override
    public List<Stock> findStocksByMagasinAndReceptionAndProduit(String refMagasin, String reception, String refProduit) {
        return stockDao.findByMagasinReferenceAndReferenceReceptionAndReferenceProduit(refMagasin, reception, refProduit);
    }

    @Override
    public int stockDefected(Stock stock) {
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
