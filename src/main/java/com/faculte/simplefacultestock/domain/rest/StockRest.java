/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.rest;

import com.faculte.simplefacultestock.domain.bean.Stock;
import com.faculte.simplefacultestock.domain.model.service.StockService;
import com.faculte.simplefacultestock.domain.rest.converter.StockConverter;
import com.faculte.simplefacultestock.domain.rest.vo.StockVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    private StockConverter stockConverter;

    public int create(Stock stock) {
        return stockService.create(stock);
    }

    @PostMapping("/")
    public int create(@RequestBody List<StockVo> stocks) {
        System.out.println("*********************************SaveStock*************************");
        System.out.println(stockConverter.toItem(stocks));
        System.out.println("*******************************************************************");
        int res = stockService.create(stockConverter.toItem(stocks));
        System.out.println("ressssssssssssssssssssssssssssssssssssss:" + res);
        return res;
    }

    @GetMapping("find/magasin/{refmagasin}/produit/{refproduit}")
    public Stock findByMagasinReferenceAndProduitReference(@PathVariable String refmagasin, @PathVariable String refproduit) {
        return stockService.findByMagasinReferenceAndReferenceProduit(refmagasin, refproduit);
    }

    @GetMapping("/stocks")
    public List<Stock> findAll() {
        return stockService.findAll();
    }

    @PutMapping("/update")
    public int stockDefected(@RequestBody Stock stock) {
        return stockService.stockDefected(stock);
    }

    public StockService getStockService() {
        return stockService;
    }

    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    public StockConverter getStockConverter() {
        return stockConverter;
    }

    public void setStockConverter(StockConverter stockConverter) {
        this.stockConverter = stockConverter;
    }

}
