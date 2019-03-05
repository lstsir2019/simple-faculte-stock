/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.rest.converter;

import com.faculte.simplefacultestock.domain.bean.Magasin;
import com.faculte.simplefacultestock.domain.rest.vo.MagasinVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anas
 */
@Component
public class MagasinConverter implements AbstractConverter<Magasin, MagasinVo> {

    @Override
    public Magasin toItem(MagasinVo vo) {
        if (vo == null) {
            return null;
        } else {
            Magasin item = new Magasin();
            item.setAddress(vo.getAddress());
            item.setDescription(vo.getDescription());
            item.setLibelle(vo.getLibelle());
            item.setReference(vo.getReference());
            return item;
        }
    }

    @Override
    public MagasinVo toVo(Magasin item) {
        if (item == null) {
            return null;
        } else {
            MagasinVo vo = new MagasinVo();
            vo.setAddress(item.getAddress());
            vo.setDescription(item.getDescription());
            vo.setLibelle(item.getLibelle());
            vo.setReference(item.getReference());
            return vo;
        }
    }
}
