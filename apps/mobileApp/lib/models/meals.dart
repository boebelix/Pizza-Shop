import 'dart:collection';
import 'package:flutter/foundation.dart';
import 'meal.dart';


class Meals extends ChangeNotifier {
  List<Meal> _meals = [
    Meal(
      imagePath:
      "https://static.essen-und-trinken.de/bilder/6b/3f/58617/facebook_image/klassischer-burger.jpg",
      name: "Erstelle dir deine eigene Pizza",
      description:
      "Sei dein eigener Koch",
      price: 0.0,
      rating: 5.0,
      numberOfRatings: 0,
    ),
    Meal(
      imagePath:
          "https://static.essen-und-trinken.de/bilder/6b/3f/58617/facebook_image/klassischer-burger.jpg",
      name: "Classic Burger",
      description:
          "with beef, cheese, onion, tomato, with beef, cheese, onion, tomato, with beef, cheese, onion, tomato",
      price: 5.5,
      rating: 4.8,
      numberOfRatings: 153,
    ),
    Meal(
      imagePath:
          "https://cdn.gutekueche.de/upload/rezept/141/1600x1200_pommes-frites-aus-belgien.jpg",
      name: "Pommes",
      description: "with ketchup",
      price: 3,
      rating: 3.47,
      numberOfRatings: 27,
    ),
    Meal(
      imagePath:
          "https://s3.eu-central-1.amazonaws.com/dtsee-media/media/image/f2/bd/64/Tajine-mit-Meeresfruechten-Rezepte-Hauptgerichte-Aufmacher-Bildformat-32-25.jpg",
      name: "Tajine",
      description: "with shrimps and others",
      price: 7.8,
      rating: 4.7,
      numberOfRatings: 274,
    ),
    Meal(
      imagePath:
          "https://img.delicious.com.au/0Hfc6oZG/w1200/del/2017/03/creamy-pumpkin-soup-43936-2.jpg",
      name: "Pumpkin Soup",
      description:
          "with pumpkins, Lorem ipsum dolor sit amet, consetetur sadipscing",
      price: 0.75,
      rating: 3.9,
      numberOfRatings: 38,
    ),
    Meal(
      imagePath:
          "https://assets.wsimgs.com/wsimgs/ab/images/dp/recipe/202032/0014/img95l.jpg",
      name: "Hummus",
      description: "with chickpeas, sed diam voluptua. At vero eos et a",
      price: 2.65,
      rating: 4.449,
      numberOfRatings: 99,
    ),
    Meal(
      imagePath:
          "https://images.eatsmarter.de/sites/default/files/styles/max_size/public/mandel-porridge-mit-heidelbeeren-656183.jpg",
      name: "Porridge",
      description:
          "with blue berries, vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr",
      price: 1.5,
      rating: 4.3,
      numberOfRatings: 12,
    ),
    Meal(
      imagePath:
          "https://img.chefkoch-cdn.de/rezepte/1281481233561735/bilder/909363/crop-360x240/rumpsteak-medium-mit-gelinggarantie.jpg",
      name: "Rump Steak",
      description: "with beef ipsum dolor sit amet. Lorem ipsum dolor ",
      price: 16.90,
      rating: 3.3,
      numberOfRatings: 35,
    ),
    Meal(
      imagePath:
          "https://images.eatsmarter.de/sites/default/files/styles/max_size/public/bunte-wraps-mit-gemuese-655718.jpg",
      name: "Colorful Wrap",
      description: "with vegetables",
      price: 5,
      rating: 3.3,
      numberOfRatings: 35,
    ),
  ];

  UnmodifiableListView<Meal> get meals => UnmodifiableListView(_meals);

  void add(Meal meal) {
    _meals.add(meal);
    notifyListeners();
  }
}
