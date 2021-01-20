import 'package:app/models/Pizza.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class PizzaCartSummary extends StatelessWidget {
  final Pizza pizza;

  const PizzaCartSummary({Key key, this.pizza}) : super(key: key);

  String formatPrice(double price) {
    final oCcy = new NumberFormat("#,##0.00", "de_DE");
    return oCcy.format(price);
  }

  @override
  Widget build(BuildContext context) {
    const double radius = 8;

    return Container(
      child: ClipRRect(
        borderRadius: BorderRadius.circular(radius),
        child: Opacity(
          opacity: 0.8,
          child: Card(
            child: Padding(
              padding: const EdgeInsets.all(8.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  Row(
                    children: [
                      Text(
                        "Pizza ${pizza.size.diameter}cm",
                        style: TextStyle(
                            fontSize: 24, fontWeight: FontWeight.bold),
                      ),
                      Spacer(),
                      Text(
                        "${formatPrice(pizza.price)}€",
                        style: TextStyle(
                            fontSize: 24, fontWeight: FontWeight.bold),
                      ),
                    ],
                  ),
                  ListView.builder(
                    physics: NeverScrollableScrollPhysics(),
                    padding: const EdgeInsets.only(
                        top: 8, left: 4, right: 4, bottom: 8),
                    itemCount: pizza.toppings.length,
                    itemBuilder: (context, index) => Text(
                      "- ${pizza.toppings[index].name}",
                      style: TextStyle(fontSize: 18),
                    ),
                    shrinkWrap: true,
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}