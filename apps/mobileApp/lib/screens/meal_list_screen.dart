<<<<<<< HEAD
=======
import 'package:app/endpoints/shop_endpoint.dart';
import 'package:app/models/meal.dart';
>>>>>>> fc4898b6ff3a2ef2227e458afe0812e4305b94c0
import 'package:app/models/meals.dart';
import 'package:app/widgets/button_create_pizza.dart';
import 'package:app/widgets/meal_item.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class meal_list extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final meals = context.watch<Meals>().meals;
    var toppings = ShopEndpoint.instance().getToppings().then((value){
      print("TOPPINGS IN MEALS"+value.toString());
    });

    var sizes = ShopEndpoint.instance().getSizes().then((value){
      print("SIZES IN MEALS"+value.toString());
    });

    return Container(
      decoration: new BoxDecoration(
        image: new DecorationImage(
          image: new AssetImage("images/Background_Login.jpg"),
          fit: BoxFit.cover,
        ),
      ),
      child: Center(
        child: SingleChildScrollView(
          child: Column(
            children: [
              CreatePizzaButton(),
              ListView(
                scrollDirection: Axis.vertical,
                shrinkWrap: true,
                padding: const EdgeInsets.only(top: 8, left: 4, right: 4, bottom: 72),
                physics: const NeverScrollableScrollPhysics(),
                children: meals.map((m) => MenuItemCard(meal: m)).toList(),
              ),
            ],
          ),
        ),
      ),
    );

  }
}
