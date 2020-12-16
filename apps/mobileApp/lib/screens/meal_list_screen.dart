import 'package:app/models/meals.dart';
import 'package:app/widgets/meal_item.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class meal_list extends StatelessWidget {


  @override
  Widget build(BuildContext context) {
    final meals = context.watch<Meals>().meals;

    return Container(
      decoration: new BoxDecoration(
        image: new DecorationImage(
          image: new AssetImage("images/Background_Login.jpg"),
          fit: BoxFit.cover,
        ),
      ),

      child: Center(
        child: ListView.builder(
          padding: const EdgeInsets.only(top: 8, left: 4, right: 4, bottom: 72),
          itemCount: meals.length,
          itemBuilder: (context, index) => MenuItemCard(
            meal: meals[index],
          ),
        ),
      ),
    );
  }
}
