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
    final pizzas = context
        .watch<Cart>()
        .pizzas;

    return Container(
      decoration: new BoxDecoration(
        image: new DecorationImage(
          image: new AssetImage("images/Background_Login.jpg"),
          fit: BoxFit.cover,
        ),
      ),
      child: Center(
        child: Column(
          children: [
            Expanded(
              flex: 18,
              child: ListView.builder(
                padding: const EdgeInsets.only(
                    top: 0, left: 4, right: 4, bottom: 0),
                itemCount: pizzas.length,
                itemBuilder: (context, index) =>
                    Dismissible(
                      onDismissed: (direction) =>
                      {
                        setState(() {
                          context.read<Cart>().remove(index);
                        })
                      },
                      key: UniqueKey(),
                      child: PizzaCartSummary(
                        pizza: pizzas[index],
                      ),
                    ),
                shrinkWrap: true,
              ),
            ),
            Spacer(),
            Flexible(
              flex: 2,
              child: Padding(padding: const EdgeInsets.only(
                  top: 0, left: 8, right: 8, bottom: 0),
                  child: SizedBox(
                    height: 50,
                    width: double.infinity,
                    child: Opacity(
                      opacity: (pizzas.length != 0)? 1 : 0.7,
                      child: RaisedButton(
                        child: Text("Bestellung aufgeben"),
                        color: (pizzas.length != 0)? Color.fromARGB(255, 0, 255, 0) : null,
                        onPressed: () {
                          if(pizzas.length == 0) {
                            return;
                          }
                          setState(() {
                            context.read<Cart>().removeAll();
                          });
                        },
                      ),
                    ),
                  ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
