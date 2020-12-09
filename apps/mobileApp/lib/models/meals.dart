import 'dart:collection';
import 'package:flutter/foundation.dart';
import 'meal.dart';


class Meals extends ChangeNotifier {
  /*
  * Bilder von
  * https://www.essen-und-trinken.de/pizza/73081-cstr-die-besten-pizza-rezepte
  * */
  List<Meal> _meals = [
    Meal(
      imagePath:
      "https://image.essen-und-trinken.de/11915996/t/Gf/v8/w960/r1/-/267910--5613-.jpg",
      name: "Erstelle dir deine eigene Pizza",
      description:
      "Sei dein eigener Koch",
      price: 0.0,
      rating: 5.0,
      numberOfRatings: 0,
    ),
    Meal(
      imagePath:
          "https://image.essen-und-trinken.de/11832544/t/dG/v9/w960/r1/-/385730--8868-.jpg",
      name: "Blumenkohl-Pizza",
      description:
          "with beef, cheese, onion, tomato, with beef, cheese, onion, tomato, with beef, cheese, onion, tomato",
      price: 5.5,
      rating: 4.8,
      numberOfRatings: 153,
    ),
    Meal(
      imagePath:
          "https://image.essen-und-trinken.de/11834520/t/hS/v9/w960/r1/-/deep-pan-pizza-53e2c013fa70fb58aa2cc59fcb688cf8-fjt2016010662-jpg--9857-.jpg",
      name: "Deep Pan Pizza",
      description: "with ketchup",
      price: 3,
      rating: 3.47,
      numberOfRatings: 27,
    ),
    Meal(
      imagePath:
          "https://image.essen-und-trinken.de/11917780/t/WY/v8/w960/r1/-/chili-con-carne-pizza-7050db21f20e777cda3f024743b6810a-fjt2013030321-jpg--6548-.jpg",
      name: "Chili-con-Carne-Pizza",
      description: "with shrimps and others",
      price: 7.8,
      rating: 4.7,
      numberOfRatings: 274,
    ),
    Meal(
      imagePath:
          "https://image.essen-und-trinken.de/11940766/t/eC/v10/w960/r1/-/pizza-sushi-style-jpg--59070-.jpg",
      name: "Sushi Style mit Spinatsalat",
      description:
          "with pumpkins, Lorem ipsum dolor sit amet, consetetur sadipscing",
      price: 0.75,
      rating: 3.9,
      numberOfRatings: 38,
    ),
    Meal(
      imagePath:
          "https://image.essen-und-trinken.de/11832944/t/UG/v9/w960/r1/-/scharfe-pizza-mit-avocado-bca3d2ffa275013797b2a30c77dccc0a-fjt2015050231-jpg--9068-.jpg",
      name: "Scharfe Pizza mit Avocado",
      description:
          "with blue berries, vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr",
      price: 1.5,
      rating: 4.3,
      numberOfRatings: 12,
    ),
    Meal(
      imagePath:
          "https://image.essen-und-trinken.de/11910910/t/SS/v8/w960/r1/-/alpen-pizzamitpfifferlingenundspeck-ec0975dde3a6c6cc01a8e9437732346c-et2009090302-jpg--3027-.jpg",
      name: "Alpen Pizza mit Pfifferlingen und Speck",
      description: "with beef ipsum dolor sit amet. Lorem ipsum dolor ",
      price: 16.90,
      rating: 3.3,
      numberOfRatings: 35,
    ),
    Meal(
      imagePath:
          "https://image.essen-und-trinken.de/11912372/t/Cm/v8/w960/r1/-/pizza-mit-salsiccia-und-fenchel-306d1daf7e67ced12672f6a826dc9813-et2010090761-jpg--3782-.jpg",
      name: "Pizza mit Salsiccia und Fenchel",
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
