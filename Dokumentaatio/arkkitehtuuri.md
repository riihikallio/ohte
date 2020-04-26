# Arkkitehtuuri

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:

![Paketointi](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/paketointi.png)

Ylin kerros on vain tekninen kuori, jolla saadaan JAR-paketointi toimimaan. Toiminnallisesti kerroksia on kolme.

_ui_ sisältää käyttöliittymän, _domain_ sovelluslogiikan ja tuleva _dao_ testitulosten pysyvän tallennuksen.

## Sovelluslogiikka

### Luokat

Luokkarakenne on hyvin yksinkertainen. TestResult-luokka on vain tietue ilman toiminnallisuutta, joten sen kentät ovat suoraan julkisia eikä niille ole tehty gettereitä ja settereitä.

![Luokat](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/luokat.png)

### Toiminta

Kun käyttäjä painaa Test-painiketta suoritetaan testikysely. Itse paketin lähetystä ja vastaanottoa ei ole tässä kuvattu.

![Sekvenssi](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/sekvenssi.png)

### DNS UDP -paketti

Alla on DNS-UDP-kyselypaketin sisältökuvaus. Tässä sovelluksessa vain luodaan yksinkertainen DNS-kysely, ja kellotetaan vastaus. Vastausta ei tulkita sen kummemmin. Kysely-paketin sisällöstä suurin osa on kiinteitä kenttiä, joista esimerkki alinna. Vihreällä taustalla on merkitty tässä sovelluksessa käytetyt kentät. Ensin on yhden bitin Recursion Desired -kenttä (RD), jolla voi pyytää palvelinta selvittämään lopullisen IP-osoitteen asettamalla sen arvoksi 1. Itse haettava nimi tallennetaan QName-kenttään ASCII-tavuina niin, että jokaisen nimen osan edessä on osan pituus (enintään 63 merkkiä) ja lopuksi 0. QName-kentän pituus on siis muuttuva, muut kentät ovat vakiomittaisia.

Haettavan nimen tallennus poikkeaa Javan Stringin tallennuksesta, joten iso osa DNSTester-luokan sendQuery-metodista käsittelee sitä.

![Paketti](https://github.com/riihikallio/ohte/blob/master/Dokumentaatio/paketti.png)
