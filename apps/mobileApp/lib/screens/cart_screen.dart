import 'package:app/models/cart.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class CartScreen extends StatelessWidget {
  static const String routeName = "/cart";


  @override
  Widget build(BuildContext context) {
    final meals = context.watch<Cart>().pizzas;

    return Container(
      decoration: new BoxDecoration(
        image: new DecorationImage(
          image: new AssetImage("images/Background_Login.jpg"),
          fit: BoxFit.cover,
        ),
      ),

      /*child: Center(
        child: ListView.builder(
          padding: const EdgeInsets.only(top: 8, left: 4, right: 4, bottom: 72),
          itemCount: meals.length,
          itemBuilder: (context, index) => MenuItemCard(
            meal: meals[index],
          ),
        ),
      ),*/
    );
  }
}
