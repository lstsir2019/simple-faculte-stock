/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.model.dao;

import com.faculte.simplefacultestock.domain.bean.Stock;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Anas
 */
public interface StockSearch {

    public List<Stock> findByCriteria(String reception, String commande, Date dateMin, Date dateMax);
}
