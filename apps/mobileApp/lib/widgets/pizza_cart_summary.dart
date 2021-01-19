import 'package:app/models/Pizza.dart';
import 'package:flutter/material.dart';

class PizzaCartSummary extends StatelessWidget {
  final Pizza pizza;

  const PizzaCartSummary({Key key, this.pizza}) : super(key: key);



  @override
  Widget build(BuildContext context) {
    const double radius = 8;

    return Container(
      child: ClipRRect(
        borderRadius: BorderRadius.circular(radius),
        child: Card(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              Text("Pizza ${pizza.size.diameter}cm",
                  style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
              ListView.builder(
                padding: const EdgeInsets.only(top: 8, left: 4, right: 4, bottom: 72),
                itemCount: pizza.toppings.length,
                itemBuilder: (context, index) => Text(
                    "- ${pizza.toppings[index].name}",
                ),
                shrinkWrap: true,
              ),
            ],
          ),
        ),
      ),
    );
  }
}
