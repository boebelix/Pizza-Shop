import 'dart:collection';

import 'package:app/models/size.dart';
import 'package:app/models/topping.dart';
import 'package:app/models/toppings.dart';

class Pizza {
  Size size;
  List<Topping> toppings = [];

  Pizza({this.size});
  Pizza.withToppings({this.size, this.toppings});


  Pizza addTopping(Topping topping) {
    toppings.add(topping);
    return this;
  }

  double get price {
    return size.basePrice + toppings.length * size.toppingPrice;
  }

  factory Pizza.fromJson(Map<String, dynamic> json) => Pizza.withToppings(
    size: json['size'],
    toppings: List<Topping>.from(json['toppings'].map((e) => Topping.fromJson(e))),
  );

  Map<String, dynamic> toJson() => {
    "sizeId": size.id,
    "toppings": toppings.map((e) => e.toJson()).toList(),
  };

}
