USE chess;

CREATE TABLE chess_game
(
    id    BIGINT      NOT NULL AUTO_INCREMENT,
    name  VARCHAR(32) NOT NULL,
    state VARCHAR(8)  NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE movement
(
    movement_id       BIGINT     NOT NULL AUTO_INCREMENT,
    chess_game_id     BIGINT     NOT NULL,
    source_coordinate VARCHAR(2) NOT NULL,
    target_coordinate VARCHAR(2) NOT NULL,

    PRIMARY KEY (movement_id),
    FOREIGN KEY (chess_game_id) REFERENCES chess_game (id)
);

CREATE TABLE piece
(
    piece_id      BIGINT     NOT NULL AUTO_INCREMENT,
    chess_game_id BIGINT     NOT NULL,
    coordinate    VARCHAR(2) NOT NULL,
    team          VARCHAR(8) NOT NULL,
    type          VARCHAR(8) NOT NULL,

    PRIMARY KEY (piece_id),
    FOREIGN KEY (chess_game_id) REFERENCES chess_game (id)
);