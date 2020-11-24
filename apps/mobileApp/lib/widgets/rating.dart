import 'package:flutter/material.dart';

class Rating extends StatelessWidget {
  final double _rating;
  final int numberOfRatings;
  final double size;

  double get rating => double.parse(_rating.toStringAsFixed(1));

  const Rating({
    Key key,
    double rating,
    int numberOfRatings,
    this.size = 14,
  })  : this._rating = rating ?? 0,
        this.numberOfRatings = numberOfRatings ?? 0,
        super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        ..._buildStars(),
        Text(
          "${rating.toStringAsFixed(1)}",
          style: TextStyle(fontWeight: FontWeight.bold, fontSize: size),
        ),
        Text(
          " ($numberOfRatings)",
          style: TextStyle(fontSize: size),
        ),
      ],
    );
  }

  _buildStars() {
    List<Widget> stars = [];
    const iconColor = Colors.amber;

    for (int i = 0; i < rating.floor(); i++) {
      stars.add(Icon(
        Icons.star,
        color: iconColor,
        size: size * 24 / 14,
      ));
    }

    if (rating - rating.floor() < 0.5) {
      stars.add(Icon(
        Icons.star_border,
        color: iconColor,
        size: size * 24 / 14,
      ));
    } else {
      stars.add(Icon(
        Icons.star_half,
        color: iconColor,
        size: size * 24 / 14,
      ));
    }

    for (int i = 0; i < 5 - rating.floor() - 1; i++) {
      stars.add(Icon(
        Icons.star_border,
        color: iconColor,
        size: size * 24 / 14,
      ));
    }

    return stars;
  }
}
