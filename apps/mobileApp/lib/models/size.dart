class Size {
  final int id;
  final double basePrice;
  final int diameter;
  final int doughAmount;
  final double toppingFactor;
  final double toppingPrice;


  Size({this.id, this.basePrice, this.diameter, this.doughAmount,
      this.toppingFactor, this.toppingPrice});


  factory Size.fromJson(Map<String, dynamic> json) => Size(
    basePrice: json['basePrice'],
    diameter: json['diameter'],
    doughAmount: json['doughAmount'],
    id: json['id'],
    toppingFactor: json['toppingFactor'],
    toppingPrice: json['toppingPrice'],
  );

  Map<String, dynamic> toJson() => {
    "basePrice": basePrice,
    "diameter":   diameter,
    "doughAmount":  doughAmount,
    "id":   id,
    "toppingFactor": toppingFactor,
    "toppingPrice" : toppingPrice,
  };

  @override
  String toString() {
    return 'Size{id: $id, basePrice: $basePrice, diameter: $diameter, doughAmount: $doughAmount, toppingFactor: $toppingFactor, toppingPrice: $toppingPrice}';
  }
}

