
import 'package:app/models/Pizza.dart';
import 'package:flutter/material.dart';

class PizzaCartSummary extends StatelessWidget {
  final Pizza pizza;

  const PizzaCartSummary({Key key, this.pizza}) : super(key: key);

  double calcPrice() {
    double price =
        pizza.size.basePrice + pizza.toppings.length * pizza.size.toppingPrice;
    //return (price * 10).round() / 10.0;
    return price;
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
                        "${calcPrice()}€",
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
