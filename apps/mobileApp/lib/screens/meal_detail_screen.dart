import 'package:app/models/cart.dart';
import 'package:app/models/meal.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class MealDetailScreen extends StatelessWidget {
  static const String routeName = "/mealDetails";

  @override
  Widget build(BuildContext context) {
    final meal = ModalRoute.of(context).settings.arguments as Meal;

    return Scaffold(
      appBar: AppBar(
        title: Text(meal.name),
        backgroundColor: Colors.black.withOpacity(0.5),
        elevation: 0,
      ),
      extendBodyBehindAppBar: true,
      body: SafeArea(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Flexible(child: _MealInfo(meal: meal)),
          ],
        ),
      ),
    );
  }
}

class _MealInfo extends StatefulWidget {
  final Meal meal;

  const _MealInfo({Key key, this.meal}) : super(key: key);

  @override
  __MealInfoState createState() => __MealInfoState();
}

class __MealInfoState extends State<_MealInfo> {
  bool _isAddToCart;

  @override
  void initState() {
    _isAddToCart = false;
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          SizedBox(height: 16),
          Text(widget.meal.description),
          Spacer(),
          Text(
            "${widget.meal.price.toStringAsFixed(2)} \€",
            textAlign: TextAlign.right,
            style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold),
          ),
          SizedBox(height: 8),
          _isAddToCart
              ? Text(
                  'Zum Warenkorb hinzugefügt',
                  style: TextStyle(color: Colors.green),
                )
              : Container(),
          RaisedButton(
              child: Text("Zum Warenkorb hinzufügen"),
              onPressed: () {
                print("${widget.meal.name} zum Warenkorb hinzugefügt");
                context.read<Cart>().add(widget.meal);
                setState(() {
                  _isAddToCart = true;
                });
              }),
        ],
      ),
    );
  }
}
