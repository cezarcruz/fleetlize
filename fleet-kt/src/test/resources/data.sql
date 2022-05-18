INSERT INTO category (name, price)
  VALUES ('Premium Plus', 140.12);

INSERT INTO car (plate, model, category_id)
  VALUES ('CVY1239', 'Ford Fiesta', SELECT c.id FROM category c WHERE name = 'Premium Plus')