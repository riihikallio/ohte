# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus testaa halutun DNS-palvelimen vasteaikaa. Käyttäjältä kysytään DNS-palvelmen IP-osoite ja selvitettävä domain nimi. Sovellus lähettää oikeanlaisen UDP-paketin palvelimelle ja kellottaa vastausajan. Testiajankohta, palvelin ja vasteaika tallennetaan tietokantaan myöhempää vertailua varten.

Vasteajat voivat vaihdella paljonkin riippuen siitä, onko tieto jo haettuna testattavalla DNS-palvelimella vai haetaanko tieto rekursiivisesti muualta. Ilman rekursiota saadaan vastaus ilman taustahakuja.

## Perusversio

- Kenttä nimipalvelimen IP-osoitetta varten (Tehty)
  - Oletuksena esimerkiksi 8.8.8.8
- Kenttä selvitettävää nimeä varten (Tehty)
  - Oletuksena esimerkiksi www.example.com
- Nappi testin käynnistämiseksi (Tehty)
- Vastauksen tiedot tulevat samaan ikkunaan (Tehty)
- Aikakatkaisu siltä varalta, että vastausta ei tulekaan (Tehty)

## Toteutetut lisätoiminnot

- Valinta rekursiivista kyselyä varten (Tehty)
- Vastauksen tiedot tallennetaan tietokantaan (Tehty)
- Saman palvelimen aiemmat tulokset näytetään vertailua varten (Tehty)

## Jatkokehitys

- Jonkinlainen graafinen esitys

Histogrammi toimisi parhaiten ainakin yhden palvelimen tulosten esittämiseen. Pitäisi vielä ratkaista, miten kadonneet paketit saisi luontevasti mukaan histogrammiin. Toinen ratkaistava ongelma on, miten yhdistää rekursiiviset ja ei-rekursiiviset kyselyt samaan kuvaan vai pitäisikö niistä tehdä eri kuvat. Yksi ratkaisu olisi käyttää niiden osuuksille eri värejä, mutta se tekisi piirtämisestä aika monimutkaista.
