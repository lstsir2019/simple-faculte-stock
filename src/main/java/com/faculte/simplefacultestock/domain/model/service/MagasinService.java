/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.model.service;

import com.faculte.simplefacultestock.domain.bean.Magasin;
import com.faculte.simplefacultestock.domain.rest.vo.MagasinVo;

import java.util.List;

/**
 *
 * @author Anas
 */
public interface MagasinService {

    public int create(Magasin magasin);
    
    public List<Magasin> findAll();
        
    public Magasin findByReference(String reference);

    public Magasin update(Magasin toItem);

}
