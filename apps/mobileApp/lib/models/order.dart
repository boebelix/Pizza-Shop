import 'package:app/models/Pizza.dart';

class Order{
  List<Pizza> pizzas;
  String city;
  String country;
  String houseNumber;
  int id;
  DateTime orderDate;
  String postCode;
  int status;
  String street;

  Order({this.pizzas,this.city,this.country,this.houseNumber,this.id,this.orderDate,this.postCode,this.status,this.street});

  factory Order.fromJson(Map<String, dynamic> json) => Order(
      city: json['city'],
      pizzas: json["pizzas"].cast<List>().map((e) => Pizza.fromJson(e)).toList(),
      country: json['country'],
      houseNumber: json['houseNumber'],
      id: json['id'],
      postCode: json['postCode'],
      street: json['street'],
      status:     json['status'],
      orderDate:  DateTime.parse(json['orderDate'].toString().split("[")[0])
  );

  Map<String, dynamic> toJson() => {
    "pizzas": pizzas.map((e) => e.toJson()).toList(),
  };

}
