insert into gebruikers(voornaam, familienaam, straatnaam, huisnummer, gemeenteId, telefoonnummer, email, gebruikersnaam, paswoord)
values ('test', 'test', 'testStraat', '1', (select id from gemeenten where postcode = '3010'), '016121314', 'test@email.be', 'test', 'test');

insert into gebruikers(voornaam, familienaam, straatnaam, huisnummer, gemeenteId, telefoonnummer, email, gebruikersnaam, paswoord)
values ('test1', 'test1', 'testStraat', '1', (select id from gemeenten where postcode = '3210'), '016121314', 'test@email.be', 'test1', 'test1');