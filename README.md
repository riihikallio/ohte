# DNS Tester

Sovellus testaa halutun DNS-palvelimen vasteaikaa. Käyttäjältä kysytään DNS-palvelmen IP-osoite ja selvitettävä domain nimi. Sovellus lähettää oikeanlaisen UDP-paketin palvelimelle ja kellottaa vastausajan. Testiajankohta, palvelin, nimi, vastaus ja vasteaika tallennetaan tietokantaan myöhempää vertailua varten.

## Dokumentaatio

[Vaatimusmäärittelyt](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/vaatimukset.md)

[Arkkitehtuuri](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/arkkitehtuuri.md)

[Tuntikirjanpito](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/tunnit.md)

## Komentorivitoiminnot

### Suorittaminen

Sovelluksen voi käynnistää komennolla

```bash
mvn compile exec:java -Dexec.mainClass=dnstester.Main
```

### Testaus

Testit suoritetaan komennolla

```bash
mvn test
```

Testikattavuusraportti luodaan komennolla

```bash
mvn test jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```bash
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _DNSTester-1.0-SNAPSHOT.jar_

<!-- ### JavaDoc

JavaDoc generoidaan komennolla

```bash
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_ -->

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/riihikallio/ohte/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```bash
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
