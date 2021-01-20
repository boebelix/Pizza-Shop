import 'package:app/models/Pizza.dart';
import 'package:app/models/cart.dart';
import 'package:app/models/sizes.dart';
import 'package:app/models/topping.dart';
import 'package:app/models/toppings.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class CreatePizzaScreen extends StatefulWidget {
  static const String routeName = "/createPizzaScreen";

  @override
  _CreatePizzaScreenState createState() => _CreatePizzaScreenState();
}

class _CreatePizzaScreenState extends State<CreatePizzaScreen> {
  var _labelCheckedMap = {}; //new Map();

  List<String> text = ["InduceSmile.com", "Flutter.io", "google.com"];

  int _value;

  @override
  Widget build(BuildContext context) {
    final _toppings = context.watch<Toppings>().toppings;

    final _sizes = context.watch<Sizes>().sizes;

    for (Topping t in _toppings) {
      _labelCheckedMap.putIfAbsent(t, () => false);
    }

    return Scaffold(
      appBar: AppBar(
        title: Text("Erstelle deine eigene Pizza"),
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: <Widget>[
          SingleChildScrollView(
            scrollDirection: Axis.vertical,
            child: Column(
              children: [
                Text("Größen",
                    style: TextStyle(
                      fontSize: 20.0,
                      fontWeight: FontWeight.bold,
                    )),
                new ListView.builder(
                  scrollDirection: Axis.vertical,
                  shrinkWrap: true,
                  itemBuilder: (context, index) {
                    return RadioListTile(
                      value: index,
                      groupValue: _value,
                      onChanged: (ind) => setState(() => _value = ind),
                      title: Text("Größe ${_sizes[index].diameter}cm"),
                    );
                  },
                  itemCount: _sizes.length,
                ),
                Container(
                  height: 350.0,
                  child: Column(children: [
                    Text("Toppings",
                        style: TextStyle(
                          fontSize: 20.0,
                          fontWeight: FontWeight.bold,
                        )),
                    for (Topping key in _labelCheckedMap.keys)
                      CheckboxListTile(
                        title: Text(key.name),
                        tristate: false,
                        value: _labelCheckedMap[key],
                        onChanged: (newValue) {
                          setState(() {
                            _labelCheckedMap.update(key, (value) => newValue);
                          });
                        },
                      )
                  ]),
                ),
              ],
            ),
          ),
          SizedBox(
            height: 25,
            child: RaisedButton(
                child: Text("Zum Warenkorb hinzufügen"),
                onPressed: () {
                  var pizza = Pizza(size: _sizes.elementAt(_value));
                  for (Topping t in _labelCheckedMap.keys) {
                    if (_labelCheckedMap[t]) pizza.addTopping(t);
                  }
                  context.read<Cart>().add(pizza);
                }),
          ),
        ],
      ),
    );
  }
}
