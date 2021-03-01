# Soloäventyr med editor

Projektet består av två program med grafiska gränssnitt. **Gamebook Player** låter dig spela textbaserade soloäventyr, och **Gamebook Editor** låter dig redigera sådana. Båda programmen kommunicerar med en MySQL-databas som innehåller all data för soloäventyret.

📑 [Post mortem](https://github.com/marcusbillman/prg2-gamebook/wiki/Post-mortem) (GitHub Wiki)

📑 [Grovplanering](https://github.com/marcusbillman/prg2-gamebook/wiki/Grovplanering) (GitHub Wiki)

📑 [Loggbok](https://github.com/marcusbillman/prg2-gamebook/wiki/Loggbok) (GitHub Wiki)

📊 [Klassdiagram](https://raw.githubusercontent.com/marcusbillman/prg2-gamebook/main/class-diagram.png) (fil i repository)

### Gamebook Player

![Gamebook Player Screenshot](https://raw.githubusercontent.com/marcusbillman/prg2-gamebook/main/gamebook-player.png)

Programmet kopplar upp sig mot databasen vid uppstart.

**Gränssnitt:**
- Den nuvarande sidans brödtext visas. 
- Under texten finns det en knapp för varje länk från den sidan i databasen.
- När man klickar på en knapp så tas man till rätt sida.

### Gamebook Editor

![Gamebook Player Screenshot](https://raw.githubusercontent.com/marcusbillman/prg2-gamebook/main/gamebook-editor.png)

Programmet kopplar upp sig mot databasen vid uppstart. Alla ändringar synkas direkt med databasen.

**Gränssnitt:**
- Man väljer sida i sidebaren till vänster (lista som visar varje sidas ID och början på brödtexten).
- Sidan kan redigeras i panelen till höger.
  - Sidans brödtext kan redigeras i den stora textrutan. Ändringarna sparas med en "Spara"-knapp.
  - Alla länkar (länktext, destinations-ID) från den nuvarande sidan visas i tabellen under.
    - Länkarnas text och destinations-ID kan ändras genom att dubbelklicka på länkens rad i tabellen
    - En länk kan tas bort genom att trycka på Delete med länkens rad markerad.
