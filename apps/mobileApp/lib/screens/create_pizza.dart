import 'package:app/models/size.dart';
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
  bool _isChecked = true; //Map mit CurrentText als key, value _isChecked
  String _currText = 'Test'; //
  var _labelCheckedMap = {}; //new Map();


  var _chosenRadio = 1;

  List<String> text = ["InduceSmile.com", "Flutter.io", "google.com"];


  @override
  Widget build(BuildContext context) {

    final _toppings = context.watch<Toppings>().toppings;

    final _sizes = context.watch<Sizes>().sizes;

    for(Topping t in _toppings)
      {
        _labelCheckedMap.putIfAbsent(t.name, () => false);
      }


    return Scaffold(
      appBar: AppBar(
        title: Text("Erstelle deine eigene Pizza"),
      ),
      body: Column(

        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: <Widget>[
          Expanded(
            child: Text("Größen",
                style: TextStyle(
                  fontSize: 20.0,
                  fontWeight: FontWeight.bold,
                )),
          ),
          Expanded(
              child: Column(
            children: [],
          )),
          Expanded(
              child: Container(
            height: 350.0,
            child: Column(children: [
              Text("Toppings",
                  style: TextStyle(
                    fontSize: 20.0,
                    fontWeight: FontWeight.bold,
                  )),
              for (String key in _labelCheckedMap.keys)
                CheckboxListTile(
                  title: Text(key),
                  tristate: false,
                  value: _labelCheckedMap[key],
                  onChanged: (newValue) {
                    setState(() {
                      _labelCheckedMap.update(key, (value) => newValue);
                    });
                  },
                )
            ]),
          )),
          Expanded(
            child: SizedBox(
              height: 25,
              child: RaisedButton(
                  child: Text("Zum Warenkorb hinzufügen"), onPressed: () {}),
            ),
          ),
        ],
      ),
    );
  }
}
