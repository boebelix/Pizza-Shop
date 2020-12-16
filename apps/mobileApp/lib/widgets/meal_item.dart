import 'package:flutter/material.dart';
import 'package:app/models/meal.dart';
import 'package:app/screens/meal_detail_screen.dart';
import 'package:app/widgets/price.dart';


class MenuItemCard extends StatelessWidget {
  final Meal meal;

  const MenuItemCard({Key key, this.meal}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    const double radius = 8;
    const double itemHeight = 120;
    return Card(
      elevation: 8,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(radius),
      ),
      child: ClipRRect(
        borderRadius: BorderRadius.circular(radius),
        child: GestureDetector(
          onTap: () {
            Navigator.pushNamed(
              context,
              MealDetailScreen.routeName,
              arguments: meal,
            );
          },
          child: Container(
            height: itemHeight,
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                Flexible(
                  child: _MealInfo(meal: meal),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class _MealInfo extends StatelessWidget {
  final Meal meal;

  const _MealInfo({Key key, this.meal}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(12.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            meal.name,
            style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
          ),
          Text(
            meal.description,
            maxLines: 2,
            overflow: TextOverflow.ellipsis,
            style: TextStyle(fontSize: 14, color: Colors.black54),
          ),
          Spacer(),
          Row(
            mainAxisAlignment: MainAxisAlignment.end,
            children: [

              Price(price: meal.price),
            ],
          ),
        ],
      ),
    );
  }
}
