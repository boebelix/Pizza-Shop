
import 'dart:io';

import 'package:app/endpoints/shop_endpoint.dart';
import 'package:app/models/meal.dart';

import 'package:app/models/meals.dart';
import 'package:app/widgets/button_create_pizza.dart';
import 'package:app/widgets/meal_item.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class meal_list extends StatelessWidget {
  List toppings;
  bool toppingsHaveFinished=false;
  List sizes;
  bool sizesHaveFinished=false;

  @override
  Widget build(BuildContext context) {
    final meals = context.watch<Meals>().meals;


    ShopEndpoint.instance().getToppings().then((value){
      toppings = value;
      toppingsHaveFinished=true;
    });


    ShopEndpoint.instance().getSizes().then((value){
      sizes = value;
      sizesHaveFinished=true;
    });

    // while(!(toppingsHaveFinished && sizesHaveFinished)){
    //   sleep(new Duration(milliseconds: 100));
    // }
    print("TOPPINGS IN MEALS"+toppings.toString());
    print("SIZES IN MEALS"+sizes.toString());

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
