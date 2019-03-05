/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.model.service.impl;


import com.faculte.simplefacultestock.domain.bean.Magasin;
import com.faculte.simplefacultestock.domain.model.dao.MagasinDao;
import com.faculte.simplefacultestock.domain.model.service.MagasinService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class MagasinSeviceImpl implements MagasinService {

    @Autowired
    private MagasinDao magasinDao;

    @Override
    public int create(Magasin magasin) {
        Magasin m = findByReference(magasin.getReference());
        if (m != null) {
            return -1;
        } else {
            magasinDao.save(magasin);
            return 1;
        }
    }

    @Override
    public Magasin findByReference(String reference) {
        return magasinDao.findByReference(reference);
    }

    @Override
    public List<Magasin> findAll() {
        return magasinDao.findAll();
    }
    
    
    
    public MagasinDao getMagasinDao() {
        return magasinDao;
    }

    public void setMagasinDao(MagasinDao magasinDao) {
        this.magasinDao = magasinDao;
    }
}
