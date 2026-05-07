
    set client_min_messages = WARNING;

    alter table if exists dettagli_carrelli 
       drop constraint if exists FK7k85wo185hg25epkab6nkajjm;

    alter table if exists dettagli_carrelli 
       drop constraint if exists FK3boemb201bsycam1xkwpstmiu;

    alter table if exists dettagli_ordini 
       drop constraint if exists FKpr0qtebyb5j0wbpocvfvov98w;

    alter table if exists dettagli_ordini 
       drop constraint if exists FKlhv8a3wyac6v9emwbum9n2d8k;

    alter table if exists libri 
       drop constraint if exists FKjkc86wutdx0snjvoahnxl8nc4;

    alter table if exists libri 
       drop constraint if exists FKkpota8cd31s16cm6ae34u6kw5;

    alter table if exists libri_categorie 
       drop constraint if exists FK66sjgxah7hsa2l4eccxdil560;

    alter table if exists libri_categorie 
       drop constraint if exists FKxrdupktuwtkj0bhopjod1ll1;

    alter table if exists libri_preferiti 
       drop constraint if exists FKrrofl78gfhw6yo9exuo97usi2;

    alter table if exists libri_preferiti 
       drop constraint if exists FK4tiunf9dh4vym00x9r4n0nivn;

    alter table if exists ordini 
       drop constraint if exists FKqj5n4mdh5tc9qkj7hx54mvgi4;

    alter table if exists recensioni 
       drop constraint if exists FK5iqrc9rnh4h0iathjn84u8d2b;

    alter table if exists recensioni 
       drop constraint if exists FK3qtralkbontl617niblqxy543;

    drop table if exists autori cascade;

    drop table if exists case_editrici cascade;

    drop table if exists categorie cascade;

    drop table if exists dettagli_carrelli cascade;

    drop table if exists dettagli_ordini cascade;

    drop table if exists libri cascade;

    drop table if exists libri_categorie cascade;

    drop table if exists libri_preferiti cascade;

    drop table if exists messaggi_sistema cascade;

    drop table if exists ordini cascade;

    drop table if exists recensioni cascade;

    drop table if exists utenti cascade;
