# Käyttöohje

Syötä testattavan palvelimen nimi tai IP-osoite DNS Server -kenttään.

Jos haluat testata koko nimiselvityksen, niin rastita Recursive query -valinta. Rekursiossa testattava palvelin kysyy tietoa edelleen muilta nimipalvelimilta, joten vasteaika kertoo koko DNS-järjestelmän nopeudesta. Ilman rekursiota saat testattua yhden palvelimen vasteaikaa erikseen.

Domain Name -kenttään syötetään haettava nimi. Nimen pitää olla kelvollinen DNS-nimi, eli ASCII-merkkejä, osat enintään 63 merkkiä pitkiä ja pistein erotettuina. Kokonaispituus ei saa ylittää 494 merkkiä. Muuten nimellä ei ole merkitystä.

Paina Test-painiketta, niin sen alle tulee vasteaika millisekunneissa.

![Näyttö](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/mainscreen.png)

## Historia

Painamalla Show History aukeaa palvelimen siihenastinen testihistoria omaan ikkunaansa. Vertailua varten voi avata useita ikkunoita eri palvelimille. Historiaikkunat eivät päivity, vaan ikkuna pitää sulkea ja avata uudelleen, jos tekee lisää testejä.

Punainen rasti tarkoittaa puuttuvaa vastausta. Pukki R-sarakkeessa tarkoittaa rekursiivista kyselyä.

![Historia](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/history.png)
