-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gamebook2
-- ------------------------------------------------------
-- Server version	8.0.23-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `links`
--

DROP TABLE IF EXISTS `links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `links` (
  `link_id` int unsigned NOT NULL AUTO_INCREMENT,
  `text` varchar(64) DEFAULT NULL,
  `from_page_id` int unsigned DEFAULT NULL,
  `to_page_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`link_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `links`
--

LOCK TABLES `links` WRITE;
/*!40000 ALTER TABLE `links` DISABLE KEYS */;
INSERT INTO `links` VALUES (1,'Försök ta dig ut',1,2),(2,'Sitt kvar och lyssna på omgivingen',1,3),(3,NULL,2,3),(4,'Försök ta dig ut',3,4),(5,'Sitt kvar och lyssna på omgivingen',3,5),(6,'Försök ta dig ut',5,7),(7,'Fortsätt lyssna',5,6),(8,'Försök ta dig ut',6,7),(9,NULL,7,8),(10,'Hoppa i',8,9),(11,'\"Vänta lite, måste jag också?\"',8,10),(12,'Hoppa i',10,9),(13,'\"Eh...\"',10,11),(14,'Hoppa i',11,9),(15,NULL,9,12),(16,NULL,12,13),(17,'Dra dig tillbaka bakom mamma',13,14),(18,'Låtsas som om du inte hörde',13,15),(19,'Försök att tänka bort det',14,15),(20,'Gå till attack',14,18),(21,'Stirra chockat på ankan som bet dig',15,16),(22,'Spring hem',15,17),(23,'Spring hem',16,17),(24,'Bit tillbaka',16,18),(25,'Stå ut',17,101),(26,'Fly under natten',17,20),(27,NULL,18,20),(28,'Fly, ingen vill ändå ha dig',11,19),(29,NULL,19,20),(30,'Flyg över staketet',20,21),(31,NULL,21,101),(32,'Spring åt sidan',20,22),(33,NULL,22,23),(34,'Se dig omkring',23,24),(35,'Ta skydd under vattnet',23,25),(36,NULL,24,25),(37,NULL,25,26),(38,'Attackera hundarna',26,102),(39,'Spela död',26,27),(40,NULL,27,28),(41,NULL,28,29),(42,NULL,29,30),(43,'Flyg fram till svanarna',30,31),(44,'Stanna, du vill inte dö riktigt än',30,32),(45,NULL,31,33),(46,NULL,32,33),(47,NULL,33,103),(48,NULL,4,5);
/*!40000 ALTER TABLE `links` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pages`
--

DROP TABLE IF EXISTS `pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pages` (
  `page_id` int unsigned NOT NULL AUTO_INCREMENT,
  `body` text NOT NULL,
  `is_ending` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`page_id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pages`
--

LOCK TABLES `pages` WRITE;
/*!40000 ALTER TABLE `pages` DISABLE KEYS */;
INSERT INTO `pages` VALUES (1,'Du vaknar i ett instängt utrymme och vet inte var du är. Det är mörkt och trångt här inne.',0),(2,'Du försöker trycka dig ut, men väggarna vill inte ge vika.',0),(3,'Utifrån hörs ett knastrande ljud och några ljusa \"pip, pip.\"',0),(4,'Du trycker mot väggarna med all din kraft. Du lyckas göra en spricka och en stark, bländande ljusstrimma tränger in.',0),(5,'Du hör fler knastranden och pipanden.\n\"Världen är så stor!\" säger några små, glada röster.\n\"Tror ni det här är hela världen?\" svarar en vuxen röst. \"Den sträcker sig långt på andra sidan trädgården, ända in på prästens ägor, men där har jag aldrig varit. Nåja, är alla här? Nej, jag har inte alla. Det största ägget ligger där ännu. Hur länge skall detta pågå? Jag är så trött på det här!\"',0),(6,'\"Nå, hur går det?\" säger en annan, äldre röst. Det låter som en besökare.\n\"Det dröjer så länge med det där stora ägget. Det vill inte bli färdigt.\"\n\"Det där är ett kalkonägg! Jag blev också lurad en gång och den ungen var min största sorg och bedrövelse.\"\n\"Jag vill ändå ligga litet längre på det. Har jag nu legat så länge så kan jag ligga den sista tiden med.\"\n\"Varsågod,\" säger den gamla rösten och går sin väg.',0),(7,'Ägget går sönder. Ett starkt bländande ljus fyller hela ditt synfält medan du rullar ut i gräset. Långsamt justerar ögonen sig så att du kan se. Det är sommar och solsken. Intill dig står en häpnad anka och fem små ankungar. Omkring dig ser du stora kardborrblad, så höga att små människobarn kan stå raka under dem.',0),(8,'Ankan ser förskräckt på dig. \"Det var då en förskräckligt stor ankunge det här! Ingen av de andra ser ut så. Det kan väl inte vara en kalkonunge? Nåja, det ska vi snart komma underfund med. I vattnet ska han!\"\nFamiljen går ner till kanalen. Ni står på rad och en efter en plumsar ankungarna framför dig i vattnet.',0),(9,'Vattnet känns varmt och skönt. En liten svallvåg slår över ditt huvud, men du kommer snabbt upp igen och flyter bra. Du simmar runt några varv och ser på din mamma som ser förvånad med belåten ut.',0),(10,'Du tvekar och ankmamman ser besviket på dig. De andra ungarna skrattar åt dig och pekar.\n\"Fegis!\" ropar en.\n\"Vågar du inte, eller?\" säger en annan.',0),(11,'Dina syskon fortsätter skratta och mamman blir mer otålig. Pressen ökar. Hoppar du i vattnet mot din vilja?',0),(12,'\"Nej, det är inte någon kalkon. Se bara hur vackert han använder benen, hur rak han håller sig! Det är min egen unge.\"\nSå småningom börjar det bli sent.\n\"Kom med nu. Vi ska ut i världen och presentera er i ankgården. Men håll er alltid nära intill mig så att ingen trampar på er!\"\nFint och prydligt kliver alla ankungar upp ur vattnet – inklusive dig – och ni börjar allesammans att gå.',0),(13,'Ni kommer fram till ankgården.\n\"Rappa er nu! Inte ihop med benen. En väluppfostrad ankunge sätter ut benen långt ifrån varandra, liksom far och mor. Se så, nig nu med halsen och säg rapp!\"\nNågra ankor tittar på er och en av dem säger högt:\n\"Se där, nu blir vi ännu fler. Som om vi inte vore tillräckligt många. Och fy, så den där ena ungen ser ut!\"',0),(14,'Du gömmer dig bakom din mammas ena vinge. Du verkar dra till dig uppmärksamhet från fler av ankorna.\n\"Ja, han är då verkligen gräslig!\"',0),(15,'En anka flyger plötsligt fram och biter dig i nacken.',0),(16,'Du stirrar på förövaren.\n\"Låt honom vara!\" utbrister modern.\n\"Ja, men han är för stor och ovanlig. Därför skall han näpsas!\"\n\"Jag håller med att han inte är vacker, men han är en god varelse och simmar lika vackert som någon av de andra. Jag tänker att han växer till sig eller att han med tiden blir lite mindre. Han har legat för länge i ägget. Det är därför han ser lite underlig ut.\"',0),(17,'Gråtandes springer du ifrån gruppen, hemåt. Familjen följer snabbt efter dig. Där hemma fortsätter de andra ungarnas kommentarer.\n\"Han är för stor.\"\n\"Om katten ändå ville ta dig, ditt otäcka spektakel!\"\nDagen efter blir du biten av ankorna, hackad på av hönsen och sparkad av pigan som brukar ge djuren mat.',0),(18,'En flock av ursinniga ankor springer fram mot dig. Du springer för ditt liv ut ur ankgården, med ankorna efter dig.',0),(19,'När ingen ser smiter du kvickt upp ur vattnet och springer iväg längs strandkanten med tårar i ögonen.',0),(20,'Du kommer fram till ett staket vid en skogsdunge. Bakom staketet skymtar en avskild vik.',0),(21,'Din ena vinge fastnar i staketet. Du försöker desperat att ta dig loss men hinner inte innan ankflocken hinner ikapp.\nDu ser din familj i anksamlingen. De ser på dig med oerhörd besvikelse, vänder sig tvärt om och försvinner bort.',0),(22,'Du gör en tvär sväng åt vänster och hittar ett hål i staketet som du springer igenom. På andra sidan lägger du dig i vassen, dricker lite dyvatten och somnar.',0),(23,'Pang! Pang!\nDu vaknar av två kraftiga smällar som ekar över mossen. Några vildgäss faller döda ned i vassen framför dig.',0),(24,'Du tittar runt och tycks se några jägare med gevär i utkanten av skogen på andra sidan vattnet.',0),(25,'Vattnet omkring dig blir blodrött.\n\"Pang! Pang!\" låter det igen.\nHela skaror av vildgäss flög upp ur vassen. Den blå röken från gevären går som skyar in mellan de mörka träden och hänger långt ut över vattnet. Du sitter som förstenad av panik.',0),(26,'Ett plaskande ljud hörs bakom dig. När du vänder dig om ser du två förfärligt stora jakthundar vid strandkanten några få meter bort. De stirrar argsint på dig och visar sina tänder medan vass och rör vajar åt alla sidor. De närmar sig. Vilken sekund som helst slår de till.',0),(27,'Du vänder på huvudet för att få det under vingen och spelar död. Hundarnas tungor hänger långt ut ur halsen och ögonen lyser så helt ohyggligt. Den ena hunden sträcker sitt gap ända ned mot dig och du sluter ögonen.\nPlask! Hundarna vänder sig om springer sin väg.\n\"Ack, vilken tur!\" suckar du lättat. \"Jag är så ful att själva hunden inte vill bita mig.\"',0),(28,'Skräckslagen ligger du kvar alldeles stilla medan haglen susar i vassen. Skott på skott knallar och först långt fram på dagen blir det tyst. Du skyndar dig bort från mossen så snabbt du kan och springer över fält och äng i veckor.\nDet blir höst och löven i skogen blir gula och bruna. Det är kyligt och sådan blåst att det är svårt att ta sig fram utan att blåsa omkull.',0),(29,'En afton när solen gått ned och himlen badar i gula och röda färger, uppenbarar sig en hel flock stora vackra fåglar ut ur buskarna – du har aldrig sett några så vackra fåglar. De är alldeles skinande vita med långa smidiga halsar. Det är svanar. De utstöter ett helt underligt ljud, breder ut sina präktiga, långa vingar och flyger elegant bort från de kalla trakterna till varmare länder och öppna sjöar.\nNär du inte längre kan se de vackra fåglarna känner du att du blivit alldeles underlig till mods.\nVintern kommer, så kall, så kall. Du måste simma omkring i vattnet för att hindra det att frysa, men varje natt blir hålet som du simmar i allt mindre och mindre. På något fantastiskt sätt uthärdar du.',0),(30,'Solen börjar åter skina varmt, lärkorna sjunger och den härliga våren kommer. Du lyfter dina vingar och flyger till en stor trädgård där äppelträden står i blom och där syrenerna doftar ljuvligt.\nUt ur de täta buskarna framför dig kommer tre vackra vita svanar. De blåser upp fjädrarna och flyter så lätt på vattnet. Du känner igen de präktiga djuren och slås av ett underligt vemod.\n\"Jag vill flyga fram till dem, de kungliga fåglarna, men de kommer hugga ihjäl mig för att jag är så ful och vågar närma mig dem. Men det gör det samma! Bättre att dödas av dem än att nypas av ankorna, hackas av hönsen, sparkas av pigan och slita ont om vintern.\"',0),(31,'Med allt ditt mod samlat flyger du fram och landar framför svanarna som tittar på dig och skjuter med uppblåsta fjädrar.\n\"Döda mig bara!\" säger du uppgivet, böjer ditt huvud ned mot vattenbrynet och väntar döden.',0),(32,'Förbluffad men sorgsen skådar du svanarna. De tittar tillbaka på dig med en underlig blick och plötsligt flyger en svan fram till dig och landar bredvid.\n\"Vad gör du här? Vi är ju på väg söderut.\"\nDu blir genast förvirrad och ser dig omkring efter någon annan svan som de egentligen pratar med.',0),(33,'Men vad ser du i det klara vattnet? I din egen spegelbild.\nDu är inte längre en klumpig, svartgrå fågel, ful och stygg. Du är själv en svan.',0),(101,'Familjen har fått nog av dig och överger dig för att du är så ful.\nDitt äventyr slutar här.',1),(102,'Du kastar dig mot en av hundarna med näbben i vädret. Det tar tvärt emot och i ett nafs blir du uppäten.\nDitt äventyr slutar här.',1),(103,'Det gör inget att vara född i ankgården om man bara legat i ett svanägg.\nAll den nöd och alla de vedervärdigheter som du har utstått lönar sig äntligen. De stora svanarna simmar runt omkring dig och stryker dig med näbben. Ut i trädgården kommer några små barn som jublar, klappar händerna och dansar runt.\n\"Där är en ny!\" De springer efter sina föräldrar och det kastas bröd och bakelser i vattnet. \"Den nya är den vackraste! Så ung och så söt!\"\nOch de gamla svanarna bugar sig för dig.\nBlygt sticker du huvudet bakom vingarna. Allt för lycklig, men inte alls stolt. Ett gott hjärta blir aldrig stolt. Dina fjädrar blåser upp sig, den smidiga halsen höjer sig och ur hjärtat jublar du:\n\"Så mycket lycka drömde jag inte om, då jag var den fula ankungen!\"',1);
/*!40000 ALTER TABLE `pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'gamebook2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-10 16:38:05
