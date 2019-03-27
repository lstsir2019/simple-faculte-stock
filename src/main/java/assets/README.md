StockVo
```json
StockVo:{
   "referenceReception",
    "referenceProduit",
    "referenceCommande",
    "qte",
    "qteDeffectueuse",
    "seuilAlert",
    "MagasinVo" }
```
StockGlobal
```json`
StockGlobal {
    "referenceProduit",
    "referenceCommande",
    "referenceMagasin",
    "qte";
}
```
MagasinVo
```json
  MagasinVo:{
   "reference",
   "libelle",
   "address",
   "description"
            }
```
## RestController 
```java
   path="http://localhost:8040/stock-api/stocks"

   @GetMapping("/commande/{refcommande}/produit/{refproduit}")
   public List<StockGlobal> findByCommandeAndProduit(@PathVariable String refcommande,@PathVariable String refproduit);

   @GetMapping("/magasin/{refmagasin}/commande/{refcommande}/produit/{refproduit}")
   public List<StockVo> findStocksByMagasinAndCommandeAndProduit(@PathVariable("refmagasin") String refMagasin,@PathVariable("refcommande") String refCommande,@PathVariable("refproduit") String refProduit);
    
   @GetMapping("/commande/{refcommande}/produit/{refproduit}")
   public List<StockVo> findStocksByCommandeAndProduit(String refCommande, String refProduit);
    
   @PutMapping("/magasin/{refmagasin}/reception/{refreception}/produit/{refproduit}/qtelivre/{qtelivre}")
   public int stockLivraison(@PathVariable String refreception,@PathVariable String refmagasin,@PathVariable String refproduit,@PathVariable Integer qtelivre);
```
