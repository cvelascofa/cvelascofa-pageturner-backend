TRUNCATE TABLE authors CASCADE;
TRUNCATE TABLE genres CASCADE;
TRUNCATE TABLE edition_types CASCADE;
TRUNCATE TABLE languages CASCADE;
TRUNCATE TABLE challenges CASCADE;

INSERT INTO authors (id, name, bio, website, followers_count, created_at, updated_at) VALUES
(1, 'S. M. Alcaine', 'Spanish writer and audiovisual translator Sònia Meseguer Alcaine writes romantic novels under this pen name. She studied Translation and Audiovisual Translation in Barcelona and published her first traditional romance in 2023.', NULL, 250000, NOW(), NOW()),
(2, 'Stephen King', 'Stephen King is an American author of horror, supernatural fiction, suspense, and fantasy novels.', 'https://stephenking.com', 1200000, NOW(), NOW()),
(3, 'J.K. Rowling', 'British author best known for the Harry Potter series.', 'https://www.jkrowling.com', 1500000, NOW(), NOW()),
(4, 'George R.R. Martin', 'George R. R. Martin is the author of the epic fantasy series A Song of Ice and Fire.', 'https://georgerrmartin.com', 1100000, NOW(), NOW()),
(5, 'Margaret Atwood', 'Canadian poet, novelist, literary critic, and inventor. Best known for "The Handmaid’s Tale".', NULL, 520000, NOW(), NOW()),
(6, 'Neil Gaiman', 'English author of short fiction, novels, comic books, graphic novels, nonfiction, and films.', 'https://neilgaiman.com', 980000, NOW(), NOW()),
(7, 'Haruki Murakami', 'Japanese writer known for his blending of fantasy and reality.', NULL, 870000, NOW(), NOW()),
(8, 'Isabel Allende', 'Chilean-American writer. Allende is known for novels such as "The House of the Spirits".', 'https://isabelallende.com', 460000, NOW(), NOW()),
(9, 'Toni Morrison', 'American novelist known for her examination of the Black experience.', NULL, 750000, NOW(), NOW()),
(10, 'Brandon Sanderson', 'American fantasy and science fiction writer known for "Mistborn" and "Stormlight Archive".', 'https://www.brandonsanderson.com', 690000, NOW(), NOW()),
(11, 'Agatha Christie', 'British writer known for her 66 detective novels and 14 short story collections.', NULL, 1300000, NOW(), NOW()),
(12, 'Dan Brown', 'American author best known for his thriller novels including "The Da Vinci Code".', 'https://danbrown.com', 870000, NOW(), NOW()),
(13, 'James Patterson', 'American author known for thriller novels and a prolific publishing career.', 'https://jamespatterson.com', 1600000, NOW(), NOW()),
(14, 'Suzanne Collins', 'American television writer and author best known for "The Hunger Games".', NULL, 800000, NOW(), NOW()),
(15, 'Ernest Hemingway', 'American novelist and short-story writer, widely regarded as one of the great American authors.', NULL, 720000, NOW(), NOW()),
(16, 'F. Scott Fitzgerald', 'American novelist, widely regarded as one of the greatest American writers of the 20th century.', NULL, 630000, NOW(), NOW()),
(17, 'Jane Austen', 'English novelist known primarily for her six major novels.', NULL, 920000, NOW(), NOW()),
(18, 'J.R.R. Tolkien', 'English writer, poet, philologist, and academic. Author of "The Lord of the Rings".', NULL, 1500000, NOW(), NOW()),
(19, 'Leo Tolstoy', 'Russian writer, widely regarded as one of the greatest authors of all time.', NULL, 540000, NOW(), NOW()),
(20, 'Charles Dickens', 'English writer and social critic, created some of the world’s best-known fictional characters.', NULL, 890000, NOW(), NOW()),
(21, 'Mark Twain', 'American writer, humorist, entrepreneur, publisher, and lecturer.', NULL, 790000, NOW(), NOW()),
(22, 'Virginia Woolf', 'English writer, considered one of the most important modernist 20th-century authors.', NULL, 600000, NOW(), NOW()),
(23, 'Emily Dickinson', 'American poet, known for her reclusive lifestyle and posthumously famous poems.', NULL, 400000, NOW(), NOW()),
(24, 'Gabriel García Márquez', 'Colombian novelist and Nobel Prize winner, known for magical realism.', NULL, 950000, NOW(), NOW()),
(25, 'H.P. Lovecraft', 'American writer known for his weird and horror fiction.', NULL, 710000, NOW(), NOW()),
(26, 'Philip K. Dick', 'American writer known for his science fiction works exploring philosophical and political themes.', NULL, 680000, NOW(), NOW());

TRUNCATE TABLE publishers CASCADE;

SELECT setval(pg_get_serial_sequence('authors', 'id'), COALESCE((SELECT MAX(id) FROM authors), 1));

INSERT INTO genres (id, name, created_at, updated_at) VALUES
(1, 'Fantasy', NOW(), NOW()),
(2, 'Science Fiction', NOW(), NOW()),
(3, 'Mystery', NOW(), NOW()),
(4, 'Thriller', NOW(), NOW()),
(5, 'Romance', NOW(), NOW()),
(6, 'Horror', NOW(), NOW()),
(7, 'Historical Fiction', NOW(), NOW()),
(8, 'Biography', NOW(), NOW()),
(9, 'Self-Help', NOW(), NOW()),
(10, 'Poetry', NOW(), NOW()),
(11, 'Adventure', NOW(), NOW()),
(12, 'Drama', NOW(), NOW()),
(13, 'Classic', NOW(), NOW()),
(14, 'Crime', NOW(), NOW()),
(15, 'Young Adult', NOW(), NOW()),
(16, 'Graphic Novel', NOW(), NOW()),
(17, 'Philosophy', NOW(), NOW()),
(18, 'Religion', NOW(), NOW()),
(19, 'Travel', NOW(), NOW());

SELECT setval(pg_get_serial_sequence('genres', 'id'), (SELECT MAX(id) FROM genres));

INSERT INTO edition_types (id, name, created_at, updated_at) VALUES
 (1, 'Hardcover', NOW(), NOW()),
 (2, 'Paperback', NOW(), NOW()),
 (3, 'Ebook', NOW(), NOW()),
 (4, 'Audiobook', NOW(), NOW()),
 (5, 'Special Edition', NOW(), NOW()),
 (6, 'Collector''s Edition', NOW(), NOW()),
 (7, 'Mass Market Paperback', NOW(), NOW()),
 (8, 'Leather Bound', NOW(), NOW()),
 (9, 'Library Edition', NOW(), NOW()),
 (10, 'Annotated Edition', NOW(), NOW());

SELECT setval(pg_get_serial_sequence('edition_types', 'id'), (SELECT MAX(id) FROM edition_types));

INSERT INTO languages (id, name, code, created_at, updated_at) VALUES
(1, 'English', 'EN', NOW(), NOW()),
(2, 'Spanish', 'ES', NOW(), NOW()),
(3, 'French', 'FR', NOW(), NOW()),
(4, 'German', 'DE', NOW(), NOW()),
(5, 'Italian', 'IT', NOW(), NOW()),
(6, 'Portuguese', 'PT', NOW(), NOW()),
(7, 'Catalan', 'CA', NOW(), NOW()),
(8, 'Dutch', 'NL', NOW(), NOW()),
(9, 'Russian', 'RU', NOW(), NOW()),
(10, 'Chinese', 'ZH', NOW(), NOW());

SELECT setval(pg_get_serial_sequence('languages', 'id'), (SELECT MAX(id) FROM languages));


INSERT INTO publishers (id, name, website, country, created_at, updated_at) VALUES
(1, 'Penguin Random House', 'https://www.penguinrandomhouse.com', 'USA', NOW(), NOW()),
(2, 'HarperCollins', 'https://www.harpercollins.com', 'USA', NOW(), NOW()),
(3, 'Simon & Schuster', 'https://www.simonandschuster.com', 'USA', NOW(), NOW()),
(4, 'Hachette Livre', 'https://www.hachette.com', 'France', NOW(), NOW()),
(5, 'Macmillan Publishers', 'https://us.macmillan.com', 'UK', NOW(), NOW()),
(6, 'Bloomsbury Publishing', 'https://www.bloomsbury.com', 'UK', NOW(), NOW()),
(7, 'Scholastic Corporation', 'https://www.scholastic.com', 'USA', NOW(), NOW()),
(8, 'Pearson Education', 'https://www.pearson.com', 'UK', NOW(), NOW()),
(9, 'Oxford University Press', 'https://global.oup.com', 'UK', NOW(), NOW()),
(10, 'Titania', 'https://www.titaniaeditorial.com', 'Spain', NOW(), NOW()),
(11, 'Random House', 'https://www.randomhousebooks.com', 'USA', NOW(), NOW()),
(12, 'Farrar, Straus and Giroux', 'https://us.macmillan.com/fsg', 'USA', NOW(), NOW()),
(13, 'Cengage Learning', 'https://www.cengage.com', 'USA', NOW(), NOW()),
(14, 'Wiley', 'https://www.wiley.com', 'USA', NOW(), NOW()),
(15, 'SAGE Publishing', 'https://us.sagepub.com', 'USA', NOW(), NOW());

SELECT setval(pg_get_serial_sequence('publishers', 'id'), (SELECT MAX(id) FROM publishers));


INSERT INTO books (id, title, description, cover_image, isbn, pages, publication_date, genre_id, language_id, author_id, publisher_id, edition_type_id, created_at, updated_at)
VALUES
(1, 'La lista de deseos de Ethan J. Goldman', '¿Qué harías tú si te dieran solo dos meses de vida? Ethan James Goldman lo tiene todo. Es rico, popular y estudia en una de las universidades más prestigiosas de la Ivy League, pero su brillante futuro desaparece de repente con dos sencillas palabras: cáncer terminal. Wild Morgan es todo lo que Ethan no es: libre, despreocupada y divertida. Aunque sí que hay una cosa que le preocupa: el dinero. Wild es una alumna becada en la Universidad de Arte de Massachusetts, cuya familia a duras penas llega a fin de mes. Cuando un encuentro desafortunado entre ambos hace que se crucen sus caminos, Wild le enseñará a Ethan la importancia de aprovechar sus últimos momentos, mientras que Ethan hará todo lo que sea necesario para convencer a Wild de que lo acompañe en su aventura de completar la lista de cosas que quiere hacer antes de morir... si sus sentimientos no acaban con él antes, claro está. Una lista que unirá a dos perfectos desconocidos. ¿Qué pasará cuando el tiempo se acabe y el juego se termine?', 'https://m.media-amazon.com/images/I/41-7QVa4vRL._SY445_SX342_.jpg', '9788419131157', 480, '2023-05-16', 5, 2, 1, 10, 3, NOW(), NOW()),
(2, 'The Shining', 'A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence.', 'https://m.media-amazon.com/images/I/81zqohMOk-L.jpg', '978-0-385-12167-5', 447, '1977-01-28', 6, 1, 2, 1, 1, NOW(), NOW()),
(3, 'Harry Potter and the Philosopher''s Stone', 'The first book in the Harry Potter series, about a young wizard discovering his magical heritage.', 'https://m.media-amazon.com/images/I/51dOacmuzvL._SY445_SX342_.jpg', '978-0-7475-3269-9', 223, '1997-06-26', 15, 1, 3, 6, 2, NOW(), NOW()),
(4, 'A Game of Thrones', 'Noble families vie for control of the Iron Throne in the Seven Kingdoms of Westeros.', 'https://m.media-amazon.com/images/I/81Qf2JW41zL._UF1000,1000_QL80_DpWeblab_.jpg', '978-0-553-10354-0', 694, '1996-08-06', 1, 1, 4, 2, 1, NOW(), NOW()),
(5, 'The Handmaid''s Tale', 'In a dystopian future, women are subjugated under a theocratic regime.', 'https://m.media-amazon.com/images/I/81lsLnsttzL._AC_UF894,1000_QL80_.jpg', '978-0-385-49081-6', 311, '1985-08-17', 7, 1, 5, 4, 3, NOW(), NOW()),
(6, 'American Gods', 'A blend of Americana, fantasy, and various strands of ancient and modern mythology. The story follows Shadow Moon, a man who becomes entangled in a war between fading old gods and rising new deities.', 'https://m.media-amazon.com/images/I/716LpMKQ3iL.jpg', '978-0-06-257223-3', 465, '2001-06-19', 1, 1, 6, 5, 2, NOW(), NOW()),
(7, 'Norwegian Wood', 'A nostalgic story of loss and sexuality in 1960s Tokyo.', 'https://m.media-amazon.com/images/I/411f6XJtWqL._SY445_SX342_.jpg', '978-0-670-82162-4', 296, '1987-09-04', 12, 1, 7, 6, 2, NOW(), NOW()),
(8, 'The House of the Spirits', 'A family saga that blends the personal and the political in Chile.', 'https://m.media-amazon.com/images/I/41-+KHWzkaL._SY445_SX342_.jpg', '978-0-06-114852-4', 433, '1982-04-01', 7, 1, 8, 6, 1, NOW(), NOW()),
(9, 'Beloved', 'Sethe is haunted by the trauma of slavery and the ghost of her dead daughter.', 'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1722944318i/6149.jpg', '978-0-440-30864-7', 324, '1987-09-16', 12, 1, 9, 7, 1, NOW(), NOW()),
(10, 'Mistborn: The Final Empire', 'In a world where ash falls from the sky, a rebellion brews against a dark lord.', 'https://m.media-amazon.com/images/I/91U6rc7u0yL._UF1000,1000_QL80_.jpg', '978-0-7653-4225-9', 541, '2006-07-17', 1, 1, 10, 8, 1, NOW(), NOW()),
(11, 'Doctor Sleep', 'The sequel to The Shining, following Danny Torrance in his adult life.', 'https://m.media-amazon.com/images/I/71At7VPGc6L._AC_UF1000,1000_QL80_.jpg', '978-0-385-52767-9', 531, '2013-09-24', 6, 1, 2, 1, 2, NOW(), NOW()),
(12, 'Harry Potter and the Chamber of Secrets', 'The second installment of the Harry Potter series, full of mysteries at Hogwarts.', 'https://m.media-amazon.com/images/I/818umIdoruL.jpg', '978-0-7475-3849-3', 251, '1998-07-02', 15, 1, 3, 6, 2, NOW(), NOW()),
(13, 'A Clash of Kings', 'Second book in the A Song of Ice and Fire series, with battles for the throne.', 'https://m.media-amazon.com/images/I/7193zyz9thL.jpg', '978-0-553-10803-3', 768, '1998-11-16', 1, 1, 4, 2, 1, NOW(), NOW()),
(14, 'The Testaments', 'Sequel to The Handmaid''s Tale, continuing the story in Gilead.', 'https://upload.wikimedia.org/wikipedia/en/5/54/The_Testaments_%28Atwood_novel%29.png', '978-0-7340-4417-0', 432, '2019-09-10', 7, 1, 5, 4, 1, NOW(), NOW()),
(15, 'Anansi Boys', 'Standalone novel set in the universe of American Gods.', 'https://m.media-amazon.com/images/I/61yF0p1FSKL._AC_UF1000,1000_QL80_.jpg', '978-0-06-055812-3', 400, '2005-06-21', 1, 1, 6, 5, 2, NOW(), NOW()),
(16, 'Kafka on the Shore', 'Surreal and metaphysical novel by Haruki Murakami.', 'https://m.media-amazon.com/images/I/81ho80SbxgL._AC_UF1000,1000_QL80_.jpg', '978-0-14-303655-2', 505, '2002-09-12', 12, 1, 7, 6, 1, NOW(), NOW()),
(17, 'Of Love and Shadows', 'Political and romantic novel set in Chile.', 'https://m.media-amazon.com/images/I/510xLeatEjL._AC_UF1000,1000_QL80_.jpg', '978-0-385-29415-9', 288, '1984-01-01', 7, 1, 8, 6, 2, NOW(), NOW()),
(18, 'Mistborn: The Final Empire', 'En un mundo donde la ceniza cae del cielo, una rebelión se alza contra un oscuro señor.', 'https://m.media-amazon.com/images/I/91U6rc7u0yL._UF1000,1000_QL80_.jpg', '978-0-7653-4225-9', 541, '2006-07-17', 1, 1, 10, 8, 1, NOW(), NOW()),
(19, 'The Well of Ascension', 'Segunda parte de la saga Mistborn, continúa la historia en un mundo con magia única.', 'https://m.media-amazon.com/images/I/91+2HQUMndL._AC_UF1000,1000_QL80_.jpg', '978-0-7653-4226-6', 590, '2007-08-21', 1, 1, 10, 8, 1, NOW(), NOW()),
(20, 'Mistborn: The Hero of Ages', 'Tercera entrega de la saga Mistborn, cerrando la trilogía original.', 'https://m.media-amazon.com/images/I/81BFn0zMJ+L.jpg', '978-0-7653-4227-3', 572, '2008-10-14', 1, 1, 10, 8, 1, NOW(), NOW());

SELECT setval(pg_get_serial_sequence('books', 'id'), (SELECT MAX(id) FROM books));

INSERT INTO roles (name, description, created_at, updated_at)
SELECT 'USER', 'Regular user role', NOW(), NOW()
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'USER');

INSERT INTO roles (name, description, created_at, updated_at)
SELECT 'LIBRARIAN', 'Librarian with special permissions', NOW(), NOW()
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'LIBRARIAN');

INSERT INTO roles (name, description, created_at, updated_at)
SELECT 'ADMIN', 'Administrator with full permissions', NOW(), NOW()
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ADMIN');

INSERT INTO challenges (name, description, target_quantity, badge_url, category, created_at)
VALUES
    ('Bronze Rank', 'Climb to Bronze in the monthly leaderboard — not bad for a warm-up!', 1, '/badges/monthly-classification/bronze.png', 'Monthly Classification', NOW()),
    ('Silver Rank', 'Shine in Silver this month — so close to glory!', 1, '/badges/monthly-classification/silver.png', 'Monthly Classification', NOW()),
    ('Gold Rank', 'Rule the monthly leaderboard and wear the Gold crown — flex responsibly!', 1, '/badges/monthly-classification/gold.png', 'Monthly Classification', NOW());

INSERT INTO challenges (name, description, target_quantity, badge_url, category, created_at)
VALUES
    ('1 Book Read', 'Read your first book — every legend starts somewhere.', 1, '/badges/books-read/1-book-read.png', 'Books Read', NOW()),
    ('3 Books Read', 'Three books down — you’re on a roll! Next stop: literary fame!', 3, '/badges/books-read/3-books-read.png', 'Books Read', NOW()),
    ('10 Books Read', 'Ten books? Who are you, a librarian ninja?', 10, '/badges/books-read/10-books-read.png', 'Books Read', NOW());

INSERT INTO challenges (name, description, target_quantity, badge_url, category, created_at)
VALUES
    ('1K Pages Read', 'Read 1,000 pages — welcome to the big leagues.', 1000, '/badges/pages-read/1k-pages-read.png', 'Pages Read', NOW()),
    ('5K Pages Read', '5,000 pages flipped — your thumbs must be ripped.', 5000, '/badges/pages-read/5k-pages-read.png', 'Pages Read', NOW()),
    ('10K Pages Read', '10,000 pages read — are you powered by coffee or magic?', 10000, '/badges/pages-read/10k-pages-read.png', 'Pages Read', NOW());

INSERT INTO challenges (name, description, target_quantity, badge_url, category, created_at)
VALUES
    ('1 Rating Given', 'Give your first rating — your opinion matters, even if it’s spicy.', 1, '/badges/ratings/1-rating.png', 'Ratings Given', NOW()),
    ('10 Ratings Given', '10 books rated — you’re basically a literary critic now.', 10, '/badges/ratings/10-ratings.png', 'Ratings Given', NOW()),
    ('25 Ratings Given', '25 books rated — Rotten Tomatoes who? You’re the real MVP.', 25, '/badges/ratings/25-ratings.png', 'Ratings Given', NOW());