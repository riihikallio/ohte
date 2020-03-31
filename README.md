# DNS Tester

Sovellus testaa halutun DNS-palvelimen vasteaikaa. Käyttäjältä kysytään DNS-palvelmen IP-osoite ja selvitettävä domain nimi. Sovellus lähettää oikeanlaisen UDP-paketin palvelimelle ja kellottaa vastausajan. Testiajankohta, palvelin, nimi, vastaus ja vasteaika tallennetaan tietokantaan myöhempää vertailua varten.

## Dokumentaatio

[Vaatimusmäärittelyt](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/vaatimukset.md)

[Tuntikirjanpito](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/tunnit.md)

## Komentorivitoiminnot

### Suorittaminen

Sovelluksen voi käynnistää komennolla

```bash
mvn compile exec:java -Dexec.mainClass=dnstester.Main
```

### Testit

Testit voi suorittaa komennolla

```bash
mvn test
```

Testejä ei vielä ole, koska ei ole sovelluslogiikkaa :(

Testikattavuusraportti luodaan komennolla

```bash
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html
