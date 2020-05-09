# DNS Tester

Sovellus testaa halutun DNS-palvelimen vasteaikaa. Käyttäjältä kysytään DNS-palvelmen IP-osoite ja selvitettävä domain nimi. Sovellus lähettää oikeanlaisen UDP-paketin palvelimelle ja kellottaa vastausajan. Testiajankohta, palvelin, ja vasteaika tallennetaan tietokantaan myöhempää vertailua varten.

## Dokumentaatio

[Käyttohje](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/ohje.md)

[Vaatimusmäärittelyt](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/vaatimukset.md)

[Arkkitehtuuri](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/testaus.md)

[Tuntikirjanpito](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/tunnit.md)

## Releaset

[Loppupalautus](https://github.com/riihikallio/ohte/releases/tag/v1.7)

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

Testit lähettävät DNS-kyselyjä Googlen palvelimelle, joten testien läpimenoa varten koneella pitää olla Internet-yhteys.

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

Paksun JAR:n generointi kolmelle käyttöjärjestelmälle (Linux, macOS ja Windows) tuottaa paljon varoituksia, joista en päässyt eroon. JAR kuitenkin toimii. Vaihtoehtona olisi tehdä jokaiselle käyttöjärjestelmälle oma JAR, mutta tässä tapauksessa paksu JAR ei paisunut kohtuuttoman kokoiseksi.

### JavaDoc

JavaDoc generoidaan komennolla

```bash
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/riihikallio/ohte/blob/master/DNSTester/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```bash
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

Tällä hetkellä siellä on pari virhettä Tester-luokassa, metodit ovat hieman liian pitkiä. Ne voisi vielä pilkkoa alimetodeiksi, mutta samalla ohjelmalogiikka monimutkaistuisi.
