/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultestock.domain.rest.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Anas
 */
@FeignClient(name = "microservice-livraisons")
public interface LivraisonProxy {

    @GetMapping("/bilan/commande/{refcommande}/produit/{refproduit}")
    public Integer getLivreeBilan(@PathVariable("refcommande") String refCommande, @PathVariable("refproduit") String refProduit);

}
