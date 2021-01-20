import 'package:app/models/Pizza.dart';
import 'package:app/models/pizzas.dart';

class Order{
  Pizzas pizzas;
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
      pizzas: Pizzas.fromJson(json["pizzas"]),
      country: json['country'],
      houseNumber: json['houseNumber'],
      id: json['id'],
      postCode: json['postCode'],
      street: json['street'],
      status:     json['status'],
      orderDate:  DateTime.parse(json['orderDate'].toString().split("[")[0])
  );

  Map<String, dynamic> toJson() => {
    "city": city,
    "pizzas": pizzas.toJson(),
    "country": country,
    "houseNumber": houseNumber,
    "id": id,
    "postCode": postCode,
    "street": street,
    "status": status,
    "orderDate":  orderDate
  };

}
