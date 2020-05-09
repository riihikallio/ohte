# Testausdokumentti

Ohjelmaa on testattu sekä JUnitilla automatisoiduilla yksikkö- ja integraatiotesteillä sekä manuaalisesti tapahtuneilla järjestelmätason testeillä.

Testit tekevät todellisia DNS-kyselyjä ja edellyttävät siksi toimivaa verkkoyhteyttä Internetiin. Testeissä käytetään sekä Googlen avointa nimipalvelinta 8.8.8.8 että osoitetta 8.8.8.1, joka ei vastaa DNS-kyselyihin.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Suurin osa testeistä on [TesterTest](https://github.com/riihikallio/ohte/blob/master/DNSTester/src/test/java/dnstester/domain/TesterTest.java)-paketissa. Se pyrkii testaamaan sovelluksen varsinaista toimintaa eri vaihtoehdoilla ja simuloimaan tyypillisiä virhetilanteita. Niiden osalta testikattavuus on täydet 100% eli kaikki catch-lohkotkin on saatu testattua.

Testit käyttävät normaalia tietokantaa. Testeissä tietokantaan tallettuvat tulokset poistetaan testiajon päätyttyä @AfterClass-metodilla. Tämä oli lopulta yksinkertaisempi ratkaisu kuin luoda rinnakkainen tallennustoteutus. Samalla se mahdollistaa todellisen integraatiotestauksen, jota edustaa testi _historyGrows_.

### DAO-yksikkötestaus

[DBHistoryDAOTest](https://github.com/riihikallio/ohte/blob/master/DNSTester/src/test/java/dnstester/dao/DBHistoryDAOTest.java) tallentaa normaaliin tietokantaan, mutta testeissä käytetään aikarajan ylittävää vasteaikaa, mikä tekee rivien poistamisesta yksinkertaista @AfterClass-metodilla.

[HistoryRowTest](https://github.com/riihikallio/ohte/blob/master/DNSTester/src/test/java/dnstester/dao/HistoryRowTest.java) testaa vain settereitä ja gettereitä, mutta testauskattavuus näytti huonolta ilman niiden testausta.

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 85% ja haarautumakattavuus 100%

![Kattavuus](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/Kuvat/testaus.png)

Testaamatta jäivät DAO:n catch-lohkot, jotka liittyvät tietokanta- ja levyjärjestelmävirheisiin.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu sekä OSX- että Linux-ympäristöissä.

Sovellusta on testattu sekä tilanteissa, joissa ohjelma on luonut uuden tietokannan että käyttänyt olemassa olevaa tietokantaa.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/vaatimukset.md) ja [käyttöohjeen](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/ohje.md) kuvaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on syötekentät yritetty täyttää myös virheellisillä arvoilla kuten tyhjillä tai pelkillä pisteillä.
