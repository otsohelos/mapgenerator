# Viikkoraportti, viikko 2
## Mitä olen tehnyt tällä viikolla

Vietin ison osan viikon työajasta ns. konfiguraatiohelvetissä. En ole ennen tehnyt Java-React-projektia, ja tuntui että oli vaikea löytää materiaaleja, joissa ohjeistettaisiin asiaa. Onneksi olen tehnyt jo sen verran projekteja että tiedän konfiguraatiohelvetin olevan normaali ja ohimenevä vaihe, ja sain lopulta sekä backendin että frontendin käynnistymään kehitysympäristössä ja frontin näyttämään backilta tulevaa informaatiota.

Tämän jälkeen aloitin varsinaisen toiminnallisuuden koodaamisen. Sain nyt aikaan koodin, joka arpoo tietylle määrälle pisteitä satunnaisen sijainnin ja tuottaa tästä svg-kuvan, joka sitten näytetään käyttäjälle. Päädyin SVG:hen, koska sen mahdollistama pieni tiedostokoko lisää backendin ja frontendin kommunikaation nopeutta ja soveltuu ainakin toistaiseksi tuottamieni hyvin yksinkertaisten kuvien piirtämiseen. Tulevaisuudessa saatan harkita toista tiedostomuotoa, jos pääsen kovin monimutkaisiin kuviin.

Opiskelin myös lisää valitsemistani algoritmeista. Aloitin testien kirjoittamisen ja loin testausraportointitoiminnallisuuden Jacocon avulla.

## Miten ohjelma on edistynyt

Olen saanut ohjelman konfiguroitua ja backendin ja frontendin pohjta toimimaan. Olen aloittanut varsinaisen toiminnallisuuden tekemistä ja hahmotellut ohjelman luokkarakennetta. En ole vielä päässyt varsinaisten algoritmien toteuttamiseen, mutta voin aloittaa sen heti seuraavan viikon aluksi.

## Mitä opin tällä viikolla

Opin yhden tavan luoda Java/React-projekti ja saada se toimimaan. Opettelin perusasiat SVG-grafiikan luonnista. Opin myös lisää algoritmeista. Kertasin tietojani Javadocien, yksikkötestien ja testausraportoinnin suhteen.

## Mikä jäi epäselväksi tai tuottanut vaikeuksia

Ohjelman perusrakenteen luominen ja konfigurointi oli vaikeaa ja ajoittain epätoivoa herättävää vaikka päättyikin sitten voittoon. Tällä hetkellä päänvaivaa tuottaa itselleni vielä toistaiseksi melko vieras SVG-grafiikka ja se, miten martan tulevia vaiheita pystyy parhaiten sen avulla visualisoimaan.

## Mitä teen seuraavaksi

Seuraavaksi rupean kirjoittamaan itse Voronoin algoritmia ja miettimään sen tuottaman verkoston visualisointia SVG-grafiikaksi. Tuotan myös backendiin yksinkertaisen post-toiminnallisuuden, jolla käyttäjä voi pyytää generoimaan tuotetun kuvan uudelleen.