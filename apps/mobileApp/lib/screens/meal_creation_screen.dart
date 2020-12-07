import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:app/models/meal.dart';
import 'package:app/models/meals.dart';
import 'dart:math';


class MealCreationScreen extends StatefulWidget {
  static const String routeName = "/createMeal";

  @override
  _MealCreationScreenState createState() => _MealCreationScreenState();
}

class _MealCreationScreenState extends State<MealCreationScreen> {
  final TextEditingController nameController = TextEditingController();

  final TextEditingController descriptionController = TextEditingController();

  final TextEditingController priceController = TextEditingController();

  final _formKey = GlobalKey<FormState>();

  @override
  void dispose() {
    nameController.dispose();
    descriptionController.dispose();
    priceController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Create a new Meal"),
      ),
      body: Form(
        key: _formKey,
        child: ListView(
          padding: const EdgeInsets.all(8),
          children: [
            _NameFormField(nameController: nameController),
            _DescriptionFormField(descriptionController: descriptionController),
            _PriceFormField(priceController: priceController),
            SizedBox(height: 32),
            RaisedButton(
              child: Text("Submit"),
              onPressed: () => onSubmitPressed(context),
            ),
          ],
        ),
      ),
    );
  }

  void onSubmitPressed(BuildContext context) {
    if (_formKey.currentState.validate()) {
      final Random random = Random();
      final Meal meal = Meal(
        name: nameController.text,
        description: descriptionController.text,
        price: double.parse(priceController.text),
        numberOfRatings: random.nextInt(300),
        rating: random.nextDouble() * 5,
      );

      context.read<Meals>().add(meal);

      Navigator.pop(context);
    }
  }
}

class _NameFormField extends StatelessWidget {
  const _NameFormField({
    Key key,
    @required this.nameController,
  }) : super(key: key);

  final TextEditingController nameController;

  @override
  Widget build(BuildContext context) {
    return TextFormField(
      controller: nameController,
      decoration: InputDecoration(
        labelText: "Name",
      ),
      validator: (value) {
        if (value == null || value.isEmpty) {
          return "Set a valid name!";
        }
        return null;
      },
    );
  }
}

class _DescriptionFormField extends StatelessWidget {
  const _DescriptionFormField({
    Key key,
    @required this.descriptionController,
  }) : super(key: key);

  final TextEditingController descriptionController;

  @override
  Widget build(BuildContext context) {
    return TextFormField(
      controller: descriptionController,
      decoration: InputDecoration(
        labelText: "Description",
      ),
      minLines: 1,
      maxLines: 5,
    );
  }
}

class _PriceFormField extends StatelessWidget {
  const _PriceFormField({
    Key key,
    @required this.priceController,
  }) : super(key: key);

  final TextEditingController priceController;

  @override
  Widget build(BuildContext context) {
    return TextFormField(
      controller: priceController,
      decoration: InputDecoration(
        labelText: "Price",
      ),
      keyboardType: TextInputType.number,
      validator: (value) {
        final price = double.tryParse(value);
        if (price == null || price < 0) {
          return "Set a valid price!";
        }
        return null;
      },
    );
  }
}
