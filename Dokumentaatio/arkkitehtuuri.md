# Arkkitehtuuri

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:

<img src="https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/paketointi.png>

Ylin kerros on vain tekninen kuori, jolla saadaan JAR-paketointi toimimaan. Toiminnallisesti kerroksia on kolme.

_ui_ sisältää käyttöliittymän, _domain_ sovelluslogiikan ja tuleva _dao_ testitulosten pysyvän tallennuksen.

## Sovelluslogiikka

<img src="https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/sekvenssi.png>
