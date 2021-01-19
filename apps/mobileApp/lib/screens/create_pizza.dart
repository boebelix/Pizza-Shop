import 'dart:collection';

import 'package:flutter/material.dart';
import 'package:grouped_buttons/grouped_buttons.dart';

class CreatePizzaScreen extends StatefulWidget {
  static const String routeName = "/createPizzaScreen";
  @override
  _CreatePizzaScreenState createState() => _CreatePizzaScreenState();
}

class _CreatePizzaScreenState extends State<CreatePizzaScreen> {
  bool _isChecked = true;//Map mit CurrentText als key, value _isChecked
  String _currText = 'Test';//
  var _labelCheckedMap={'Schinken':false,'Salami':false};//new Map();


  //_labelCheckedMap['Salami']=false;
  //_labelCheckedMap['Schinken']=false;

  List<String> text = ["InduceSmile.com", "Flutter.io", "google.com"];


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Get check Value Example"),
      ),
      body: Column(
        children: <Widget>[
          Expanded(
            child: Center(
              child: Text(_currText,
                  style: TextStyle(
                    fontSize: 20.0,
                    fontWeight: FontWeight.bold,
                  )),
            ),
          ),
          Expanded(
              child: Container(
              height: 350.0,
                child:Column(
                children:[
                  for(String key in _labelCheckedMap.keys)CheckboxListTile(
                    title:  Text(key),
                    tristate: false,
                    value: _labelCheckedMap[key],
                    onChanged: (newValue) {
                      setState(() {
                        _labelCheckedMap.update(key, (value) => newValue);
                      });
                    },
              //  <-- leading Checkbox
              )]),
          )),
        ],
      ),
    );
  }
}
