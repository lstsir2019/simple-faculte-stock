/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.model.dao.search;

import com.faculte.simplefacultestock.commun.util.SearchUtil;
import com.faculte.simplefacultestock.domain.bean.Stock;
import com.faculte.simplefacultestock.domain.model.dao.StockSearch;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anas
 */
@Repository
public class StockSearchImpl implements StockSearch {

    //Enti
    private EntityManager entityManager;

    public StockSearchImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Stock> findByCriteria(String reception, String commande, Date dateMin, Date dateMax) {
        String query = "SELECT s FROM Stock s where 1=1 ";
        query += SearchUtil.addConstraint("s", "referenceReception", "LIKE", reception);
        query += SearchUtil.addConstraint("s", "referenceCommande", "LIKE", commande);
        query += SearchUtil.addConstraintMinMaxDate("s", "dateReception", dateMin, dateMax);
        return entityManager.createQuery(query).getResultList();
    }
}
