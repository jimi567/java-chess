USE chess;

CREATE TABLE movement
(
    movement_id       INT        NOT NULL AUTO_INCREMENT,
    source_coordinate VARCHAR(2) NOT NULL,
    target_coordinate VARCHAR(2) NOT NULL,

    PRIMARY KEY (movement_id)
);
