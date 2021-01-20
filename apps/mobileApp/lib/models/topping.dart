class Topping {
  final int baseAmount;
  final int id;
  final String name;
  final String unit;


  Topping({this.baseAmount, this.id, this.name, this.unit});


  factory Topping.fromJson(Map<String, dynamic> json) => Topping(
      baseAmount: json['baseAmount'],
      id: json['id'],
      name: json['name'],
      unit: json['unit'],
  );

  Map<String, dynamic> toJson() => {
    "baseAmount": baseAmount,
    "amount" : baseAmount,
    "id":   id,
    "toppingId":  id,
    "name":  name,
    "unit":   unit,
  };

  @override
  String toString() {
    return 'Topping{baseAmount: $baseAmount, id: $id, name: $name, unit: $unit}';
  }
}

