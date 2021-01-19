import 'package:flutter/material.dart';

class CreatePizzaScreen extends StatefulWidget {
  static const String routeName = "/createPizzaScreen";

  @override
  _CreatePizzaScreenState createState() => _CreatePizzaScreenState();
}

class _CreatePizzaScreenState extends State<CreatePizzaScreen> {
  bool _isChecked = true; //Map mit CurrentText als key, value _isChecked
  String _currText = 'Test'; //
  var _labelCheckedMap = {'Schinken': false, 'Salami': false}; //new Map();
  /* sizeOne= new Size(id: 1, 15.3, 15, 200,
     1, 0.50);

  List<Size> _sizes=List.of({sizeOne,});*/

  var _chosenRadio = 1;

  List<String> text = ["InduceSmile.com", "Flutter.io", "google.com"];

  @override
  Widget build(BuildContext context) {
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
