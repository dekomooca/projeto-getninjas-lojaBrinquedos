-- Usuarios
INSERT INTO LOJA_BRINQUEDOS.USERS (id, login, password) VALUES
(1, 'admin', 'admin'); 

-- categorias
INSERT INTO LOJA_BRINQUEDOS.CATEGORIAS (id, nome, url_foto) VALUES 
(1, 'Carrinhos', 'https://www.svgrepo.com/svg/436382/car-inbound'),
(2, 'Quebra-Cabeças', 'https://www.svgrepo.com/show/499964/puzzle.svg'),
(3, 'Bonecas', 'https://www.svgrepo.com/show/126/doll.svg'),
(4, 'Jogos de Tabuleiro', 'https://www.svgrepo.com/show/83116/board-games-set.svg');

-- Carrinhos
INSERT INTO LOJA_BRINQUEDOS.BRINQUEDOS (id, descricao, id_categoria, marca, url_foto, valor, detalhes) VALUES
(1, 'Carrinho de controle remoto', 1, 'ToyShow', 'https://www.svgrepo.com/show/262265/car-toy.svg', 65.99, 'Carrinho de controle remoto de longa distância da marca ToyShow'),
(2, 'Carrinho de corrida', 1, 'RaceMaster', 'https://www.svgrepo.com/show/533553/car-side.svg', 25.50, 'Carrinho de corrida de alta velocidade da marca RaceMaster'),
(3, 'Carrinho de bombeiro', 1, 'RescueToys', 'https://www.svgrepo.com/show/500053/fire-truck.svg', 29.99, 'Carrinho de bombeiro com luzes e sons da marca RescueToys'),
(4, 'Carrinho de polícia', 1, 'PoliceToys', 'https://www.svgrepo.com/show/500057/police-car.svg', 34.99, 'Carrinho de polícia com sirene da marca PoliceToys'),
(5, 'Carrinho de construção', 1, 'BuildIt', 'https://www.svgrepo.com/show/521011/truck-loading.svg', 69.99, 'Carrinho de construção com escavadeira da marca BuildIt');

-- Quebra Cabeça
INSERT INTO LOJA_BRINQUEDOS.BRINQUEDOS (id, descricao, id_categoria, marca, url_foto, valor, detalhes) VALUES
(6, 'Quebra-cabeça de 1000 peças', 2, 'PuzzleFun', 'https://www.svgrepo.com/show/480613/puzzle-9.svg', 40.00, 'Quebra-cabeça desafiador de 1000 peças da marca PuzzleFun'),
(7, 'Quebra-cabeça 3D', 2, '3D Puzzles', 'https://www.svgrepo.com/show/304341/puzzle-tetris.svg', 31.99, 'Quebra-cabeça em 3D com construção da marca 3D Puzzles'),
(8, 'Quebra-cabeça infantil', 2, 'KidsPuzzles', 'https://www.svgrepo.com/show/489256/puzzle.svg', 19.99, 'Quebra-cabeça colorido para crianças da marca KidsPuzzles'),
(9, 'Quebra-cabeça de paisagem', 2, 'NaturePuzzles', 'https://www.svgrepo.com/show/480616/puzzle-8.svg', 34.99, 'Quebra-cabeça de paisagem com belas vistas da marca NaturePuzzles');

-- Bonecas
INSERT INTO LOJA_BRINQUEDOS.BRINQUEDOS (id, descricao, id_categoria, marca, url_foto, valor, detalhes) VALUES
(10, 'Boneca de porcelana', 3, 'PorcelainDolls', 'https://www.svgrepo.com/show/415032/doll-baby-child.svg', 50.99, 'Boneca de porcelana delicadamente pintada da marca PorcelainDolls'),
(11, 'Boneca bebê realista', 3, 'RealBabyDolls', 'https://www.svgrepo.com/show/6443/doll-toy.svg', 40.35, 'Boneca bebê realista com expressões da marca RealBabyDolls'),
(12, 'Boneca de pano', 3, 'FabricFriends', 'https://www.svgrepo.com/show/5682/doll-hand-drawn-toy.svg', 32.60, 'Boneca de pano feita à mão da marca FabricFriends'),
(13, 'Boneca fashion', 3, 'FashionDolls', 'https://www.svgrepo.com/show/1704/doll.svg', 38.99, 'Boneca fashion com roupas estilosas da marca FashionDolls'),
(14, 'Boneca de madeira', 3, 'WoodenToys', 'https://www.svgrepo.com/show/267730/doll.svg', 26.99, 'Boneca de madeira esculpida à mão da marca WoodenToys');

-- Jogos de Tabuleiro
INSERT INTO LOJA_BRINQUEDOS.BRINQUEDOS (id, descricao, id_categoria, marca, url_foto, valor, detalhes) VALUES
(15, 'Xadrez clássico', 4, 'ChessMasters', 'https://www.svgrepo.com/show/224/chess.svg', 45.99, 'Jogo de xadrez clássico da marca ChessMasters'),
(16, 'Jogo da Vida', 4, 'GameOfLife', 'https://www.svgrepo.com/show/27049/labyrinth.svg', 32.99, 'Jogo da Vida com decisões importantes da marca GameOfLife'),
(17, 'Jogo de Monopoly', 4, 'MonopolyFun', 'https://www.svgrepo.com/show/39638/parchis.svg', 44.99, 'Jogo de Monopoly clássico da marca MonopolyFun'),
(18, 'Quebra-cabeça de Tabuleiro', 4, 'BoardPuzzles', 'https://www.svgrepo.com/show/45383/sudoku.svg', 34.99, 'Quebra-cabeça de tabuleiro interativo da marca BoardPuzzles'),
(19, 'Jogo de Cartas', 4, 'CardGames', 'https://www.svgrepo.com/show/82872/playing-cards.svg', 25.50, 'Jogo de cartas com estratégia da marca CardGames');
