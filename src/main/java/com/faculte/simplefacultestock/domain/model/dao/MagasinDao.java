/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.model.dao;


import com.faculte.simplefacultestock.domain.bean.Magasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anas
 */
@Repository
public interface MagasinDao extends JpaRepository<Magasin, Long> {

    public Magasin findByReference(String reference);
}
