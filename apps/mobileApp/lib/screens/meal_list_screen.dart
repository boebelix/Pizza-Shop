import 'package:app/endpoints/shop_endpoint.dart';
import 'package:app/models/meals.dart';
import 'package:app/models/toppings.dart';
import 'package:app/widgets/button_create_pizza.dart';
import 'package:app/widgets/meal_item.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class meal_list extends StatefulWidget {
  @override
  _meal_listState createState() => _meal_listState();
}

class _meal_listState extends State<meal_list> {

  @override
  void initState() {
    super.initState();
    loadToppings();
  }

  void loadToppings() async {
    context.read<Toppings>().addAll(await ShopEndpoint.instance().getToppings());
  }

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
        child: SingleChildScrollView(
          child: Column(
            children: [
              CreatePizzaButton(),
              ListView(
                scrollDirection: Axis.vertical,
                shrinkWrap: true,
                padding: const EdgeInsets.only(
                    top: 8, left: 4, right: 4, bottom: 72),
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
