import 'package:app/models/cart.dart';
import 'package:app/widgets/pizza_cart_summary.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class CartScreen extends StatefulWidget {
  static const String routeName = "/cart";

  @override
  _CartScreenState createState() => _CartScreenState();
}

class _CartScreenState extends State<CartScreen> {
  @override
  Widget build(BuildContext context) {
    final pizzas = context.watch<Cart>().pizzas;

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
          itemCount: pizzas.length,
          itemBuilder: (context, index) => Dismissible(
            onDismissed: (direction) => {
              setState(() {context.read<Cart>().remove(index);})
            },
            key: UniqueKey(),
            child: PizzaCartSummary(
              pizza: pizzas[index],
            ),
          ),
        ),
      ),
    );
  }
}
