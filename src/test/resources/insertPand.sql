insert into panden(datumToegevoegd, typeWoning, naam, titel, straatnaam, huisnummer, gemeenteId, bouwjaar, oppervlakte, staatGebouw, aantalgevels, aantalslaapkamers,
            aantalbadkamers, aantaltoiletten, aantalverdiepingen, living, eetkamer, bureau, kelder, zolder, garage, tuin, terras, veranda,
            code_epc, dubbelebegelazing, kadastraalinkomen, vrijVanaf, startbod, huidigbod, gebruikerId, startdatum, einddatum, omschrijving)
values ('2019-10-27 12:00:00', 3, 'Herenhuis', 'Herenhuis', 'testStraat', '1', (select id from gemeenten where postcode = '3010'), 1985, 500, null, 2, 3, 1,
        1, 3, true, true, true, true, true, true, true, true, false, null, false, 1250, 'Bij de akte', 450000, 450000, null, '2019-10-27 13:00:00',
        '2019-11-27 13:00:00', 'Mooie woning');

insert into panden(datumToegevoegd, typeWoning, naam, titel, straatnaam, huisnummer, gemeenteId, bouwjaar, oppervlakte, staatGebouw, aantalgevels, aantalslaapkamers,
            aantalbadkamers, aantaltoiletten, aantalverdiepingen, living, eetkamer, bureau, kelder, zolder, garage, tuin, terras, veranda,
            code_epc, dubbelebegelazing, kadastraalinkomen, vrijVanaf, startbod, huidigbod, startdatum, einddatum, omschrijving)
values ('2019-10-27 12:00:00', 0, 'Studio', 'Ruime studio', 'testStraat', '1', (select id from gemeenten where postcode = '3210'), 2001, 220, null, 2, 3, 1,
        1, 3, true, true, true, true, true, true, true, true, false, null, false, 1250, 'Bij de akte', 250000, 250000, '2019-10-27 13:00:00',
        '2019-11-27 13:00:00', 'Mooie woning');