# Käyttöohje

Syötä testattavan palvelimen nimi tai IP-osoite DNS Server -kenttään.

Jos haluat testata koko nimiselvityksen, niin rastita Recursive query -valinta. Rekursiossa testattava palvelin kysyy tietoa edelleen muilta nimipalvelimilta, joten vasteaika kertoo koko DNS-järjestelmän nopeudesta. Ilman rekursiota saat testattua yhden palvelimen vasteaikaa.

Domain Name -kenttään syötetään haettava nimi. Nimen pitää olla kelvollinen DNS-nimi, eli ASCII-merkkejä, osat enintään 63 merkkiä pitkiä ja pistein erotettuina. Kokonaispituus ei saa ylittää 494 merkkiä. Muuten sillä ei ole merkitystä.

Paina Test-painiketta, niin sen alle tulee vasteaika millisekunneissa.

![Näyttö](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/screenshot.png)