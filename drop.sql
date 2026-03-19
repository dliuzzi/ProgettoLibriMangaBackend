
    set client_min_messages = WARNING;

    alter table if exists libri 
       drop constraint if exists FKjkc86wutdx0snjvoahnxl8nc4;

    alter table if exists libri 
       drop constraint if exists FKkpota8cd31s16cm6ae34u6kw5;

    alter table if exists libri_categorie 
       drop constraint if exists FK66sjgxah7hsa2l4eccxdil560;

    alter table if exists libri_categorie 
       drop constraint if exists FKxrdupktuwtkj0bhopjod1ll1;

    drop table if exists autori cascade;

    drop table if exists case_editrici cascade;

    drop table if exists categorie cascade;

    drop table if exists libri cascade;

    drop table if exists libri_categorie cascade;

    drop table if exists messaggi_sistema cascade;

    drop table if exists utenti cascade;
