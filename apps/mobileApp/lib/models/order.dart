import 'package:app/models/Pizza.dart';

class Order {
  List<Pizza> pizzas;
  String city;
  String country;
  String houseNumber;
  int id;
  DateTime orderDate;
  String postCode;
  int status;
  String street;

  Order(
      {this.pizzas,
      this.city,
      this.country,
      this.houseNumber,
      this.id,
      this.orderDate,
      this.postCode,
      this.status,
      this.street});

  factory Order.fromJson(Map<String, dynamic> json) => Order(
      city: json['city'],
      pizzas: List<Pizza>.from(json["pizzas"].map((e) => Pizza.fromJson(e))),
      country: json['country'],
      houseNumber: json['houseNumber'],
      id: json['id'],
      postCode: json['postCode'],
      street: json['street'],
      status: json['status'],
      orderDate: DateTime.parse(json['orderDate'].toString().replaceFirst("Z", "")) // TODO: why does the server return yyyy-mm-ddZ?
  );

  Map<String, dynamic> toJson() => {
        "pizzas": pizzas.map((e) => e.toJson()).toList(),
      };
}
