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

## Jatkokehitys

- Valinta rekursiivista kyselyä varten (Tehty)
- Vastauksen tiedot tallennetaan tietokantaan (Aloitettu)
- Saman palvelimen aiemmat tulokset näytetään vertailua varten
- Jonkinlainen graafinen esitys
