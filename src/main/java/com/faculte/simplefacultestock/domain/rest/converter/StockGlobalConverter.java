/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.rest.converter;

import com.faculte.simplefacultestock.commun.util.NumberUtil;
import com.faculte.simplefacultestock.domain.bean.Magasin;
import com.faculte.simplefacultestock.domain.bean.Stock;
import com.faculte.simplefacultestock.domain.model.service.StockService;
import com.faculte.simplefacultestock.domain.rest.vo.StockGlobal;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anas
 */
@Component
public class StockGlobalConverter {

    @Autowired
    private StockService stockService;

    public List<StockGlobal> findByCommandeAndProduit(String referenceCommande, String referenceProduit) {
        List<StockGlobal> globals = new ArrayList<>();
        List<Stock> stocks = stockService.findStocksByCommandeAndProduit(referenceCommande, referenceProduit);
        List<Magasin> magasins = getMagasinsFromList(stocks);
        for (Magasin magasin : magasins) {
            List<Stock> stocksByMagasin = uniqueMagsin(magasin.getReference(), stocks);
            globals.add(listStocksToStockGlobal(magasin.getReference(), referenceCommande, referenceProduit, calculerQteGlobal(stocksByMagasin)));
        }
        return globals;
    }

    private StockGlobal listStocksToStockGlobal(String referenceMagasin, String referenceCommande, String referenceProduit, Integer qte) {
        StockGlobal global = new StockGlobal();
        global.setQte(NumberUtil.toString(qte));
        global.setReferenceCommande(referenceCommande);
        global.setReferenceMagasin(referenceMagasin);
        global.setReferenceProduit(referenceProduit);
        return global;
    }

    private List<Magasin> getMagasinsFromList(List<Stock> stocks) {
        List<Magasin> magasins = new ArrayList<>();
        if (stocks.isEmpty()) {
            return magasins;
        } else {
            Stock first = stocks.get(0);
            magasins.add(first.getMagasin());
            for (Stock stock : stocks) {
                if (!magasinInList(stock.getMagasin(), magasins)) {
                    magasins.add(stock.getMagasin());
                }
            }
            return magasins;
        }
    }

    private Boolean magasinInList(Magasin magasin, List<Magasin> magasins) {
        for (Magasin mag : magasins) {
            if (mag.getReference().equals(magasin.getReference())) {
                return true;
            }
        }
        return false;
    }

    private Integer calculerQteGlobal(List<Stock> stocks) {
        return stocks.stream()
                .mapToInt(c -> c.getQte())
                .sum();
    }

    private List<Stock> uniqueMagsin(String referenceMagasin, List<Stock> stocks) {
        return stocks.stream()
                .filter(s -> s.getMagasin().getReference().equals(referenceMagasin))
                .collect(Collectors.toList());
    }

    private Boolean isListHasUniqueMagasin(List<Stock> stocks) {
        if (stocks.isEmpty()) {
            return false;
        }
        Stock first = stocks.get(0);
        for (Stock stock : stocks) {
            if (!stock.getMagasin().getReference().equals(first.getMagasin().getReference())) {
                return false;
            } else if (!stock.getReferenceProduit().equals(first.getReferenceProduit())) {
                return false;
            }
        }
        return true;
    }
}
