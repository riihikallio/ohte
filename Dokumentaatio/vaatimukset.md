# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus testaa halutun DNS-palvelimen vasteaikaa. Käyttäjältä kysytään DNS-palvelmen IP-osoite ja selvitettävä domain nimi. Sovellus lähettää oikeanlaisen UDP-paketin palvelimelle ja kellottaa vastausajan. Testiajankohta, palvelin, nimi, vastaus ja vasteaika tallennetaan tietokantaan myöhempää vertailua varten.

Vasteajat voivat vaihdella paljonkin riippuen siitä, onko tieto jo testattavalla DNS-palvelimella vai haetaanko tieto muualta. Heti perään toistettuna voidaan testata vasteaikaa yksiselitteisemmin.

## Perusversio

- Kenttä nimipalvelimen IP-osoitetta varten
  - Oletuksena esimerkiksi 8.8.8.8
- Kenttä selvitettävää nimeä varten
  - Oletuksena esimerkiksi www.example.com
- Nappi testin käynnistämiseksi
- Vastauksen tiedot tulevat samaan ikkunaan
- Aikakatkaisu siltä varalta, että vastausta ei tulekaan

## Jatkokehitys

- Vastauksen tiedot tallennetaan tietokantaan
- Saman palvelimen aiemmat tulokset näytetään vertailua varten
- Yleinen vertailu eri palvelinten kesken
- Jonkinlainen graafinen esitys
