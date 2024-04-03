USE chess;

CREATE TABLE chess_game
(
    name  VARCHAR(32) NOT NULL,
    state VARCHAR(8)  NOT NULL,

    PRIMARY KEY (name)
);

CREATE TABLE movement
(
    movement_id       BIGINT      NOT NULL AUTO_INCREMENT,
    chess_game_name   VARCHAR(32) NOT NULL,
    source_coordinate VARCHAR(2)  NOT NULL,
    target_coordinate VARCHAR(2)  NOT NULL,

    PRIMARY KEY (movement_id),
    FOREIGN KEY (chess_game_name) REFERENCES chess_game (name) ON DELETE CASCADE
);

