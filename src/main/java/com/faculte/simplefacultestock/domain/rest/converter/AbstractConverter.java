/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.rest.converter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anas
 */
public interface AbstractConverter<T, VO> {

    public abstract T toItem(VO vo);

    public abstract VO toVo(T item);

    public default List<T> toItem(List<VO> vos) {
        if (vos == null || vos.isEmpty()) {
            return null;
        } else {
            List<T> items = new ArrayList();
            vos.forEach(vo -> items.add(toItem(vo)));
            return items;
        }
    }

    public default  List<VO> toVo(List<T> items) {
        if (items == null || items.isEmpty()) {
            return null;
        } else {
            List<VO> vos = new ArrayList();
            items.forEach(item -> vos.add(toVo(item)));
            return vos;
        }
    }
}
