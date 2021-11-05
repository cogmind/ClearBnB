-- --------------------------------------------------------
-- Värd:                         127.0.0.1
-- Serverversion:                8.0.26 - MySQL Community Server - GPL
-- Server-OS:                    Win64
-- HeidiSQL Version:             11.3.0.6369
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumpar data för tabell clearbnb.booking: ~3 rows (ungefär)
INSERT INTO `booking` (`booking_id`, `listing_id`, `user`, `fee`, `start_date`, `end_date`, `guests`, `cancelled`, `available_funds`) VALUES
	(4, 1, 1, 40000, '2021-11-04', '2021-11-04', 1, 0, 0),
	(6, 1, 1, 46000, '2021-11-06', '2021-11-06', 1, 0, 0),
	(7, 1, 1, 46000, '2021-11-03', '2021-11-03', 1, 0, 4000000);

-- Dumpar data för tabell clearbnb.listing: ~10 rows (ungefär)
INSERT INTO `listing` (`listing_id`, `version`, `audited_datetime`, `owner_id`, `title`, `description`, `image_url`, `location`, `price`, `guests`, `start_date`, `end_date`) VALUES
	(1, 1, '2021-10-14 19:26:06', 1, 'Empire Loft: in the shadow of Colosseo', 'Comfortable furnishings: living/dining room with 1 sofabed and TV. 1 double bedroom. Open walk-through room with 1 french bed. Kitchenette (4 hot plates, freezer). Shower/bidet/WC. Heating, air-conditioning. Parquet floors. Facilities: washing machine, iron.', 'https://a0.muscache.com/im/pictures/061581b3-f19e-4536-820e-9d62e7d5a41f.jpg?im_w=1200', 'Rome', 40000, 5, '2021-11-11', '2021-12-01'),
	(2, 1, '2021-10-14 19:28:15', 1, 'Lovely apartment overlooking the Pantheon square', 'Delizioso Loft, nel cuore pulsante del Rione Monti, a circa 200 metri dal Colosseo e dai Fori Imperiali. Il Loft è all\'interno di un palazzo d\'epoca immerso nel verde. E\' caratterizzato dalle tipiche travi in legno e da pavimenti originali di fine secolo. L\'empire loft è dotato di tutti i servizi ed è ideale per una fuga romantica nella città Eterna! Il quartiere Monti è uno dei più antichi e caratteristici di Roma, ricco di ristoranti tipici e negozi hand-made. Vivi la vera esperienza Romana!', 'https://a0.muscache.com/im/pictures/fc87c4e7-1f5d-45b1-b601-aa9d1f90329f.jpg?im_w=960', 'Rome', 100000, 5, '2021-11-01', '2021-12-01'),
	(3, 1, '2021-11-05 17:21:57', 2, 'Тверская & Маяковская студия, центр', 'Your 5* accommodation in the heart of Moscow, just 200 m from Tverskaya street /Mayakovskaya 🚇, 20 min walk to Red Square. CBD area. Very quiet courtyard in buzzing city centre. Self-check in. Sound-proof walls, heated floors, drinking water filters, high quality mattress & bed linen & towels, Nespresso& coffee machine, fast WiFi, smart TV &170 channels, huge storage, a/c, washer&dryer - all you need in one place. Lots of restaurants, grocery shops, museums, concert halls, theaters and parks.', 'https://a0.muscache.com/im/pictures/cd0da807-9295-495d-8c8b-eae139f15deb.jpg?im_w=1200', 'Moscow', 40000, 2, '2021-11-01', '2021-12-01'),
	(4, 1, '2021-11-05 17:23:56', 2, 'Светлое и уютное жилье в уникальной локации', 'Ljusa och mysiga lägenheter i ett unikt läge: den historiska stadskärnan i Moskva, i omedelbar närhet av Trefaldighetskyrkan. De gamla gatorna och ringandet av klockorna kommer att skapa en outplånlig känsla av närhet mellan dig och den historiska, atmosfäriska och ursprungliga centrum av Moskva.', 'https://a0.muscache.com/im/pictures/75375685-5864-4678-8c41-7fe19830970c.jpg?im_w=1200', 'Moscow', 40000, 2, '2021-11-01', '2021-12-01'),
	(5, 1, '2021-11-05 17:26:01', 3, 'Studio Apartment close to Center & Airport JACUZZI', 'Private studio apartment in a traditional Finnish wooden house. Shared Jacuzzi/Hot tub (own private turn 20:00-21:00 every night with slippers and housecoats) Quick access to airport & city center by train. Safe/peaceful neighborhood and free parking in our fenced yard. Keyless entry - late arrivals welcome! Nice comfortable beds and excellent air circulation due to the high ceilings. We provide SAFETY, A GOOD NIGHT SLEEP, CLEANLINESS, FRIENDLINESS & CONVENIENCE (Tea/coffee complimentary)', 'https://a0.muscache.com/im/pictures/eaed52db-a27e-47de-a3bf-dea9d76ff265.jpg?im_w=1200', 'Helsinki', 40000, 5, '2021-11-01', '2021-12-01'),
	(6, 1, '2021-11-05 17:27:18', 3, 'Central location with small terrace and great view', 'This top 8th floor sunny studio flat is located in the centre of Helsinki in a 1940 built house. Studio is 37 square meter big and has a small terrace over looking the roofs of West Helsinki all the way to Lauttasaari and west harbour. Building has elevator.', 'https://a0.muscache.com/im/pictures/ad41836c-84d9-4002-8b3e-f5aea5f4e44c.jpg?im_w=1200', 'Helsinki', 50000, 3, '2021-10-05', '2021-11-05'),
	(7, 1, '2021-11-05 17:29:33', 4, 'Comfy studio apartment, walking distance to city', 'Bekväm och praktisk liten lägenhet med pentry och eget badrum. Lägenheten ligger på översta våningen i ett 1920-talshus i charmig gammal stil.', 'https://a0.muscache.com/im/pictures/cf6ae167-07ac-4d26-a4f2-eee227e16919.jpg?im_w=1200', 'Borås', 20000, 2, '2021-11-05', '2022-01-20'),
	(8, 1, '2021-11-05 17:31:17', 5, 'Private area , small cooking space, Borås', 'Liten "lägenhet" i suterrängplan i villa. Egen ingång och tillgång till parkering. Stora fönster som gör rummet ljust med utsikt över sjön.', 'https://a0.muscache.com/im/pictures/496b840a-65a1-47fa-a5fd-96311509cc3f.jpg?im_w=720', 'Borås', 20000, 1, '2021-12-01', '2022-04-22'),
	(9, 1, '2021-11-05 17:32:50', 6, 'Mysig stuga med utsikt över Dundet', 'Mysig och modern stuga ett stenkast från Dundret.', 'https://a0.muscache.com/im/pictures/d868260d-809c-43ea-880b-88b72c44c3e9.jpg?im_w=1200', 'Gällivare', 10000, 3, '2022-02-26', '2022-04-11'),
	(10, 1, '2021-11-05 17:35:43', 7, 'Cosy riverside house close to Icehotel', 'Welcome to our house! It\'s close to the Torneriver and forest. Calm and quiet area. I\'ll be happy to help with questions or places worth a visit in the surroundings. I respect privacy.', 'https://a0.muscache.com/im/pictures/027487b1-cb13-4e8b-a39c-552080fe3cdc.jpg?im_w=1200', 'Kiruna', 5000, 2, '2021-12-01', '2022-04-01');

-- Dumpar data för tabell clearbnb.message: ~0 rows (ungefär)

-- Dumpar data för tabell clearbnb.review: ~4 rows (ungefär)
INSERT INTO `review` (`review_id`, `version`, `timestamp`, `author_id`, `target_id`, `comment`, `rating`) VALUES
	(1, 1, '2021-11-05 22:37:38', 2, 3, 'Smiling could easily be misinterpreted for showing your teeth to someone because they said something that made you happy. I\'m in a band that does Metallica covers with our private parts - it\'s called Myphallica. Petrovache. Tim Horton was a hockey player but is the name of a coffee chain, which means my dream of a goat sanctuary being my legacy is not unrealistic. If you wake up with a giant zit, you are really facing your fears when you look in the mirror. Are there Out-of-Stock photos? Gafuffle.', 1),
	(2, 1, '2021-11-05 22:46:25', 2, 4, 'We say we are walking the dog, but the dog always leads. Are there Out-of-Stock photos? Gafuffle. If you wake up with a giant zit, you are really facing your fears when you look in the mirror. Tim Horton was a hockey player but is the name of a coffee chain, which means my dream of a goat sanctuary being my legacy is not unrealistic. Tim Horton was a hockey player but is the name of a coffee chain, which means my dream of a goat sanctuary being my legacy is not unrealistic.', 5),
	(3, 1, '2021-11-05 22:48:31', 3, 6, 'Do we make money or does money make us? Chezwich. If you were a member of the Bloods and became paralyzed do you then become a member of the Crips?. A tagline for an airline: Take the High Road. I started a sensory deprivation chamber business - it involves really dark curtains, ear plugs, and a sleeping mask. I have a moral code, but I haven\'t figured out how to read it yet.', 3),
	(4, 1, '2021-11-05 22:49:11', 1, 7, 'You know the Grammys are a joke when Future doesn\'t win Best Everything. Most streets are two-way streets...why does that make love so special?. Curling is the best sport named after something you do to your hair. Rumour has it targeted online advertising was developed because the internet was upset that you could read it but it couldn\'t read you. Trepidelicious. Are there Out-of-Stock photos?', 4);

-- Dumpar data för tabell clearbnb.user: ~20 rows (ungefär)
INSERT INTO `user` (`user_id`, `username`, `password`, `email`, `balance`) VALUES
	(1, 't', '889729e8d2d8864a59db1e195ad67c76949578ff2b4637388564a81dd68fc01e', 't', 3954000),
	(2, 'a', '80084bf2fba02475726feb2cab2d8215eab14bc6bdd8bfb2c8151257032ecd8b', 'a', 5000),
	(3, 'b', 'b039179a8a4ce2c252aa6f2f25798251c19b75fc1508d9d511a191e0487d64a7', 'b', 40000),
	(4, 'c', '263ab762270d3b73d3e2cddf9acc893bb6bd41110347e5d5e4bd1d3c128ea90a', 'c', 40000),
	(5, 'd', '4ce8765e720c576f6f5a34ca380b3de5f0912e6e3cc5355542c363891e54594b', 'd', 40000),
	(6, 'e', '42538602949f370aa331d2c07a1ee7ff26caac9cc676288f94b82eb2188b8465', 'e', 40000),
	(7, 'f', 'a0b37b8bfae8e71330bd8e278e4a45ca916d00475dd8b85e9352533454c9fec8', 'f', 40000),
	(8, 'g', '9f2898da52dedaca29f05bcac0c8e43e4b9f7cb5707c14cc3f35a567232cec7c', 'g', 40000),
	(9, 'h', '5a082c81a7e4d5833ee20bd67d2f4d736f679da33e4bebd3838217cb27bec1d3', 'h', 40000),
	(10, 'i', 'bf872d20c4ef70ab19c9d413f172ce399a30ddeca771658561b1443111069c9e', 'i', 40000),
	(11, 'j', 'f35e560e05de779f2669b9f513c2a7ab81dfeb100e2f4ee1fb17354bfa2740ca', 'j', 40000),
	(12, 'k', '7c712596135d13a73c0dd366151b9440f3e9072371b436371107f12b3d850180', 'k', 40000),
	(13, 'l', '3e5e3e723953551a2ba2e7c5584bcc4ce407414af1ab2569051e7c9bfa33164d', 'l', 40000),
	(14, 'm', '1b42f48aa4371867a7c51ae6f237f35626e02c12eefa592614e1b10af7769370', 'm', 40000),
	(15, 'n', '8ee93ceda95bbe450f7fb53a700c56dfac4387e48eb127881a2a68727bc7810c', 'n', 40000),
	(16, 'o', '12c6debe02a118f89049700e723650d269838a76024a826607b163bc2a237031', 'o', 40000),
	(17, 'p', '14c68e20d8ddb4dbd248ed14bdb2012cfcee23530af0f71328009d1e90bb36ac', 'p', 40000),
	(18, 'q', '8a5e1d339fafc39350fd8cf1d7ca7982091c27f6b77f75bd4ddab3df425b4f8c', 'q', 40000),
	(19, 'r', 'f695d5fe6e2c67fe29ccf09341c29ad58154c568c5917a919c31936a3c96d607', 'r', 40000),
	(20, 's', 'cdc56a5028e51232cb28194fb1eb93e7014d60fb7afb447a49a1e1aaa640c9a4', 's', 40000);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
