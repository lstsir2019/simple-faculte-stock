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
   "http://localhost:4200/stock-api/stocks"

   @GetMapping("/magasin/{refmagasin}/commande/{refcommande}/produit/{refproduit}")
   public List<Stock> findStocksByMagasinAndCommandeAndProduit(@PathVariable("refmagasin") String refMagasin,@PathVariable("refcommande") String refCommande,@PathVariable("refproduit") String refProduit);
    
    @GetMapping("/commande/{refcommande}/produit/{refproduit}")
    public List<Stock> findStocksByCommandeAndProduit(String refCommande, String refProduit);
    
    @PutMapping("/magasin/{refmagasin}/reception/{refreception}/produit/{refproduit}/qtelivre/{qtelivre}")
   public int stockLivraison(@PathVariable String refreception,@PathVariable String refmagasin,@PathVariable String refproduit,@PathVariable Integer qtelivre);
```