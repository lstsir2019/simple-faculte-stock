/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.rest;

import com.faculte.simplefacultestock.domain.bean.Magasin;
import com.faculte.simplefacultestock.domain.bean.Stock;
import com.faculte.simplefacultestock.domain.model.service.MagasinService;
import com.faculte.simplefacultestock.domain.rest.converter.AbstractConverter;
import com.faculte.simplefacultestock.domain.rest.vo.MagasinVo;
import com.faculte.simplefacultestock.domain.rest.vo.StockVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@RestController
@RequestMapping("/magasin-api/magasins")
@CrossOrigin(origins = "http://localhost:4200")
public class MagasinRest {

    @Autowired
    private MagasinService magasinService;
    @Autowired
    @Qualifier("magasinConverter")
    private AbstractConverter<Magasin, MagasinVo> magasinConverter;

    @PostMapping("/")
    public int create(@RequestBody Magasin magasin) {
        return magasinService.create(magasin);
    }

    @GetMapping("/")
    public List<Magasin> findAll() {
        return magasinService.findAll();
    }

    @PutMapping("/")
    public MagasinVo upDate(@RequestBody MagasinVo magasinVo) {
        return magasinConverter.toVo(magasinService.update(magasinConverter.toItem(magasinVo)));
    }

    public MagasinService getMagasinService() {
        return magasinService;
    }

    public void setMagasinService(MagasinService magasinService) {
        this.magasinService = magasinService;
    }

    public AbstractConverter<Magasin, MagasinVo> getMagasinConverter() {
        return magasinConverter;
    }

    public void setMagasinConverter(AbstractConverter<Magasin, MagasinVo> magasinConverter) {
        this.magasinConverter = magasinConverter;
    }

}
