/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.rest;

import com.faculte.simplefacultestock.commun.util.DateUtil;
import com.faculte.simplefacultestock.commun.util.NumberUtil;
import com.faculte.simplefacultestock.domain.bean.Stock;
import com.faculte.simplefacultestock.domain.model.dao.StockDao;
import com.faculte.simplefacultestock.domain.model.service.StockService;
import com.faculte.simplefacultestock.domain.rest.vo.StockGlobal;
import com.faculte.simplefacultestock.domain.rest.converter.AbstractConverter;
import com.faculte.simplefacultestock.domain.rest.converter.StockConverter;
import com.faculte.simplefacultestock.domain.rest.converter.StockGlobalConverter;
import com.faculte.simplefacultestock.domain.rest.vo.StockGlobalVo;
import com.faculte.simplefacultestock.domain.rest.vo.StockVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Anas
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("stock-api/stocks")
public class StockRest {

    @Autowired
    private StockService stockService;
    @Autowired
    @Qualifier("stockConverter")
    private AbstractConverter<Stock, StockVo> stockConverter;
    @Autowired
    @Qualifier("stockGlobalConverter")
    private AbstractConverter<StockGlobal, StockGlobalVo> stockGlobalConverter;

    public int create(Stock stock) {
        return stockService.create(stock);
    }

    @PostMapping("/")
    public int create(@RequestBody List<StockVo> stocks) {
        int res = stockService.create(stockConverter.toItem(stocks));
        return res;
    }

    @GetMapping("/magasin/{refmagasin}/commande/{refcommande}/produit/{refproduit}")
    public List<StockVo> findStocksByMagasinAndCommandeAndProduit(@PathVariable("refmagasin") String refMagasin, @PathVariable("refcommande") String refCommande, @PathVariable("refproduit") String refProduit) {
        return stockConverter.toVo(stockService.findStocksByMagasinAndCommandeAndProduit(refMagasin, refCommande, refProduit));
    }

    @GetMapping("/")
    public List<StockVo> findAll() {
        return stockConverter.toVo(stockService.findAll());
    }

    @PostMapping("/search")
    public List<StockVo> findByCriteria(@RequestBody StockVo stockVo) {
        return stockConverter.toVo(stockService.findByCriteria(stockVo.getReferenceReception(), stockVo.getReferenceCommande(), DateUtil.parseDate(stockVo.getDateMin()), DateUtil.parseDate(stockVo.getDateMax())));
    }

    @PutMapping("/")
    public int stockLivraison(@RequestBody StockVo stockVo) {
        Stock stock = stockConverter.toItem(stockVo);
        return stockService.LivraisonStockUnique(stock.getReferenceReception(), stock.getMagasin().getReference(), stock.getReferenceProduit(), stock.getQte());
    }

    //StockGlobal
    @GetMapping("/commande/{refcommande}/produit/{refproduit}")
    public List<StockGlobalVo> findByCommandeAndProduit(@PathVariable String refcommande, @PathVariable String refproduit) {
        System.out.println(refcommande + " , " + refproduit);
        return stockGlobalConverter.toVo(stockService.findStockGlobalByCommandeAndProduit(refcommande, refproduit));
        // return converter.findByCommandeAndProduit(refcommande, refproduit);
    }

    //StockGlobal
    @GetMapping("/stockglobal")
    public List<StockGlobalVo> findAllByCommande() {
        return stockGlobalConverter.toVo(stockService.findAllStockGlobal());
    }

    @GetMapping("/commande/{refcommande}/produit/{refproduit}/strategy/{strategy}")
    public List<StockVo> findStocksByReceptionAndProduitAndStrategy(@PathVariable String refcommande, @PathVariable String refproduit, @PathVariable String strategy) {
        return stockConverter.toVo(stockService.findStocksByCommandeAndProduitAndStrategy(refcommande, refproduit, strategy));
    }

    @PutMapping("/stockglobal/strategy/{strategy}")
    public int livraisonStockGlobal(@RequestBody StockGlobalVo stockGlobal, @PathVariable String strategy) {
        return stockService.livraisonStockGlobal(stockGlobal.getReferenceCommande(), stockGlobal.getReferenceProduit(), strategy, NumberUtil.toDouble(stockGlobal.getQte()));
    }

    @PutMapping("/update")
    public int stockDefected(@RequestBody Stock stock) {
        return stockService.stockDefected(stock);
    }

    public StockService getStockService() {
        return stockService;
    }

    public AbstractConverter<Stock, StockVo> getStockConverter() {
        return stockConverter;
    }

    public void setStockConverter(AbstractConverter<Stock, StockVo> stockConverter) {
        this.stockConverter = stockConverter;
    }

    public void setStockConverter(StockConverter stockConverter) {
        this.stockConverter = stockConverter;
    }

    public AbstractConverter<StockGlobal, StockGlobalVo> getStockGlobalConverter() {
        return stockGlobalConverter;
    }

    public void setStockGlobalConverter(AbstractConverter<StockGlobal, StockGlobalVo> stockGlobalConverter) {
        this.stockGlobalConverter = stockGlobalConverter;
    }

}
