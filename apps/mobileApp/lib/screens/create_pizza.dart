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

  var _labelCheckedMap = {}; //new Map();
  //var _chosenRadio = {};

  List<String> text = ["InduceSmile.com", "Flutter.io", "google.com"];

  @override
  Widget build(BuildContext context) {
    final _toppings = context.watch<Toppings>().toppings;

    final _sizes = context.watch<Sizes>().sizes;
    Size _currentChoosen=_sizes.first;

    for (Topping t in _toppings) {
      _labelCheckedMap.putIfAbsent(t.name, () => false);
    }

  //  for (int i=0; i<_sizes.length;i++) {
    //  _chosenRadio.putIfAbsent(i, () => _sizes.elementAt(i));
   // }


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
                for(int i = 0;i<_sizes.length;i++)
                RadioListTile<Size>(
                  value: _sizes.elementAt(i),
                   groupValue: _currentChoosen,
                   onChanged: (Size choosen){
                    setState(() {
                      _currentChoosen=choosen;
                    }
                    );
                   },
                   title: Text(_sizes.elementAt(i).diameter.toString()+' cm'),
               ),

                Container(
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
                ),
              ],
            ),
          ),
          SizedBox(
            height: 25,
            child: RaisedButton(
                child: Text("Zum Warenkorb hinzufügen"), onPressed: () {}),
          ),
        ],
      ),
    );
  }
}
