/*la creation des tables et la mise en place des containtes se fera en premier  
*par hibernate se basant sur les annotations qu'on lui a fourni dans le model
*format des tables:
*les_programmeurs (programmeur_id , mot_de_passe, nom , numbureau , prenom , roles )
*les_consommations (consommation_id , nb_tasses, num_semaine , programmeur_id )
**/

INSERT INTO les_programmeurs VALUES (null,'FAVRE','FAVRE',412,'Jean Marie','PROF');
INSERT INTO les_programmeurs VALUES (null,'PARENT','PARENT',201,'Catherine','PROF');
INSERT INTO les_programmeurs VALUES (null,'ENE','ENE',208,'Christian','PROF');
INSERT INTO les_programmeurs VALUES (null,'WAILLE','WAILLE',401,'Philippe','PROF');
INSERT INTO les_programmeurs VALUES (null,'GENOUD','GENOUD',381,'Philippe','PROF,RESP');
INSERT INTO les_programmeurs VALUES (null,'SICARD','SICARD',404,'Pascal','PROF');
INSERT INTO les_programmeurs VALUES (null,'CAFFIAU','CAFFIAU',445,'Sybille','PROF');
INSERT INTO les_programmeurs VALUES (null,'CORTES-CORNAX','CORTES-CORNAX',395,'Mario','PROF');
INSERT INTO les_programmeurs VALUES (null,'GARDNER','GARDNER',446,'Virginia','PROF');

INSERT INTO les_consommations VALUES (null,9,25,1);
INSERT INTO les_consommations VALUES (null,7,25,2);
INSERT INTO les_consommations VALUES (null,9,25,3);
INSERT INTO les_consommations VALUES (null,5,25,4);
INSERT INTO les_consommations VALUES (null,10,25,5);
INSERT INTO les_consommations VALUES (null,12,25,6);
INSERT INTO les_consommations VALUES (null,10,25,7);
INSERT INTO les_consommations VALUES (null,13,25,8);
INSERT INTO les_consommations VALUES (null,5,25,9);

INSERT INTO les_consommations VALUES (null,13,26,1);
INSERT INTO les_consommations VALUES (null,10,26,2);
INSERT INTO les_consommations VALUES (null,4,26,3);
INSERT INTO les_consommations VALUES (null,9,26,4);
INSERT INTO les_consommations VALUES (null,7,26,5);
INSERT INTO les_consommations VALUES (null,11,26,6);
INSERT INTO les_consommations VALUES (null,9,26,7);
INSERT INTO les_consommations VALUES (null,11,26,8);
INSERT INTO les_consommations VALUES (null,4,26,9);

INSERT INTO les_consommations VALUES (null,12,27,1);
INSERT INTO les_consommations VALUES (null,13,27,2);
INSERT INTO les_consommations VALUES (null,10,27,3);
INSERT INTO les_consommations VALUES (null,7,27,4);
INSERT INTO les_consommations VALUES (null,3,27,5);
INSERT INTO les_consommations VALUES (null,19,27,6);
INSERT INTO les_consommations VALUES (null,15,27,7);
INSERT INTO les_consommations VALUES (null,10,27,8);
INSERT INTO les_consommations VALUES (null,6,27,9);

INSERT INTO les_consommations VALUES (null,8,28,1);
INSERT INTO les_consommations VALUES (null,14,28,4);
INSERT INTO les_consommations VALUES (null,7,28,5);
INSERT INTO les_consommations VALUES (null,5,28,8);
INSERT INTO les_consommations VALUES (null,6,28,9);
