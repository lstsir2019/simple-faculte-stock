/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.rest.converter;

import com.faculte.simplefacultestock.commun.util.DateUtil;
import com.faculte.simplefacultestock.commun.util.NumberUtil;
import com.faculte.simplefacultestock.domain.bean.Magasin;
import com.faculte.simplefacultestock.domain.bean.Stock;
import com.faculte.simplefacultestock.domain.rest.vo.MagasinVo;
import com.faculte.simplefacultestock.domain.rest.vo.StockVo;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anas
 */
@Component
public class StockConverter implements AbstractConverter<Stock, StockVo> {

    @Autowired
    @Qualifier("magasinConverter")
    private AbstractConverter<Magasin, MagasinVo> magasinConverter;

    @Override
    public Stock toItem(StockVo vo) {
        if (vo == null) {
            return null;
        } else {
            Stock item = new Stock();
            item.setReferenceReception(vo.getReferenceReception());
            item.setDateReception(DateUtil.parseDate(vo.getDateReception()));
            item.setReferenceCommande(vo.getReferenceCommande());
            item.setReferenceProduit(vo.getReferenceProduit());
            item.setMagasin(magasinConverter.toItem(vo.getMagasinVo()));
            item.setQte(NumberUtil.toDouble(vo.getQte()));
            item.setQteDeffectueuse(NumberUtil.toDouble(vo.getQteDeffectueuse()));
            item.setSeuilAlert(NumberUtil.toDouble(vo.getSeuilAlert()));
            item.setId(NumberUtil.toLong(vo.getId()));
            return item;
        }
    }

    @Override
    public StockVo toVo(Stock item) {
        if (item == null) {
            return null;
        } else {
            StockVo vo = new StockVo();
            vo.setReferenceReception(item.getReferenceReception());
            vo.setDateReception(DateUtil.formateDate(item.getDateReception()));
            vo.setReferenceProduit(item.getReferenceProduit());
            vo.setReferenceCommande(item.getReferenceCommande());
            vo.setMagasinVo(magasinConverter.toVo(item.getMagasin()));
            vo.setQte(NumberUtil.toString(item.getQte()));
            vo.setQteDeffectueuse(NumberUtil.toString(item.getQteDeffectueuse()));
            vo.setSeuilAlert(NumberUtil.toString(item.getSeuilAlert()));
            vo.setId(item.getId() + "");
            return vo;
        }
    }

    public AbstractConverter<Magasin, MagasinVo> getMagasinConverter() {
        return magasinConverter;
    }

    public void setMagasinConverter(AbstractConverter<Magasin, MagasinVo> magasinConverter) {
        this.magasinConverter = magasinConverter;
    }

}
