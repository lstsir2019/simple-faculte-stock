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
import com.faculte.simplefacultestock.domain.rest.vo.StockGlobalVo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author Anas
 */
@Component
public class StockGlobalConverter implements AbstractConverter<StockGlobal, StockGlobalVo> {

    @Autowired
    private StockService stockService;

    @Override
    public StockGlobal toItem(StockGlobalVo vo) {
        if (vo == null) {
            return null;
        } else {
            StockGlobal item = new StockGlobal();
            item.setReferenceCommande(vo.getReferenceCommande());
            item.setReferenceProduit(vo.getReferenceProduit());
            item.setReferenceMagasin(vo.getReferenceMagasin());
            item.setQte(NumberUtil.toDouble(vo.getQte()));
            return item;
        }
    }

    @Override
    public StockGlobalVo toVo(StockGlobal item) {
        if (item == null) {
            return null;
        } else {
            StockGlobalVo vo = new StockGlobalVo();
            vo.setReferenceCommande(item.getReferenceCommande());
            vo.setReferenceProduit(item.getReferenceProduit());
            vo.setReferenceMagasin(item.getReferenceMagasin());
            vo.setQte(item.getQte() + "");
            return vo;
        }
    }
}
