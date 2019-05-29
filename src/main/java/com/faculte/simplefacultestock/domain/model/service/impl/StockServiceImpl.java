/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.model.service.impl;

import com.faculte.simplefacultestock.commun.util.SearchUtil;
import com.faculte.simplefacultestock.domain.bean.Magasin;
import com.faculte.simplefacultestock.domain.bean.Stock;
import com.faculte.simplefacultestock.domain.model.dao.StockDao;
import com.faculte.simplefacultestock.domain.model.service.MagasinService;
import com.faculte.simplefacultestock.domain.model.service.StockService;
import com.faculte.simplefacultestock.domain.rest.vo.StockGlobal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Stock> findByCriteria(String reception, String commande, Date dateMin, Date dateMax) {
        return entityManager.createQuery(constructQuery(reception, commande, dateMin, dateMax)).getResultList();
    }

    private String constructQuery(String reception, String commande, Date dateMin, Date dateMax) {
        String query = "SELECT s FROM Stock s where 1=1 ";
        query += SearchUtil.addConstraint("s", "referenceReception", "LIKE", reception);
        query += SearchUtil.addConstraint("s", "referenceCommande", "LIKE", commande);
        query += SearchUtil.addConstraintMinMaxDate("s", "dateReception", dateMin, dateMax);
        return query;
    }

    @Override
    public int create(Stock stock) {
        int res = valideStock(stock);
        if (res > 0) {
            stockDao.save(stock);
        }
        return res;
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

    @Override
    public void deleteByReferenceCommandeAndReception(String referenceCommande, String referenceReception) {
        List<Stock> stocks = findByReferenceCommandeAndReferenceReception(referenceCommande, referenceReception);
        stocks.forEach(s -> stockDao.delete(s));
    }

    public List<Stock> findByReferenceCommandeAndReferenceReception(String refCommande, String refReception) {
        return stockDao.findByReferenceCommandeAndReferenceReception(refCommande, refReception);
    }

    @Override
    public List<Stock> findAll() {
        return stockDao.findAll();
    }

    @Override
    public List<StockGlobal> findStockGlobalByCriteria(StockGlobal stockGlobal) {
        return entityManager.createQuery(constructQueryForStockGlobal(stockGlobal)).getResultList();
    }

    private String constructQueryForStockGlobal(StockGlobal global) {
        String query = "SELECT NEW com.faculte.simplefacultestock.domain.rest.vo.StockGlobal(s.referenceCommande,s.referenceProduit,s.magasin.reference,SUM(s.qte)) FROM Stock s WHERE 1=1";
        query += SearchUtil.addConstraint("s", "referenceProduit", "LIKE", global.getReferenceProduit());
        query += SearchUtil.addConstraint("s", "referenceCommande", "LIKE", global.getReferenceCommande());
        query += SearchUtil.addConstraint("s", "magasin.reference", "LIKE", global.getReferenceMagasin());
//        query += SearchUtil.addConstraintMinMaxDate("s", "dateReception", dateMin, dateMax);
        query += " GROUP BY s.referenceCommande,s.referenceProduit,s.magasin.reference ";
        return query;
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
    public List<Stock> findStocksByCommandeAndProduit(String refCommande, String refProduit) {
        return stockDao.findByReferenceCommandeAndReferenceProduit(refCommande, refProduit);
    }

    @Override
    public List<Stock> findStocksByMagasinAndCommandeAndProduit(String refMagasin, String refCommande, String refProduit) {
        return stockDao.findByMagasinReferenceAndReferenceCommandeAndReferenceProduit(refMagasin, refCommande, refProduit)
                .stream()
                .filter(stock -> stock.getQte() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public int LivraisonStockUnique(String refReception, String refMagasin, String refProduit, Double qteLivre) {
        List<Stock> stocks = findStocksByMagasinAndReceptionAndProduit(refMagasin, refReception, refProduit);
        if (qteLivre == 0) {
            return -1;
        } else if (stocks == null || stocks.isEmpty()) {
            return -2;
        } else if (!verifierQte(stocks, qteLivre)) {
            return -3;
        } else {
            takingFromStocks(stocks, qteLivre);
            return 1;
        }
    }

    private void takingFromStocks(List<Stock> stocks, Double qteLivre) {
        for (Stock stock : stocks) {
            if (stock.getQte() > qteLivre) {
                stock.setQte(stock.getQte() - qteLivre);
                stockDao.save(stock);
                break;
            } else {
                if (qteLivre > 0) {
                    qteLivre = qteLivre - stock.getQte();
                    stock.setQte(0D);
                    stockDao.save(stock);
                } else {
                    break;
                }
            }
        }
    }

    private boolean verifierQte(List<Stock> stocks, Double qteLivre) {
        Double qteTotale = 0D; //= stocks.forEach(i->i)
        qteTotale = stocks.stream()
                .map((stock) -> stock.getQte())
                .reduce(qteTotale, Double::sum);
        return qteTotale >= qteLivre;
    }

    @Override
    public List<Stock> findStocksByMagasinAndReceptionAndProduit(String refMagasin, String reception, String refProduit) {
        return stockDao.findByMagasinReferenceAndReferenceReceptionAndReferenceProduit(refMagasin, reception, refProduit);
    }

    @Override
    public int stockDefected(Stock stock) {
        if (stock.getQteDeffectueuse() < 0) {
            return -2;
        } else if (stock.getSeuilAlert() < 0) {
            return -3;
        } else {
            Stock s = stockDao.getOne(stock.getId());
            if (s == null) {
                return -1;
            } else if (s.getQte() < (stock.getQteDeffectueuse() - s.getQteDeffectueuse())) {
                return -2;
            } else {
                s.setQte(s.getQte() - (stock.getQteDeffectueuse() - s.getQteDeffectueuse()));
                s.setQteDeffectueuse(stock.getQteDeffectueuse());
                s.setSeuilAlert(stock.getSeuilAlert());
                stockDao.save(s);
                return 1;
            }
        }
    }

    @Override
    public List<Stock> findStocksByCommandeAndProduitAndStrategy(String refcommande, String refProduit, String strategy) {
        return findStocksByCommandeProduitStrategy(refcommande, refProduit, strategy).stream()
                .filter(s -> s.getQte() > 0)
                .collect(Collectors.toList());
    }

    private List<Stock> findStocksByCommandeProduitStrategy(String refcommande, String refProduit, String strategy) {
        if (strategy.equalsIgnoreCase("FIFO")) {
            return stockDao.findByReferenceCommandeAndReferenceProduitOrderByDateReceptionAsc(refcommande, refProduit);
        } else if (strategy.equalsIgnoreCase("LIFO")) {
            return stockDao.findByReferenceCommandeAndReferenceProduitOrderByDateReceptionDesc(refcommande, refProduit);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public int livraisonStockGlobal(String refcommande, String refProduit, String strategy, Double qteLivre) {
        List<Stock> stocks = findStocksByCommandeAndProduitAndStrategy(refcommande, refProduit, strategy);
        if (stocks.isEmpty()) {
            return -1;
        } else if (qteTotal(stocks) < qteLivre) {
            return -2;
        } else {
            takingFromStocks(stocks, qteLivre);
            return 1;
        }
    }

    public int decrementStockGlobal(Stock stock, Double qteAnnule) {
        Stock s = new Stock();
        s.setDateReception(new Date());
        s.setReferenceCommande(stock.getReferenceCommande());
        Magasin m = magasinService.findByReference(stock.getMagasin().getReference());
        s.setMagasin(m);
        s.setReferenceProduit(stock.getReferenceReception());
        s.setQte(qteAnnule);
        s.setReferenceReception(stock.getReferenceReception());
        stockDao.save(stock);
        return 1;
    }

    @Override
    public List<StockGlobal> findAllStockGlobal() {
        return stockDao.findAllByCommande();
    }

    @Override
    public List<StockGlobal> findStockGlobalByCommandeAndProduit(String refCommande, String refProduit) {
        return stockDao.findStockGlobalByCommandeAndProduit(refCommande, refProduit);
    }

    @Override
    public Double getStockBilan(String refCommande, String refProduit) {
        return qteTotal(findStocksByCommandeAndProduit(refCommande, refProduit));
    }

    private Double qteTotal(List<Stock> stocks) {
        return stocks.stream()
                .mapToDouble(s -> s.getQte())
                .sum();
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
