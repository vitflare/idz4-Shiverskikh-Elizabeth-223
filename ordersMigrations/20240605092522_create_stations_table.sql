-- +goose Up
-- +goose StatementBegin
CREATE TABLE IF NOT EXISTS stations (
    id SERIAL PRIMARY KEY,
    station VARCHAR(50) NOT NULL
);

Delete from stations where station = 'Moscow';
Delete from stations where station = 'Saint Petersburg';
Delete from stations where station = 'Kazan';
Delete from stations where station = 'Sochi';
Delete from stations where station = 'Vladivostok';
Delete from stations where station = 'Novosibirsk';
Delete from stations where station = 'Yekaterinburg';
Delete from stations where station = 'Krasnoyarsk';

INSERT INTO stations (station) VALUES ('Moscow');
INSERT INTO stations (station) VALUES ('Saint Petersburg');
INSERT INTO stations (station) VALUES ('Kazan');
INSERT INTO stations (station) VALUES ('Sochi');
INSERT INTO stations (station) VALUES ('Vladivostok');
INSERT INTO stations (station) VALUES ('Novosibirsk');
INSERT INTO stations (station) VALUES ('Yekaterinburg');
INSERT INTO stations (station) VALUES ('Krasnoyarsk');
-- +goose StatementEnd

-- +goose Down
-- +goose StatementBegin
DROP TABLE stations;
-- +goose StatementEnd
